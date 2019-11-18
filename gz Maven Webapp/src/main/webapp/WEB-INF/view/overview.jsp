<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="com.oracat.util.FusionCharts" %>
<%@page import="com.oracat.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    
    <title>FusionCharts | My First Chart</title>

 
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>

 
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>




    <script  >


        //在页面未加载完毕之前显示的loading Html自定义内容
        var _LoadingHtml = '<div id="loadingDiv" style="display: none; "><div id="over" style=" position: absolute;top: 0;left: 0; width: 100%;height: 100%; background-color: #f5f5f5;opacity:0.5;z-index: 1000;"></div><div id="layout" style="position: absolute;top: 40%; left: 40%;width: 20%; height: 20%;  z-index: 1001;text-align:center;"><img src="./images/timg.gif" /></div></div>';
        //呈现loading效果
        document.write(_LoadingHtml);

        //移除loading效果
        function completeLoading() {
            document.getElementById("loadingDiv").style.display="none";
        }
        //展示loading效果http://localhost:2006/
        function showLoading()
        {
            document.getElementById("loadingDiv").style.display="block";
        }



        $(document).ready(function(){
                //任选以form结束的form进行处理
                $("form[id$='form']").submit(function(){
                    showLoading();
                    return true;
                });

                //导出excel
                $("a[id$='export']").click(function(){
                    showLoading();
                    return true;
                });

            }

        );

    </script>


</head>
<body>



<!-- 柱图 -->

        <div id="chart"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "品种数量对比");
            chartConfig.put("subCaption", "");
            chartConfig.put("xAxisName", "公司名称");
            chartConfig.put("yAxisName", "品种数量");
            chartConfig.put("formatNumberScale", "0");
            chartConfig.put("numberSuffix", "");
            chartConfig.put("theme", "fusion");
            

            //store label-value pair
            Map<String, Integer> dataValuePair = new HashMap<String, Integer>();
            
        
            		//遍历List
            		Object re = request.getAttribute("overViewCatagory");
            		List<OverViewCatagory> ol= (List)re;
		 for(int i=0;i<ol.size();i++){  
              OverViewCatagory      ov = ol.get(i); 
	 
 
            dataValuePair.put(""+ov.getCompany()+"", ov.getCatagory());
 
         }
            StringBuilder jsonData = new StringBuilder();
            StringBuilder data = new StringBuilder();

            // json data to use as chart data source
            jsonData.append("{'chart':{");
            for(Map.Entry conf:chartConfig.entrySet())
            {
                jsonData.append("'" + conf.getKey()+"':'"+conf.getValue() + "',");
            }

            jsonData.replace(jsonData.length() - 1, jsonData.length() ,"},");

            // build  data object from label-value pair
            data.append("'data':[");

            for(Map.Entry pair:dataValuePair.entrySet())
            {
                data.append("{'label':'" + pair.getKey() + "','value':'" + pair.getValue() +"'},");
            }
            data.replace(data.length() - 1, data.length(),"]");

            jsonData.append(data.toString());
            jsonData.append("}");


            // Create chart instance
            // charttype, chartID, width, height,containerid, data format, data
            FusionCharts firstChart = new FusionCharts(
                "column2d", 
                "first_chart", 
                "700",
                "400", 
                "chart",
                "json", 
                jsonData.toString()
            );
        %>
        <%= firstChart.render() %>
        
        
        
        
    </body>
</html>
