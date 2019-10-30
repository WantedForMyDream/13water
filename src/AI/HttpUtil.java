package AI;


import javafx.util.Pair;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Logger;

public class HttpUtil {
    static String Name="X-Auth-Token";

    public static String sendGet(String urlAddress,int x,String Token) {
        try {
            HttpURLConnection urlConnection=null;
            URL url=new URL(urlAddress);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            if(x==1)
            {
                urlConnection.addRequestProperty("Content-Type", "application/json");
                urlConnection.addRequestProperty("X-Auth-Token", Token);
            }
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            BufferedReader bf=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            String line=bf.readLine();
            System.out.println(line);
            return line;
        }catch (Exception e) {
            System.out.println("sendGet error!");
        }
        return null;
    }

    public static String sendPost(String URL, JSONObject json, int x) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        post.setHeader("Content-Type", "application/json");
        String result = "";
        try {
            if(x ==1)
            {
                StringEntity s = new StringEntity(json.toString(), "utf-8");
                s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
                post.setEntity(s);
            }
            HttpResponse httpResponse = client.execute(post);
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line);
            inStream.close();
            result = strber.toString();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String sendPost(String URL, JSONObject json, int x,String Token) {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        post.setHeader("Content-Type", "application/json");
        post.addHeader(Name, Token);
        String result = "";
        try {
            if(x ==1)
            {
                StringEntity s = new StringEntity(json.toString(), "utf-8");
                s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
                post.setEntity(s);
            }
            HttpResponse httpResponse = client.execute(post);
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line);
            inStream.close();
            result = strber.toString();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
        return result;
    }
}
