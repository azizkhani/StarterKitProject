<div class="search_form"  style="width: 100%">
    <table class="search_table"  align="center" width="99%">
            <tr>
                <td align="center">
                   <table style="width: 100%" cellpadding="5">
                                <tr>
                                    <td class="fieldTitle" align="left">
                                        <spring:message code="UI.Personel.PersonCode" />:
                                    </td>
                                    <td class="fieldValue" align="right">
                                        <input  type="text" class="text search" searchProperty="e.personCode" style="width: 120px" />
                                        <select class="searchPattern" searchProperty="e.personCode" style="width: 150px">
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
                                     <td class="fieldTitle" align="left">
                                        <spring:message code="UI.Personel.FirstName" />:
                                    </td>
                                    <td class="fieldValue" align="right">
                                        <input  type="text" class="text search" searchProperty="e.firstName" style="width: 120px" />
                                        <select class="searchPattern" searchProperty="e.firstName" style="width: 150px">
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
                                    <td><input type="button" value="جستجو" onclick="search()" class="actionBtn" /></td>
                                </tr>
                                <tr>
                                    <td class="fieldTitle" align="left">
                                        <spring:message code="UI.Personel.LastName" />:
                                    </td>
                                    <td class="fieldValue" align="right">
                                       <input  type="text" class="text search" searchProperty="e.lastName" style="width: 120px" />
                                       <select  class="searchPattern" searchProperty="e.lastName" style="width: 150px;">
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
                                    <td class="fieldTitle" align="left">
                                        <spring:message code="UI.Personel.NationalityNumber" />:
                                    </td>
                                    <td class="fieldValue" align="right">
                                       <input  type="text" class="text search" searchProperty="e.nationalityNumber" style="width: 120px" />
                                       <select  class="searchPattern" searchProperty="e.nationalityNumber" style="width: 150px;">
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
                                    <td><input type="button" value="پاک کردن" onclick="clearFilter();search();" class="actionBtn" /></td>
                                </tr>
                            </table>
                </td>
            </tr>
        </table>
</div>

