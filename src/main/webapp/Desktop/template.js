MyDesktop.BogusMenuDocument = Ext.extend(Ext.app.Module, {
    init : function() {
        this.launcher = {
            text: 'مدیریت اسناد و بایگانی'
            , iconCls: 'bogus'
            , handler: function() {
				return false;
			}
			, menu : {
                items : [{
                    text : 'اسناد و بایگانی'
                    , iconCls : 'bogus'
					, scope : this
                    , handler : function(src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('id1');
				        if(!win) {
				            win = desktop.createWindow({
				                id : 'id1'
				                , title : src.text
				                , width : 900
				                , height : 500
								, html : '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls : 'bogus'
				                , plain : true
				            });
				        }
				        win.show();
					}
				}
				, {
                    text : 'ضمائم اسناد'
                    , iconCls :'bogus'
					, scope : this
                    , handler : function(src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('id2');
				        if(!win) {
				            win = desktop.createWindow({
				                id : 'id2'
				                , title : src.text
				                , width : 900
				                , height : 500
								, html : '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls : 'bogus'
				                , plain : true
				            });
				        }
				        win.show();
					}
                }
				, {
                    text : 'اسناد مرتبط'
                    , iconCls :'bogus'
                    , scope : this
					, handler : function(src) {
				        var desktop = this.app.getDesktop();
				        var win = desktop.getWindow('id3');
				        if(!win) {
				            win = desktop.createWindow({
				                id : 'id3'
				                , title : src.text
				                , width : 900
				                , height : 500
								, html : '<iframe src="" width="100%" height="100%" frameborder="0"></iframe>'
				                , iconCls : 'bogus'
				                , plain : true
				            });
				        }
				        win.show();
					}
                }]
            }
        }
    }
});