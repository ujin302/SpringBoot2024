// 아이디 중복 체크 	
function checkId() {
	console.log("blur")
	$('#idDiv').css('color', 'red');
	
	if(!$(this).prop("readonly")) {
		$.ajax({
			type: 'post',
			url: '/user/checkId',
			data: {'id' : $('#id').val()},
			dataType: 'text',
			success: function(data) {
				console.log(data.trim());
				if(data.trim() === "true") {
					$('#idDiv').text('사용 가능');
					$('#idDiv').css('color', 'green');
				}
				else $('#idDiv').text('사용 불가능');
			}
		})
	}
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
function Join(e) {
	var isOk = true;
	// 1. 아이디 유효성 확인
	if($('#idDiv').text() != '사용 가능') {
		$('#idDiv').text('아이디를 작성하세요.');
		e.preventDefault();
		// return false;
		isOk = false;
	}
	
	// 2. 이름 유효성 확인
	if(document.getElementById('name').value == '') {
		$('#nameDiv').text('이름을 작성하세요.');
		//alert("이름을 작성하세요.");
		e.preventDefault();
		// return false;
		isOk = false;
	}
	
	// 3. 비밀번호 유효성 확인
	if(document.getElementById('pwd').value == '') {
		$('#pwdDiv').text('비밀번호를 작성하세요.');
		// alert("비밀번호를 작성하세요.");
		e.preventDefault();
		// return false;
		isOk = false;
	}
	
	if(isOk) {
		$.ajax({
			type: 'post',
			url: '/user/write',
			data: $('#writeFrom').serialize(),
			success: function(data) {
				console.log('회원가입');
				alert("회원가입을 축하합니다.");
				location.href="/user/list";
			},
			error: function(e) {
				console.log(e);
				alert('실패');
			}
		});	
	}
	
}

function onInput() {
	var nameAttr = $(this).prop('name');
	console.log(this);
	console.log($(this).prop('name'));
	// console.log(this.attr('name'));
	console.log(nameAttr);
	
	$('#'+nameAttr+'Div').text('');
}

$(function() {
	// 1. id 중복 체크
	$('#id').blur(checkId);

	// 2. div 초기화
	$('.input').change(onInput);


});