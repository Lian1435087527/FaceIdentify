/**download for another**/


function displayforrole(){

    let nrole=localStorage.getItem("role");

    if(nrole==2){
        var content = document.querySelector('#foradmin0').content;
        var content1 = document.querySelector('#foradmin1').content;
        // 更新 template DOM 中的内容。
        document.querySelector('#right').appendChild(
            document.importNode(content1, true));
        document.querySelector('#dirlist').appendChild(
            document.importNode(content, true));


    }};



displayforrole();
$("showall").prop('checked',false);
var nodes = [];

var zTreeObj;
var zTreeObj1;
var zTreeObj3;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    data:{//表示tree的数据格式
        simpleData:{
            enable:true,//表示使用简单数据模式
            idKey:"id",//设置之后id为在简单数据模式中的父子节点关联的桥梁
            pidKey:"pId",//设置之后pid为在简单数据模式中的父子节点关联的桥梁和id互相对应
            rootId:"null"//pid为null的表示根节点
        }
    },
    view:{//表示tree的显示状态
        selectMulti:false//表示禁止多选
    },
    check:{//表示tree的节点在点击时的相关设置
        enable:false,//是否显示radio/checkbox
        chkStyle:"checkbox",//值为checkbox或者radio表示
        checkboxType:{p:"",s:""},//表示父子节点的联动效果
        radioType:"level"//设置tree的分组
    },
    callback:{//表示tree的一些事件处理函数
        onClick:chosenode
    }
};
var setting1 = {
    data:{//表示tree的数据格式
        simpleData:{
            enable:true,//表示使用简单数据模式
            idKey:"id",//设置之后id为在简单数据模式中的父子节点关联的桥梁
            pidKey:"pId",//设置之后pid为在简单数据模式中的父子节点关联的桥梁和id互相对应
            rootId:"null"//pid为null的表示根节点
        }
    },
    view:{//表示tree的显示状态
        selectMulti:false//表示禁止多选
    },
    check:{//表示tree的节点在点击时的相关设置
        enable:false,//是否显示radio/checkbox
        chkStyle:"checkbox",//值为checkbox或者radio表示
        checkboxType:{p:"",s:""},//表示父子节点的联动效果
        radioType:"level"//设置tree的分组
    },
    callback:{//表示tree的一些事件处理函数
        onClick:chosenode1
    }
};
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
var setting3 = {
    data:{//表示tree的数据格式
        simpleData:{
            enable:true,//表示使用简单数据模式
            idKey:"id",//设置之后id为在简单数据模式中的父子节点关联的桥梁
            pidKey:"pId",//设置之后pid为在简单数据模式中的父子节点关联的桥梁和id互相对应
            rootId:"null"//pid为null的表示根节点
        }
    },
    view:{//表示tree的显示状态
        selectMulti:false//表示禁止多选
    },
    check:{//表示tree的节点在点击时的相关设置
        enable:false,//是否显示radio/checkbox
        chkStyle:"checkbox",//值为checkbox或者radio表示
        checkboxType:{p:"",s:""},//表示父子节点的联动效果
        radioType:"level"//设置tree的分组
    },
    callback:{//表示tree的一些事件处理函数
        onClick:chosenode3
    }
};
window.onload=gtree();
function gtree(){
    nodes=[];
    $.ajax({
        type : 'POST',
        url : "/gettree",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        data:{},


        success : function(data) {
            if(data.message==0
            ){
                alert("treeisnull");


            }


            else{
                tre1=data.tree;

                maketree();
                zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);
            }}}

    )};
function maketree(){
    for(let n=0;n<tre1.length;n++){
        var nodeobj={isParent:true};
        nodeobj["id"]=tre1[n].t_id;
        nodeobj["name"]=tre1[n].t_name;
        nodeobj["pId"]=tre1[n].t_pid;
        nodes.push(nodeobj);
    }


};
var prename;
var trid;
function chosenode(event,treeId,treeNode) {
    prename=treeNode.name;
    trid=treeNode.id;
    function getpath(treeNode){

        if(treeNode.getParentNode()!=null){
            prename=treeNode.getParentNode().name+'/'+prename;

            getpath(treeNode.getParentNode());
        }
        else{

            listfile(prename);

        }};
    getpath(treeNode);

};
var prename1;


function chosenode1(event,treeId,treeNode) {
    prename1=treeNode.name;
    function getpath(treeNode){

        if(treeNode.getParentNode()!=null){
            prename1=treeNode.getParentNode().name+'/'+prename1;

            getpath(treeNode.getParentNode());
        }
        else{



        }};
    getpath(treeNode);



};
function chosenode3(event,treeId,treeNode) {
    prename3=treeNode.name;
    function getpath(treeNode){

        if(treeNode.getParentNode()!=null){
            prename3=treeNode.getParentNode().name+'/'+prename3;

            getpath(treeNode.getParentNode());
        }
        else{



        }};
    getpath(treeNode);



};

const table = document.getElementById('table');
const prev = document.getElementById('prev');
const next = document.getElementById('next');
const pages = document.getElementById('pages');
const table0=document.getElementById('table0');
var checkp=0;
const blobService = AzureStorage.Blob.createBlobServiceWithSas(localStorage.getItem("blobUri"), localStorage.getItem("sas"));
//默认设定每页十
let num1 = 10;
//定义一个变量保存每页真实应该展示的数量
let num2;
//默认展示第一页
let page = 1;
var j=0;
var islist=false;
var downloadLink=[];
var blobname=[];
var chosen=[];
var todo=[];
var allcheck=[];

var isfull=false;
var pagesum=0;
files=document.getElementById("file");

function list(prefix) {

    let popLayer = document.getElementById("popLayer2");
    let popbox = document.getElementById("popBox2");
    popLayer.style.display = "block";
    popbox.style.display="block";
    document.getElementById("chosenum").innerText=0;
    checkp=0;

    blobService.listBlobsSegmentedWithPrefix('modelblob1',prefix+'/', null, (error, results,response) => {
        if (error) {
            // Handle list blobs error
        } else {
            results.entries.forEach(blob => {
                if(document.getElementById("showall").checked==false) {
                    if (prefix.split("/").length == blob.name.split("/").length - 1) {


                        blobname[j] = blob.name;
                        downloadLink[j] = blobService.getUrl('modelblob1', blob.name);
                        //console.log(downloadLink[i]);
                        //document.getElementById(j).innerHTML=blob.name;
                        chosen[j] = j;
                        //console.log(i);
                        //files.innerHTML=files.innerHTML+blob.name+downloadLink+"<br>";
                        j++;
                    }
                }
                else{
                    blobname[j] = blob.name;
                    downloadLink[j] = blobService.getUrl('modelblob1', blob.name);
                    //console.log(downloadLink[i]);
                    //document.getElementById(j).innerHTML=blob.name;
                    chosen[j] = j;
                    //console.log(i);
                    //files.innerHTML=files.innerHTML+blob.name+downloadLink+"<br>";
                    j++;
                }
            });
            pagesum=Math.ceil(j / 10);
            document.getElementById("pagesum").innerText="/"+pagesum;
            render();
            chushihua();
            popLayer.style.display = "none";
            popbox.style.display = "none";
        }

    });

};
function chushihua() {
    allcheck=[];
    for(let xj=0;xj< Math.ceil(j / 10);xj++){
        allcheck.push(0);
    }
    //console.log(allcheck);
};


const render = function () {


    table.innerHTML =
        `<thead>
		<th>选择</th>
        <th>名称</th>
        <th>操作</th>

        
    </thead>`;

    //判断当前选择的页码对应的人数
    if (j - num1 * (page - 1) >= 10) {
        num2 = 10;
    } else {
        num2 = j - num1 * (page - 1);
    }

    //渲染该页对应的数据
    let nrole=localStorage.getItem("role");
    if(nrole==2){


        for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {

            let blob=blobname[i];
            let index1 = blob.lastIndexOf("/");
            let sub_blob = blob.substring(index1+1);

            table.innerHTML +=
                `<tr>
        <td ><input  type="checkbox" id='${chosen[i]}' value="" onclick="checksum(this.id)"/></td>
        <td ><a onclick="showImg('${downloadLink[i]}','${sub_blob}')">${sub_blob}</a></td>
        <td > <div class="dropdown">
      <button class="dropbtn">操作</button>
        <div class="dropdown-content">
          <button id="down1" class=button onclick="downloadImg('${downloadLink[i]}','${blobname[i]}')">download</button>
              <button id="move1" class=button  onclick="move('${downloadLink[i]}','${blobname[i]}')">move</button>
              <button id="deleted1" class=button onclick="deleted('${blobname[i]}')">delete</button>
              <button id="rename" class=button onclick="rename('${downloadLink[i]}','${blobname[i]}')">rename</button>

              <button id="copyurl" class="button"   onclick="copyurl('${downloadLink[i]}')">copy url</button>


      </div></td>

    </tr>`;





            /* if (todo.includes(i.toString())) {
                 //设置checked属性
                 console.log(todo.includes(i.toString()).toString()+i);
                 $("#"+i).prop("checked", true);
             } else {
                 //设置checked属性
                 $("#"+i).prop("checked",false);
             }*/





            checkdefine();

        }}
    else if(nrole==1){


        for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {

            let blob=blobname[i];
            let index1 = blob.lastIndexOf("/");
            let sub_blob = blob.substring(index1+1);


            table.innerHTML +=
                `<tr>
        <td ><input  type="checkbox" id='${chosen[i]}' value="" onclick="checksum(this.id)"/></td>
        <td ><a onclick="showImg('${downloadLink[i]}','${sub_blob}')">${sub_blob}</a></td>
        <td > <div class="dropdown">
      <button class="dropbtn">操作</button>
        <div class="dropdown-content">
          <button id="down1" class=button onclick="downloadImg('${downloadLink[i]}','${blobname[i]}')">download</button>
              <button id="move1" class=button  onclick="move('${downloadLink[i]}','${blobname[i]}')">move</button>

              <button id="rename" class=button onclick="rename('${downloadLink[i]}','${blobname[i]}')">rename</button>

              <button id="copyurl" class="button"   onclick="copyurl('${downloadLink[i]}')">copy url</button>


      </div></td>

    </tr>`;
            checkdefine();

        }
    }
    let id="allcheck"+page;

    table0.innerHTML= `<tr> <td><button class="button"  id='${id}' value='${page}' onclick="allcheck1(page)">全选</button></td>    </tr>`;

};
function copyurl(downloadlink){

    new ClipboardJS('.button', {
        text: function() {
            return downloadlink;
        }
    });


    alert("复制成功");
};
function checksum(id){

    if(document.getElementById(id).checked==true){
        checkp++;
        document.getElementById("chosenum").innerText=checkp;
    }
    else{
        checkp--;
        document.getElementById("chosenum").innerText=checkp;
    }
};

function listfile(dir){

    todo=[];
    list(dir);



    document.getElementById("page").value=1;
    page=1;
    //console.log(j);

    j=0;
};
function jumpto() {
    let pagejojo= document.getElementById("page").value;
    if(pagejojo>pagesum){
        alert("超出最大值！");
    }
    else{
        page=pagejojo;
        render();
        document.getElementById("page").value=page;
    }

};
function allcheck1(page){
    if (j - num1 * (page - 1) >= 10) {
        num2 = 10;
    } else {
        num2 = j - num1 * (page - 1);
    }
    let id="allcheck"+page;
  let index=document.getElementById(id).value-1;
//console.log(allcheck[document.getElementById(id).value-1]);
    if(allcheck[index]==0) {
        document.getElementById(id).innerHTML="取消全选";
        allcheck[index]=1;

        for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {

            if ($("#"+i).prop('checked') == false) {
                $("#" + i).prop("checked", true);
                checksum(i);
            }
        }

    }
    else {

        allcheck[index]=0;
        document.getElementById(id).innerHTML="全选";
        for (let i = num1 * (page - 1); i < num2 + num1 * (page - 1); i++) {
            if($("#"+i).prop('checked')){
                $("#"+i).prop("checked", false);
                checksum(i);}
        }
    }};
//绑定向前翻页事件
prev.onclick = function () {
    if (page > 1) {
        gets_all_plus(page);
        page--;

        render();



        document.getElementById("page").value=page;

    } else {
        alert('当前为第一页！');
    }
};

//绑定向后翻页事件
next.onclick = function () {
    if (page < Math.ceil(j / 10)) {
        gets_all_plus(page);
        page++;

        render();



        document.getElementById("page").value=page;

    } else {
        alert('当前为最后一页！');
    }
};



function closeBox() {
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
    popLayer.style.display = "none";
};
function closeBox1() {
    var popBox = document.getElementById("popBox1");
    var popLayer = document.getElementById("popLayer1");
    popBox.style.display = "none";
    popLayer.style.display = "none";
};
function closeBox3() {
    var popBox = document.getElementById("popBox3");
    var popLayer = document.getElementById("popLayer3");
    popBox.style.display = "none";
    popLayer.style.display = "none";
};

function downloadImg(downloadLink,blobname){
    let subti=downloadLink.lastIndexOf(".");
    let d_subti=downloadLink.substring(subti+1);
    let subnai=blobname.lastIndexOf("/");
    let d_subnai=blobname.substring(subnai+1);
    if(d_subti=="jpg"||d_subti=="png"){
        var img = new Image();
        img.src = downloadLink;
        // 必须设置，否则canvas中的内容无法转换为blob
        img.setAttribute('crossOrigin', 'Anonymous');
        img.onload = function() {
            var canvas = document.createElement('canvas');
            canvas.width = img.width;
            canvas.height = img.height;
            var ctx = canvas.getContext('2d');
            // 将img中的内容画到画布上
            ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
            // 将画布内容转换为Blob
            canvas.toBlob((blob) => {
                // blob转为同源url
                var blobUrl = window.URL.createObjectURL(blob);
                // 创建a链接
                var a = document.createElement('a');
                a.href = blobUrl;
                a.download = d_subnai;
                // 触发a链接点击事件，浏览器开始下载文件
                a.click();
                a.remove();
            })
        };

    }
    else{

        let url =downloadLink;
        let a = document.createElement("a");          // 创建一个a节点插入的document
        let event = new MouseEvent("click");           // 模拟鼠标click点击事件
        a.download = d_subnai;                  // 设置a节点的download属性值
        a.href = url;                                 // 将图片的src赋值给a节点的href
        a.dispatchEvent(event);   }                   // 触发鼠标点击事件
};

function download_p() {

    gets_all();
    let zip = new JSZip();//*****创建实例，zip是对象实例
    let file_name = 'pic.zip';
    let k=0;

    for(let i=0;i<todo.length;i++){
        let fname=blobname[todo[i]];
        let lsuffix=fname.lastIndexOf(".");
        if (fname.substring(lsuffix + 1) == "jpg" || fname.substring(lsuffix + 1) == "png") {
        //对每一个图片链接获取base64的数据，并使用回调函数处理
        getBase64Image(downloadLink[todo[i]],blobname[todo[i]],function(dataURL){

            //对获取的图片base64数据进行处理

            let img_arr = dataURL.split(',');


            zip.file(fname,img_arr[1],{base64: true});
            k++;


            if(k==todo.length){//当所有图片都已经生成打包并保存zip
                zip.generateAsync({type:"blob"})
                    .then(function(content) {

                        saveAs(content, file_name);
                    });
                //todo=[]
                //chushihua()
                listfile(prename);
            }
        });}
        else{
            alert("包含非图片数据！");
            break;
        }


    }



};
function getBase64Image(downloadLink,picname,callback) {
    let lsuffix=picname.lastIndexOf(".");
    let realsuffix;



        if (picname.substring(lsuffix + 1) == "jpg") {
            realsuffix = "jpeg";
        } else {
            realsuffix = "png";
        }
        var img = new Image();
        img.src = downloadLink;
        // 必须设置，否则canvas中的内容无法转换为blob
        img.setAttribute('crossOrigin', 'Anonymous');
        img.onload = function () {
            var canvas = document.createElement('canvas');
            canvas.width = img.width;
            canvas.height = img.height;
            var ctx = canvas.getContext('2d');
            // 将img中的内容画到画布上
            ctx.drawImage(img, 0, 0, canvas.width, canvas.height);

            let dataURL = canvas.toDataURL("image/" + realsuffix);//使用canvas获取图片的base64数据

            callback ? callback(dataURL) : null; //调用回调函数

        };


};























function deleted(blobname) {
    let truthBeTold = window.confirm("是否确定删除？");
    if (truthBeTold) {
        let blob = blobname;

        blobService.deleteBlobIfExists('modelblob1', blob, function (error, result) {
            if (error) {
                alert("删除失败");
            } else{


                alert("删除成功");
                listfile(prename);
            }


        });


    }
};
function move(downloadLink,blobname){
    zTreeObj1 = $.fn.zTree.init($("#treeDemo1"), setting1, nodes);
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    var downloadLink1=downloadLink;
    var blobname1=blobname;
    document.getElementById('confirm').addEventListener('click', () => {
        movereal(downloadLink1,blobname1,true);

    });};

function movereal(downloadLink,blobname,isfull){

    if(prename1==null){
        alert("未选择文件夹");
    }
    else{

        let dir=prename1;
        let url =downloadLink;
        let blob=blobname;
        let index1 = blob.lastIndexOf("/");
        let sub_blob = blob.substring(index1+1);


        blobService.createBlockBlobFromText('modelblob1',dir+"/"+sub_blob,"tee",(error, Response) => {
            if (error) {
                // Handle list blobs error
            } else {

                // console.log(Response);
                blobService.startCopyBlob(url, 'modelblob1', dir+"/"+sub_blob, (error,results) => {
                    if (error) {
                        alert("error");
                        console.log(error);
                    }
                    else {
                        //deleted_rea(blob);
                       console.log(results);
                        deleted_rea(blob,isfull);



                    }
                });



            }

        });

    }	};
var xx=0;
var jj=0;
function deleted_rea(blobname,isfull) {


    let blob=blobname;
    blobService.deleteBlobIfExists('modelblob1', blob, function(error, result) {
        if (error) {
            alert ("删除失败");
        } else {
            if(isfull==true)

            {
                console.log("delete is finished"+xx);
                xx++;
                listfile(prename);
                chushihua();
            }
            else{
                console.log("delete is not finished"+jj);
                jj++;
            }


        }

    })


};
function deletedir(){
    $.ajax({
        type : 'POST',
        url : "/deldir",
        dataType:"json",
        //contentType:"application/json;charset=UTF-8",
        data:{"dirn":trid},


        success : function(data) {
            if(data.state==1){
                alert("delete is successful");
                gtree();

            }


            else{
                alert("delete is failed");
            }}


    });


    blobService.listBlobsSegmentedWithPrefix('modelblob1',prename+'/', null, (error, results,response) => {
        if (error) {
            // Handle list blobs error
        } else {
            results.entries.forEach(blob => {
                blobService.deleteBlobIfExists('modelblob1', blob.name, (error, result) => {
                    if (error) {
                        //alert("删除失败");
                    } else{


                        j--;
                        if(j==0){
                            //alert("删除文件夹成功")
                            listfile(prename);
                        }
                    }


                });


            });

        }

    });
};

function checkdefine(){
    if (j - num1 * (page - 1) >= 10) {
        num2 = 10;
    } else {
        num2 = j - num1 * (page - 1);
    }

    for (let k = num1 * (page - 1); k< num2 + num1 * (page - 1); k++) {

        //勾选旧页面已选值
        if (todo.includes(k.toString())) {
            //设置checked属性
            //console.log(todo.includes(k.toString()).toString()+k);
            $("#"+k).prop("checked", true);

        } else {
            //设置checked属性
            $("#"+k).prop("checked",false);





        }
    }


};

function gets_all_plus(page_d){
    let pageing=false;

    let zj=todo.length,zy=todo.length;
    //获取旧页面信息的起止下标
    for(let z=0;z<todo.length;z++){
        if(todo[z]==-page_d){
            zj=z;
            break;
        }
    }
    for(let z=zj+1;z<todo.length;z++) {
        if (todo[z] < 0) {
            zy = z;
            break;
        }
    }



    //删除旧页面已选值
    todo.splice(zj,zy-zj);
    for(let k=0;k<j;k++){

        let xxo=k;
        let xxe = document.getElementById(xxo);


        try{

            if(xxe.checked==true){
                if(pageing==false){//标页
                    todo.push(-page);
                    pageing=true;
                }

                todo.push(xxe.id);//存储所有被选中的项

                //console.log(todo);
            }

            else {

            }
        }



        catch{



        }}



};
function gets_all() {

    gets_all_plus(page);
    for(let f=0;f<todo.length;f++){
        if(todo[f]<0){
            todo.splice(f,1);
        }

    }
};


function copyreal(downloadLink,blobname) {

    let dir=prename3;
    let url =downloadLink;
    let blob=blobname;
    let index1 = blob.lastIndexOf("/");
    let sub_blob = blob.substring(index1+1);


    blobService.createBlockBlobFromText('modelblob1',dir+"/"+sub_blob,"tee",(error, Response) => {
        if (error) {
            // Handle list blobs error
        } else {


            blobService.startCopyBlob(url, 'modelblob1', dir+"/"+sub_blob, (error,results) => {
                    if (error) {

                        alert("error");
                    }
                    else {

                        $("#closemove3").click();



                    }
                }
            );



        }
    });

};
function deleted_p() {
    gets_all();
    //console.log(todo);
    //console.log(j);
    let truthBeTold = window.confirm("是否确定删除？");

    if(truthBeTold){
        for(let m=0;m<todo.length;m++) {
            if(m==todo.length-1)
                deleted_rea(blobname[todo[m]],true);
            else{
                deleted_rea(blobname[todo[m]],false);
            }

        }

        //todo=[];




        alert("删除成功");



    }};
function move_p() {

    zTreeObj1 = $.fn.zTree.init($("#treeDemo1"), setting1, nodes);
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    gets_all();
    //console.log(todo);
    document.getElementById('confirm').addEventListener('click', () => {
        for(let m=0;m<todo.length;m++) {
            //console.log("移动启动");
            if(m==todo.length-1){

                movereal(downloadLink[todo[m]],blobname[todo[m]],true);

            }
            else{

                movereal(downloadLink[todo[m]],blobname[todo[m]],false);
            }
        }
        //todo=[];
    });

    //listfile();
    //console.log(todo);
};
function copy_p() {

    zTreeObj3 = $.fn.zTree.init($("#treeDemo3"), setting3, nodes);
    var popBox = document.getElementById("popBox3");
    var popLayer = document.getElementById("popLayer3");
    popBox.style.display = "block";
    popLayer.style.display = "block";

    gets_all();

    document.getElementById('confirm3').addEventListener('click', () => {

        for(let m=0;m<todo.length;m++) {

            copyreal(downloadLink[todo[m]],blobname[todo[m]]);

        }
        //todo=[];
        //listfile(prename);
    });




};
function rename(downloadLink,blobname){
    var popBox = document.getElementById("popBox1");
    var popLayer = document.getElementById("popLayer1");

    let dir=prename;//获取当前选择项的文本.
    popBox.style.display = "block";
    popLayer.style.display = "block";

    let blobyuan = blobname;


    let suffix=blobyuan.split(".");
    document.getElementById("suffix").innerText="."+suffix[suffix.length-1];
    document.getElementById("confirm1").addEventListener('click', () => {
        let url = downloadLink;
        let blobname1 = document.getElementById("newname").value;
        if(blobname1.match("/")==null){
            blobService.createBlockBlobFromText('modelblob1', dir + "/" + blobname1+"."+suffix[suffix.length-1], "tee", (error, Response) => {
                if (error) {
                    // Handle list blobs error
                } else {


                    blobService.startCopyBlob(url, 'modelblob1', dir + "/" + blobname1+"."+suffix[suffix.length-1], (error, results) => {
                        if (error) {
                            alert("error");
                        } else {
                            //deleted_rea(blob);
                            $("#closere").click();

                            deleted_rea(blobyuan,true);

                        }
                    });


                }
            });}
        else {alert("包含有非法字符'/'");}

    });
};
function showImg(downloadLink,blobname){
    var popBox = document.getElementById("ImgpopBox");
    var popLayer = document.getElementById("ImgpopLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    var Imgnamelabel=document.getElementById("Imgshowname");
    Imgnamelabel.innerText="name:"+blobname;
    var Imgurllabel=document.getElementById("Imgshowurl");
    Imgurllabel.innerText="url:"+downloadLink;
    document.getElementById("Imgshowimage").src=downloadLink;

};
function closeImgBox(){
    var popBox = document.getElementById("ImgpopBox");
    var popLayer = document.getElementById("ImgpopLayer");
    popBox.style.display = "none";
    popLayer.style.display = "none";
    document.getElementById("Imgshowimage").src="";
};