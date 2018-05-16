package api_builders;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ParserApi {
    public HttpEntity findElement(File file, String elementName, String threshold) throws Exception {
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
        return response.getEntity();
    }

    public HttpEntity initThresholds(File file, String elements) throws Exception {
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

        return response.getEntity();
    }

    public HttpEntity findElementByTempalte(File originalImage, File templateImage) throws Exception {
        HttpPost post = new HttpPost("http://172.19.2.136:8000/recognition/find_element/");
        Cookie token = utils.HttpUtils.getToken("http://172.19.2.136:8000/recognition/find_template_image/");

        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(token);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();

        FileBody originalImageData = new FileBody(originalImage);
        FileBody templateImageData = new FileBody(templateImage);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create().setLaxMode();
        reqEntity.addPart("original_image", originalImageData);
        reqEntity.addPart("template_image", templateImageData);

        HttpEntity httpEntity = reqEntity.build();
        post.setEntity(httpEntity);

        HttpResponse response = client.execute(post);
        return response.getEntity();
    }

}
