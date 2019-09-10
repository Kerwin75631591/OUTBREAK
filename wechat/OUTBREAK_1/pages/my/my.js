  /**
   * @author：王明钊 王嘉磊
   * CreateTime:2019-09-02
   * Update:2019-09-05
   */
// pages/my/my.js
Page({
  data: {
    modalHidden: true,
    modalHidden2: true,
    email:''
  },

  /*
  * 名称：界面函数
  * 描述：显示提示框
  * 作者：王明钊
  */
  modalTap: function (e) {
    this.setData({
      modalHidden: false
    })
  },

  /*
  * 名称：界面函数
  * 描述：隐藏提示框
  * 作者：王明钊
  */
  modalChange: function (e) {
    this.setData({
      modalHidden: true
    })
  },

  /*
  * 名称：界面函数
  * 描述：显示提示框
  * 作者：王明钊
  */
  modalTap2: function (e) {
    this.setData({
      modalHidden2: false
    })
  },

  /*
  * 名称：界面函数
  * 描述：隐藏提示框
  * 作者：王明钊
  */
  modalChange2: function (e) {
    this.setData({
      modalHidden2: true
    })
  },

  /*
  * 名称：获取信息函数
  * 描述：获取全局邮箱
  * 作者：王嘉磊
  */
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    var app = getApp();
    this.setData({
      email: app.globalData.email
    })
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})