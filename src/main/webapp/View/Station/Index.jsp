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
    <%@ include file="../AutoCompleteInclude.jsp" %>
     <%@ include file="../FancyHead.jsp" %>
    <script language="javascript" type="text/javascript">
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.code desc';
        var resultNum = 0;
        var currentId = -1;
        var emptyEntity = { id: -1,code: "",name: "",address:	"",description: "",location:{id:-1}, latitude: 0, longitude: 0};
        var currentEntity = $.extend(true, {}, emptyEntity); 
        var restUrl="<c:url value = '/rest/baseinfo/station' />";

        $(function () {
			$('#btnSelectPosition').click(function(){
				var lat=$('#txtLatitude').val();
				var lng=$('#txtLongitude').val();
				$.fancybox({
		            'width': '100%',
		            'height': '100%',
		            'autoScale': true,
		            'transitionIn': 'fade',
		            'transitionOut': 'fade',
		            'type': 'iframe',
		            'href': "<c:url value = '/View/Map/SelectLonLat.jsp' />?lat="+lat+"&lng="+lng
		    	});
			});
			 $('#btnSelectLocation').click(function(){
	    			$.fancybox({
	    		            'width': '70%',
	    		            'height': '90%',
	    		            'autoScale': true,
	    		            'transitionIn': 'fade',
	    		            'transitionOut': 'fade',
	    		            'type': 'iframe',
	    		            'href': "<c:url value = '/View/Location/Index.jsp' />"
	    		    });
	            });
            $('#txtLocationCode').keypress(function(event){
            	var keycode = (event.keyCode ? event.keyCode : event.which);
            	if(keycode == '13'){
            			$.getJSON("<c:url value = '/rest/location/find/code/' />"+$(this).val(), {}, function (location) {
                			if(location)
                				setLocation(location.id,location.name,location.code);
             	    	});
            	}
            });
	    });
	 	function setPosition(lat, lng){
	 		$('#txtLatitude').val(lat);
			$('#txtLongitude').val(lng);
			$.fancybox.close();
		}
	 	function setLocation(locationId,locationName,locationCode){
        	$('#hfieldLocationId').val(locationId);
            $('#txtLocationCode').val(locationCode);
            $('#lblLocationTitle').html(locationName);
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
            $('#txtLocationCode').val();
            $('#lblLocationTitle').html('');
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
            $('#hfieldLocationId').val(entity.location.id);
            $('#txtLocationCode').val(entity.location.code);
            $('#lblLocationTitle').html(entity.location.name);
        }
        function setEntityFromInput(entity) {
            baseSetEntityFromInput(entity);
            entity.location.id= $('#hfieldLocationId').val();    
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
