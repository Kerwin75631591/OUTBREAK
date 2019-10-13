/**
 * @author:马康耀、王明钊
 * CreateTime:2019-09-02
 * Update:2019-09-05
 */

Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    pw:'',
    rpw:'',
    CHECK:0,
    check:0,
    modalHidden2: true
  },

  /**
  *名称：modalTap2
  *描述：两次输入密码不一致取消按钮
  *参数：var
  *返回类型：void
  *作者：王明钊
  */
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },

  /**
  *名称：modalChange2
  *描述：两次输入密码不一致取消按钮
  *参数：var
  *返回类型：void
  *作者：王明钊
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

  /**
  *名称：setCheck
  *描述：发送验证码
  *参数：void
  *返回类型：void
  *作者：马康耀
  */
  setCheck: function(){
    var check=0;
    while(check<99999){
      check=Math.floor(Math.random()*1000000);
    }
    this.setData({
      CHECK:check
    });
  },

  /**
  *名称：inputEmail
  *描述：获取输入的邮箱
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  inputEmail:function(e){
    this.setData({
      email:e.detail.value
    })
  },

  /**
  *名称：inputpw
  *描述：获取输入的密码
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  inputnpw: function(e){
    this.setData({
      pw:e.detail.value
    })
  },

  /**
  *名称：inputrpw
  *描述：获取第二次输入的密码
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  inputrpw:function(e){
    this.setData({
      rpw:e.detail.value
    })
  },

  /**
  *名称：inputCheck
  *描述：获取输入的验证码
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  inputCheck: function(e){
    this.setData({
      check:e.detail.value
    })
  },

  /**
  *名称：getCheck
  *描述：获取验证码按钮的注册函数
  *参数：void
  *返回类型：void
  *作者：马康耀
  */
  getCheck: function(){
    var that=this;
    wx.request({
      url: 'http://127.0.0.1:443/sendEmail',
      data:{
        email:that.data.email
      },
      method:'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function(res){
        console.log(res.data);
        that.setData({
          CHECK:res.data.code
        });
      }
    })
  },

  /**
  *名称：resetpw
  *描述：重置密码按钮的注册函数
  *参数：void
  *返回类型：void
  *作者：马康耀、王明钊
  */
  resetpw: function(){
    var that=this;
    if(that.data.pw==that.data.rpw){
      if(that.data.CHECK==that.data.check){
        wx.request({
          url: 'http://127.0.0.1:443/changeData',
          data: {
            email:that.data.email,
            name:'password',
            value:that.data.pw
          },
          method:'GET',
          header: {
            'content-type':'application/json'
          },
          success: function(res){
            var judge=res.data.judge;
            if(judge){
              wx.showModal({
                title: '重置密码成功',
                content: '重置密码成功',
                success:function(res){
                  if(res.confirm){
                    wx.navigateBack({
                      //
                    })
                    /*
                    wx.redirectTo({
                      url: '../log/log',
                    })*/
                  }
                }
              })
            }
          }
        })
      }else{
        wx.showModal({
          title: '重置密码失败',
          content: '验证码错误',
        })
      }
    }else{
      wx.showModal({
        title: '重置密码失败',
        content: '两次输入密码不一致',
      })
    }
    console.log(that.data);
  }
})