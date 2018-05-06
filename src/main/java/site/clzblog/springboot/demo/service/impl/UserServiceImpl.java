package site.clzblog.springboot.demo.service.impl;

import site.clzblog.springboot.demo.mapper.UserMapper;
import site.clzblog.springboot.demo.model.User;
import site.clzblog.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.delUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
