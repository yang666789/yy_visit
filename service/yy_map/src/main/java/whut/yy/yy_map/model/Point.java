package whut.yy.yy_map.model;

import lombok.Builder;
import lombok.Data;

/**
 * 地点信息
 */
@Data
@Builder
public class Point {
    private double lng; //经度
    private double lat; //纬度
    private String name; //小店名字
    private String address; //小店地址
}
