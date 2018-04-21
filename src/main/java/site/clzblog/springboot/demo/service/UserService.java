package site.clzblog.springboot.demo.service;

import site.clzblog.springboot.demo.model.User;

public interface UserService {
    User add(User user);
    User findById(int id);
    User findByName(String name);
}
