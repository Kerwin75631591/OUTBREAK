/**
 * @author:王嘉磊、胡昱
 * CreateTime:2019-09-02
 * Update:2019-09-08
 */
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    password:'',
    judge:false,
    modalHidden2: true
  },

  /*
  *名称：modalTap2
  *描述：密码错误后的取消返回
  *参数：var
  *返回类型：void
  *作者：胡昱
  */
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },

    /*
  *名称：modalChange2
  *描述：密码错误后的确定返回
  *参数：var
  *返回类型：void
  *作者：胡昱
  */
  modalChange2: function (e) {
    this.setData({
      modalHidden2: true
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },

  /*
  *名称：LoginEmail
  *描述：获取输入框中输入的邮箱
  *参数：var
  *返回类型：void
  *作者：王嘉磊
  */
  LoginEmail: function (lm) {
    this.setData({
      email: lm.detail.value
    })
  },

  /*
  *名称：LoginPassword
  *描述：获取输入框中的密码
  *参数：var
  *返回类型：void
  *作者：王嘉磊
  */
  LoginPassword: function (lp) {
    this.setData({
      password: lp.detail.value
    })
  },

  /*
  *名称：LoginBtn
  *描述：登录按钮的注册函数，点击该函数进行登录
  *参数：void
  *返回类型：void
  *作者：王嘉磊
  */
  LoginBtn: function() {
    var that = this;
    // 发出请求
    wx.request({
      url: 'http://49.235.194.230:443/Login',
      data: {
        email: that.data.email,
        password: that.data.password
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        // 获得来自后台的变量值
        var judge = res.data.judge;
        // 将后台数据传至data中
        that.setData({
          judge: judge
        })
        // 如果邮箱与密码匹配，登录成功
        if (that.data.judge == true) {
          // 将邮箱给到app.js，作为全局变量
          var app = getApp();
          wx.reLaunch({
            url: '/pages/home/home',
          })
        } else {
          // 此处用于处理登录密码错误的情况
          that.modalTap2();
        }
      }
    })
  },

  /*
  *名称：FogetPwdBtn
  *描述：忘记密码链接的注册函数，点击该按钮进入找回找回密码界面
  *参数：void
  *返回类型：void
  *作者：胡昱
  */
  FogetPwdBtn: function() {
    wx.navigateTo({
      url: '../pwforget/pwforget',
    })
  },

  /*
  *名称：RegisterBtn
  *描述：注册按钮的注册函数，点击该按钮跳转至注册界面
  *参数：var
  *返回类型：void
  *作者：王嘉磊
  */
  RegisterBtn: function () {
    wx.navigateTo({
      url: '/pages/reg/reg',
      success: function(res) {},
      fail: function(res) {},
      complete: function(res) {},
    })
  }
  
})