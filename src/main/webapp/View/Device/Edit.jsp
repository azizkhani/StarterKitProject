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
                                <td style="vertical-align: top;" width="100%" align="center">
				                      <table cellpadding="2px" width="100%">
				                         	<tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Device.Code" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="code" id="txtCode"  type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Device.Devicegroup" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                	<input  id="txtDeviceGroupCode" type="text" class="text validate[required]"   style="width: 40px" />
				                                	<input id="btnSelectDeviceGroup" type="button" value="...">
				                                	<span id="lblDeviceGroupTitle" style="color:blue"></span>
				                                	<input id="hfieldDeviceGroupId" type="hidden" class="validate[required]" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Device.Model" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbModel" class="validate[required]">
				                                    </select>
				                                </td>
				                            </tr>
				                             <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Device.Mark" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbMark" class="validate[required]">
				                                    </select>
				                                </td>
				                            </tr>
				                             <tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Device.Description" /> &nbsp;:
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
