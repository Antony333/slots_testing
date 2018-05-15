package image_driver;

import api_builders.ParserApi;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class ImageDriver {
    private ParserApi parserApi;

    public ImageDriver() {
        this.parserApi = new ParserApi();
    }

    public JSONObject findElement(File file, String elementName, String threshold) throws Exception {
        HttpEntity findElementHttpEntity = parserApi.findElement(file, elementName, threshold);

        String json = EntityUtils.toString(findElementHttpEntity, StandardCharsets.UTF_8);

        System.out.println(json);
        if (!json.equals("null")) {
            return new JSONObject(json);
        }

        return null;

    }

    public JSONObject initThresholds(File file, String elements) throws Exception {
        HttpEntity initThresholdsEntity = parserApi.initThresholds(file, elements);

        String json = EntityUtils.toString(initThresholdsEntity, StandardCharsets.UTF_8);

        return new JSONObject(json);

    }
}
