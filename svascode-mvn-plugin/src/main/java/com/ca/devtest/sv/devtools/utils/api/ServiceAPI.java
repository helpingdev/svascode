package com.ca.devtest.sv.devtools.utils.api;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ca.devtest.sv.devtools.svascode.model.VirtualServiceModel;

public class ServiceAPI {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceAPI.class);
	private static String FormattedUrl = "http://%s:1507/devtest/api/sv/vse/service/update/%s/%s";
	private static final String jsonMessage = "\"{\"capacity\":%d,\"groupTag\":\"%s\",\"thinkScale\":%d,\"autoRestart\":true,\"executionMode\":\"%s\"}\"";

	/**
	 * @param service
	 * @throws IOException
	 */
	public static void updateServiceStatus(String registryHostName, String vseName, String userName, String password,
			VirtualServiceModel service) throws IOException {

		HttpClient httpClient = HttpClients.createDefault();
		String deployedName = service.getGroup() + "." + service.getName();
		String urlPost = String.format(FormattedUrl, registryHostName, vseName, deployedName);

		HttpPost post = new HttpPost(urlPost);
		String message = String.format(jsonMessage, service.getCapacity(), service.getGroup(), service.getThinkScale(),
				service.getExecutionMode());

		StringEntity param = new StringEntity(message);
		post.setEntity(param);

		post.setHeader("Authorization", "Bearer jijd8jvudt298icf16ng2a1gutq0vmli");
		post.addHeader("content-type", "application/json;charset=UTF-8");

		HttpResponse response = httpClient.execute(post);

		LOG.info("Change service Status " + deployedName + "....");
		HttpEntity entity = response.getEntity();

		LOG.info("Server Response Code :" + response.getStatusLine().getStatusCode());
		if (LOG.isDebugEnabled()) {
			String responseString = EntityUtils.toString(entity, "UTF-8");
			LOG.debug("Server respond :" + responseString);
		}
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

			LOG.error("Error changing service  status:" + deployedName);
			throw new HttpResponseException(response.getStatusLine().getStatusCode(),
					"VS creation did not complete normally");
		}

	}

}
