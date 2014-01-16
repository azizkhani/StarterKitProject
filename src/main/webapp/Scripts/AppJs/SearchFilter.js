var filter = '';
var initFilter = ''; //In some entities we need initially filter list, this field use for this
function showFilter() {
    showElements(new Array('search_form'));
}
function addFilter(template, fname, value) {
    if (filter.length > 0)
        filter += '@;@';
    filter += template + '@@' + fname + '@@' + value;
}
//This method gets searchFields from page and makes filter string.
function getSearchFilter() {
    filter = initFilter;
    for (i = 0; i < searchFields.length; i++) {
        var fname = 'search_' + dotToDolar(searchFields[i]);
        if (document.getElementById(fname) == null) {
            continue;
        }
        var val = document.getElementById(fname).value;
        if (val != null && val != '-1' && val != '') {
            var template = document.getElementById(fname + '_template').value;
            if (template == null || template.length < 2)
                template = "fname = 'value'";
            addFilter(template, searchFields[i], val);
        }
    }
    if (filter.length == 0) {
        $('#filteredIcon').css('display', 'none');
        if ($('#simpleSearch') != null)
            $('#simpleSearch').css('display', 'block');
    }
    else {
        $('#filteredIcon').css('display', 'block');
        if ($('#simpleSearch') != null)
            $('#simpleSearch').css('display', 'none');
    }
    return toFarsi(filter);
}


function clearFilter() {
    parent.topFilter = '';
    filter = '';
    var inputs = document.getElementById('search_form').getElementsByTagName('input');
    for (i = 0; i < inputs.length; i++) {
        if (inputs[i].type == 'button' ||
			(inputs[i].type == 'hidden' && inputs[i].id.indexOf('search_') == 0 && (inputs[i].id.indexOf('EntityName') > -1 || inputs[i].id.indexOf('FieldName') > -1 || inputs[i].id.indexOf('Filter') > -1))
			|| inputs[i].id.indexOf('SimpleSearch') > -1)
            continue;
        document.getElementById(inputs[i].id).value = "";
    }
    var selects = document.getElementById('search_form').getElementsByTagName('select');
    for (i = 0; i < selects.length; i++) {
        if (selects[i].getAttribute('class') != 'template' || (selects[i].outerHTML && selects[i].outerHTML.indexOf('template') == -1)) {
            selects[i].selectedIndex = 0;
            var autoCompletId = selects[i].id.substring(0, selects[i].id.length - 6) + '$id';
            if (document.getElementById(autoCompletId))
                document.getElementById(autoCompletId).value = '-1';
        }
    }

    var inputs = document.getElementById('search_form').getElementsByTagName('textarea');
    for (i = 0; i < inputs.length; i++) {
        document.getElementById(inputs[i].id).value = ("");
    }
    document.getElementById('parentTitle').value = ("");
    if (document.getElementById('simpleSearch') != null) {
        var simpleSearchs = document.getElementById('simpleSearch').getElementsByTagName('input');
        for (i = 0; i < simpleSearchs.length; i++) {
            if (simpleSearchs[i].type != 'hidden') {//all filter query and order fileld in auto complete keeped in hidden inputs. For autocomplets only id must be -1 to clear
                document.getElementById(simpleSearchs[i].id).value = (null);
            }
        }
        simpleSearchs = document.getElementById('simpleSearch').getElementsByTagName('select');
        for (i = 0; i < simpleSearchs.length; i++) {
            simpleSearchs[i].selectedIndex = 0;
            var autoCompletId = simpleSearchs[i].id.substring(0, simpleSearchs[i].id.length - 6) + 'Id';
            document.getElementById(autoCompletId).value = '-1';
        }
    }
}
function delFilter() {
    clearFilter();
    init();
}
//parent node is a filter and is parent name such as 'fldPersonCure' and childNode is field name of this entity that is equal to parent
function baseSetTopFilter(parentNodes, childNodes) {
    var pnodes = parentNodes.split(',');
    var cnodes = childNodes.split(',');
    var parentNodeSetted = false;
    for (i = 0; i < pnodes.length; i++) {
        var parentNode = pnodes[i];
        var childNode = cnodes[i];
        if (parent.topFilter) {
            var fields = parent.topFilter.split('@@');
            for (i = 0; i < fields.length; i += 3) {
                if (fields[i].length < 2)
                    continue;

                if (fields[i] == 'search_e$id')
                    $('#' + fields[i]).val(fields[i + 1]);
                else
                    if (fields[i] == parentNode) {
                        $('#' + 'search_e$' + childNode + '$id').val(fields[i + 1]);
                        $('#' + childNode + 'SimpleSearchId').val(fields[i + 1]);

                        if (document.getElementById('search_e$' + childNode + 'Title'))
                            $('#' + 'search_e$' + childNode + 'Title').val(fields[i + 2]);

                        if (fields[i + 2] && fields[i + 2].length > 0 && document.getElementById(childNode + 'SimpleSearchTitle'))
                            $('#' + childNode + 'SimpleSearchTitle').val(fields[i + 2].split('-')[0]);

                        $('#' + childNode + 'Id').val(fields[i + 1]);
                        if (fields[i + 2] && fields[i + 2].length > 0)
                            $('#' + childNode + 'Title').val(fields[i + 2].split('-')[0]);
                        if (fields[i + 2] && fields[i + 2].length > 4)
                            $('#' + 'parentTitle').val(fields[i + 2]);
                        parentNodeSetted = true;
                    }
            }
        }
    }
    return true;
}
// field name such as fldCureCase. this field and his value set as top filter of next page
function baseGo(fieldName, noItemSelectMsg) {
    var entityId = selectedRowId('table_content');
    var url = document.getElementById('selectAction').value;
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
                try {
                    parent.addHistory(currURL, entityId);
                } catch (e) { }
                parent.topFilter = fieldName + '@@' + entityId + '@@' + entitiesCache[entityId].title;
                try {
                    parent.setContent(url);
                } catch (e) { document.location.href = '/' + getContextName() + '/' + url }
            }
        }
    }
    else {
        showMessage('ردیفی انتخاب نشده است', 4);
    }
}
