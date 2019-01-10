<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- kakao -->
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    
    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/sign/in" method="post">
                               <div class="form-group">
                                   <input class="form-control" placeholder="E-mail" name="name" autofocus>
                               </div>
                               <div class="form-group">
                                   <input class="form-control" placeholder="Password" name="password" type="password" value="">
                               </div>
                               <div class="checkbox">
                                   <label>
                                       <input name="userCookie" type="checkbox">Remember Me
                                   </label>
                               </div>
                               <!-- Change this to a button or input when using this as a form -->
                               <div class="form-group"><button type="submit" class="btn btn-lg btn-success btn-block">Login</button></div>
                                <a id="kakao-login-btn"></a>
                                <a href="http://developers.kakao.com/logout"></a>
                        </form>
                        <div class="form-group">
	                        <form role="form" action="/sign/social" method="post" id="form">
	                        	<div id="token"></div>
	                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>
    
    <script type='text/javascript'>
      //<![CDATA[
        // 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('1a13f4981bdf4963532f1b66e10cb0e4');
        // 카카오 로그인 버튼을 생성합니다.
        Kakao.Auth.createLoginButton({
          container: '#kakao-login-btn',
          success: function(authObj) {
            console.log(JSON.stringify(authObj));
            
            var token = authObj.access_token;
            var str = "<input type='hidden' name='credential' value='" + token + "'>";
            
            $("#token").append(str);
            $("#form").submit();
          },
          fail: function(err) {
        	  console.log(JSON.stringify(err));
          }
        });
      //]]>
    </script>
    
</body>
</html>
