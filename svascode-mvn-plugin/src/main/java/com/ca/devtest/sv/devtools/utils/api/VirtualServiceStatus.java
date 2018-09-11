/**
 * 
 */
package com.ca.devtest.sv.devtools.utils.api;

/**
 * @author gaspa03
 *
 */
public class VirtualServiceStatus {

	boolean autoRestart=true;
	int capacity=1;
	String executionMode="EFFICIENT";
	String groupTag="";
	int thinkScale=	100;
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
	/**
	 * @return the groupTag
	 */
	public final String getGroupTag() {
		return groupTag;
	}
	/**
	 * @param groupTag the groupTag to set
	 */
	public final void setGroupTag(String groupTag) {
		this.groupTag = groupTag;
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
	
}
