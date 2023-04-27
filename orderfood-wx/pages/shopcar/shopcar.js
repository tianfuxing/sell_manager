// pages/shopcar.js
const http = require('../utils/http.js');
const app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    foodData:[],
    showShopNum:[],
    showFoodData:[],
    orderData:{},
    eatType:"TS",
    totalNum:0,
    totalPrice:0,
    addressData:[],
    curAddress:{},
    noAddress:false,
    showAddress:false,
    showLoading:true
  },

  chooseAddress: function (){
    this.data.showAddress=true;
    this.setData({
      showAddress:true
    })
  },

  updateCurAddress: function(e){
    for (var i=0;i<this.data.addressData.length;i++){
      if (this.data.addressData[i].id==e.currentTarget.dataset.id){
        this.data.curAddress=this.data.addressData[i];
        this.setData({
          curAddress:this.data.curAddress,
          showAddress:false
        })
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  getAddress: function(){
    http.request(
      "/specific/getAddress",
      "GET",
      {
      },
      {
        "content-type":"application/x-www-form-urlencoded"
      },
      (res)=>{
        this.data.addressData=res.data.data;
        if (this.data.addressData.length>0){
          this.data.curAddress=this.data.addressData[0];
        }
        this.setData({
          addressData:this.data.addressData,
          curAddress:this.data.curAddress
        })
      }
    )
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getTypeData();
    this.setData({
      imageUrl:app.globalData.imageUrl,
      orderSuccess:false,
      orderError:false,
      noFood:false,
      delShopCar:false
    })
  },

  closeAddress: function (){
    this.setData({
      showAddress:false
    });
  },

  unLoading: function(){
    if (this.data.showLoading){
      this.data.showLoading=false;
      this.setData({
        showLoading:false
      })
    }
  },

  showDelShopCar: function(){
    this.setData({
      delShopCar:true
    })
  },

  getTypeData: function(){
    http.request(
      "/specific/getWxFoodTypeAll",
      "GET",
      {},
      {
        "content-type":"application/x-www-form-urlencoded"
      },
      (res)=>{
        this.data.foodData=res.data.data.foodData;
        this.showShopNum(res.data.data.foodData);
      }
    )
  },

  showShopNum:function(data){
    var shopNum = wx.getStorageSync("shopNum");
    var showFoodData=[];
    var totalPrice=0;
    var totalNum = 0;
    if (shopNum!=null && shopNum!="" && shopNum.length>0){
      for (var i=0;i<data.length;i++){
        for (var j=0;j<shopNum.length;j++){
          if (data[i].id==shopNum[j].id && shopNum[j].num>0){
            data[i].shopNum = shopNum[j].num;
            showFoodData.push(data[i]);
            totalNum+=shopNum[j].num;
            totalPrice+=shopNum[j].num*data[i].price;
            break;
          }
        }
      }
    }
    this.data.totalNum=totalNum;
    this.data.totalPrice=totalPrice;
    this.data.showFoodData=showFoodData;
    this.setData({
      totalNum:totalNum,
      showFoodData:showFoodData,
      totalPrice:totalPrice
    })
  },

  handleChange: function(e){
    this.data.eatType=e.detail.value;
    this.setData({
      eatType:this.data.eatType
    })
  },

  addShopNum: function(e){
    var shopNum = wx.getStorageSync("shopNum");
    var flag=false;
    if (shopNum!=null && shopNum!="" && shopNum.length>0){
      for (var i=0;i<shopNum.length;i++){
        if (shopNum[i].id==e.currentTarget.dataset.id){
          shopNum[i].num++;
          flag=true;
          break;
        }
      }
    }else{
      shopNum=[];
    }
    if (!flag){
      var data={"id":e.currentTarget.dataset.id,"num":1,"typeid":e.currentTarget.dataset.typeid};
      shopNum.push(data);
    }
    wx.setStorageSync("shopNum",shopNum);
    this.showShopNum(this.data.foodData);
  },

  reduceShopNum: function(e){
    var shopNum = wx.getStorageSync("shopNum");
    if (shopNum!=null && shopNum!="" && shopNum.length>0){
      for (var i=0;i<shopNum.length;i++){
        if (shopNum[i].id==e.currentTarget.dataset.id){
          if (shopNum[i].num>0){
            shopNum[i].num--;
          }
          break;
        }
      }
    }
    wx.setStorageSync("shopNum",shopNum);
    this.showShopNum(this.data.foodData);
  },

  addOrder:function(){
    if (this.data.showFoodData.length==0){
      this.setData({
        noFood:true
      })
      return ;
    }
    this.setData({
      showLoading:true
    })
    this.data.orderData.type=this.data.eatType;
    this.data.orderData.totalmoney=this.data.totalPrice;
    this.data.orderData.totalnum=this.data.totalNum;
    this.data.orderData.address="";
    this.data.orderData.name="";
    this.data.orderData.tel="";
    if (this.data.orderData.type=="WM"){
      if (this.data.curAddress.address==null || this.data.curAddress.address==""){
        this.setData({
          noAddress:true,
          showLoading:false
        })
        return ;
      }
      this.data.orderData.address=this.data.curAddress.address;
      this.data.orderData.name=this.data.curAddress.name;
      this.data.orderData.tel=this.data.curAddress.tel;
    }
    var orderInfoList=[];
    for (var i=0;i<this.data.showFoodData.length;i++){
      var orderInfo = {};
      orderInfo.foodid=this.data.showFoodData[i].id;
      orderInfo.num=this.data.showFoodData[i].shopNum;
      orderInfo.price=this.data.showFoodData[i].price;
      orderInfo.totalprice=orderInfo.num*orderInfo.price;
      orderInfoList.push(orderInfo);
    }
    this.data.orderData.orderInfoList=orderInfoList;
    http.request(
      "/specific/addOrder",
      "POST",
      this.data.orderData,
      {
        "content-type":"application/json"
      },
      (res)=>{
        if (res.data.code=="200"){
          this.setData({
            orderSuccess:true
          })
          //清空购物车
          this.clearShopCar();
          //跳转到订单页面
          wx.switchTab({
            url: '../pages/order',
          })
        }else{
          this.setData({
            showLoading:false,
            orderError:true
          })
        }
      }
    )
  },

  clearShopCar: function(){
    wx.setStorageSync("shopNum",[]);
    this.showShopNum(this.data.foodData);
  },

  changeAddress: function(){
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getAddress();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})