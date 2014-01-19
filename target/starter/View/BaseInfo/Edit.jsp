<div class="edit_form" id="edit_form" style="width: 100%" align="center">
   <br>
   <br>
   <br>
   <br>
   <br>
   <br>
    <input type="hidden" id="id" value="-1" />
    <table class="plain">
        <tr>
            <td class="fieldTitle" align="left">
                <span style="color:red"><spring:message code="UI.BaseInformation.Code"  /></span> &nbsp;:
            </td>
            <td class="fieldValue" align="right">
                <input name="code" id="txtCode" type="text" class="validate[required,custom[integer]] " maxlength="4" style="width:50px"  />
            </td>
        </tr>
        <tr>
            <td class="fieldTitle"  align="left">
                <span style="color:red"><spring:message code="UI.BaseInformation.Topic"  /></span> &nbsp;:
            </td>
            <td class="fieldValue" align="right">
                <input name="topic" id="txtTopic" type="text" class="text validate[required]" maxlength="50" style="width:400px"   />
            </td>
        </tr>
    </table>
    <input type="button"  value='<spring:message code="UI.General.Save"  />' 	onclick="saveEntity()" class="actionBtn"  id="BtnSaveEntity"/>
    <input type="button"  value='<spring:message code="UI.General.Refresh"  />' onclick="refreshForm()" class="actionBtn" />
    <input type="button"  value='<spring:message code="UI.General.New"  />' 	onclick="clearEntity()" class="actionBtn" />
    <input type="button"  value='<spring:message code="UI.General.Back"  />' 	onclick="showListPage()" class="actionBtn" />
</div>
