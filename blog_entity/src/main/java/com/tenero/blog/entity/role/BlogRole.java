package com.tenero.blog.entity.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @description
 * @author kesong
 * @date 2022/3/16 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRole {
    private Integer id;

    /**
    * 角色名
    */
    private String roleName;

    /**
    * 描述
    */
    private String description;
}