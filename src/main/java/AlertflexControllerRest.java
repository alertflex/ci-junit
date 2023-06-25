
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class AlertflexControllerRest {

    OkHttpClient okHttpClient;
    String scanId;
    Boolean result = true;

    String url;
    int delay;
    String postureType;
    String alertCorr;
    String target;
    String host;
    String node;
    String project;

    public AlertflexControllerRest(String url,
                                   int delay,
                                   String postureType,
                                   String alertCorr,
                                   String target,
                                   String host,
                                   String node,
                                   String project) throws IOException, InterruptedException {
        this.url = url;
        this.delay = delay;
        this.postureType = postureType;
        this.alertCorr = alertCorr;
        this.target = target;
        this.host = host;
        this.node = node;
        this.project = project;

        okHttpClient = new OkHttpClient();
        result = runScan();
    }

    public boolean runScan() throws IOException, InterruptedException {

        if(!result) return false;

        String body = convertParamsToJson();

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"), body))
                .build();

        Response response = okHttpClient.newCall(request).execute();

        int res = response.code();

        if (res != 200) return false;

        JSONObject obj = new JSONObject(response.body().string());
        scanId = obj.getString("taskId");

        Thread.sleep(1000);

        return true;
    }

    public Boolean getAlerts(int critical, int major, int minor, int info) throws IOException {

        String newUrl = url + "/alerts/" + scanId;

        Request request = new Request.Builder()
                .url(newUrl)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();

        int res = response.code();

        if (res != 200) return false;

        String r = response.body().string();
        JSONObject obj = new JSONObject(r);

        int criticalAlert = obj.getInt("alert_critical");
        if (criticalAlert >= critical && critical != 0) return false;

        int majorAlerts = obj.getInt("alert_major");
        if (majorAlerts >= major && major != 0) return false;

        int minorAlerts = obj.getInt("alert_minor");
        if (minorAlerts >= minor && minor != 0) return false;

        int infoAlerts = obj.getInt("alert_info");
        if (infoAlerts >= info && info != 0) return false;

        return true;
    }

    String getScanId() {
        return scanId;
    }

    String convertParamsToJson() {
        return "{\"delay\": " + delay + "," +
                "\"postureType\": \"" + postureType + "\"," +
                "\"alertCorr\": \"" + alertCorr + "\"," +
                "\"target\": \"" + target + "\"," +
                "\"host\": \"" + host + "\"," +
                "\"node\": \"" + node + "\"," +
                "\"project\": \"" + project + "\"}";
    }
}


