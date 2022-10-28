package whut.yy.yy_map.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("yy-delicacy")
public interface DelicacyClient {

    @GetMapping("/delicacy/{uId}")
    String getDelicacyByUId(@PathVariable("uId") int uId);

}
