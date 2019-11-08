package com.exampl.demo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    private Integer id;
 
    /**
     * 用户名
     */
    private String username;
 
  
 
    /**
     * 密码
     */
    private String password;
 
    /**
     * 角色
     */
    private Role role;
 
    /**
     * 状态：1-正常，0-封禁
     */
    private Integer status;
 
 
 
   
 
    private static final long serialVersionUID = 1L;}