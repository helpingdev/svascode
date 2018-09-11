/**
 * 
 */
package com.ca.devtest.sv.devtools.svascode.model;

import com.ca.devtest.sv.devtools.services.VirtualService;

/**
 * @author gaspa03
 * Temporary class should be redesigned 
 *
 */
public final class CompositeVirtualService {

	private VirtualServiceModel model;
	private VirtualService service;
	
	public CompositeVirtualService (VirtualServiceModel model,VirtualService service){
		super();
		this.model=model;
		this.service=service;
		
	}
	/**
	 * @return the model
	 */
	public final VirtualServiceModel getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public final void setModel(VirtualServiceModel model) {
		this.model = model;
	}
	/**
	 * @return the service
	 */
	public final VirtualService getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public final void setService(VirtualService service) {
		this.service = service;
	}
}
