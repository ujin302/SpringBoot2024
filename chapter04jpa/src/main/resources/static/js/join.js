// 아이디 중복 체크 	
function checkId() {
	console.log("blur")
	
	if($('#id').val() != ''){
		$('#idDiv').css('color', 'red');
		$.ajax({
			type: 'get',
			url: '/member/checkId',
			data: {'id' : $('#id').val()},
			dataType: 'text',
			success: function(data) {
				console.log(data.trim());
				if(data.trim() === "true") {
					$('#idDiv').text('사용 가능');
					$('#idDiv').css("color","green");
				}
				else {
					$('#idDiv').text('사용 불가능');
					$('#idDiv').css('color', 'red');
				}
			}
		})
	} else {
		$('#idDiv').text('');
	}
	
}

// 아이디 중복 체크 후, 사용자가 id를 다시 설정하였을 경우 
// let focusId = null;
// document.getElementById('id').addEventListener("focus", () => {
// 	focusId = $('#id').val();
// });

// document.getElementById('id').addEventListener("blur", () => {
// 	if(focusId != $('#id').val()) { // id 변경 
// 		document.getElementById('id').dataset['checkid'] = false; // 중복체크 X
// 	}
// });

// 회원가입 시, 유효성 검사 
function Join(e) {
	var isJoin = true;

	// 1. 아이디 유효성 확인
	if($('#idDiv').text() == '사용 불가능') {
		$('#idDiv').text("아이디 중복체크 하세요.");
		e.preventDefault();
		isJoin = false;
	}

	if($('#idDiv').text() == '') {
		$('#idDiv').text("아이디을 작성하세요.");
		e.preventDefault();
		isJoin = false;
	}
	
	// 2. 이름 유효성 확인
	if(document.getElementById('name').value == '') {
		$('#nameDiv').text("이름을 작성하세요.");
		e.preventDefault();
		isJoin = false;
	}
	
	// 3. 비밀번호 유효성 확인
	if(document.getElementById('pwd').value == '') {
		$('#pwdDiv').text("비밀번호을 작성하세요.");
		e.preventDefault();
		isJoin = false;
	}
	
	if(isJoin) {
		$.ajax({
			type: 'post',
			url: '/member/join/submit',
			data: $('#joinFrom').serialize(),
			success: function() {
				console.log('회원가입');
				alert("회원가입을 축하합니다.");
				location.href="/member/list";
			},
			error: function(e) {
				console.log(e);
				alert('실패');
			}
		});	
	}
	
}

$(function() {
	// 1. id 중복 체크
	$('#id').blur(checkId);

	// 2. Div 초기화
	$('.input').blur(function() {
		var n = $(this).attr('name');
		// console.log("#" + n + "Div");
		$("#" + n + "Div").text('');
	});
});