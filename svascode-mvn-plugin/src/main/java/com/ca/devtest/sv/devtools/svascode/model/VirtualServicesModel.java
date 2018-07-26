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
	
	private List<VirtualServiceModel> service;

	/**
	 * @return the service
	 */
	public final List<VirtualServiceModel> getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public final void setService(List<VirtualServiceModel> service) {
		this.service = service;
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
