/**
 * 
 */
package com.ca.devtest.sv.devtools.svascode.model;

import java.util.List;
import java.util.Map;

/**
 * @author gaspa03
 *
 */
public final class VirtualServiceModel {
	
	private String name; 
	private String group;
	private String definition;
	private int capacity =1;
	private int thinkScale=100;
	private boolean autoRestart=true;
	private String executionMode="EFFICIENT";

	private Map<String, String> configuration;
	
	private String workingFolder;
	
	private List<String> targetedVSE;

	
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

	/**
	 * @return the targetedVSE
	 */
	public final List<String> getTargetedVSE() {
		return targetedVSE;
	}

	/**
	 * @param targetedVSE the targetedVSE to set
	 */
	public final void setTargetedVSE(List<String> targetedVSE) {
		this.targetedVSE = targetedVSE;
	}

	/**
	 * @return the capacity
	 */
	public final int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public final void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the thinkScale
	 */
	public final int getThinkScale() {
		return thinkScale;
	}

	/**
	 * @param thinkScale the thinkScale to set
	 */
	public final void setThinkScale(int thinkScale) {
		this.thinkScale = thinkScale;
	}

	/**
	 * @return the autoRestart
	 */
	public final boolean isAutoRestart() {
		return autoRestart;
	}

	/**
	 * @param autoRestart the autoRestart to set
	 */
	public final void setAutoRestart(boolean autoRestart) {
		this.autoRestart = autoRestart;
	}

	/**
	 * @return the executionMode
	 */
	public final String getExecutionMode() {
		return executionMode;
	}

	/**
	 * @param executionMode the executionMode to set
	 */
	public final void setExecutionMode(String executionMode) {
		this.executionMode = executionMode;
	}

	

}
