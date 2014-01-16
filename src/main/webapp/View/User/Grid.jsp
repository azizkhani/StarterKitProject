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
            <span >${firstName}</span>
        </td>
        <td align="right">
            <span >${lastName}</span>
        </td>
         <td align="right">
            <span >${userName}</span>
        </td>
         <td align="right">
            <span >${passWord}</span>
        </td>
		<td align="right">
            <span >${email}</span>
        </td>
		<td align="right">
            <span style="cursor:pointer" onclick="openUserGroups(${id})"><img src="../../Desktop/images/gears.gif"> </img></span>
        </td>
		<td align="right">
            <span style="cursor:pointer" onclick="openUserOrganizationStruct(${id})"><img src="../../Desktop/images/view_list.png"> </img></span>
        </td>
    </tr>
</script> 
<script>
	function openUserGroups(userId){
		url="View/User/UserGroups.jsp?userId="+userId;
		window.parent.openWindow(100, "گروههای این کاربر", url, 300, 250);
	}
    function openUserOrganizationStruct(CurrentId){
    	url="View/User/SelectLocation.jsp?userId="+CurrentId;
		window.parent.openWindow(100, "منطقه مجاز این کاربر  ", url, 500, 300);
    }
</script>
<div class="table_content" id="table_content" style="width:100%">
   
        <table dir="ltr" align="center" style="border: 0px; vertical-align: bottom;background-color:#DFE7FC" width="100%"
           >
            <tr>
                <td align="center">
                    <table cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <td>
                                <table width="220px" cellpadding="2" style="float: right">
                                    <tr>
                                        <td>
                                            <a style="display: none;" id="filteredIcon" title="نمایش همه " class="noborder" href="javascript:{}"
                                                onclick="delFilter()">
                                                <img src="../../Content/images/filtered.bmp" /></a>
                                        </td>
                                        <td>
                                            <a title="جستجو" class="noborder" href="javascript:{}" onclick="showFilter()">
                                                <img src="../../Content/images/filter.bmp" /></a>
                                        </td>
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
                                    انتخاب
                                </th>
                                <th nowrap="nowrap">
                                    نام<a class="orderLink" href="javascript:{orderAsc('e.firstName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.firstName')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    نام خانوادگی<a class="orderLink" href="javascript:{orderAsc('e.lastName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.lastName')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    نام کاربری<a class="orderLink" href="javascript:{orderAsc('e.userName')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.userName')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    رمز عبور<a class="orderLink" href="javascript:{orderAsc('e.passWord')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.passWord')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    پست الکترونیک<a class="orderLink" href="javascript:{orderAsc('e.email')}"> &#8595;</a><a
                                        class="orderLink" href="javascript:{orderDesc('e.email')}"> &#8593;</a>
                                </th>
                                <th nowrap="nowrap">
                                    ثبت گروه
                                </th>
                                 <th nowrap="nowrap">
                                    مکان
                                </th>
                            </tr>
                        </thead>
                        <tbody id="entityBody">
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
    
</div>
