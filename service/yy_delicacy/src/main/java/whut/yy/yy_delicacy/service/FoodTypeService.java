package whut.yy.yy_delicacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whut.yy.yy_delicacy.model.FoodType;
import whut.yy.yy_delicacy.repository.FoodTypeRepository;

import java.util.List;

@Service
public class FoodTypeService {

    @Autowired
    FoodTypeRepository foodTypeRepository;

    public List<FoodType> getAll() {
        return foodTypeRepository.getAll();
    }
}
