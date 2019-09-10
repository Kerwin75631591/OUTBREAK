  /**
   * @author：马康耀
   * CreateTime:2019-09-02
   * Update:2019-09-06
   */
// pages/qrcode/qr.js
var wxbarcode = require('../../utils/qrcodeCreator.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    mid:0
  },

  /*
   *名称：生命周期函数
   *描述：生成二维码
   *作者：马康耀
   */
  onLoad: function (options) {
    var that=this;
    that.setData({
      email:options.email,
      mid:options.mid
    });
    wxbarcode.qrcode('qrcode','Email'+this.data.email+'  MeetingID'+this.data.mid,400,400);
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
    this.onLoad();
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