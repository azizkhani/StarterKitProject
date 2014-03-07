var tmp;
$(window).error(function (msg, url, line) {
    //jQuery.post("js_error_log.php", { msg: msg, url: url, line: line });
    alert('Error Raising ' + msg);
});

$(function () {
	try{
	$('.persianDate').datepicker({dateFormat: 'yy/mm/dd',showButtonPanel: true});
	}
	catch(e){}
	$.ajaxSetup({
	      "error":function(xhr, ev, settings, exception) {
	            var message;
	            var statusErrorMap = {
	                '400' : "ثبت اطلاعات با موفقیت صورت نگرفت.",/*"Server understood the request but request content was invalid."*/
	                '401' : "شما دسترسی مجاز برای این کار را ندارید",/*"Unauthorised access."*/
	                '403' : "شما دسترسی مجاز برای این کار را ندارید",/*"Forbidden resouce can't be accessed"*/
	                '500' : "خطا در اجرای عملیات .",/*"Server understood the request but request content was invalid."*/
	                '503' : "Service Unavailable"/*"Server understood the request but request content was invalid."*/
	            };
	            if (xhr.status != undefined && xhr.status != null) {
	                message =statusErrorMap[xhr.status];
	                if(xhr.status==403 || xhr.status==401){
	                	Loader(true);
	                	showMessage(message);
	                	document.location='/baharan-core/403.jsp';
	                	return;
	                }
	                else
	                {
	                	Loader(false);
	                	if(!message) message="خطای سیستمی \n.";
	                }
	            }else if(ev=='parsererror'){
	                message="Error.\nParsing JSON Request failed.";
	            }else if(ev=='timeout'){
	                message="Request Time out.";
	            }else if(ev=='abort'){
	                message="Request was aborted by the server";
	            }else {
	                message="Unknow Error \n.";
	            }
	    	    showMessage(message);
	      }
	});     	
});
function pageLoad() {
    pageNo = 0;
    if (window.screen.height <= 800)
        $('#pageSize').val('10');
    else if (window.screen.height < 1000)
        $('#pageSize').val('10');
    else
        $('#pageSize').val('15');
    showElements(new Array('table_content'));
    init();
//    Tabs.init('tabList', 'tabContents');
//    SearchTabs.init('searchTabList', 'searchTabContents');
}
function Loader(isFade) {
    if (isFade) {
        $('#progressBackgroundFilter').fadeIn();
        $('#loadingbox').fadeIn();
        $('#Loader').fadeIn();
        $("#FormMain").mask("در حــال اجـــرای عملیات......");
    } else {
        $('#progressBackgroundFilter').fadeOut(1000);
        $('#loadingbox').fadeOut(1000);
        $('#Loader').fadeOut(1000);
        $("#FormMain").unmask();
    }
}
var ids = new Array('table_content', 'edit_form', 'search_form', 'wait', 'parentTitle');
function showElements(showIds) {
    for (var i = 0; i < ids.length; i++) {
        if ($('#' + ids[i]))
            $('#' + ids[i]).css('display', 'none');
    }

    for (i = 0; i < showIds.length; i++) {
        if ($('#' + showIds[i]))
            $('#' + showIds[i]).css('display', 'block');
    }
}

function openWindow(winId, title, url, w, h) {
	if(winId==0)
		winId= new Date().getTime()
	window.parent.openWindow(winId, title, url, w, h);
}

//--------------------------------------------------Grid----------------
function nextPage() {
    if (resultNum + 1 == pageSize) {
        pageNo++;
        init();
    }
}
function prevPage() {
    if (pageNo > 0) {
        pageNo--;
        init();
    }
}
function showPage(pNo) {
    pageNo = pNo;
    init();
}
function orderAsc(fieldName) {
    order = fieldName + ' asc';
    init();
}
function orderDesc(fieldName) {
    order = fieldName + ' desc';
    init();
}
function backPage() {
    if (window.event != null)
        history.back();
    else
        back();
}
function refreshForm() {
    if (currentId != -1)
        showCurrent(currentId);
}
function createNavigation(resultNum, current, pageSize) {
    var template = '<A class="noborder" href="javascript:{}" onclick="showPage(pageNo)" >num &nbsp;</A>';
    var pageCount = Math.floor(resultNum / pageSize);
    if (pageCount * pageSize < resultNum)
        pageCount++;
    if (current > pageCount)
        current = pageCount;

    var start = Math.max(1, current - 3);
    var end = Math.min(pageCount, start + 9);
    if (end - start < 10)
        start = Math.max(1, end - 9);

    if (current + 1 < pageCount)
        $('#nextIcon').css('display', 'block');
    else
        $('#nextIcon').css('display', 'none'); // hideElement('nextIcon');
    if (current == 0)
        $('#prevIcon').css('display', 'none'); //hideElement('prevIcon');
    else
        $('#prevIcon').css('display', 'block'); // showElement('prevIcon');
    var childs = '';
    for (i = start; i <= end; i++) {
        if (i == current + 1)
            childs += '<font face="tahoma" size="2" color="red"><b>' + i + '</b>&nbsp;</font>';
        else
            childs += template.replace('num', '&nbsp;' + i).replace('pageNo', i - 1);
    }
    $('#navigateNums').html(childs);
    $('#resultNum').html(resultNum);
}
function selectedRowId(parentId) {
    var selected = null;
    var inputs = document.getElementById(parentId).getElementsByTagName('input');
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].id && inputs[i].id.indexOf('selectedItem') == 0 && inputs[i].checked) {
            selected = inputs[i].id.substring(14);
            break;
        }
    }
    return selected;
}
function normLight(id) {
    $('#' + id).css('backgroundColor', tmp);
}
function highLight(id) {
    tmp = $('#' + id).css('backgroundColor');
    $('#' + id).css('backgroundColor', '#ADEBAB');
}
function testMandatories() {
    var tags = new Array('input', 'textarea', 'select');
    for (j = 0; j < tags.length; j++) {
        var elm = document.getElementsByTagName(tags[j]);
        for (i = 0; i < elm.length; i++) {
            if (elm[i].name == "*") {
                if (!testMandatory(elm[i].id))
                    return false;
            }
        }
    }
    return true;
}
function testMandatory(id) {
    var elm = document.getElementById(id);
    var val = dwr.util.getValue(id);
    if (elm.tagName == 'SELECT' && elm.selectedIndex == 0) {
        val = '';
    }
    if (elm.tagName == 'INPUT' && elm.type == 'file') {
        if (elm.value.length == 0 && document.getElementById(id + 'Link').style.display == 'none')
            val = '';
    }
    if (document.getElementById(id + 'Caption') == null)
        alert(id + 'Caption is null');

    if (val == null || val.length == 0) {
        var title = document.getElementById(id + 'Caption').innerHTML;
        var i = title.indexOf('</font>');
        title = title.substring(i == -1 ? 0 : i + 7);
        showErrorMessage('<font color=\"red\">"</font><font color=\"navy\">' + title.replace(':', '</font><font color=\"red\">"</font>') + ' اجباری است', 4);
        elm.focus();
        return false;
    }
    return true;
}
function showMessage(msg, delay) {
    if (parent != this)
        parent.showMessage(msg, delay);
}
function isIE() {
    return navigator.appVersion.indexOf('MSIE') != -1;
}
function dotToDolar(str) {
    if (str)
        return str.replace(/\./gi, "$");
    return str;
}
function dolarToDot(str) {
    if (str)
        return str.replace(/\$/gi, ".");
    return str;
}
function toFarsi(str) {
    return str.replace(/ك/gi, "ک").replace(/ي/gi, "ی");
}
function baseSetInputByEntity(entity) {
	try{
	    for (prop in entity) {
	        if (entity[prop] != null && prop.toString().indexOf('Date') >= 0 && entity[prop].toString().indexOf('Date') >= 0) {
	            var date = eval(entity[prop].replace(/\/Date\((\d+)\)\//gi, "new Date($1)"));
	            $("[name=" + prop + "], [id=" + prop + "]").val(date).data('EnglishDateTime', date);
	        }
	        else
	            $("[name=" + prop + "], [id=" + prop + "]").val(entity[prop]);
	    }
	}
	catch(e){alert(e);}
}
function baseSetEntityFromInput(entity) {
    for (prop in entity) {
    	try{
	    	if($("[name=" + prop + "], [id=" + prop + "]") != null && $("[name=" + prop + "], [id=" + prop + "]").val() !=null)
	    		entity[prop] = $("[name=" + prop + "], [id=" + prop + "]").val();
	        if (entity[prop] != null && prop.indexOf('Date') >= 0 && $("[name=" + prop + "], [id=" + prop + "]").data('EnglishDateTime')!=null) {
	            entity[prop] = $("[name=" + prop + "], [id=" + prop + "]").data('EnglishDateTime').toUTCString();
	        }
    	}
    	catch(e){alert(e);}
    }
}
function setComboValueFromObject(cmbSelector,object){
	if(object)
		$(cmbSelector).val(object.id);
}
function getJsonObjectByComboValue(cmbSelector){
	 var object;
	 if($(cmbSelector).val()!=-1){
		object={id:0};
		object.id = $(cmbSelector).val();
     }
     else
    	 object=null;
	 return object;
}
var isSearchState = false;
function search() {
    isSearchState = true;
    pageNo = 0;
    init();
}
function showFilter() {
    showElements(new Array('search_form','table_content'));
}
function delFilter() {
    clearFilter();
    init();
}
function getSearchFilter() {

	txt = "$$,$$";
	$(".search").each(function(){
		var temp = $(this).attr("searchProperty");
		if(temp !== null)
		{
			temp2 = temp;
			if($(this).val() != "")
				temp2 += "$$"+ $(this).val();
			else
					return true;
			temp3 = "";
			temp3 = $(".search2").filter("[searchProperty=\'" + temp + "\']");
			if( typeof  temp3.val() != 'undefined')
			{
				if(temp3.val() != "")
					temp2 += "@@"+temp3.val() + "$$";
				else
					return true;
			}
			else
				temp2 += "$$";
			temp2 +=$(".searchPattern").filter("[searchProperty=\'" + temp + "\']").val()+ "$$,$$";
			if(temp2 != "$$$$$$,$$")
				txt += temp2;
		}
	});
	if(txt =="$$,$$") txt="";
	if(txt.length == 0) {
        $('#filteredIcon').css('display', 'none');
        if ($('#simpleSearch') != null)
            $('#simpleSearch').css('display', 'block');
	 }
	 else {
        $('#filteredIcon').css('display', 'block');
        if ($('#simpleSearch') != null)
            $('#simpleSearch').css('display', 'none');
	 }
	return txt;
}
function clearFilter(){
	$(".search").each(function() {
		$(this).val("");
	});
}
//$.get("",txt.toString());
function fillCombo(controlId,jsonUrl,jsonData, itemKey, itemValue,firstItemText,selectedValue) {
    $.getJSON(jsonUrl, jsonData, function (entities) {
        var ddlSelectedProduct = $("#" + controlId);
        // clear all previous options 
        $("#" + controlId + " > option").remove();
        if (firstItemText)
            ddlSelectedProduct.append($("<option />").val(-1).text(firstItemText));
        // populate the products 
        if(entities.entityList)
        	entityList=entities.entityList;
        else
        	entityList=entities;
        
        $.each(entityList, function (i, entityItem) {
        	if(selectedValue!=null && entityItem[itemKey]==selectedValue)
        		ddlSelectedProduct.append($("<option selected='selected' />").val(entityItem[itemKey]).text(entityItem[itemValue]));
        	else
        		ddlSelectedProduct.append($("<option />").val(entityItem[itemKey]).text(entityItem[itemValue]));
        });
        ddlSelectedProduct.attr('selectedIndex', 0);
    });
}
function getAuthenticatedUser(callback)
{
	$.getJSON("http://localhost:8080/baharan-core/rest/security/user/authenitacedUser", {}, function (authenticatedUser) {
		if (typeof callback == "function") callback(authenticatedUser); else alert('meh');
	});
}

