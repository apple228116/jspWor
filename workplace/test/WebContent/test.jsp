<%@ page contentType="text/html; charset=utf8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="./bootstrap.min.css" rel="stylesheet">
  <style>
    #carouselExampleSlidesOnly{
      width: 100%;
      height: 1000px;
    }
  </style>
</head>
<body>
  <script src="./jquery.min.js"></script>
  <script src="./bootstrap.min.js"></script>
  
  <p id='show'></p>
  <button id='ji'>按下！</button>
  
  
  <script>
  	$('#ji').click(function(){
  		$.ajax({
  			url: 'shiyan1Servlet',
  			method: 'GET',
  			success: function(data){
  				alert(data)
  				data = data || '没有东西'
	  			var x = ''
	  			for(var a in data){
	  				x += a + '---' + data[a] + '<br/>'
	  			}
  				$('#show').html(x) 
  			},
  			error:function(e){
  				alert('请求错误！')
  			}
  		})
  	})
  </script>
</body>
</html>