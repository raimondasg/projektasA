package com.exigen.ipb.lmb.sandbox.rest_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class EisRestHttpClient {

	public static void main(String[] args) {
		  try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			//HttpGet getRequest = new HttpGet(
			//	"http://localhost:8090/services/platform-rs/v1/lookups");
			HttpGet getRequest = new HttpGet(
					"http://dev5lmbapp110.eqxdev.exigengroup.com:8080/services/platform-rs/v1/lookups");
			getRequest.addHeader("Authorization", "Basic cWE6cWE=");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                         new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (ClientProtocolException e) {
		
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }

		}
}
