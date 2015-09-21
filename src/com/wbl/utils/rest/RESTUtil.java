package com.wbl.utils.rest;

import com.wbl.utils.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by svelupula on 8/25/2015.
 */
public class RESTUtil {

    public final Configuration _configuration;
    private Logger _logger;
    private HttpUriRequest request;
    private HttpResponse response;
    public Headers header = null;//Header[] headers; //response.getAllHeaders();
    private JSONWrapper json = null;
    private String uri;
//    private JSONArray jsonArray;
    //private JSONObject jsonObj;

    public RESTUtil(Configuration configuration) {
        _configuration = configuration;
        _logger = Logger.getLogger(RESTUtil.class);
        this.header = new Headers();
    }

    public JSONWrapper getJson() {
        return json;
    }

    public void setJson(JSONWrapper json) {
        this.json = json;
    }

    private void get(String resource, String contentType, String accept, String authorization) throws Exception {
       //  request = new HttpGet(_configuration.BaseURI + resource);
        request = new HttpGet(getUri() + resource);
        if (contentType != null)
            request.setHeader("Content-Type", contentType);
        if (accept != null)
            request.setHeader("Accept", accept);
        if (authorization != null)
            request.setHeader("Authorization", authorization);
        request.addHeader("User-Agent", "USER_AGENT");
        response = HttpClientBuilder.create().build().execute(request);
        setHeader(response.getAllHeaders());
        // headers = response.getAllHeaders();
    }

    private void setHeader(Header[] mHeaders)
    {
        header.headers = mHeaders;
    }

    public void getJSONArray(String resource) throws Exception{
            get(resource, null, "application/json", null);
            String json = IOUtils.toString(response.getEntity().getContent());
            JSONArray jsonArray = new JSONArray(json);
            setJson(new JSONWrapper(jsonArray));
    }

    public void getJSONEntity(String resource) throws Exception {
        get(resource, null, "application/json", null);
        // json.setJsonObject(IOUtils.toString(response.getEntity().getContent()));
        String json = IOUtils.toString(response.getEntity().getContent());
        JSONObject obj = new JSONObject(json);
        setJson(new JSONWrapper(obj));
        // this.jsonObj = new JSONObject(jsonObj);
    }


    public boolean isValidResponse() {
        return (response != null);
    }

    public int getStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public String getLocale() { return response.getLocale().toString();}


    public String getResource(String url)
    {
        String resource = null;
        String[] resourceArray = url.split(_configuration.BaseURI);
        if(resourceArray != null && resourceArray[1] != null)
        {
          resource = resourceArray[1];
        }

        return resource;
    }

    public Map<String,String> getFields(String fields)
    {
        Map<String,String> headerMap = new HashMap<>();
        String[] arr = fields.split(",");
        if(arr != null)
        {
            for(int i=0;i<arr.length;i++)
            {
                String[] fieldArr = arr[i].split(":");
                headerMap.put(fieldArr[0],fieldArr[1]);
            }
        }

        return headerMap;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
