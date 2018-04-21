package site.clzblog.springboot.demo.api;

import com.alibaba.fastjson.JSONObject;
import site.clzblog.springboot.demo.model.User;
import site.clzblog.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Object add(@RequestBody User user) {
        if (userService.findByName(user.getName()) != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "用户名已被使用");
            return jsonObject;
        }
        return userService.add(user);
    }
}
