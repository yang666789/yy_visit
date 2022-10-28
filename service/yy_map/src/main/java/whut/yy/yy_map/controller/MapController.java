package whut.yy.yy_map.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whut.yy.utils.utils.R;
import whut.yy.yy_map.clients.DelicacyClient;
import whut.yy.yy_map.model.Point;
import whut.yy.yy_map.service.BaiduMapService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private DelicacyClient delicacyClient;

    @Autowired
    private BaiduMapService baiduMapService;

    /***
     * 生成哪个用户的地图数据
     * @return
     */
    @GetMapping("/{id}")
    public R<List<Point>> getMapData(@PathVariable("id") int id) {
        List<Point> points = new ArrayList<>();

        //1.请求服务
        JSONObject responseBody = JSONObject.parseObject(delicacyClient.getDelicacyByUId(id));
        //2.解析数据
        JSONArray data = responseBody.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject jo = data.getJSONObject(i);
            Point point = baiduMapService.getLngAndLat(jo.getString("address"));
            point.setName(jo.getString("name"));
            point.setAddress(jo.getString("address"));
            points.add(point);
        }
        //3.返回结果
        return R.ok(points);
    }
}
