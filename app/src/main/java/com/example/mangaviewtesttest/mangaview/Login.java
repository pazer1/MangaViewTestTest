package com.example.mangaviewtesttest.mangaview;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class Login {

    private String user;
    private String pass;
    String cookie = "";

    public Login(String user,String pass){
        this.user = user;
        this.pass = pass;
    }

    public Boolean oldsubmit(String baseUrl){
        try{
            URL url = new URL(baseUrl+"/bbs/login_check.php");
            String param = "mb_id="+user+"&mb_password="+pass;
            byte[] data = param.getBytes(Charset.forName("UTF-8"));
            List<String> cookies = null;
            int responseCode = 0;
            if(url.getProtocol().equals("http")){
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Accept","*");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty("charset","UTF-8");
                connection.setRequestProperty("Content-Length",Integer.toString(data.length));
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
                new DataOutputStream(connection.getOutputStream()).write(data);
                responseCode = connection.getResponseCode();
                cookies = connection.getHeaderFields().get("Set-Cookie");
            }else if(url.getProtocol().equals("https")){
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Accept","*");
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty( "Charset", "UTF-8");
                connection.setRequestProperty( "Content-Length", String.valueOf(data.length));
                connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
                new DataOutputStream(connection.getOutputStream()).write(data);
                responseCode = connection.getResponseCode();
                cookies = connection.getHeaderFields().get("Set-Cookie");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
