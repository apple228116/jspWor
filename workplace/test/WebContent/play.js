 // 转动轮播图
      
 var tmpId = 0;
      
 $('.carousel').carousel({
   interval: 3000
 })

 // 传过来的权限
 var role = parseInt(sessionStorage.getItem('role'))
 // 判断是游客还是会员 先隐藏游客
 $('.showTitle').eq(1).css('display','none')
 if(role===2){
   $('#role').text('管理员')
   $('#name').text(sessionStorage.getItem('name'))
   $('#Aname').text(sessionStorage.getItem('Aname'))
 }else if(role===0){
     $('.showTitle').eq(0).css('display','none')
     $('.showTitle').eq(1).css('display','block')
     $('#pageJudge>.col').eq(0).css('display','block').siblings().css('display','none')
 }else{
     $('#name').text(sessionStorage.getItem('name'))
     $('#Aname').text(sessionStorage.getItem('Aname'))
     $('#role').text('会员')
 }
 
 // 所有社团 我的社团成员 我的社团公告 我的社团活动 加入社团申请


// 亮出首界面 
$('#page>div').eq(0).css('display','block')
$('#page>div').addClass('animate__animated animate__backInDown')
$('#pageJudge>em').eq(0).addClass('clickClass')
 $('#pageJudge>em').each(function(i,v){
   $(v).click(function(){
     $("#userBody").empty()
     $(this).addClass('clickClass').siblings().removeClass('clickClass')
     $('#page>div').eq(i).css('display','block').siblings().css('display','none')
     $('#page>div').eq(i).addClass('animate__animated animate__jello')
           .siblings().removeClass('animate__animated animate__jello')
     // 当社团成员被点击时，请求数据
     if(i===1 && role){
       $.ajax({
         url: 'shiyan1Servlet',
         dataType: 'json',
         headers:{"Content-Type":"text/plain;charset=UTF-8"},
         data: {
           'play':'6',
           'tableName':'user',
           'Aname': sessionStorage.getItem('Aname')
           },
         success:function(data){
           if(!data.msg){
             for(var i=0;i<data.length;i++){
               var tmpTr = $('<tr></tr>')

               tmpTr.append("<td>"+data[i][0]+"</td>");
               let tmpRole = parseInt(data[i][5].trim())
               for(var j=3;j<data[i].length;j++){
                 if(j===5){
                   data[i][j] = (tmpRole===3)?'管理员':'会员'
                 }
                 if(j===2){
                   tmpTr.append("<td class='x'>"+data[i][j]+"</td>")
                 }else{
                   tmpTr.append("<td>"+data[i][j]+"</td>")
                 }
                 
               }
               if(role===2){
                 tmpTr.append("<td><button type='button' class='btn btn-primary userUpdate' data-toggle='modal' data-target='#exampleModalLong'>修改信息</button><button type='button' class='btn btn-danger userDelete'>删除</button></td></tr>");
                 
               }else{
                 tmpTr.append("<td><button type='button' class='btn btn-success userBtn'>点他一下</button></td>")
                 
               }
               
               $('#userBody').append(tmpTr)
               $('.userUpdate').each(function(){
                   $(this).click(function(){
                   tmpId = $(this).parent().prevAll().eq(3).text()
                   })
                 })
               $('.userDelete').each(function(){
                 $(this).click(function(){
                   tmpId = $(this).parent().prevAll().eq(3).text()
                   $.ajax({
                     url: 'shiyan1Servlet',
                     dataType: 'json',
                     headers:{"Content-Type":"text/plain;charset=UTF-8"},
                     data: {
                       'play':'4',
                       'tableName':'user',
                       'uid': tmpId
                     },
                     success:function(data){
                       if(!parseInt(data.msg)){
                         alert('删除成功! 但是数据库反应慢，请叉掉重连试试')
                         $.parser.parse()
                       }else{
                         alert('删除错误!')
                       }
                     },
                     error:function(){
                       alert('编辑错误')
                     }
                   })
                 })
               })
               
             }
             $('.userBtn').each(function(i,v){
               $(v).click(function(){
                 alert('你拍了一下'+$(this).parent().prevAll().eq(2).text()+'的肩膀说请他吃饭')
               })
             })
           }else{
             alert("获取数据错误啦！")
             return false;
           }
         },
         error:function(){
           alert('请求错误！')
         }
       })

     }
     else if(i===2){
       $('#advertise .card').remove()
       $.ajax({
         url: 'shiyan1Servlet',
         dataType: 'json',
         headers:{"Content-Type":"text/plain;charset=UTF-8"},
         data: {
           'play':'6',
           'tableName':'introduce',
           'Aname': sessionStorage.getItem('Aname')
         },
         success:function(data){
          
           for(var i=0;i<data.length;i++){
             var tmpCard = $('<div class="card"></div>')
             tmpCard.append('<div class="card-header">'+data[i][2]+'</div>')
             tmpCard.append('<div class="card-body"><h5 class="card-title">'+data[i][3]+'</h5></div>')
             $('#advertise').append(tmpCard)
           }
           
         },
         error:function(){
           alert('编辑错误')
         }
       })
     }
     else if(i===3){
       $('#information .card').remove()
       $.ajax({
         url: 'shiyan1Servlet',
         dataType: 'json',
         headers:{"Content-Type":"text/plain;charset=UTF-8"},
         data: {
           'play':'6',
           'tableName':'active',
           'Aname': sessionStorage.getItem('Aname')
           },
         success:function(data){
           if(!data.msg){
             var tmpDom = $('#information')
             for(var i=0;i<data.length;i++){
               var tmp1 = $('<div class="card-body"></div>')
               for(var j=0;j<data[i].length-1;j++){
                 switch(j){
                   case 0: 
                     tmp1.append('<h2 class="card-title"> NO.<span>'+data[i][j]+'</span><button type="button" class="close closeCard" aria-label="Close"><span aria-hidden="true">&times;</span></button></h2>'); 
                     break;
                   case 1: 
                     tmp1.append('<h3 class="card-subtitle mb-2 text-muted"> 活动标题: '+data[i][j]+'</h3>'); 
                     break;
                   case 2:
                     tmp1.append('<h3 class="card-subtitle mb-2 text-muted"> 参加时间: '+data[i][j]+'</h3>'); 
                     break;
                   case 3:
                     tmp1.append('<p class="card-text">  活动名称:'+data[i][j]+'</p>'); 
                     break;
                   case 4:
                     tmp1.append('<h6 class="card-text">  创建时间:'+data[i][j]+'</h6>'); 
                     break;
                 }
               }
               tmp1.append('<button class="btn btn-primary goCenter activeBtn">参加方式?</button>')
               var tmp2 = $('<div class="card  rounded shadow-lg" style="width: 100%; margin-top: 10px">').append(tmp1)
               tmpDom.append(tmp2)
             }
             $('.activeBtn').click(function(){
               alert('在qq群通知里投一票!!！')
             })
             $('.closeCard').each(function(i,v){
               $(v).click(function(){
                $.ajax({
                  url: 'shiyan1Servlet',
                  dataType: 'json',
                  headers:{"Content-Type":"text/plain;charset=UTF-8"},
                  data: {
                    'play':'4',
                    'tableName':'active',
                    'uid': $(this).prev().text()
                  },
                  success:function(data){
                    if(!parseInt(data.msg)){
                      alert('删除活动成功!')
                      $(this).parents('.card').remove()
                    }else{
                      alert('删除错误!')
                    }
                  },
                  error:function(){
                    alert('删除错误')
                  }
                })
               })
             })
             $.parser.parse(tmpDom)
           }else{
             alert("获取数据错误啦！")
             return false;
           }
         },
         error:function(){
           alert('请求错误！')
         }
       })
     }
     else if(i===4){
       $.ajax({
         url: 'shiyan1Servlet',
         dataType: 'json',
         headers:{"Content-Type":"text/plain;charset=UTF-8"},
         data: {
           'play':'6',
           'tableName':'joinassociation',
           'Aname': sessionStorage.getItem('Aname')
           },
         success:function(data){
           if(!data.msg){
             for(var i=0;i<data.length;i++){
               var tmpTr = $('<tr></tr>')
               
               for(var j=0;j<data[i].length-1;j++){
                 tmpTr.append("<td>"+data[i][j]+"</td>")
               }
               if(role===2){
                if(!parseInt(data[i][data[i].length-1])){
                  tmpTr.append("<td><button type='button' class='passJudge btn btn-success'>同意</button><button type='button' class='btn btn-danger'>拒绝</button></td>");
                 }else{
                  tmpTr.append("<td><h5 style='text-align:left;'>已通过</h5></td>")
                 }
                 
               }else{
                 if(!parseInt(data[i][data[i].length-1])){
                  tmpTr.append("<td><button type='button' class='btn btn-success passEnd'>审核中</button></td>")
                 }else{
                  tmpTr.append("<td><h5 style='text-align:left;'>已通过</h5></td>")
                 }
                  
               }
               $("#join tbody").append(tmpTr)
              }
              $('.passJudge').each(function(i,v){
                $(v).click(function(){
                  // 删除请求表中该角色
                  $.ajax({
                    url: 'shiyan1Servlet',
                    dataType: 'json',
                    headers:{"Content-Type":"text/plain;charset=UTF-8"},
                    data: {
                      'play':'4',
                      'tableName':'joinassociation',
                      'uid': $(this).parent().prevAll().eq(2).text()
                    },
                    success:function(data){
                      if(!parseInt(data.msg)){
                        alert('成功通过！')  
                      }else{
                        alert('编辑错误!')
                      }
                    },
                    error:function(){
                      alert('编辑错误')
                    }
                  })
                  
                  $.ajax({
                    url: 'shiyan1Servlet',
                    dataType: 'json',
                    headers:{"Content-Type":"text/plain;charset=UTF-8"},
                    data: {
                      'play':'31',
                      'tableName':'user',
                      'uid': $(this).parent().prevAll().eq(2).text(),
                      'Aname': sessionStorage.getItem('Aname')
                    },
                    success:function(data){
                      if(!parseInt(data.msg)){
                        alert('成功更改用户列表中的信息！')  
                      }else{
                        alert('编辑错误!')
                      }
                    },
                    error:function(){
                      alert('编辑错误')
                    }
                  })

                })
              })
           }else{
             alert("获取数据错误啦！")
             return false;
           }
         },
         error:function(){
           alert('请求错误！')
         }
       })
     }
   })
 })

 //添加hover特效
 $('#pageJudge>.col').each(function(i,v){
   $(v).hover(function(){
     $(this).addClass('animate__animated animate__jello').siblings().removeClass('animate__animated animate__jello')
   })
 })
 $('.card').each(function(i,v){
   $(v).hover(function(){
     $(this).addClass('animate__animated animate__bounceIn').siblings().removeClass('animate__animated animate__bounceIn')
   })
   $(v).click(function(){
     sessionStorage.setItem('imgSrc',$(this).find('.card-img-top').attr('src'))
     sessionStorage.setItem('pageTitle',$(this).find('.card-title').text())
     console.log(sessionStorage.getItem('pageTitle'))
     window.open('aInformation.html')
   })
 })
 $('h1').mouseenter(function(){
   $(this).addClass('animate__animated animate__shakeY')
 });
 $('h1').mouseout(function(){
   $(this).removeClass('animate__animated animate__shakeY')
 }).click(function(){
   var ran = Math.floor(Math.random()*5)
   $('.card').eq(ran).click()
 });
 $('.carousel-item').each(function(i,v){
   $(v).click(function(){
     $('.card').eq(i).click()
   })
 })
 $('.carousel-item').each(function(i,v){
   $(v).hover(function(){
     $(v).addClass('animate__animated animate__rubberBand').siblings().removeClass('animate__animated animate__rubberBand')
   })
 })



//  js代码处理逻辑
$('#sureUpdate').click(function(){
 $.ajax({
   url: 'shiyan1Servlet',
   dataType: 'json',
   headers:{"Content-Type":"text/plain;charset=UTF-8"},
   data: {
     'play':'3',
     'tableName':'user',
     'uid': tmpId,
     'name': $('#mName').val(),
     'role': $('#mRole').val()
   },
   success:function(data){
     if(!parseInt(data.msg)){
       alert('信息修改成功!')
     }else{
       alert('编辑错误!')
     }
   },
   error:function(){
     alert('编辑错误')
   }
 })
})
$('.say').mouseenter(function(){
 $(this).css('opacity',1)
}).mouseleave(function(){
 $(this).css('opacity',.1)
})
$('#advertiseBtn').click(function(){
  $.ajax({
    url: 'shiyan1Servlet',
    dataType: 'json',
    headers:{"Content-Type":"text/plain;charset=UTF-8"},
    data: {
      'play':'22',
      'tableName':'introduce',
      'name': sessionStorage.getItem('name'),
      'Aname': sessionStorage.getItem('Aname'),
      'say': $('#advertiseSay').val()
    },
    success:function(data){
      if(!parseInt(data.msg)){
        alert('信息修改成功!')
        $('#advertise').append('<div class="card"><div class="card-header">'+sessionStorage.getItem('name')+'</div> <div class="card-body"><h5 class="card-title">'+$('#advertiseSay').val()+'</h5></div></div>')
        
      }else{
        alert('编辑错误!')
      }
    },
    error:function(){
      alert('编辑错误')
    }
  })
})
$('#sureDo').click(function(){
  $.ajax({
    url: 'shiyan1Servlet',
    dataType: 'json',
    headers:{"Content-Type":"text/plain;charset=UTF-8"},
    data: {
      'play':'23',
      'tableName':'active',
      'name': sessionStorage.getItem('name'),
      'Aname': sessionStorage.getItem('Aname'),
      'title': $('#mTitle').val(),
      'go_time': $('#mTime').val(),
      'content': $('#mThing').val()
    },
    success:function(data){
      if(!parseInt(data.msg)){
        alert('信息修改成功!')
        $('#advertise').append('<div class="card"><div class="card-header">'+sessionStorage.getItem('name')+'</div> <div class="card-body"><h5 class="card-title">'+$('#advertiseSay').val()+'</h5></div></div>')
        
      }else{
        alert('编辑错误!')
      }
    },
    error:function(){
      alert('编辑错误')
    }
  })
})



// $.ajax({
//   url: 'shiyan1Servlet',
//   dataType: 'json',
//   headers:{"Content-Type":"text/plain;charset=UTF-8"},
//   data: {
//     'play':'4',
//     'tableName':'joinassociation',
//     'uid': $(this).parent().prevAll().eq(2).text()
//   },
//   success:function(data){
//     if(!parseInt(data.msg)){
//       $('#advertise').append('<div class="card"><div class="card-header">'+sessionStorage.getItem('name')+'</div> <div class="card-body"><h5 class="card-title">'+$('#advertiseSay').val()+'</h5></div></div>')
      
//     }else{
//       alert('编辑错误!')
//     }
//   },
//   error:function(){
//     alert('编辑错误')
//   }
// })
