import org.json.JSONObject;

public class ScanResponse {

    int criticalAlerts;
    int majorAlerts;
    int minorAlerts;
    int infoAlerts;
    String postureType;
    String target;
    String node;
    String host;

    public ScanResponse(String jsonResponse) {
        JSONObject obj = new JSONObject(jsonResponse);

        criticalAlerts = obj.getInt("criticalAlerts");
        majorAlerts = obj.getInt("majorAlerts");
        minorAlerts = obj.getInt("minorAlerts");
        infoAlerts = obj.getInt("infoAlerts");
        postureType = obj.getString("postureType");
        target = obj.getString("target");
        node = obj.getString("node");
        host = obj.getString("host");
    }

    public int getCriticalAlerts() {
        return this.criticalAlerts;
    }

    public void setCriticalAlerts(int a) {
        this.criticalAlerts = a;
    }

    public int getMajorAlerts() {
        return this.majorAlerts;
    }

    public void setMajorAlerts(int a) {
        this.majorAlerts = a;
    }

    public int getMinorAlerts() {
        return this.minorAlerts;
    }

    public void setMinorAlerts(int a) {
        this.minorAlerts = a;
    }

    public int getInfoAlerts() {
        return this.infoAlerts;
    }

    public void setInfoAlerts(int a) {
        this.infoAlerts = a;
    }

    public String getPostureType() {
        return this.postureType;
    }

    public void setPostureType(String pt) {
        this.postureType = pt;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String t) {
        this.target = t;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String n) {
        this.node = n;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String h) {
        this.host = h;
    }
}
