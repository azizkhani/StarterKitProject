var contextName = getContextName(); //return page context such as ACS
var week = new Array('یك شنبه', 'دوشنبه', 'سه شنبه', 'چهار شنبه', 'پنج شنبه', 'جمعه', 'شنبه');

function getContextName() {
    var i = location.href.indexOf('/', 8);
    var j = location.href.indexOf('/', i + 1);
    return location.href.substring(i + 1, j);
}

function errh(msg) {
    try {
        if (localErrh(msg))//If has local errh function and error locally handled dont continue
            return;
    } catch (e) { }
    switch (msg) {
        case 'User expired':
            //window.showModalDialog('/' + contextName +'/login.jsp', null, 'dialogHeight=200px;dialogWidth=300px;dialogTop=' + (window.screen.height / 2 - 200) + ';dialogLeft=' + (window.screen.width / 2 - 100));
            top.location.assign('/' + contextName + '/index.jsp');
            break;
        case 'Image big size':
            showErrorMessage('اندازه فایل بیش از حد مجاز است', 4);
            break;
        case 'Image big dim':
            showErrorMessage('حد اكثر اندازه عكس 1024 در 1024 است', 4);
            break;
        case 'File big size':
            showErrorMessage('اندازه فایل بیش از حد مجاز است', 4);
            break;
        case 'Organ diff':
            showErrorMessage('شما مجاز به تغییر  اطلاعات مركز دیگر نیستید', 4);
            break;
        case 'The input file was not found':
            showErrorMessage('فايلي براي بارگذاري انتخاب نشده است', 4);
            break;
        default:
            if (msg.indexOf('Access denied') > -1) {
                showErrorMessage('دسترسی غیر مجاز &nbsp;<a href="javascript:{showErrorMessage(\'' + msg + '\')}">جزئيات<a>');
            } else
                if (msg.indexOf('Entity does not exist for user') > -1) {
                    showErrorMessage('مجاز به انجام عمليات درخواست شده نيستيد  &nbsp;<a href="javascript:{showErrorMessage(\'' + msg + '\')}">جزئيات<a>');
                } else
                    if (msg.indexOf('integrity constraint') > -1) {
                        showErrorMessage('به علت ارتباط با سایر اطلاعات قابل حذف نیست', 5);
                    }
                    else if (msg.indexOf('unique constraint') > -1) {
                        showErrorMessage('این مورد تكراری است و قابل ذخیره نمی باشد', 5);
                    } else if (msg.indexOf('DatabaseException') > -1) {
                        showErrorMessage('خطای مرتبط با بانك داده! عملیات انجام نشد', 10);
                    }
                    else {
                        showErrorMessage('خطا! عمیلات انجام نشد &nbsp;<a href="javascript:{showErrorMessage(\'' + msg + '\')}">جزئيات<a>');
                    }
    }
}

function useLoadingMessage(msg) {
    dwr.util.useLoadingMessage(msg);
}

var tmp;
function pageLoad() {
    dwr.engine.setErrorHandler(errh);
    pageNo = 0;
    if (window.screen.height < 800)
        dwr.util.setValue('pageSize', '10');
    else if (window.screen.height < 1000)
        dwr.util.setValue('pageSize', '14');
    else
        dwr.util.setValue('pageSize', '18');
    //showElements(new Array('wait'));
    init();
    Tabs.init('tabList', 'tabContents');
    SearchTabs.init('searchTabList', 'searchTabContents');
}
function highLight(id) {
    tmp = getById(id).style.backgroundColor;
    getById(id).style.backgroundColor = '#ADEBAB';
}
function getById(id) {
    return document.getElementById(id);
}
function backPage() {
    if (window.event != null)
        history.back();
    else
        back();
}
function normLight(id) {
    getById(id).style.backgroundColor = tmp;
}

var ids = new Array('table_content', 'edit_form', 'search_form', 'wait', 'parentTitle');
function showElements(showIds) {
    for (var i = 0; i < ids.length; i++) {
        if (getById(ids[i]))
            getById(ids[i]).style.display = 'none';
    }

    for (i = 0; i < showIds.length; i++) {
        if (getById(showIds[i]))
            getById(showIds[i]).style.display = 'block';
    }
}

function dwrUploadSupport() {
    return !isIE();
}

function isIE() {
    return navigator.appVersion.indexOf('MSIE') != -1;
}

function addEvent(obj, evType, fn) {
    if (obj.addEventListener) {
        obj.addEventListener(evType, fn, false);
        return true;
    } else if (obj.attachEvent) {
        var r = obj.attachEvent("on" + evType, fn);
        return r;
    } else {
        return false;
    }
}
function cancelEvent(e) {
    if (window.event)
        window.event.returnValue = false;
    else
        e.preventDefault();
    return false;
}

function showDate(dateInputName, anchorName) {
    if (getById(dateInputName.replace('@to', '@from') + 'Div').style.visibility == 'visible') {
        getById(dateInputName.replace('@to', '@from') + 'Div').style.visibility = 'hidden';
        return;
    }

    var cal = new CalendarPopup(dateInputName.replace('@to', '@from') + 'Div');
    cal.setCssPrefix('TEST');
    cal.showNavigationDropdowns();
    cal.select(window.getById(dateInputName), anchorName, 'yyyy/MM/dd');
    return false;
}
function changeDownUpArrow(id) {
    var elm = getById(id);
    if (elm.src.indexOf('downArrow') > -1)
        elm.src = elm.src.replace('downArrow', 'upArrow');
    else
        elm.src = elm.src.replace('upArrow', 'downArrow');
}
function showHide(id) {
    var elm = getById(id)
    if (elm) {
        if (elm.style.display == 'block' || elm.style.display == '')
            elm.style.display = 'none';
        else
            elm.style.display = '';
    }
}
function showElement(id) {
    var elm = getById(id)
    if (elm)
        elm.style.display = 'block';
}
function hideElement(id) {
    var elm = getById(id)
    if (elm)
        getById(id).style.display = 'none';
}

function nextPage() {
    if (resultNum == pageSize) {
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
function createNavigation(resultNum, current, pageSize) {
    var template = '<A class="noborder" href="javascript:{}" onclick="showPage(pageNo)" >num &nbsp;</A>';
    var nav = getById('navigateNums');
    nav.innerHTML = '';
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
        showElement('nextIcon');
    else
        hideElement('nextIcon');


    if (current == 0)
        hideElement('prevIcon');
    else
        showElement('prevIcon');
    var childs = '';
    for (i = start; i <= end; i++) {
        if (i == current + 1)
            childs += '<font face="tahoma" size="2" color="red"><b>' + i + '</b>&nbsp;</font>';
        else
            childs += template.replace('num', '&nbsp;' + i).replace('pageNo', i - 1);
    }
    if (isIE()) {
        try {
            nav.insertAdjacentHTML('BeforeEnd', childs); //IE
        } catch (e) { }
    } else {
        nav.innerHTML = childs;
    }
    dwr.util.setValue('resultNum', resultNum);

}
function refreshForm() {
    if (currentId != -1)
        showCurrent(currentId);
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

//return id of selected item in seaarch grid
function selectedRowId(parentId) {
    var selected = null;
    var inputs = getById(parentId).getElementsByTagName('input');
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].id && inputs[i].id.indexOf('selectedItem') == 0 && inputs[i].checked) {
            selected = inputs[i].id.substring(14);
            break;
        }
    }
    return selected;
}

function showFilter() {
    showElements(new Array('search_form'));
}

var filter = '';
function addFilter(template, fname, value) {
    if (filter.length > 0)
        filter += '@;@';
    filter += template + '@@' + fname + '@@' + value;
}

var initFilter = ''; //In some entities we need initially filter list, this field use for this
//This method gets searchFields from page and makes filter string.
function getSearchFilter() {
    filter = initFilter;
    for (i = 0; i < searchFields.length; i++) {
        var fname = 'search_' + dotToDolar(searchFields[i]);
        if (getById(fname) == null) {
            continue;
        }
        var val = dwr.util.getValue(fname);
        if (val != null && val != '-1' && val != '') {
            var template = dwr.util.getValue(fname + '_template');
            if (template == null || template.length < 2)
                template = "fname = 'value'";
            addFilter(template, searchFields[i], val);
        }
    }
    if (filter.length == 0) {
        getById('filteredIcon').style.visibility = 'hidden';
        if (getById('simpleSearch') != null)
            getById('simpleSearch').style.display = 'block';
    }
    else {
        getById('filteredIcon').style.visibility = 'visible';
        if (getById('simpleSearch') != null)
            getById('simpleSearch').style.display = 'none';
    }
    return toFarsi(filter);
}
var isSearchState = false;
function search() {
    isSearchState = true;
    resetTopFilter(false);
    pageNo = 0;
    init();
}

function clearFilter() {
    parent.topFilter = '';
    filter = '';
    var inputs = getById('search_form').getElementsByTagName('input');
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].type == 'button' ||
			(inputs[i].type == 'hidden' && inputs[i].id.indexOf('search_') == 0 && (inputs[i].id.indexOf('EntityName') > -1 || inputs[i].id.indexOf('FieldName') > -1 || inputs[i].id.indexOf('Filter') > -1))
			|| inputs[i].id.indexOf('SimpleSearch') > -1)
            continue;
        dwr.util.setValue(inputs[i], null);
    }
    var selects = getById('search_form').getElementsByTagName('select');
    for (i = 0; i < selects.length; i++) {
        if (selects[i].getAttribute('class') != 'template' || (selects[i].outerHTML && selects[i].outerHTML.indexOf('template') == -1)) {
            selects[i].selectedIndex = 0;
            var autoCompletId = selects[i].id.substring(0, selects[i].id.length - 6) + '$id';
            dwr.util.setValue(autoCompletId, '-1');
        }
    }

    var inputs = getById('search_form').getElementsByTagName('textarea');
    for (i = 0; i < inputs.length; i++) {
        dwr.util.setValue(inputs[i], null);
    }
    dwr.util.setValue('parentTitle', null);

    if (getById('simpleSearch') != null) {
        var simpleSearchs = getById('simpleSearch').getElementsByTagName('input');
        for (i = 0; i < simpleSearchs.length; i++) {
            if (simpleSearchs[i].type != 'hidden') {//all filter query and order fileld in auto complete keeped in hidden inputs. For autocomplets only id must be -1 to clear
                dwr.util.setValue(simpleSearchs[i], null);
            }
        }
        simpleSearchs = getById('simpleSearch').getElementsByTagName('select');
        for (i = 0; i < simpleSearchs.length; i++) {
            simpleSearchs[i].selectedIndex = 0;
            var autoCompletId = simpleSearchs[i].id.substring(0, simpleSearchs[i].id.length - 6) + 'Id';
            dwr.util.setValue(autoCompletId, '-1');

        }

    }
    //	var inputs = getById('search_form').getElementsByTagName('select');
    //	for(i = 0; i < inputs.length; i++){
    //		inputs[i].selectedIndex = 0;
    //	}
}
function delFilter() {
    clearFilter();
    init();
}
function showMessage(msg, delay) {
    if (parent != this)
        parent.showMessage(msg, delay);
    //	else
    //		alert(msg);
}

function showErrorMessage(msg, delay) {
    if (parent != this)
        parent.showErrorMessage(msg, delay);
    //	else
    //		alert(msg);
}


function showCategory(cats, shown) {
    var tags = document.getElementsByTagName('tr');
    for (i = 0; i < tags.length; i++) {
        if (tags[i].id == shown)
            tags[i].style.display = 'block';
        else
            if (cats.indexOf(',' + tags[i].id + ',') > -1) {
                tags[i].style.display = 'none';
            }
    }
}


//------------------------------------- Base ---------------------------------------------------------------------
//parent node is a filter and is parent name such as 'fldPersonCure' and childNode is field name of this entity that is equal to parent
function baseSetTopFilter(parentNodes, childNodes) {
    //  if(parentNodes.length ==0 || parent.topFilter.length ==0)
    //return true;
    var pnodes = parentNodes.split(',');
    var cnodes = childNodes.split(',');
    var parentNodeSetted = false;
    for (i = 0; i < pnodes.length; i++) {
        var parentNode = pnodes[i];
        var childNode = cnodes[i];
        //	  if(parentNodes.length ==0 || parent.topFilter.indexOf(parentNode) ==-1){
        //	continue;
        //}
        var fields = parent.topFilter.split('@@');
        for (i = 0; /*parentNode.length > 0 && */i < fields.length; i += 3) {
            if (fields[i].length < 2)
                continue;

            if (fields[i] == 'search_e$id')
                dwr.util.setValue(fields[i], fields[i + 1]);
            else
                if (fields[i] == parentNode) {
                    dwr.util.setValue('search_e$' + childNode + '$id', fields[i + 1]);
                    dwr.util.setValue(childNode + 'SimpleSearchId', fields[i + 1]);

                    if (getById('search_e$' + childNode + 'Title'))
                        dwr.util.setValue('search_e$' + childNode + 'Title', fields[i + 2]);

                    if (fields[i + 2] && fields[i + 2].length > 0 && getById(childNode + 'SimpleSearchTitle'))
                        dwr.util.setValue(childNode + 'SimpleSearchTitle', fields[i + 2].split('-')[0]);

                    addToAutoComplete(childNode, fields[i + 1], fields[i + 2]);
                    addToAutoComplete("search_e$" + childNode, fields[i + 1], fields[i + 2]);
                    addToAutoComplete(childNode + 'SimpleSearch', fields[i + 1], fields[i + 2]);

                    dwr.util.setValue(childNode + 'Id', fields[i + 1]);
                    if (fields[i + 2] && fields[i + 2].length > 0)
                        dwr.util.setValue(childNode + 'Title', fields[i + 2].split('-')[0]);
                    if (fields[i + 2] && fields[i + 2].length > 4)
                        dwr.util.setValue('parentTitle', fields[i + 2]);

                    parentNodeSetted = true;
                }
        }
    }
    //  if(parentNodes.length > 0 && !parentNodeSetted)
    //	return false;
    return true;
}

// field name such as fldCureCase. this field and his value set as top filter of next page
function baseGo(fieldName, noItemSelectMsg) {
    var entityId = selectedRowId('table_content');
    var url = getById('selectAction').value;
    if (entityId || url.indexOf("command:") > -1) {
        if (url.length < 2) {
            showMessage(noItemSelectMsg, 2);
        }
        else {
            if (url.indexOf("command:") == 0) {
                setTimeout(url.substring(8), 1);
            } else {//go to url
                var path = location.href.split('/');
                var currURL = path[path.length - 2];
                parent.addHistory(currURL, entityId);
                parent.topFilter = fieldName + '@@' + entityId + '@@' + entitiesCache[entityId].title;
                parent.setContent(url);
            }
        }
    }
    else {
        showMessage('ردیفی انتخاب نشده است', 4);
    }
}

function baseResetTopFilter(parentNodes, childNodes) {
}
//------------------------------------- Base -------------------------------------------------------------------- end -

function filterStaticCombo(elmId, id) {
    var grps = getById(elmId).getElementsByTagName("optgroup");
    for (i = 0; i < grps.length; i++) {
        if (grps[i].id == id)
            grps[i].style.display = 'block';
        else
            grps[i].style.display = 'none';
    }
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
    var elm = getById(id);
    var val = dwr.util.getValue(id);
    if (elm.tagName == 'SELECT' && elm.selectedIndex == 0) {
        val = '';
    }
    if (getById(id + 'Caption') == null)
        alert(id + 'Caption is null');

    if (val == null || val.length == 0) {
        var title = getById(id + 'Caption').innerHTML;
        var i = title.indexOf('</font>');
        title = title.substring(i == -1 ? 0 : i + 7);
        showErrorMessage('<font color=\"red\">"</font><font color=\"navy\">' + title.replace(':', '</font><font color=\"red\">"</font>') + ' اجباری است', 4);
        elm.focus();
        return false;
    }
    return true;
}
function logout() {
    if (!confirm('آیا مطمئنید؟ شما قصد خروج از سامانه را دارید'))
        return;
    exitSystem();
}


function winClosed() {
    /*if(!logoutSelected){
    dwr.util.useLoadingMessage();
    dwr.engine.beginBatch();
    SecurityCreator.logout( function(done) {
    });
    dwr.engine.endBatch();
    alert('كاربر از سامانه خارج شد');
    document.location.replace('index.jsp');
    }*/
}

var logoutSelected = false;
function exitSystem() {
    dwr.util.useLoadingMessage();
    dwr.engine.beginBatch();
    SecurityCreator.logout(function (done) {
        logoutSelected = true;
        showMessage('خروج با موفقیت انجام شد', 4);
        window.setTimeout("document.location.replace('index.jsp')", 1500);
    });
    dwr.engine.endBatch();
}

function getToday() {
    return parent.today;
}

function setToday(id) {
    try {
        var oldVal = dwr.util.getValue(id);
        if (oldVal == null || oldVal.length < 5) {
            dwr.util.setValue(id, getToday());
        }
    } catch (e) { }
}

function setSelectOptions(selectElmId, optionsHTML) {
    var selElm = getById(selectElmId);
    if (isIE()) {
        var parentELM = selElm.parentNode;
        if (selElm.innerHTML.length < 5)
            selElm.innerHTML = 'aa';
        var x = selElm.outerHTML.replace(selElm.innerHTML, optionsHTML);
        //var id = selElm.getAttribute('id');
        parentELM.removeChild(selElm);
        parentELM.insertAdjacentHTML('BeforeEnd', x); //IE
    } else {
        selElm.innerHTML = optionsHTML;
    }
}

// ---------------------------- cookie --------------------------------------------
function setCookie(name, value) {
    document.cookie += '||' + name + '=' + value;
}

function getCookie(name) {
    if (document.cookie.indexOf('||' + name) == -1)
        return null;
    var i = document.cookie.indexOf('||' + name) + name.length + 3;
    var j = document.cookie.indexOf(';', i);
    return document.cookie.substring(i, j);
}
// ---------------------------- cookie end------------------------------------------
function orderAsc(fieldName) {
    order = fieldName + ' asc';
    init();
}

function orderDesc(fieldName) {
    order = fieldName + ' desc';
    init();
}

function encode(str) {
    if (str == null)
        return null;
    var ret = '';
    for (i = 0; i < str.length; i++) {
        ret += '$' + str.charCodeAt(i);
    }
    return ret;
}

function decode(str) {
    if (str == null)
        return null;
    var ret = '';
    var s = str.split('$')
    for (i = 0; i < s.length; i++) {
        if (s[i] != null && s[i].length > 0)
            ret += String.fromCharCode(s[i]);
    }
    return ret;

}