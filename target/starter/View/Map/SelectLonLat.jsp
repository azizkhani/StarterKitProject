<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<style type="text/css">
    .formatText
    {
        color: Green;
        font-size: 11px;
        font-family: Arial;
        font-weight: bold;
    }
</style>

<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
    var map;
    var MarkersList;
    function initialize() {
        var lat=<%= request.getParameter("lat")%>;
        var lng=<%= request.getParameter("lng")%>;
		if(lat==0)
			lat=35.78129586967038;
		
		if(lng==0)
			lng=51.46792984008789;
        var myLatlng = new google.maps.LatLng(lat, lng);
        var myOptions = {
            zoom: 15,
            center: myLatlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        }
        map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        MarkersList = new Array();
        addMarker(map.getCenter());
        google.maps.event.addListener(map, 'click', function(event) {
            addMarker(event.latLng);
        });
        google.maps.event.addListener(map, 'dblclick', function(event) {
            SelectLocation(event.latLng);
        });
    }
    function SelectLocation(Position){
        window.parent.setPosition(Position.lat(), Position.lng());
        //window.close();
    }
    function addMarker(Position) {
        clearOverlays();
        marker = new google.maps.Marker({
            position: Position,
            map: map
        });
        MarkersList.push(marker);
    }
    function clearOverlays() {
        if (MarkersList) {
            for (i in MarkersList) {
                MarkersList[i].setMap(null);
            }
            this.MarkersList = new Array();
        }
    }
    
</script>

</head>
<body onload="initialize();">
    <div id="map_canvas" style="width: 98%; height: 450px">
    </div>
</body>
</html>