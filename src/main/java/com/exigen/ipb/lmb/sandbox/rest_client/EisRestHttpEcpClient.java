package com.exigen.ipb.lmb.sandbox.rest_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;

import de.tudarmstadt.ukp.shibhttpclient.ShibHttpClient;

public class EisRestHttpEcpClient {

	public static void main(String[] args) {
		try {

			String aIdpUrl = "http://localhost:8093/lmb-saml-idp-app/idp/profile/SAML2/SOAP/ECP";
			String aUsername = "admin";
			String aPassword = "admin";
			HttpClient httpClient = new ShibHttpClient(aIdpUrl, aUsername, aPassword, true);
			HttpPost postRequest = new HttpPost(
				"http://localhost:8090/services/platform-rs/v1/lookups");
			
			HttpResponse response = httpClient.execute(postRequest);

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
