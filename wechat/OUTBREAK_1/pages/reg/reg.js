/**
 * @author: 马康耀
 * CreateTime: 2019-09-02
 * Update: 2019-09-08
 */

Page({

  /**
   * 页面的初始数据
   */
  data: {
    email: '',
    password: '',
    agpassword: '',
    name: '',
    judge: false,
    modalHidden2: true
  },

  /*
  *名称：modalTap2
  *描述：两次输入密码不一致的取消返回
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },

    /*
  *名称：modalChange2
  *描述：两次密码不一致的确认返回
  *参数：var
  *返回类型：void
  *作者：马康耀
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
  *名称：RegisterEmail
  *描述：获取输入框中的邮箱
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  RegisterEmail: function (re) {
    this.setData({
      email: re.detail.value
    })
  },

  /*
  *名称：RegisterName
  *描述：获取输入框中的姓名
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  RegisterName: function (rn) {
    this.setData({
      name: rn.detail.value
    })
  },

  /*
  *名称：RegisterPassword
  *描述：获取输入框中的密码
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  RegisterPassword: function (rp) {
    this.setData({
      password: rp.detail.value
    })
  },
  /*
  *名称：RegisterAgaPassword
  *描述：获取第二次输入的密码
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  RegisterAgaPassword: function (rap) {
    this.setData({
      agpassword: rap.detail.value
    })
  },

  /*
  *名称：isEmail
  *描述：判断email值是否为邮箱
  *参数：var
  *返回类型：void
  *作者：马康耀
  */
  isEmail:function(str){
    if(str==null||str==''){
      return false;
    }
    var reg = new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);
    return reg.test(str);
  },
  
    /*
  *名称：testIsEmail
  *描述：测试isEmail函数
  *参数：void
  *返回类型：void
  *作者：马康耀
  */
  testIsEmail:function(){
    console.log(this.isEmail(this.data.email));
  },

  /*
  *名称：RegisterBtn
  *描述：注册按钮的注册函数，点击该按钮可以完成注册
  *参数：void
  *返回类型：void
  *作者：马康耀
  */
  RegsterBtn: function () {
    var that = this;
    // 发出请求
    if(this.isEmail(this.data.email)){
      if((that.data.name!=null)&&(that.data.name!='')){
        if (that.data.password == that.data.agpassword&&that.data.password!='') {
          wx.request({
            url: 'http://49.235.194.230:443/Register',
            data: {
              email: that.data.email,
              name: that.data.name,
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
              // 如果改邮箱尚未注册，注册成功
              if (that.data.judge == true) {
                // 将邮箱给到app.js，作为全局变量
                var app = getApp();
                app.globalData.email = that.data.email;
                //提醒用户尽快完善个人信息
                wx.showModal({
                  title: '友情提醒',
                  content: '请尽快至「我的 - 编辑个人资料」完善个人信息，以便使用名片墙功能！',
                  confirmText: '立即修改',
                  success (res) {
                    if (res.confirm) {
                      wx.reLaunch({
                        url: '/pages/my/my',
                      })
                    }else{
                      wx.reLaunch({
                        url: '/pages/home/home',
                      })
                    }
                  },
                })
              }
            }
          })
        } else {
          // 此处用于两次输入密码不匹配的处理
          that.modalTap2()
        }
      }else{
        wx.showModal({
          title: '注册失败',
          content: '请输入姓名',
        })
      }
    }else{
      wx.showModal({
        title: '注册失败',
        content: '请输入正确的邮箱',
      })
    }
  }
})