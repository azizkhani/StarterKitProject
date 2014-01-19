<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<%@ include file="../Head.jsp" %>
  	<script type="text/javascript" src="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxcommon.js"></script>
 	<script type="text/javascript" src="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.js"></script>
 	<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.css" />
 	<script type="text/javascript">
		var userId=<%= request.getParameter("userId")%>;
		var restSecurityUrl="<c:url value = '/rest' />";
		var XMLData="<?xml version='1.0' encoding=\"utf-8\"?><tree id=\"0\">";
		XMLData=XMLData+"<item  text=\"...\" id=\"1\" open=\"1\" im0=\"tombs.gif\"    im1=\"tombs.gif\"    im2=\"iconSafe.gif\">";
	  	$(function () {
  				tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);						 
  				tree.setSkin('dhx_skyblue');
  				tree.setImagePath("../../Scripts/MyJQuery/dhtmlxTree/imgs_rtl//");
  				tree.enableCheckBoxes(1);
  				tree.enableThreeStateCheckboxes(true);
  				$.get(restSecurityUrl+"/location/tree/1", function(data) {	    	
  					  tree.loadXMLString(data);
  				});
		 });
	 	
	    function saveUserLocation(userId){
			var result = new Array();
			var ItemCheck=tree.getAllCheckedBranches(); 
		    var array = ItemCheck.split(',');
			for(var x = 0; x < array.length; x++ ) {
		 		if(array[x].substring(0,1)!=='t' && array[x]!=='1')
			   		result.push({ 'id': array[x]});
			}	
	 		$.ajax({
				  url: restSecurityUrl+"/security/user/saveLocations/"+userId,
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json; charset=utf-8',
				  data:JSON.stringify(result),
				  success: function(res) {showMessage('<spring:message code="UI.General.SaveMessage" />');}
			});  	   
       }
 	</script>
</head>
<body>
 <form id="FormMain" >
 <div id="Loader" style="position: absolute; color: white; top: 0; left: 0; display: none;
        background-color: Red; border-color: White;">
        <div id="loadingbox">
        	<spring:message code="UI.General.Loading" />
        </div>
        <div id="progressBackgroundFilter">
        </div>
    </div>
	 <div id="treeboxbox_tree" style="width:98%; height:90%;background-color:#f5f5f5;border :1px solid Silver;overflow:scroll;" ></div>
	 <input type="button" id="BtnSaveUserGroups" value='<spring:message code="UI.General.Save"  />' 
	        onclick="saveUserLocation(userId)" class="actionBtn" 
	        style="text-align:center;margin-left: 30px;margin-top: 5px;" />	
    
    </form>
</body>
</html>