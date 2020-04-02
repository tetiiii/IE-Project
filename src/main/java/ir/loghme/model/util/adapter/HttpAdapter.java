package main.java.ir.loghme.model.util.adapter;

import main.java.ir.loghme.exeption.HttpException;
import main.java.ir.loghme.exeption.HttpResponseException;
import main.java.ir.loghme.exeption.HttpStatusException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;

public class HttpAdapter {
    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() throws IOException {
        httpClient.close();
    }

    public String sendGet(URL url) throws HttpException, IOException {

        HttpGet request = new HttpGet(String.valueOf(url));

        // add request headers
        request.addHeader(HttpHeaders.ACCEPT, "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
                throw new HttpStatusException("status code was " + response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                return result;
            }
            throw new HttpResponseException("Http result is null");

        }

    }
}
