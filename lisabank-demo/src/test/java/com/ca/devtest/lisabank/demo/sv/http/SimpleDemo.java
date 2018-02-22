/**
 * 
 */
package com.ca.devtest.lisabank.demo.sv.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ca.devtest.lisabank.demo.LisaBankClientApplication;
import com.ca.devtest.lisabank.demo.business.BankService;
import com.ca.devtest.lisabank.wsdl.User;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualServer;
import com.ca.devtest.sv.devtools.annotation.DevTestVirtualService;
import com.ca.devtest.sv.devtools.annotation.Parameter;
import com.ca.devtest.sv.devtools.annotation.Protocol;
import com.ca.devtest.sv.devtools.annotation.ProtocolType;
import com.ca.devtest.sv.devtools.junit.VirtualServicesRule;

/**
 * @author pascal.gasp@ca.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LisaBankClientApplication.class)
@DevTestVirtualServer(registryHost = "localhost", deployServiceToVse = "VSE")
public class SimpleDemo {
	static final Log logger = LogFactory.getLog(SimpleDemo.class);
	@Autowired
	private BankService bankServices;
	@Rule
	public VirtualServicesRule rules = new VirtualServicesRule();

	@DevTestVirtualService(serviceName = "getListUser", basePath = "/itkoExamples/EJB3UserControlBean", port = 9081, 
			workingFolder = "UserServiceTest/getListUser/EJB3UserControlBean", 
			requestDataProtocol = {
			@Protocol(ProtocolType.DPH_SOAP) })
	@Test
	public void getListUser() {
		User[] users = bankServices.getListUser();
		assertNotNull(users);
		printUsers(users);
		assertEquals(9, users.length);
	}
/*	@DevTestVirtualService(serviceName = "getListUser0", 
			basePath = "/itkoExamples/EJB3UserControlBean", 
			port = 9081, workingFolder = "Demo/limiteTest", 
			requestDataProtocol = {
			@Protocol(ProtocolType.DPH_SOAP) })
	@Test
	public void getListUser0() {
		User[] users = bankServices.getListUser();
		assertNotNull(users);
		assertEquals(0, users.length);
	}

	@DevTestVirtualService(serviceName = "getListUserTemplate", 
			basePath = "/itkoExamples/EJB3UserControlBean", 
			port = 9081, workingFolder = "UserServiceTest/getListUser/Volvo", 
			parameters={@Parameter(name="myVariable", value="HELLO")},
			requestDataProtocol = {
			@Protocol(ProtocolType.DPH_SOAP) })
	@Test
	public void getListUserTemplate() {
		User[] users = bankServices.getListUser();
		assertNotNull(users);
		assertEquals(1, users.length);
		printUsers(users);
	}
*/
	private void printUsers(User[] users) {
		for (User user : users) {
			logger.info(user.getFname() + " " + user.getLname() + " " + user.getLogin());
		}

	}
}
