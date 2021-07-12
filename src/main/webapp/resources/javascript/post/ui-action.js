function hidePostList() {
	$("#postList").toggleClass("d-none");
	if ($("#postToggler").text() == "펼치기")
		$("#postToggler").text("접어두기")
	else
		$("#postToggler").text("펼치기")
}

function shareTwitter() {
	var sendText = $("#postTitle").text();
	var sendUrl = location.href;
	window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url="
			+ sendUrl);
}

function shareFacebook() {
	var sendUrl = location.href;
	window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}

function shareKakao() {
	Kakao.init('263fba347c2d0c35bd0540d4416e2105');

	Kakao.Link.createDefaultButton({
		container : '#btnKakao',
		objectType : 'feed',
		content : {
			title : $("#postTitle").text(),
			description : $('meta[name="description"]').attr('content'),
			imageUrl : $('meta[property="og:image"]').attr('content'),
			link : {
				mobileWebUrl : location.href,
				webUrl : location.href
			}
		}
	});
}

$(document).ready(function() {
	shareKakao();
});
