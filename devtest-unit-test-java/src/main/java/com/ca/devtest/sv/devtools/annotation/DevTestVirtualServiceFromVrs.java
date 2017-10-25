/**
 * 
 */
package com.ca.devtest.sv.devtools.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Define virtual service using VRS fragment file. <br/>
 * 
 * Attributes are :
 * <ul>
 * <li>serviceName : virtual service name</li>
 * <li>workingFolder : folder of request/response files</li>
 * <li>vrsConfig : VRS file name</li>
 * </ul>
 * 
 * @author gaspa03, bboulch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Repeatable(DevTestVirtualServicesFromVrs.class)
public @interface DevTestVirtualServiceFromVrs {
	String serviceName();
	String workingFolder();
	Config vrsConfig();
	
	
}


