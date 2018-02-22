package com.ca.devtest.sv.devtools;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({"classpath:local-svascode.porperties","classpath:svascode.porperties"})
public interface SVasCodeConfig extends Config {

	/**
	 * Registry server name. By default 'localhost'.
	 * 
	 * @return registry server name.
	 */
	@Key("devtest.registry")
	String registryHost();

	/**
	 * VSE name. By default 'VSE'.
	 * 
	 * @return VSE name
	 */
	@Key("devtest.vse")
	String deployServiceToVse();

	/**
	 * Devtest user. By default 'svpower'
	 * 
	 * @return devtest user
	 */
	@Key("devtest.login")
	String login();

	/**
	 * Devtest password. By default 'svpower'
	 * 
	 * @return devtest password
	 */
	@Key("devtest.password")
	String password();
	
	/**
	 * Protocol to access API. By default 'http'
	 * 
	 * @return protocol to access api
	 */
	@Key("devtest.protocol")
	String protocol() ;

}
