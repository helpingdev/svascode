package com.ca.devtest.sv.devtools.svascode.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import com.ca.devtest.sv.devtools.DevTestClient;
import com.ca.devtest.sv.devtools.VirtualServiceEnvironment;
import com.ca.devtest.sv.devtools.protocol.builder.ParamatrizedBuilder;
import com.ca.devtest.sv.devtools.protocol.builder.TransportProtocolFromVrsBuilder;
import com.ca.devtest.sv.devtools.services.ExecutionModeType;
import com.ca.devtest.sv.devtools.services.VirtualService;
import com.ca.devtest.sv.devtools.services.builder.VirtualServiceBuilder;
import com.ca.devtest.sv.devtools.svascode.helper.VirtualServiceHelper;
import com.ca.devtest.sv.devtools.svascode.model.VirtualServiceModel;
import com.ca.devtest.sv.devtools.svascode.model.VirtualServicesModel;

/**
 * A goal to generate code.
 * 
 * @goal deploySV
 * @phase generate-sources
 */

public class SVasCode extends AbstractMojo {

	/**
	 * @parameter defaultValue="localhost"
	 */
	private String registryHostName;

	/**
	 * @parameter defaultValue="1505"
	 */
	private String port;

	/**
	 * @parameter defaultValue="svpower"
	 */
	private String user;

	/**
	 * @parameter defaultValue="svpower"
	 */
	private String password;

	File workspaceDir = null;

	/**
	 * @parameter defaultValue = "${project.basedir}/src/main/resources"
	 */
	private String workspace;

	/**
	 * @parameter defaultValue =
	 *            "${project.basedir}/src/main/resources/services.yml"
	 */
	private String servicesDefinitionFile;

	public void execute() throws MojoExecutionException {

		getLog().info("Load service configuration file <" + servicesDefinitionFile + "... ");
		workspaceDir = new File(workspace);
		if (!workspaceDir.exists()) {
			getLog().error("Workspace <" + workspace + "> should be an existing folder...");
			throw new MojoExecutionException("Workspace <" + workspace + "> should be an existing folder...");
		}

		VirtualServicesModel services = VirtualServiceHelper
				.loadVirtualServiceDefinitions(new File(servicesDefinitionFile));

		getLog().info("Should deploy  " + services.getServices().size() + " services.");

		Map<String, List<VirtualService>> virtualServices = null;
		try {
			virtualServices=buildServices(services);
			Set<String> vseNames=virtualServices.keySet();
			for (String vseName : vseNames) {
				checkServer(vseName);
				cleanServer(vseName);
				deployVirtualServices(virtualServices.get(vseName));
			}
			
			
		} catch (IOException e) {
			getLog().error("Erreur during building Virtual Services...", e);
			throw new MojoExecutionException("Erreur during building Virtual Services...", e);
		}

		

		getLog().info("Start deploying SV on server... ");

	}

	/**
	 * @param vseName
	 * @throws IOException
	 */
	private void cleanServer(String vseName) throws IOException {
		
		VirtualServiceEnvironment vse= new VirtualServiceEnvironment(registryHostName, vseName,user,password, null);
		vse.cleanServer();
	}

	private void checkServer(String vseName) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param virtualServices
	 */
	private void deployVirtualServices(List<VirtualService> virtualServices) {

		for (VirtualService virtualService : virtualServices) {
			getLog().info("deploying service <" + virtualService.getDeployedName() + ">");
			try {
				virtualService.deploy();
			} catch (IOException e) {
				getLog().warn("Error during deployement of service <" + virtualService.getDeployedName() + ">");
			}
		}

	}

	/**
	 * @param services
	 * @return
	 * @throws IOException
	 */
	private Map<String, List<VirtualService>> buildServices(VirtualServicesModel services) throws IOException {

		List<VirtualServiceModel> models = services.getServices();
		Map<String, List<VirtualService>> result = new HashMap<String, List<VirtualService>>();
		List<VirtualService> virtualServices = null;
		for (VirtualServiceModel virtualServiceModel : models) {
			File rrpairsFolder = new File(workspaceDir, virtualServiceModel.getWorkingFolder());
			File vrsFile = new File(workspaceDir, virtualServiceModel.getDefinition());

			List<String> tagetedVSE = virtualServiceModel.getTargetedVSE();

			// build Transport Protocol
			TransportProtocolFromVrsBuilder transportBuilder = new TransportProtocolFromVrsBuilder(vrsFile);
			// Optional:fill out parameter in your VRS file
			propagateConfig(transportBuilder, virtualServiceModel.getConfiguration());
			for (String vseName : tagetedVSE) {
				// group VS by TargetedVSE
				if (!result.containsKey(vseName)) {
					virtualServices = new ArrayList<VirtualService>();
					result.put(vseName, virtualServices);
				} else {
					virtualServices = result.get(vseName);
				}
				DevTestClient devtest = new DevTestClient(registryHostName, vseName, user, password,
						virtualServiceModel.getGroup());
				

				VirtualServiceBuilder vsbuilder = devtest.fromRRPairs(virtualServiceModel.getName(), rrpairsFolder);
				vsbuilder.over(transportBuilder.build());

				// Optional : fill out parameters in you rrpairs file
				propagateConfig(vsbuilder, virtualServiceModel.getConfiguration());
				// Virtual Service
				// add Transport Protocol
				vsbuilder.over(transportBuilder.build());
				vsbuilder.setCapacity(virtualServiceModel.getCapacity());
				vsbuilder.setAutoRestartEnabled(virtualServiceModel.isAutoRestart());
				vsbuilder.setExecutionMode(ExecutionModeType.valueIgnoreCaseOf(virtualServiceModel.getExecutionMode()));
				vsbuilder.setThinkScale(virtualServiceModel.getThinkScale());
				VirtualService sv = vsbuilder.build();
				virtualServices.add(sv);

			}

		}
		return result;
	}

	/**
	 * @param paramatrizedBuilder
	 * @param configuration
	 */
	private void propagateConfig(ParamatrizedBuilder paramatrizedBuilder, Map<String, String> configuration) {

		Set<String> keys = configuration.keySet();
		for (String key : keys) {

			paramatrizedBuilder.addKeyValue(key, configuration.get(key));
		}
	}
}
