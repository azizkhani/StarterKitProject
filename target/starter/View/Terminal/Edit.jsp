
<div class="edit_form" id="edit_form" style="width: 100%" align="center" class="plain">
    <input type="hidden" id="id" value="-1" />
    <br>
    <br>
           <table cellpadding="2px" width="99%" class="plain">
               <tr>
                  <td style="vertical-align: top;" width="50%">
                      <table cellpadding="2px">
                         	<tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Terminal.Code" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="code" id="txtCode"  type="text" class="text validate[required,custom[integer]]" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                     <span style="color:red"><spring:message code="UI.Terminal.Name" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="name" id="txtName"  type="text" class="text validate[required]" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                     <spring:message code="UI.Terminal.Latitude" /> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="latitude" id="txtLatitude"  type="text" class="text validate[required]" style="width: 80px" /><input   id="btnSelectPosition" type="button" value="...."/>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <spring:message code="UI.Terminal.Longitude" /> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                     <input name="longitude" id="txtLongitude"  type="text" class="text validate[required]"   style="width: 80px" />
                                </td>
                            </tr>
                             <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Terminal.LineTotal" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="lineTotal" id="txtLineTotal"  type="text" class="text validate[required,custom[integer]]" style="direction:ltr" size="40" />
                                </td>
                            </tr>
                     		<tr>
                                <td class="fieldTitle" align="left">
                                   <span style="color:red"><spring:message code="UI.Terminal.VehicleCapacity" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="vehicleCapacity" id="txtvehicleCapacity"  type="text" class="text validate[required,custom[integer]]" style="direction:ltr" size="40" />
                                </td>
                            </tr>  
                         </table>
                   </td>
                   <td  style="vertical-align: top;" width="50%">
                     <table>
                     		<tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Personel.Location" /></span>&nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                	<input  id="txtLocationCode" type="text" class="text validate[required,custom[integer]]"   style="width: 40px" />
                                	<input id="btnSelectLocation" type="button" value="...">
                                	<span id="lblLocationTitle" style="color:blue"></span>
                                	<input id="hfieldLocationId" type="hidden"  />
                                </td>
                            </tr>
                            <tr>
                            	<td class="fieldTitle" align="left">
                                   	<span style="color:red"><spring:message code="UI.Line.Responsible" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="autoComplete" id="txtAutoComplete" type="text" class="text validate[required]" style="width: 90px" />
                                    <input type="hidden" id="hfieldResponsibleId" value="-1" />
                                     <span style="color:blue" id="txtFullName" ></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                   <spring:message code="UI.Terminal.PhoneNumber" /> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="phoneNumber"  type="text" class="text Property" style="direction:ltr" size="40" />
                                </td>
                            </tr>
                             <tr>
                                <td class="fieldTitle" align="left">
                                    <spring:message code="UI.Terminal.Address" /> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">  
                                    <textarea name="address" id="txtAddress"  class="text Property" rows="2" style="width: 200px" ></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <spring:message code="UI.Terminal.Description" /> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                	<textarea name="description" id="txtDescription"  class="text Property" rows="2" style="width: 200px" ></textarea>                    
                                </td>
                            </tr>
                       </table>
                     
                 </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            
              			<input type="button"  value='<spring:message code="UI.General.Save"  />'    onclick="saveEntity()"   class="actionBtn" id="btnSaveEntity"/>
				      	<input type="button"  value='<spring:message code="UI.General.Refresh"  />' onclick="refreshForm()"  class="actionBtn" />
				       	<input type="button"  value='<spring:message code="UI.General.New"  />'     onclick="clearEntity()"  class="actionBtn" />
				      	<input type="button"  value='<spring:message code="UI.General.Back"  />'    onclick="showListPage()" class="actionBtn" />
            	</td>
            </tr>
         </table>
</div>
