package site.clzblog.springboot.demo.service.impl;

import site.clzblog.springboot.demo.mapper.UserMapper;
import site.clzblog.springboot.demo.model.User;
import site.clzblog.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User add(User user) {
        int i = userMapper.addUser(user);
        if (i > 0) {
            System.out.println("Add user success " + i + " id:" + user.getId());
        }
        return findById(user.getId());
    }

    @Override
    public User findById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findUserByName(name);
    }
}
