// ------------------------------------- Mask -----------------------------------------------------------
function maskNumber(id, neg, digitNum, floatNum) {//neg= true :can be negative , digitNum:number of digits,  floatNum:number of digits after point
    var c = document.getElementById(id).value;
    if (neg && c.length > 0 && c[0] == '-')
        digitNum++;
    var s = '';
    var d = c.split('.');
    if (d[0].length > digitNum)
        showMessage('تعداد رقم مجاز = ' + digitNum, 3);
    for (i = 0; d.length > 0 && i < d[0].length && i < digitNum; i++) {
        if ((d[0].charAt(i) >= '0' && d[0].charAt(i) <= '9') || (neg && d[0].charAt(i) == '-' && i == 0)) {
            s += d[0].charAt(i);
        } else {
            if (!neg && d[0].charAt(i) == '-')
                showMessage('این مقدار نمی تواند منفی باشد', 3);
            else
                if (d[0].charAt(i) == '.' && floatNum == 0)
                    showMessage('عدد صحیح وارد شود', 3);
                else
                    showMessage('لطفا عدد وارد نمایید', 3);
        }
    }
    if (c.indexOf('.') > -1) {
        if (floatNum > 0) {
            s += '.';
            if (d[1].length > floatNum)
                showMessage('تعداد رقم اعشار مجاز= ' + floatNum, 3);
            for (i = 0; d.length > 1 && i < d[1].length && i < floatNum; i++) {
                if ((d[1].charAt(i) >= '0' && d[1].charAt(i) <= '9')) {
                    s += d[1].charAt(i);
                } else {
                    showMessage('لطفا عدد وارد نمایید', 3);
                }
            }
        }
        else {
            showMessage('عدد صحیح وارد شود', 3);
        }
    }
    document.getElementById(id).value = s;
}

function maskDate(id) {
    var continu = true;
    var c = document.getElementById(id).value;
    var s = '';
    for (i = 0; i < c.length && i < 4; i++) {
        if (c.charAt(i) >= '0' && c.charAt(i) <= '9') {
            s += c.charAt(i);
        }
    }
    if (c.length > 3) {
        var y = c.substring(0, 4);
        if (y > '1410' || y < '1280') {
            showMessage('سال غیر معتبر است', 3);
            continu = false;
        }
        else
            s += '/';
    }
    if (continu) {
        for (i = 5; i < c.length && i < 7; i++) {
            if (c.charAt(i) >= '0' && c.charAt(i) <= '9') {
                s += c.charAt(i);
            }
        }
        if (c.length > 6) {
            var m = c.substring(5, 7);
            if (!(m >= '01' && m <= '12')) {
                showMessage('ماه غیر معتبر است', 3);
                continu = false;
            }
            else
                s += '/';
        }
    }
    if (continu) {
        for (i = 8; i < c.length && i < 10; i++) {
            if (c.charAt(i) >= '0' && c.charAt(i) <= '9') {
                s += c.charAt(i);
            }
        }
        if (c.length > 9) {
            var d = c.substring(8, 10);
            if (!(d >= '01' && d <= '31')) {
                showMessage('روز غیر معتبر است', 3);
                s = s.substring(0, 8);
                continu = false;
            }
        }
    }
    document.getElementById(id).value = s;
}
function checkDate(id) {
    var c = document.getElementById(id).value;
    if (c.length == 0)
        return;
    try {
        var q = c.split('/');
        if (q.length != 3) {
            showMessage('تاریخ غیر معتبر است', 3);
            setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
            return;
        }
        if (q[0] > '1410' || q[0] < '1280') {
            showMessage('سال غیر معتبر است', 3);
            setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
            return;
        }
        if (q[1].length != 2 || !(q[1] >= '01' && q[1] <= '12')) {
            showMessage('ماه غیر معتبر است, ماههای معتبر مانند:02 یا 11', 3);
            setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
            return;
        }
        if (q[2].length != 2 || !(q[2] >= '01' && q[2] <= '31')) {
            showMessage('روز غیر معتبر است, روزهای معتبر مانند:02 یا 11', 3);
            setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
            return;
        }

    } catch (e) {
        showMessage('تاریخ غیر معتبر است', 3);
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return;
    }
}

function maskText(id, size) {
    var v = toFarsi(dwr.util.getValue(id));
    if (v.length == 0)
        return;
    if (v.length > size) {
        showMessage('حداكثر تعداد حروف = ' + size, 3);
        //getById(id).value = v.substr(0, size);	
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return;
    } else {
        dwr.util.setValue(id, v);
    }
}

function maskCetifSerNo(id) {
    var v = toFarsi(dwr.util.getValue(id));
    if (v.length == 0)
        return;
    if (v.indexOf('\u200E') == -1 && v.indexOf('/') > -1)
        getById(id).value = v.replace('/', '\u200E/');
}

function checkTime(id) {
    var c = getById(id).value;
    if (c.length == 0)
        return;
    try {
        var q = c.split(':');
        var h = q[0] * 1;
        if (!(h >= 0 && h <= 23)) {
            showMessage('ساعت غیر معتبر است, ساعت معتبر از 0 تا 23 می باشد. زمان با این فرمت وارد شود 9:20', 4);
            setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
            return;
        }
        if (h < 10)
            getById(id).value = '0' + h;
        else
            getById(id).value = h;

        if (q.length > 1) {
            var m = q[1] * 1;
            if (m < 0 || m > 59) {
                showMessage('دقیقه غیر معتبر است, دقیقه معتبر از 0 تا 59 می باشد. زمان با این فرمت وارد شود 9:20', 3);
                setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
                return;
            }
            getById(id).value += ':';
            if (m < 10)
                getById(id).value += '0';
            getById(id).value += m;
        }
        else {
            getById(id).value += ':00';
        }
    } catch (e) {
        showMessage('زمان غیر معتبر است, نمونه=12:30', 3);
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return;
    }
}

function checkFieldPattern(fieldType, fieldValue) {
    var pattern;
    switch (fieldType) {
        case 'username': pattern = /^[A-Za-z0-9]{5,40}$/; break;
        case 'password': pattern = /^[A-Za-z0-9_!@#$%\~\.\-]{5,40}$/; break;
        case 'tel': pattern = '^[0-9]*[\-]?[0-9]*$'; break;
        case 'mobile': pattern = /^[0-9 \.\-]{10,255}$/; break;
        case 'email': pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; break;
        case 'url': pattern = /(^(http|https|ftp):\/)?\/?([^:\/\s]+)((\/\w+)*\/)$/; break;
        case 'numeric': pattern = /^[0-9]{5,9}/; break;
        case 'not_empty': pattern = /[^ ]{3,}/; break;
        case 'select': pattern = /[^ ]{1,}/; break;
    }

    var reg = new RegExp(pattern);
    return reg.test(fieldValue);
}
function checkField(id, fieldType) {
    var matched = checkFieldPattern(fieldType, getById(id).value);
    if (!matched) {
        showErrorMessage(dwr.util.getValue(id + 'Caption') + ' معتبر نیست', 4);
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return false;
    }
    return true;
}

//Check national number
function checkNationalNo(id) {
    var n = 0;
    var m = 0;
    var ld = 0;
    var nid = getById(id).value;
    if (isNaN(nid)) {
        showErrorMessage('كد ملی معتبر نمی باشد', 4);
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return false;
    }
    if (nid.length == 0) return true;

    if (nid.length < 10) {
        showErrorMessage('كد ملی معتبر نمی باشد', 4);
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return false;
    }

    ld = Number(nid.substr(9, 1));

    for (i = 0; i < 10; i++) {
        if (nid.charCodeAt(i) == 32) {
            showErrorMessage('كد ملی معتبر نمی باشد', 4);
            setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
            return false;
        }
        if (i < 9)
            n = n + Number(nid.substr(i, 1)) * (10 - i);
    }
    m = n % 11;

    if (!((m == 0 && ld == 0) || (m == 1 && ld == 1) || (m > 1 && ld == 11 - m))) {
        showErrorMessage('كد ملی معتبر نمی باشد', 4);
        setTimeout('document.getElementById(\'' + id + '\').focus()', 100);
        return false;
    }
    else {
        return true;
    }
}
// ------------------------------------- Mask -----------------------------------------------------------end