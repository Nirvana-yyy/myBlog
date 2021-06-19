package com.nirvana.mapper;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OthersTest {

    @Test
    public void timeTest(){
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));

    }

    @Test
    public void intTest(){
        String str = "1243";
        Integer i = Integer.valueOf(str);

        System.out.println(i);
    }
}
