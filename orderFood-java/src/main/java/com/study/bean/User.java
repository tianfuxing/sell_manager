package com.study.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class User {

    private String id;

    private String openid;

    @NotBlank(message = "用户名称不能为空")
    @Length(max = 10, message = "用户名称长度不能超过10个字符")
    private String name;

    @NotBlank(message = "账号不能为空")
    @Length(max = 20, message = "账号长度不能超过20个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(max = 20, message = "密码长度不能超过20个字符")
    private String password;

    private Date createtime;

    private Date lastlogintime;

    private String departmentid;

    private String departmentname;

    private int score;

    private List<UserRole> userRoleList;

    private List<String> userRoleLists;

    private List<MenuTree> menuList;

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<String> getUserRoleLists() {
        return userRoleLists;
    }

    public void setUserRoleLists(List<String> userRoleLists) {
        this.userRoleLists = userRoleLists;
    }

    public List<MenuTree> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuTree> menuList) {
        this.menuList = menuList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
