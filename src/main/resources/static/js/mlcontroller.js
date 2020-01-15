function mlstart(){
    var popBox = document.getElementById("popBox2");
    var popLayer = document.getElementById("popLayer2");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    $.ajax({
        type : 'POST',
        url : "/mlcon",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"msg":"1"},


        success : function(data) {
            closeBox();
            switch(data.message){
                case "1":
                    alert("开启服务成功！");
                    break;
                case "-1":
                    alert("部署未成功，请稍后重试！");
                    break;
                case "-2":
                    alert("虚拟机连接错误,请稍后重试！");
                    break;
                case "-3":
                    alert("未知错误，请重试一次!");
                case "-4":
                    alert("Azure服务 错误，请重试一次");
                case "2":
                    alert("关闭成功！");
            }


            }})
};
function mlclose(){
    var popBox = document.getElementById("popBox2");
    var popLayer = document.getElementById("popLayer2");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    $.ajax({
        type : 'POST',
        url : "/mlcon",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{"msg":"0"},


        success : function(data) {
            closeBox();

            switch(data.message){
                case "1":
                      alert("开启服务成功！");
                    break;
                case "-1":
                    alert("部署未成功，请稍后重试！");
                    break;
                case "-2":
                    alert("虚拟机连接错误,请稍后重试！");
                    break;
                case "-3":
                    alert("未知错误，请重试一次!");
                case "-4":
                    alert("Azure服务 错误，请重试一次");
                case "2":
                    alert("关闭成功！");
            }


            }})
};
function closeBox() {
    var popBox = document.getElementById("popBox2");
    var popLayer = document.getElementById("popLayer2");
    popBox.style.display = "none";
    popLayer.style.display = "none";
};