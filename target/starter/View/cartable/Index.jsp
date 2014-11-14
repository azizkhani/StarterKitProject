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
		var restUrl = "<c:url value = '/rest/cartable' />";
		function init() {
			fillProcess();
			fillAuthenticatedUserTasks();
		}
		function fillProcess() {
			Loader(true);
			$.getJSON(restUrl + "/process/list", {}, function(entities) {
				$('table.gridprocess tbody tr').remove();
				$('#GridRowTemplateProcess').tmpl(entities).appendTo(
						'#entityBodyProcess');
				$('table.gridprocess tbody tr:not([th]):odd')
						.addClass('oddRow');
				$('table.gridprocess tbody tr:not([th]):even').css(
						'backgroundColor', '#DFEBF4');
				createNavigation(10, pageNo, pageSize);
				Loader(false);
			});
		}
		function fillAuthenticatedUserTasks() {
			Loader(true);
			$.getJSON(restUrl + "/task/list", {}, function(entities) {
				$('table.gridtasks tbody tr').remove();
				$('#GridRowTemplateTask').tmpl(entities).appendTo('#entityBodyTask');
				$('table.gridtasks tbody tr:not([th]):odd').addClass('oddRow');
				$('table.gridtasks tbody tr:not([th]):even').css('backgroundColor', '#DFEBF4');
				createNavigation(10, pageNo, pageSize);
				Loader(false);
			});
		}
		function startProcess(processKey, formKey,processName) {
			if (formKey.length > 0) {
				if(formKey.indexOf('?')>=0){
					openWindow("1221", processName, formKey+'&processDefinationKey='+processKey, 800, 400);
				}
				else{
					openWindow("1221", processName, formKey+'?processDefinationKey='+processKey, 800, 400);
				}
			} else {
				Loader(true);
				$.ajax({
					type : "POST",
					url : restUrl + "/process/startByKey/" + processKey,
					contentType : "application/json;",
					dataType : "json",
					success : function(res) {
						Loader(false);
						fillAuthenticatedUserTasks();
					},
					error : function() {
						Loader(false);
						fillAuthenticatedUserTasks();
					}
				});
			}
		}
		function startTask(taskId, formKey,taskName) {
			if (formKey.length > 0) {
				if(formKey.indexOf('?')>=0){
					openWindow("1221", taskName, formKey+'&taskKey='+taskId, 800, 400);
				}
				else{
					openWindow("1221", taskName, formKey+'?taskKey='+taskId, 800, 400);
				}
			} 
		}
		function completeTask(taskId) {
			Loader(true);
			$.ajax({
				type : "POST",
				url : restUrl + "/task/complete/" + taskId,
				contentType : "application/json;",
				dataType : "json",
				success : function(res) {
					Loader(false);
					fillAuthenticatedUserTasks();
				},
				error : function() {
					Loader(false);
					fillAuthenticatedUserTasks();
				}
			});
		}
		function claimTask(taskId) {
			Loader(true);
			$.ajax({
				type : "POST",
				url : restUrl + "/task/claim/" + taskId,
				contentType : "application/json;",
				dataType : "json",
				success : function(res) {
					Loader(false);
					fillAuthenticatedUserTasks();
				},
				error : function() {
					Loader(false);
					fillAuthenticatedUserTasks();
				}
			});
		}
	</script>
</head>
<body onload="pageLoad()">
	<form id="FormMain">
		<%@ include file="GridProcess.jsp"%>
		<%@ include file="GridTasks.jsp"%>
	</form>
</body>
</html>
