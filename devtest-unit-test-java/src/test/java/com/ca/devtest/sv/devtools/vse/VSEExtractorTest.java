package com.ca.devtest.sv.devtools.vse;

import org.junit.Test;

public class VSEExtractorTest {

	

	@Test
	public void startVse() throws Exception {
		VirtualServerEnvironmentWrapper vseWrapper= new VirtualServerEnvironmentWrapper();
		vseWrapper.start(null);
		Thread.sleep(10000);
		vseWrapper.stop();

	}

}
