  /**
   * @author：王嘉磊
   * CreateTime:2019-09-02
   * Update:2019-09-03
   */
// pages/info/info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    number: 0,
    list: null
  },

  /*
   *名称：生命周期函数
   *描述：监听页面加载 获取系统消息
   *作者：王嘉磊
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'http://49.235.194.230:443/getMessage',
      method: 'GET',
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data);
        that.setData({
          number: res.data.number,
          list: res.data.list
        })
      }

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
    this.onLoad();
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