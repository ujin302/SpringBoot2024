// 아이디 중복 체크 	
function checkId() {
	console.log("blur")
	$.ajax({
		type: 'get',
		url: '/memberMVC/member/checkId.do',
		data: {'id' : $('#id').val()},
		dataType: 'text',
		success: function(data) {
			console.log(data.trim());
			console.log($(data) === "true");
			console.log(data === "true");
			if(data.trim() === "true") {
				$('#idDiv').text('사용 가능');
			}
			else $('#idDiv').text('사용 불가능');
		}
	})
}

// 아이디 중복 체크 후, 사용자가 id를 다시 설정하였을 경우 
let focusId = null;
document.getElementById('id').addEventListener("focus", () => {
	focusId = document.getElementById('id').value;
});

document.getElementById('id').addEventListener("blur", () => {
	if(focusId != document.getElementById('id').value) { // id 변경 
		document.getElementById('id').dataset['checkid'] = false; // 중복체크 X
	}
});

// 회원가입 시, 유효성 검사 
function memberJoin(e) {
    console.log($('#name').val());
	e.preventDefault();

	// 1. 아이디 유효성 확인
	if($('#idDiv').text() == '사용 불가능') {
		alert("아이디 중복체크 하세요.");
		e.preventDefault();
		return false;
	}

	// 2. 이름 유효성 확인
    if($('#name').val() == '') {
		alert("이름을 작성하세요.");
		e.preventDefault();
		return false;
	}
	
	// 3. 비밀번호 유효성 확인
	if(document.getElementById('pwd').value == '') {
		alert("비밀번호을 작성하세요.");
		e.preventDefault();
		return false;
	}		
	// 3. 비밀번호 재확인 유효성 확인
	if(document.getElementById('repwd').value == '') {
		alert("비밀번호 재확인하세요.");
		e.preventDefault();
		return false;
	}else if(document.getElementById('repwd').value != document.getElementById('pwd').value) {
		alert("비밀번호가 일치하지 않습니다. 비밀번호를 다시 확인하세요.");
		e.preventDefault();
		return false;
	}
}

$(function() {
    console.log($('#name'));
    console.log($('#name').val());
	// 1. id 중복 체크
	// $('#id').blur(checkId);
});