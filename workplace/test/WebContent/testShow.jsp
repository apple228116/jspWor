<%@ page contentType="text/html;charset=gb2312" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" type="text/css">
    <title>社团管理系统</title>
    <link rel="stylesheet" href="./bootstrap.min.css">
    <script src="./jquery.min.js"></script>
    <script src="./bootstrap.min.js"></script>
    <style>
      img{
        width: 100%;
        height: 100%;
      }
      #carousel-example-generic{
        background: url('./images/city.jpg');
        background-size: 100% 100%;
      }
      .carousel-inner .item{
        height: 100%;
        width: 100%;
      }
      .inItem{
        height: 100%;
        width: auto;
        margin: 0 auto;
      }
    </style>
</head>
<body>
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
        <li data-target="#carousel-example-generic" data-slide-to="5"></li>
      </ol>
    
      <!-- Wrapper for slides -->
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img src="./images/goodshow (55).png" alt="..." class="inItem">
        </div>
        <div class="item">
          <img src="./images/goodshow (62).png" alt="..." class="inItem">
        </div>
        <div class="item">
          <img src="./images/goodshow (61).png" alt="..." class="inItem">
        </div>
        <div class="item">
          <img src="./images/goodshow (16).png" alt="..." class="inItem">
        </div>
        <div class="item">
          <img src="./images/goodshow (48).png" alt="..." class="inItem">
        </div>
        <div class="item">
          <img src="./images/goodshow (49).png" alt="..." class="inItem">
        </div>
      </div>
    
      <!-- Controls -->
      <a class="left carousel-control" href="#" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right aria-hidden="true""></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    
    <script>
      $('.carousel').carousel({
        interval: 2000
      })
    </script>




    <!-- <div class="row">
      <div class="col-xs-6 col-md-3">
        <a href="#" class="thumbnail">
          <img src="./images/goodshow (16).png" alt="...">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <a href="#" class="thumbnail">
          <img src="./images/goodshow (48).png" alt="...">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <a href="#" class="thumbnail">
          <img src="./images/goodshow (49).png" alt="...">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <a href="#" class="thumbnail">
          <img src="./images/goodshow (55).png" alt="...">
        </a>
      </div>
    </div> -->
</body>
</html>  
