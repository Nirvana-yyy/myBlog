package com.nirvana.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:sqlMapConfig.xml", "classpath:spring/spring-mvc.xml", "classpath:applicationContext.xml"})
public class BaseTest {

}
