$(function(){
	
});

function login(){
	var username = $("#username").val();
	var password = $("#password").val();
	if (username == "" || password == ""){
		alert("请输入用户名和密码");
		return;
	}
	$.post(
		contextPath + "/main?method=checkLogin",
		{"username" : username, "password" : password},
		function(res){
			if (res.result != "true"){
				alert("登录失败");
			} else {
				window.location.href = contextPath+"/main";
			}
		}
	);
}