package whut.yy.yy_user.repository;


import org.apache.ibatis.annotations.Mapper;
import whut.yy.yy_user.model.User;

@Mapper
public interface UserRepository {
    User getByName(String name);
}
