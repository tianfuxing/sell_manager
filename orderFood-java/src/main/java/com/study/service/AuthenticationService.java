package com.study.service;

import com.study.bean.*;
import com.study.dao.AuthenticationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationMapper authenticationMapper;

    public boolean addData(User user){
        int result = authenticationMapper.addData(user);
        if (result > 0){
            return true;
        }
        return false;
    }

    public User searchDataByOpenid(String openid){
        User user = authenticationMapper.searchDataByOpenid(openid);
        return user;
    }

    public void updateData(User user){
        authenticationMapper.updateData(user);
    }

    public int addRole(Role role) {
        return authenticationMapper.addRole(role);
    }

    public int updateRole(Role role) {
        return authenticationMapper.updateRole(role);
    }

    public int deleteRole(String id) {
        authenticationMapper.deleteRoleMenu(id);
        return authenticationMapper.deleteRole(id);
    }

    public List<Role> getRolePage(Map dataMap) {
        return authenticationMapper.getRolePage(dataMap);
    }

    public int getRoleCount() {
        return authenticationMapper.getRoleCount();
    }

    public List<Role> getRoleAll() {
        return authenticationMapper.getRoleAll();
    }

    public int addMenu(Menu menu) {
        return authenticationMapper.addMenu(menu);
    }

    public int updateMenu(Menu menu) {
        return authenticationMapper.updateMenu(menu);
    }

    public int deleteMenu(Menu menu) {
        return authenticationMapper.deleteMenu(menu);
    }

    public List<Menu> getMenu() {
        List<Menu> allMenuList = authenticationMapper.getMenu();
        List<Menu> topMenuList = new ArrayList<Menu>();
        //获取所有顶级菜单
        for (Menu menu : allMenuList) {
            if (menu.getMasterid() == null || "".equals(menu.getMasterid())) {
                topMenuList.add(menu);
            }
        }
        setChildrenMenu(topMenuList, allMenuList);
        return topMenuList;
    }

    private void setChildrenMenu(List<Menu> topMenuList, List<Menu> allMenuList) {
        //给所有上级菜单赋予子级菜单
        for (Menu menu : topMenuList) {
            List<Menu> children = new ArrayList<Menu>();
            for (Menu childMenu : allMenuList) {
                if (childMenu.getMasterid() != null && menu.getId().equals(childMenu.getMasterid())) {
                    children.add(childMenu);
                }
            }
            if (children.size() > 0) {
                menu.setChildren(children);
                setChildrenMenu(children, allMenuList);
            }
        }
    }

    public List<Menu> getTopLevelMenuAll() {
        return authenticationMapper.getTopLevelMenuAll();
    }

    public void setMenuTree(List<MenuTree> rtnList, List<Menu> dataList) {
        for (Menu menu : dataList) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(menu.getId());
            menuTree.setName(menu.getName());
            menuTree.setRoute(menu.getRoute());
            menuTree.setIcon(menu.getIcon());
            menuTree.setSort(menu.getSort());
            menuTree.setChildren(getChildrenMenuTree(menuTree.getId()));
            rtnList.add(menuTree);
        }
    }

    public void setMenuTreeByUser(List<MenuTree> rtnList, List<Menu> dataList,String userid) {
        for (Menu menu : dataList) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(menu.getId());
            menuTree.setName(menu.getName());
            menuTree.setRoute(menu.getRoute());
            menuTree.setIcon(menu.getIcon());
            menuTree.setSort(menu.getSort());
            menuTree.setChildren(getChildrenMenuTreeByUser(menuTree.getId(),userid));
            rtnList.add(menuTree);
        }
    }

    public List<MenuTree> getChildrenMenuTree(String masterid) {
        return authenticationMapper.getChildrenMenuTree(masterid);
    }

    public List<MenuTree> getChildrenMenuTreeByUser(String masterid,String userid) {
        return authenticationMapper.getChildrenMenuTreeByUser(masterid,userid);
    }

    public List<RoleMenu> getRoleMenu(String roleid, int status) {
        return authenticationMapper.getRoleMenu(roleid, status);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addRoleMenu(RoleMenuList roleMenuList) {
        authenticationMapper.deleteRoleMenu(roleMenuList.getRoleid());
        Date date = new Date();
        List<RoleMenu> addList = new ArrayList<RoleMenu>();
        for (String allSelect : roleMenuList.getAllSelectMenu()) {//添加全选菜单
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId(UUID.randomUUID().toString());
            roleMenu.setRoleid(roleMenuList.getRoleid());
            roleMenu.setMenuid(allSelect);
            roleMenu.setStatus(RoleMenu.ALL_SELECT);
            roleMenu.setCreatetime(date);
            addList.add(roleMenu);
        }
        for (String halfSelect : roleMenuList.getHalfSelectMenu()) {//添加半选菜单
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId(UUID.randomUUID().toString());
            roleMenu.setRoleid(roleMenuList.getRoleid());
            roleMenu.setMenuid(halfSelect);
            roleMenu.setStatus(RoleMenu.HALF_SELECT);
            roleMenu.setCreatetime(date);
            addList.add(roleMenu);
        }
        if (addList.size() > 0) {
            authenticationMapper.addRoleMenuList(addList);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addUser(User user) {
        Date date = new Date();
        user.setId(UUID.randomUUID().toString());
        user.setCreatetime(date);
        authenticationMapper.addUser(user);
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        if (user.getUserRoleLists() != null) {
            for (String roleid : user.getUserRoleLists()) {
                UserRole userRole = new UserRole();
                userRole.setId(UUID.randomUUID().toString());
                userRole.setRoleid(roleid);
                userRole.setUserid(user.getId());
                userRole.setCreatetime(date);
                userRoleList.add(userRole);
            }
        }
        user.setUserRoleList(userRoleList);
        if (user.getUserRoleList().size() > 0) {
            authenticationMapper.addUserRole(user.getUserRoleList());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(com.study.bean.User user) {
        Date date = new Date();
        authenticationMapper.updateUser(user);
        List<UserRole> userRoleList = new ArrayList<UserRole>();
        if (user.getUserRoleLists() != null) {
            for (String roleid : user.getUserRoleLists()) {
                UserRole userRole = new UserRole();
                userRole.setId(UUID.randomUUID().toString());
                userRole.setRoleid(roleid);
                userRole.setUserid(user.getId());
                userRole.setCreatetime(date);
                userRoleList.add(userRole);
            }
        }
        user.setUserRoleList(userRoleList);
        authenticationMapper.deleteUserRole(user.getId());
        if (user.getUserRoleList().size() > 0) {
            authenticationMapper.addUserRole(user.getUserRoleList());
        }
    }

    public List<com.study.bean.User> getUserPage(Map dataMap) {
        List<com.study.bean.User> userList = authenticationMapper.getUserPage(dataMap);
        for (com.study.bean.User user : userList) {
            List<UserRole> userRoleList = authenticationMapper.getUserRoleByUserid(user.getId());
            user.setUserRoleList(userRoleList);
            List<String> userRoleLists = new ArrayList<>();
            for (UserRole userRole : userRoleList) {
                userRoleLists.add(userRole.getRoleid());
            }
            user.setUserRoleLists(userRoleLists);
        }
        return userList;
    }

    public int getUserCount() {
        return authenticationMapper.getUserCount();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(String id) {
        authenticationMapper.deleteUser(id);
        authenticationMapper.deleteUserRole(id);
    }

    public com.study.bean.User getUserByUsernamePassword(com.study.bean.User user) {
        return authenticationMapper.getUserByUsernamePassword(user);
    }

    public int updateLastLoginTime(String id, Date lastlogintime) {
        return authenticationMapper.updateLastLoginTime(id, lastlogintime);
    }

    public List<Menu> getTopLevelMenuByUser(String userid) {
        return authenticationMapper.getTopLevelMenuByUser(userid);
    }

    public int updatePassword(String id, String newPassword) {
        return authenticationMapper.updatePassword(id, newPassword);
    }

    public com.study.bean.User getUserById(String id) {
        return authenticationMapper.getUserById(id);
    }

    public boolean checkUsernameRepeat(String username) {
        int count = authenticationMapper.checkUsernameRepeat(username);
        return count > 0 ? true : false;
    }

    public int addDepartment(Department department) {
        return authenticationMapper.addDepartment(department);
    }

    public int updateDepartment(Department department) {
        return authenticationMapper.updateDepartment(department);
    }

    public int deleteDepartment(String id) {
        return authenticationMapper.deleteDepartment(id);
    }

    public List<Department> getDepartmentTree() {
        List<Department> allDepartmentList = authenticationMapper.getDepartmentAll();
        List<Department> topDepartmentList = new ArrayList<Department>();
        //获取所有顶级部门
        for (Department department : allDepartmentList) {
            if (department.getMasterid() == null || "".equals(department.getMasterid())) {
                topDepartmentList.add(department);
            }
        }
        setChildrenDepartment(topDepartmentList, allDepartmentList);
        return topDepartmentList;
    }

    private void setChildrenDepartment(List<Department> topDepartmentList, List<Department> allDepartmentList) {
        //给所有上级部门赋予子级部门
        for (Department department : topDepartmentList) {
            List<Department> children = new ArrayList<Department>();
            for (Department childDepartment : allDepartmentList) {
                if (childDepartment.getMasterid() != null && department.getId().equals(childDepartment.getMasterid())) {
                    children.add(childDepartment);
                }
            }
            if (children.size() > 0) {
                department.setChildren(children);
                setChildrenDepartment(children, allDepartmentList);
            }
        }
    }
}
