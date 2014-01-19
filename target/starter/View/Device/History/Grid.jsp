<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
<div class="table_content" id="table_content" style="width:100%">
        <table dir="ltr" align="center" style="border: 0px; vertical-align: bottom;background-color:#DFE7FC" width="100%">
            <tr>
                <td align="center">
                    <table cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <td>
                                <table width="220px" cellpadding="2" style="float: right">
                                   <tr>
                                        <td>
                                            <a style="display: none;" id="filteredIcon" title="'<spring:message code="UI.General.ShowAll" />'" class="noborder" href="javascript:{}"
                                                onclick="delFilter()">
                                                <img src="../../../Content/images/filtered.bmp" /></a>
                                        </td>
                                        <td>
                                            <a title="<spring:message code="UI.General.Search" />" class="noborder" href="javascript:{}" onclick="showFilter()">
                                                <img src="../../../Content/images/filter.bmp" /></a>
                                        </td>
                                        <td>
                                            <a title="<spring:message code="UI.General.Show" />" class="noborder" href="javascript:{}" onclick="pageNo=0;init()">
                                                <img src="../../../Content/images/refresh.gif" /></a>
                                        </td>
                                        <td>
                                            <a title="<spring:message code="UI.General.New" />" class="noborder" href="javascript:{}" onclick="showEditPage()">
                                                <img src="../../../Content/images/add.gif" /></a>
                                        </td>
                                        <td style="border-left: 1px solid silver">
                                        </td>
                                       
                                        <td>
                                            <a title="<spring:message code="UI.General.Delete" />" id="delete" class="noborder" href="javascript:{deleteClicked()}">
                                                <img src="../../../Content/images/delete.gif" /></a>
                                        </td>
                                        <td>
                                            <a title="<spring:message code="UI.General.Edit" />" id="edit" class="noborder" href="javascript:{editClicked()}">
                                                <img src="../../../Content/images/edit.gif" /></a>
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
                                   <spring:message code="UI.Device.DeviceHistory.simCard" /><a class="orderLink" href="javascript:{orderAsc('e.simCard')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.simCard')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                   <spring:message code="UI.Device.DeviceHistory.softwareVersion" /><a class="orderLink" href="javascript:{orderAsc('e.softwareVersion')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.softwareVersion')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    <spring:message code="UI.Device.DeviceHistory.sendDateTime" /><a class="orderLink" href="javascript:{orderAsc('e.sendDateTime')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.sendDateTime')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    <spring:message code="UI.Device.DeviceHistory.installDateTime" /><a class="orderLink" href="javascript:{orderAsc('e.installDateTime')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.installDateTime')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                   <spring:message code="UI.Device.DeviceHistory.location" /><a class="orderLink" href="javascript:{orderAsc('e.location.name')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.location.name')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                 <spring:message code="UI.Device.DeviceHistory.responsible" /><a class="orderLink" href="javascript:{orderAsc('e.responsible.fullName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.responsible.fullName')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                 <spring:message code="UI.Device.DeviceHistory.status" /><a class="orderLink" href="javascript:{orderAsc('e.status')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.status')}"> &#8593;</a>
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
							            <span >${simCard}</span>
							        </td>
							        <td align="right">
							            <span >${softwareVersion}</span>
							        </td>
									<td align="right">
										<span >${sendDateTime}</span>
							        </td>
									<td align="right">
							          <span > ${installDateTime}</span>
							        </td>
							        <td align="right">
							            <span >${location.name}</span>
							        </td>
							         <td align="right">
							            <span >${responsible.fullName}</span>
							        </td>
									 <td align="right">
										{{if status}}
        											فعال
    									{{else}}
        											غیر فعال
   										{{/if}}
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
                                                <img src="../../../Content/images/refresh.gif" /></a>
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
                                                <img src="../../../Content/images/next.gif" /></a>
                                        </td>
                                        <td id="navigateNums" nowrap="nowrap" align="left" dir="ltr">
                                        </td>
                                        <td>
                                            <a title="" id="prevIcon" class="noborder" href="#" onclick="prevPage()">
                                                <img src="../../../Content/images/prev.gif" /></a>
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
