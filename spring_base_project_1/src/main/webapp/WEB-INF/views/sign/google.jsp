<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html itemscope itemtype="http://schema.org/Article">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="https://apis.google.com/js/client:platform.js?onload=start" async defer></script>

<script type="text/javascript">
	function start(){
		
		gapi.load('auth2', function(){
			window.auth2 = gapi.auth2.init({
				client_id: '788806329174-6aufaqsdku9p51avh129kkusgk9i1v0t.apps.googleusercontent.com'
			});
			
			/* auth2.grantOfflineAccess({
				scope: 'profile email'
			}).then(function(resp){
				var auth_code = resp.code;
				console.log("auth_code : " + auth_code);
			}); */
		});
	}
</script>

<title>Insert title here</title>
</head>
<body>
	<button id="signinButton">Sign in with Google</button>
	<script type="text/javascript">
		$('#signinButton').click(function() {
		    // signInCallback defined in step 6.
		    auth2.grantOfflineAccess().then(signInCallback);
		  });
	</script>
</body>
<script type="text/javascript">
	function signInCallback(authResult){
		console.log(authResult);
		
		if (authResult['code']) {
			console.log("authResult['code'] : " + authResult['code']);
			
			//$('#signinButton').attr('style', 'display: none');
			
			$.ajax({
			      type: 'POST',
			      url: '/sign/google',
			      // Always include an `X-Requested-With` header in every AJAX request,
			      // to protect against CSRF attacks.
			      headers: {
			        'X-Requested-With': 'XMLHttpRequest'
			      }, 
			      contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
			      success: function(result) {
			        // Handle or verify the server response.
			      },
			      processData: true,
			      data: {"authResult":authResult['code']}
			      /* type:'POST',
			      url:'/sign/google',
			      data:{"id":authResult['code']},
			      success: function(result) {
				        // Handle or verify the server response.
				      } */
			    });
		} else {
			console.log("error");
		}
	}
</script>
</html>