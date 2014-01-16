<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

/*!
* Ext JS Library 3.2.1
* Copyright(c) 2006-2010 Ext JS, Inc.
* licensing@extjs.com
* http://www.extjs.com/license
*/

MyDesktop = new Ext.app.App({
    init: function () {
        Ext.QuickTips.init();
    },
    getModules: function () {
        return [
            new MyDesktop.MenuBaseInformation(),
			new MyDesktop.MenuUC1(),
			new MyDesktop.MenuUC2(),
			new MyDesktop.MenuUC4(),
		];
    },
    getStartConfig: function () {
        return { 
            title: 'سیستم جامع بازرسی ستاد مشترک سپاه',
            iconCls: 'user',
            height: 350,
            width: 350,
            toolItems: [{
                text: 'راهنما',
                iconCls: 'settings',
                scope: this
            }, '-', {
                text: 'تنظيمات',
                iconCls: 'settings',
                scope: this
            }, '-', {
                text: 'خروج',
                iconCls: 'logout',
                scope: this,
                handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageIndicator');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageIndicator'
				                , title: src.text
				                , width: 720
				                , height: 420
								, html: '<iframe src=<c:url value="j_spring_security_logout" /> width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
            }]
        };
    }
});

//	اطلاعات پایه
MyDesktop.MenuBaseInformation = Ext.extend(Ext.app.Module, {
    init: function () {
        this.launcher = {
            text: 'اطلاعات پایه'
            , iconCls: 'bogus'
            , handler: function () {
                return false;
            }
            //	LEVEL2:BEGIN
			, menu: {
			    items: [ 
			    {
			        text: 'شاخص'
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageIndicator');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageIndicator'
				                , title: src.text
				                , width: 720
				                , height: 420
								, html: '<iframe src="View/Evaluation/Indicator/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    },
			    
			    {
			        text: 'معرف'
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageReagent');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageReagent'
				                , title: src.text
				                , width: 720
				                , height: 420
								, html: '<iframe src="View/Evaluation/Reagent/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    },
			    
			    {
			        text: 'سنجه'
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageMetric');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageMetric'
				                , title: src.text
				                , width: 720
				                , height: 420
								, html: '<iframe src="View/Evaluation/Metric/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    },
			    {
			        text: 'مراحل خدمتی'
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageBaseInfo1');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageBaseInfo1'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/baseInfo/Index.jsp?parentId=1" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    }
			    ,
			    {
			        text: 'انواع منابع سنجش'
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageBaseInfo2');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageBaseInfo2'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/baseInfo/Index.jsp?parentId=2" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    },
			    {
			        text: 'تعریف دوره های ارزشیابی'
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageEvaluationPeriod');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageEvaluationPeriod'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/Evaluation/EvaluationPeriod/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    },
			    
			    {
			        text: 'تست KoReagent '
                    , iconCls: 'bogus'
                    , scope: this
                    , handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageId9');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageReagent'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/Evaluation/KOReagent/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    }
				]
			    //	LEVEL3:END
			}
        }
    }
});

//	عملیات اصلی
MyDesktop.MenuUC1 = Ext.extend(Ext.app.Module, {
    init: function () {
        this.launcher = {
            text: 'عملیات اصلی',
            iconCls: 'bogus',
            handler: function () {
                return false;
            },
            menu: {
                items: [ {
                    text: 'اطلاعات پرسنل',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PagePersonel');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PagePersonel'
				                , title: src.text
				                , width: 900
				                , height: 500
								, html: '<iframe src="View/Personel/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                }, {
                    text: 'ثبت اطلاعات ارزشیابها',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageEvaluator');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageEvaluator'
				                , title: src.text
				                , width: 900
				                , height: 450
								, html: '<iframe src="View/Evaluation/Evaluator/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                },
                {
                    text: 'لیست مشاهده',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageId6');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageId5'
				                , title: src.text
				                , width: 900
				                , height: 600
								, html: '<iframe src="View/Evaluation/EvaluationObservation/Index.jsp?id=2" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                },                {
                    text: 'امتیازات ',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageId5');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageId5'
				                , title: src.text
				                , width: 900
				                , height: 450
								, html: '<iframe src="View/Evaluation/EvaluationScore/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                }]
            }
        }
    },
    createWindow: function (src) {
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('???');
        if (!win) {
            win = desktop.createWindow({
                id: '???',
                title: src.text,
                width: 900,
                height: 500,
                html: '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    }
});

//	گزارشات یزد
MyDesktop.MenuUC2 = Ext.extend(Ext.app.Module, {
    init: function () {
        this.launcher = {
            text: 'گزارشات ',
            iconCls: 'bogus',
            handler: function () {
                return false;
            },
            menu: {
                items: [{
                    text: 'test',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageId2002');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageId2002'
				                , title: src.text
				                , width: 900
				                , height: 600
								, html: '<iframe src="Default.aspx?PageId=2002" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                }]
            }
        }
    },

    createWindow: function (src) {
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('???');
        if (!win) {
            win = desktop.createWindow({
                id: '???',
                title: src.text,
                width: 900,
                height: 500,
                html: '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    }
});



//	مدیریت سیستم
MyDesktop.MenuUC4 = Ext.extend(Ext.app.Module, {
    init: function () {
        this.launcher = {
            text: 'مدیریت سیستم',
            iconCls: 'bogus',
            handler: function () {
                return false;
            },
            menu: {
                items: [{
                    text: 'ليست كاربران',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageId2');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageUser'
				                , title: src.text
				                , width: 900
				                , height: 500
								, html: '<iframe src="View/User/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                }, {
                    text: 'گروههاي كاربري',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageId5');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageGroup'
				                , title: src.text
				                , width: 700
				                , height: 450
								, html: '<iframe src="View/Group/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
                }]
            }
        }
    },

    createUC1Window: function (src) {
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('???');
        if (!win) {
            win = desktop.createWindow({
                id: '???',
                title: src.text,
                width: 900,
                height: 500,
                html: '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },

    createUC2Window: function (src) {
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('???');
        if (!win) {
            win = desktop.createWindow({
                id: '???',
                title: src.text,
                width: 900,
                height: 500,
                html: '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    }
});


function winHTML(url, winId) {
    if (url.indexOf('text:') == 0) {
        url = url.substring(5);
        return url;
    }
    url += (url.indexOf('?') > 0 ? '&' : '?') + 'refreshId=' + new Date().getTime();
    if (winId != 'loginWin')
        parent.lastWinId = winId;
    return '<iframe name="' + winId + 'Frame" src="/' + getContextName() + '/' + url + '" width="100%" height="100%" frameborder="0"></iframe>';
}
function getContextName() {
    var i = location.href.indexOf('/', 8);
    var j = location.href.indexOf('/', i + 1);
    return location.href.substring(i + 1, j);
}
