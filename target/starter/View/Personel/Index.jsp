<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
    <title></title>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <%@ include file="/View/Head.jsp" %>
    <%@ include file="/View/DateHead.jsp" %>
    <%@ include file="/View/FancyHead.jsp" %>
 	
 	
    <script language="javascript" type="text/javascript">
        var pageSize = 10;
        var pageNo = 1;
        var order = 'e.personCode desc';
        var resultNum = 0;
        var currentId = -1;
        var emptyEntity = { id: -1,
                			personCode: "",firstName: "", lastName: "", fatherName: "",
                			nationalityNumber: "",idNumber: "",birthDate: "",maritalStatus:true,gender:true,
                			licenseNumber: "",phoneNumber: "",mobile: "",address:	"",accountNumber: "",description: "",
                			location:{id:-1},bank:{id:-1},educationLevel:{id:-1},licenseType:{id:-1},actors:[]
        };
        var currentEntity = $.extend(true, {}, emptyEntity); 
        var restUrl="<c:url value = '/rest/personel' />";
        $(function () {
        	JsonData = { searchFilter: "", order: "", pageNumber: 0, pageSize: 0 };
            fillCombo("cmbEducationLevel"	, 	"<c:url value = '/rest/baseinformation/list/1' />", JsonData, "id", "topic",".....");
            fillCombo("cmbBank"				, 	"<c:url value = '/rest/baseinformation/list/2' />", JsonData, "id", "topic",".....");
            fillCombo("cmbLicenseType"		,	"<c:url value = '/rest/baseinformation/list/3' />", JsonData, "id", "topic",".....");
            fillCheckBoxList();
            $('#txtPersonCode').bind('blur', validateEntity);
            $('#txtBirthDate').datepicker({dateFormat: 'yy/mm/dd',showButtonPanel: true});
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
        function setLocation(locationId,locationName,locationCode){
        	$('#hfieldLocationId').val(locationId);
            $('#txtLocationCode').val(locationCode);
            $('#lblLocationTitle').html(locationName);
            $.fancybox.close();
        }
        function fillCheckBoxList() {
    	    Loader(true);
    	    JsonData = { searchFilter: "", order: "", pageNumber: 0, pageSize: 0 };
    	    $.getJSON("<c:url value = '/rest/baseinformation/list/8' />", JsonData, function (entities) {
    	        $('#checkBoxTemplatePersonelActor').tmpl(entities.entityList).appendTo('#divPersonelActor');
            	Loader(false);
    	    });
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
            entitiesCache = {};
            JsonData = { searchFilter: getSearchFilter(), order: order, pageNumber: pageNo, pageSize: pageSize };
            $.getJSON(restUrl+"/list", JsonData, function (entities) {
                showElements(new Array('table_content'));
                resultNum = entities.totalRecords;
                $('table.grid tbody tr').remove();
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
        				success:function(res){fillTable(); Loader(false);}
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
				error:function(){showMessage('<spring:message code="UI.Personel.PersonCodeIsExist" />');}
            });
        }
        function validateEntity() {
            if (this.value.length > 0) {
                Loader(true);
                $.getJSON(restUrl+"/validatePersonel/"+$('#id').val()+"/"+this.value, function (data) {
                    if (!data) {
                    	showMessage('<spring:message code="UI.Personel.PersonCodeIsExist" />');
                        $('#txtPersonCode').css('background-color','red');
                        $('#btnSaveEntity').attr('disabled', 'disabled');
                    }
                    else {
                    	$('#txtPersonCode').css('background-color','');
                        $('#btnSaveEntity').removeAttr('disabled');
                    }
                    Loader(false);
                });
            }
        }
        function clearEntity() {
            currentId = -1;
            setInputByEntity(emptyEntity);
            $(".PersonelActorCheckBoxItem").each(function(){
    			 $(this).removeAttr("Checked","Checked");
    		});
            $('#txtLocationCode').val();
            $('#lblLocationTitle').html('');
            $('#txtPersonCode').css('background-color','');
            $('#btnSaveEntity').removeAttr('disabled');
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
            $.each(entity.actors,function(i,actor){
    			$("#actorItem"+actor.id).attr("Checked","Checked");
    		}); 

			if(entity.maritalStatus)
				$('#radioMaritalStatusSingle').attr('checked', 'checked');
			
			if(entity.gender)
            	$('#radioGenderMale').attr('checked', 'checked');

			setComboValueFromObject('#cmbBank',entity.bank);
			setComboValueFromObject('#cmbEducationLevel',entity.educationLevel);
			setComboValueFromObject('#cmbLicenseType',entity.licenseType); 
            
            $('#hfieldLocationId').val(entity.location.id);
            $('#txtLocationCode').val(entity.location.code);
            $('#lblLocationTitle').html(entity.location.name);
        }
        function setEntityFromInput(entity) {
            baseSetEntityFromInput(entity);
            entity.actors=[];
    		$(".PersonelActorCheckBoxItem:checked ").each(function(){
    			entity.actors.push({ 'id': $(this).val()});
    		});
            entity.maritalStatus = $('#radioMaritalStatusSingle').is(':checked');
            entity.gender = $('#radioGenderMale').is(':checked');
            
            entity.bank=getJsonObjectByComboValue('#cmbBank');
            entity.educationLevel=getJsonObjectByComboValue('#cmbEducationLevel');
            entity.licenseType=getJsonObjectByComboValue('#cmbLicenseType');
            
            entity.location.id= $('#hfieldLocationId').val();    
        }
        
    </script>
</head>
<body onload="pageLoad()">
    <form id="FormMain" >
            
			<%@ include file="Grid.jsp" %>	
			<%@ include file="Edit.jsp" %>
    </form>
   <sec:authorize access="IS_AUTHENTICATED_ANONYMOUSLY" >
   	YES, you are logged in!
	</sec:authorize>
</body>
</html>
