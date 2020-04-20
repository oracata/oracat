<%--
  Created by IntelliJ IDEA.
  User: oracat
  Date: 2019-11-11
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="java.util.*" %>
<%@page import="com.oracat.util.FusionCharts" %>
<%@page import="com.oracat.model.*" %>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="refresh" content="30" />

    <title>FusionCharts | My First Chart</title>

    <!--
        <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>


        <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
    -->


    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>

    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.maps.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/maps/fusioncharts.yunnan.js"></script>



    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/gumshoe/3.5.1/js/gumshoe.min.js"></script>

    <!-- GEO IP -->
    <script src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"></script>
    <script type="text/javascript" src="https://assets.calendly.com/assets/external/widget.js"></script>

    <!-- Mixpanel -->
    <script src="/public/js/mixpanel.js"></script>










</head>
<body>



<!--

<table align="center">
    <tr>

        <td>
            <script>







                const dataSource = {
                    chart: {
                        dateformat: "dd/mm/yyyy",
                        caption: "线上客户节工作进度",
                        subcaption: "Build by gz",
                        plottooltext: "<b>$label</b><br>Start: <b>$start</b><br>End: <b>$end</b>",
                        theme: "fusion"
                    },
                    legend: {
                        item: [
                            {
                                label: "计划进度",
                                color: "#0000AA"
                            },
                            {
                                label: "实际进度",
                                color: "#88D8B0"
                            },
                            {
                                label: "超期进度",
                                color: "#e44a00"
                            }
                        ]
                    },

                    processes: {
                        headertext: "任务",
                        align: "center",
                        process: [
                            {
                                label: "总条目数",
                                id: "1",
                                link:"khjtask/task"
                            },

                            {
                                label: "Define Specifications",
                                id: "2"
                            },
                            {
                                label: "Overall Architecture",
                                id: "3"
                            },
                            {
                                label: "Project Planning",
                                id: "4"
                            },
                            {
                                label: "Detail Design",
                                id: "5"
                            },
                            {
                                label: "Software Development",
                                id: "6"
                            },
                            {
                                label: "Test Plan",
                                id: "7"
                            },
                            {
                                label: "Testing & QA",
                                id: "8"
                            },
                            {
                                label: "User Documentation",
                                id: "9"
                            }
                        ]
                    },



                    datatable: {
                        headervalign: "center",
                        datacolumn: [
                            {
                                headertext: "负责部门",
                               // headervalign: "bottom",
                                headeralign: "center",
                                align: "center",
                                text: [
                                    {
                                        label: "采购部"
                                    },

                                    {
                                        label: "Marketing Team"
                                    },
                                    {
                                        label: "Product Team"
                                    },
                                    {
                                        label: "Dev Team"
                                    },
                                    {
                                        label: "Design Team"
                                    },
                                    {
                                        label: "Dev Team"
                                    },
                                    {
                                        label: "QA Team"
                                    },
                                    {
                                        label: "Product Team"
                                    },
                                    {
                                        label: "Marketing Team"
                                    }
                                ]
                            }
                        ]
                    },

                    tasks: {
                        showlabels: "1",
                        task: [
                            {
                                label: "目标：4762",
                                processid: "1",
                                start: "2/4/2020",
                                end: "5/4/2020",
                                id: "1-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "完成：1374",
                                processid: "1",
                                start: "2/4/2020",
                                end: "2/4/2020",
                                id: "1",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Planned",
                                processid: "2",
                                start: "10/4/2020",
                                end: "20/4/2020",
                                id: "2-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "2",
                                start: "10/4/2020",
                                end: "22/4/2020",
                                id: "2",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Delay",
                                processid: "2",
                                start: "20/4/2020",
                                end: "22/4/2020",
                                id: "2-2",
                                color: "#FF6F69",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%",
                                tooltext: "Delayed by 2 days."
                            },
                            {
                                label: "Planned",
                                processid: "3",
                                start: "21/4/2020",
                                end: "30/4/2020",
                                id: "3-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "3",
                                start: "22/4/2020",
                                end: "1/5/2020",
                                id: "3",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Delay",
                                processid: "3",
                                start: "30/4/2020",
                                end: "1/5/2020",
                                id: "3-2",
                                color: "#FF6F69",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%",
                                tooltext: "Delayed by 1 day"
                            },
                            {
                                label: "Planned",
                                processid: "4",
                                start: "02/5/2020",
                                end: "10/5/2020",
                                id: "4-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "4",
                                start: "4/5/2020",
                                end: "10/5/2020",
                                id: "4",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Planned",
                                processid: "5",
                                start: "5/5/2020",
                                end: "16/5/2020",
                                id: "5-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "5",
                                start: "6/5/2020",
                                end: "16/5/2020",
                                id: "5",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Planned",
                                processid: "6",
                                start: "16/5/2020",
                                end: "27/5/2020",
                                id: "6-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "6",
                                start: "15/5/2020",
                                end: "31/5/2020",
                                id: "6",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Delay",
                                processid: "6",
                                start: "27/5/2020",
                                end: "1/6/2020",
                                id: "6-2",
                                color: "#FF6F69",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%",
                                tooltext: "Delayed by 4 days"
                            },
                            {
                                label: "Planned",
                                processid: "7",
                                start: "1/6/2020",
                                end: "12/5/2020",
                                id: "7-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "7",
                                start: "1/6/2020",
                                end: "12/5/2020",
                                id: "7",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Planned",
                                processid: "8",
                                start: "12/6/2020",
                                end: "20/6/2020",
                                id: "8-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "8",
                                start: "12/6/2020",
                                end: "19/6/2020",
                                id: "8",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Planned",
                                processid: "9",
                                start: "20/6/2020",
                                end: "27/6/2020",
                                id: "9-1",
                                color: "#0000AA",
                                alpha: "100",
                                height: "27%",
                                toppadding: "20%"
                            },
                            {
                                label: "Actual",
                                processid: "9",
                                start: "20/6/2020",
                                end: "30/6/2020",
                                id: "9",
                                color: "#88D8B0",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%"
                            },
                            {
                                label: "Delay",
                                processid: "9",
                                start: "27/6/2020",
                                end: "30/6/2020",
                                id: "9-2",
                                color: "#FF6F69",
                                alpha: "100",
                                height: "27%",
                                toppadding: "65%",
                                tooltext: "Delayed by 3 days"
                            }
                        ]
                    },

                    categories: [
                        {
                            category: [
                                {
                                    start: "25/3/2020",
                                    end: "31/3/2020",
                                    label: "三月"
                                },
                                {
                                    start: "1/4/2020",
                                    end: "30/4/2020",
                                    label: "四月"
                                },
                                {
                                    start: "1/5/2020",
                                    end: "31/5/2020",
                                    label: "五月"
                                },
                                {
                                    start: "1/6/2020",
                                    end: "28/6/2020",
                                    label: "六月"
                                }
                            ]
                        },
                        {
                            category: [
                                {
                                    start: "25/3/2020",
                                    end: "31/3/2020",
                                    label: "第0周"
                                },
                                {
                                    start: "1/4/2020",
                                    end: "5/4/2020",
                                    label: "第1周"
                                },
                                {
                                    start: "6/4/2020",
                                    end: "12/4/2020",
                                    label: "第2周"
                                },
                                {
                                    start: "13/4/2020",
                                    end: "19/4/2020",
                                    label: "第3周"
                                },
                                {
                                    start: "20/4/2020",
                                    end: "26/4/2020",
                                    label: "第4周"
                                },
                                {
                                    start: "27/4/2020",
                                    end: "3/5/2020",
                                    label: "第5周"
                                },
                                {
                                    start: "4/5/2020",
                                    end: "10/5/2020",
                                    label: "第6周"
                                },
                                {
                                    start: "11/5/2020",
                                    end: "17/5/2020",
                                    label: "第7周"
                                },
                                {
                                    start: "18/5/2020",
                                    end: "24/5/2020",
                                    label: "第8周"
                                },
                                {
                                    start: "25/5/2020",
                                    end: "31/5/2020",
                                    label: "第9周"
                                },
                                {
                                    start: "1/6/2020",
                                    end: "7/6/2020",
                                    label: "第10周"
                                },
                                {
                                    start: "8/6/2020",
                                    end: "14/6/2020",
                                    label: "第11周"
                                },
                                {
                                    start: "15/6/2020",
                                    end: "21/6/2020",
                                    label: "第12周"
                                },
                                {
                                    start: "22/6/2020",
                                    end: "28/6/2020",
                                    label: "第13周"
                                }


                            ]
                        }
                    ]
                };

                FusionCharts.ready(function() {
                    var myChart = new FusionCharts({
                        type: "gantt",
                        renderAt: "chart-container",
                        width: "1024",
                        height: "768",
                        dataFormat: "json",
                        dataSource
                    }).render();
                });




            </script>

            <div id="chart-container"></div>
        </td>
    </tr>



</table>


-->


<table align="center">
    <tr>

        <td>
            <script>








                var str='{\n'+
                    'chart:{\n'+
                    'dateformat:"dd/mm/yyyy",\n'+
                    'caption:"线上客户节工作进度",\n'+
                    'subcaption:"Buildbygz",\n'+
                    'plottooltext:"<b>$label</b><br>开始时间:<b>$start</b><br>结束时间:<b>$end</b>",\n'+
                    '                        valuefontsize: "24",\n' +
                    '                        basefontsize: "16",\n' +
                    'theme:"fusion"\n'+
                    '},\n'+
                    'legend:{\n'+
                    'item:[\n'+
                    '{\n'+
                    'label:"计划进度",\n'+
                    'color:"#0000AA"\n'+
                    '},\n'+
                    '{\n'+
                    'label:"实际进度",\n'+
                    'color:"#88D8B0"\n'+
                    '},\n'+
                    '{\n'+
                    'label:"超期进度",\n'+
                    'color:"#e44a00"\n'+
                    '}\n'+
                    ']\n'+
                    '},\n'+
                    '\n'+
                    'processes:{\n'+
                    'headertext:"任务",\n'+
                    'align:"center",\n'+
                    'process:[\n';

                 var process='';

                <c:forEach items="${task}" var="task">
                process=process+'          {\n' +
                    ' label: "${task.task_name}", \n' +
                    '    id: "${task.id}",  \n' +
                    '       link:"khjtask/task"  \n' +
                    '      },  ';

                </c:forEach>
                process=process.substr(0, process.length - 1);
                str=str+process;


                str=str+     '   ]                                                     \n'+
                '               },                                        \n'+
                '                                                         \n'+
                '                                                         \n'+
                '                                                         \n'+
                '               datatable: {                              \n'+
                '                   headervalign: "center",               \n'+
                '                   datacolumn: [                         \n'+
                '                       {                                 \n'+
                '                           headertext: "负责部门",       \n'+
                '                           // headervalign: "bottom",    \n'+
                '                           headeralign: "center",        \n'+
                '                           align: "center",              \n'+
                '                           text: [                      ';

            var owner='';
                <c:forEach items="${task}" var="task">

                owner=owner+'                   {   \n'+
                    '           label: "${task.task_owner}"  \n'+
                    '       },';

                </c:forEach>
                 owner=owner.substr(0, owner.length - 1);
                 str=str+owner;

       str=str+ '              ] \n'+
           '               }  \n'+
           '           ]  \n'+
           '        },\n'+
           '        tasks: {  \n'+
           '            showlabels: "1",  \n'+
           '             task: [  ';

                        var task='';
                    <c:forEach items="${task}" var="task"   varStatus="stat" >
                task=task+'      {   \n'+
                            '      label: "目标：${task.plan_value}",   \n'+
                            '      processid: "${task.id}",    \n'+
                            '      start: "${task.plan_start}",   \n'+
                            '      end: "${task.plan_end}",    \n'+
                            '      id: "${task.id}-1",    \n'+
                            '      color: "#0000AA",    \n'+
                            '      alpha: "100",    \n'+
                            '      height: "27%",    \n'+
                            '      toppadding: "20%"   \n'+
                            ' },';

               var actual_start;
                actual_start='${task.actual_start}';
                if(actual_start!=''){
                    task=task+'      {   \n'+
                        '      label: "完成：${task.complete_value}",   \n'+
                        '      processid: "${task.id}",    \n'+
                        '      start: "${task.plan_start}",   \n'+
                        '      end: "${task.plan_end}",    \n'+
                        '      id: "${task.id}",    \n'+
                        '      color: "#88D8B0",    \n'+
                        '      alpha: "100",    \n'+
                        '      height: "27%",    \n'+
                        '      toppadding: "65%"   \n'+
                        ' },';
                }



                var delay_start;
                //字符串赋值必须加引号，不然会报错。
                  delay_start='${task.delay_start}';
                if(delay_start.length>0){
                    task=task+'      {   \n'+
                        '      label: "",   \n'+
                        '      processid: "${task.id}",    \n'+
                        '      start: "${task.delay_start}",   \n'+
                        '      end: "${task.delay_end}",    \n'+
                        '      id: "${task.id}-2",    \n'+
                        '      color: "#FF6F69",    \n'+
                        '      alpha: "100",    \n'+
                        '      height: "27%",    \n'+
                        '      toppadding: "65%"   \n'+
                        ' },';
                }




                            </c:forEach>
                            task=task.substr(0, task.length - 1);
                        str=str+task;
                        str=str+'] \n'+
                  '  },';

                str=str+'      categories: [ \n'+
                    '         {                                          \n'+
                    '             category: [                            \n'+
                    '                 {                                  \n'+
                    '                     start: "25/3/2020",            \n'+
                    '                     end: "31/3/2020",              \n'+
                    '                     label: "三月"                  \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "1/4/2020",             \n'+
                    '                     end: "30/4/2020",              \n'+
                    '                     label: "四月"                  \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "1/5/2020",             \n'+
                    '                     end: "31/5/2020",              \n'+
                    '                     label: "五月"                  \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "1/6/2020",             \n'+
                    '                     end: "28/6/2020",              \n'+
                    '                     label: "六月"                  \n'+
                    '                 }                                  \n'+
                    '             ]                                      \n'+
                    '         },                                         \n'+
                    '         {                                          \n'+
                    '             category: [                            \n'+
                    '                 {                                  \n'+
                    '                     start: "25/3/2020",            \n'+
                    '                     end: "31/3/2020",              \n'+
                    '                     label: "第0周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "1/4/2020",             \n'+
                    '                     end: "5/4/2020",               \n'+
                    '                     label: "第1周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "6/4/2020",             \n'+
                    '                     end: "12/4/2020",              \n'+
                    '                     label: "第2周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "13/4/2020",            \n'+
                    '                     end: "19/4/2020",              \n'+
                    '                     label: "第3周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "20/4/2020",            \n'+
                    '                     end: "26/4/2020",              \n'+
                    '                     label: "第4周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "27/4/2020",            \n'+
                    '                     end: "3/5/2020",               \n'+
                    '                     label: "第5周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "4/5/2020",             \n'+
                    '                     end: "10/5/2020",              \n'+
                    '                     label: "第6周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "11/5/2020",            \n'+
                    '                     end: "17/5/2020",              \n'+
                    '                     label: "第7周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "18/5/2020",            \n'+
                    '                     end: "24/5/2020",              \n'+
                    '                     label: "第8周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "25/5/2020",            \n'+
                    '                     end: "31/5/2020",              \n'+
                    '                     label: "第9周"                 \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "1/6/2020",             \n'+
                    '                     end: "7/6/2020",               \n'+
                    '                     label: "第10周"                \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "8/6/2020",             \n'+
                    '                     end: "14/6/2020",              \n'+
                    '                     label: "第11周"                \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "15/6/2020",            \n'+
                    '                     end: "21/6/2020",              \n'+
                    '                     label: "第12周"                \n'+
                    '                 },                                 \n'+
                    '                 {                                  \n'+
                    '                     start: "22/6/2020",            \n'+
                    '                     end: "28/6/2020",              \n'+
                    '                     label: "第13周"                \n'+
                    '                 }                                  \n'+
                    '                                                    \n'+
                    '                                                    \n'+
                    '             ]                                      \n'+
                    '         }                                          \n'+
                    '     ]                                              \n'+
                    ' }';


                //  eval解析复杂json字符串 多层字符串普通的JSON.parse是解析不了的,不能一步到位解析到底的。
                const dataSource = eval("("+ str +")");

                FusionCharts.ready(function() {
                    var myChart = new FusionCharts({
                        type: "gantt",
                        renderAt: "chart-container",
                        width: "1324",
                        height: "900",
                        dataFormat: "json",
                        dataSource
                    }).render();
                });




            </script>

            <div id="chart-container"></div>
        </td>
    </tr>



</table>





</body>
</html>
