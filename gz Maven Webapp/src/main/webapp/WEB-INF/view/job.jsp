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
    <a class="layui-btn layui-btn-xs" lay-event="edit">运行</a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">暂停</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">恢复</a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">停止</a>


</div>




<%--数据表格结束--%>


<%--添加和修改的弹出层开始--%>
<div style="display: none;padding: 30px;" id="addOrUpdateDiv" >
    <form class="layui-form" action="" id="dataFrm" lay-filter="dataFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户姓名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="custname"  autocomplete="off" class="layui-input">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">客户地址:</label>
                <div class="layui-input-inline">
                    <input type="text" name="address"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">客户电话:</label>
                <div class="layui-input-inline">
                    <input type="text" name="phone"  autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">

            <div class="layui-inline">
                <label class="layui-form-label">客户职位:</label>
                <div class="layui-input-inline">
                    <input type="text" name="career"  autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="sex" value="1" title="男">
                    <input type="radio" name="sex" value="0" title="女">
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
                ,{field:'job_name', title:'任务名称',align:'center',width:'180'}
                ,{field:'job_group', title:'任务组',align:'center',width:'100'}
                ,{field:'job_class_name', title:'任务类名',align:'center',width:'220'}
                ,{field:'trigger_name', title:'触发器名称',align:'center',width:'150'}
                ,{field:'trigger_group', title:'触发器组',align:'center',width:'160'}
                ,{field:'repeat_interval', title:'间隔时间（豪秒）',align:'center',width:'200' }
                ,{field:'times_triggered', title:'已处发次数',align:'center',width:'220'}
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
                title:'添加客户',
                content:$("#addOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${ctx}/customer/addCustomer.action";
                }
            });
        }
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
            layer.confirm('真的删除选中的这些客户吗', function(index){
                //向服务端发送删除指令
                $.post("${ctx}/customer/deleteBatchCustomer.action",params,function(res){
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