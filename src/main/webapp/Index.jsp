<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="Content-Language" content="fa">
    <title></title>
    <link rel="stylesheet" type="text/css" href="Desktop/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="Desktop/css/desktop.css" />
    <!-- GC -->
    <!-- LIBS -->
    <script type="text/javascript" src="Desktop/adapter/ext/ext-base.js"></script>
    <!-- ENDLIBS -->
    <script type="text/javascript" src="Desktop/core/ext-all.js"></script>
    <!-- DESKTOP -->
    <script type="text/javascript" src="Desktop/js/StartMenu.js"></script>
    <script type="text/javascript" src="Desktop/js/TaskBar.js"></script>
    <script type="text/javascript" src="Desktop/js/Desktop.js"></script>
    <script type="text/javascript" src="Desktop/js/App.js"></script>
    <script type="text/javascript" src="Desktop/js/Module.js"></script>
    <script type="text/javascript" src="Desktop/sample.js"></script>
    <script type="text/javascript" src="Scripts/jquery-1.6.2.min.js" ></script>
     <script type="text/javascript" src="Scripts/jquery-ui-1.8.11.js" ></script>
    <script type="text/javascript" src="Desktop/draggable.htm"></script>
    <script language="javascript">


        var topFilter = '';
        var lastWinId = '';

        function showMessage(text, delay) {
            Ext.MessageBox.show({
                msg: '<div style="direction:rtl;text-align:center;line-height:1.75em;">' + text + '<div>'
				, width: text.length > 40 ? 400 : 300
				, buttons: Ext.MessageBox.OK
				, icon: Ext.MessageBox.INFO
				, cls: "msgClass",modal:true
            });

            if (delay > 0) {
                setTimeout(function () { Ext.MessageBox.hide(); }, delay * 1000);
            }
        }

        function showErrorMessage(text) {
            Ext.MessageBox.show({
                msg: '<div style="direction:rtl;text-align:center;line-height:1.75em;">' + text + '<div>'
			    , width: text.length > 40 ? 400 : 300
				, buttons: Ext.MessageBox.OK
		        , icon: Ext.MessageBox.ERROR
				, cls: "msgClass"
            });
        }

        function showConfirmMessage(text, command, frame) {
            if (command == 'delete')
                Ext.MessageBox.show({
                    msg: '<div style="direction:rtl;text-align:center">' + text + '<div>'
					, width: 300
					, buttons: Ext.MessageBox.YESNO
					, icon: Ext.MessageBox.QUESTION
					, cls: "msgClass"
					, fn: function (btn) {
					    window.frames[frame + 'Manager'].deleteConfirm(btn);
					}
                });
            if (command == 'move') {
                Ext.MessageBox.show({
                    msg: '<div style="direction:rtl;text-align:center">' + text + '<div>'
					, width: 300
					, buttons: Ext.MessageBox.YESNO
					, icon: Ext.MessageBox.QUESTION
					, cls: "msgClass"
					, fn: function (btn) {
					    window.frames[frame + 'Manager'].moveConfirm(btn);
					}
                });
            }
        }

        function hideMessage() {
        }
        function openWindow(winId, title, url, w, h) {
            if (winId != 'loginWin')
                lastWinId = winId;
            var desktop = MyDesktop.getDesktop();
            var win = desktop.getWindow(winId);
            var html = '';
            if (url.indexOf('text:') == 0) {
                html = url.substring(5);
            } else {
                url += (url.indexOf('?') >= 0 ? '&' : '?') + 'refreshId=' + new Date().getTime();
                html = '<iframe src="/' + getContextName() + '/' + url + '" width="100%" height="100%" frameborder="0" name="' + winId + '"></iframe>';
            }
            maximized = false;
            maximizable = true;

            if (winId == 'DivisionCommand'
				|| winId == 'winDocumentChart'
				|| winId == 'IssuanceIntroduction') {
                maximized = true;
                maximizable = false;
            }
            if (!win) {
                win = desktop.createWindow({
                    id: winId
					, title: title
					, width: w
					, height: h
					, maximized: maximized
					, maximizable: maximizable
					, closeAction: 'close'
					, html: html
					, iconCls: 'bogus'
					, plain: true
                });
            }
            win.show();
        }
        function closeWindow(winId) {
            var desktop = MyDesktop.getDesktop();
            var win = desktop.getWindow(winId);
            if (win)
                win.close();
        }

        function closeLastOpenedWin() {
            if (lastWinId && lastWinId.length > 1)
                closeWindow(lastWinId);
            lastWinId = '';
        }

        var today = '1390/06/16';
        var tmp, t;
        function switchWindow(winId, isInternal) {
            var desktop = MyDesktop.getDesktop();
            if (isInternal)
                var win = desktop.getWindow(winId);
            else
                var win = desktop.getWindow('win' + winId);
            if (win) win.show();
        }
        function hideMessage() {
        }
        function ChechAccessforOpenWindows(winId, title, url, w, h){
        	//$.getJSON("/baharan-core/rest/security/user/checkAccess/"+winId, function (entityData) {
        	///	if (entityData===true)
            		      			openWindow(winId, title, url, w, h);
        	//	else
        	//			showMessage(' شما دسترسی به این عملیات ندارید');

           // });
        }
    	function initDesktop() {
		 	eraseCookie("iconsPosition2285");
			init();
		}
        function init() {
            //setTimeout('changeBack()', 15 * 60 * 1000);
            var clientHeight = document.body.clientHeight - 43;
			var maxIconsPerCol = parseInt(clientHeight/100, 10);
			var numIcons = iconList.length;
			var left = 15, top = 15;		
			var c = readCookie("iconsPosition2285");
			if (numIcons <= maxIconsPerCol) {
				for (var i = 0; i < numIcons; i ++) {
					if (c != null && c.indexOf("@icon"+iconList[i]+"@") != -1) {
						var leftTemp = c.substr(c.indexOf("@icon"+iconList[i]+"@")+iconList[i].length+6, 4);
						getById("icon"+iconList[i]).style.left = parseInt(leftTemp, 10)+"px";
						var topTemp = c.substr(c.indexOf("@icon"+iconList[i]+"@")+iconList[i].length+11, 4);
						getById("icon"+iconList[i]).style.top = parseInt(topTemp, 10)+"px";
					} else {
						getById("icon"+iconList[i]).style.left = left+'px';
						getById("icon"+iconList[i]).style.top = ((i*100)+top)+'px';
					}
					getById("icon"+iconList[i]).style.display = '';
				}
			} else {
				for (var i = 0; i <= parseInt(numIcons/maxIconsPerCol, 10); i ++) {
					for (var j = 0; j < maxIconsPerCol; j ++) {
						if (i*maxIconsPerCol+j >= numIcons) break;
						if (c != null && c.indexOf("@icon"+iconList[i*maxIconsPerCol+j]+"@") != -1) {
							var leftTemp = c.substr(c.indexOf("@icon"+iconList[i*maxIconsPerCol+j]+"@")+iconList[i*maxIconsPerCol+j].length+6, 4);
							getById("icon"+iconList[i*maxIconsPerCol+j]).style.left = parseInt(leftTemp, 10)+"px";
							var topTemp = c.substr(c.indexOf("@icon"+iconList[i*maxIconsPerCol+j]+"@")+iconList[i*maxIconsPerCol+j].length+11, 4);
							getById("icon"+iconList[i*maxIconsPerCol+j]).style.top = parseInt(topTemp, 10)+"px";
						} else {
							getById("icon"+iconList[i*maxIconsPerCol+j]).style.left = (i*86+left)+'px';
							getById("icon"+iconList[i*maxIconsPerCol+j]).style.top = (j*100+top)+'px';
						}
						getById("icon"+iconList[i*maxIconsPerCol+j]).style.display = '';
					}
				}
			}
        }
        var i = 1;
        function changeBack() {
            document.getElementById('bodyElm').style.backgroundImage = 'url(./images/wallpapers/desktop' + i + '.jpg)';
            i = i == 5 ? 1 : i + 1;
            setTimeout('changeBack()', 15 * 60 * 1000);
        }
        function normLight(id){
        	getById(id).style.backgroundColor = tmp;
        }
		function iconOver(obj) {
			obj.style.background = obj.style.backgroundImage + " 15px 5px no-repeat rgba(255, 255, 255, .3)";
		}
		function iconOut(obj) {
			obj.style.background = obj.style.backgroundImage + " 15px 5px no-repeat rgba(255, 255, 255, .05)";
		}
        //for transfer variables between pages
        var virtualVar = {};
        var virtualMemberFinderCase = {};
        var memberPersonelCase = {};
        var memberParent = {};
        var interfaceParent = {};
        var parentQuota = "";
        function handleDragStop(event, ui) {
			var left = parseInt(ui.offset.left, 10)+"";
			var top = parseInt(ui.offset.top, 10)+"";
			var iconId = this.id;
			
			var c = readCookie("iconsPosition2285");
			switch (left.length) {
				case 1:
					left = "000"+left;
					break;
				case 2:
					left = "00"+left;
					break;
				case 3:
					left = "0"+left;
					break;
			}
			switch (top.length) {
				case 1:
					top = "000"+top;
					break;
				case 2:
					top = "00"+top;
					break;
				case 3:
					top = "0"+top;
					break;
			}
			
			if (c == null) {
				var cookieValue = "@"+this.id+"@"+left+"|"+top+"@";
				createCookie("iconsPosition2285", cookieValue, 365);
			} else {
				if (c.indexOf("@"+this.id+"@") != -1)
					c = c.replace(c.substr(c.indexOf("@"+this.id+"@"), this.id.length+12), "");
				var cookieValue = c+"@"+this.id+"@"+left+"|"+top+"@";
				createCookie("iconsPosition2285", cookieValue, 365);
			}
		}
      //Mohsen Salehi 
        // ---------------------------- cookie --------------------------------------------
           function setCookie(name, value){
           	document.cookie += '||' + name + '=' + value;
           }
           function getCookie(name){
           	if(document.cookie.indexOf('||' + name) == -1)
           		return null;
           	var i = document.cookie.indexOf('||' + name) + name.length + 3;
           	var j = document.cookie.indexOf(';' , i);
           	return document.cookie.substring(i,j);
           }
   		//  Mohsen Salehi : begin
           function createCookie(name,value,days) {
           	if (days) {
           		var date = new Date();
           		date.setTime(date.getTime()+(days*24*60*60*1000));
           		var expires = "; expires="+date.toGMTString();
           	}
           	else var expires = "";
           	document.cookie = name+"="+value+expires+"; path=/";
           }
           function readCookie(name) {
           	var nameEQ = name + "=";
           	var ca = document.cookie.split(';');
           	for(var i=0;i < ca.length;i++) {
           		var c = ca[i];
           		while (c.charAt(0)==' ') c = c.substring(1,c.length);
           		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
           	}
           	return null;
           }

           function eraseCookie(name) {
           	createCookie(name,"",-1);
           }
           function getById(id){
           	return document.getElementById(id);
           }
   	//        	Mohsen Salehi : end
   // ---------------------------- cookie end------------------------------------------
    </script>
    <style>
		.desktopIcon {
					position: absolute;
					font-size: 11px;
					width: 86px;
					padding-left: 2px;
					padding-right: 2px;
					height: 34px;
					padding-top: 60px;
					vertical-align: bottom;
					text-align: center;
					border-radius: 10px;
					color: #fff;
					box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
					-moz-user-select: none;
					cursor: pointer;
		}
		#messageMarquee {
		    -moz-user-select: none;
		    background: none repeat scroll 0 0 rgba(255, 255, 255, 0.1);
		    border-radius: 5px 5px 5px 5px;
		    bottom: 15px;
		    box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
		    color: #FFFFFF;
		    font-size: 11px;
		    height: 250px;
		    padding-left: 5px;
		    padding-right: 5px;
		    padding-top: 5px;
		    position: absolute;
		    right: 15px;
		    width: 220px;
		}
</style>
</head>
<body scroll="no" onload="init()">
    <div id="x-desktop">
    <div id="iconPersonel" class="desktopIcon ui-draggable" style="background: url('Desktop/images/icon/7.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 15px; top: 15px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('11', 'اطلاعات پرسنل', 'View/Personel/Index.jsp', 900, 500);">اطلاعات پرسنل</div>
		<div id="iconStation" class="desktopIcon ui-draggable" style="background: url('Desktop/images/icon/8.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 15px; top: 115px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('12', 'ایستگاه ها', 'View/Station/Index.jsp', 1020, 400);">ایستگاه ها</div>
		<div id="iconTerminal" class="desktopIcon ui-draggable" style="background: url('Desktop/images/icon/3.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 15px; top: 215px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('14', 'اطلاعات ترمینال', 'View/Terminal/Index.jsp', 800, 600);">اطلاعات ترمینال</div>
	 	<div id="iconUser" class="desktopIcon ui-draggable" style="background: url('Desktop/images/icon/users.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 15px; top: 315px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('15', 'لیست کاربران', 'View/User/Index.jsp', 800, 400);">لیست کاربران</div>
	 	<div id="iconVehicle" class="desktopIcon ui-draggable" style="background: url('./Desktop/images/icon/Bus.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 15px; top: 415px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('36', 'اتوبوس ها', 'View/Vehicle/Index.jsp', 1020, 600);">اتوبوس ها</div>
	 	<div id="iconLocation" class="desktopIcon ui-draggable" style="background: url('./Desktop/images/icon/Location.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 150px; top: 15px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('36', ' مکانها', 'View/Location/Index.jsp', 1020, 600);">مکانها</div>
	 	<div id="iconDeviceGroup" class="desktopIcon ui-draggable" style="background: url('./Desktop/images/icon/DeviceGroup.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 150px; top: 115px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('36', 'گروههای دستگاه', 'View/Device/DeviceGroup/Index.jsp', 1020, 600);">گروههای دستگاه</div>
	 	<div id="iconLine" class="desktopIcon ui-draggable" style="background: url('./Desktop/images/icon/Line.png') no-repeat scroll 15px 5px rgba(255, 255, 255, 0.05); left: 150px; top: 215px;" onmouseover="iconOver(this);" onmouseout="iconOut(this);" ondblclick="activeWindowId = 'winSoldierSelectionCaseFrame'; ChechAccessforOpenWindows('36', 'خطوط', 'View/Line/Index.jsp', 1020, 600);">خطوط</div>
	 		
	 		
	 		<div id="messageMarquee">
		<table style="width: 100%;" cellpadding="0" cellspacing="0">
			<tbody><tr>
				<td style="padding: 4px 4px 9px; text-align: center; border-radius: 3px 3px 3px 3px; background: none repeat scroll 0% 0% rgba(255, 255, 255, 0.1); cursor: pointer;" onclick="openWindow('MessageList', 'اخبار و پیامها', 'edit/Message/list.jsp', 800, 500);" onmouseover="this.style.cursor = 'pointer';">اطلاع رسانی</td>
			</tr>
			<tr>
				<td>
					<marquee direction="up" onmouseover="this.stop();" onmouseout="this.start();" behavior="scroll" loop="true" scrollamount="1" scrolldelay="10" style="height: 220px;">
						<table class="grid" cellpadding="0" cellspacing="5">
							<tbody id="entitybody"><tr id="pattern1590" class="oddRow" style="" onmouseover="" onmouseout="normLight(this.id);" ondblclick="show(this.id.substring(7));">
									 
									<!--  Sample Data for Test ** Msalehi ** -->
									<tr id="pattern1587" class="oddRow" style="" onmouseover="" onmouseout="normLight(this.id);" ondblclick="show(this.id.substring(7));">
										<td style="border: 0px none ! important; background: none repeat scroll 0% 0% rgba(255, 255, 255, 0.1); padding: 3px 7px 7px; border-radius: 3px 3px 3px 3px; line-height: 140%; text-align: justify; direction: rtl;">
											<a href="javascript:{}" style="text-decoration: none;" onclick="show(this.id.substring(2));" id="id1590"><span id="tablefldTitle1590">راه اندازی فاز نخست سامانه مدیریت اتوبوسرانی شهری و کارت شهروندی		</span></a>
										</td>
									</tr>
									<tr id="pattern1587" class="oddRow" style="" onmouseover="" onmouseout="normLight(this.id);" ondblclick="show(this.id.substring(7));">
										<td style="border: 0px none ! important; background: none repeat scroll 0% 0% rgba(255, 255, 255, 0.1); padding: 3px 7px 7px; border-radius: 3px 3px 3px 3px; line-height: 140%; text-align: justify; direction: rtl;">
											<a href="javascript:{}" style="text-decoration: none;" onclick="show(this.id.substring(2));" id="id1590"><span id="tablefldTitle1590">اطلاعات ایستگاه ها به طور کامل در سیستم وارد گردیده است	</span></a>
										</td>
									</tr>
									<tr id="pattern1587" class="oddRow" style="" onmouseover="" onmouseout="normLight(this.id);" ondblclick="show(this.id.substring(7));">
										<td style="border: 0px none ! important; background: none repeat scroll 0% 0% rgba(255, 255, 255, 0.1); padding: 3px 7px 7px; border-radius: 3px 3px 3px 3px; line-height: 140%; text-align: justify; direction: rtl;">
											<a href="javascript:{}" style="text-decoration: none;" onclick="show(this.id.substring(2));" id="id1590"><span id="tablefldTitle1590">توجه به زودی فاز دوم سامانه در سیستم راه اندازی میگردد		</span></a>
										</td>
									</tr>
									<tr id="pattern1587" class="oddRow" style="" onmouseover="" onmouseout="normLight(this.id);" ondblclick="show(this.id.substring(7));">
										<td style="border: 0px none ! important; background: none repeat scroll 0% 0% rgba(255, 255, 255, 0.1); padding: 3px 7px 7px; border-radius: 3px 3px 3px 3px; line-height: 140%; text-align: justify; direction: rtl;">
											<a href="javascript:{}" style="text-decoration: none;" onclick="show(this.id.substring(2));" id="id1590"><span id="tablefldTitle1590"> جهت بهبود سیستم نظرات خود را با ما درمیان بگذارید		</span></a>
										</td>
									</Tr>
					 
									<!--  Sample Data for Test ** Msalehi ** -->
									
							</tbody>
						</table>
					</marquee>
				</td>
			</tr><tr>
		</tr></tbody></table>	
	</div>
        <div>	
        </div>
    </div>
    <div id="ux-taskbar">
        <div id="ux-taskbar-start">
        </div>
        <div id="ux-taskbuttons-panel">
        </div>
        <div class="x-clear">
        </div>
    </div>
</body>
</html>
