package org.mule.modules.shopify;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.shopify.config.ConnectorConfig;
import org.mule.modules.shopify.config.DefaultResponseHandler;
import org.mule.util.Base64;

@Connector(name="shopify", friendlyName="Shopify")
public class ShopifyConnector {

    @Config
    ConnectorConfig config;

    /**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     */
    @Processor
    public JSONArray getOrders(@Optional @Default("") String fields, @Optional @Default("1") int page, @Optional @Default("50") int limit) throws ClientProtocolException, IOException, JSONException  {
        String resource = "orders";
        
        // If no fields were specified, we will set it as an empty String
        if (fields==null){
        	fields="";
        }
        // We create the HTTP Client
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        // We create the URL
    	String url = String.format("%s/admin/%s.json?fields=%s&page=%s&limit=%s",this.config.getHostname(),resource,fields,page,limit);
    	HttpGet request = new HttpGet(url);
    	// Here we add the Base64 Basic Auth credentials String
    	this.addCredentails(request);
    	String ordersResponseBody = httpClient.execute(request, new DefaultResponseHandler());
    	
    	// We cast a JSONArray from a JSONObject that contained all the arrays returned. For some reason the Shopify API returns a huge JSONObject.
    	// We want a JSONArray so we can make use of its metadata
    	JSONObject jsonObject = new JSONObject(ordersResponseBody);
		return jsonObject.getJSONArray("orders");
    }
    
    private void addCredentails(org.apache.http.client.methods.HttpRequestBase request) throws IOException {
    	String token = Base64.encodeBytes(new String(this.config.getApikey() + ":" + this.config.getPassword()).getBytes());
    	// Here we remove the \n that is placed inside the Base64 String as it is too long and the encryptors add a line break to it.
    	// If we leave this line break the authentication fails
    	request.addHeader("Authorization", "Basic " + token.replaceAll("\n", ""));
    	request.addHeader("Content-Type", "application/json");
    }
    
    @Processor
    public JSONObject createProduct(String requestBody) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
    	String url = String.format("%s/admin/%s.json",this.config.getHostname(),"products");
    	HttpPost request = new HttpPost(url);
    	this.addCredentails(request);
    	request.setEntity(new ByteArrayEntity(requestBody.getBytes()));
    	String ordersResponseBody = httpClient.execute(request, new DefaultResponseHandler());
    	JSONObject jsonObject = new JSONObject(ordersResponseBody);
		return jsonObject.getJSONObject("product");
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }
    
}