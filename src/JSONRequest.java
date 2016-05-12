import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Deepak on 5/12/16.
 */
public class JSONRequest {

    public static void main(String[] args) throws IOException, JSONException {

        String URL = "http://dev.compact.scanlife.com:8091/images";
        Gson gson = new Gson();
        HttpClient hclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URL);

        Request req = new Request();
        req.setData("http://linkedin.com");
        req.setReferenceId(null);

        /*JSONObject json = new JSONObject();
        json.put("data", "http://google.com");
        json.put("referenceId", "123");*/

        StringEntity postingString = new StringEntity(gson.toJson(req));//json.toString());
        postingString.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(postingString);
        HttpResponse response = hclient.execute(post);
        String json = EntityUtils.toString(response.getEntity());

        Gson data = new Gson();
        Response res = gson.fromJson(json,Response.class);

        System.out.println(res.getCodeData());

        /*JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject);
        */


       /* InputStream is = null;
        is = response.getEntity().getContent();
        String result = "";
        JSONObject jsonObject = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch(Exception e) {

        }

        // Convert string to object
        try {
            jsonObject = new JSONObject(result);
            System.out.println(jsonObject);
        } catch(JSONException e) {

        }*/

    }

}


class Request
{
    private String data;
    private String referenceId;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}

class Response
{
    private int id;
    private String codeData;
    private String s3FileUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeData() {
        return codeData;
    }

    public void setCodeData(String codeData) {
        this.codeData = codeData;
    }

    public String getS3FileUrl() {
        return s3FileUrl;
    }

    public void setS3FileUrl(String s3FileUrl) {
        this.s3FileUrl = s3FileUrl;
    }
}