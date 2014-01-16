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
                    text: jQuery.browser.mozilla?'نمودار کارکرد ماهانه راننده اتوبوس':'نمودار کارکرد ماهانه راننده اتوبوس'
                },
                subtitle: {
                    text: ''
                },
                xAxis: {categories: ['فروردين','ارديبهشت','خرداد','تير','مرداد','شهريور','مهر','آبان','آذر','دی','بهمن','اسفند']},
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
                    series: [{"name":"عزیزخانی","data":[7035000,9712000,11381000,2741000,0,0,0,0,0,0,0,0]}]
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