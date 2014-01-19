<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<%@ include file="../../Head.jsp" %>
 	<script type="text/javascript">
 
	    var emptyEntity = { id: -1,code:"",name:"",parent:{id: -1},  description: "" };
	    var currentEntity = $.extend(true, {}, emptyEntity); //Clone The Object
	 	var restUrl="<c:url value = '/rest/device/deviceGroup' />";
	 	var currentId=<%= request.getParameter("id")%>;; 
	 	var IsEditMode=<%= request.getParameter("EditMode")%>;
		if (IsEditMode===1){
			Loader(true);
	        $.getJSON(restUrl+"/load/"+currentId, function (entityData) {
	            setInputByEntity(entityData); Loader(false);
	        });
		}
	    function writeEntity() {
	  	   if (!$("#FormDeviceGroup").validationEngine('validate'))
	               return;
	        setEntityFromInput(currentEntity);
	        Loader(true);
			$.ajax({
				  url: restUrl+"/save",
				  type: 'POST',
				  dataType: 'json',
				  contentType: 'application/json; charset=utf-8',
				  data:JSON.stringify(currentEntity),
				  success: function(res) {
				        showMessage('<spring:message code="UI.General.SaveMessage" />');  
				        window.parent.refreshTree();
					    Loader(false);  
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
 <form id="FormDeviceGroup"  >
		 <input type="hidden" id="id" value="-1" />
  			<table border="0" cellpadding="0" cellspacing="0" dir="rtl" align="center" style="width: 100%">
                <tr>
                    <td style="background: url(../../../Content/Login/P_RightTop.jpg) no-repeat right bottom;
                        width: 20px; ">
                        &nbsp;
                    </td>
                    <td style="background: url(../../../Content/Login/P_RepeatTop.jpg) repeat-x 50% bottom;
                       height: 17px">
                    </td>
                    <td style="background: url(../../../Content/Login/P_LeftTop.jpg) no-repeat left bottom;
                        width: 20px;">
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td style="background: url(../../../Content/Login/P_RepeatRight.jpg) repeat-y right 50%;width: 20px;">
                        &nbsp;
                    </td>
                    <td >
                          <table cellpadding="2px" style="direction: rtl;width: 100%">
							<tr>
							  	<td style="text-align: left"><span style="color:red"><spring:message code="UI.DeviceGroup.Code" /></span> &nbsp;:</td>
								<td><input name="code" id="txtCode"  style="width: 300px;" type="text" class="text Property validate[required]"  /></td>
							</tr>
							<tr>
								<td style="text-align: left"><span style="color:red"><spring:message code="UI.DeviceGroup.Name" /></span> &nbsp;:</td>
								<td><input name="name" id="txtName"  style="width: 300px;" type="text" class="text Property validate[required]"  /></td>
							</tr>
							 <tr>
								<td style="text-align: left"><spring:message code="UI.DeviceGroup.Description" /> &nbsp;:</td>
								<td><textarea name="description" style="width: 300px;"  id="txtDescription"  class="text Property" ></textarea> </td>
							</tr>
							 <tr>
							    <td colspan="2">
							       <input style="float: left;" type="button" id="BtnWriteEntity" value="ثبت" onclick="writeEntity()" class="actionBtn" />
							          </td>
							 </tr>
						</table>
                    </td>
                    <td style="background: url(../../../Content/Login/P_RepeatLeft.jpg) repeat-y left 50%;width: 20px;">
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td style="background: url(../../../Content/Login/P_RightBtm.jpg) no-repeat right top;width: 20px">
                        &nbsp;
                    </td>
                    <td style="background: url(../../../Content/Login/P_RepeatBtm.jpg) repeat-x;height: 17px">
                        &nbsp;
                    </td>
                    <td style="background: url(../../../Content/Login/P_LeftBtm.jpg) no-repeat left top;width: 20px">
                        &nbsp;
                    </td>
                </tr>
            </table>
</form> 
 
</body>
</html>