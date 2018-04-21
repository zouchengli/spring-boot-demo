package site.clzblog.springboot.demo.mapper;

import site.clzblog.springboot.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO user(name,password)VALUES(#{name},#{password})")
    int addUser(User user);

    @Select("SELECT id,name,password FROM user WHERE id=#{id}")
    User findUserById(@Param("id")int id);

    @Select("SELECT id,name,password FROM user WHERE name=#{name}")
    User findUserByName(@Param("name")String name);
}
