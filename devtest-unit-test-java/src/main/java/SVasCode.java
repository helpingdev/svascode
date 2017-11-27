import org.aeonbits.owner.Config;

public interface SVasCode extends Config {

	/**
	 * Registry server name. By default 'localhost'.
	 * 
	 * @return registry server name.
	 */
	@DefaultValue("localhost")
	String registryHost();

	/**
	 * VSE name. By default 'VSE'.
	 * 
	 * @return VSE name
	 */
	@DefaultValue("VSE")
	String deployServiceToVse();

	/**
	 * Devtest user. By default 'svpower'
	 * 
	 * @return devtest user
	 */
	@DefaultValue("svpower")
	String login();

	/**
	 * Devtest password. By default 'svpower'
	 * 
	 * @return devtest password
	 */
	@DefaultValue("svpower")
	String password();

}
