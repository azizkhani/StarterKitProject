<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hafiz cartable</title>
	<%@ include file="../HeadN.jsp"%>
	<script language="javascript" type="text/javascript">
		var restUrl = "<c:url value = '/rest/cartable' />";
		$(function(){	
  			fillProcessTree()
		});
		function onDblClick(rowIndex, rowData){
			startTask(rowData.id,rowData.formKey,rowData.name);
		}
		function fillProcessTree() {
			$.getJSON(restUrl + "/process/list", {}, function(entities) {
				treeData=new Array();;
				for(entity in entities){
					treeData.push(
								{
									id: entities[entity].id,
									text: entities[entity].name,
									attributes:{
										id:entities[entity].id,
										name:entities[entity].name,
										key:entities[entity].key,
										description:entities[entity].description,
										version:entities[entity].version,
										startFormKey:entities[entity].tartFormKey,
										formKey:entities[entity].formKey
									}
								}
					);
				}
				$('#tree_process').tree({data:treeData});
			});
		}
		function openWindows(url,processName){
			$('#iframeWindow').attr('src', url);
			$('#window_process').window({title:processName});
			$('#window_process').window('open');
		}
		function startProcess() {
			var node = $('#tree_process').tree('getSelected');
			processKey=node.attributes.key;
			formKey=node.attributes.formKey;
			processName=node.attributes.name;
			if (formKey.length > 0) {
				if(formKey.indexOf('?')>=0){
					openWindows('<c:url value = '/' />'+formKey+'&processDefinationKey='+processKey,processName);
				}
				else{
					openWindows('<c:url value = '/' />'+formKey+'?processDefinationKey='+processKey,processName);
				}
			} else {
				$.ajax({
					type : "POST",
					url : restUrl + "/process/startByKey/" + processKey,
					contentType : "application/json;",
					dataType : "json",
				});
			}
		}
		function startTask(taskId, formKey,taskName) {
			if (formKey.length > 0) {
				if(formKey.indexOf('?')>=0){
					openWindows('<c:url value = '/' />'+formKey+'&taskKey='+taskId, taskName);
				}
				else{
					openWindows('<c:url value = '/' />'+formKey+'?taskKey='+taskId, taskName);
				}
			} 
		}
		function showContextMenufunction(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			$('#mm').menu('show',{
				left: e.pageX,
				top: e.pageY
			});
		}
	</script>
</head>
<body>
	 <div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="startProcess()" data-options="iconCls:'icon-add'">شروع فرایند</div>
		<div onclick="removeit()" data-options="iconCls:'icon-remove'">نمایش فرایند</div>
	</div>
	<div class="easyui-layout" style="width:100%;height:600px;margin-left: 1px;margin-right: 1px">
		<div data-options="region:'north'" style="height:50px;text-align: right;">
			<img src="logoleft.png" height="50px" width="240px" style="vertical-align: middle;"></img>کارتابل گردش کاری شرکت حفیظ
		</div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'east',split:true" title="منوها" style="width:240px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="کارتابل ها" style="padding:10px;">
					<ul class="easyui-tree" data-options="url:'tree_cartables.json',method:'get',animate:true,dnd:true"></ul>
				</div>
				<div title="فرایندها" data-options="selected:true" style="padding:10px;">
					<ul id="tree_process" class="easyui-tree" 
						data-options="animate:true,dnd:true, onContextMenu: showContextMenufunction"></ul>
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'کارها'">
			<table class="easyui-datagrid"
				   data-options="url:'<c:url value = '/rest/cartable/task/list' />',
				   				 method:'get',singleSelect:true,fit:true,fitColumns:true,rownumbers:true,
				   				 pagination:true,onDblClickRow:onDblClick">
				<thead>
					<tr>
							<th  data-options="field:'id'">id</th>
							<th  data-options="field:'name'">نام کار</th>
							<th  data-options="field:'taskDefinitionKey'">کد کار</th>
							<th  data-options="field:'description'" width="50px">شرح</th>
							<th  data-options="field:'processInstanceId'">کد فرایند اجرایی</th>
							<th  data-options="field:'createTime'">تاریخ ارجاع</th>
							<th  data-options="field:'formKey'">کد فرم</th>
							<th  data-options="field:'actions'">actions</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	 <div id="window_process" class="easyui-window"  
	 		data-options="modal:true,closed:true,iconCls:'icon-save',inline:true,minimizable:false" style="width:800px;height:450px;">
	 	<iframe  id="iframeWindow" src="" width="100%" height="100%" style="border: none"></iframe>
	</div>
</body>
</html>