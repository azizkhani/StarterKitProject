<div class="search_form" id="search_form" style="width: 100%">
        <table width="95%" align="center">
               <tr>
                   <td class="fieldTitle" align="left">
                       <spring:message code="UI.BaseInformation.Code"  />
                   </td>
                   <td class="fieldValue" align="right">
                       <input id="search_personCode" type="text" class="text search" searchProperty="e.code" size="40" style="width: 40px" />
                       <select class="searchPattern" searchProperty="e.code"   id="search_personCode_template" style="width: 150px;display: none">
 							<option value="1"><spring:message code="UI.General.Search.isEqual"  /></option>
                       </select>
                   </td>
                   <td class="fieldTitle" align="left">
                       <spring:message code="UI.BaseInformation.Topic"  />
                   </td>
                   <td class="fieldValue" align="right">
                       <input id="search_firstName" type="text" class="text search" searchProperty="e.topic" size="40" />
                       <select class="searchPattern" searchProperty="e.topic" id="search_firstName_template" style="width: 150px;display: none">
                           <option value="7"><spring:message code="UI.General.Search.IncludeThisValue"  /></option>
                       </select>
                   </td>
                   <td>
                  		<input type="button" value="<spring:message code="UI.General.Search"  />" onclick="search()" 		class="actionBtn" />
          				<input type="button" value="<spring:message code="UI.General.Clear"  />"  onclick="clearFilter();delFilter()" 	class="actionBtn" />
          				<input type="button" value="<spring:message code="UI.General.New" />" 		onclick="showEditPage();"  class="actionBtn">
                   </td>
               </tr>
        </table>
</div>

