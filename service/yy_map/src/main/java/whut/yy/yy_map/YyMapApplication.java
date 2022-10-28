package whut.yy.yy_map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //启动feign
public class YyMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyMapApplication.class, args);
    }

}
