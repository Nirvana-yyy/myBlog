package com.nirvana.domain;

import lombok.Data;

/**
 * @author YJL
 */
@Data
public class Role {

    Integer RoleId;

    String RoleName;
    /**
     * 角色描述
     */
    String ROleDesc;
}
