<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
    <br>
     <div style="width: 96%;height: 98%" id="tabsList">
     <ul  style="width: 90%; padding-right: 20px;">
            <li><a  href="#main_infoDiv" id="mainTab"> <spring:message code="UI.General.MainInformationTab"  /> </a></li>
            <li><a  href="#other_infoDiv" > <spring:message code="UI.Line.LineStations"  /> </a></li>
    </ul>
    <table width="96%">
        <tr>
            <td align="center" width="100%" class="plain">
                <div id="tabContents">
                    <div id="main_infoDiv">
                     	<table cellpadding="2px" width="100%">
                         	<tr>
                                <td style="vertical-align: top;" width="50%">
				                      <table cellpadding="2px">
				                         	<tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Line.Code" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="code" id="txtCode"  type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Line.Name" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="name" id="txtName"  type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Line.TotalTimeGo" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="totalTimeGo" id="txtTotalTimeGo"  type="text" class="text validate[required,custom[integer]]" style="width: 50px" /> <span style="color:blue">دقیقه</span>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Line.TotalTimeBack" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="totalTimeBack" id="txtTotalTimeBack"  type="text" class="text validate[required,custom[integer]]"  style="width: 50px" /> <span style="color:blue">دقیقه</span>
				                                </td>
				                            </tr>
				                             <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Line.Tariff" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="tariff" id="txttariff"  type="text" class="text validate[required,custom[integer]]"  style="width: 50px" /> <span style="color:blue">ریال</span>
				                                </td>
				                            </tr>
				                            <tr>
                                				<td class="fieldTitle" align="left">
			                                    <span style="color:red"> <spring:message code="UI.Line.Responsible" /></span> &nbsp;:
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
				                         </table>
                                </td>
                                <td  style="vertical-align: top;" width="50%">
				                     <table>
				                      		<tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Line.Terminal" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbTerminal" class="validate[required]">
				                                    </select>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Line.Type" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                     <select name="type" id="cmbtype" class="validate[required]">
				                                     	<option value="1"><spring:message code="UI.Line.Type.Fast" /></option>
				                                      	<option value="2"><spring:message code="UI.Line.Type.Common" /></option>
				                                    </select>
				                                </td>
				                            </tr>
				                     		<tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Line.StartTime" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="startTime" id="txtStartTime" type="text" class="text Property" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>  
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Line.EndTime" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="endTime" id="txtEndTime"  type="text" class="text Property" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Line.Description" /> &nbsp;:
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
                    <div id="other_infoDiv">
                     <%@ include file="LineStation.jsp" %>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    </div>
</div>
