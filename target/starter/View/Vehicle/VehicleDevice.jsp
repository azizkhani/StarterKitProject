<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script  language="javascript" type="text/javascript">
	var vehicleDevice={
		entity:{id:0,vehicle:{id:0},driver:{id:0},assignDate:0,description:""},
		restUrl:"<c:url value = '/rest/baseinfo/vehicle/vehicleDevice' />",
		loadEntity:function(id){
		 	Loader(true);
            $.getJSON(vehicleDevice.restUrl+"/load/"+id, function (entity) {
    			$('#txtAssignDate').val(entity.assignDate);
    			$('#hfieldDeviceId').val(entity.driver.id);
                $('#txtDeviceAutoComplete').val(entity.driver.personCode);
                Loader(false);
            });
		},
		deleteEntity:function(id){
			if (confirm('<spring:message code="UI.General.DeleteMessage" />') && id>0) {
                Loader(true);
                $.ajax({
    				type:"DELETE",
    				url	:vehicleDevice.restUrl+"/delete/"+id,
    				contentType:"application/json;",
    				dataType:"json",
    				success:function(res){vehicleDevice.fillGrid(); Loader(false);},
    				error:function(res){showMessage('<spring:message code="UI.General.CanNotDelete" />');Loader(false);}
                });
            }
		},
		fillGrid:function(){
            $.getJSON(vehicleDevice.restUrl+"/list/"+$('#id').val(), {}, function (entities) {
                $('#gridVehicleDeviceBody tr').remove();
                $('#vehicleDeviceGridRowTemplate').tmpl(entities).appendTo('#gridVehicleDeviceBody');
                Loader(false);
            });
		},
		clearEntity:function(){
			vehicleDevice.entity={id:0,vehicle:{id:0},driver:{id:0},assignDate:0,description:""};
			$('#vehicleDeviceId').val(0);
			$('#txtAssignDate').val('');

			
			$('#hfieldDriverId').val(0);
            $('#txtAutoComplete').val('');

		},
		saveEntity:function(){
			vehicleDevice.entity.vehicle.id=$('#id').val();
			vehicleDevice.entity.driver.id=$('#hfieldDeviceId').val();
			vehicleDevice.entity.assignDate=$('#txtAssignDate').val();

			Loader(true);  
            $.ajax({
				type:"POST",
				url	:vehicleDevice.restUrl+"/save",
				data:JSON.stringify(vehicleDevice.entity),
				contentType:"application/json;",
				dataType:"json",
				success:function(res){vehicleDevice.fillGrid();Loader(false);vehicleDevice.clearEntity();},
				error:function(res){showMessage('<spring:message code="UI.General.DuplicateCode" />');Loader(false);}
            });
		}
	};

</script>
<form id="FormvehicleDevice">
<input id="vehicleDeviceId" type="hidden" value="0"  />
<table width="100%">
	<tr>
		<td width="50%" style="vertical-align: top;">
			<table width="100%">
				<tr>
                	<td class="fieldTitle" align="left">
                       <span style="color:red"> <spring:message code="UI.Device.Code" /></span> &nbsp;:
                    </td>
                    <td class="fieldValue" align="right">
                        <input name="autoComplete" id="txtDeviceAutoComplete" type="text" class="text validate[required,custom[integer]]" size="40" />
                        <input type="hidden" id="hfieldDeviceId" value="-1" />
                    </td>
                </tr>
                 <tr>
                    <td class="fieldTitle" align="left">
                       <span style="color:red"><spring:message code="UI.Device.DeviceHistory.installDateTime" /></span> &nbsp;:
                    </td>
                    <td class="fieldValue" align="right">
                        <input name="installDateTime" id="txtinstallDateTime"  type="text" class="persianDate text validate[required]" size="40" />
                    </td>
                </tr>
                <tr>
                     <td class="fieldTitle" align="left">
                        <span style="color:red"><spring:message code="UI.Device.DeviceHistory.VehicleSetupLocation" /></span> &nbsp;:
                     </td>
                     <td class="fieldValue" align="right">
                      	<select  id="cmbVehicleSetupLocation" class="validate[required]">
                         </select>
                     </td>
                 </tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button"  value='<spring:message code="UI.General.Save"  />'  class="actionBtn"  onclick="vehicleDevice.saveEntity();"/>&nbsp;&nbsp;
						<input type="button"  value='<spring:message code="UI.General.Back"  />'  class="actionBtn"  onclick="vehicleDevice.clearEntity();"  />
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;">
			<table id="gridVehicleDevice" class="grid" width="100%" align="center">
                <thead>
                    <tr class="grid_header">
                        <th nowrap="nowrap">
                             <spring:message code="UI.Device.Code" />
                        </th>
                        <th nowrap="nowrap">
                             <spring:message code="UI.Device.DeviceHistory.VehicleSetupLocation" />
                        </th>
                        <th nowrap="nowrap">
                             <spring:message code="UI.Device.DeviceHistory.installDateTime" />
                        </th>
                         <th nowrap="nowrap">
							<spring:message code="UI.General.Edit" />
                        </th>
                        <th nowrap="nowrap" >
							<spring:message code="UI.General.Delete" />
                        </th>
                    </tr>
                </thead>
                <tbody id="gridVehicleDeviceBody">
                	<script id="vehicleDeviceGridRowTemplate" type="text/html"> 
							 <tr id="pattern${id}"  class="oddRow" >
									<td align="right">
										${device.code}
							        </td>
									<td align="center">
							            ${vehicleSetupLocation}
							        </td>
									<td align="center">
							            ${installDateTime}
							        </td>	
   									<td align="center">
							            <span onclick="vehicleDevice.loadEntity(${id})"   style="cursor:pointer;"> <img src="<c:url value = '/Content/images/edit.png' />" /> </span>
							        </td>
 									<td align="center">
							            <span onclick="vehicleDevice.deleteEntity(${id})"  style="cursor:pointer;"><img src="<c:url value = '/Content/images/delete.gif' />" /> </span>
							        </td>
							    </tr>
					</script>	
             	</tbody>
         </table>
		</td>
		
	</tr>
</table>
</form>