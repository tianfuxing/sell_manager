package com.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.study.bean.*;
import com.study.common.annotation.NoLogin;
import com.study.common.bean.CommonResult;
import com.study.bean.User;
import com.study.common.util.JWTUtil;
import com.study.common.util.UserContextHolder;
import com.study.service.AuthenticationService;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 鉴权controller
 */
@RestController
@RequestMapping("authority")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Value("${study.wx.appId:}")
    public String appId;

    @Value("${study.wx.appSecret:}")
    public String appSecret;

    @Value("${study.wx.requestUrl:}")
    public String requestUrl;

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 微信小程序登录接口
     * @param code
     * @return
     */
    @NoLogin
    @PostMapping("/wxLogin")
    public CommonResult wxLogin(@RequestParam String code){
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", appId);
        //小程序secret
        requestUrlParam.put("secret", appSecret);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSONObject.parseObject(doPost(requestUrl, requestUrlParam));
        String openid = jsonObject.getString("openid");
        if (jsonObject!=null && openid!=null && !"".equals(openid)){
            User user = authenticationService.searchDataByOpenid(openid);
            Date date = new Date();
            if (user==null){
                user = new User();
                user.setCreatetime(date);
                user.setLastlogintime(date);
                user.setId(UUID.randomUUID().toString());
                user.setOpenid(openid);
                user.setScore(0);
                authenticationService.addData(user);
            }else{
                user.setLastlogintime(date);
                authenticationService.updateData(user);
            }
            user.setName("default");
            //返回token
            String token = JWTUtil.sign(user);
            user.setToken(token);
            return CommonResult.success(user);
        }
        return CommonResult.failed();
    }

    private String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    public CommonResult addRole(@RequestBody @Valid Role role, BindingResult validResult) {
        Date date = new Date();
        role.setId(UUID.randomUUID().toString());
        role.setCreatetime(date);
        int result = authenticationService.addRole(role);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @PostMapping("/updateRole")
    public CommonResult updateRole(@RequestBody @Valid Role role, BindingResult validResult) {
        int result = authenticationService.updateRole(role);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除角色
     *
     * @param role
     * @return
     */
    @PostMapping("/deleteRole")
    public CommonResult deleteRole(@RequestBody Role role) {
        int result = authenticationService.deleteRole(role.getId());
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取角色
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getRolePage")
    public CommonResult getRolePage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<Role> dataList = authenticationService.getRolePage(dataMap);
        int count = authenticationService.getRoleCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("/getRoleAll")
    public CommonResult getRoleAll() {
        List<Role> dataList = authenticationService.getRoleAll();
        return CommonResult.success(dataList);
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/addMenu")
    public CommonResult addMenu(@RequestBody @Valid Menu menu, BindingResult validResult) {
        Date date = new Date();
        menu.setId(UUID.randomUUID().toString());
        menu.setCreatetime(date);
        int result = authenticationService.addMenu(menu);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/updateMenu")
    public CommonResult updateMenu(@RequestBody @Valid Menu menu, BindingResult validResult) {
        int result = authenticationService.updateMenu(menu);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除菜单
     *
     * @param menu
     * @return
     */
    @PostMapping("/deleteMenu")
    public CommonResult deleteMenu(@RequestBody Menu menu) {
        int result = authenticationService.deleteMenu(menu);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("/getMenu")
    public CommonResult getMenu() {
        List<Menu> dataList = authenticationService.getMenu();
        return CommonResult.success(dataList);
    }

    /**
     * 获取所有顶级菜单
     *
     * @return
     */
    @GetMapping("/getTopLevelMenuAll")
    public CommonResult getTopLevelMenuAll() {
        List<Menu> dataList = authenticationService.getTopLevelMenuAll();
        return CommonResult.success(dataList);
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    @GetMapping("/getMenuTree")
    public CommonResult getMenuTree() {
        List<MenuTree> rtnList = new ArrayList<MenuTree>();
        List<Menu> dataList = authenticationService.getTopLevelMenuAll();
        authenticationService.setMenuTree(rtnList, dataList);
        return CommonResult.success(rtnList);
    }

    /**
     * 根据角色id获取角色的菜单权限
     *
     * @param roleid
     * @return
     */
    @GetMapping("/getRoleMenu")
    public CommonResult getRoleMenu(@RequestParam String roleid) {
        List<RoleMenu> dataList = authenticationService.getRoleMenu(roleid, RoleMenu.ALL_SELECT);
        List<String> rtnList = new ArrayList<String>();
        for (RoleMenu roleMenu : dataList) {
            rtnList.add(roleMenu.getMenuid());
        }
        return CommonResult.success(rtnList);
    }

    /**
     * 添加角色菜单权限
     *
     * @param roleMenuList
     * @return
     */
    @PostMapping("/addRoleMenu")
    public CommonResult addRoleMenu(@RequestBody @Valid RoleMenuList roleMenuList, BindingResult validResult) {
        authenticationService.addRoleMenu(roleMenuList);
        return CommonResult.success();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public CommonResult addUser(@RequestBody @Valid User user, BindingResult validResult) {
        if (authenticationService.checkUsernameRepeat(user.getUsername())) {
            return CommonResult.failed("已存在相同用户名");
        }
        authenticationService.addUser(user);
        return CommonResult.success();
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public CommonResult updateUser(@RequestBody @Valid User user, BindingResult validResult) {
        authenticationService.updateUser(user);
        return CommonResult.success();
    }

    /**
     * 分页获取用户信息
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getUserPage")
    public CommonResult getUserPage(@RequestParam int page, @RequestParam int limit) {
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<User> dataList = authenticationService.getUserPage(dataMap);
        int count = authenticationService.getUserCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @PostMapping("/deleteUser")
    public CommonResult deleteUser(@RequestBody User user) {
        authenticationService.deleteUser(user.getId());
        return CommonResult.success();
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @NoLogin
    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        User curUser = authenticationService.getUserByUsernamePassword(user);
        if (curUser == null) {
            return CommonResult.failed("账号或密码错误");
        }
        //更新最后登录时间
        authenticationService.updateLastLoginTime(curUser.getId(), new Date());
        //返回token
        String token = JWTUtil.sign(curUser);
        curUser.setToken(token);
        return CommonResult.success(curUser);
    }

    /**
     * 修改密码
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(@RequestBody JSONObject jsonObject) {
        String oldPassword = jsonObject.get("oldPassword") == null ? "" : jsonObject.get("oldPassword").toString();
        String newPassword = jsonObject.get("newPassword") == null ? "" : jsonObject.get("newPassword").toString();
        String repeatNewPassword = jsonObject.get("repeatNewPassword") == null ? "" : jsonObject.get("repeatNewPassword").toString();
        if ("".equals(oldPassword) || "".equals(newPassword) || "".equals(repeatNewPassword)) {
            return CommonResult.failed("密码不能为空");
        }
        if (!newPassword.equals(repeatNewPassword)) {
            return CommonResult.failed("两次新密码输入不一致");
        }
        User curUser = authenticationService.getUserById(UserContextHolder.getInstance().getId());
        if (!oldPassword.equals(curUser.getPassword())) {
            return CommonResult.failed("原密码输入错误");
        }
        int result = authenticationService.updatePassword(curUser.getId(), newPassword);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 获取用户的菜单树权限
     *
     * @return
     */
    @GetMapping("/getUserMenuList")
    public CommonResult getUserMenuList() {
        User curUser = UserContextHolder.getInstance();
        //获取用户的菜单树权限
        List<Menu> dataList = authenticationService.getTopLevelMenuByUser(curUser.getId());
        List<MenuTree> rtnList = new ArrayList<MenuTree>();
        authenticationService.setMenuTreeByUser(rtnList, dataList,curUser.getId());
        return CommonResult.success(rtnList);
    }

    /**
     * 添加部门
     *
     * @param department
     * @param validResult
     * @return
     */
    @PostMapping("/addDepartment")
    public CommonResult addDepartment(@RequestBody @Valid Department department, BindingResult validResult) {
        department.setId(UUID.randomUUID().toString());
        department.setCreatetime(new Date());
        int result = authenticationService.addDepartment(department);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 修改部门
     *
     * @param department
     * @param validResult
     * @return
     */
    @PostMapping("/updateDepartment")
    public CommonResult updateDepartment(@RequestBody @Valid Department department, BindingResult validResult) {
        int result = authenticationService.updateDepartment(department);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除部门
     *
     * @param department
     * @return
     */
    @PostMapping("/deleteDepartment")
    public CommonResult deleteDepartment(@RequestBody Department department) {
        int result = authenticationService.deleteDepartment(department.getId());
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 获取部门树
     *
     * @return
     */
    @GetMapping("/getDepartmentTree")
    public CommonResult getDepartmentTree() {
        List<Department> rtnList = authenticationService.getDepartmentTree();
        return CommonResult.success(rtnList);
    }
}
