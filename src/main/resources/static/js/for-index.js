var obj=document.getElementById('nuser');
var nusername=localStorage.getItem("nuid");
obj.innerHTML=nusername;
function displayforrole(){
    let nrole=localStorage.getItem("role");
    switch(nrole){
        case "0":

            var content = document.querySelector('#fortour').content;
            // 更新 template DOM 中的内容。

            document.querySelector('#tpnv').appendChild(
                document.importNode(content, true));
            break;
        case "1":

            var content = document.querySelector('#fordev').content;
            // 更新 template DOM 中的内容。
            var content1=document.querySelector('#notfortour').content;
            document.querySelector('#tpnv').appendChild(
                document.importNode(content1, true));
            document.querySelector('#tpnv').appendChild(
                document.importNode(content, true));

            break;
        case "2":

            var content = document.querySelector('#fordev').content;
            var content1 = document.querySelector('#notfortour').content;
            // 更新 template DOM 中的内容。
            document.querySelector('#tpnv').appendChild(
                document.importNode(content1, true));
            document.querySelector('#tpnv').appendChild(
                document.importNode(content, true));

            break;
    }};



displayforrole();
//ScrollUp
$(function () {
    $.scrollUp({
        scrollName: 'scrollUp', // Element ID
        topDistance: '300', // Distance from top before showing element (px)
        topSpeed: 300, // Speed back to top (ms)
        animation: 'fade', // Fade, slide, none
        animationInSpeed: 400, // Animation in speed (ms)
        animationOutSpeed: 400, // Animation out speed (ms)
        scrollText: 'Scroll to top', // Text for element
        activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
    });
});




function load_home(ele) {
    var x=document.getElementsByClassName('selected');

    var i;
    for (i = 0; i <x.length; i++) {
        x[i].className= null;
    }
    switch(ele.id){
        case "ce1":

            document.getElementById("htbody").innerHTML = '<object type="text/html" data="upload.html" width="100%" height=700px></object>';
            $("#ce1").attr("class", "selected");
            break;
        case "ce2":
            document.getElementById("htbody").innerHTML = '<object type="text/html" data="downfortour.html" width="100%" height=700px></object>';
            $("#ce2").attr("class", "selected");
            break;
        case "ce3":
            document.getElementById("htbody").innerHTML = '<object type="text/html" data="download.html" width="100%" height=700px></object>';
            $("#ce3").attr("class", "selected");
            break;
        case "ce4":
            document.getElementById("htbody").innerHTML = '<object type="text/html" data="faceapi.html" width="100%" height=700px></object>';
            $("#ce4").attr("class", "selected");
            break;
        case "ce5":
            document.getElementById("htbody").innerHTML = '<object type="text/html" data="tables.html" width="100%" height=700px></object>';
            $("#ce5").attr("class", "selected");
            break;
        case "ce6":
            document.getElementById("htbody").innerHTML = '<object type="text/html" data="rtmp.html" width="100%" height=700px></object>';
            $("#ce6").attr("class", "selected");
            break;





    }
};
function editP() {
    window.location.herf="editP.html";
};

var downloadlink=null;
const account = {
    name: "cs1f9abf47a9b73x49c3x9c1",
    sas: "?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2020-07-08T17:12:05Z&st=2019-11-06T09:12:05Z&spr=https&sig=fGDtdhwB%2BvA3ayl443p4OIfM0Vxwj%2BNp%2Fb%2BLudKDfN4%3D"
};
var dir=null;
//var filename=null;
const blobUri = 'https://' + account.name + '.blob.core.windows.net';
const blobService = AzureStorage.Blob.createBlobServiceWithSas(blobUri, account.sas);

var status=0;
function uploading(){
    document.getElementById("fileinput").click();
}
document.getElementById("fileinput").addEventListener("change",function () {
    upload();
});

function upload()
{
    let popLayer = document.getElementById("popLayer2");
    let popbox = document.getElementById("popBox2");
    popLayer.style.display = "block";
    popbox.style.display="block";
    //alert('正在上传，请勿退出');
    let filename=null;


    var len=document.getElementById('fileinput').files.length;
    for(i=0;i<len;i++){
        var file = document.getElementById('fileinput').files[i];


        blobService.createBlockBlobFromBrowserFile('modelblob1',
            "picformodel"+'/'+file.name,
            file,
            (error, result) => {
                if(error) {
                    // Handle blob error
                }
                else {

                    //alert('Upload is successful');

                    filename=file.name;


                    console.log(filename);

                    blobService.listBlobsSegmentedWithPrefix('modelblob1',"picformodel"+'/'+filename, null, (error, results) => {
                        if (error) {
                            // Handle list blobs error
                        } else {
                            //alert('正在运算，请勿退出');
                            results.entries.forEach(blob => {

                                    downloadlink= blobService.getUrl('modelblob1',blob.name );

                                    document.getElementById("yuantu").src=downloadlink;
                                    post_a(downloadlink,blob.name);
                                }
                            );}});
                }
            });
    }  };

function upload1() {
    let popLayer = document.getElementById("popLayer2");
    let popbox = document.getElementById("popBox2");
    popLayer.style.display = "block";
    popbox.style.display="block";
    let downloadlink=document.getElementById("picurl").value;
    if (downloadlink==null){
        alert("url 错误");
    }
    else{
        document.getElementById("yuantu").src=downloadlink;
        post_a(downloadlink,"///");//url不用删除，传一个不容易重复的无用参数
    }

};
function post_a(downloadlink,blobname){
    var options2=$("#select-name option:selected");//获取当前选择项.
    dir=options2.text();//获取当前选择项的文本.

    $.ajax({
            type : 'POST',
            url : "/score",

            dataType: 'json',

            data:{'url':downloadlink,'modelname':dir},

            success : function(data) {
                document.getElementById("fankui").src="https://cs1f9abf47a9b73x49c3x9c1.blob.core.windows.net/apioutput/"+data.id+"/output.jpg";
                //alert(data.face_liveness);

                $("#closemove").click();
                if(blobname=="///"){}
                else {
                    dele(blobname);
                }

            }


        }

    )};
function dele(blob){


    blobService.deleteBlobIfExists('modelblob1', blob, function (error, result) {
        if (error) {
            alert("数据异常");
        } else {


        }});};
function closeBox() {
    var popBox = document.getElementById("popBox2");
    var popLayer = document.getElementById("popLayer2");
    popBox.style.display = "none";
    popLayer.style.display = "none";
};
function AutoResizeImage(maxWidth,maxHeight,objImg){
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth ==0 && maxHeight==0){
        Ratio = 1;
    }else if (maxWidth==0){//
        if (hRatio<1) Ratio = hRatio;
    }else if (maxHeight==0){
        if (wRatio<1) Ratio = wRatio;
    }else if (wRatio<1 || hRatio<1){
        Ratio = (wRatio<=hRatio?wRatio:hRatio);
    }
    if (Ratio<1){
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;
};