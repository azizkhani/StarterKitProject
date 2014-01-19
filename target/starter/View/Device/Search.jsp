<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<div class="search_form"  style="width: 100%">
    <table class="search_table" cellpadding="8" align="center" width="99%">
            <tr>
                <td align="center">
                            <table cellpadding="2px">
                                <tr>
                                    <td class="fieldTitle" align="left">
                                        کد :
                                    </td>
                                    <td class="fieldValue" align="right">
                                        <input  type="text" class="text search" searchProperty="e.code"  size="40" />
                                        <select class="searchPattern" searchProperty="e.code" style="width: 150px">
                                            <option value="1"><spring:message code="UI.General.Search.isEqual"  /></option>
                                            <option value="2"><spring:message code="UI.General.Search.isNotEqual"  /></option>
                                            <option value="7"><spring:message code="UI.General.Search.IncludeThisValue"  /></option>
                                            <option value="8"><spring:message code="UI.General.Search.BeginValue"  /></option>
                                            <option value="9"><spring:message code="UI.General.Search.EndValue"  /></option>
                                            <option value="10"><spring:message code="UI.General.Search.NotLike"  /></option>
                                        </select>
                                        
                                    </td>
                                    <td>
                                     <input type="button" value=" <spring:message code="UI.General.Search"  />" onclick="search()" class="actionBtn" />
					                    &nbsp; &nbsp;
					                    <input type="button" value="پاک کردن" onclick="clearFilter();search();" class="actionBtn" />
                                    </td>
                                </tr>
                            </table>
                   
                   
                </td>
            </tr>
        </table>
    
</div>

