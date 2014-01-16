<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head >
    <title></title>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="/View/Head.jsp" %>
    <%@ include file="/View/AutoCompleteInclude.jsp" %>
    <%@ include file="/View/FancyHead.jsp" %>
    
    <script language="javascript" type="text/javascript">
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.code desc';
        var resultNum = 0;
        var currentId = -1;
        var emptyEntity = { id: -1,
        					code : "",deviceGroup : {id : -1},description : "", 
        					model :{id : -1},mark :{id : -1}
        };
        var currentEntity = $.extend(true, {}, emptyEntity); 
        var restUrl="<c:url value = '/rest/device' />";
        $(function () {
        		 $('#btnSelectDeviceGroup').click(function(){
         			$.fancybox({
         		            'width': '70%',
         		            'height': '90%',
         		            'autoScale': true,
         		            'transitionIn': 'fade',
         		            'transitionOut': 'fade',
         		            'type': 'iframe',
         		            'href': "<c:url value = '/View/Device/DeviceGroup/Index.jsp' />"
         		    });
                 });
                 $('#txtDeviceGroupCode').keypress(function(event){
                 	var keycode = (event.keyCode ? event.keyCode : event.which);
                 	if(keycode == '13'){
                 			$.getJSON("<c:url value = '/rest/device/deviceGroup/find/code/' />"+$(this).val(), {}, function (deviceGroup) {
                     			if(location)
                     				setDeviceGroup(deviceGroup.id,deviceGroup.name,deviceGroup.code);
                  	    	});
                 	}
                 });
                 JsonData = { searchFilter: "", order: "", pageNumber: 0, pageSize: 0 };
                 fillCombo("cmbModel"	, 	"<c:url value = '/rest/baseinformation/list/9' />", JsonData, "id", "topic","....."); 
                 fillCombo("cmbMark"	, 	"<c:url value = '/rest/baseinformation/list/10' />", JsonData, "id", "topic","....."); 
        });
        function setDeviceGroup(deviceGroupId,deviceGroupName,deviceGroupCode){
        	$('#hfieldDeviceGroupId').val(deviceGroupId);
            $('#txtDeviceGroupCode').val(deviceGroupCode);
            $('#lblDeviceGroupTitle').html(deviceGroupName);
            $.fancybox.close();
        }
        function init() {
            fillTable();
        }
        function fillTable() {
            Loader(true);
            pageSize = $('#pageSize').val();
            if (pageSize < 5) {
                pageSize = 5;
                $('#pageSize').val(5);
            }
            if (pageSize > 50) {
                pageSize = 50;
                $('#pageSize').val(50);
            }
            JsonData = { searchFilter: getSearchFilter(), order: order, pageNumber: pageNo, pageSize: pageSize };
            $.getJSON(restUrl+"/list", JsonData, function (entities) {
                showElements(new Array('table_content', 'parentTitle'));
                resultNum = entities.totalRecords;
                $('#entityBody tr').remove();
                $('#GridRowTemplate').tmpl(entities.entityList).appendTo('#entityBody');
                if (resultNum == 0) {
                    if (isSearchState)
                        showMessage('<spring:message code="UI.General.NotFoundData" />');
                }
                $('table.grid tbody tr:not([th]):odd').addClass('oddRow');
                $('table.grid tbody tr:not([th]):even').css('backgroundColor', '#DFEBF4');
                createNavigation(entities.totalRecords, pageNo, pageSize);
                Loader(false);
            });
        }
        function showCurrent(CurrentId) {
            clearEntity();
            currentId = CurrentId;
            showElements(new Array('edit_form'));
            Loader(true);
            $.getJSON(restUrl+"/load/"+currentId, function (entity) {
                setInputByEntity(entity);
                Loader(false);
            });
        }
        function editClicked() {
            var id = selectedRowId('table_content');
            if (id)
                showCurrent(id);
            else
                showMessage('<spring:message code="UI.General.NotSelectItem" />');
        }
        function deleteClicked() {
            var id = selectedRowId('table_content');
            if (id>0) {
                if (confirm('<spring:message code="UI.General.DeleteMessage" />')) {
                    Loader(true);
                    $.ajax({
        				type:"DELETE",
        				url	:restUrl+"/delete/"+id,
        				data:{},
        				contentType:"application/json;",
        				dataType:"json",
        				success:function(res){fillTable(); Loader(false);},
        				error:function(res){showMessage('<spring:message code="UI.General.CanNotDelete" />');Loader(false);}
                    });
                }
            }
            else 
                showMessage('<spring:message code="UI.General.NotSelectItem" />');
        }
        function saveEntity() {
            if (!$("#FormMain").validationEngine('validate'))
                return;
            setEntityFromInput(currentEntity);
            Loader(true);  
            $.ajax({
				type:"POST",
				url	:restUrl+"/save",
				data:JSON.stringify(currentEntity),
				contentType:"application/json;",
				dataType:"json",
				success:function(res){Loader(false);fillTable();},
				error:function(res){showMessage('<spring:message code="UI.General.DuplicateCode" />');Loader(false);}
            });
        }
        function clearEntity() {
            currentId = -1;
            setInputByEntity(emptyEntity);
        }
        function showListPage() {
            fillTable();
        }
        function showEditPage() {
            clearEntity();
            showElements(new Array('edit_form'));
        }
        function setInputByEntity(entity) {
            baseSetInputByEntity(entity);
          
            $('#cmbModel').val(entity.model.id);   
            $('#cmbMark').val(entity.mark.id);    
            
            $('#hfieldDeviceGroupId').val(entity.deviceGroup.id);
            $('#txtDeviceGroupCode').val(entity.deviceGroup.code);
            $('#lblDeviceGroupTitle').html(entity.deviceGroup.name);
        }
        function setEntityFromInput(entity) {
            baseSetEntityFromInput(entity);
            entity.model.id=$('#cmbModel').val();      
            entity.mark.id=$('#cmbMark').val(); 
            entity.deviceGroup.id=$('#hfieldDeviceGroupId').val();             
        }
        function showHistory(deviceId){
        	url="<c:url value = 'View/Device/History/Index.jsp' />?id="+deviceId;
    		window.parent.openWindow(100, '<spring:message code="UI.Device.DeviceHistory" /> ', url, 800, 500);
        }
        
    </script>
</head>
<body onload="pageLoad()">
    <form id="FormMain" >
    
    <div dir="rtl">
            
			<%@ include file="Grid.jsp" %>	
			<%@ include file="Edit.jsp" %>
    </div>
    </form>
</body>
</html>
