package whut.yy.yy_delicacy.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import whut.yy.yy_delicacy.model.FoodType;

import java.util.List;

@Mapper
public interface FoodTypeRepository {
    @Select("select * from food_type")
    List<FoodType> getAll();
}
