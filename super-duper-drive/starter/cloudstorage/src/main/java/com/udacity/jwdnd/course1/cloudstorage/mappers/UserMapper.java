package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Update("UPDATE USERS SET username=#{username}, salt=#{salt}, password=#{password}, firstname=#{firstName}, lastname=#{lastName} WHERE userid=#{userId}")
    void updateUser(User user);

    @Delete("DELETE FROM USERS WHERE userid=#{userId}")
    void deleteUser(Integer userId);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);


}
