document.write('<script language=javascript src="/js/jquery.ztree.core.js"></script>');
var tre1;
var nodes=[];
var notodata=[];
var filedir=document.getElementById("upload2");
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
                //console.log(data.tree);
                maketree();
                zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);
            }}}

    )};
function ptree() {
    $.ajax({
        type : 'POST',
        url : "/posttree",
        dataType:"json",
        //contentType:"application/json;charset=UTF-8",
        data:{'tree':JSON.stringify(notodata)},


        success : function(data) {
            if(data.state==1){
                zTreeObj = $.fn.zTree.destroy("treeDemo");
                gtree();

            }
        }
    })
};
function maketree(){
    for(let n=0;n<tre1.length;n++){
        var nodeobj={isParent:true};
        nodeobj["id"]=tre1[n].t_id;
        nodeobj["name"]=tre1[n].t_name;
        nodeobj["pId"]=tre1[n].t_pid;
        nodes.push(nodeobj);
    }
//console.log(nodes);
    nlength=nodes.length+1;
};


//var nodes = [{name:"Uphoton深度图数据",id:0,pId:null},{name:"DP",id:1,pId:0},{name:"IR",id:2,pId:0},{name:"Fake",id:3,pId:1,isParent:true},{name:"People",id:4,pId:1,isParent:true},{name:"Fake",id:5,pId:2,isParent:true},{name:"People",id:6,pId:2,isParent:true}]

/* var map = {};
 var val = [];
function tree(data) {

    //生成数据对象集合
    data.forEach(it => {
        map[it.id] = it;
    })
    console.log(map);


    //生成结果集
    data.forEach(it => {
        const parent = map[it.pid];
        if (parent) {
            if (!Array.isArray(parent.children)) parent.children = [];
            parent.children.push(it);
        } else {
            val.push(it);
        }
    })
    console.log(val)
    return val;

}
tree(data);*/

var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    data:{//表示tree的数据格式
        simpleData:{
            enable:true,//表示使用简单数据模式
            idKey:"id",//设置之后id为在简单数据模式中的父子节点关联的桥梁
            pidKey:"pId",//设置之后pid为在简单数据模式中的父子节点关联的桥梁和id互相对应
            rootId:"null"//pid为null的表示根节点
        },
    },
    view:{//表示tree的显示状态
        selectMulti:false//表示禁止多选
    },
    check:{//表示tree的节点在点击时的相关设置
        enable:true,//是否显示radio/checkbox
        chkStyle:"checkbox",//值为checkbox或者radio表示
        checkboxType:{p:"",s:""},//表示父子节点的联动效果
        radioType:"level"//设置tree的分组
    },
    callback:{//表示tree的一些事件处理函数
        onClick:chosenode
    },
};
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）

function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
};


var dir;
var dir1;
var chnode;
var npid;
var ndir;
function chosenode(event,treeId,treeNode) {
    var prename=treeNode.name;
    ndir=treeNode.name;
    npid=treeNode.id;
    function getpath(treeNode){

        if(treeNode.getParentNode()!=null){
            prename=treeNode.getParentNode().name+'/'+prename;

            getpath(treeNode.getParentNode());
        }
        else{

            dir=prename;
            dir1=prename;
        }}
    getpath(treeNode);

    chnode=treeNode.children;

};



var stacode;

const account = {
    name: "cs1f9abf47a9b73x49c3x9c1",
    sas: "?sv=2019-02-02&ss=bfqt&srt=sco&sp=rwdlacup&se=2020-07-08T17:12:05Z&st=2019-11-06T09:12:05Z&spr=https&sig=fGDtdhwB%2BvA3ayl443p4OIfM0Vxwj%2BNp%2Fb%2BLudKDfN4%3D"
};

const blobUri = 'https://' + account.name + '.blob.core.windows.net';
const blobService = AzureStorage.Blob.createBlobServiceWithSas(blobUri, account.sas);
/*document.getElementById('create-button').addEventListener('click', () => {

blobService.createContainerIfNotExists('mycontainer',  (error, container) => {
    if (error) {
        // Handle create container error
    } else {
        console.log(container.name);
    }
});


});*/
var fgh=0;
var nlength;
async function getdir1(prefix,kpid){


//console.log(prefix);
    fgh++;

//console.log("递归了"+fgh);
    blobService.listBlobDirectoriesSegmentedWithPrefix("modelblob1", prefix+"/",null, (error, result) => {
        if(error) {
            // Handle blob error
        } else {
            let dirpren0=result.entries;
            if (dirpren0.length>0){

                for(let n=0;n<dirpren0.length;n++){
                    let array1=dirpren0[n]["name"].split("/");
                    let kkpid=guid();
                    let nodeobj={t_id:kkpid};


                    nodeobj["t_name"]=array1[array1.length-2];
                    nodeobj["t_pid"]=kpid;
                    //console.log("for"+n);
                    //console.log(nodeobj);

                    notodata.push(nodeobj);


                    //console.log(prefix+"/"+array1[array1.length-2]);
                    getdir1(prefix+"/"+array1[array1.length-2],kkpid);

                }
            }
            else{

                /*if(fgh==dirpren0.length){
                    console.log(notodata);*/

            }
            fgh--;
            if( fgh==0)ptree();
        }
    });
    //console.log("最终");
    //console.log(notodata);
};


document.getElementById('uploadfile-button').addEventListener('click', () =>{
    document.getElementById("upload1").click();
    document.getElementById("filelist").innerHTML="";
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    stacode=1;});
document.getElementById('uploaddir-button').addEventListener('click', () =>{
    document.getElementById("filelist").innerHTML="";
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";
    document.getElementById("upload2").click();

    stacode=2;});


function startup(){
    if (npid==null){
        alert("未选中文件夹!");
    }
    else{
        if (stacode==1){



            var len=document.getElementById('upload1').files.length;
            var successnum=0;


            for(i=0;i<len;i++){

                let file = document.getElementById('upload1').files[i];

                blobService.createBlockBlobFromBrowserFile('modelblob1',
                    dir+"/"+file.name,
                    file,
                    (error, result) => {
                        if(error) {
                            // Handle blob error
                        } else {


                            if(successnum==len-1){
                                append(file.name);
                                append("upload is finished!");
                                // $("#closemove").click();
                            }
                            else {successnum++;
                                append(file.name);}

                        }
                    });}}
        else if(stacode==2){


            var len=filedir.files.length;
            var successnum=0;
            var isrrr;
            let finow=document.getElementById("upload2").files[0].webkitRelativePath.split("/");
            if(chnode){
                for (let n=0;n<chnode.length;n++){
                    if(finow[0]==chnode[n]["name"]){
                        alert("文件夹已存在");
                        isrrr=true;
                        break;
                    }else{

                        isrrr=false;
                    }}}
            else{isrrr=false;}
            if(isrrr==false)  {
                let kkpid=guid();
                let nodeobj={t_id:kkpid};


                nodeobj["t_name"]=finow[0];
                nodeobj["t_pid"]=npid;
                notodata.push(nodeobj);
                for(i=0;i<len;i++){

                    let file = document.getElementById('upload2').files[i];


                    let relativepath= file.webkitRelativePath;

                    blobService.createBlockBlobFromBrowserFile('modelblob1',
                        dir+"/"+relativepath,
                        file,
                        (error, result) => {
                            if(error) {
                                // Handle blob error
                            } else {


                                if(successnum==len-1){
                                    append(file.name);
                                    append("upload is finished!");
                                    getdir1(dir+"/"+finow[0],kkpid);




                                    // $("#closemove").click();
                                }
                                else {successnum++;
                                    append(file.name);}

                            }
                        });


                }}}}};
function append(file1){
    document.getElementById("filelist").innerHTML+=file1+"上传成功！"+"</br>";};


function closeBox() {
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
    popLayer.style.display = "none";
};
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