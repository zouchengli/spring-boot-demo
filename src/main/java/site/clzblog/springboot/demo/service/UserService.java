package site.clzblog.springboot.demo.service;

import site.clzblog.springboot.demo.model.User;

import java.util.List;

public interface UserService {
    User add(User user);
    List<User> findAll();
    User findById(int id);
    User findByName(String name);
    int deleteUser(int id);
    int updateUser(User user);
}
