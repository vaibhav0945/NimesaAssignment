package processor;

import org.json.JSONArray;
import org.json.JSONObject;
import data.OutputData;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class DataProcessor {
    private final String dataUri;
    public DataProcessor(String dataUri){
        this.dataUri = dataUri;
    }
    public String fetchInputData() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(dataUri)).build();
        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse <String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public Map<String, OutputData> preProcessData(String body) {
        JSONObject jsonObject = new JSONObject(body);
        JSONArray list = jsonObject.getJSONArray("list");
        HashMap<String, OutputData> resultMap = new HashMap<>();
        for(int i=0;i<list.length();i++) {
            if(i == 13 || i == 29 || i == 32 || i == 71 || i == 95)
                continue;
            JSONObject map = (JSONObject) list.get(i);
            String date = (String) map.get("dt_txt");
            OutputData output = createOutput(map);
            resultMap.put(date, output);
        }
        return resultMap;
    }

    public OutputData createOutput(JSONObject input){
        JSONObject main = (JSONObject) input.get("main");
        BigDecimal temp = (BigDecimal) main.get("temp");
        BigDecimal pressure = (BigDecimal) main.get("pressure");
        JSONObject wind = (JSONObject) input.get("wind");
        BigDecimal speed = (BigDecimal) wind.get("speed");
        return new OutputData(speed, pressure, temp);
    }

}
