<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head > 
    <title></title>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="../Head.jsp" %>
    <script language="javascript" type="text/javascript">
        var entitiesCache = {};
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.code desc';
        var resultNum = 0;
        var currentId = -1;
        var emptyEntity = { id: -1,code:"",topic:"", parent:{id:""}};
        var currentEntity = $.extend(true, {}, emptyEntity); 
        var restUrl="<c:url value = '/rest/baseinformation' />";
        var parentId=<%= request.getParameter("parentId")%>;
      
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
            $.getJSON(restUrl+"/list/"+parentId+"/", JsonData, function (entities) {
                entitiesCache = entities.entityList;
                showElements(new Array('table_content', 'search_form'));
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
        function deleteClicked(id) {
            if (id>0) {
                if (confirm("آیا مطمئنید؟ شما قصد حذف اطلاعات زیر را دارید\n\r " )) {
                    Loader(true);
                    $.ajax({
        				type:"DELETE",
        				url	:restUrl+"/delete/"+id,
        				contentType:"application/json;",
        				success:function(res){fillTable();Loader(false);},
        				error:function(res){showMessage('این مورد در جدول دیگری استفاده شده و امکان حذف وجود ندارد');Loader(false);}
                    });
                }
            }
            else 
                showMessage('موردی انتخاب نشده است', 3);
        }
        function saveEntity() {
            if (!$("#FormMain").validationEngine('validate'))
                return;
            setEntityFromInput(currentEntity);
            Loader(true);
            $.ajax({
				type:"POST",
				url	:restUrl+"/save/" + parentId,
				data:JSON.stringify(currentEntity),
				contentType:"application/json;",
				dataType:"json",
				success:function(res){fillTable();Loader(false);},
				error:function(res){showMessage('کد یا شرح وارد شده تکراری است');Loader(false);}
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
        	entity.parent.id=parentId;
            baseSetEntityFromInput(entity);
        }
    </script>
</head>
<body onload="pageLoad()">
    <form id="FormMain" >
    <div dir="rtl">
        <center>
            <%@ include file="Search.jsp" %>
			<%@ include file="Grid.jsp" %>	
			<%@ include file="Edit.jsp" %>
        </center>
    </div>
    </form>
</body>
</html>
