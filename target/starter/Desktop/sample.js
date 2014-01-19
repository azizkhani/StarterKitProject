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
            title: 'سامانه مدیریت اتوبوسرانی شهری و کارت شهروندی ',
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
                handler: function (src) {document.location='j_spring_security_logout'}
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
				    text: 'انواع مقاطع تحصیلی',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo3');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo1'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=1" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'بانکها',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo2');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo2'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=2" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'انواع گواهینامه',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo3');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo3'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=3" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'مدلهای خودرو',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo4');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo4'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=4" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'مارکهای خودرو',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo5');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo5'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=5" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'انواع سوخت',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo6');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo6'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=6" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'انواع کارت درخواستی',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo7');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo7'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=7" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'انواع نقشها',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo8');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo8'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=8" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				}
				,
				{
				    text: 'انواع مدلهای دستگاه',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo9');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo9'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=9" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				}
				,
				{
				    text: 'انواع مارکهای دستگاه',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo10');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo10'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=10" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
				    text: 'مکانهای نصب دستگاه در اتوبوس',
				    iconCls: 'bogus',
				    scope: this,
				    handler: function (src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('PageBaseInfo10');
				        if (!win) {
				            win = desktop.createWindow({
				                id: 'PageBaseInfo10'
				                , title: src.text
				                , width: 650
				                , height: 400
								, html: '<iframe src="View/BaseInfo/Index.jsp?parentId=11" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
				            });
				        }
				        win.show();
				    }
				},
				{
			        text: 'مکان ها',
                    iconCls: 'bogus',
                    scope: this,
                    handler: function (src) {
                        var desktop = this.app.getDesktop();
                        var win = desktop.getWindow('PageIndicator');
                        if (!win) {
                            win = desktop.createWindow({
                                id: 'PageIndicator'
				                , title: src.text
				                , width: 900
				                , height: 500
								, html: '<iframe src="View/Location/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls: 'bogus'
				                , plain: true
                            });
                        }
                        win.show();
                    }
			    },
		 
				{
			        text: 'گروه بندی تجهیزات و دستگاه ها',
                    iconCls: 'bogus',
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
								, html: '<iframe src="View/Device/DeviceGroup/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
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
                items: [ 
                         {
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
		                }, 
		                {
		                    text: 'ترمینال',
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
										, html: '<iframe src="View/Terminal/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
						                , iconCls: 'bogus'
						                , plain: true
		                            });
		                        }
		                        win.show();
		                    }
		                }, 
		                {
		                    text: 'ایستگاهها',
		                    iconCls: 'bogus',
		                    scope: this,
		                    handler: function (src) {
		                        var desktop = this.app.getDesktop();
		                        var win = desktop.getWindow('Station');
		                        if (!win) {
		                            win = desktop.createWindow({
		                                id: 'Station'
						                , title: src.text
						                , width: 900
						                , height: 450
										, html: '<iframe src="View/Station/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
						                , iconCls: 'bogus'
						                , plain: true
		                            });
		                        }
		                        win.show();
		                    }
		                }, 
		                {
		                    text: 'خطوط',
		                    iconCls: 'bogus',
		                    scope: this,
		                    handler: function (src) {
		                        var desktop = this.app.getDesktop();
		                        var win = desktop.getWindow('Line');
		                        if (!win) {
		                            win = desktop.createWindow({
		                                id: 'Line'
						                , title: src.text
						                , width: 900
						                , height: 450
										, html: '<iframe src="View/Line/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
						                , iconCls: 'bogus'
						                , plain: true
		                            });
		                        }
		                        win.show();
		                    }
		                },
		                {
		                    text: 'دستگاه ها',
		                    iconCls: 'bogus',
		                    scope: this,
		                    handler: function (src) {
		                        var desktop = this.app.getDesktop();
		                        var win = desktop.getWindow('Line');
		                        if (!win) {
		                            win = desktop.createWindow({
		                                id: 'Line'
						                , title: src.text
						                , width: 900
						                , height: 450
										, html: '<iframe src="View/Device/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
						                , iconCls: 'bogus'
						                , plain: true
		                            });
		                        }
		                        win.show();
		                    }
		                },
		                {
		                    text: ' وسیله نقلیه ',
		                    iconCls: 'bogus',
		                    scope: this,
		                    handler: function (src) {
		                        var desktop = this.app.getDesktop();
		                        var win = desktop.getWindow('Vehicle');
		                        if (!win) {
		                            win = desktop.createWindow({
		                                id: 'Vehicle'
						                , title: src.text
						                , width: 900
						                , height: 450
										, html: '<iframe src="View/Vehicle/Index.jsp" width="100%" height="100%" frameborder="0"></iframe>'
						                , iconCls: 'bogus'
						                , plain: true
		                            });
		                        }
		                        win.show();
		                    }
		                }
		              ]
            }
        }
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
                items: [
							//////////////////////////////////////////////Line////////////////////////////////////////
							{
							    text: 'گزارش جزئیات کارکرد خط',
							    iconCls: 'bogus',
							    scope: this,
							    handler: function (src) {
							        var desktop = this.app.getDesktop();
							        var win = desktop.getWindow('Vehicle');
							        if (!win) {
							            win = desktop.createWindow({
							                id: 'Vehicle'
							                , title: src.text
							                , width: 900
							                , height: 450
											, html: '<iframe src="View/packet/passenger/report/line/LineWorkDetail.jsp" width="100%" height="100%" frameborder="0"></iframe>'
							                , iconCls: 'bogus'
							                , plain: true
							            });
							        }
							        win.show();
							    }
							}
							,
							{
							    text: 'گزارش کارکرد خطوط - ساعتی',
							    iconCls: 'bogus',
							    scope: this,
							    handler: function (src) {
							        var desktop = this.app.getDesktop();
							        var win = desktop.getWindow('Vehicle');
							        if (!win) {
							            win = desktop.createWindow({
							                id: 'Vehicle'
							                , title: src.text
							                , width: 900
							                , height: 450
											, html: '<iframe src="View/packet/passenger/report/line/LineWorkHourly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
							                , iconCls: 'bogus'
							                , plain: true
							            });
							        }
							        win.show();
							    }
							}
							,
							{
							    text: 'گزارش کارکرد خطوط – روزانه ',
							    iconCls: 'bogus',
							    scope: this,
							    handler: function (src) {
							        var desktop = this.app.getDesktop();
							        var win = desktop.getWindow('Vehicle');
							        if (!win) {
							            win = desktop.createWindow({
							                id: 'Vehicle'
							                , title: src.text
							                , width: 900
							                , height: 450
											, html: '<iframe src="View/packet/passenger/report/line/LineWorkDaily.jsp" width="100%" height="100%" frameborder="0"></iframe>'
							                , iconCls: 'bogus'
							                , plain: true
							            });
							        }
							        win.show();
							    }
							}
							,
							{
								text: 'گزارش کارکرد خطوط – ماهیانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/line/LineWorkMonthly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							//////////////////////////////////////////////bus////////////////////////////////////////
							,
							{
								text: 'گزارش جزئیات کارکرد اتوبوس',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/bus/BusWorkDetail.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد  اتوبوس - ساعتی',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/bus/BusWorkHourly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد  اتوبوس – روزانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/bus/BusWorkDaily.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد  اتوبوس – ماهیانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/bus/BusWorkMonthly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							//////////////////////////////////////////////Driver////////////////////////////////////////
							,
							{
								text: 'گزارش جزئیات کارکرد راننده',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/driver/DriverWorkDetail.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد راننده - ساعتی',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/driver/DriverWorkHourly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد راننده – روزانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/driver/DriverWorkDaily.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد راننده – ماهیانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/driver/DriverWorkMonthly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							//////////////////////////////////////////////Device////////////////////////////////////////
							,
							{
								text: 'گزارش جزئیات کارکرد دستگاه',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/device/DeviceWorkDetail.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد دستگاه - ساعتی',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/device/DeviceWorkHourly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد دستگاه – روزانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/device/DeviceWorkDaily.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							,
							{
								text: 'گزارش کارکرد دستگاه – ماهیانه ',
								iconCls: 'bogus',
								scope: this,
								handler: function (src) {
									var desktop = this.app.getDesktop();
									var win = desktop.getWindow('Vehicle');
									if (!win) {
										win = desktop.createWindow({
											id: 'Vehicle'
												, title: src.text
												, width: 900
												, height: 450
												, html: '<iframe src="View/packet/passenger/report/device/deviceWorkMonthly.jsp" width="100%" height="100%" frameborder="0"></iframe>'
													, iconCls: 'bogus'
														, plain: true
										});
									}
									win.show();
								}
							}
							
                        ]
            }
        }
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
                }
                ]
            }
        }
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
