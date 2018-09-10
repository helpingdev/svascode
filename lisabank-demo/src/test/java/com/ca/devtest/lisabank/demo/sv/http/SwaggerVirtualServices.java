package com.ca.devtest.lisabank.demo.sv.http;

import static org.junit.Assert.*;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ca.devtest.lisabank.demo.LisaBankClientApplication;
import com.ca.devtest.sv.devtools.annotation.Config;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServer;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServiceFromVrs;
import com.ca.devtest.sv.devtools.annotation.Parameter;
import com.ca.devtest.sv.devtools.junit.VirtualServiceClassScopeRule;
import com.ca.devtest.sv.devtools.junit.VirtualServicesRule;

@SpringBootTest(classes = LisaBankClientApplication.class)
@DevTestVirtualServer()
public class SwaggerVirtualServices {

	@ClassRule
	public static VirtualServiceClassScopeRule clazzRule = new VirtualServiceClassScopeRule();
	@Rule
	public VirtualServicesRule rules = new VirtualServicesRule();

	@DevTestVirtualServiceFromVrs(serviceName = "swagger", workingFolder = "swagger", vrsConfig = @Config(value = "swagger.vrs", parameters = {
			@Parameter(name = "port", value = "8010"), @Parameter(name = "basePath", value = "/") }))
	@Test
	public void test() {
		System.out.println("OK");
	}

}
