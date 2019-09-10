// pages/home/home.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email: '',
    Number: 0,
    list: '',
    userList: null,
    hiddenAssessmentModal: false,
    A1: null,
    A2: null,
    A3: null,
    A4: null,
    A5: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var app = getApp();
    this.setData({
      email: app.globalData.email
    })

    var that = this;
    wx.request({
      url: 'http://49.235.194.230:443/SimpleMeeting',
      data: {
        email: that.data.email
      },
      method: 'GET',
      header: {
        'content-type': "applicaton/json"
      },
      success: function (res) {
        console.log(res.data); // 将后台得到的数据打印到控制台
        var list = res.data.list;
        if (list != null) {
          that.setData({
            Number: res.data.Number,
            list: res.data.list
          })
        } else {
          // 此处用处理该用户无有关会议的情况
        }
      }
    })

    wx.request({
      url: 'http://49.235.194.230:443/searchAssessment',
      data: {
        email: that.data.email
      },
      method: 'GET',
      header: {
        'content-type': "applicaton/json"
      },
      success: function (res) {
        console.log(res.data);
        var temp = res.data.list;
        if (temp != null) {
          that.setData({
            userList: res.data.list
          })
        } else {
          // 此处用处理该用户不需要进行会议评估的情况
        }
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

  },

  /**
   * 用户点击详细信息进入该会议详细信息界面
   */
  // 跳转到详情页
  jumpToDetail: function (v) {
    console.log(v.currentTarget.id);
    wx.navigateTo({
      url: '/pages/meetingdetail/meetingdetail?mid=' + v.currentTarget.id
    })
  },

  /**
  * 动态改变A1.value
  */
  bindinput_Q1: function (e) {
    this.setData({
      A1: e.detail.value
    })
    if (this.data.A1 > 10 || this.data.A1 < 0) {
      wx.showToast({
        title: '错误！',
        content: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A1: null
      })
    }
  },

  /**
  * 动态改变A2.value
  */
  bindinput_Q2: function (e) {
    this.setData({
      A2: e.detail.value
    })
    if (this.data.A2 > 10 || this.data.A2 < 0) {
      wx.showToast({
        title: '错误！',
        content: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A2: null
      })
    }
  },

  /**
  * 动态改变A3.value
  */
  bindinput_Q3: function (e) {
    this.setData({
      A3: e.detail.value
    })
    if (this.data.A3 > 10 || this.data.A3 < 0) {
      wx.showToast({
        title: '错误！',
        content: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A3: null
      })
    }
  },

  /**
  * 动态改变A4.value
  */
  bindinput_Q4: function (e) {
    this.setData({
      A4: e.detail.value
    })
    if (this.data.A4 > 10 || this.data.A4 < 0) {
      wx.showToast({
        title: '错误！',
        content: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A4: null
      })
    }
  },

  /**
  * 动态改变A5.value
  */
  bindinput_Q5: function (e) {
    this.setData({
      A5: e.detail.value
    })
    if (this.data.A5 > 10 || this.data.A5 < 0) {
      wx.showToast({
        title: '错误！',
        content: '请输入0～10',
        duration: 1000,
        mask: true,
      })
      this.setData({
        A5: null
      })
    }
  },

  /**
  * 会议评估框的取消函数
  */
  assessmentCancel: function () {
    wx.showToast({
      content: '请您尽快完成会议评估',
      duration: 1000,
      mask: true,
    });
    this.setData({
      hiddenAssessmentModal: true,
    })
  },

  /**
  * 会议评估框的确认函数
  */
  assessmentConfirm: function (e) {
    var mid = e.currentTarget.dataset.mid;
    if (this.data.A1 == null || this.data.A1 == '' || this.data.A2 == null || this.data.A2 == '' || this.data.A3 == null || this.data.A3 == '' ||this.data.A4 == null || this.data.A4 == '' || this.data.A5 == null || this.data.A5 == '') {
      wx.showToast({
        title: '错误！',
        content: '请输入正确的分数！',
        duration: 1000,
        mask: true,
      })
    } else {
      console.log(this.data.A1, this.data.A2, this.data.A3, this.data.A4, this.data.A5);
      var that=this;
      wx.request({
        url: 'http://49.235.194.230:443/Assessment',
        data: {
          email: this.data.email,
          mid: mid,
          grade1: that.data.A1,
          grade2: that.data.A2,
          grade3: that.data.A3,
          grade4: that.data.A4,
          grade5: that.data.A5
        },
        header: {},
        method: 'GET',
        dataType: 'json',
        responseType: 'text',
        success: function (res) {
          wx.showLoading({
            content: '正在提交会议评估表，请稍等！',
            duration: 500,
            mask: true
          }
          )
        },
        fail: function (res) {
          wx.showToast({
            content: '提交失败，请重试！',
            icon: 'none',
            duration: 1000
          })
        },
        complete: function (res) {
          if (res.data.judge) {
            wx.showToast({
              content: '会议评估表提交成功！',
              duration: 1000,
              mask: true
            })
          } else {
            wx.showToast({
              content: '会议评估表提交失败，请重试！',
              icon: 'none',
              duration: 1000,
              mask: true
            })
          }
          getCurrentPages()[getCurrentPages().length - 1].onLoad();
          
        },
      });
      
    }
  },

})