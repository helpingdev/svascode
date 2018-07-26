/**
 * 
 */
package com.ca.devtest.sv.devtools.svascode.model;

import java.util.Map;

/**
 * @author gaspa03
 *
 */
public final class VirtualServiceModel {
	
	private String name; 
	private String group;
	private String definition;
	
	



	private Map<String, String> configuration;
	
	private String workingFolder;
	
	private String targetedVSE;

	
	/**
	 * @return the group
	 */
	public final String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public final void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return the configuration
	 */
	public final Map<String, String> getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public final void setConfiguration(Map<String, String> configuration) {
		this.configuration = configuration;
	}

	/**
	 * @return the workingFolder
	 */
	public final String getWorkingFolder() {
		return workingFolder;
	}

	/**
	 * @param workingFolder the workingFolder to set
	 */
	public final void setWorkingFolder(String workingFolder) {
		this.workingFolder = workingFolder;
	}

	/**
	 * @return the targetedVSE
	 */
	public final String getTargetedVSE() {
		return targetedVSE;
	}

	/**
	 * @param targetedVSE the targetedVSE to set
	 */
	public final void setTargetedVSE(String targetedVSE) {
		this.targetedVSE = targetedVSE;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
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
