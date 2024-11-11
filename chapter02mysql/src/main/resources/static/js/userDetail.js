// 회원정보 수정 
function userInfoUpdate(e) {
	if(confirm("회원 정보를 수정하시겠습니까? ")) {
		$.ajax({
			type: 'post',
			url: '/Spring/user/update',
			data: $('#updateFrom').serialize(),
			success: function(data) {
				console.log('회원수정');
				alert("회원정보가 수정되었습니다.");
				location.href="/Spring/user/list?pg="+$('#pg').text();
			},
			error: function(e) {
				console.log(e);
				location.reload();
			}
		});
	}
}


// 회원 탈퇴 
function userInfoDelete(e) {
	$.ajax({
		type: 'get',
		url: '/Spring/user/getExistPwd?id=' + $('#id').val(),
		dataType: 'json',
		success: function(data) {
			console.log('회원탈퇴: 비밀번호 가져오기');
			console.log(JSON.stringify(data));
			var input = prompt('비밀번호를 입력해주세요.');
			
			if(input == data.pwd) {
				if(confirm("탈퇴 하시겠습니까?")) {
					$.ajax({
						type: 'get',
						url: '/Spring/user/delete?id=' + $('#id').val(),
						success: function(data) {
							console.log('회원탈퇴');
							alert("탈퇴되었습니다.");
							location.href="/Spring/user/list";
						},
						error: function(e) {
							console.log(e);
						}
					});
				}
			}else alert('비밀번호가 일치하지 않습니다.')
		},
		error: function(e) {
			console.log(e);
		}
	});
}

$(function() {
	
});