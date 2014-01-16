<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script  language="javascript" type="text/javascript">
	var vehicleLine={
		restUrl:"<c:url value = '/rest/baseinfo/vehicle' />",
		removeLine:function(lineId){
			if (confirm('<spring:message code="UI.General.DeleteMessage" />') && lineId>0) {
                Loader(true);
                $.ajax({
    				type:"POST",
    				url	:vehicleLine.restUrl+"/removeLine/"+$('#id').val()+"/"+lineId,
    				contentType:"application/json;",
    				dataType:"json",
    				success:function(res){vehicleLine.fillGrid(); Loader(false);},
    				error:function(res){showMessage('<spring:message code="UI.General.CanNotDelete" />');Loader(false);}
                });
            }
		},
		fillGrid:function(){
            $.getJSON(vehicleLine.restUrl+"/lineList/"+$('#id').val(), {}, function (entities) {
                $('#gridvehicleLineBody tr').remove();
                $('#vehicleLineGridRowTemplate').tmpl(entities).appendTo('#gridvehicleLineBody');
                Loader(false);
            });
		},
		clearEntity:function(){
		},
		addLine:function(){
			Loader(true);  
            $.ajax({
				type:"POST",
				url	:vehicleLine.restUrl+"/addLine/"+$('#id').val()+"/"+$('#cmbLine').val(),
				contentType:"application/json;",
				dataType:"json",
				success:function(res){vehicleLine.fillGrid();Loader(false);vehicleLine.clearEntity();},
				error:function(res){showMessage('<spring:message code="UI.General.DuplicateCode" />');Loader(false);}
            });
		}
	};

</script>
<form id="FormvehicleLine">
<input id="vehicleLineId" type="hidden" value="0"  />
<table width="100%">
	<tr>
		<td width="50%" style="vertical-align: top;">
			<table width="100%">
               <tr>
                    <td class="fieldTitle" align="left">
                    	<spring:message code="UI.line"  />
                    </td>
                    <td class="fieldValue" align="right">
                       <select  id="cmbLine" >
                       </select>
                    </td>
                </tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button"  value='<spring:message code="UI.General.Save"  />'  class="actionBtn"  onclick="vehicleLine.addLine();"/>&nbsp;&nbsp;
						<input type="button"  value='<spring:message code="UI.General.Back"  />'  class="actionBtn"  onclick="vehicleLine.clearEntity();"  />
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;">
			<table id="gridvehicleLine" class="grid" width="100%" align="center">
                <thead>
                    <tr class="grid_header">
                        <th nowrap="nowrap">
                             <spring:message code="UI.Line.Code" />
                        </th>
                        
                        <th nowrap="nowrap">
                             <spring:message code="UI.Line.Name" />
                        </th>
                        <th nowrap="nowrap" >
							<spring:message code="UI.General.Delete" />
                        </th>
                    </tr>
                </thead>
                <tbody id="gridvehicleLineBody">
                 			<script id="vehicleLineGridRowTemplate" type="text/html"> 
											 <tr id="pattern${id}"  class="oddRow" >
 													<td align="right">
														${code}
											        </td>
													<td align="center">
											            ${name}
											        </td>										         
     											    <td align="center">
											            <span onclick="vehicleLine.removeLine(${id})"  style="cursor:pointer;"><img src="<c:url value = '/Content/images/delete.gif' />" /> </span>
											        </td>
											    </tr>
							</script>
             		</tbody>
         </table>
		</td>
		
	</tr>
</table>
</form>