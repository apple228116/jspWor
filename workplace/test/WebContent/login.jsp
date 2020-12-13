<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE">
<html>
<head>
<meta charset="UTF-8">
<link href="./bootstrap.min.css" rel="stylesheet">
<title>全民制作人大家好</title>
<style>
	#ti{
		opacity: 0;
		width: 600px;
		height: 200px;
		margin-left: 50%;
		transform: translateX(-50%);
	}
</style>
</head>
<body>
	<script src="./jquery.min.js"></script>
    <script src="./bootstrap.min.js"></script>
    <h1 style='text-align:center;'>欢迎来到城院社团管理系统!</h1>
    
    <div class="d-flex justify-content-center" id="formbox">
      <form id='form' action='play.jsp' class="form-signin align-self-center">
        <h1 class="mb-3 font-weight-normal">Please sign in</h1>
        <label>
          <span class='md-3'>Username</span>
          <input type="text" value='caixukun' id="username" class="form-control md-9" placeholder="Username" required autofocus>
        </label>
        
        <br>
        <label>
          Password
          <input type="password" value='lanqiu' id="password" class="form-control" placeholder="Password" required>
        </label>

        <div class="checkbox mb-3">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button id='login' class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
      </form>
    </div>
    <script>
    $('#form').submit(function(e){
    	e.preventDefault();
    	$.ajax({
			url: 'shiyan1Servlet',
			dataType: 'json',
			headers:{"Content-Type":"text/plain;charset=UTF-8"},
			data: {'username':$('#username').val(),'password':$('#password').val(),'tableName':'user'},
			success:function(data){
				alert(data[0][3])
				sessionStorage.setItem('name',data[0][3])
				alert('登录成功！')
				if(!data.msg){
					$(location).attr('href', "play.jsp");
					
				}else{
					alert("账号或密码错误！")
					return false;
				}
				
			},
			error:function(){
				alert('请求错误！')
			}
		})
    })
    	
    </script>
</body>
</html>