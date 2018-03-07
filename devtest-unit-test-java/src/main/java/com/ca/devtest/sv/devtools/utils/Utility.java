/**
 * 
 */
package com.ca.devtest.sv.devtools.utils;

import com.ca.devtest.sv.devtools.annotation.Parameter;
import com.ca.devtest.sv.devtools.protocol.builder.ParamatrizedBuilder;

/**
 * @author gaspa03
 *
 */
public class Utility {

	private static final String OS = System.getProperty("os.name").toLowerCase();
	/**
	 * @param builder
	 * @param parameters
	 */
	public static void addParamsToBuilder(ParamatrizedBuilder builder, Parameter[] parameters) {

		for (Parameter parameter : parameters) {
			builder.addKeyValue(parameter.name(), parameter.value());
		}

	}
	
	/**
	 * @return OS userName
	 */
	public static String getUserName() {
		return System.getProperty(OS.indexOf("win") >= 0 ? "USERNAME" : "user.name").toLowerCase();

	}
	
	/**
	 * @return appropriate classpath Separator
	 */
	public static  String classpathSeparator() {

		return OS.indexOf("win") >= 0 ? ";" : ":";

	}
}
