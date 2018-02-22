/**
 * 
 */
package com.ca.devtest.sv.devtools.vse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.ca.devtest.sv.devtools.utils.FreePortFinder;

/**
 * @author gaspa03
 *
 */
public final class VirtualServerEnvironmentWrapper {

	String OS = System.getProperty("os.name").toLowerCase();

	private Process vseProcess = null;
	String target = "/Users/gaspa03/00-CA/01-Tools/00_DevTest/proto/DevTest-VSE-10.1";
	File jarFile = new File("/Users/gaspa03/00-CA/01-Tools/00_DevTest/proto/DevTest-VSE-10.1.jar");
	File directoryTaget = new File(target);

	/**
	 * first time install VSE from Jar and start VSE in fork JVM
	 * 
	 * @return true if VSE is started
	 * @throws IOException
	 */
	public boolean start(String registry) throws IOException {

		install(directoryTaget, jarFile);

		String vseName = "vse-" + getUserName().toLowerCase();
		if (StringUtils.isEmpty(registry))
			registry = "tcp://127.0.0.1:2010/Registry";

		vseProcess = buildProcess(directoryTaget, vseName, registry).start();

		return vseProcess.isAlive();
	}

	/**
	 * stop VSE process
	 * 
	 * @return true if VSE is started
	 * @throws RuntimeException
	 *             if start method is not called before
	 */
	public boolean stop() throws RuntimeException {

		if (null == vseProcess)
			throw new RuntimeException("Error: You should start VSE before stoped!!");
		vseProcess.destroyForcibly();

		return vseProcess.isAlive();
	}

	/**
	 * unzip DevTest jar if not exist
	 * 
	 * @param directory
	 *            target directory
	 * @param version
	 * @throws IOException
	 */
	private void install(File directory, File jarFile) throws IOException {

		// to access a Jar content from http
		URL url = new URL("jar:file:" + jarFile + "!/");
		JarURLConnection conn = (JarURLConnection) url.openConnection();
		URL urlJar = conn.getJarFileURL();
		File zipFile = new File(urlJar.getFile());
		// if the output directory doesn't exist, create it
		if (!directory.exists())
			directory.mkdirs();
		File lisaProperties = new File(directory, "lisa.properties");
		if (!lisaProperties.exists()) {

			// buffer for read and write data to file
			byte[] buffer = new byte[2048];

			try {
				FileInputStream fInput = new FileInputStream(zipFile);
				ZipInputStream zipInput = new ZipInputStream(fInput);

				ZipEntry entry = zipInput.getNextEntry();

				while (entry != null) {
					String entryName = entry.getName();
					File file = new File(directory, entryName);

					System.out.println("Unzip file " + entryName + " to " + file.getAbsolutePath());

					// create the directories of the zip directory
					if (entry.isDirectory()) {
						File newDir = new File(file.getAbsolutePath());
						if (!newDir.exists()) {
							boolean success = newDir.mkdirs();
							if (success == false) {
								System.out.println("Problem creating Folder");
							}
						}
					} else {
						FileOutputStream fOutput = new FileOutputStream(file);
						int count = 0;
						while ((count = zipInput.read(buffer)) > 0) {
							// write 'count' bytes to the file output stream
							fOutput.write(buffer, 0, count);
						}
						fOutput.close();
					}
					// close ZipEntry and take the next one
					zipInput.closeEntry();
					entry = zipInput.getNextEntry();
				}

				// close the last ZipEntry
				zipInput.closeEntry();

				zipInput.close();
				fInput.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @param devTestHome
	 * @return
	 * @throws IOException
	 */
	private ProcessBuilder buildProcess(File devTestHome, String vseName, String registry) throws IOException {

		String separator = System.getProperty("file.separator");
		String devTestHome_Env = devTestHome.getCanonicalPath();

		String HeapDumpPath = "-XX:HeapDumpPath=" + devTestHome_Env + separator + "tmp";
		String HeapDumpOnOutOfMemoryError = "-XX:+HeapDumpOnOutOfMemoryError";
		String Xmx = "-Xmx256m";
		String javaEndor = "-Djava.endorsed.dirs=" + devTestHome_Env + separator + "lib" + separator + "endorsed";
		String lisa_home = "-DLISA_HOME=" + devTestHome_Env;
		String lisa_tmp = "-Dlisa.tmpdir=" + devTestHome_Env + separator + "tmp";
		String java_tmpdir = "-Djava.io.tmpdir=" + devTestHome_Env + separator + "tmp";
		String lisa_log = "-DLISA_LOG=" + vseName + ".log";
		String file_encoding = "-Dfile.encoding=UTF-8";
		String jmx = "-Dcom.sun.management.jmxremote";
		String security = "-Djava.security.policy=" + devTestHome_Env + separator + "lisa.permissions";
		String ipv4Stack = "-Djava.net.preferIPv4Stack=true";
		String language = "-Duser.language=en";
		String bindToAddress = "-Dlisa.net.bindToAddress=127.0.0.1";
		int port = FreePortFinder.nextFreePort(2013, 2050);
		String vse = "--name=tcp://127.0.0.1:" + port + "/" + vseName;
		String registryName = "--registry=" + registry;
		String path = System.getProperty("java.home") + separator + "bin" + separator + "java";
		String[] arguments = new String[] { path, "-server", HeapDumpOnOutOfMemoryError, HeapDumpPath, Xmx, javaEndor,
				lisa_home, lisa_log, file_encoding, jmx, security, ipv4Stack, language, bindToAddress, lisa_tmp,
				java_tmpdir, "-classpath", classpath(devTestHome),
				"com.itko.lisa.coordinator.VirtualServiceEnvironmentImpl", vse, registryName };
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(arguments);
		processBuilder.inheritIO();
		return processBuilder;
	}

	/**
	 * @param path
	 * @return a classpath string from Path
	 */
	private String classpath(File path) {
		String[] extensions = new String[] { "jar", "zip" };
		StringBuilder builder = new StringBuilder();
		File libFolder = new File(path, "lib");
		Collection<File> jars = FileUtils.listFiles(libFolder, extensions, true);
		for (File jar : jars) {
			builder.append(jar.getAbsolutePath());
			builder.append(classpathSeparator());
		}

		return builder.toString();
	}

	/**
	 * @return appropriate classpath Separator
	 */
	private String classpathSeparator() {

		return OS.indexOf("win") >= 0 ? ";" : ":";

	}

	/**
	 * @return OS userName
	 */
	private String getUserName() {
		return System.getProperty(OS.indexOf("win") >= 0 ? "USERNAME" : "user.name");

	}
}
