<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<script  language="javascript" type="text/javascript">
	var lineStation={
		entity:{id:0,line:{id:0},station:{id:0},ordering:0,goBack:0,betweenTimeMinute:0},
		restUrl:"<c:url value = '/rest/baseinfo/lineStation' />",
		loadEntity:function(id){
		 	Loader(true);
            $.getJSON(lineStation.restUrl+"/load/"+id, function (entity) {
            	$('#LineStationId').val(entity.id);
    			$('#cmbStation').val(entity.station.id);
   				$('#txtOrdering').val(entity.ordering);
    			$('#cmbGoBack').val(entity.goBack);
    			$('#txtBetweenTimeMinute').val(entity.betweenTimeMinute);
                Loader(false);
            });
		},
		deleteEntity:function(id){
			if (confirm('<spring:message code="UI.General.DeleteMessage" />') && id>0) {
                Loader(true);
                $.ajax({
    				type:"DELETE",
    				url	:lineStation.restUrl+"/delete/"+id,
    				contentType:"application/json;",
    				dataType:"json",
    				success:function(res){lineStation.fillGrid(); Loader(false);},
    				error:function(res){showMessage('<spring:message code="UI.General.CanNotDelete" />');Loader(false);}
                });
            }
		},
		fillGrid:function(){
            $.getJSON(lineStation.restUrl+"/list/"+$('#id').val(), {}, function (entities) {
                $('#gridLineStationBody tr').remove();
                $('#LineStationGridRowTemplate').tmpl(entities).appendTo('#gridLineStationBody');
                Loader(false);
            });
		},
		clearEntity:function(){
			lineStation.entity={id:0,line:{id:0},station:{id:0},ordering:0,goBack:0};
			$('#LineStationId').val(0);
			$('#cmbStation').val(-1);
			$('#txtOrdering').val(0);
			$('#cmbGoBack').val(0);
			$('#txtBetweenTimeMinute').val(0);
		},
		saveEntity:function(){
			lineStation.entity.id=$('#LineStationId').val();
			lineStation.entity.line.id=$('#id').val();
			lineStation.entity.station.id=$('#cmbStation').val();
			lineStation.entity.ordering=$('#txtOrdering').val();
			lineStation.entity.goBack=$('#cmbGoBack').val();
			lineStation.entity.betweenTimeMinute=$('#txtBetweenTimeMinute').val();
			Loader(true);  
            $.ajax({
				type:"POST",
				url	:lineStation.restUrl+"/save",
				data:JSON.stringify(lineStation.entity),
				contentType:"application/json;",
				dataType:"json",
				success:function(res){lineStation.fillGrid();Loader(false);lineStation.clearEntity();},
				error:function(res){showMessage('<spring:message code="UI.General.DuplicateCode" />');Loader(false);}
            });
		}
	};

</script>
<form id="FormLineStation">
<input id="LineStationId" type="hidden" value="0"  />
<table width="100%">
	<tr>
		<td width="50%" style="vertical-align: top;">
			<table width="100%">
				<tr>
					<td><spring:message code="UI.Line.LineStation" /></td>
					<td>
						 <select  id="cmbStation" >
				         </select> 
					</td>
				</tr>
				<tr>
					<td><spring:message code="UI.Line.LineStation.IsGoBack" /></td>
					<td>
						 <select  id="cmbGoBack">
                          	<option value="1"><spring:message code="UI.Line.LineStation.Back" /></option>
                           	<option value="2"><spring:message code="UI.Line.LineStation.Go" /></option>
                         </select> 
					</td>
				</tr>
				<tr>
					<td><spring:message code="UI.Line.LineStation.Order" /></td>
					<td> <input id="txtOrdering"  type="text" class="text validate[required,custom[integer]]" style="direction:ltr;width: 70px"  /></td>
				</tr>
				<tr>
					<td><spring:message code="UI.Line.LineStation.BetweenTimeMinute" /></td>
					<td> <input id="txtBetweenTimeMinute"  type="text" class="text validate[required,custom[integer]]" style="direction:ltr;width: 70px"  /></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button"  value='<spring:message code="UI.General.Save"  />'  class="actionBtn"  onclick="lineStation.saveEntity();"/>&nbsp;&nbsp;
						<input type="button"  value='<spring:message code="UI.General.Back"  />'  class="actionBtn"  onclick="lineStation.clearEntity();"  />
					</td>
				</tr>
			</table>
		</td>
		<td style="vertical-align: top;">
			<table id="gridLineStation" class="grid" width="100%" align="center">
                <thead>
                    <tr class="grid_header">
                        <th nowrap="nowrap">
                             <spring:message code="UI.Line.LineStation" />
                        </th>
                        
                        <th nowrap="nowrap">
                             <spring:message code="UI.Line.LineStation.IsGoBack" />
                        </th>
                        <th nowrap="nowrap">
                             <spring:message code="UI.Line.LineStation.Order" />
                         </th>
                         <th nowrap="nowrap">
							<spring:message code="UI.General.Edit" />
                        </th>
                        <th nowrap="nowrap" >
							<spring:message code="UI.General.Delete" />
                        </th>
                    </tr>
                </thead>
                <tbody id="gridLineStationBody">
                 			<script id="LineStationGridRowTemplate" type="text/html"> 
											 <tr id="pattern${id}"  class="oddRow" >
											        <td align="right">
											            <span >${station.name}</span>
											        </td>
											        	
 													<td align="right">
															{{if goBack==1}}
																	<spring:message code="UI.Line.LineStation.Back" />
 															{{else}}
																	<spring:message code="UI.Line.LineStation.Go" />
    														{{/if}}
											        </td>
													<td align="center">
											            <span >${ordering}</span>
											        </td>										         
       												<td align="center">
											            <span onclick="lineStation.loadEntity(${id})"   style="cursor:pointer;"> <img src="<c:url value = '/Content/images/edit.png' />" /> </span>
											        </td>
     											    <td align="center">
											            <span onclick="lineStation.deleteEntity(${id})"  style="cursor:pointer;"><img src="<c:url value = '/Content/images/delete.gif' />" /> </span>
											        </td>
											    </tr>
							</script>
             		</tbody>
         </table>
		</td>
		
	</tr>
</table>
</form>