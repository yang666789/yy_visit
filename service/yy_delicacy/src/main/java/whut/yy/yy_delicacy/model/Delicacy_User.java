package whut.yy.yy_delicacy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Delicacy_User {
    private Integer id;
    private Integer dId;
    private Integer uId;
}
