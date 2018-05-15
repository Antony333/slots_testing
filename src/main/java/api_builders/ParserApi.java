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
    public void findElement(File file, String elementName) throws Exception {
        HttpPost post = new HttpPost("http://172.19.2.136:8000/recognition/find_element/");
        Cookie token = utils.HttpUtils.getToken("http://172.19.2.136:8000/recognition/find_element/");

        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(token);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();

        FileBody data = new FileBody(file);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        reqEntity.addPart("csrfmiddlewaretoken", new StringBody(token.getValue(), ContentType.TEXT_PLAIN));
        reqEntity.addPart("element_text", new StringBody(elementName, ContentType.TEXT_PLAIN));
        reqEntity.addPart("text_threshold", new StringBody("250", ContentType.TEXT_PLAIN));
        reqEntity.addPart("docfile", data);

        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("csrfmiddlewaretoken", token.getValue()));
        postParameters.add(new BasicNameValuePair("element_text", elementName));
        postParameters.add(new BasicNameValuePair("text_threshold", "250"));
        postParameters.add(new BasicNameValuePair("docfile", "gfgfgf"));
        post.setHeader("User-Agent", USER_AGENT);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
        post.setEntity(reqEntity.build());

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

    public static void main(String[] args) throws Exception {
        ParserApi parserApi = new ParserApi();
        parserApi.findElement(new File("Screenshot_2018-04-27-16-23-48-709_com.productmadness.hovmobile.png"), "siper element");
    }
}
