/**
 * 标签输入
 */
$('#tags').tagsInput({
	width: '100%',
	height: '35px',
	defaultText: '请输入文章标签'
});
/**
 * 开关按钮
 */
$('.toggle').toggles({
	on: true,
	text: {
		on: '开启',
		off: '关闭'
	}
});
/**
 * 多选下拉框
 */
$('.select2').select2({
	width: '100%'
});

/**
 * markdown编辑器
 */
var textarea = $('#text');
toolbar = $('<div class="markdown-editor" id="md-button-bar" />').insertBefore(textarea.parent());
preview = $('<div id="md-preview" class="md-hidetab" />').insertAfter('.markdown-editor');
markdown(textarea, toolbar, preview);

/**
 * 根据开关设置隐藏域的值
 * @param obj
 */
function allow_comment(obj) {
	var this_ = $(obj);
	var on = this_.find('.toggle-on.active').length;
	var off = this_.find('.toggle-off.active').length;
	if (on == 1) {
		$('#allow_comment').val(false);
	}
	if (off == 1) {
		$('#allow_comment').val(true);
	}
}
function allow_ping(obj) {
	var this_ = $(obj);
	var on = this_.find('.toggle-on.active').length;
	var off = this_.find('.toggle-off.active').length;
	if (on == 1) {
		$('#allow_ping').val(false);
	}
	if (off == 1) {
		$('#allow_ping').val(true);
	}
}
function allow_feed(obj) {
	var this_ = $(obj);
	var on = this_.find('.toggle-on.active').length;
	var off = this_.find('.toggle-off.active').length;
	if (on == 1) {
		$('#allow_feed').val(false);
	}
	if (off == 1) {
		$('#allow_feed').val(true);
	}
}

$('div.allow-false').toggles({
	off: true,
	text: {
		on: '开启',
		off: '关闭'
	}
});

var tale = new $.tale();
/**
 * 保存文章
 * @param status
 */
function subArticle(status) {
	var title = $('#articleForm input[name=title]').val();
	var content = $('#text').val();
	if (title == '') {
		tale.alertWarn('请输入文章标题');
		return ;
	}
	if (content == '') {
		tale.alertWarn('请输入文章内容');
		return ;
	}
	$('#content_editor').val(content);
	$('#articleForm #status').val(status);
	$('#articleForm #categories').val($('#multiple-sel').val());
	var param = $('#articleForm').serialize();
	var url = $('#article #cid') == '' ? '/admin/article/publish' : '/admin/article/modify';
	tale.post({
		url: url,
		data: param,
		success: function(result) {
			if (result && result.success) {
				tale.alertOk({
					text: '文章保存成功',
					then: function() {
						setTimeout(function() {
							window.location.href = '/admin/article';
						}, 500)
					}
				});
			} else {
				tale.alertError(result.msg || '文章保存失败');
			}
		}
	});
}