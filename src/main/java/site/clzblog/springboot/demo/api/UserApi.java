package site.clzblog.springboot.demo.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import site.clzblog.springboot.demo.model.User;
import site.clzblog.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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

    @PostMapping("/test")
    public String test(@RequestParam("name") String name) {
        return name;
    }
}
