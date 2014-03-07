<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<%@ include file="../Head.jsp"%>
	<script language="javascript" type="text/javascript">
		var currentId = -1;
		var emptyEntity = {
			id : -1,
			code : "",
			topic : "",
			parentId : -1,
			processDefinationKey :'<%=request.getParameter("processDefinationKey")%>',
			taskKey :'<%=request.getParameter("taskKey")%>'
		};
		var currentEntity = $.extend(true, {}, emptyEntity);
		var restUrl = "<c:url value = '/rest/baseinformation' />";
		$(function() {
			showCurrent(<%=request.getParameter("id")%>);
		});
		function showCurrent(currentId) {
			Loader(true);
			$.getJSON(restUrl + "/load/" + currentId, function(entityData) {
				setInputByEntity(entityData);
				Loader(false);
			});
		}
		function confirm() {
			Loader(true);
			$.ajax({
				type : "POST",
				url : restUrl + "/confirm/" + emptyEntity.taskKey + "/"+ $('#cmbApproved').val(),
				data : JSON.stringify(currentEntity),
				contentType : "application/json;",
				dataType : "json",
				success : function(res) {
					Loader(false);
				},
				error : function(res) {
					Loader(false);
				}
			});
		}
		function setInputByEntity(entity) {
			baseSetInputByEntity(entity);
		}
		function setEntityFromInput(entity) {
			entity.parentId = parentId;
			baseSetEntityFromInput(entity);
		}
	</script>
</head>
<body>
	<form id="FormMain">
		<div style="width: 100%" align="center">
			<input type="hidden" id="id" value="-1" />
			<table class="plain">
				<tr>
					<td class="fieldTitle" align="left">
						<span style="color: red">
							<spring:message code="UI.BaseInformation.Code" />
						</span>
						&nbsp;:
					</td>
					<td class="fieldValue" align="right">
						<input name="code" id="txtCode" type="text" class="validate[required,custom[integer]] " maxlength="4" style="width: 50px" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="fieldTitle" align="left">
						<span style="color: red">
							<spring:message code="UI.BaseInformation.Topic" />
						</span>
						&nbsp;:
					</td>
					<td class="fieldValue" align="right">
						<input name="topic" id="txtTopic" type="text" class="text validate[required]" maxlength="50" style="width: 400px" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="fieldTitle" align="left">
						<span style="color: red"> approved </span>
						&nbsp;:
					</td>
					<td class="fieldValue" align="right">
						<select id="cmbApproved">
							<option value="1">ok</option>
							<option value="0">!ok</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="button" value='confirm' onclick="confirm()" class="actionBtn" />
		</div>

	</form>
</body>
</html>
