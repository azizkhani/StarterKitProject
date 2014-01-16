<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head >
    <title></title>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="../Head.jsp" %>
    <script language="javascript" type="text/javascript">
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.groupName desc';
        var resultNum = 0;
        var currentId = -1;
        var entitiesCache = {};
        var emptyEntity = { id: -1,groupName:"",createdBy: -1, updatedBy:  -1, isEnabled: true };
        var currentEntity = $.extend(true, {}, emptyEntity); //Clone The Object
        var restUrl="<c:url value = '/rest/security/group' />";
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
                        showMessage('موردی یافت نشد', 2);
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
                showMessage('موردی انتخاب نشده است', 3);
        }
        function deleteClicked() {
            var id = selectedRowId('table_content');
            if (id) {
                if (confirm("آیا مطمئنید؟ شما قصد حذف اطلاعات زیر را دارید\n\r ")) {
                    Loader(true);
                    $.getJSON(restUrl+"/delete/"+id, function (data) {
                        fillTable(); Loader(false);
                    });
                }
            }
            else {
                showMessage('موردی انتخاب نشده است', 3);
            }
        }
        function writeEntity() {
            if (!$("#FormMain").validationEngine('validate'))
                return;
            setEntityFromInput(currentEntity);
            Loader(true);
            $.getJSON(restUrl+"/save", currentEntity, function (data) {
                showListPage(); Loader(false);
            });
        }
        function clearEntity() {
            currentId = -1;
            setInputByEntity(emptyEntity);
        }
        function simpleSearch() {
            search();
        }
        function showListPage() {
            fillTable();
        }
        function showEditPage() {
            currentId = -1;
            clearEntity();
            showElements(new Array('edit_form'));
        }
        function setInputByEntity(entity) {
            baseSetInputByEntity(entity);
        }
        function setEntityFromInput(entity) {
            baseSetEntityFromInput(entity);
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
