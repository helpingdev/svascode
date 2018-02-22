package com.ca.devtest.sv.devtools.utils;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang.StringUtils;

import com.ca.devtest.sv.devtools.SVasCodeConfig;

/**
 * Merge Annotation configuration with svascode.properties file
 * 
 * @author gaspa03
 *
 */
public final class SvAsCodeConfigUtil {

	private static final SVasCodeConfig CONFIG = ConfigFactory.create(SVasCodeConfig.class, System.getProperties(),
			System.getenv());

	


	/**
	 * Registry server name. By default 'localhost'.
	 * 
	 * @return registry server name.
	 */
	public static String registryHost(String registryHost) {

		return StringUtils.defaultIfEmpty(registryHost, CONFIG.registryHost());
	}

	/**
	 * @param devTestServer
	 * @return
	 */
	public static String deployServiceToVse(String deployServiceToVse) {

		 return StringUtils.defaultIfEmpty(deployServiceToVse, CONFIG.deployServiceToVse());
	}

	/**
	 * Devtest poweruser login. 
	 * 
	 * @return devtest user
	 */
	public static String login(String login) {

		 return StringUtils.defaultIfEmpty(login, CONFIG.login());
	}

	/**
	 * 
	 * @param group
	 * @return groupName of SV. If null return ThreadName
	 */
	public static String group(String group) {

		 return StringUtils.defaultIfEmpty(group, Thread.currentThread().getName());
	}
	
	/**
	 * Devtest poweruser password. 
	 * 
	 * @return devtest user
	 */
	public static String password(String password) {

		 return StringUtils.defaultIfEmpty(password, CONFIG.password());
	}

	/**
	 * Devtest API protocol . 
	 * 
	 * @return devtest user
	 */
	public static String protocol(String protocol) {

		return StringUtils.defaultIfEmpty(protocol, CONFIG.protocol());
	}

	
}
