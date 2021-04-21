package bms.player.beatoraja.ir;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class IrStub implements IRConnection {
    public static final String NAME = "IR Stub";
    public static final String HOME = "";
    public static final String VERSION = "0.0.1";

    private static final String IRUrl = "http://127.0.0.1:39393";

    private boolean makePOSTRequest(String uri, String data) {
        System.out.println(IRUrl + uri);
        System.out.println(data);
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(IRUrl + uri);

            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            boolean res = response.getStatusLine().getStatusCode() == 200;
            client.close();
            return res;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public IRResponse<IRPlayerData> register(String id, String pass, String name) {
        return null;
    }

    public IRResponse<IRPlayerData> login(String id, String pass) {
        ResponseCreator<IRPlayerData> rc = new ResponseCreator<>();
        return rc.create(true, "It worked!", null);
    }

    public IRResponse<Object> sendPlayData(IRChartData model, IRScoreData score) {
        ResponseCreator<Object> rc = new ResponseCreator<>();
        PlayData playData = new PlayData(model, score);
        try {
            ObjectWriter ow = (new ObjectMapper()).writer();
            String json = ow.writeValueAsString(playData);
            boolean res = makePOSTRequest("/api/play", json);
            return rc.create(res, "Score", null);
        } catch (Exception e) {
            System.out.println(e.toString());
            return rc.create(false, "Internal Exception", null);
        }
    }

    public IRResponse<Object> sendCoursePlayData(IRCourseData course, IRScoreData score) {
        ResponseCreator<Object> rc = new ResponseCreator<>();
        CourseData courseData = new CourseData(course, score);
        try {
            ObjectWriter ow = (new ObjectMapper()).writer();
            String json = ow.writeValueAsString(courseData);
            boolean res = makePOSTRequest("/api/course", json);
            return rc.create(res, "Course", null);
        } catch (Exception e) {
            System.out.println(e.toString());
            return rc.create(false, "Internal Exception", null);
        }
    }

    public IRResponse<IRPlayerData[]> getRivals() {
        ResponseCreator<IRPlayerData[]> rc = new ResponseCreator<>();
        return rc.create(false, "Unimplemented.", new IRPlayerData[0]);
    }

    public IRResponse<IRTableData[]> getTableDatas() {
        ResponseCreator<IRTableData[]> rc = new ResponseCreator<>();
        return rc.create(false, "Unimplemented.", new IRTableData[0]);
    }

    public IRResponse<IRScoreData[]> getPlayData(IRPlayerData irpd, IRChartData model) {
        ResponseCreator<IRScoreData[]> rc = new ResponseCreator<>();
        return rc.create(false, "Unimplemented.", new IRScoreData[0]);
    }

    public IRResponse<IRScoreData[]> getCoursePlayData(IRPlayerData irpd, IRCourseData course) {
        ResponseCreator<IRScoreData[]> rc = new ResponseCreator<>();
        return rc.create(false, "Unimplemented.", new IRScoreData[0]);
    }

    public String getSongURL(IRChartData song) {
        return null;
    }

    public String getCourseURL(IRCourseData course) {
        return null;
    }

    public String getPlayerURL(IRPlayerData irpd) {
        return null;
    }
}

