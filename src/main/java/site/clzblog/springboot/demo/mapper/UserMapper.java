package site.clzblog.springboot.demo.mapper;

import org.springframework.stereotype.Repository;
import site.clzblog.springboot.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO user(name,password)VALUES(#{name},#{password})")
    int addUser(User user);

    @Select("SELECT id,name,password FROM user")
    List<User> findAll();

    @Select("SELECT id,name,password FROM user WHERE id=#{id}")
    User findUserById(@Param("id")int id);

    @Select("SELECT id,name,password FROM user WHERE name=#{name}")
    User findUserByName(@Param("name")String name);

    @Delete("DELETE user WHERE id=#{id}")
    int delUser(@Param("id")int id);

    @Update("UPDATE user SET name=#{name},password=#{password} WHERE id=#{id}")
    int updateUser(User user);
}
