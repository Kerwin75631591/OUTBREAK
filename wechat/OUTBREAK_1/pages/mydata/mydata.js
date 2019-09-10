Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    phoneNum: '',
    duties: '',
    address: '',
    email: '',
    hideResetPwdModal: true,
    newPwd: null,
    repeatNewPwd: null,
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
    // 发出请求
    wx.request({
      url: 'http://49.235.194.230:443/UserData',
      data: {
        email: that.data.email
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)//打印到控制台
        // 获得来自后台的变量值
        var name = res.data.name;
        var phoneNum = res.data.phoneNum;
        var duties = res.data.duties;
        var address = res.data.address;
        var email = res.data.email;
        // 将后台数据传至data中
        that.setData({
          name: name,
          phoneNum: phoneNum,
          duties: duties,
          address: address,
          email: email
        }) 
      }
    })
  },

  exit: function (e) {
    wx.showModal({
      title: '提示',
      content: '是否确认退出',
      success: function (res) {
        if (res.confirm) {
          // console.log('用户点击确定')
          wx.removeStorageSync('student');
          //页面跳转
          wx.redirectTo({
            url: '../log/log',
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  /**
   * 重置密码函数
   */
  resetPwd: function (e) {
    this.setData({
      hideResetPwdModal: !this.data.hideResetPwdModal
    })
  },

  /**
    * 动态改变newPwd.value
    */
  bindinput_newPwd: function (e) {
    this.setData({
      newPwd: e.detail.value
    })
  },

  /**
    * 动态改变repeatNewPwd.value
    */
  bindinput_repeatNewPwd: function (e) {
    this.setData({
      repeatNewPwd: e.detail.value
    })
  },    

  /**
   * 修改密码框的取消函数
   */
  resetCancel: function(){
    this.setData({
      hideResetPwdModal: true,
      newPwd: null,
      repeatNewPwd: null,
    })
  },

  /**
   * 修改密码框的确认函数
   */
  resetConfirm: function (e) {
    if(this.data.newPwd == this.data.repeatNewPwd){
      if(this.data.newPwd != '' && this.data.newPwd != null){
        let parameterType = "password";
        var that = this;
        wx.request({
          url: 'http://49.235.194.230:443/changeData',
          data: {
            email: that.data.email,
            name: parameterType,
            value: that.data.newPwd,
          },
          header: {},
          method: 'GET',
          dataType: 'json',
          responseType: 'text',
          success: function (res) {
            wx.showLoading({
              title: '正在提交新的密码，请稍等！',
              duration: 500,
              mask: true
            })
          },
          fail: function (res) {
            wx.showToast({
              title: '提交失败，请重试！',
              icon: 'none',
              duration: 1000
            })
          },
          complete: function (res) {
            if (res.data.judge) {
              wx.showToast({
                title: '密码修改成功！',
                duration: 1000,
                mask: true
              })
            } else {
              wx.showToast({
                title: '密码修改失败，请重试！',
                icon: 'none',
                duration: 1000,
                mask: true
              })
            }
          },
        })
        this.setData({
          hideResetPwdModal: true,
          newPwd: null,
          repeatNewPwd: null,
        });
      }
      else{
        wx.showToast({
          title: '密码不能为空！',
          icon: 'none',
          duration: 1000,
          mask: true
        })
      }
    }
    else{
      wx.showToast({
        title: '两次输入的密码不一致！',
        icon: 'none',
        duration: 1000,
        mask: true
      })
    }
    
  },

  /**
   * 提示邮箱不可修改
   */
  setemail: function (e) {
    wx.showToast({
      title: '邮箱不可修改！',
      icon: 'none',
      duration: 1000,
      mask: true
    })
  },

  /**
   * 动态改变name.value
   */
  bindinput_name: function (e) {
    this.setData({
      name: e.detail.value
    })
  },

  /**
   * 当用户焦点离开name输入框时提交当前的value至数据库
   */
  bindblur_name: function () {
    let parameterType = "name";
    var that=this;
    wx.request({
      url: 'http://49.235.194.230:443/changeData',
      data: {
        email:that.data.email,
        name: parameterType,
        value: that.data.name
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        wx.showLoading({
          title: '正在提交新的姓名，请稍等！',
          duration: 500,
          mask: true
        })
      },
      fail: function(res) {
        wx.showToast({
          title: '提交失败，请重试！',
          icon: 'none',
          duration: 1000
        })
      },
      complete: function(res) {
        if(res.data.judge){
          wx.showToast({
            title: '姓名修改成功！',
            duration: 1000,
            mask: true
          })
        }else{
          wx.showToast({
            title: '姓名修改失败，请重试！',
            icon: 'none',
            duration: 1000,
            mask: true
          })
        }
      }
    })
  },

  /**
    * 动态改变phoneNum.value
    */
  bindinput_phoneNum: function (e) {
    this.setData({
      phoneNum: e.detail.value
    })
  },

  /**
   * 当用户焦点离开phoneNum输入框时提交当前的value至数据库
   */
  bindblur_phoneNum: function () {
    let parameterType = "phoneNumber";
    var that = this;
    wx.request({
      url: 'http://49.235.194.230:443/changeData',
      data: {
        email: that.data.email,
        name: parameterType,
        value: that.data.phoneNum
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        wx.showLoading({
          title: '正在提交新的联系方式，请稍等！',
          duration: 500,
          mask: true
        })
      },
      fail: function (res) {
        wx.showToast({
          title: '提交失败，请重试！',
          icon: 'none',
          duration: 1000
        })
      },
      complete: function (res) {
        if (res.data.judge) {
          wx.showToast({
            title: '联系方式修改成功！',
            duration: 1000,
            mask: true
          })
        } else {
          wx.showToast({
            title: '联系方式修改失败，请重试！',
            icon: 'none',
            duration: 1000,
            mask: true
          })
        }
      }
    })
  },

  /**
    * 动态改变duties.value
    */
  bindinput_duties: function (e) {
    this.setData({
      duties: e.detail.value
    })
  },

  /**
   * 当用户焦点离开duties输入框时提交当前的value至数据库
   */
  bindblur_duties: function () {
    let parameterType = "duties";
    var that = this;
    wx.request({
      url: 'http://49.235.194.230:443/changeData',
      data: {
        email: that.data.email,
        name: parameterType,
        value: that.data.duties
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        wx.showLoading({
          title: '正在提交新的职务，请稍等！',
          duration: 500,
          mask: true
        })
      },
      fail: function (res) {
        wx.showToast({
          title: '提交失败，请重试！',
          icon: 'none',
          duration: 1000
        })
      },
      complete: function (res) {
        if (res.data.judge) {
          wx.showToast({
            title: '职务修改成功！',
            duration: 1000,
            mask: true
          })
        } else {
          wx.showToast({
            title: '职务修改失败，请重试！',
            icon: 'none',
            duration: 1000,
            mask: true
          })
        }
      }
    })
  },

  /**
  * 动态改变address.value
  */
  bindinput_address: function (e) {
    this.setData({
      address: e.detail.value
    })
  },

  /**
   * 当用户焦点离开address输入框时提交当前的value至数据库
   */
  bindblur_address: function () {
    let parameterType = "address";
    var that = this;
    wx.request({
      url: 'http://49.235.194.230:443/changeData',
      data: {
        email: that.data.email,
        name: parameterType,
        value: that.data.address
      },
      header: {},
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function (res) {
        wx.showLoading({
          title: '正在提交新的地址，请稍等！',
          duration: 500,
          mask: true
        })
      },
      fail: function (res) {
        wx.showToast({
          title: '提交失败，请重试！',
          icon: 'none',
          duration: 1000
        })
      },
      complete: function (res) {
        if (res.data.judge) {
          wx.showToast({
            title: '地址修改成功！',
            duration: 1000,
            mask: true
          })
        } else {
          wx.showToast({
            title: '地址修改失败，请重试！',
            icon: 'none',
            duration: 1000,
            mask: true
          })
        }
      }
    })
  },
})


