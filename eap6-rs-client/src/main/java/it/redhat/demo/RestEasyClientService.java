package it.redhat.demo;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class RestEasyClientService {

	/**
	 * see https://access.redhat.com/solutions/752103
	 */
	public String makeGet() {

		RequestConfig config = RequestConfig.custom()
			.setConnectTimeout( 100 )
			.setSocketTimeout( 100 )
			.setConnectionRequestTimeout( 100 )
			.build();

		CloseableHttpClient client = HttpClientBuilder.create()
				.setDefaultRequestConfig( config )
				.build();

		ApacheHttpClient4Executor executor = new ApacheHttpClient4Executor(client) {
			@Override
			public void loadHttpMethod(ClientRequest request,
									   HttpRequestBase httpMethod) throws Exception {
				httpMethod.setConfig(config);
				super.loadHttpMethod(request, httpMethod);
			}
		};

		ClientRequest request = new ClientRequest("http://localhost:8080/eap6-rs", executor);

		request.accept("application/json");

		ClientResponse<String> response = null;

		try {
			response = request.get( String.class );
		}
		catch (Exception e) {
			throw new RuntimeException( e );
		}

		return response.getEntity();

	}

}
