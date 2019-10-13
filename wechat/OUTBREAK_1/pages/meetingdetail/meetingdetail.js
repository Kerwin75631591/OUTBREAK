/**
 * @author: 马康耀、胡昱
 */
Page({

  /**
   * 页面的初始数据
   */
  data: {
    email:'',
    mid:0,
    //saved in data
    num:0,//number of invited people
  
    meetingname:'',
    topic:'',
    time:'',
    place:'',
    state:0,
    files:null,
    content:'',
    //saved in data.peoplei(0----n-1)
    //name saved in data.people.name,TOF saved in data.people.TOF
    people:'',
    TOF:null
    //people:[{name:'Harry',TOF:1,email:'Harry@owl.com'},{name:'Sirius',TOF:0,email:'Sirius@owl.com'}]
  },

  /**
  * 名称：onLoad
  * 描述：生命周期函数--监听页面加载
  * 参数：var
  * 返回类型：void
  * 作者：马康耀
  */
  onLoad: function (options) {
    var app = getApp();
    this.setData({
      email: app.globalData.email,
      mid:options.mid
    });
    var that=this;
    wx.request({
      url: 'http://127.0.0.1:443/ComplexMeeting',
      data:{
        mid:that.data.mid
      },
      method:"GET",
      header: {
        'content-type': "applicaton/json"
      },
      success: function(res){
        that.setData({
          num:res.data.number,
          meetingname:res.data.meeting.name,
          topic:res.data.meeting.topic,
          time:res.data.meeting.time,
          place:res.data.meeting.place,
          state:res.data.meeting.state,
          files:res.data.meeting.fileUrl,
          content:res.data.meeting.content,
          people:res.data.list
        });
        for(var i=0;i<that.data.num;i++){
          if(that.data.email==that.data.people[i].email){
            that.setData({
              TOF:that.data.people[i].TOF
            });
            /*
            if(that.data.people[i].TOF==1){
              that.data.TOF=true;
            }else{
              that.data.TOF=false;
            }*/
            //that.data.TOF=that.data.people[i].TOF;
            break;
          }
        }
        //console.log(that.data);
      }
    });
    console.log(this.data);
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

  //functions
  download: function(){
    wx.downloadFile({
      url:this.data.files,
      success: function(res){
        console.log('success');
      }
    });
  },

  /**
  * 名称：setTOF
  * 描述：确认参加会议
  * 参数：void
  * 返回类型：void
  * 作者：马康耀
  */
  setTOF:function(){
    console.log(this.data.TOF);
    var that=this;
    wx.showModal({
      title: '确认参加会议',
      content: '确认后将无法更改，您是否确认参加会议',
      success:function(res){
        if(res.confirm){
          wx.request({
            url: 'http://127.0.0.1:443/setTOF',
            data:{
              email:that.data.email,
              mid:that.data.mid
            },
            method:'GET',
            header: {
              'content-type': 'application/json'
            },
            success:function(res){
              var judge=res.data.judge;
              if(judge){
                that.setData({TOF:true});
                wx.showModal({
                  title: '确认参加会议',
                  content: '确认成功',
                })
              }else{
                wx.showModal({
                  title: '确认参加会议',
                  content: '确认失败',
                })
              }
            }
          })
        }
      }
    })
  },

  /**
  * 名称：showQR
  * 描述：显示二维码
  * 参数：void
  * 返回类型：void
  * 作者：马康耀
  */
  showQr:function(){
    console.log(this.data.TOF);
    var that=this;
    wx.navigateTo({
      url: '/pages/qrcode/qr?email='+that.data.email+'&mid='+that.data.mid,
    });
  },

  //testing functions
  printPeople: function(){
    console.log(this.data.people);
  },

  /**
  * 名称：resetPeople
  * 描述：重置人员信息
  * 参数：void
  * 返回类型：void
  * 作者：马康耀
  */
  resetPeople:function(){
    this.data.people=[];
    this.data.people.push({name:'Ronn',TOF:'参加',email:'Ronn@owl.com'});
    this.data.people.push({name:'George',TOF:'未确定',email:'George@owl.com'});
    console.log(this.data.people);
  },

/*
* 名称：与会者名片查看函数
* 描述：查看某一个与会者的名片，只有一个返回按钮
* 参数：String email（即该与会者的邮箱）
* 返回类型：void
* 作者：胡昱
*/
  peopleInfomation: function(e){
    var emailForInfomation = e.currentTarget.dataset.email;
    wx.request({
      url: 'http://127.0.0.1:443/UserData',
      data: {
        email: emailForInfomation,
      },
      method: "GET",
      header: {
        'content-type': "applicaton/json"
      },
      success: function (res) {
        var duties = res.data.duties;
        var phoneNumber = res.data.phoneNum;
        var email = res.data.email;
        var address = res.data.address;
        if(duties == null || typeof(duties) == undefined){
          duties = '未设置或不愿透露';
        }
        if (phoneNumber == null || typeof (phoneNumber) == undefined) {
          phoneNumber = '未设置或不愿透露';
        }
        if (address == null || typeof (address) == undefined) {
          address = '未设置或不愿透露';
        }
        wx.showModal({
          title: res.data.name + '的名片',
          content: '职务：'+ duties + '\n联系方式：' + phoneNumber + '\n邮箱：' + email + '\n地址：' + address,
          showCancel: false,
        })
      }
      
    });
  }
})