<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
 <div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
    <br>
    <br>
    <table width="96%">
        <tr>
            <td align="center" width="100%" class="plain">
                <div id="tabContents">
                    <div id="main_infoDiv">
                        <table cellpadding="2px">
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma">نام گروه &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="groupName" id="txtGroupName" type="text" class="text Property" size="40" />
                                </td>
                            </tr>
                        </table>
                    </div>
                   
                </div>
                <input type="button" id="BtnWriteEntity" value="ثبت" onclick="writeEntity()" class="actionBtn" />
                <input type="button" value="بازخوانی" onclick="refreshForm()" class="actionBtn" />
                <input type="button" value="جدید" onclick="clearEntity()" class="actionBtn" />
                <input type="button" value="بازگشت" onclick="showListPage()" class="actionBtn" />
            </td>
        </tr>
    </table>
</div>
