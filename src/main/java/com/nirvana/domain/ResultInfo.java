package com.nirvana.domain;

import lombok.Data;
import org.apache.ibatis.ognl.ObjectElementsAccessor;

import java.io.Serializable;

/**
 * 用于封装后端返回前端对象
 */
@Data
public class ResultInfo implements Serializable {

    private boolean flag;
    //后端返回结果对象
    private Object data;
    //发生异常的错误信息
    private String errorMsg;



}
