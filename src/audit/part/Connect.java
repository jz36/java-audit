package audit.part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jz36 on 30.05.16.
 */
public class Connect {
    private Map<String, String> megaindexParams = new HashMap<String, String>();
    private Map<String, String> googleParams = new HashMap<String, String>();
    private URL megaindexSiteUrl, googleSiteUrl;
    private HttpURLConnection megaindexConn, googleConn;
    private String megaindexSiteUrlConnect = "http://api.megaindex.ru/?";
    private String googleSiteUrlConnect;
    private final String USER_AGENT = "Mozilla/5.0";

    public void getReindexSite(String site) throws IOException, ParseException {
        megaindexParams.put("method", "reindex_site");
        megaindexParams.put("output", "json");
        megaindexParams.put("mode", "site");
        megaindexParams.put("login", "dmitriy@biksileev.ru");
        megaindexParams.put("password", "NokiaN9777");
        megaindexParams.put("url", site);
        megaindexParams.put("target", "reindex");
        megaindexParams.put("count_page", "1");
        for (Map.Entry<String, String> entry : megaindexParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            megaindexSiteUrlConnect += key + "=" + value + "&";
        }
        megaindexSiteUrl = new URL(megaindexSiteUrlConnect);
        megaindexConn = (HttpURLConnection) megaindexSiteUrl.openConnection();
        megaindexConn.setRequestMethod("GET");
        megaindexConn.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(megaindexConn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
        megaindexParams.put("version_id", jsonObject.get("version_id").toString());



    }

    public JSONArray getIndex() throws IOException, ParseException {
        megaindexParams.put("method", "get_index");
        for (Map.Entry<String, String> entry : megaindexParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            megaindexSiteUrlConnect += key + "=" + value + "&";
        }
        megaindexSiteUrl = new URL(megaindexSiteUrlConnect);
        megaindexConn = (HttpURLConnection) megaindexSiteUrl.openConnection();
        megaindexConn.setRequestMethod("GET");
        megaindexConn.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(megaindexConn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
        return jsonArray;
    }

    public URLConnection getConnection(){
        return megaindexConn;
    }
}
