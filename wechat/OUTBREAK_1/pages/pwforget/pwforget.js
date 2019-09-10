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
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },
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

  setCheck: function(){
    var check=0;
    while(check<99999){
      check=Math.floor(Math.random()*1000000);
    }
    this.setData({
      CHECK:check
    });
  },

  inputEmail:function(e){
    this.setData({
      email:e.detail.value
    })
  },
  inputnpw: function(e){
    this.setData({
      pw:e.detail.value
    })
  },
  inputrpw:function(e){
    this.setData({
      rpw:e.detail.value
    })
  },
  inputCheck: function(e){
    this.setData({
      check:e.detail.value
    })
  },

  getCheck: function(){
    var that=this;
    wx.request({
      url: 'http://49.235.194.230:443/sendEmail',
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
  resetpw: function(){
    var that=this;
    if(that.data.pw==that.data.rpw){
      if(that.data.CHECK==that.data.check){
        wx.request({
          url: 'http://49.235.194.230:443/changeData',
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