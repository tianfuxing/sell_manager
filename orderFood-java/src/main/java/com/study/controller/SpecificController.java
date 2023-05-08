package com.study.controller;

import com.study.bean.specific.*;
import com.study.common.bean.CommonResult;
import com.study.common.util.NumberID;
import com.study.common.util.UserContextHolder;
import com.study.service.SpecificService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("specific")
public class SpecificController {

    @Autowired
    private SpecificService service;

    /**
     * 添加会员等级
     * @param userLevel
     * @param validResult
     * @return
     */
    @PostMapping("/addUserLevel")
    public CommonResult addUserLevel(@RequestBody @Valid UserLevel userLevel, BindingResult validResult){
        userLevel.setId(UUID.randomUUID().toString());
        userLevel.setCreatetime(new Date());
        int result = service.addUserLevel(userLevel);
        if (result > 0) {
            return CommonResult.success();
        }

        return CommonResult.failed();
    }

    /**
     * 更新会员等级
     * @param userLevel
     * @param validResult
     * @return
     */
    @PostMapping("/updateUserLevel")
    public CommonResult updateUserLevel(@RequestBody @Valid UserLevel userLevel, BindingResult validResult){
        int result = service.updateUserLevel(userLevel);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除会员等级
     * @param userLevel
     * @return
     */
    @PostMapping("/deleteUserLevel")
    public CommonResult deleteUserLevel(@RequestBody UserLevel userLevel){
        int result = service.deleteUserLevel(userLevel);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取会员等级
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getUserLevelByPage")
    public CommonResult getUserLevelByPage(@RequestParam int page, @RequestParam int limit){
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        List<UserLevel> dataList = service.getUserLevelByPage(dataMap);
        int count = service.getUserLevelByPageCount();
        return CommonResult.success(dataList, count);
    }

    /**
     * 添加菜品种类
     * @param foodType
     * @param validResult
     * @return
     */
    @PostMapping("/addFoodType")
    public CommonResult addFoodType(@RequestBody @Valid FoodType foodType, BindingResult validResult){
        foodType.setId(UUID.randomUUID().toString());
        foodType.setCreatetime(new Date());
        int result = service.addFoodType(foodType);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 更新菜品种类
     * @param foodType
     * @param validResult
     * @return
     */
    @PostMapping("/updateFoodType")
    public CommonResult updateFoodType(@RequestBody @Valid FoodType foodType, BindingResult validResult){
        int result = service.updateFoodType(foodType);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除菜品种类
     * @param foodType
     * @return
     */
    @PostMapping("/deleteFoodType")
    public CommonResult deleteFoodType(@RequestBody FoodType foodType){
        int result = service.deleteFoodType(foodType);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取菜品种类
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getFoodTypeByPage")
    public CommonResult getFoodTypeByPage(@RequestParam int page, @RequestParam int limit,@RequestParam String selectName){
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        dataMap.put("selectName", selectName);
        List<FoodType> dataList = service.getFoodTypeByPage(dataMap);
        int count = service.getFoodTypeByPageCount(dataMap);
        return CommonResult.success(dataList, count);
    }

    /**
     * 添加菜品
     * @param food
     * @param validResult
     * @return
     */
    @PostMapping("/addFood")
    public CommonResult addFood(@RequestBody @Valid Food food, BindingResult validResult){
        food.setId(UUID.randomUUID().toString());
        food.setCreatetime(new Date());
        int result = service.addFood(food);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 更新菜品
     * @param food
     * @param validResult
     * @return
     */
    @PostMapping("/updateFood")
    public CommonResult updateFood(@RequestBody @Valid Food food, BindingResult validResult){
        int result = service.updateFood(food);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 删除菜品
     * @param food
     * @return
     */
    @PostMapping("/deleteFood")
    public CommonResult deleteFood(@RequestBody Food food){
        int result = service.deleteFood(food);
        if (result > 0) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    /**
     * 分页获取菜品
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getFoodByPage")
    public CommonResult getFoodByPage(@RequestParam int page, @RequestParam int limit,@RequestParam String selectName){
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        dataMap.put("selectName", selectName);
        List<Food> dataList = service.getFoodByPage(dataMap);
        int count = service.getFoodByPageCount(dataMap);
        return CommonResult.success(dataList, count);
    }

    /**
     * 获取所有菜品种类
     * @return
     */
    @GetMapping("/getFoodTypeAll")
    public CommonResult getFoodTypeAll(){
        List<FoodType> dataList = service.getFoodTypeAll();
        return CommonResult.success(dataList);
    }

    /**
     * wx获取所有菜品种类
     * @return
     */
    @GetMapping("/getWxFoodTypeAll")
    public CommonResult getWxFoodTypeAll(){
        List<FoodType> typeList = service.getWxFoodTypeAll();
        List<Food> foodList = service.getWxFoodAll();
        List<Map> rtnList = new ArrayList<Map>();
        for (FoodType foodType : typeList){
            Map foodTypeMap = new HashMap();
            foodTypeMap.put("id",foodType.getId());
            foodTypeMap.put("name",foodType.getName());
            foodTypeMap.put("type","0");
            rtnList.add(foodTypeMap);
            for (Food food : foodList){
                if (foodType.getId().equals(food.getTypeid())){
                    int sold = service.getSoldByFoodId(food.getId());
                    Map foodMap = new HashMap();
                    foodMap.put("id",food.getId());
                    foodMap.put("typeid",food.getTypeid());
                    foodMap.put("name",food.getName());
                    foodMap.put("image",food.getImage());
                    foodMap.put("price",food.getPrice());
                    foodMap.put("sold",sold);
                    foodMap.put("type","1");
                    rtnList.add(foodMap);
                }
            }
        }
        Map rtnMap = new HashMap();
        rtnMap.put("typeData",typeList);
        rtnMap.put("foodData",rtnList);
        return CommonResult.success(rtnMap);
    }

    /**
     * 下单
     * @param order
     * @return
     */
    @PostMapping("/addOrder")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CommonResult addOrder(@RequestBody Order order){
        order.setId(UUID.randomUUID().toString());
        order.setUserid(UserContextHolder.getInstance().getId());
        order.setNumber(order.getType() + NumberID.getNumberId());
        order.setStatus(Order.RECEIVED);
        order.setCreatetime(new Date());
        for (OrderInfo orderInfo : order.getOrderInfoList()){
            orderInfo.setId(UUID.randomUUID().toString());
            orderInfo.setOrderid(order.getId());
        }
        service.addOrder(order);
        service.updateUserScore(Integer.parseInt(order.getTotalmoney().toString()));
        return CommonResult.success();
    }

    /**
     * 获取用户等级
     * @return
     */
    @GetMapping("/getUserLevel")
    public CommonResult getUserLevel(){
        return CommonResult.success(service.getUserLevel());
    }

    /**
     * 获取用户订单
     * @return
     */
    @GetMapping("/getUserOrder")
    public CommonResult getUserOrder(){
        List<Order> orderList = service.getUserOrder();
        return CommonResult.success(orderList);
    }

    /**
     * 保存地址
     * @param address
     * @return
     */
    @PostMapping("/addAddress")
    public CommonResult addAddress(@RequestBody Address address){
        if (address.getId()==null || address.getId().equals("")){
            address.setId(UUID.randomUUID().toString());
            address.setUserid(UserContextHolder.getInstance().getId());
            address.setDefaults("1");
            address.setCreatetime(new Date());
            service.addAddress(address);
        }else{
            service.updateAddress(address);
        }
        return CommonResult.success();
    }

    /**
     * 获取我的地址
     * @return
     */
    @GetMapping("/getAddress")
    public CommonResult getAddress(){
        return CommonResult.success(service.getAddress());
    }

    /**
     * 查询食品
     * @return
     */
    @GetMapping("/getFoodSearch")
    public CommonResult getFoodSearch(@RequestParam String searchWord){
        List<Food> foodList = service.getWxFoodAll();
        List<Map> rtnList = new ArrayList<Map>();
        for (Food food : foodList){
            if (food.getName().contains(searchWord)){
                int sold = service.getSoldByFoodId(food.getId());
                Map foodMap = new HashMap();
                foodMap.put("id",food.getId());
                foodMap.put("typeid",food.getTypeid());
                foodMap.put("name",food.getName());
                foodMap.put("image",food.getImage());
                foodMap.put("price",food.getPrice());
                foodMap.put("sold",sold);
                rtnList.add(foodMap);
            }
        }
        return CommonResult.success(rtnList);
    }

    /**
     * 获取订单详细
     * @param id
     * @return
     */
    @GetMapping("/getOrderById")
    public CommonResult getOrderById(@RequestParam String id){
        return CommonResult.success(service.getOrderById(id));
    }

    /**
     * 分页获取订单数据
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getOrderByPage")
    public CommonResult getOrderByPage(@RequestParam int page, @RequestParam int limit,@RequestParam String startTime,@RequestParam String endTime){
        int start = limit * page - limit;
        Map dataMap = new HashMap();
        dataMap.put("start", start);
        dataMap.put("limit", limit);
        dataMap.put("startTime", startTime);
        dataMap.put("endTime", endTime);
        List<Order> dataList = service.getOrderByPage(dataMap);
        int count = service.getOrderByPageCount(dataMap);
        return CommonResult.success(dataList, count);
    }

    /**
     * 订单取消
     * @param order
     * @return
     */
    @PostMapping("/orderCancel")
    public CommonResult orderCancel(@RequestBody Order order){
        service.orderCancel(order.getId());
        List<OrderInfo> orderInfos = service.getOrderInfoByOrderId(order.getId());
        for(OrderInfo orderInfo:orderInfos){
          //还原库存数量
          Food food=service.getFoodById(orderInfo.getFoodid());
          food.setNum(food.getNum()+orderInfo.getNum());
          service.updateFood(food);
        }
        return CommonResult.success();
    }

    /**
     * 结账
     * @param order
     * @return
     */
    @PostMapping("/orderAccomplish")
    public CommonResult orderAccomplish(@RequestBody Order order){
        service.orderAccomplish(order.getId());
        return CommonResult.success();
    }
}
