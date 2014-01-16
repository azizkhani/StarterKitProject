
<div class="table_content" id="table_content" style="width:100%">
<%@ include file="Search.jsp" %>
        <table dir="ltr" align="center" style="border: 0px; vertical-align: bottom;background-color:#DFE7FC" width="100%"
           >
            <tr>
                <td align="center">
                    <table cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <td>
                                <table width="180px" cellpadding="2" style="float: right">
                                    <tr>
                                        <td>
                                            <a title="نمایش" class="noborder" href="javascript:{}" onclick="pageNo=0;init()">
                                                <img src="../../Content/images/refresh.gif" /></a>
                                        </td>
                                        <td>
                                            <a title="افزودن" class="noborder" href="javascript:{}" onclick="showEditPage()">
                                                <img src="../../Content/images/add.gif" /></a>
                                        </td>
                                        <td style="border-left: 1px solid silver">
                                        </td>
                                        <td>
                                            <a title="حذف" id="delete" class="noborder" href="javascript:{deleteClicked()}">
                                                <img src="../../Content/images/delete.gif" /></a>
                                        </td>
                                        <td>
                                            <a title="ویرایش" id="edit" class="noborder" href="javascript:{editClicked()}">
                                                <img src="../../Content/images/edit.gif" /></a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td align="right" nowrap="nowrap">
                                <span class="h3" id="parentTitle"></span>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center" width="100%">
                    <table class="grid" width="100%" align="center">
                        <thead>
                            <tr class="grid_header">
                                <th nowrap="nowrap">
                                   <spring:message code="UI.General.Select" />
                                </th>
                                 <th nowrap="nowrap">
                                   <spring:message code="UI.Personel.PersonCode" /><a class="orderLink" href="javascript:{orderAsc('e.personCode')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.personCode')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                   <spring:message code="UI.Personel.FirstName" /><a class="orderLink" href="javascript:{orderAsc('e.firstName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.firstName')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                   <spring:message code="UI.Personel.LastName" /><a class="orderLink" href="javascript:{orderAsc('e.lastName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.lastName')}"> &#8593;</a>
                                </th>
                               
                                <th nowrap="nowrap">
                                 <spring:message code="UI.Personel.FatherName" /><a class="orderLink" href="javascript:{orderAsc('e.fatherName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.fatherName')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                   <spring:message code="UI.Personel.BirthDate" /><a class="orderLink" href="javascript:{orderAsc('e.birthDate')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.birthDate')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    <spring:message code="UI.Personel.NationalityNumber" /><a class="orderLink" href="javascript:{orderAsc('e.nationalityNumber')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.nationalityNumber')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    <spring:message code="UI.Personel.IdNumber" /><a class="orderLink" href="javascript:{orderAsc('e.idNumber')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.idNumber')}"> &#8593;</a>
                                </th>
                            </tr>
                        </thead>
                        <tbody id="entityBody">
	<script id="GridRowTemplate" type="text/html">
    <tr id="pattern${id}"  class="oddRow"
            onclick="$('#selectedItemId'+${id}).attr('checked','checked');"
            onmouseover="highLight(this.id)" 
            onmouseout="normLight(this.id)" 
            ondblclick=showCurrent(${id})>
        <td align="center" width="30px">
            <span><input type="radio" name="selectedItem" id="selectedItemId${id}" /></span>
        </td>
		<td align="right">
            <span >${personCode}</span>
        </td>
        <td align="right">
            <span >${firstName}</span>
        </td>
        <td align="right">
            <span >${lastName}</span>
        </td>
         <td align="right">
            <span >${fatherName}</span>
        </td>
		<td align="right">
            <span >${birthDate}</span>
        </td>
		<td align="right">
			<span >${nationalityNumber}</span>
        </td>
		<td align="right">
          <span > ${idNumber}</span>
        </td>
    </tr>
	</script>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <!--navigation:begin-->
                <td align="left" style="width: 100%; height: 25px; background: #B9CCEB;">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <table>
                                    <tr>
                                        <td nowrap="nowrap">
                                            <input type="text" name="pageSize" onblur="pageSize = $('#pageSize').val();init();"
                                                id="pageSize" value="10" size="1" />
                                        </td>
                                        <td>
                                            <a href="#" onclick="pageSize = $('#pageSize').val();init();returnValue=false;">
                                                <img src="../../Content/images/refresh.gif" /></a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="padding-right: 50px;">
                                حداکثر تعداد سطرها <span id="resultNum" style="font-weight: bold; color: #f00;">
                                </span>
                            </td>
                            <td style="padding-left: 5px; padding-right: 50px;">
                                <table>
                                    <tr>
                                        <td>
                                            صفحه
                                        </td>
                                        <td>
                                            <a title="" id="nextIcon" class="noborder" href="#" onclick="nextPage();" align="left">
                                                <img src="../../Content/images/next.gif" /></a>
                                        </td>
                                        <td id="navigateNums" nowrap="nowrap" align="left" dir="ltr">
                                        </td>
                                        <td>
                                            <a title="" id="prevIcon" class="noborder" href="#" onclick="prevPage();">
                                                <img src="../../Content/images/prev.gif" /></a>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
                <!--navigation:end-->
            </tr>
        </table>
    
</div>
