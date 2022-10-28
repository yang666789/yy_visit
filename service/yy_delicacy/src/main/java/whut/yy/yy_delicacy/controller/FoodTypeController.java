package whut.yy.yy_delicacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import whut.yy.utils.utils.R;
import whut.yy.yy_delicacy.model.FoodType;
import whut.yy.yy_delicacy.service.FoodTypeService;

import java.util.List;

@Controller
@RequestMapping("/foodType")
public class FoodTypeController {

    @Autowired
    FoodTypeService foodTypeService;

    @GetMapping("/")
    @ResponseBody
    public R<List<FoodType>> getAllFoodType() {
        List<FoodType> foodTypes = foodTypeService.getAll();
        return R.ok(foodTypes);
    }
}
