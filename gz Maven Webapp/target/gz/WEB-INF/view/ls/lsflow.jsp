<%--
  Created by IntelliJ IDEA.
  User: oracat
  Date: 2019-11-11
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.oracat.util.FusionCharts" %>
<%@page import="com.oracat.model.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="refresh" content="300" />

    <title>FusionCharts | My First Chart</title>



    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/themes/fusioncharts.theme.fint.js"></script>



    <script type="text/javascript"
            src="<%=basePath%>js/fusioncharts/fusioncharts.powercharts.js"></script>






    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/gumshoe/3.5.1/js/gumshoe.min.js"></script>

    <!-- GEO IP -->
    <script src="//js.maxmind.com/js/apis/geoip2/v2.1/geoip2.js"></script>
    <script type="text/javascript" src="https://assets.calendly.com/assets/external/widget.js"></script>

    <!-- Mixpanel -->
    <script src="/public/js/mixpanel.js"></script>








</head>
<body>


<script>

    <!-- 360浏览器需要 用极速模式才能显示 -->

    <c:forEach items="${realtime}" var="realtime">

    </c:forEach>

    const dataSource = {
        chart: {
            caption: "Process Involved During Course Design",
            yaxismaxvalue: "1100",
            yaxisminvalue: "0",
            theme: "fusion",
            valuefontsize: "12",
            viewmode: "1",
            valuefontcolor: "#FFFFFF",
            plotfillhovercolor: "#1A237E",
            divlinealpha: "0"
        },
        dataset: [
            {
                data: [
                    {
                        id: "01",
                        x: "15",
                        y: "1000",
                        label: "Kick Off{br}meeting",
                        shape: "rectangle",
                        color: "#5D62B5",
                        width: "100",
                        height: "60",
                        hovercolor: "#1A237E"
                    },
                    {
                        id: "02",
                        x: "15",
                        y: "800",
                        label: "Review existing{br}course materials",
                        color: "#29C3BE",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "03",
                        x: "15",
                        y: "600",
                        label:
                            "Collaborate on{br}course topics,{br}outcomes,{br}objectives,etc.",
                        color: "#F2726F",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "04",
                        x: "15",
                        y: "350",
                        label: "Finalize outcomes{br}& objectives.",
                        color: "#FFC533",
                        shape: "polygon",
                        radius: "60"
                    },
                    {
                        id: "05",
                        x: "15",
                        y: "100",
                        label: "Complete Course{br}blueprint.",
                        color: "#62B58F",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "06",
                        x: "45",
                        y: "100",
                        label: "Discuss{br}assessments of{br} course outcomes.",
                        color: "#BC95DF",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "07",
                        x: "45",
                        y: "350",
                        label: "Align assessments{br} to outcomes.",
                        color: "#F2726F",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "08",
                        x: "45",
                        y: "600",
                        label: "Develop{br}assessments for{br}online delivery.",
                        color: "#FFC533",
                        shape: "polygon",
                        radius: "60"
                    },
                    {
                        id: "09",
                        x: "45",
                        y: "800",
                        label: "Update Course{br} blueprint with{br} assessment info.",
                        color: "#C7D631",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "10",
                        x: "45",
                        y: "1000",
                        label: "Determine weekly{br} activities and{br}materials",
                        color: "#FFC533",
                        shape: "polygon",
                        radius: "60"
                    },
                    {
                        id: "11",
                        x: "75",
                        y: "1000",
                        label: "Update Course{br}blueprint with{br}weekly activities",
                        color: "#C7D631",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "12",
                        x: "75",
                        y: "800",
                        label: "Build course{br}carmen",
                        color: "#BC95DF",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "13",
                        x: "75",
                        y: "600",
                        label: "Complete syllabus{br}templete",
                        color: "#C7D631",
                        shape: "rectangle",
                        width: "100",
                        height: "60"
                    },
                    {
                        id: "14",
                        x: "75",
                        y: "350",
                        label: "Review course{br}(Faculty)",
                        color: "#FFC533",
                        shape: "polygon",
                        radius: "60"
                    },
                    {
                        id: "15",
                        x: "75",
                        label: "Course{br}complete",
                        y: "100",
                        shape: "rectangle",
                        color: "#5D62B5",
                        width: "100",
                        height: "60"
                    }
                ]
            }
        ],
        connectors: [
            {
                connector: [
                    {
                        from: "01",
                        to: "02",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "02",
                        to: "03",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "03",
                        to: "04",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "04",
                        to: "05",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "05",
                        to: "06",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "06",
                        to: "07",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "07",
                        to: "08",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "08",
                        to: "09",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "09",
                        to: "10",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "10",
                        to: "11",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "11",
                        to: "12",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "12",
                        to: "13",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "13",
                        to: "14",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    },
                    {
                        from: "14",
                        to: "15",
                        strength: "2",
                        arrowatstart: "0",
                        arrowatend: "1",
                        alpha: "50"
                    }
                ]
            }
        ]
    };

    FusionCharts.ready(function() {
        var myChart = new FusionCharts({
            type: "dragnode",
            renderAt: "chart-container2",
            width: "100%",
            height: "800",
            dataFormat: "json",
            dataSource
        }).render();
    });


</script>

<div id="chart-container2" ></div>






</body>
</html>
