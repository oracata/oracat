<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link rel="stylesheet" href="./js/layui/css/layui.css" />
    <script type="text/javascript" src="./js/layui/layui.js"></script>
    <script>
    layui.use(['layer','table','form'],function () {
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var index  = layer.load(2);

    //用户表格初始化
    var dataTable = table.render({
    elem: '#dataTable' ,
        done:function () {
    layer.close(index) //加载完数据
    }
    });



    })
    </script>



</head>
<body >
<table id="dataTable" class="layui-table">
    <tr><td>dfsafda</td></tr>
</table>
</body>
</html>

