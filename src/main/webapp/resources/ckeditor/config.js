/**
 * @license Copyright (c) 2003-2021, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */


$(document).ready(function() {
	CKEDITOR.replace('editor');

	window.parent.CKEDITOR.tools.callFunction('${uploaded}', '${url}', '파일 전송 완료.');
});

CKEDITOR.editorConfig = function( config ){
	config.language = 'ko';
	config.uiColor = '#EEEEEE';
	config.height = '550px';
	config.font_defaultLabel = 'Gulim';
	config.font_names='Gulim/Gulim;Dotum/Dotum;Batang/Batang;Gungsuh/Gungsuh/Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';
	config.fontSize_defaultLabel = '12px';
	config.fontSize_sizes='8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;';
	config.enterMode =CKEDITOR.ENTER_BR;
	config.startupFocus = true;
	config.allowedContent = true;
	config.filebrowserImageUploadUrl = '/post/uploadImgTemp.do';
	config.filebrowserFlashUploadUrl = '/post/uploadImgTemp.do';
	config.toolbarCanCollapse = true;
	config.docType = "<!DOCTYPE html>";
};
