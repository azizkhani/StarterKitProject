<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  		<script type="text/javascript" src="../../Scripts/MyJQuery/chart/jquery-1.js"></script>
  		<script type="text/javascript" src="../../Scripts/MyJQuery/chart/highcharts.js"></script>
  		<script type="text/javascript" src="../../Scripts/MyJQuery/chart/superfish.js"></script>
  		<script type="text/javascript" src="../../Scripts/MyJQuery/chart/supersubs.js"></script>
  		<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/chart/superfish.css" />
  		<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/chart/superfish-rtl.css" />
  		<script type="text/javascript">
  		var chart;
        jQuery(document).ready(function() {
            chart = new Highcharts.Chart({
                chart: {
                    renderTo: 'chartcontainer',
                    defaultSeriesType: 'column'
                },
                title: {
                    text: jQuery.browser.mozilla?'نمودار مجموع کارکرد خط بر اساس ساعات روز':'نمودار مجموع کارکرد خط بر اساس ساعات روز'
                },
                subtitle: {
                    text: ''
                },
                xAxis: {categories: ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]},
                yAxis: {
                    min: 0,
                    title: {
                        text: jQuery.browser.mozilla?'جمع کل':'جمع کل'
                    },
                    labels: {
                        align: 'left',
                        x: 3,
                        y: 16,
                        formatter: function() {
                           return Highcharts.numberFormat(this.value, 0);
                        }
                    },
                },
                legend: {
                    layout: 'vertical',
                    backgroundColor: '#FFFFFF',
                    align: 'left',
                    verticalAlign: 'top',
                    x: 100,
                    y: 70,
                    floating: true,
                    shadow: true
                },
                tooltip: {
                    formatter: function() {
                        if (jQuery.browser.mozilla)
                            return ''+
                                'ريال ' + this.y + ' :' + this.x;
                        else
                            return ''+
                                 this.x + ': ' + this.y + ' ريال';
                    }
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                    series: [{"name":0,"data":[8204500,2123000,2000,1303000,623000,190000,66000,49000,28000,62004,863000,5751000,8027000,11181000,11582501,10428500,9794500,7591500,5216500,3798000,3393000,5382000,6708000,8925000]}]
            });


        });

        </script>
</head>
<body>
 	<form id="FormMain" >
 	<div class="body">
            
            <div id="chartcontainer" style="width: 800px; height: 400px; margin: 0 auto"></div>
        </div>
    </form>
</body>
</html>