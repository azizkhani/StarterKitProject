<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<%@ include file="../Head.jsp" %>
  	
  	<script type="text/javascript" src="../../Scripts/MyJQuery/FancyBox/jquery.fancybox-1.3.4.pack.js"></script>
 	<link rel="stylesheet" type="text/css" href="../../Scripts/MyJQuery/FancyBox/jquery.fancybox-1.3.4.css" />
 	
 	<script type="text/javascript">
	    var emptyEntity = { id: -1,code:"",name:"",parent:{id: -1}, latitude: -1, longitude: -1, description: ""};
	 	var restUrl="<c:url value = '/rest/location' />";
	 	var currentId=<%= request.getParameter("id")%>;; 
	 	var IsEditMode=<%= request.getParameter("EditMode")%>;
	 	$(function () {
				if (IsEditMode===1){
					Loader(true);
			        $.getJSON(restUrl+"/load/"+currentId, function (entityData) {
			            setInputByEntity(entityData); Loader(false);
			        });
				}
				$('#btnSelectPosition').click(function(){
					var lat=$('#txtLatitude').val();
					var lng=$('#txtLongitude').val();
					$.fancybox({
    		            'width': '100%',
    		            'height': '100%',
    		            'autoScale': true,
    		            'transitionIn': 'fade',
    		            'transitionOut': 'fade',
    		            'type': 'iframe',
    		            'href': "<c:url value = '/View/Map/SelectLonLat.jsp' />?lat="+lat+"&lng="+lng
    		    	});
				});
	    });
	 	function setPosition(lat, lng){
	 		$('#txtLatitude').val(lat);
			$('#txtLongitude').val(lng);
			$.fancybox.close();
		}
	    function writeEntity() {
	        if (!$("#FormLocation").validationEngine('validate'))
	            return;
	        setEntityFromInput(emptyEntity);
	        Loader(true);
			$.ajax({
				  url: restUrl+"/save",
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json; charset=utf-8',
				  data:JSON.stringify(emptyEntity),
				  success: function(res) {
				       showMessage('<spring:message code="UI.General.SaveMessage" />',2);  
					   Loader(false);  
					   window.parent.refreshTree();
		          },
		          error:function(res){showMessage('<spring:message code="UI.General.DuplicateCode" />');Loader(false);}
			});     
	    }
		function setInputByEntity(entity)	{
			 baseSetInputByEntity(entity);
			if (IsEditMode===1)
	        	 $('#id').val(entity.id);
	    	else
	    		 $('#id').val(-1);
		}
	    function setEntityFromInput(entity) {
	    	 baseSetEntityFromInput(entity);
	    	if (IsEditMode===1)
	        	entity.id=$('#id').val();
	    	else
	    		entity.parent.id=currentId;
	    }     
 	</script>
 
 </head>
<body>
  <input type="hidden" id="id" value="-1" />
 <form id="FormLocation" style="float: right;" >
 	<div id="Loader" style="position: absolute; color: white; top: 0; left: 0; display: none;
        background-color: Red; border-color: White;">
        <div id="loadingbox">
        	<spring:message code="UI.General.Loading" />
        </div>
        <div id="progressBackgroundFilter">
        </div>
    </div>
 
        <table cellpadding="2px" width="100%" style="direction: rtl;margin-top: 20px;">
                           
 
             <tr>
             	<td width="30%" style="text-align: left;"><span style="color:red"><spring:message code="UI.Location.Code" /></span> &nbsp;:</td>
             	<td><input name="code"  id="txtCode" style="width: 40px;" type="text" tabindex="1" class="text Property  validate[required]"  /></td>
             </tr>
             <tr>
             	<td style="text-align: left"><span style="color:red"><spring:message code="UI.Location.Name" /></span> &nbsp;:</td>
             	<td><input name="name" id="txtName"  style="width: 300px;" type="text" class="text Property validate[required]"  /></td>
             </tr>
             <tr>
             	<td style="text-align: left"><span style="color:red"><spring:message code="UI.Location.latitude" /></span>&nbsp;: </td>
             	<td>
             		<input name="latitude"  id="txtLatitude"  style="width: 100px;" type="text" class="text Property validate[required]" />
             		<input   id="btnSelectPosition" type="button" value="...."/>
             	</td>
             </tr>
              <tr>
             	<td style="text-align: left"><span style="color:red"><spring:message code="UI.Location.Longitude" /></span> &nbsp;:</td>
             	<td><input name="longitude" id="txtLongitude" style="width: 100px;" type="text" class="text Property validate[required]"  /></td>
             </tr>
              <tr>
             	<td style="text-align: left"><spring:message code="UI.Location.Description" /> &nbsp;:</td>
             	<td><textarea name="description" style="width: 300px;"  id="txtDescription"  class="text Property" ></textarea> </td>
             </tr>
             <tr>
                <td colspan="2" align="center">
                   <input  type="button" id="BtnWriteEntity" value="ثبت" onclick="writeEntity()" class="actionBtn" />
                </td>
             </tr>
	</table>
</body>
</html>