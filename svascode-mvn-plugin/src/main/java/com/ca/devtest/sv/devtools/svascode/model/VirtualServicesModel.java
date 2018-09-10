/**
 * 
 */
package com.ca.devtest.sv.devtools.svascode.model;

import java.util.List;

/**
 * @author gaspa03
 *
 */
public final class VirtualServicesModel {
	
	private String definition;
	
	private List<VirtualServiceModel> services;

	/**
	 * @return the services
	 */
	public final List<VirtualServiceModel> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public final void setServices(List<VirtualServiceModel> service) {
		this.services = service;
	}

	/**
	 * @return the definition
	 */
	public final String getDefinition() {
		return definition;
	}

	/**
	 * @param definition the definition to set
	 */
	public final void setDefinition(String definition) {
		this.definition = definition;
	}
}
