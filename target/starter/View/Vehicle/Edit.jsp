
<div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
        <br>
     <div style="width: 96%;height: 98%" id="tabsList">
     <ul  style="width: 90%; padding-right: 20px;">
            <li><a  href="#main_infoDiv" id="mainTab"> <spring:message code="UI.General.MainInformationTab"  /> </a></li>
            <li><a  href="#driver_infoDiv" > <spring:message code="UI.Vehicle.Driver"  /> </a></li>
            <li><a  href="#line_infoDiv" > <spring:message code="UI.Line.List"  /> </a></li>
            <li><a  href="#device_infoDiv" > <spring:message code="UI.Vehicle.Device"  /> </a></li>
     </ul>
    <table width="96%">
        <tr>
            <td align="center" width="100%" class="plain">
                <div id="tabContents">
                    <div id="main_infoDiv">
					    <table width="99%" align="center">
					        <tr>
					            <td align="center" width="50%" >
					                     	<table cellpadding="2px" width="100%" align="center">
					                         	<tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.Code" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="code" id="txtCode"  type="text" class="text validate[required]" size="40" />
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.Plaque" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="plaque1" id="txtPlaque4" type="text" class="text validate[required]" style="width:20px;"  />
					                                    iran
					                                    <input name="plaque2" id="txtPlaque3" type="text" class="text validate[required]" style="width:40px;" />
					                                    <input name="plaque3" id="txtPlaque2" type="text" class="text validate[required]" style="width:20px;" />
					                                    
					                                    <input name="plaque4" id="txtPlaque1" type="text" class="text validate[required]" style="width:20px;"/>
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <spring:message code="UI.Vehicle.Possession" /> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                     <select name="possession" id="cmbPossession" >
						                                     	<option value="1"><spring:message code="UI.Vehicle.Possession.Public" /></option>
						                                      	<option value="2"><spring:message code="UI.Vehicle.Possession.Private" /></option>
						                                      	<option value="3"><spring:message code="UI.Vehicle.Possession.Hybrid" /></option>
									                     </select>
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.EnginNumber" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="enginNumber" id="txtEnginNumber" type="text" class="text validate[required]" size="40" />
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.ChasisNumber" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="chasisNumber" id="txtChasisNumber" type="text" class="text validate[required]" size="40" />
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.ProductionYear" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="productionYear" id="txtProductionYear" type="text" class="text validate[required]" size="40" />
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.Capacity" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="capacity" id="txtCapacity" type="text" class="text validate[required]" size="40" />
					                                </td>
					                            </tr>
					                           
					                  
					                         </table>
									</td>
									  <td align="center" width="50%" >
					                     	<table cellpadding="2px" width="100%" align="center">
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <span style="color:red"><spring:message code="UI.Vehicle.AxisTotal" /></span> &nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <input name="axisTotal" id="txtAxisTotal" type="text" class="text validate[required]" size="40" />
					                                </td>
					                            </tr>
					                             <tr>
					                                <td class="fieldTitle" align="left">
					                                   <span style="color:black"><spring:message code="UI.Vehicle.FuelType" /></span>&nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <select  id="cmbFuelType">
					                                    </select>
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                   <span style="color:black"><spring:message code="UI.Vehicle.Model" /></span>&nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <select  id="cmbModel">
					                                    </select>
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                   <span style="color:black"><spring:message code="UI.Vehicle.Mark" /></span>&nbsp;:
					                                </td>
					                                <td class="fieldValue" align="right">
					                                    <select  id="cmbMark">
					                                    </select>
					                                </td>
					                            </tr>
					                            <tr>
					                                <td class="fieldTitle" align="left">
					                                    <spring:message code="UI.Vehicle.Description" /> &nbsp;:</span>
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
                    <div id="driver_infoDiv">
                     <%@ include file="VehicleDriver.jsp" %>
                    </div>
                     <div id="line_infoDiv">
                     <%@ include file="VehicleLine.jsp" %>
                    </div>
                     <div id="device_infoDiv">
                     <%@ include file="VehicleDevice.jsp" %>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    </div>
</div>
