<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<script  language="javascript" type="text/javascript">
	
	var vehicleDriver={
		entity:{id:0,vehicle:{id:0},driver:{id:0},assignDate:0,description:""},
		restUrl:"<c:url value = '/rest/baseinfo/vehicleDriver' />",
		loadEntity:function(id){
		 	Loader(true);
            $.getJSON(vehicleDriver.restUrl+"/load/"+id, function (entity) {
            	$('#vehicleDriverId').val(entity.id);
    			$('#txtAssignDate').val(entity.assignDate);
    			$('#txtDesc').val(entity.description);
    			$('#hfieldDriverId').val(entity.driver.id);
                $('#txtAutoComplete').val(entity.driver.personCode);
                $('#txtFullName').html(entity.driver.fullName);
                Loader(false);
            });
		},
		deleteEntity:function(id){
			if (confirm('<spring:message code="UI.General.DeleteMessage" />') && id>0) {
                Loader(true);
                $.ajax({
    				type:"DELETE",
    				url	:vehicleDriver.restUrl+"/delete/"+id,
    				contentType:"application/json;",
    				dataType:"json",
    				success:function(res){vehicleDriver.fillGrid(); Loader(false);},
    				error:function(res){showMessage('<spring:message code="UI.General.CanNotDelete" />');Loader(false);}
                });
            }
		},
		fillGrid:function(){
            $.getJSON(vehicleDriver.restUrl+"/list/"+$('#id').val(), {}, function (entities) {
                $('#gridvehicleDriverBody tr').remove();
                $('#vehicleDriverGridRowTemplate').tmpl(entities).appendTo('#gridvehicleDriverBody');
                Loader(false);
            });
		},
		clearEntity:function(){
			vehicleDriver.entity={id:0,vehicle:{id:0},driver:{id:0},assignDate:0,description:""};
			$('#vehicleDriverId').val(0);
			$('#txtAssignDate').val('');
			$('#txtDesc').val('');
			
			$('#hfieldDriverId').val(0);
            $('#txtAutoComplete').val('');
            $('#txtFullName').html('');
		},
		saveEntity:function(){
			vehicleDriver.entity.id=$('#vehicleDriverId').val();
			vehicleDriver.entity.vehicle.id=$('#id').val();
			vehicleDriver.entity.driver.id=$('#hfieldDriverId').val();
			vehicleDriver.entity.assignDate=$('#txtAssignDate').val();
			vehicleDriver.entity.description=$('#txtDescription').val();
			Loader(true);  
            $.ajax({
				type:"POST",
				url	:vehicleDriver.restUrl+"/save",
				data:JSON.stringify(vehicleDriver.entity),
				contentType:"application/json;",
				dataType:"json",
				success:function(res){vehicleDriver.fillGrid();Loader(false);vehicleDriver.clearEntity();},
				error:function(res){showMessage('<spring:message code="UI.General.DuplicateCode" />');Loader(false);}
            });
		}
	};

</script>
<form id="FormvehicleDriver">
<input id="vehicleDriverId" type="hidden" value="0"  />
<table width="100%">
	<tr>
		<td width="50%" style="vertical-align: top;">
			<table width="100%">
				<tr>
                	<td class="fieldTitle" align="left">
                       <span style="color:red"> <spring:message code="UI.Vehicle.VehicleDriver.Driver" /></span> &nbsp;:
                    </td>
                    <td class="fieldValue" align="right">
                        <input name="autoComplete" id="txtAutoComplete" type="text" class="text validate[required,custom[integer]]" size="40" />
                        <input type="hidden" id="hfieldDriverId" value="-1" />
                    </td>
                </tr>
               <tr>
                    <td class="fieldTitle" align="left">
                    </td>
                    <td class="fieldValue" align="right">
                        <span id="txtFullName" style="color:blue" ></span>
                    </td>
                </tr>
                 <tr>
                    <td class="fieldTitle" align="left">
                        <span style="color:red"><spring:message code="UI.Vehicle.VehicleDriver.AssignDate" /></span> &nbsp;:
                    </td>
                    <td class="fieldValue" align="right">
                        <input  id="txtAssignDate" type="text" class="text persianDate" style="direction:ltr" size="40" />
                    </td>
                </tr>  
				<tr>
                    <td class="fieldTitle" align="left">
                        <spring:message code="UI.Vehicle.VehicleDriver.Description" /> &nbsp;:
                    </td>
                    <td class="fieldValue" align="right">
                    	<textarea  id="txtDesc"  class="text Property" rows="2" style="width: 200px" ></textarea>                    
                    </td>
                </tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button"  value='<spring:message code="UI.General.Save"  />'  class="actionBtn"  onclick="vehicleDriver.saveEntity();"/>&nbsp;&nbsp;
						<input type="button"  value='<spring:message code="UI.General.Back"  />'  class="actionBtn"  onclick="vehicleDriver.clearEntity();"  />
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;">
			<table id="gridvehicleDriver" class="grid" width="100%" align="center">
                <thead>
                    <tr class="grid_header">
                        <th nowrap="nowrap">
                             <spring:message code="UI.Vehicle.VehicleDriver.Driver" />
                        </th>
                        
                        <th nowrap="nowrap">
                             <spring:message code="UI.Vehicle.VehicleDriver.AssignDate" />
                        </th>
                         <th nowrap="nowrap">
							<spring:message code="UI.General.Edit" />
                        </th>
                        <th nowrap="nowrap" >
							<spring:message code="UI.General.Delete" />
                        </th>
                    </tr>
                </thead>
                <tbody id="gridvehicleDriverBody">
                 			<script id="vehicleDriverGridRowTemplate" type="text/html"> 
											 <tr id="pattern${id}"  class="oddRow" >
 													<td align="right">
															${driver.fullName}
											        </td>
													<td align="center">
											            <span >${assignDate}</span>
											        </td>										         
       												<td align="center">
											            <span onclick="vehicleDriver.loadEntity(${id})"   style="cursor:pointer;"> <img src="<c:url value = '/Content/images/edit.png' />" /> </span>
											        </td>
     											    <td align="center">
											            <span onclick="vehicleDriver.deleteEntity(${id})"  style="cursor:pointer;"><img src="<c:url value = '/Content/images/delete.gif' />" /> </span>
											        </td>
											    </tr>
							</script>
             		</tbody>
         </table>
		</td>
		
	</tr>
</table>
</form>