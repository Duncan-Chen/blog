/**
 * 把tale合并到jQuery的全局对象中
 */
$.extend({
	tale: function() {
		
	}
});

/**
 * 错误提示
 */
$.tale.prototype.alertError = function(options) {
	options = options.length ? {text: options} : (options || {});
	options.title = options.title || '错误 信息';
	options.text = options.text;
	options.type = 'error';
	this.alertBox(options);
};

/**
 * 公共弹窗
 */
$.tale.prototype.alertBox = function(options) {
	swal({
		title: options.title,
		text: options.text,
		type: options.type,
		timer: options.timer || 9999,
		showCloseButton: options.showCloseButton,
		showCancelButton: options.showCancelButton,
		showLoaderOnConfirm: options.showLoaderOnConfirm || false,
		confirmButtonColor: options.confirmButtonColor || '#3085d6',
		cancelButtonColor: options.cancelButtonColor || '#d33',
		confirmButtonText: options.confirmButtonText || '确定',
		cancelButtonText: options.cancelButtonText || '取消',
	}).then(function (e) {
		options.then && options.then(e);
	}).catch(swal.noop);
};

/**
 * 全局post
 */
$.tale.prototype.post = function(options) {
	var self = this;
	$.post({
		url: options.url,
		data: options.data || {},
		type: 'post',
		async: options.async || false,
		dataType: 'json',
		success: function(result) {
			self.hideLoading();
			options.success && options.success(result);
		},
		error: function() {
			
		}
	})
};

$.tale.protptype.showLoading = function() {
	if ($('#tale-loading').length == 0) {
		$('body').append('<div id="tale-loading"></div>');
	}
	$('#tale-loading').show();
}
/**
 * 隐藏动画
 */
$.tale.prototype.hideLoading = function() {
	$('#tale-loading') && $('#tale-loading').hide();
};