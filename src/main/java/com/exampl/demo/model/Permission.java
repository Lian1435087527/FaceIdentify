package com.exampl.demo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Permission implements Serializable {
    /**
     * 权限唯一标识ID
     */
    private Integer pId;
 
    /**
     * 权限名称
     */
    private String pName;
 
    /**
     * 权限别名
     */
    private String pAlias;
 
    /**
     * 创建时间
     */
    private Long created;
 
    /**
     * 修改时间
     */
    private Long updated;
 
    /**
     * 创建时间字符串
     */
    private String createdAt;
 
    /**
     * 修改时间字符串
     */
    private String updatedAt;
 
    private static final long serialVersionUID = 1L;}
