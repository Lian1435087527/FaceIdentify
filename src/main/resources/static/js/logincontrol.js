/**
 * 
 */
function logout(){
	if(localStorage.getItem("nuid")==null){
		alert("未登录");}
	else{
		var flag = confirm("确定要退出吗？");
		if(flag==true){
			localStorage.setItem("nuid",null);
			window.location.href="login.html";
		}
		}
}