/**
 * layout - jQuery EasyUI
 * 
 * Copyright (c) 2009-2013 www.jeasyui.com. All rights reserved.
 *
 * Licensed under the GPL or commercial licenses
 * To use it on other terms please contact us: info@jeasyui.com
 * http://www.gnu.org/licenses/gpl.txt
 * http://www.jeasyui.com/license_commercial.php
 * 
 * Dependencies:
 *   resizable
 *   panel
 */
(function($){
	var resizing = false;	// indicate if the region panel is resizing
	
	function setSize(container){
		var state = $.data(container, 'layout');
		var opts = state.options;
		var panels = state.panels;
		
		var cc = $(container);
		if (container.tagName == 'BODY'){
			cc._fit();
		} else {
			opts.fit ? cc.css(cc._fit()) : cc._fit(false);
		}
		
//		opts.fit ? cc.css(cc._fit()) : cc._fit(false);
//		if (opts.fit == true){
//			var p = cc.parent();
//			p.addClass('panel-noscroll');
//			if (p[0].tagName == 'BODY') $('html').addClass('panel-fit');
//			cc.width(p.width());
//			cc.height(p.height());
//		}
		
		var cpos = {
			top:0,
			left:0,
			width:cc.width(),
			height:cc.height()
		};
		
		setVSize(isVisible(panels.expandNorth) ? panels.expandNorth : panels.north, 'n');
		setVSize(isVisible(panels.expandSouth) ? panels.expandSouth : panels.south, 's');
		setHSize(isVisible(panels.expandEast) ? panels.expandEast : panels.east, 'e');
		setHSize(isVisible(panels.expandWest) ? panels.expandWest : panels.west, 'w');
		
		panels.center.panel('resize', cpos);
		
		function getPanelHeight(pp){
			var opts = pp.panel('options');
			return Math.min(Math.max(opts.height, opts.minHeight), opts.maxHeight);
		}
		function getPanelWidth(pp){
			var opts = pp.panel('options');
			return Math.min(Math.max(opts.width, opts.minWidth), opts.maxWidth);
		}
		function setVSize(pp, type){
			if (!pp.length){return}
			var opts = pp.panel('options');
			var height = getPanelHeight(pp);
			pp.panel('resize', {
				width: cc.width(),
				height: height,
				left: 0,
				top: (type=='n' ? 0 : cc.height()-height)
			});
			cpos.height -= height;
			if (type == 'n'){
				cpos.top += height;
				if (!opts.split && opts.border){cpos.top--;}
			}
			if (!opts.split && opts.border){
				cpos.height++;
			}
		}
		function setHSize(pp, type){
			if (!pp.length){return}
			var opts = pp.panel('options');
			var width = getPanelWidth(pp);
			pp.panel('resize', {
				width: width,
				height: cpos.height,
				left: (type=='e' ? cc.width()-width : 0),
				top: cpos.top
			});
			cpos.width -= width;
			if (type == 'w'){
				cpos.left += width;
				if (!opts.split && opts.border){cpos.left--;}
			}
			if (!opts.split && opts.border){cpos.width++;}
		}
	}
	
	/**
	 * initialize and wrap the layout
	 */
	function init(container){
		var cc = $(container);
		
//		if (cc[0].tagName == 'BODY'){
//			$('html').addClass('panel-fit');
//		}
		cc.addClass('layout');
		
		function _add(cc){
			cc.children('div').each(function(){
				var opts = $.fn.layout.parsePanelOptions(this);
				if ('north,south,east,west,center'.indexOf(opts.region) >= 0){
					addPanel(container, opts, this);
				}
			});
		}
		
		cc.children('form').length ? _add(cc.children('form')) : _add(cc);
		
		cc.append('<div class="layout-split-proxy-h"></div><div class="layout-split-proxy-v"></div>');
		
		cc.bind('_resize', function(e,force){
			var opts = $.data(container, 'layout').options;
			if (opts.fit == true || force){
				setSize(container);
			}
			return false;
		});
	}
	
	/**
	 * Add a new region panel on specified element
	 */
	function addPanel(container, param, el){
		param.region = param.region || 'center';
		var panels = $.data(container, 'layout').panels;
		var cc = $(container);
		var dir = param.region;
		
		if (panels[dir].length) return;	// the region panel is already exists
		
		var pp = $(el);
		if (!pp.length){
			pp = $('<div></div>').appendTo(cc);	// the predefined panel isn't exists, create a new panel instead
		}
		
		var popts = $.extend({}, $.fn.layout.paneldefaults, {
			width: (pp.length ? parseInt(pp[0].style.width) || pp.outerWidth() : 'auto'),
			height: (pp.length ? parseInt(pp[0].style.height) || pp.outerHeight() : 'auto'),
			doSize: false,
			collapsible: true,
			cls: ('layout-panel layout-panel-' + dir),
			bodyCls: 'layout-body',
			onOpen: function(){
				var tool = $(this).panel('header').children('div.panel-tool');
				tool.children('a.panel-tool-collapse').hide();	// hide the old collapse button
				
				var buttonDir = {north:'up',south:'down',east:'right',west:'left'};
				if (!buttonDir[dir]) return;
				
				var iconCls = 'layout-button-' + buttonDir[dir];
				// add collapse tool to panel header
				var t = tool.children('a.' + iconCls);
				if (!t.length){
					t = $('<a href="javascript:void(0)"></a>').addClass(iconCls).appendTo(tool);
					t.bind('click', {dir:dir}, function(e){
						collapsePanel(container, e.data.dir);
						return false;
					});
				}
				$(this).panel('options').collapsible ? t.show() : t.hide();
			}
		}, param);
		
		pp.panel(popts);	// create region panel
		panels[dir] = pp;
		
		if (pp.panel('options').split){
			var panel = pp.panel('panel');
			panel.addClass('layout-split-' + dir);
			
			var handles = '';
			if (dir == 'north') handles = 's';
			if (dir == 'south') handles = 'n';
			if (dir == 'east') handles = 'w';
			if (dir == 'west') handles = 'e';
			
			panel.resizable($.extend({}, {
				handles:handles,
				onStartResize: function(e){
					resizing = true;
					
					if (dir == 'north' || dir == 'south'){
						var proxy = $('>div.layout-split-proxy-v', container);
					} else {
						var proxy = $('>div.layout-split-proxy-h', container);
					}
					var top=0,left=0,width=0,height=0;
					var pos = {display: 'block'};
					if (dir == 'north'){
						pos.top = parseInt(panel.css('top')) + panel.outerHeight() - proxy.height();
						pos.left = parseInt(panel.css('left'));
						pos.width = panel.outerWidth();
						pos.height = proxy.height();
					} else if (dir == 'south'){
						pos.top = parseInt(panel.css('top'));
						pos.left = parseInt(panel.css('left'));
						pos.width = panel.outerWidth();
						pos.height = proxy.height();
					} else if (dir == 'east'){
						pos.top = parseInt(panel.css('top')) || 0;
						pos.left = parseInt(panel.css('left')) || 0;
						pos.width = proxy.width();
						pos.height = panel.outerHeight();
					} else if (dir == 'west'){
						pos.top = parseInt(panel.css('top')) || 0;
						pos.left = panel.outerWidth() - proxy.width();
						pos.width = proxy.width();
						pos.height = panel.outerHeight();
					}
					proxy.css(pos);
					
					$('<div class="layout-mask"></div>').css({
						left:0,
						top:0,
						width:cc.width(),
						height:cc.height()
					}).appendTo(cc);
				},
				onResize: function(e){
					if (dir == 'north' || dir == 'south'){
						var proxy = $('>div.layout-split-proxy-v', container);
						proxy.css('top', e.pageY - $(container).offset().top - proxy.height()/2);
					} else {
						var proxy = $('>div.layout-split-proxy-h', container);
						proxy.css('left', e.pageX - $(container).offset().left - proxy.width()/2);
					}
					return false;
				},
				onStopResize: function(e){
//					$('>div.layout-split-proxy-v', container).css('display','none');
//					$('>div.layout-split-proxy-h', container).css('display','none');
//					var opts = pp.panel('options');
//					opts.width = panel.outerWidth();
//					opts.height = panel.outerHeight();
//					opts.left = panel.css('left');
//					opts.top = panel.css('top');
//					pp.panel('resize');
					
					cc.children('div.layout-split-proxy-v,div.layout-split-proxy-h').hide();
					pp.panel('resize',e.data);
					
					setSize(container);
					resizing = false;
					
					cc.find('>div.layout-mask').remove();
				}
			}, param));
		}
	}
	
	/**
	 * remove a region panel
	 */
	function removePanel(container, region){
		var panels = $.data(container, 'layout').panels;
		if (panels[region].length){
			panels[region].panel('destroy');
			panels[region] = $();
			var expandP = 'expand' + region.substring(0,1).toUpperCase() + region.substring(1);
			if (panels[expandP]){
				panels[expandP].panel('destroy');
				panels[expandP] = undefined;
			}
		}
	}
	
	function collapsePanel(container, region, animateSpeed){
		if (animateSpeed == undefined){animateSpeed = 'normal';}
		var panels = $.data(container, 'layout').panels;
		
		var p = panels[region];
		var popts = p.panel('options');
		if (popts.onBeforeCollapse.call(p) == false) return;
		
		// expand panel name: expandNorth, expandSouth, expandWest, expandEast
		var expandP = 'expand' + region.substring(0,1).toUpperCase() + region.substring(1);
		if (!panels[expandP]){
			panels[expandP] = createExpandPanel(region);
			panels[expandP].panel('panel').bind('click', function(){
				var copts = getOption();
				p.panel('expand',false).panel('open').panel('resize', copts.collapse);
				
				p.panel('panel').animate(copts.expand, function(){
					$(this).unbind('.layout').bind('mouseleave.layout', {region:region}, function(e){
						if (resizing == true) return;
						collapsePanel(container, e.data.region);
					});
				});
				
				return false;
			});
		}
		
		var copts = getOption();
		if (!isVisible(panels[expandP])){
			panels.center.panel('resize', copts.resizeC);
		}
		p.panel('panel').animate(copts.collapse, animateSpeed, function(){
			p.panel('collapse',false).panel('close');
			panels[expandP].panel('open').panel('resize', copts.expandP);
			
			$(this).unbind('.layout');
		});
		
		/**
		 * create expand panel
		 */
		function createExpandPanel(dir){
			var icon;
			if (dir == 'east') icon = 'layout-button-left'
			else if (dir == 'west') icon = 'layout-button-right'
			else if (dir == 'north') icon = 'layout-button-down'
			else if (dir == 'south') icon = 'layout-button-up';
			
			var p = $('<div></div>').appendTo(container);
			p.panel($.extend({}, $.fn.layout.paneldefaults, {
				cls: ('layout-expand layout-expand-' + dir),
				title: '&nbsp;',
				closed: true,
				doSize: false,
				tools: [{
					iconCls: icon,
					handler:function(){
						expandPanel(container, region);
						return false;
					}
				}]
			}));
			p.panel('panel').hover(
				function(){$(this).addClass('layout-expand-over');},
				function(){$(this).removeClass('layout-expand-over');}
			);
			return p;
		}
		
		/**
		 * get collapse option:{
		 *   resizeC:{},
		 *   expand:{},
		 *   expandP:{},	// the expand holder panel
		 *   collapse:{}
		 * }
		 */
		function getOption(){
			var cc = $(container);
			var copts = panels.center.panel('options');
			
			if (region == 'east'){
				var ww = copts.width + popts.width - 28;
				if (popts.split || !popts.border){ww++;}
				return {
					resizeC:{
						width: ww
					},
					expand:{
						left: cc.width() - popts.width
					},
					expandP:{
						top: copts.top,
						left: cc.width() - 28,
						width: 28,
						height: copts.height
					},
					collapse:{
						left: cc.width(),
						top: copts.top,
						height: copts.height
					}
				};
			} else if (region == 'west'){
				var ww = copts.width + popts.width - 28;
				if (popts.split || !popts.border){ww++;}
				return {
					resizeC:{
						width: ww,
						left: 28 - 1
					},
					expand:{
						left: 0
					},
					expandP:{
						left: 0,
						top: copts.top,
						width: 28,
						height: copts.height
					},
					collapse:{
						left: -popts.width,
						top: copts.top,
						height: copts.height
					}
				};
			} else if (region == 'north'){
				var hh = copts.height;
				if (!isVisible(panels.expandNorth)){
					hh += popts.height - 28 + ((popts.split || !popts.border)?1:0);
				}
				panels.east.add(panels.west).add(panels.expandEast).add(panels.expandWest).panel('resize', {
					top: 28 - 1,
					height: hh
				});
				
				return {
					resizeC:{
						top: 28 - 1,
						height: hh
					},
					expand:{
						top:0
					},
					expandP:{
						top: 0,
						left: 0,
						width: cc.width(),
						height: 28
					},
					collapse:{
						top: -popts.height,
						width: cc.width()
					}
				};
			} else if (region == 'south'){
				var hh = copts.height;
				if (!isVisible(panels.expandSouth)){
					hh += popts.height - 28 + ((popts.split || !popts.border)?1:0);
				}
				panels.east.add(panels.west).add(panels.expandEast).add(panels.expandWest).panel('resize', {
					height: hh
				});
				
				return {
					resizeC:{
						height: hh
					},
					expand:{
						top: cc.height()-popts.height
					},
					expandP:{
						top: cc.height() - 28,
						left: 0,
						width: cc.width(),
						height: 28
					},
					collapse:{
						top: cc.height(),
						width: cc.width()
					}
				};
			}
		}
	}
	
	function expandPanel(container, region){
		var panels = $.data(container, 'layout').panels;
		
		var p = panels[region];
		var popts = p.panel('options');
		if (popts.onBeforeExpand.call(p) == false){return;}
		
		var eopts = getOption();
		var expandP = 'expand' + region.substring(0,1).toUpperCase() + region.substring(1);
		if (panels[expandP]){
			panels[expandP].panel('close');
			p.panel('panel').stop(true,true);
			p.panel('expand',false).panel('open').panel('resize', eopts.collapse);
			p.panel('panel').animate(eopts.expand, function(){
				setSize(container);
			});
		}
		
		/**
		 * get expand option: {
		 *   collapse:{},
		 *   expand:{}
		 * }
		 */
		function getOption(){
			var cc = $(container);
			var copts = panels.center.panel('options');
			
			if (region == 'east' && panels.expandEast){
				return {
					collapse:{
						left: cc.width(),
						top: copts.top,
						height: copts.height
					},
					expand:{
						left: cc.width() - panels['east'].panel('options').width
					}
				};
			} else if (region == 'west' && panels.expandWest){
				return {
					collapse:{
						left: -panels['west'].panel('options').width,
						top: copts.top,
						height: copts.height
					},
					expand:{
						left: 0
					}
				};
			} else if (region == 'north' && panels.expandNorth){
				return {
					collapse:{
						top: -panels['north'].panel('options').height,
						width: cc.width()
					},
					expand:{
						top: 0
					}
				};
			} else if (region == 'south' && panels.expandSouth){
				return {
					collapse:{
						top: cc.height(),
						width: cc.width()
					},
					expand:{
						top: cc.height()-panels['south'].panel('options').height
					}
				};
			}
		}
	}
	
//	function bindEvents(container){
//		var panels = $.data(container, 'layout').panels;
//		var cc = $(container);
//		
//		// bind east panel events
//		if (panels.east.length){
//			panels.east.panel('panel').bind('mouseover','east',_collapse);
//		}
//		
//		// bind west panel events
//		if (panels.west.length){
//			panels.west.panel('panel').bind('mouseover','west',_collapse);
//		}
//		
//		// bind north panel events
//		if (panels.north.length){
//			panels.north.panel('panel').bind('mouseover','north',_collapse);
//		}
//		
//		// bind south panel events
//		if (panels.south.length){
//			panels.south.panel('panel').bind('mouseover','south',_collapse);
//		}
//		
//		panels.center.panel('panel').bind('mouseover','center',_collapse);
//		
//		function _collapse(e){
//			if (resizing == true) return;
//			
//			if (e.data != 'east' && isVisible(panels.east) && isVisible(panels.expandEast)){
//				collapsePanel(container, 'east');
//			}
//			if (e.data != 'west' && isVisible(panels.west) && isVisible(panels.expandWest)){
//				collapsePanel(container, 'west');
//			}
//			if (e.data != 'north' && isVisible(panels.north) && isVisible(panels.expandNorth)){
//				collapsePanel(container, 'north');
//			}
//			if (e.data != 'south' && isVisible(panels.south) && isVisible(panels.expandSouth)){
//				collapsePanel(container, 'south');
//			}
//			return false;
//		}
//	}
	
	function isVisible(pp){
		if (!pp) return false;
		if (pp.length){
			return pp.panel('panel').is(':visible');
		} else {
			return false;
		}
	}
	
	function initCollapse(container){
		var panels = $.data(container, 'layout').panels;
		if (panels.east.length && panels.east.panel('options').collapsed) {
			collapsePanel(container, 'east', 0);
		}
		if (panels.west.length && panels.west.panel('options').collapsed) {
			collapsePanel(container, 'west', 0);
		}
		if (panels.north.length && panels.north.panel('options').collapsed) {
			collapsePanel(container, 'north', 0);
		}
		if (panels.south.length && panels.south.panel('options').collapsed) {
			collapsePanel(container, 'south', 0);
		}
	}
	
	$.fn.layout = function(options, param){
		if (typeof options == 'string'){
			return $.fn.layout.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'layout');
			if (state){
				$.extend(state.options, options);
			} else {
				var opts = $.extend({}, $.fn.layout.defaults, $.fn.layout.parseOptions(this), options);
				$.data(this, 'layout', {
					options: opts,
					panels: {center:$(), north:$(), south:$(), east:$(), west:$()}
				});
				init(this);
//				bindEvents(this);
			}
			setSize(this);
			initCollapse(this);
		});
	};
	
	$.fn.layout.methods = {
		resize: function(jq){
			return jq.each(function(){
				setSize(this);
			});
		},
		panel: function(jq, region){
			return $.data(jq[0], 'layout').panels[region];
		},
		collapse: function(jq, region){
			return jq.each(function(){
				collapsePanel(this, region);
			});
		},
		expand: function(jq, region){
			return jq.each(function(){
				expandPanel(this, region);
			});
		},
		add: function(jq, options){
			return jq.each(function(){
				addPanel(this, options);
				setSize(this);
				if ($(this).layout('panel', options.region).panel('options').collapsed){
					collapsePanel(this, options.region, 0);
				}
			});
		},
		remove: function(jq, region){
			return jq.each(function(){
				removePanel(this, region);
				setSize(this);
			});
		}
	};
	
	$.fn.layout.parseOptions = function(target){
		return $.extend({}, $.parser.parseOptions(target,[{fit:'boolean'}]));
	};
	
	$.fn.layout.defaults = {
		fit: false
	};
	
	$.fn.layout.parsePanelOptions = function(target){
		var t = $(target);
		return $.extend({}, $.fn.panel.parseOptions(target), 
				$.parser.parseOptions(target, [
					'region',{split:'boolean',minWidth:'number',minHeight:'number',maxWidth:'number',maxHeight:'number'}
				]));
	};
	
	$.fn.layout.paneldefaults = $.extend({}, $.fn.panel.defaults, {
		region:null,	// possible values are: 'north','south','east','west','center'
		split:false,
		minWidth:10,
		minHeight:10,
		maxWidth:10000,
		maxHeight:10000
	});
})(jQuery);
