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
                
                localStorage.setItem("blobUri","https://cs1f9abf47a9b73x49c3x9c1.blob.core.windows.net");
                localStorage.setItem("sas","?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2020-07-08T17:12:05Z&st=2019-11-06T09:12:05Z&spr=https&sig=fGDtdhwB%2BvA3ayl443p4OIfM0Vxwj%2BNp%2Fb%2BLudKDfN4%3D");
                localStorage.setItem("token",data.token);
                localStorage.setItem("nuid",data.userid);
                localStorage.setItem("role",data.role);
                localStorage.setItem("npwd",data.password);

                window.location.href="index.html";
            }}}

    )}