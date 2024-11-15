// 페이징
function memberPage(pg) {
    pg = pg-1;
    location.href="/member/list?page=" + pg;
}

// 검색
function clickSearchBtn() {
    const inputVal = $('#searchInput').val()

    if(inputVal == '') alert('검색어를 입력하세요.');
    else {
        $.ajax({
            type: 'get',
            url: '/member/list/search',
            data: {
                'colName' : $('#colName').val(),
                'value' : inputVal
            },
            dataType: 'json',
            success: function(data) {
                console.log(data);

                $('#list tr:gt(2)').remove();

                $.each(data, function(index, item) {
                    let result = `<tr>
                                    <td class="seq"><span>${item.seq}</span></td>
                                    <td class="name"><span>${item.name}</span></td>
                                    <td class="id"><span>${item.id}</span></td>
                                    <td class="pwd"><span>${item.pwd}</span></td>
                                </tr>`
                    $('#list').append(result);
                })

            },
            error: function(e) {
                console.log(e);
            }
        })
    }
}

// 검색 페이징
function clickSearchPagingBtn() {
    const inputVal = $('#searchInput').val();
    const pg = $('#page').val();

    if(inputVal == '') alert('검색어를 입력하세요.');
    else {
        $.ajax({
            type: 'get',
            url: '/member/list/searchPaging',
            data: {
                'colName' : $('#colName').val(),
                'value' : inputVal,
                'page' : pg
            },
            dataType: 'json',
            success: function(data) {
                console.log(data);

                $('#list tr:gt(2)').remove();

                $.each(data.list, function(index, item) {
                    let result = `<tr>
                                    <td class="seq"><span>${item.seq}</span></td>
                                    <td class="name"><span>${item.name}</span></td>
                                    <td class="id"><span>${item.id}</span></td>
                                    <td class="pwd"><span>${item.pwd}</span></td>
                                </tr>`
                    $('#list').append(result);
                })

                $('#list tfoot').append(
                    `<tr>
                        <td colspan="4">
                            ${data.memberPaging.pagingHTML}
                        </td>
                    </tr>`
                );
            },
            error: function(e) {
                console.log(e);
            }
        })
    }
}

function memberSearchPage(pg) {
    $('#page').val(pg-1)
    $('#searchBtn').trigger('click')

}

$(function() {
	// $('#searchBtn').click(clickSearchBtn)
	$('#searchBtn').click(clickSearchPagingBtn)
});