package whut.yy.yy_map.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whut.yy.yy_map.clients.BaiduMapClient;
import whut.yy.yy_map.config.ApiConfig;
import whut.yy.yy_map.model.Point;

@Service
public class BaiduMapService {

    @Autowired
    BaiduMapClient baiduMapClient;

    @Autowired
    ApiConfig apiConfig;

    public Point getLngAndLat(String address) {
        Point point = Point.builder().build();

        String result = baiduMapClient
                .addressToLngAndLat("武汉", address, apiConfig.getOutput(), apiConfig.getAk());
        JSONObject responseBody = JSONObject.parseObject(result);
        if (responseBody.get("status").toString().equals("0")) {
            JSONObject location = responseBody.getJSONObject("result").getJSONObject("location");
            point.setLng(location.getDouble("lng"));
            point.setLat(location.getDouble("lat"));
        }
        return point;
    }
}
