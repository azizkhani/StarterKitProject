
<div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
    <br> <br>
    <table width="99%" align="center">
        <tr>
            <td align="center" width="100%" class="plain">
                     	<table cellpadding="2px" width="50%" align="center">
                         	<tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Station.Code" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="code" id="txtCode"  type="text" class="text validate[required]" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Station.Name" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="name" id="txtName" type="text" class="text validate[required]" size="40" />
                                </td>
                            </tr>
                             <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Personel.Location" /></span>&nbsp;:
                                </td>
                                <td class="fieldValue" align="right">
                                	<input  id="txtLocationCode" type="text" class="text validate[required]"   style="width: 40px" />
                                	<input id="btnSelectLocation" type="button" value="...">
                                	<span id="lblLocationTitle" style="color:blue"></span>
                                	<input id="hfieldLocationId" type="hidden" class="validate[required]" />
                                </td>
                            </tr>
                            
                             <tr>
				             	<td style="text-align: left"><span style="color:red"><spring:message code="UI.Location.latitude" /></span>&nbsp;: </td>
				             	<td>
				             		<input name="latitude"  id="txtLatitude"  style="width: 100px;" type="text" class="text Property validate[required]" />
				             		<input   id="btnSelectPosition" type="button" value="...."/>
				             	</td>
				             </tr>
				              <tr>
				             	<td style="text-align: left"><span style="color:red"><spring:message code="UI.Location.Longitude" /></span> &nbsp;:</td>
				             	<td><input name="longitude" id="txtLongitude" style="width: 100px;" type="text" class="text Property validate[required]"  /></td>
				             </tr>
				             <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="color:red"><spring:message code="UI.Station.Address" /></span> &nbsp;:
                                </td>
                                <td class="fieldValue" align="right">  
                                    <textarea name="address" id="txtAddress"  class="text validate[required]" rows="2" style="width: 200px" ></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma"><spring:message code="UI.Station.Description" /> &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                	<textarea name="description" id="txtDescription"  class="text Property" rows="2" style="width: 200px" ></textarea>                    
                                </td>
                            </tr>
                         </table>
                <input type="button"  value='<spring:message code="UI.General.Save"  />'    onclick="saveEntity()"   class="actionBtn" id="btnSaveEntity"/>
             	<input type="button"  value='<spring:message code="UI.General.Refresh"  />' onclick="refreshForm()"  class="actionBtn" />
              	<input type="button"  value='<spring:message code="UI.General.New"  />'     onclick="clearEntity()"  class="actionBtn" />
             	<input type="button"  value='<spring:message code="UI.General.Back"  />'    onclick="showListPage()" class="actionBtn" />
            </td>
        </tr>
    </table>
</div>
