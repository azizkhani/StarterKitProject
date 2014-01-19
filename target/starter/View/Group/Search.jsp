<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true"%>
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

