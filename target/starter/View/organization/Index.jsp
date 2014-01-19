<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<%@ include file="../Head.jsp" %>
  	<script type="text/javascript" src="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxcommon.js"></script>
 	<script type="text/javascript" src="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.js"></script>
 	<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.css" />
 	
 	<script type="text/javascript">
		var rootId='000000000000000000000'; 
		var restSecurityUrl="<c:url value = '/rest/organization' />";
		var XMLData="<?xml version='1.0' encoding=\"utf-8\"?><tree id=\"0\">";
		XMLData=XMLData+"<item  text=\"...\" id=\"1\" open=\"1\" im0=\"tombs.gif\"    im1=\"tombs.gif\"    im2=\"iconSafe.gif\">";
	  	 $(function () {
  				$("#Divwait").css('visibility','visible');
 				tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
		 		tree.setSkin('dhx_skyblue');
 				tree.setImagePath("../../Scripts/MyJQuery/dhtmlxTree/imgs_rtl//");
 				tree.enableCheckBoxes(1);
				tree.enableThreeStateCheckboxes(true);
	 			$.get(restSecurityUrl+"/all/"+rootId, function(data) {	
	 					XMLData=XMLData+data	+"</item>   </tree>"
	 			    	tree.loadXMLString(XMLData);
						$("#Divwait").css('visibility','hidden');
	 			});
	 			tree.attachEvent('onOpenEnd', function(id,state) {
	 				if(state == 1) 
	 					treeOnClick(id);
		 		});			
	 	 });
	 	function treeOnClick(id) 
	 	{
 		 	$("#Divwait").css('visibility','visible');
		 	var treeStr='';	 	
		 	var url=restSecurityUrl+'/all/'+id;		 	
		 	var src = '<item text="..." im0="leaf.gif" id="t'+id+'"/>';
	 		if(tree.getItemText('t'+id) === '...')
	 		{
	 			$.get(url, function(data){	 
				 		 XMLData=XMLData.replace(src, data);
						 tree.destructor();
						 tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
				 		 tree.setSkin('dhx_skyblue');
		 				 tree.setImagePath("../../Scripts/MyJQuery/dhtmlxTree/imgs_rtl//");
		 				 tree.enableCheckBoxes(1);
						 tree.enableThreeStateCheckboxes(true);
						 tree.loadXMLString(XMLData);
						 $("#Divwait").css('visibility','hidden');
						 tree.openItem(id);	 
						 tree.attachEvent('onOpenEnd', function(id,state) {
			 				if(state == 1) 
			 					treeOnClick(id);
			 			 });	
 				});			
	 		} 
		}
 	</script>
</head>
<body>
 <div id="Divwait" style="width: 100%;height: 100%;opacity:0.5;background-color:#2D2D2D;z-index: 1000;position: fixed; ">
 	<img src="../../Desktop/images/ajax-loader.gif" alt="Smiley face" height="42" width="42" style="margin-right: 50%;" /> 
 </div>
	 <div id="treeboxbox_tree" style="width:98%; height:90%;background-color:#f5f5f5;border :1px solid Silver;overflow:scroll;" ></div>
	 <input type="button" id="BtnSaveUserGroups" value="ثبت" onclick="saveGroupActions()" class="actionBtn" style="text-align: center;" />	
</body>
</html>