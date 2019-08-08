<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <!-- Step 1 - Include the fusioncharts core library -->
    <script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js"></script>
    <!-- Step 2 - Include the fusion theme -->
    <script type="text/javascript" src="https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js"></script>
<script type="text/javascript">
    FusionCharts.ready(function(){
    
    var list = ${overViewCatagory};//读取request里的liftTypeDicListJson对象
    var     c1_company=$(list).[0].company;
    var     c1_catagory=$(list).[0].catagory;
    var     c2_company=$(list).[1].company;
    var     c2_catagory=$(list).[1].catagory;
 
    
    var fusioncharts = new FusionCharts({
    type: 'column2d',
    renderAt: 'chart-container',
    width: '700',
    height: '400',
    dataFormat: 'json',
    dataSource: {
        // Chart Configuration
        "chart": {
            "caption": "品种",
            "subCaption": "各电商品种",
            "xAxisName": "各电商品种",
            "yAxisName": "各电商品种",
            "numberSuffix": "个",
            "theme": "fusion",
        },
        // Chart Data
        "data": [{
            "label": c1_company,
            "value": c1_catagory
        }, {
            "label": c2_company,
            "value": c2_catagory
        }]
    }
});
    fusioncharts.render();
    });
</script>
</head>
<body>
    <div id="chart-container">FusionCharts XT will load here!</div>
</body>
</html>