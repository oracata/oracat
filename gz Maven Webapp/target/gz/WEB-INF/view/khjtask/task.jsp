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
    <script type="text/javascript" src="${ctx}/js/layui_ext/dist/layui.js"></script>

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
            <label class="layui-form-label">任务id：</label>
            <div class="layui-input-inline">
                <input type="text"  name="id"  autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">任务名称：</label>
            <div class="layui-input-inline">
                <input type="text"  name="task_name"  autocomplete="off" class="layui-input">
            </div>
        </div>


        <div class="layui-inline">
            <label class="layui-form-label">负责部门：</label>
            <div class="layui-input-inline">
                <input type="text"  name="task_owner"  autocomplete="off" class="layui-input">
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

<button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>

    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>

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
                <label class="layui-form-label">任务ID:</label>
                <div class="layui-input-inline">
                    <input type="text" name="id"  lay-verify="id" autocomplete="off"  class="layui-input" placeholder="任务ID"  >
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">任务名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="task_name"  lay-verify="task_name" autocomplete="off" class="layui-input" placeholder="任务名称"  >
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">负责部门 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="task_owner"  autocomplete="off" class="layui-input"  lay-verify="task_owner"  placeholder="负责部门" >
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">计划开始时间 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="plan_start"  id="plan_start"  autocomplete="off" class="layui-input"  lay-verify="plan_start"  placeholder="计划开始时间" placeholder="yyyy-MM-dd" >
                </div>
            </div>






            <div class="layui-inline">
                <label class="layui-form-label">计划结束时间 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="plan_end" id="plan_end"  autocomplete="off" class="layui-input"  lay-verify="plan_end"  placeholder="计划结束时间" placeholder="yyyy-MM-dd" >
                </div>
            </div>


            <div class="layui-inline">
                <label class="layui-form-label">计划量 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="plan_value"  autocomplete="off" class="layui-input"  lay-verify="plan_value"  placeholder="计划量"  >
                </div>
            </div>


            <div class="layui-inline">
                <label class="layui-form-label">实际开始时间 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="actual_start"  id="actual_start"  autocomplete="off" class="layui-input"  lay-verify="actual_start"  placeholder="实际开始时间" placeholder="yyyy-MM-dd" >
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">实际结束时间 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="actual_end"  id="actual_end"  autocomplete="off" class="layui-input"  lay-verify="actual_end"  placeholder="实际结束时间" placeholder="yyyy-MM-dd" >
                </div>
            </div>


            <div class="layui-inline">
                <label class="layui-form-label">完成量 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="complete_value"   autocomplete="off" class="layui-input"  lay-verify="complete_value"  placeholder="完成量"  >
                </div>
            </div>


            <div class="layui-inline">
                <label class="layui-form-label">超时开始时间 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="delay_start"   id="delay_start"  autocomplete="off" class="layui-input"  lay-verify="delay_start"  placeholder="超时开始时间" placeholder="yyyy-MM-dd" >
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">超时结束时间 :</label>
                <div class="layui-input-inline">
                    <input type="text" name="delay_end"  id="delay_end"  autocomplete="off" class="layui-input"  lay-verify="delay_end"  placeholder="超时结束时间"  placeholder="yyyy-MM-dd" >
                </div>
            </div>






        </div>





        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>

                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>

            </div>
        </div>
    </form>
</div>
<%--添加和修改的弹出层结束--%>










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
            ,url:"listtask.do?"+params //数据接口
            ,title: '任务'//数据导出来的标题
            ,toolbar:"#classifyToolBar"  //表格的工具条

            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页

            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'任务ID',align:'center',width:'100'}
                ,{field:'task_name', title:'任务名称',align:'center',width:'100'}
                ,{field:'task_owner', title:'负责部门',align:'center',width:'100'}
                ,{field:'plan_start', title:'计划开始时间',align:'center',width:'200'}
                ,{field:'plan_end', title:'计划完成时间',align:'center',width:'200'}
                ,{field:'plan_value', title:'计划量',align:'center',width:'100'}
                ,{field:'actual_start', title:'实际开始时间',align:'center',width:'200'}
                ,{field:'actual_end', title:'实际完成时间',align:'center',width:'200'}
                ,{field:'complete_value', title:'完成量',align:'center',width:'100'}
                ,{field:'delay_start', title:'超时开始时间',align:'center',width:'200'}
                ,{field:'delay_end', title:'超时结束时间',align:'center',width:'200'}

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
                url:"listtask.do?"+params ,
                page:{
                    curr:1
                }
            })
        });


        //监听导出
        $("#doExport").click(function(){
            var params=$("#searchFrm").serialize();
            //上传与下载是同步的，所以不用异步请求
            window.location.href="${ctx}/stat/exporttask.action?"+params;
        });

        //监听头部工具栏事件
        table.on("toolbar(classifyTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddtask();
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

         if(layEvent === 'edit'){ //编辑
                openUpdatetask(data);
            }

        });


        var url;
        var mainIndex;
        //打开添加页面
        function openAddtask(){
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
                    url="savetask.do";
                }
            });
        }
        //表单默认值
        /*
        form.val(
           {
                plan_value:0
                ,complete_value:0
            }
        );
  */
        //表单验证
        form.verify({
            id: function(value){

                if(value==""||value==""){
                    return 'id不能为空';
                }
            }
            ,task_name: function(value){

                if(value==""){
                    return '任务名不能为空';
                }
            }
            ,task_owner: function(value){

                if(value==""){
                    return '负责部门不能为空';
                }
            }

            ,plan_value: function(value){

                if(value==""){
                    return '计划值不能为空';
                }
            }

            ,complete_value: function(value){

                if(value==""){
                    return '完成值不能为空';
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
        function openUpdatetask(data){
            mainIndex=layer.open({
                type:1,
                title:'修改分类',
                content:$("#addOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){

                    form.val("dataFrm",data);

                    url="updatetask.do";






                    //渲染form，让select 下拉框显示数据
                    form.render();




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
                    params+="ids="+item.id;
                }else{
                    params+="&ids="+item.id;
                }
            });
            layer.confirm('真的删除选中的这些任务吗', function(index){
                //向服务端发送删除指令
                $.post("deleteBatchTask.do",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }
    });

</script>

<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;



        laydate.render({
            elem: '#plan_start'
        });

        laydate.render({
            elem: '#plan_end'
        });

        laydate.render({
            elem: '#actual_start'
        });

        laydate.render({
            elem: '#actual_end'
        });



        laydate.render({
            elem: '#delay_start'
        });

        laydate.render({
            elem: '#delay_end'
        });





    });
</script>

</body>
</html>