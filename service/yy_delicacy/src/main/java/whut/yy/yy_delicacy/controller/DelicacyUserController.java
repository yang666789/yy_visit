package whut.yy.yy_delicacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whut.yy.utils.utils.R;
import whut.yy.yy_delicacy.model.Delicacy_User;
import whut.yy.yy_delicacy.service.DelicacyUserService;

import java.util.List;

@RestController
@RequestMapping("/delicacyUser")
public class DelicacyUserController {

    @Autowired
    DelicacyUserService delicacyUserService;

    @GetMapping("/{id}")
    public R<List<Delicacy_User>> getByUId(@PathVariable("id") Integer uId) {
        return R.ok(delicacyUserService.getByUId(uId));
    }

    @PostMapping("/")
    public R add(@RequestBody Delicacy_User delicacy_user) {
        delicacyUserService.add(delicacy_user);
        return R.ok(null);
    }

    @DeleteMapping("/{uId}/{dId}")
    public R delete(@PathVariable("uId") Integer uId,
                    @PathVariable("dId") Integer dId) {
        delicacyUserService.delete(uId, dId);
        return R.ok(null);
    }
}
