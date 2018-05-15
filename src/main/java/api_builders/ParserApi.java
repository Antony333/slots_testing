package api_builders;

import com.sun.deploy.net.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class ParserApi {
    public void findElement(File file, String elementName, String threshold) throws Exception {
        HttpPost post = new HttpPost("http://172.19.2.136:8000/recognition/find_element/");
        Cookie token = utils.HttpUtils.getToken("http://172.19.2.136:8000/recognition/find_element/");

        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(token);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();

        FileBody data = new FileBody(file);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create().setLaxMode();
        reqEntity.addPart("csrfmiddlewaretoken", new StringBody(token.getValue(), ContentType.TEXT_PLAIN));
        reqEntity.addPart("element_text", new StringBody(elementName, ContentType.TEXT_PLAIN));
        reqEntity.addPart("text_threshold", new StringBody(threshold, ContentType.TEXT_PLAIN));
        reqEntity.addPart("docfile", data);

        HttpEntity httpEntity = reqEntity.build();
        post.setEntity(httpEntity);

        HttpResponse response = client.execute(post);
        System.out.println(response);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(response.getEntity().toString());
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
    }

    public void initThresholds(File file, String elements) throws Exception {
        HttpPost post = new HttpPost("http://172.19.2.136:8000/recognition/init_thresholds/");

        Cookie token = utils.HttpUtils.getToken("http://172.19.2.136:8000/recognition/init_thresholds/");

        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(token);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();

        FileBody data = new FileBody(file);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create().setLaxMode();
        reqEntity.addPart("csrfmiddlewaretoken", new StringBody(token.getValue(), ContentType.TEXT_PLAIN));
        reqEntity.addPart("elements", new StringBody(elements, ContentType.TEXT_PLAIN));
        reqEntity.addPart("docfile", data);

        HttpEntity httpEntity = reqEntity.build();
        post.setEntity(httpEntity);

        HttpResponse response = client.execute(post);
        System.out.println(response);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
    }
}
