package whut.yy.yy_delicacy.model;

import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;


@Data
@Builder
public class Delicacy {
    private Integer id;
    private String name;
    private Integer tId;
    private String address;
    private Money average;
    private String imgPath;
    private int rate;

//    private List<Recommendation> recomFoods;
}
