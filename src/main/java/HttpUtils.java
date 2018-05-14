import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.Header;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

class HttpUtils {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpUtils http = new HttpUtils();

//        System.out.println("GET Request Using HttpURLConnection");
//        http.sendGet("http://172.19.2.136:8000/find_element/find_element/");
        List<Cookie> token = http.getToken("http://172.19.2.136:8000/find_element/find_element/");
        System.out.println();


        System.out.println("POST Request Using HttpURLConnection");
        http.sendPost("http://172.19.2.136:8000/find_element/find_element/");
//        http.sendPost("http://172.19.2.136:8000/find_element/find_element/", token);

    }

    private List<Cookie> getToken(String url) throws Exception {
        /* init client */
        HttpClient http = null;
        CookieStore httpCookieStore = new BasicCookieStore();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
        http = builder.build();

        /* do stuff */
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse httpResponse = null;
        try {httpResponse = http.execute(httpRequest);} catch (Throwable error) {throw new RuntimeException(error);}

        /* check cookies */

        System.out.println(httpCookieStore.getCookies());
        List<Cookie> cookies = httpCookieStore.getCookies();
        for (int i = 0; i < cookies.size(); i++) {
//            System.out.println("Cookie: " + cookies.get(i));
            Cookie cookie = cookies.get(i);
            System.out.println(
                    "Cookie: " + cookie.getName() +
                            ", Value: " + cookie.getValue() +
                            ", IsPersistent?: " + cookie.isPersistent() +
                            ", Expiry Date: " + cookie.getExpiryDate() +
                            ", Comment: " + cookie.getComment());
        }
        return cookies;

    }

    private void sendGet(String url) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);


        httpGet.setHeader("User-Agent", USER_AGENT);


        HttpResponse response = client.execute(httpGet);

        Header[] headers = response.getHeaders("Set-Cookie");
        for (Header h : headers) {
            System.out.println(h.getValue());
        }

        System.out.println("\nSending 'Get' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

    }

    private void sendPost(String url) throws Exception {
//    private void sendPost(String url, List<Cookie> token) throws Exception {

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
//        CookieStore cookieStore = new BasicCookieStore();

        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("param1", "param1"));
        urlParameters.add(new BasicNameValuePair("param2", "param2"));
        urlParameters.add(new BasicNameValuePair("param3", "param3"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

//        cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "true");

        client.getParams().setParameter("_csrf_header", "X-CSRF-Token");


        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

    }
}