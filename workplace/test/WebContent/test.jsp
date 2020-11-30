<%@ page contentType="text/html; charset=utf8"%>
<!doctype html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>啊啊啊啊</title>
    <!-- Bootstrap core CSS -->
    <link href="./bootstrap.min.css" rel="stylesheet">
    <style>
      html,body{
        height: 100%;
        width: 100%;
        background-color: aliceblue;
      }
      #formbox{
        height: 100%;
      }
    </style>
  </head>
  <body class="text-center">
    <script src="./jquery.min.js"></script>
    <script src="./bootstrap.min.js"></script>
    <h1>全民制作人大家好！</h1>
    <div class="d-flex justify-content-center" id="formbox">
      <form class="form-signin align-self-center">
        <h1 class="mb-3 font-weight-normal">Please sign in</h1>
        <label>
          <span class='md-3'>Username</span>
          <input type="text" id="username" class="form-control md-9" value='lubenwei' placeholder="Username" required autofocus>
        </label>
        
        <br>
        <label>
          Password
          <input type="password" id="password" class="form-control" value='niubi' placeholder="Password" required>
        </label>

        <div class="checkbox mb-3">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id='signIn'>Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
      </form>
    </div>
    <p id="showP"></p>
    <script>
	$('#signIn').click(function(){
		$.ajax({
			url: 'shiyan1Servlet',
			data: {'username':'lubenwei','password':'niubi','tableName':'user'},
			success:function(data){
				data = JSON.parse(data)
				if(data[0][0]){
					alert(data);
				}else{
					alert('登录失败,输入的账号或密码有误!')
				}
				
			},
			error: function(data){
				alert('传送失败!')
				$('#show').html(data)
			}
		})
	})
    
    </script>
</body>
</html>