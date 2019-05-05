package com.rapi.server.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rapi.server.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FunctionalJSONTest {

    private String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36";
    private String GET = "GET";
    private String POST = "POST";
    private String PUT = "PUT";
    private String DELETE = "DELETE";
    private String LOCAL_HOST = "http://localhost:8080/";
    private Gson gson = new Gson();


    private User userA;
    private User userEqualsA;
    private User userB;

    public String sendJSONRequest(String url, String request, String method) {
        try {
            URL obj = new URL(LOCAL_HOST + url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();
            StringBuffer response = new StringBuffer();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(response.toString(), Object.class);
            String indented = mapper.writeValueAsString(json);
            return indented;
        } catch (ProtocolException e) {
//            e.printStackTrace();
        } catch (JsonParseException e) {
//            e.printStackTrace();
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        } catch (JsonMappingException e) {
//            e.printStackTrace();
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;
    }


    @Before
    public void setUp() {
        userA = new User();
        userA.setName("John");
        userA.setSecondName("n/a");
        userA.setLastName("Simpson");
        userA.setTel("123456");
        userA.setAddress("Street");
        userEqualsA = userA;
        userB = new User();
        userB.setName("Martha");
        userB.setSecondName("n/a");
        userB.setLastName("Simpson");
        userB.setTel("123456");
        userB.setAddress("Street");

    }

    @Test
    public void getAllUsers() throws Exception {
        User user = gson.fromJson(gson.toJson(userA), User.class);
        Assert.assertEquals(user.getName(), userA.getName());
    }

    @Test
    public void getUser() {
        sendJSONRequest("users/1", "", GET);
    }

    @Test
    public void saveUser() {
        sendJSONRequest("users", gson.toJson(userA), POST);
        sendJSONRequest("users", gson.toJson(userEqualsA), POST);
        sendJSONRequest("users", gson.toJson(userB), POST);
        Assert.assertEquals(userA, userEqualsA);
    }

    @Test
    public void deleteUser() {
        sendJSONRequest("users/1", "", DELETE);
        Assert.assertNotEquals(userA, userB);
    }

    @Test
    public void getAllBanks() {
    }

    @Test
    public void getBank() {
    }

    @Test
    public void saveBank() {
    }

    @Test
    public void deleteBank() {
    }

    @Test
    public void getAllAccounts() {
    }

    @Test
    public void getAllAccountsByUserId() {
    }

    @Test
    public void getAccount() {
    }

    @Test
    public void saveAccount() {
    }

    @Test
    public void deleteAccount() {
    }

    @Test
    public void accountTransferToAccount() {
    }

    @Test
    public void getAllTransactions() {
    }

    @Test
    public void getAllTransactionsByUserId() {
    }

    @Test
    public void getAllTransactionsByUserInBank() {
    }

    @Test
    public void getAllTransactionsFromAccountToAccount() {
    }

    @Test
    public void getTransaction() {
    }

}