<div class="table_content" id="table_content" style="width:100%">
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
                                    <spring:message code="UI.BaseInformation.Code" />
                                    <a class="orderLink" href="javascript:{orderAsc('e.code')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.code')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                     <spring:message code="UI.BaseInformation.Topic" />
                                    <a class="orderLink" href="javascript:{orderAsc('e.topic')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.topic')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    <spring:message code="UI.General.Delete" />
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
            <span >${code}</span>
        </td>
        <td align="right">
            <span >${topic}</span>
        </td>
		<td align="center">
           <img src="../../Content/images/delete.gif" onclick="deleteClicked(${id})"  />
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
                                <spring:message code="UI.General.ResultNum" /> <span id="resultNum" style="font-weight: bold; color: #f00;">
                                </span>
                            </td>
                            <td style="padding-left: 5px; padding-right: 50px;">
                                <table>
                                    <tr>
                                        <td>
                                            <spring:message code="UI.General.Page" />
                                        </td>
                                        <td>
                                            <a title="" id="nextIcon" class="noborder" href="#" onclick="nextPage()" align="left">
                                                <img src="../../Content/images/next.gif" /></a>
                                        </td>
                                        <td id="navigateNums" nowrap="nowrap" align="left" dir="ltr">
                                        </td>
                                        <td>
                                            <a title="" id="prevIcon" class="noborder" href="#" onclick="prevPage()">
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
     	<table dir="ltr" align="center" style="border: 0px; vertical-align: bottom;background-color:#DFE7FC" width="100%">
            <tr>
                <td align="right">
                     
                </td>
            </tr>
        </table>
</div>
