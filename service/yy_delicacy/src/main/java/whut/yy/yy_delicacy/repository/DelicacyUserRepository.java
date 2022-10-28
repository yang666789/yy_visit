package whut.yy.yy_delicacy.repository;

import org.apache.ibatis.annotations.*;
import whut.yy.yy_delicacy.model.Delicacy_User;

import java.util.List;


@Mapper
public interface DelicacyUserRepository {
    @Select("select * from delicacy_user where u_id =#{uId}")
    List<Delicacy_User> getByUId(Integer uId);

    @Insert("insert into delicacy_user(d_id,u_id) values (#{dId},#{uId})")
    int add(Delicacy_User delicacy_user);

    @Delete("delete from delicacy_user where d_id=#{dId} and u_id=#{uId}")
    int delete(Integer uId, Integer dId);
}
