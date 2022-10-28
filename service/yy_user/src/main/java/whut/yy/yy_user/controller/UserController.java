package whut.yy.yy_user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whut.yy.utils.utils.JwtUtils;
import whut.yy.utils.utils.R;
import whut.yy.yy_user.model.User;
import whut.yy.yy_user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public R Login(@RequestBody User user, HttpServletResponse response) {
        User resultUser = userService.Login(user);
        boolean isSuccess = resultUser != null && user.getPassword().equals(resultUser.getPassword());
        if (isSuccess) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", resultUser.getId());
            claims.put("userName", resultUser.getName());
            response.addHeader("Access-Control-Expose-Headers", "X-Token");
            response.addHeader("X-Token", JwtUtils.createToken(claims));
            return R.ok(null);
        } else {
            return R.error(HttpStatus.UNAUTHORIZED.value(), "密码错误或不存在此用户");
        }
    }

    /**
     * 登录成功后从请求头获取用户名
     *
     * @param request
     * @return
     */
    @GetMapping("/info")
    public R<Map<String, Object>> getInfo(HttpServletRequest request) {
        String token = request.getHeader("X-Token");
        String userName = JwtUtils.parseToken(token).get("userName").toString();
        int userId = (Integer) JwtUtils.parseToken(token).get("userId");
        Map<String, Object> info = new HashMap<>();
        info.put("userName", userName);
        info.put("userId", userId);
        return R.ok(info);
    }
}
