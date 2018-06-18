package com.bnpparibas.expe.svasacode;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.ca.devtest.sv.devtools.DevTestClient;
import com.ca.devtest.sv.devtools.protocol.builder.TransportProtocolFromVrsBuilder;
import com.ca.devtest.sv.devtools.services.VirtualService;
import com.ca.devtest.sv.devtools.services.builder.VirtualServiceBuilder;

/**
 * 
 */

/**
 * @author gaspa03
 *
 */
public class SvAsCodeAPI {
	
	@Test
	public void testAPI() throws IOException{
		
		
		
		File rrpairsFolder=new File("/Users/gaspa03/git/svascode/devtest-unit-test-java/src/test/resources/rrpairs/search_client");
		File vrsFile=new File(rrpairsFolder,"vrs_template.xml");
		
		// Create 
		DevTestClient devtest= new DevTestClient("devtest-ubuntu.local", "VSE", "svpower", "svpower", "demo");
		
		// build Transport Protocol
		TransportProtocolFromVrsBuilder transportBuilder = new TransportProtocolFromVrsBuilder(vrsFile);
		//Optional:fill out parameter in your VRS file
		transportBuilder.addParameter("port", "8081");
		
		//  Virtual Service builder
		VirtualServiceBuilder vsbuilder=devtest.fromRRPairs("myservice",rrpairsFolder);
		vsbuilder.over(transportBuilder.build());
		
		// Optional : fill out parameters in you rrpairs file
		vsbuilder.addKeyValue("clientID", "12345");
		
		//  Virtual Service 
		VirtualService sv =vsbuilder.build();
		//  Deploy VS
		sv.deploy();
		
		//  unDeploy VS
		sv.unDeploy();
		
	}

}
