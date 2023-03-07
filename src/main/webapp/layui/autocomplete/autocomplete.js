if (!Object.keys) {
  Object.keys = (function () {
    var hasOwnProperty = Object.prototype.hasOwnProperty,
        hasDontEnumBug = !({toString: null}).propertyIsEnumerable('toString'),
        dontEnums = [
          'toString',
          'toLocaleString',
          'valueOf',
          'hasOwnProperty',
          'isPrototypeOf',
          'propertyIsEnumerable',
          'constructor'
        ],
        dontEnumsLength = dontEnums.length;

    return function (obj) {
      if (typeof obj !== 'object' && typeof obj !== 'function' || obj === null) throw new TypeError('Object.keys called on non-object');

      var result = [];

      for (var prop in obj) {
        if (hasOwnProperty.call(obj, prop)) result.push(prop);
      }

      if (hasDontEnumBug) {
        for (var i = 0; i < dontEnumsLength; i++) {
          if (hasOwnProperty.call(obj, dontEnums[i])) result.push(dontEnums[i]);
        }
      }
      return result;
    }
  })()
}

layui.define(['jquery', 'laytpl', 'layer'], function (e) {
  "use strict";
  var hint = layui.hint(),
    $ = layui.jquery,
    laytpl = layui.laytpl,
    layer = layui.layer,
    module = 'autocomplete',
    filter = 'layui-autocomplete',
    container = 'layui-form-autocomplete',
    container_focus = 'layui-form-autocomplete-focus',
    container_list = 'layui-anim layui-anim-upbit',
    system = {
      config: {
        template: ['<div class="' + container + '">', '<dl class="' + container_list + '">', '</dl>', '</div>'].join(''),
        layout: ['<dd data-index="{{d.index}}">{{d.text}}</dd>'].join(''),
        template_txt: '{{d.text}}',
        template_val: '{{d.value}}',
        cache: false
      },
      data: {}
    },
    callback = function () {
      var _self = this,
        _config = _self.config;
      return {
        call: function (handle, params) {
          if (!_self.handles[handle]) return hint.error(handle + " handle is not defined");
          _self.handles[handle].call(_self, params)
        }
      }
    },
    job = function (e) {
      var _self = this;
      _self.config = $.extend({}, _self.config, system.config, e);
      _self.render();
    };
  job.prototype.config = {
    response: {
      code: 'code',
      data: 'data',
      msg: 'msg'
    },
    request: {
      keywords: 'keywords'
    },
    statusCode: 0,
    time_limit: 300,
    pullTimer: null,
    data: {},
    params: {},
    filter: '',
    method: 'get',
    ajaxParams: {},
    char_limit: 1,
  }
  job.prototype.render = function () {
    var _self = this, _config = _self.config;
    if (_config.elem = $(_config.elem), _config.where = _config.where || {}, !_config.elem[0]) return _self;
    var _elem = _config.elem,
      _container = _elem.next('.' + container),
      _html = _self.elem = $(laytpl(_config.template).render({}));
    _config.id = _self.id, _container && _container.remove(), _elem.attr('autocomplete', 'off'), _elem.after(_html);
    _self.events()
  }
  job.prototype.pullData = function () {
    var _self = this,
      _config = _self.config,
      _elem = _config.elem;
    if (_config.filter.length < _config.char_limit) return _self.renderData([]);
    if ((_config.cache || !_config.url) && _config.data instanceof Object && Object.keys(_config.data).length > 0) return _self.renderData(_config.data);
    var keywords = _config.request.keywords
    var params = {
      t: new Date().getTime()
    }
    params[keywords] = _config.filter;

    var $loading = $('<i class="layui-icon layui-icon-loading layui-anim layui-anim-rotate layui-anim-loop"></i>');
    $.ajax($.extend({
      type: _config.method,
      url: _config.url,
      data: $.extend(params, _config.params instanceof Function ? _config.params() :_config.params),
      contentType: 'text/json,charset=utf-8',
      dataType: "json",
      beforeSend: function () {
        $loading.attr('style', [
          'position:absolute', 
          'left:' + (_elem.offset().left + _elem.outerWidth() - 20) + 'px', 
          'top:' + _elem.offset().top + 'px',
          'height:' + _elem.height() + 'px',
          'line-height:' + _elem.height() + 'px'
        ].join(';'));
        $loading.appendTo('body');
      },
      success: function (resp) {
        return _config.statusCode != resp[_config.response.code] ? layer.msg(resp[_config.response.msg]) : _config.data = resp[_config.response.data], _self.renderData(_config.data)
      },
      error: function () {
        hint.error("请求失败");
      },
      complete: function () {
        $loading.remove();
      }
    }, _config.ajaxParams))
  }
  job.prototype.renderData = function (resp) {
    var _self = this,
      _config = _self.config,
      _elem = _config.elem,
      _container = _elem.next('.' + container),
      _dom = _container.find('dl');
    
    var _list = [];
    layui.each(resp, function (i, e) {
      if (_config.cache) {
        if (e instanceof Object) {
          layui.each(e, function (_i, _e) {
            if (_e && _e.toString().toLowerCase().indexOf(_config.filter.toLowerCase()) > -1) {
              _list.push(e)
              return true;
            }
          })
        } else {
          if (e && e.toString().toLowerCase().indexOf(_config.filter.toLowerCase()) > -1) {
            _list.push(e)
          }
        }
      } else {
        _list.push(e)
      }
    })
    if (_list.length > 0) {
      _dom.html('')
      layui.each(_list, function (i, e) {
        _self.renderItem(i, e)
      })
      _container.addClass(container_focus)
    }
  }
  job.prototype.renderItem = function (index, data) {
    var _self = this,
      _config = _self.config,
      _elem = _config.elem,
      _container = _elem.next('.' + container),
      _dom = _container.find('dl');
    var itemDom = laytpl(_config.layout).render({ index: index, text: laytpl(_config.template_txt).render(data) })
    $(itemDom).appendTo(_dom).on('click', function () {
      _self.selectItem(data)
    })
  }
  job.prototype.selectItem = function (data) {
    var _self = this,
      _config = _self.config,
      _elem = _config.elem,
      _container = _elem.next('.' + container);
    _elem.val(laytpl(_config.template_val).render(data)), _config.onselect == undefined || _config.onselect(data)
    _container.removeClass(container_focus)
  }
  job.prototype.handles = {
    addData: function (data) {
      var _self = this,
        _config = _self.config;
      if (data instanceof Array) {
        _config.data = _config.data.concat(data)
      } else {
        _config.data.push(data)
      }
    },
    setData: function (data) {
      var _self = this,
        _config = _self.config;
      _config.data = data;
    }
  }
  job.prototype.events = function () {
    var _self = this,
      _config = _self.config,
      _elem = _config.elem,
      _container = _elem.next('.' + container),
      _dom = _container.find('dl'),
      _selectIndex = -1;
    _elem.unbind('focus').unbind('blur').unbind('input propertychange').unbind('input keydown').on('focus', function () {
      _selectIndex = -1, _config.filter = this.value, _self.pullData()
    }).on('input propertychange', function (e) {
      var _value = this.value
      clearTimeout(_config.pullTimer), _config.pullTimer = setTimeout(function () {
        _config.filter = _value, _self.pullData()
      }, _config.time_limit)
    }).on('input keydown', function (e) {
      var _children = $(_self.elem).children().children('dd')
      var highlightItem = function () {
        _children.removeClass('active')
        _children.eq(_selectIndex).addClass('active')
      }
      if (_children.length > 0) {
        switch (e.keyCode) {
          case 38:
            _selectIndex--
            if (_selectIndex < 0) {
              _selectIndex = 0
            }
            highlightItem()
            break
          case 40:
            _selectIndex++
            if (_selectIndex >= _config.data.length) {
              _selectIndex = _config.data.length - 1
            }
            highlightItem()
            break
          case 13:
            _children.eq(_selectIndex).click()
            _selectIndex = -1
            break
        }
      }
    })
    $(document).on('click', function (e) {
      var _target = e.target, _item = _dom.find(_target), _e = _item.length > 0 ? _item.closest('dd') : undefined;
      if (_target === _elem[0]) return false;
      if (_e !== undefined) return false;
      _container.removeClass(container_focus);
    })
  }
  system.init = function (e, c) {
    var c = c || {}, _self = this, _elems = $(e ? 'input[lay-filter="' + e + '"]' : 'input[' + filter + ']')
    
    _elems.each(function (_i, _e) {
      var _elem = $(_e),
        _lay_data = _elem.attr('lay-data');
      try {
        _lay_data = new Function("return " + _lay_data)()
      } catch (ex) {
        return hint.error("autocomplete元素属性lay-data配置项存在语法错误：" + _lay_data)
      }
      var _config = $.extend({ elem: this }, system.config, c, _lay_data);
      _config.url == undefined && (_config.data == undefined || _config.length === 0) && hint.error("autocomplete配置有误，缺少获取数据方式");
      system.render(_config);
    })
  }
  system.render = function (e) {
    var j = new job(e);
    return callback.call(j)
  }
  system.init(), e(module, system);
})