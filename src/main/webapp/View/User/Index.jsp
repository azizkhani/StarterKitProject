<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
    <title></title>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="../Head.jsp" %>
    
    <script language="javascript" type="text/javascript">
        var entitiesCache = {};
        var viewed = -1;
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.firstName desc'; 
        var resultNum = 0;
        var currentId = -1;
        var emptyEntity = { id: -1,
                			firstName:"",lastName:"", userName: "", passWord: "", email: "" ,
                			personel:{ id: -1,personCode:"",firstName:"", lastName: ""}
        };
        var currentEntity = $.extend(true, {}, emptyEntity); //Clone The Object
        var restUrl="<c:url value = '/rest/security/user' />";
        $(function () {
            $("#FormMain").validationEngine('attach');
        });
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
            entitiesCache = {};
            JsonData = { searchFilter: getSearchFilter(), order: order, pageNumber: pageNo, pageSize: pageSize };
            $.getJSON(restUrl+"/list", JsonData, function (entities) {
                entitiesCache = entities.entityList;
                showElements(new Array('table_content', 'parentTitle'));
                resultNum = entities.totalRecords;
                $('table.grid tbody tr').remove();
                $('#GridRowTemplate').tmpl(entitiesCache).appendTo('#entityBody');
                if (resultNum == 0) {
                    if (isSearchState)
                        showMessage(2,'<spring:message code="UI.General.NotFoundData" />');
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
            $.getJSON(restUrl+"/load/"+currentId, function (entityData) {
                setInputByEntity(entityData); Loader(false);
            });
        }
        function editClicked() {
            var id = selectedRowId('table_content');
            if (id)
                showCurrent(id);
            else
                showMessage(3,'<spring:message code="UI.General.NotSelectItem" />');
        }
        function deleteClicked() {
            var id = selectedRowId('table_content');
            if (id) {
                if (confirm('<spring:message code="UI.General.DeleteMessage" />')) {
                    Loader(true);
                    $.getJSON(restUrl+"/delete/"+id, function (data) {
                        fillTable(); Loader(false);
                    });  
                }
            }
            else 
                showMessage(3,'<spring:message code="UI.General.NotSelectItem" />');
        }
        function writeEntity() {
            if (!$("#FormMain").validationEngine('validate'))
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
    				  showListPage(); Loader(false);  
    	          }
    		});     
        }
        function clearEntity() {
            viewed = -1;
            currentId = -1;
            setInputByEntity(emptyEntity);
            $('#txtAutoComplete').removeAttr('disabled');
        }
        function simpleSearch() {
            search();
        }
        function showListPage() {
        	jQuery('#FormMain').validationEngine('hide');
            fillTable();
        }
        function showEditPage() {
            clearEntity();
            showElements(new Array('edit_form'));
        }
        function setInputByEntity(entity) {
            baseSetInputByEntity(entity);
            $('#personelId').val(entity.personel.id);
            $('#txtAutoComplete').val(entity.personel.personCode);
            $('#txtAutoComplete').attr('disabled','disabled');
        }
        function setEntityFromInput(entity) {
            baseSetEntityFromInput(entity);
            entity.personel.id=$('#personelId').val();            
        }
    </script>
</head>
<body onload="pageLoad()">
    <form id="FormMain" >
   
    <div dir="rtl">
             <%@ include file="Search.jsp" %>
			<%@ include file="Grid.jsp" %>	
			<%@ include file="Edit.jsp" %>
    </div>
    </form>
</body>
</html>
