package com.nirvana.mapper;

import com.nirvana.domain.User;
import com.nirvana.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class UserMapperTest extends BaseTest{
    @Autowired
    UserService userService;

    @Test
    public void usernameExistTest() throws IOException {
        boolean exist = userService.usernameExist("luv");
        System.out.println(exist);
    }
    @Test
    public void findUserByUsernameAndPassword() throws IOException {
        User user = userService.findUserByUsernameAndPassword("yjl", "luv");
        System.out.println(user);
    }
    @Test
    public void Test3() throws IOException {
        User user = userService.findUserById(1);
        System.out.println(user);
    }
}
