package whut.yy.yy_delicacy.model;

import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;

@Data
@Builder
public class Recommendation {
    private Integer id;
    private String name;
    private Money price;
    private String imgPath;
    private Integer dId;
}
