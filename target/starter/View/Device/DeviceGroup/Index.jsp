<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<%@ include file="/View/Head.jsp" %>
  	<%@ include file="/View/FancyHead.jsp" %>
  	<script type="text/javascript" src="../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxcommon.js"></script>
 	<script type="text/javascript" src="../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.js"></script>
 	<script language="JavaScript" src="../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlxmenu.js"></script>
	<script language="JavaScript" src="../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlxmenu_ext.js"></script>
 	<link rel="stylesheet" type="text/css" href="../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlxmenu_dhx_skyblue.css">
 	<link rel="stylesheet" type="text/css" href="../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxtree.css" />
 	
 	<script type="text/javascript">
		 	var restUrl="<c:url value = '/rest/device/deviceGroup' />";
		 	var parentId=1;   
			var MenuId=-1;
			var FirstData='<?xml version="1.0" encoding="utf-8"?><tree id="0"><item  text="درختواره گروه بندی تجهیزات و دستگاه ها" id="1" open="1" im0="tombs.gif" im1="tombs.gif" im2="iconSafe.gif">';
			var XMLData=FirstData;
		  	$(function () {
				initTree();
			});
			function initTree(){
				Loader(true);
				configTree();
				$.get(restUrl+"/all/1", function(data){	
					XMLData=XMLData+data	+"</item>   </tree>"
			    	tree.loadXMLString(XMLData);	
				});
				Loader(false);	 
			}
			function configTree(){
				tree = new dhtmlXTreeObject("treeboxbox_tree", "100%", "100%", 0);
		 		tree.setSkin('dhx_skyblue');
				tree.setImagePath("../../../Scripts/MyJQuery/dhtmlxTree/imgs_rtl/");
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
				menu.loadXML("../../../Scripts/MyJQuery/dhtmlxTree/dhtmlxMenu/dhtmlMenu.xml");
			}
			function treeOnClick(id){
		 		Loader(true);
			 	var treeStr='';	 	
			 	var src = '<item text="..." im0="leaf.gif" id="t'+id+'"/>';
		 		if(tree.getItemText('t'+id) === '...'){
		 			$.get(restUrl+"/all/"+id, function(data){	 
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
			            'width': 500,
			            'height':200,
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
		            			  url: restUrl+"/delete/"+id,
		            			  type: 'DELETE',
		            			  dataType: 'json',
		            			  contentType: 'application/json; charset=utf-8',
		            			  success: function(res) {
		            				 showMessage('<spring:message code="UI.General‬.DeleteMessage" />',2);   
		            				 tree.deleteItem(id,true);
		                             Loader(false);
		            	  		  },
		            	  		  error:function(){
		            	  			showMessage('این گرو اطلاعات آن در جای دیگری استفاده شده و امکان حذف آن وجود ندارد');   
			            	  	  }	
		            	  });
		          }
		     }
		     function refreshTree(){
		   	  	tree.destructor();
		      	 $("#treeboxbox_tree").html('');
			   	 parentId=1; 
			   	 MenuId=-1;
			 	 XMLData=FirstData;
				 initTree();
		     }
		     function treeOnDoubleClick(itemId,state){
		    	 $.getJSON("<c:url value = '/rest/device/deviceGroup/load/' />"+itemId, {}, function (deviceGroup) {
			    	 try{
		     			if(location)
		     			    window.parent.setDeviceGroup(deviceGroup.id,deviceGroup.name,deviceGroup.code);
			    	 }
			    	 catch(e){}
		  	    });
		     }
 	</script>
</head>
<body>
 <form id="FormMain" >
 	<div id="lightbox">
 	</div>
    <table style="width: 100%">
	    <tr>
	    	<td align="center">
	    		<div id="treeboxbox_tree" style="width:98%; height:320px;background-color:#f5f5f5;border :1px solid Silver;overflow:scroll;" ></div>
	    	</td>
	    </tr>
    </table>
    </form>
</body>
</html>