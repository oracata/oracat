<%@ page import="com.oracat.model.ErpCustom" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>商品分类</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="This is my page" />

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="${ctx}/js/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx}/js/css/public.css" media="all" />
    <link rel="stylesheet" href="${ctx}/js/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${ctx}/js/layui_ext/dtree/font/dtreefont.css">
    <script type="text/javascript" src="${ctx}/js/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/js/layui_ext/dist/dtree.js"></script>

    <style type="text/css">
        .layer-photos-demo{margin:10px 0;}
        .layer-photos-demo img{width: 160px; height: 100px;}
        .select-test{position: absolute;max-height: 500px;height: 350px;overflow: auto;width: 100%;z-index: 123;display: none;border:1px solid silver;top: 42px;}
        .layui-show{display: block!important;}
    </style>
</head>
<body class="childrenBody">
<%--搜索条件开始--%>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form  id="searchFrm" class="layui-form" method="post">
    <div class="layui-form-item" >
        <div class="layui-inline">
            <label class="layui-form-label">商品id：</label>
            <div class="layui-input-inline">
                <input type="text"  name="spid"  autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">商品编码：</label>
            <div class="layui-input-inline">
                <input type="text"  name="spbm"  autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">商品名称：</label>
            <div class="layui-input-inline">
                <input type="text"  name="spmch"  autocomplete="off" class="layui-input">
            </div>
        </div>
<!--
        <div class="layui-input-block">
            <input type="checkbox" name="isnull" lay-skin="primary" title="无分类商品"  >

        </div>
-->

        <div class="layui-inline">
            <label class="layui-form-label">无分类：</label>
            <div class="layui-input-block">
                <input type="checkbox" name="isnull" lay-skin="primary" value="1" >

            </div>

            <label class="layui-form-label">已上架：</label>
            <div class="layui-input-block">
                <input type="checkbox" name="ison" lay-skin="primary" value="1"  checked="true">

            </div>
        </div>


    </div>



    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-search"  id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置</button>

            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-download-circle"  id="doExport">全部导出</button>

        </div>
    </div>

</form>
<%--搜索条件结束--%>
<%--数据表格开始--%>
<table class="layui-hide" id="classifyTable" lay-filter="classifyTable">

</table>



<div style="display: none;" id="classifyToolBar">
    <!--
<button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
-->
<button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="addBatch">批量设置</button>

</div>




<div  style="display: none;" id="classifybar" >

    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
<!--
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    -->



</div>







<%--数据表格结束--%>


<%--添加和修改的弹出层开始--%>
<div style="display: none;padding: 30px;" id="addOrUpdateDiv" >
    <form class="layui-form" action="" id="dataFrm" lay-filter="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">商品ID:</label>
                <div class="layui-input-inline">
                    <input type="text" name="spid"  lay-verify="spid" autocomplete="off" class="layui-input" placeholder="商品ID" disabled="">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">商品编码:</label>
                <div class="layui-input-inline">
                    <input type="text" name="spbm"  lay-verify="spbm" autocomplete="off" class="layui-input" placeholder="商品编码" disabled="">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">商品名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="spmch"  autocomplete="off" class="layui-input"  lay-verify="spmch"  placeholder="商品名称" disabled="">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">分类编码:</label>
                <div class="layui-input-inline">
                    <input id="flbm" type="text" name="flbm"  autocomplete="off" class="layui-input"  lay-verify="flbm"  placeholder="分类编码"  disabled="" >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">分类1:</label>
                <div class="layui-input-inline">


                        <select name="fenlei1" id="fenlei1"  lay-verify="fenlei1"   lay-filter="fenlei1" class="layui-input layui-unselect"  >
                            <option value=""></option>
                        </select>



                </div>
            </div>
        </div>
        <div class="layui-form-item">

            <div class="layui-inline">
                <label class="layui-form-label">分类2:</label>
                <div class="layui-input-inline">
                    <select name="fenlei2" id="fenlei2"  lay-filter="fenlei2"   lay-verify="fenlei2"  >
                        <option value=""></option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">分类3:</label>
                <div class="layui-input-inline">
                    <select name="fenlei3" id="fenlei3"  lay-filter="fenlei3"  lay-verify="fenlei3" >
                        <option value=""></option>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">分类4:</label>
                <div class="layui-input-inline">
                    <select name="fenlei4" id="fenlei4"  lay-filter="fenlei4"  lay-verify="fenlei4"  >
                        <option value=""></option>
                    </select>
                </div>
            </div>
            <!--
            <div class="layui-inline">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男">
                    <input type="radio" name="sex" value="0" title="女">
                </div>
            </div>
            -->
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <!--
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
                -->
            </div>
        </div>
    </form>
</div>
<%--添加和修改的弹出层结束--%>








<script type="text/html" id="status">
    {{#  if(d.status === 1){ }}
    <a href='javascript:void(0);' class='layui-btn  layui-btn-normal'>暂停</a>
    {{#   } else if(d.status === 2){ }}
    <a href='javascript:void(0);' class='layui-btn  layui-btn-normal'>停止</a>
    {{#  } else { }}
    <a href='javascript:void(0);' class='layui-btn layui-btn-danger '>运行</a>
    {{#  } }}
</script>


<!--客户分配菜单的弹出层结束-->
<script type="text/javascript">

    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table'  ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;

        var params=$("#searchFrm").serialize();
        //渲染数据表格
        tableIns=table.render({
            elem: '#classifyTable'   //渲染的目标对象
            ,url:"listclassify.do?"+params //数据接口
            ,title: '商品分类'//数据导出来的标题
            ,toolbar:"#classifyToolBar"  //表格的工具条

            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页

            ,cols: [[   //列表数据
               {type: 'checkbox', fixed: 'left'}
                ,{field:'spid', title:'商品ID',align:'center',width:'100'}
                ,{field:'spbm', title:'商品编码',align:'center',width:'100'}
                ,{field:'spmch', title:'商品名称',align:'center',width:'250'}

                ,{field:'flbm', title:'分类编码',align:'center',width:'100'}
                ,{field:'fenlei1', title:'分类1',align:'center',width:'220'}
                ,{field:'fenlei2', title:'分类2',align:'center',width:'220'}
                ,{field:'fenlei3', title:'分类3',align:'center',width:'100'}
                ,{field:'fenlei4', title:'分类4',align:'center',width:'220'}
                ,{field:'spec', title:'规格',align:'center',width:'220'}
                ,{field:'manufacturer', title:'厂家',align:'center',width:'150'}
                ,{field:'approval_number', title:'批号',align:'center',width:'150' }
                ,{fixed: 'right', title:'编辑', toolbar: '#classifybar', width:80 ,align:'center'}

            ]]
          // ,   where: { type: "all" }
            ,done:function(data,curr,count){
                //不是第一页时如果当前返回的的数据为0那么就返回上一页
                if(data.data.length==0&&curr!=1){
                    tableIns.reload({
                        page:{
                            curr:curr-1
                        }
                    });
                }
            }
        })



        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"listclassify.do?"+params ,
                page:{
                    curr:1
                }
            })
        });


        //监听导出
        $("#doExport").click(function(){
            var params=$("#searchFrm").serialize();
            //上传与下载是同步的，所以不用异步请求
            window.location.href="${ctx}/stat/exportclassify.action?"+params;
        });

        //监听头部工具栏事件
        table.on("toolbar(classifyTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddclassify();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            };
        })




        //监听行任务操作工具事件
        table.on('tool(classifyTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'pause'){ //暂停
                layer.confirm('真的暂停【'+data.job_name+'】这个任务吗？', function(index){
                    //向服务端发送暂停指令
                    $.post("pauseJob.do",{jobId:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            }

            if(layEvent === 'resume'){ //暂停
                layer.confirm('真的恢复【'+data.job_name+'】这个任务吗？', function(index){
                    //向服务端发送恢复指令
                    $.post("resumeJob.do",{jobId:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            }



            if(layEvent === 'run'){ //执行
                layer.confirm('真的执行【'+data.job_name+'】这个任务吗？', function(index){
                    //向服务端发送恢复指令
                    $.post("runJob.do",{jobId:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            }



            if(layEvent === 'stop'){ //停止
                layer.confirm('真的停止【'+data.job_name+'】这个任务吗？', function(index){
                    //向服务端发送恢复指令
                    $.post("stopJob.do",{jobId:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            }

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.job_name+'】这个任务吗？', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/classify/deleteclassify.action",{identity:data.identity},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateclassify(data);
            }

        });


        var url;
        var mainIndex;
        //打开添加页面
        function openAddclassify(){
            mainIndex=layer.open({
                type:1,
                title:'添加任务',
                content:$("#addOrUpdateDiv"),
                area:['800px','450px'],
                maxmin : true,
                //弹出层坐标
                offset: '100px',
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="addJobandTrigger.do";
                }
            });
        }

        //表单验证
        form.verify({
            flbm: function(value){

                if(value==""||value==""){
                    return '分类编码不能为空';
                }
            }
            ,fenlei1: function(value){

                if(value==""){
                    return '分类1不能为空';
                }
            }
            ,fenlei2: function(value){

                if(value==""){
                    return '分类2不能为空';
                }
            }
            ,fenlei3: function(value){

                if(value==""){
                    return '分类3不能为空';
                }
            }
            ,fenlei4: function(value){

                if(value==""){
                    return '分类4不能为空';
                }
            }



            /*
            job_name: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            */


            //     ,phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！']
            //    ,email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']
        });

        //打开修改页面
        function openUpdateclassify(data){
            mainIndex=layer.open({
                type:1,
                title:'修改分类',
                content:$("#addOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){

                    form.val("dataFrm",data);

                    url="updateandsaveclassify.do";



                    //更新select 下拉框
                    var fenlei1=$("#fenlei1");
                    if(data.fenlei1!=null) {
                        var fenlei1Option = $("<option selected='selected' value='" + data.fenlei1 + "'>" + data.fenlei1 + "</option>");
                    }
                    fenlei1.append(fenlei1Option);

                    //分类1
                    var params=$("#dataFrm").serialize();
                    $.ajax({
                        type : "post",
                        url : "findFenlei1.do?"+params ,
                        async: false, //必须改为同步请求，不然接收不到数据
                        dataType : "json",
                        success : function(d) {

                            for ( var i in d.data) {
                                fenlei1.append($('<option value="' + d.data[i]['fenlei1'] +  '">' +  d.data[i]['fenlei1'] +'</option>'));
                            }

                        },
                        error:function(){
                            layer.alert('请求失败，稍后再试', {icon: 5});
                        }

                    });


                    var fenlei2=$("#fenlei2");
                    if(data.fenlei2!=null) {
                        var fenlei2Option = $("<option selected='selected' value='" + data.fenlei2 + "'>" + data.fenlei2 + "</option>");
                    }
                    fenlei2.append(fenlei2Option);


                    //分类2 必须重新置 params  因为此时fenlei2还没有加载
                    var params=$("#dataFrm").serialize();
                    $.ajax({
                        type : "post",
                        url : "findFenlei2.do?"+params ,

                        async: false, //必须改为同步请求，不然接收不到数据
                        dataType : "json",
                        success : function(d) {

                            for ( var i in d.data) {
                                fenlei2.append($('<option value="' + d.data[i]['fenlei2'] +  '">' +  d.data[i]['fenlei2'] +'</option>'));
                            }

                        },
                        error:function(){
                            layer.alert('请求失败，稍后再试', {icon: 5});
                        }

                    });

                    var fenlei3=$("#fenlei3");
                    if(data.fenlei3!=null) {
                        var fenlei3Option = $("<option selected='selected' value='" + data.fenlei3 + "'>" + data.fenlei3 + "</option>");
                    }
                    fenlei3.append(fenlei3Option);




                    //分类3
                    var params=$("#dataFrm").serialize();
                    $.ajax({
                        type : "post",
                        url : "findFenlei3.do?"+params ,

                        async: false, //必须改为同步请求，不然接收不到数据
                        dataType : "json",
                        success : function(d) {

                            for ( var i in d.data) {
                                fenlei3.append($('<option value="' + d.data[i]['fenlei3'] +  '">' +  d.data[i]['fenlei3'] +'</option>'));
                            }

                        },
                        error:function(){
                            layer.alert('请求失败，稍后再试', {icon: 5});
                        }

                    });


                    var fenlei4=$("#fenlei4");
                    if(data.fenlei4!=null) {
                        var fenlei4Option = $("<option selected='selected' value='" + data.fenlei4 + "'>" + data.fenlei4 + "</option>");
                    }
                    fenlei4.append(fenlei4Option);



                    //分类4
                    var params=$("#dataFrm").serialize();
                    $.ajax({
                        type : "post",
                        url : "findFenlei4.do?"+params ,

                        async: false, //必须改为同步请求，不然接收不到数据
                        dataType : "json",
                        success : function(d) {

                            for ( var i in d.data) {
                                fenlei4.append($('<option value="' + d.data[i]['fenlei4'] +  '">' +  d.data[i]['fenlei4'] +'</option>'));
                            }

                        },
                        error:function(){
                            layer.alert('请求失败，稍后再试', {icon: 5});
                        }

                    });



                    //渲染form，让select 下拉框显示数据
                    form.render();




                }

            });


        }





//分类select 选择后的事件处理
        form.on('select(fenlei1)', function(data) {
            var fenlei2=$("#fenlei2");
            fenlei2.empty();
            //分类2 必须重新置 params  因为此时fenlei2还没有加载
            var params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenlei2.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {

                    for ( var i in d.data) {
                        fenlei2.append($('<option value="' + d.data[i]['fenlei2'] +  '">' +  d.data[i]['fenlei2'] +'</option>'));
                    }


                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }
            });


            //分类3
            var fenlei3=$("#fenlei3");
            fenlei3.empty();
             params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenlei3.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {

                    for ( var i in d.data) {
                        fenlei3.append($('<option value="' + d.data[i]['fenlei3'] +  '">' +  d.data[i]['fenlei3'] +'</option>'));
                    }
                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }

            });


            //分类4
            var fenlei4=$("#fenlei4");
            fenlei4.empty();
            params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenlei4.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {

                    for ( var i in d.data) {
                        fenlei4.append($('<option value="' + d.data[i]['fenlei4'] +  '">' +  d.data[i]['fenlei4'] +'</option>'));
                    }
                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }

            });

            flashfenleibm(data);

        });





//分类select 选择后的事件处理
        form.on('select(fenlei2)', function(data) {
            //分类3
            var fenlei3=$("#fenlei3");
            fenlei3.empty();
            params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenlei3.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {


                    for ( var i in d.data) {
                        fenlei3.append($('<option value="' + d.data[i]['fenlei3'] +  '">' +  d.data[i]['fenlei3'] +'</option>'));
                    }
                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }

            });


            //分类4
            var fenlei4=$("#fenlei4");
            fenlei4.empty();
            params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenlei4.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {

                    for ( var i in d.data) {
                        fenlei4.append($('<option value="' + d.data[i]['fenlei4'] +  '">' +  d.data[i]['fenlei4'] +'</option>'));
                    }
                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }
            });
            flashfenleibm(data);
        });





//分类select 选择后的事件处理
        form.on('select(fenlei3)', function(data) {



            //分类4
            var fenlei4=$("#fenlei4");
            fenlei4.empty();
            params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenlei4.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {

                    for ( var i in d.data) {
                        fenlei4.append($('<option value="' + d.data[i]['fenlei4'] +  '">' +  d.data[i]['fenlei4'] +'</option>'));
                    }
                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }
            });


            flashfenleibm(data);
        });




        //分类select 选择后的事件处理
        form.on('select(fenlei4)', function(data) {

            flashfenleibm(data);
        });




        function flashfenleibm(data){
            params=$("#dataFrm").serialize();
            $.ajax({
                type : "post",
                url : "findFenleibm.do?"+params ,

                async: false, //必须改为同步请求，不然接收不到数据
                dataType : "json",
                success : function(d) {

                    var flbm=$("#flbm");
                    flbm.val( d.data[0]['flbm']) ;

                    form.render();
                },
                error:function(){
                    layer.alert('请求失败，稍后再试', {icon: 5});
                }
            });
        }


        //保存
        form.on("submit(doSubmit)",function(obj){

            //序列化表单数据
            var params=$("#dataFrm").serialize();
            $.post(url,params,function(obj){

                layer.msg(obj.msg);

                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('classifyTable');
            var data = checkStatus.data;
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.identity;
                }else{
                    params+="&ids="+item.identity;
                }
            });
            layer.confirm('真的删除选中的这些任务吗', function(index){
                //向服务端发送删除指令
                $.post("deleteBatchJobandTrigger.do",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }
    });

</script>
</body>
</html>