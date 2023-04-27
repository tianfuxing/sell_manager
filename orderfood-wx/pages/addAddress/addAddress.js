// pages/addAddress.js
const http = require('../utils/http.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:"",
    address:"",
    name:"",
    tel:"",
    showLoading:false,
    showSuccess:false,
    showError:false,
    errorMessage:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.id=options.id;
    this.data.address=options.address;
    this.data.name=options.name;
    this.data.tel=options.tel;
    this.setData({
      address:this.data.address,
      name:this.data.name,
      tel:this.data.tel
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  getInputValue: function(e){
    let valueName = e.target.dataset.name;
    if (valueName=="address"){
      this.data.address=e.detail.value;
    }else if (valueName=="name"){
      this.data.name=e.detail.value;
    }else if (valueName=="tel"){
      this.data.tel=e.detail.value;
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  addAddress: function(){
    if (this.data.address==null || this.data.address==""){
      this.setData({
        showError:true,
        errorMessage:"地址不能为空"
      })
      return;
    }
    if (this.data.name==null || this.data.name==""){
      this.setData({
        showError:true,
        errorMessage:"联系人不能为空"
      })
      return;
    }
    if (this.data.tel==null || this.data.tel==""){
      this.setData({
        showError:true,
        errorMessage:"手机号不能为空"
      })
      return;
    }
    this.setData({
      showLoading:true
    })
    http.request(
      "/specific/addAddress",
      "POST",
      {
        "id":this.data.id,
        "address":this.data.address,
        "name":this.data.name,
        "tel":this.data.tel
      },
      {
        "content-type":"application/json"
      },
      (res)=>{
        if (res.data.code=="200"){
          wx.navigateBack({
            delta: 1,
          })
        }else{
          this.setData({
            showLoading:false,
            showError:true,
            errorMessage:"保存地址失败"
          })
        }
      }
    )
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