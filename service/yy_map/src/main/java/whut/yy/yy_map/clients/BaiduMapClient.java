package whut.yy.yy_map.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "baiduMap", url = "https://api.map.baidu.com")
public interface BaiduMapClient {

    @GetMapping("/geocoding/v3")
    String addressToLngAndLat(@RequestParam String city,
                              @RequestParam String address,
                              @RequestParam String output,
                              @RequestParam String ak);
}
