/**
 * 
 */
package com.ca.devtest.lisabank.demo.sv.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ca.devtest.lisabank.demo.LisaBankClientApplication;
import com.ca.devtest.lisabank.demo.business.BankService;
import com.ca.devtest.lisabank.wsdl.User;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServer;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualService;
import com.ca.devtest.sv.devtools.annotation.Protocol;
import com.ca.devtest.sv.devtools.annotation.ProtocolType;
import com.ca.devtest.sv.devtools.junit.VirtualServiceClassScopeRule;

/**
 * @author pascal.gasp@ca.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LisaBankClientApplication.class)
// Mark as Test using CA Service Virtualization
@DevTestVirtualServer()
// Define Virtual Service with Clazz scope => Deploy once for all methods
@DevTestVirtualService(serviceName = "VSClazzScopeSimpleDemo",
basePath = "/itkoExamples/EJB3UserControlBean",
port = 9081, 
workingFolder = "UserServiceTest/getListUser/EJB3UserControlBean", 
requestDataProtocol = {
		@Protocol(ProtocolType.DPH_SOAP) })

public class VSClazzScopeSimpleDemo {
	static final Log logger = LogFactory.getLog(VSClazzScopeSimpleDemo.class);
	@Autowired
	private BankService bankServices;

	// handle VS with Class scope
	@ClassRule
	public static VirtualServiceClassScopeRule clazzRule = new VirtualServiceClassScopeRule();

	@Test
	public void getListUser() {
		User[] users = bankServices.getListUser();
		assertNotNull(users);
		printUsers(users);
		assertEquals(9, users.length);
	}

	private void printUsers(User[] users) {
		for (User user : users) {
			logger.info(user.getFname() + " " + user.getLname() + " " + user.getLogin());
		}

	}
}
