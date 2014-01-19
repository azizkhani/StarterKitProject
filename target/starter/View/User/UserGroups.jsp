<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title></title> 
<%@ include file="../Head.jsp" %>
<script type="text/javascript">
	var userId=<%= request.getParameter("userId")%>;
	$(function(){
		fillCheckBoxList();
	});
	var restSecurityUrl="<c:url value = '/rest/security' />";
	function fillCheckBoxList() 
	{
	    Loader(true);
	    JsonData = { searchFilter: "", order: "", pageNumber: 1, pageSize: 100 };
	    $.getJSON(restSecurityUrl+"/group/listAll", JsonData, function (entities) {
	        $('#CheckBoxListItemTemplate').tmpl(entities).appendTo('#divCheckboxUserGroups');
	        $.getJSON(restSecurityUrl+"/user/load/"+userId, function (userEnttitiy) {
	        	$.each(userEnttitiy.groups,function(i,group){
	    			$("#groupItem"+group.id).attr("Checked","Checked");
	    		 });
                Loader(false);
            });
        	Loader(false);
	    });
	}
	function saveUserGroups()
	{		
		var group={id:-1};
		var result = new Array();
		$(".UserGroupCheckBoxItem:checked ").each(function(){
            result.push({ 'id': $(this).val()});
		});
		$.ajax({
			  url: restSecurityUrl+"/user/saveGroups/"+userId,
			  type: 'POST',
			  dataType: 'json',
			  contentType: 'application/json; charset=utf-8',
			  data:JSON.stringify(result),
			  success: function(res) {
	                alert('گروه های این کاربر با موفقیت ثبت گردید.');
	          }
		});
	} 
</script>
</head>
<body>
	<div style="float: right;">
		<div class="fieldTitle" style="direction: rtl;">انتخاب گروه های کاربری :</div>
		<br/>
		<div id="divCheckboxUserGroups" style="direction: rtl;">						
		</div>
		<div>
			<input type="button" id="BtnSaveUserGroups" value="ثبت"
				onclick="saveUserGroups()" class="actionBtn" />
		</div>
	</div>
	    <script id="CheckBoxListItemTemplate" type="text/html">
    		<input type="checkbox" id=groupItem${id}  value=${id} class="UserGroupCheckBoxItem" /> 
				<label> <span >${groupName}</span> </label>		
			<br>
		</script>	
</body>
</html>