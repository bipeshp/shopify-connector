package org.mule.modules.shopify.config;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.mule.util.Base64;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.TestConnectivity;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;

@ConnectionManagement(friendlyName = "Shopify Configuration")
public class ConnectorConfig {
	private static String SHOPIFY_GETONEPRODUCT_RESOURCE = "/admin/products.json?limit=1&fields=id";

	private String apikey;
	private String password;
	@Configurable
	private String hostname;
	private String connectionResponse;
	
    /**
     * Connect
     *
     * @param apikey A apikey
     * @param password A password
     * @param hostname A hostname
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String apikey, @Password String password)
        throws ConnectionException {
    	this.apikey = apikey;
    	this.password = password;
    	
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	
    	try{
    		HttpGet request = new HttpGet(this.hostname+SHOPIFY_GETONEPRODUCT_RESOURCE);
    		String token = Base64.encodeBytes(new String(apikey + ":" + password).getBytes());
    		request.addHeader("Authorization", "Basic " + token.replaceAll("\n", ""));
    		String productResponseBody = httpClient.execute(request, new DefaultResponseHandler());
    		this.connectionResponse = productResponseBody;
    	}catch(Exception e){
    		throw new ConnectionException(org.mule.api.ConnectionExceptionCode.INCORRECT_CREDENTIALS, e.getMessage(), e.getMessage(), e);
    	}
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        this.connectionResponse = null;
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        
        return (!StringUtils.isEmpty(this.connectionResponse));
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return this.connectionResponse;
    }
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
	
//	public static void main(String[] args) throws ClientProtocolException, IOException {
//    	CloseableHttpClient httpClient = HttpClients.createDefault();
//    	
//    		HttpGet request = new HttpGet("https://tip-top-sport.myshopify.com/admin/products.json?limit=1&fields=id");
//    		String token = Base64.encodeBytes(new String("320d694cfbbe8800537abd287d7faff2"  + ":" + "25ac58f57bc207c332b13d94a0b520e6").getBytes());
//    		request.addHeader("Authorization", "Basic " + token.replaceAll("\n", ""));
//    		String productResponseBody = httpClient.execute(request, new DefaultResponseHandler());
//    		System.out.println(productResponseBody);
//		
//	}
}