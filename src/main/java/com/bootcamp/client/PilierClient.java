package com.bootcamp.client;

import com.bootcamp.constants.AppConstant;
import com.bootcamp.jpa.entities.Axe;
import com.bootcamp.jpa.entities.Pilier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

public class PilierClient implements AppConstant {
    private DefaultHttpClient httpClient;

    public PilierClient() {
        httpClient = new DefaultHttpClient();
    }

    // client pour utiliser le findAll   List<Projet>
    public void findAll() throws IOException {
        String uri =  PILIER_CLIENT_BASE_URI+PILIER_CLIENT_GET_URI;

        // pour les requetes de type get
        HttpGet getRequest = new HttpGet(uri);

        getRequest.addHeader("accept", "application/json");
        // executer la requetes
        try {
            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());

            }
            final GsonBuilder builder = new GsonBuilder();
            final Gson gson = builder.create();
            //postRequest.setEntity((HttpEntity) ben);
            StringEntity result = new StringEntity(gson.toJson(response));
            //String result = parseResppnse(response);
            System.out.println(result);

        } catch (Exception e) {
        }


    }

    // pour utiliser le create
    public void create() throws UnsupportedEncodingException, IOException{
        String uri = PILIER_CLIENT_BASE_URI + PILIER_CLIENT_CREATE_URI;

        HttpPost postRequest = new HttpPost(uri);
        Pilier pilier = new Pilier();
        pilier.setDescription("description 1 du pilier");
        pilier.setName("Pilier 1");
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        StringEntity input = new StringEntity(gson.toJson(pilier));
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);
    }

    private String parseResppnse(HttpResponse response) throws IOException{
        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));
        String output;
        String result="";

        System.out.println("Output from Server .... \n");

        while ((output = br.readLine()) != null) {

            result+=output;

        }

        return result;
    }
}
