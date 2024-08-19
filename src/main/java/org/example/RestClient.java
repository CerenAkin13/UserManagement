package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestClient {

    private static final String URL = "http://localhost:8080/users";

    public static void main(String[] args) {
        RestClient client = new RestClient();
        client.addUser("John Doe");
        client.getUsers();
    }

    // GET isteği gönderme
    public void getUsers() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println("Users: " + result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // POST isteği gönderme
    public void addUser(String user) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(URL);
            StringEntity entity = new StringEntity(user);
            request.setEntity(entity);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String result = EntityUtils.toString(responseEntity);
                    System.out.println("Response: " + result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

