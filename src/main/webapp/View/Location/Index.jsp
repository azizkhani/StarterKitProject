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
 	<script language="JavaScript" src="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlxmenu.js"></script>
	<script language="JavaScript" src="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlxmenu_ext.js"></script>
 	<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlxmenu_dhx_skyblue.css">
 	<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.css" />
 	<script type="text/javascript" src="../../Scripts/MyJQuery/FancyBox/jquery.fancybox-1.3.4.pack.js"></script>
 	<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/FancyBox/jquery.fancybox-1.3.4.css" />		
 	<script type="text/javascript">
		var parentId=1;   
		var MenuId=-1;
		var restSecurityUrl="<c:url value = '/rest/' />";
		var XMLData="<?xml version='1.0' encoding=\"utf-8\"?><tree id=\"0\">";
		XMLData=XMLData+"<item  text=\"مناطق سیستم\" id=\"1\" open=\"1\" im0=\"tombs.gif\"    im1=\"tombs.gif\"    im2=\"iconSafe.gif\">";
	  	$(function () {
				initTree();
		});
		function initTree(){
			Loader(true);
			configTree();
 			$.get("<c:url value = '/rest/location/all/1' />", function(data){	
					XMLData=XMLData+data	+"</item>   </tree>"
			    	tree.loadXMLString(XMLData);	
			});
 			Loader(false);	 
		}
		function configTree(){
			tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
	 		tree.setSkin('dhx_skyblue');
			tree.setImagePath("../../Scripts/MyJQuery/dhtmlxTree/imgs_rtl/");
			tree.setOnDblClickHandler(treeOnDoubleClick); 
			tree.attachEvent("onRightClick", function(id, e){
				MenuId=id;
				menu.showContextMenu(e.clientX,e.clientY);
			});
 			tree.attachEvent('onOpenEnd', function(id,state){
	 			if(state == 1) 
	 				treeOnClick(id); 
	 		});
 			tree.setOnDblClickHandler(treeOnDoubleClick); 
			//Start Config Contex Menu
 			menu = new dhtmlXMenuObject();
			menu.renderAsContextMenu();
			menu.attachEvent("onClick", onButtonClick);
			menu.loadXML("../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlMenu.xml");
		}
		function treeOnClick(id){
	 		Loader(true);
		 	var treeStr='';	 	
		 	var src = '<item text="..." im0="leaf.gif" id="t'+id+'"/>';
	 		if(tree.getItemText('t'+id) === '...'){
	 			$.get("<c:url value = '/rest/location/all/' />"+id, function(data){	 
			 		XMLData=XMLData.replace(src, data);
					tree.destructor();
					configTree();
	 				tree.loadXMLString(XMLData);
					tree.openItem(id);	
 				});		
	 		} 	
	 		Loader(false);
		}
		function onButtonClick(menuitemId, type){
			if (menuitemId==='1'){ // یعنی حالت درج	 
				var url="Edit.jsp?id="+MenuId+"&EditMode=0";
				url="<c:url value = '"+url+"' />";
				showFancyBox(url);
			}
			else if (menuitemId==='2'){ // یعنی حالت ویرایش	 
				var url="Edit.jsp?id="+MenuId+"&EditMode=1";
				url="<c:url value = '"+url+"' />";
				showFancyBox(url);
			}
			else if (menuitemId==='3'){ // یعنی حالت حذف	
				deleteClicked (MenuId);
		    } 
		 }
		function showFancyBox(url){
			  $.fancybox({
		            'width': '100%',
		            'height':'100%',
		            'autoScale': true,
		            'transitionIn': 'fade',
		            'transitionOut': 'fade',
		            'type': 'iframe',
		            'href': url
		      });
		  }
	     function deleteClicked(id) {
	          if (id>0 && confirm('<spring:message code="UI.General.DeleteMessage" />')) {
	                  Loader(true);
	              	  $.ajax({
	            			  url: "<c:url value = '/rest/location/delete/' />"+id,
	            			  type: 'DELETE',
	            			  dataType: 'json',
	            			  contentType: 'application/json; charset=utf-8',
	            			  success: function(res) {
	            				 showMessage('<spring:message code="UI.General‬.DeleteMessage" />',2);   
	            				 tree.deleteItem(id,true);
	                             Loader(false);
	            	  			}	,
	            	  		  error:function(){
		            	  			showMessage('این مکان اطلاعات آن در جای دیگری استفاده شده و امکان حذف آن وجود ندارد');   
			            	  	  }	
	            	  });
	          }
	     }
	     function refreshTree(){
	   	  	tree.destructor();
	      	 $("#treeboxbox_tree").html('');
		   	 parentId=1; 
		   	 MenuId=-1;
		   	 XMLData="<?xml version='1.0' encoding=\"utf-8\"?><tree id=\"0\">";
		   	 XMLData=XMLData+"<item  text=\"...\" id=\"1\" open=\"1\" im0=\"tombs.gif\"    im1=\"tombs.gif\"    im2=\"iconSafe.gif\">";
			 initTree();
	     }
	     function treeOnDoubleClick(itemId,state){
	    	 $.getJSON("<c:url value = '/rest/location/load/' />"+itemId, {}, function (location) {
		    	 try{
	     			if(location)
	     			    window.parent.setLocation(location.id,location.name,location.code);
		    	 }
		    	 catch(e){}
  	    	 });
	     }
 	</script>
</head>
<body>
 <form id="FormMain" >
    <table style="width: 100%">
	    <tr>
	    	<td align="center">
	    		<div id="treeboxbox_tree" style="width:98%; height:450px;background-color:#f5f5f5;border :1px solid Silver;overflow:scroll;" ></div>
	    	</td>
	    </tr>
    </table>
    </form>
</body>
</html>