package whut.yy.yy_delicacy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FoodType {
    private Integer id;
    private String name;
}
