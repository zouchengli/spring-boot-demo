package site.clzblog.springboot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import site.clzblog.springboot.demo.model.User;
import site.clzblog.springboot.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SpringBootDemoApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void testFind() {
        User user = userService.findById(2);
        System.out.println(user);
    }

    @Test
    public void testFindAll() {
        System.out.println(userService.findAll());
    }

    @Rollback(false)
    @Test
    public void testAdd() {
        User user = new User();
        user.setName("implication1");
        user.setPassword("implication");
        User add = userService.add(user);
        System.out.println("==========================" + add.getId());
    }

    @Rollback(false)
    @Test
    public void testDelete() {
        System.out.println(userService.deleteUser(7));
    }

    @Rollback(false)
    @Test
    public void testUpdate() {
        User user = userService.findById(4);
        user.setPassword("BlackEnvelop");
        System.out.println(userService.updateUser(user));
    }
}
