
package com.ca.devtest.lisabank.demo.sv.vsm;

import static org.junit.Assert.fail;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.ca.devtest.sv.devtools.annotation.Config;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServer;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualService;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServiceFromVrs;
import com.ca.devtest.sv.devtools.annotation.Parameter;
import com.ca.devtest.sv.devtools.annotation.VirtualServiceType;
import com.ca.devtest.sv.devtools.junit.VirtualServiceClassScopeRule;
import com.ca.devtest.sv.devtools.junit.VirtualServicesRule;

@DevTestVirtualServer(deployServiceToVse = "VSE",groupName="Test")

public class ExistingVirtualServiceTest {

	// handle VS with Class scope
//	@ClassRule
//	public static VirtualServiceClassScopeRule clazzRule = new VirtualServiceClassScopeRule();
	//
	@Rule
	public VirtualServicesRule rules = new VirtualServicesRule();
	
	
	
	@DevTestVirtualService(serviceName = "Proxy", type = VirtualServiceType.VSM, workingFolder = "vsm/lisabank" )
	@Test
	public void test1() {
		System.out.println("Not yet implemented");
	}

	
}
