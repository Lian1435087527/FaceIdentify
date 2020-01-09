document.onkeydown=function(ev){

    var ev=ev||window.event;

    if(ev.which==13){
        login();
    }
}
function register(){
    var userName=document.getElementById('userName').value;
    var userPw=document.getElementById('userPw').value;
    let token=localStorage.getItem("token");
    let nuname=userName;
    $.ajax({
        type : 'POST',
        url : "/register",
        dataType:"json",
        contentType:"application/json;",
        data:JSON.stringify({user_id:userName,password:userPw,role:0}),


        success : function(data) {
            if(data.state==0){alert("用户名已存在")



            }
            else{
                alert("注册成功");

            }}}

    )}
function login(){
    var userName=document.getElementById('userName').value;
    var userPw=document.getElementById('userPw').value;
    $.ajax({
        type : 'POST',
        url : "/login",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:JSON.stringify({user_id:userName,password:userPw,role:0}),


        success : function(data) {
            if(data.message==0
            ){
                alert("用户名不存在");


            }
            else if (data.message==1){
                alert("密码错误");
            }
            else{

                alert("登录成功");

                localStorage.setItem("token",data.token);
                localStorage.setItem("nuid",data.userid);
                localStorage.setItem("role",data.role)
                window.location.href="index.html";
            }}}

    )}