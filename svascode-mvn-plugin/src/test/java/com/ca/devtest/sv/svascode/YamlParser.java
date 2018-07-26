package com.ca.devtest.sv.svascode;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.ca.devtest.sv.devtools.svascode.model.VirtualServicesModel;
import com.google.common.io.ByteStreams;

public class YamlParser {

	@Test
	public void servicesParser() throws URISyntaxException {
		
		File servicesFile = getServicesFile();
		try {
			InputStream is = new FileInputStream(servicesFile);
			VirtualServicesModel services= new Yaml().loadAs(
	                new ByteArrayInputStream(ByteStreams.toByteArray(is))
	                , VirtualServicesModel.class);
			System.out.println(services);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File getServicesFile() throws URISyntaxException {
		return new File(getClass().getClassLoader().getResource("services.yml").toURI());

	}
}
