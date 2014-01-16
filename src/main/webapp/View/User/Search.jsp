
<div class="search_form" id="search_form" style="width: 100%">
        <table class="search_table" cellpadding="8" align="center" width="96%">
            <tr>
                <td colspan="3" align="center" class="h3">
                    جستجو
                </td>
            </tr>
            <tr>
                <td align="center">
                    <div id="searchTabContents">
                        <div id="searchmain_infoDiv">
                            <table cellpadding="2px">
                                <tr>
                                    <td class="fieldTitle" align="left">
                                        نام کاربری:
                                    </td>
                                    <td class="fieldValue" align="right">
                                        <input  type="text" class="text search" searchProperty="e.userName"  size="40" />
                                        <select class="searchPattern" searchProperty="e.userName" style="width: 150px">
                                            <option value="1"><spring:message code="UI.General.Search.isEqual"  /></option>
                                            <option value="2"><spring:message code="UI.General.Search.isNotEqual"  /></option>
                                            <option value="7"><spring:message code="UI.General.Search.IncludeThisValue"  /></option>
                                            <option value="8"><spring:message code="UI.General.Search.BeginValue"  /></option>
                                            <option value="9"><spring:message code="UI.General.Search.EndValue"  /></option>
                                            <option value="10"><spring:message code="UI.General.Search.NotLike"  /></option>
                                            <option value="11"><spring:message code="UI.General.Search.NotLikeBeginValue"  /></option>
                                            <option value="12"><spring:message code="UI.General.Search.NotLikeEndValue"  /></option>
                                        </select>
                                    </td>
                                </tr>
                               
                            </table>
                        </div>
                    </div>
                    <input type="button" value="جستجو" onclick="search()" class="actionBtn" />
                    &nbsp; &nbsp;
                    <input type="button" value="پاک کردن" onclick="clearFilter()" class="actionBtn" />&nbsp;
                    &nbsp;
                    <input type="button" value="بازگشت" onclick="showElements(new Array('table_content','parentTilte'))"
                        class="actionBtn" />
                </td>
            </tr>
        </table>
    
</div>

