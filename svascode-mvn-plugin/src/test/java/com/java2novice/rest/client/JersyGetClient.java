package com.java2novice.rest.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;


public class JersyGetClient {
	public static void main(String[] args) throws IOException
	{
	    httpGETCollectionExample();
	}
	 
	private static void httpGETCollectionExample()
	{
	   
	 
	    HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
	    Client client = ClientBuilder.newClient( new ClientConfig().register( feature) );
	    WebTarget webTarget = client.target("http://localhost:1505/devtest/api/sv/vse/service/update/VSE/carsV2.carsV2").path("vses");
	     
	    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
	    Response response = invocationBuilder.get();
	     
	    
	}
}