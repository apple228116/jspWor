<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE">
<html>
<head>
<meta charset="UTF-8">
<link href="./bootstrap.min.css" rel="stylesheet">
<title>全民制作人大家好</title>
</head>
<body>
	<script src="./jquery.min.js"></script>
    <script src="./bootstrap.min.js"></script>
    <h1>欢迎来到城院社团管理系统!</h1>
    <div class="d-flex justify-content-center" id="formbox">
      <form class="form-signin align-self-center">
        <h1 class="mb-3 font-weight-normal">Please sign in</h1>
        <label>
          <span class='md-3'>Username</span>
          <input type="text" value='a' id="inputEmail" class="form-control md-9" placeholder="Username" required autofocus>
        </label>
        
        <br>
        <label>
          Password
          <input type="password" value='a' id="inputPassword" class="form-control" placeholder="Password" required>
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
    	$('#login').click(function(){
    		$.ajax({
				url: 'shiyan1Servlet',
				headers:{"Content-Type":"text/plain;charset=UTF-8"},
				data: {'username':'lubenwei','password':'niubi','tableName':'user'},
				success:function(data){
					data = JSON.parse(data)
					alert(data)
					alert(data[0][0])
				},
				error:function(){
					alert('!!!')
				}
			})
    	})
    </script>
</body>
</html>