<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@page import="java.util.*" %>
<%@page import="com.oracat.util.FusionCharts" %>
<%@page import="com.oracat.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>FusionCharts | My First Chart</title>

 
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>

 
    <script src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
</head>
<body>
        <div id="chart"></div>
        <%
            // store chart config name-config value pair
            Map<String, String> chartConfig = new HashMap<String, String>();
            chartConfig.put("caption", "品种数量对比");
            chartConfig.put("subCaption", "竞争对手的对比");
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
