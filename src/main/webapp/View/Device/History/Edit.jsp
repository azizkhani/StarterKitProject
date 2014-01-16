<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
    <br>
     <div style="width: 96%;height: 98%" id="tabsList">
    <table width="96%">
        <tr>
            <td align="center" width="100%" class="plain">
                <div id="tabContents">
                    <div id="main_infoDiv">
                     	<table cellpadding="2px" width="100%">
                         	<tr>
                                <td style="vertical-align: top;" width="100%">
				                      <table cellpadding="2px" align="center">
				                         	<tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Device.DeviceHistory.simCard" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="simCard" id="txtsimCard"  type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                           <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Device.DeviceHistory.softwareVersion" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="softwareVersion" id="txtsoftwareVersion"  type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Device.DeviceHistory.softwareUpdateDateTime" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="softwareUpdateDateTime" id="txtsoftwareUpdateDateTime"  type="text" class="persianDate text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Device.DeviceHistory.sendDateTime" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="sendDateTime" id="txtsendDateTime"  type="text" class="persianDate text validate[required]" size="40" />
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
				                                   <span style="color:red"><spring:message code="UI.Device.DeviceHistory.historyDateTime" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="historyDateTime" id="txthistoryDateTime"  type="text" class="persianDate text validate[required]" size="40" />
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
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Device.DeviceHistory.vehicle" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbVehicle" class="validate[required]">
				                                    </select>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Device.DeviceHistory.location" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                	<input  id="txtLocationCode" type="text" class="text validate[required]"   style="width: 40px" />
				                                	<input id="btnSelectLocation" type="button" value="...">
				                                	<span id="lblLocationTitle" style="color:blue"></span>
				                                	<input id="hfieldLocationId" type="hidden" class="validate[required]" />
				                                </td>
				                            </tr>
				                            <tr>
                                				<td class="fieldTitle" align="left">
			                                    <span style="color:red"> <spring:message code="UI.Device.DeviceHistory.responsible" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="autoComplete" id="txtAutoComplete" type="text" class="text validate[required,custom[integer]]" size="40" />
				                                    <input type="hidden" id="hfieldResponsibleId" value="-1" />
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
				                                    <spring:message code="UI.Device.DeviceHistory.description" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                	<textarea name="description" id="txtDescription"  class="text Property" rows="2" style="width: 200px" ></textarea>                    
				                                </td>
				                            </tr>
				                         </table>
                                </td>
				            </tr>
                         </table>
                            <input type="button"  value='<spring:message code="UI.General.Save"  />'    onclick="saveEntity()"   class="actionBtn" id="btnSaveEntity"/>
			             	<input type="button"  value='<spring:message code="UI.General.Refresh"  />' onclick="refreshForm()"  class="actionBtn" />
			              	<input type="button"  value='<spring:message code="UI.General.New"  />'     onclick="clearEntity()"  class="actionBtn" />
			             	<input type="button"  value='<spring:message code="UI.General.Back"  />'    onclick="showListPage()" class="actionBtn" />
                    </div>
                </div>
            </td>
        </tr>
    </table>
    </div>
</div>
