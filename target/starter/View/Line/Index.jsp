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
    <%@ include file="/View/TabsHead.jsp" %>
    <script language="javascript" type="text/javascript">
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.code desc';
        var resultNum = 0;
        var currentId = -1;
        var emptyEntity = { id: -1,
        					code : "",name : "",startTime : "",endTime : "",description : "", 
        					totalTimeGo : 0, totalTimeBack : 0,type : 0,tariff:0,
        					terminal :{id : -1},responsible :{id : -1}
        };
        var currentEntity = $.extend(true, {}, emptyEntity); 
        var restUrl="<c:url value = '/rest/baseinfo/line' />";
        $(function () {
        		 $("#tabsList").tabs({
        	            select: function(event, ui) {
        	                if (currentId <= 0) {
        	                	showMessage("ابتدا مشخصات اصلي را وارد كنيد");
        	                    return false;
        	                }
        	                return true;
        	            },
        	            selected: 0
        	      });
                 $("#txtAutoComplete").autocomplete({  
                     source: function(req, resp){  
                     	jsonReq={ search : req.term,resultCount:20};
                        $.getJSON("<c:url value = '/rest/personel/autoComplete' />", jsonReq, function(data) {  
                         	resp( $.map( data, function( item ) {
     							return {
     								label: item.personCode+'  /  '+item.firstName+'  /  '+item.lastName,
     								value: item.id,
     								data:item,
     							}
     						}));
                     	});  
                	},  
             	    select: function(event, ui) { 
             	    	$('#txtFullName').html(ui.item.data.fullName);
             	 		$('#hfieldResponsibleId').val(ui.item.data.id);
             	    	$(event.target).val(ui.item.data.personCode);
                         return false;
                     },  
                     minLength: 2  
                 });
                 $("#txtStartTime").mask("99:99");
                 $("#txtEndTime").mask("99:99");
                 JsonData = { searchFilter: "", order: "", pageNumber: 0, pageSize: 0 };
                 fillCombo("cmbTerminal"	, 	"<c:url value = '/rest/baseinfo/terminal/list' />", JsonData, "id", "name","....."); 
                 fillCombo("cmbStation"	, 	"<c:url value = '/rest/baseinfo/station/list' />", JsonData, "id", "name","....."); 
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
                lineStation.fillGrid();
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
            $('#hfieldResponsibleId').val(entity.responsible.id);
            $('#txtAutoComplete').val(entity.responsible.personCode);
            $('#txtFullName').html(entity.responsible.fullName);
            $('#cmbTerminal').val(entity.terminal.id);    
        }
        function setEntityFromInput(entity) {
            baseSetEntityFromInput(entity);
            entity.terminal.id=$('#cmbTerminal').val();      
            entity.responsible.id=$('#hfieldResponsibleId').val();           
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
