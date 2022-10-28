package whut.yy.yy_delicacy.repository;

import org.apache.ibatis.annotations.Mapper;
import whut.yy.yy_delicacy.model.Delicacy;

import java.util.List;

@Mapper
public interface DelicacyRepository {
    int addDelicacy(Delicacy delicacy);

    List<Delicacy> getAll(String name, Integer tId);

    List<Delicacy> getByUserId(Integer uId, String name, Integer tId);

    int updateDelicacy(Delicacy delicacy);

    int deleteDelicacy(Integer id);
}
