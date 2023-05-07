package com.study.service;

import com.study.bean.specific.*;
import com.study.common.util.UserContextHolder;
import com.study.dao.SpecificMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpecificService {

    @Autowired
    private SpecificMapper mapper;

    public int addUserLevel(UserLevel userLevel){
        return mapper.addUserLevel(userLevel);
    }

    public int updateUserLevel(UserLevel userLevel){
        return mapper.updateUserLevel(userLevel);
    }

    public int deleteUserLevel(UserLevel userLevel){
        return mapper.deleteUserLevel(userLevel);
    }

    public List<UserLevel> getUserLevelByPage(Map dataMap){
        return mapper.getUserLevelByPage(dataMap);
    }

    public int getUserLevelByPageCount(){
        return mapper.getUserLevelByPageCount();
    }

    public int addFoodType(FoodType foodType){
        return mapper.addFoodType(foodType);
    }

    public int updateFoodType(FoodType foodType){
        return mapper.updateFoodType(foodType);
    }

    public int deleteFoodType(FoodType foodType){
        return mapper.deleteFoodType(foodType);
    }

    public List<FoodType> getFoodTypeByPage(Map dataMap){
        return mapper.getFoodTypeByPage(dataMap);
    }

    public int getFoodTypeByPageCount(){
        return mapper.getFoodTypeByPageCount();
    }

    public int addFood(Food food){
        return mapper.addFood(food);
    }

    public int updateFood(Food food){
        return mapper.updateFood(food);
    }

    public int deleteFood(Food food){
        return mapper.deleteFood(food);
    }

    public List<Food> getFoodByPage(Map dataMap){
        return mapper.getFoodByPage(dataMap);
    }

    public int getFoodByPageCount(Map dataMap){
        return mapper.getFoodByPageCount(dataMap);
    }

    public List<FoodType> getFoodTypeAll(){
        return mapper.getFoodTypeAll();
    }

    public List<FoodType> getWxFoodTypeAll(){
        return mapper.getWxFoodTypeAll();
    }

    public List<Food> getWxFoodAll(){
        return mapper.getWxFoodAll();
    }

    public void addOrder(Order order){
        mapper.addOrder(order);
        mapper.addOrderInfo(order.getOrderInfoList());
    }

    public void updateUserScore(Integer score){
        mapper.updateUserScore(score, UserContextHolder.getInstance().getId());
    }

    public int getSoldByFoodId(String foodid){
        return mapper.getSoldByFoodId(foodid);
    }

    public Map getUserLevel(){
        Map rtnMap = new HashMap();
        List<UserLevel> userLevelList = mapper.getUserLevelAll();
        System.out.println(UserContextHolder.getInstance().getId());
        int score = mapper.getUserScore(UserContextHolder.getInstance().getId());
        rtnMap.put("curVipName",userLevelList.get(userLevelList.size()-1).getName());
        rtnMap.put("curVipSize",userLevelList.size()-1);
        for (int i=0;i<userLevelList.size();i++){
            if (score< userLevelList.get(i).getMaxscore()){
                rtnMap.put("curVipName",userLevelList.get(i).getName());
                rtnMap.put("curVipSize",i);
                break;
            }
        }
        rtnMap.put("vipLevel",userLevelList);
        return rtnMap;
    }

    public List<Order> getUserOrder(){
        List<Order> orderList = mapper.getUserOrder(UserContextHolder.getInstance().getId());
        for (Order order : orderList){
            List<OrderInfo> orderInfoList = mapper.getOrderInfoByOrderId(order.getId());
            order.setOrderInfoList(orderInfoList);
        }
        return orderList;
    }

    public int addAddress(Address address){
        return mapper.addAddress(address);
    }

    public int updateAddress(Address address){
        return mapper.updateAddress(address);
    }

    public List<Address> getAddress(){
        return mapper.getAddress(UserContextHolder.getInstance().getId());
    }

    public Order getOrderById(String id){
        Order order = mapper.getOrderById(id);
        List<OrderInfo> orderInfoList = mapper.getOrderInfoByOrderId(order.getId());
        order.setOrderInfoList(orderInfoList);
        return order;
    }

    public List<Order> getOrderByPage(Map dataMap){
        List<Order> dataList = mapper.getOrderByPage(dataMap);
        for (Order order : dataList){
            List<OrderInfo> orderInfoList = mapper.getOrderInfoByOrderId(order.getId());
            order.setOrderInfoList(orderInfoList);
        }
        return dataList;
    }

    public int getOrderByPageCount(){
        return mapper.getOrderByPageCount();
    }

    public int orderCancel(String id){
        return mapper.updateOrderStatus(id,Order.CANCEL);
    }

    public int orderAccomplish(String id){
        return mapper.updateOrderStatus(id,Order.ACCOMPLISH);
    }
}
