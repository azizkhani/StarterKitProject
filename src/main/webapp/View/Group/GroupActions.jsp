<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
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
		var groupId=<%= request.getParameter("groupId")%>;
		var restSecurityUrl="<c:url value = '/rest/security' />";
		 $(function () {
			Loader(true);
			tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);						 
			tree.setSkin('dhx_skyblue');
			tree.setImagePath("../../Scripts/MyJQuery/dhtmlxTree/imgs_rtl//");
			tree.enableCheckBoxes(1);
			tree.enableThreeStateCheckboxes(true);
			$.get(restSecurityUrl+"/action/getGroupActions/"+groupId, function(data) {	    	
				  tree.loadXMLString(data);
			});
		 });
	 	function saveGroupActions(){		
	  		var result = new Array();
	 		$.each( tree.getAllChecked().split(','), function(i, act) {
	 		   result.push({ 'id': act});
	 		});	
		 	$.ajax({
				  url: restSecurityUrl+"/group/saveGroupActions/"+groupId,
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json; charset=utf-8',
				  data:JSON.stringify(result),
				  success: function(res) {
		                alert('ثبت باموفقیت انجام شد.');
		          }
			});
 		}
 	</script>
</head>
<body>
	 <div id="treeboxbox_tree" style="width:98%; height:90%;background-color:#f5f5f5;border :1px solid Silver;overflow:scroll;" ></div>
	 <input type="button" id="BtnSaveUserGroups" value="ثبت" onclick="saveGroupActions()" class="actionBtn" style="text-align: center;" />	
</body>
</html>