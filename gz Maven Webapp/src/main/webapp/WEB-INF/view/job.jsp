<%@ page import="com.oracat.model.ErpCustom" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>调度表</title>
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
            <label class="layui-form-label">任务名称：</label>
            <div class="layui-input-inline">
                <input type="text"  name="job_name"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">任务组：</label>
            <div class="layui-input-inline">
                <input type="text"  name="job_group"  autocomplete="off" class="layui-input">
            </div>
        </div>


    </div>



    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-search"  id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置</button>
            <!--
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-download-circle"  id="doExport">导出</button>
            -->
        </div>
    </div>

</form>
<%--搜索条件结束--%>
<%--数据表格开始--%>
<table class="layui-hide" id="customerTable" lay-filter="customerTable">

</table>
<div style="display: none;" id="customerToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>


<div  style="display: none;" id="customerbar" >

    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>



</div>

<div  style="display: none;" id="schedulebar" >
    <a class="layui-btn layui-btn-xs" lay-event="run">立即执行</a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="pause">暂停</a>
    <a class="layui-btn layui-btn-xs" lay-event="resume">恢复</a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="stop">停止</a>


</div>


<div  style="display: none;" id="statubar" >
    <a class="layui-btn layui-btn-xs" lay-event="statue">状态</a>

</div>




<%--数据表格结束--%>


<%--添加和修改的弹出层开始--%>
<div style="display: none;padding: 30px;" id="addOrUpdateDiv" >
    <form class="layui-form" action="" id="dataFrm" lay-filter="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">任务id:</label>
                <div class="layui-input-inline">
                    <input type="text" name="id"  lay-verify="id" autocomplete="off" class="layui-input" placeholder="请输入任务id">
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">任务名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="job_name"  lay-verify="job_name" autocomplete="off" class="layui-input" placeholder="请输入任务名">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">任务组:</label>
                <div class="layui-input-inline">
                    <input type="text" name="job_group"  autocomplete="off" class="layui-input"  lay-verify="job_group"  placeholder="请输任务组">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">任务类名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="job_class_name"  autocomplete="off" class="layui-input"  lay-verify="job_class_name"  placeholder="请输任务类名">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">触发器名称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="trigger_name"  autocomplete="off" class="layui-input"  lay-verify="trigger_name"  placeholder="请输触发器名称">
                </div>
            </div>
        </div>
        <div class="layui-form-item">

            <div class="layui-inline">
                <label class="layui-form-label">触发器组:</label>
                <div class="layui-input-inline">
                    <input type="text" name="trigger_group"  autocomplete="off" class="layui-input"  lay-verify="trigger_group"  placeholder="请输触发器组">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">间隔时间（豪秒）:</label>
                <div class="layui-input-inline">
                    <input type="text" name="repeat_interval"  autocomplete="off" class="layui-input"  lay-verify="repeat_interval"  placeholder="间隔时间（豪秒）">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">已处发次数:</label>
                <div class="layui-input-inline">
                    <input type="text" name="times_triggered"  autocomplete="off" class="layui-input"  lay-verify="required|number"  placeholder="请输已处发次数">
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
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>
</div>
<%--添加和修改的弹出层结束--%>

<script type="text/html" id="status">
    {{#  if(d.status === 1){ }}
    <a href='javascript:void(0);' class='layui-btn  layui-btn-normal'>正常</a>
    {{#  } else { }}
    <a href='javascript:void(0);' class='layui-btn layui-btn-danger '>暂停</a>
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
        //渲染数据表格
        tableIns=table.render({
            elem: '#customerTable'   //渲染的目标对象
            ,url:'queryjob.do' //数据接口
            ,title: '调度表'//数据导出来的标题
            ,toolbar:"#customerToolBar"   //表格的工具条

            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页

            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'任务ID',align:'center',width:'30'}
                ,{field:'job_name', title:'任务名称',align:'center',width:'180'}
                ,{field:'job_group', title:'任务组',align:'center',width:'100'}
                ,{field:'bean_class', title:'任务类名',align:'center',width:'220'}
                ,{field:'method_name', title:'方法名称',align:'center',width:'150'}
                ,{field:'status', title:'状态',align:'center',width:'150',templet: '#status'}

                ,{field:'cron_expression', title:'cron表达式',align:'center',width:'200' }
                ,{field:'params', title:'参数',align:'center',width:'220'}
                ,{field:'create_time', title:'创建时间',align:'center',width:'220'}
                ,{field:'modify_time', title:'修改时间',align:'center',width:'220'}
                ,{field:'remark', title:'备注',align:'center',width:'220'}

                ,{fixed: 'right', title:'编辑', toolbar: '#customerbar', width:180 ,align:'center'}
                ,{fixed: 'right', title:'控制', toolbar: '#schedulebar', width:250,align:'center'}
            ]],
            done:function(data,curr,count){
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
                url:"queryjob.do?"+params ,
                page:{
                    curr:1
                }
            })
        });


        //监听导出
        $("#doExport").click(function(){
            var params=$("#searchFrm").serialize();
            //上传与下载是同步的，所以不用异步请求
            window.location.href="${ctx}/stat/exportCustomer.action?"+params;
        });

        //监听头部工具栏事件
        table.on("toolbar(customerTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddCustomer();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(customerTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.custname+'】这个客户吗', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/customer/deleteCustomer.action",{identity:data.identity},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateCustomer(data);
            }
        });

        var url;
        var mainIndex;
        //打开添加页面
        function openAddCustomer(){
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
            job_name: function(value){

                if(value==""){
                    return '不能为空';
                }
            }
            ,job_group: function(value){

                if(value==""){
                    return '不能为空';
                }
            }
            ,job_class_name: function(value){

                if(value==""){
                    return '不能为空';
                }
            }
            ,trigger_name: function(value){

                if(value==""){
                    return '不能为空';
                }
            }
            ,trigger_group: function(value){

                if(value==""){
                    return '不能为空';
                }
            }
            ,repeat_interval: function(value){

                if(value==""){
                    return '不能为空';
                }
               else if(/[^\d.]/.test(value)){
                    return '必须是数字！';
                }

            }

            ,times_triggered: function(value){

                if(value==""){
                    return '不能为空';
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
        function openUpdateCustomer(data){
            mainIndex=layer.open({
                type:1,
                title:'修改客户',
                content:$("#addOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${ctx}/customer/updateCustomer.action";
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
            var checkStatus = table.checkStatus('customerTable');
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