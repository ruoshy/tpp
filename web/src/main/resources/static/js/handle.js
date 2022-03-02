let api = 'http://localhost:8010'

function getRegions(cityId, fun) {
    $.get({
        url: api + '/region/list',
        data: {id: cityId},
        success: (result) => {
            fun(result)
        }
    })
}

function getCinemas(regionId, fun) {
    $.get({
        url: api + '/cinema/list',
        data: {id: regionId},
        success: (result) => {
            fun(result)
        }
    })
}

function getCinema(cinemaId, fun) {
    $.get({
        url: api + '/cinema',
        data: {id: cinemaId},
        success: (result) => {
            fun(result)
        }
    })
}

function getArranges(film_id, cinema_id, date, fun) {
    $.get({
        url: api + '/cinema/arrange/list',
        data: {filmId: film_id, cinemaId: cinema_id, date: date},
        success: (result) => {
            fun(result)
        }
    })
}

function log(...i) {
    console.log(i)
}

//
function closeModal() {
    $('.modal-mask').hide()
}

// ------------ cinema edit
function cinemaEdit(id, index) {
    $('.modal-cinema-edit input').val('')
    $('.modal-cinema-edit').show()
    let cells = $('.table .table-row').eq(index).find('.table-cell')
    let name = cells.eq(0).children('span').text()
    let address = cells.eq(1).children('span').text()
    let phone = cells.eq(2).children('span').text()
    let lal = cells.eq(3).children('span').text()
    $('.modal-cinema-edit input[name="name"]').val(name)
    $('.modal-cinema-edit input[name="address"]').val(address)
    $('.modal-cinema-edit input[name="phone"]').val(phone)
    $('.modal-cinema-edit input[name="lal"]').val(lal)
    $('.modal-cinema-edit input[name="id"]').val(id)
}

function submitCinemaEdit() {
    closeModal()
    let id = $('.modal-cinema-edit input[name="id"]').val()
    let name = $('.modal-cinema-edit input[name="name"]').val()
    let phone = $('.modal-cinema-edit input[name="phone"]').val()
    let lal = $('.modal-cinema-edit input[name="lal"]').val()
    let address = $('.modal-cinema-edit input[name="address"]').val()
    $.post({
        url: 'http://localhost:8010/admin/cinema/update',
        data: {
            id: id,
            name: name,
            address: address,
            phone: phone,
            lal: lal,
        },
        success: (result) => {
            console.log(result)
        }
    })
}

// ------------ cinema add
function cinemaAdd() {
    $('.modal-cinema-add input').val('')
    $('.modal-cinema-add').show()
}

function submitCinemaAdd() {
    let region_id = $('.modal-cinema-add input[name="region_id"]').val()
    let name = $('.modal-cinema-add input[name="name"]').val()
    let phone = $('.modal-cinema-add input[name="phone"]').val()
    let lal = $('.modal-cinema-add input[name="lal"]').val()
    let address = $('.modal-cinema-add input[name="address"]').val()
    log(region_id, name, phone, lal, address)
    $.post({
        url: 'http://localhost:8010/admin/cinema/add',
        data: {
            name: name,
            address: address,
            phone: phone,
            regionId: region_id,
            lal: lal,
        },
        success: (result) => {
            console.log(result)
        }
    })
    closeModal()
}

//
function citySelected(e) {
    let cityId = e.val()
    let region = e.parent().parent().parent().next()
    let content = '';
    getRegions(cityId, (result) => {
        let regions = result.data
        $.each(regions, (i, region) => {
            content += '<li class="select-item" value="' + region.id + '">' + region.name + '</li>'
        })
        region.find('.select-dropdown-list').html(content)
    })
}

// ------------ film edit
function filmEdit(index) {
    $('.modal-film-edit').show()
    let cells = $('.table .table-row').eq(index).find('.table-cell')
    let id = cells.eq(0).children('input[name="id"]').val()
    let status = cells.eq(0).children('input[name="status"]').val()
    let name = cells.eq(1).children('span').text()
    let director = cells.eq(2).children('span').text()
    let actor = cells.eq(3).children('span').text()
    let type = cells.eq(4).children('span').text()
    let makeArea = cells.eq(5).children('span').text()
    let language = cells.eq(6).children('span').text()
    let time = cells.eq(7).children('span').text()
    let introduce = cells.eq(8).children('span').text()
    let releaseDate = cells.eq(9).children('span').text()
    log(id, status, name, director, actor, type, makeArea, language, time, introduce, releaseDate)
    $('.modal-film-edit input[name="id"]').val(id)
    $('.modal-film-edit input[name="name"]').val(name)
    $('.modal-film-edit input[name="director"]').val(director)
    $('.modal-film-edit input[name="actor"]').val(actor)
    $('.modal-film-edit input[name="type"]').val(type)
    $('.modal-film-edit input[name="makeArea"]').val(makeArea)
    $('.modal-film-edit input[name="language"]').val(language)
    $('.modal-film-edit input[name="time"]').val(time)
    $('.modal-film-edit textarea[name="introduce"]').val(introduce)
    $('.modal-film-edit input[name="releaseDate"]').val(releaseDate.split(' ').join('T'))
    $('.modal-film-edit input[name="status"]').attr("checked", status === 'true')
}

function submitFilmEdit() {
    closeModal()
    let id = $('.modal-film-edit input[name="id"]').val()
    let name = $('.modal-film-edit input[name="name"]').val()
    let director = $('.modal-film-edit input[name="director"]').val()
    let actor = $('.modal-film-edit input[name="actor"]').val()
    let type = $('.modal-film-edit input[name="type"]').val()
    let makeArea = $('.modal-film-edit input[name="makeArea"]').val()
    let language = $('.modal-film-edit input[name="language"]').val()
    let time = $('.modal-film-edit input[name="time"]').val()
    let introduce = $('.modal-film-edit textarea[name="introduce"]').val()
    let releaseDate = $('.modal-film-edit input[name="releaseDate"]').val()
    let status = $('.modal-film-edit input[name="status"]').is(':checked') ? 1 : 0
    log(id, name, director, actor, type, makeArea, language, time, introduce, releaseDate, status)
    $.post({
        url: api + '/admin/film/update',
        data: {
            id: id, name: name, director: director, actor: actor, type: type, releaseDate: releaseDate,
            makeArea: makeArea, language: language, time: time, introduce: introduce, status: status
        },
        success: (result) => {
            console.log(result)
        }
    })
}

// ------------ cinema add
function filmAdd() {
    $('.modal-film-add').show()
    // $('.modal-film-add input[name="name"]').val('哆啦A梦：伴我同行2')
    // $('.modal-film-add input[name="director"]').val('山崎贵, 八木龙一')
    // $('.modal-film-add input[name="actor"]').val('水田山葵,大原惠美,嘉数由美,木村昴')
    // $('.modal-film-add input[name="type"]').val('动画')
    // $('.modal-film-add input[name="makeArea"]').val('日本')
    // $('.modal-film-add input[name="language"]').val('日语')
    // $('.modal-film-add input[name="time"]').val(96)
    // $('.modal-film-add textarea[name="introduce"]').text('时隔6年，动画电影“伴我同行”系列回归，同时本片也是该系列的终章。' +
    //     '大雄为了实现奶奶看到妻子的愿望，想坐着时光机去未来参加他的婚礼，但爱情长跑50年终于要娶到静香的大雄竟然想逃婚…')
    // $('.modal-film-add input[name="releaseDate"]').val('2021-05-21')

}

function submitFilmAdd() {
    closeModal()
    let name = $('.modal-film-add input[name="name"]').val()
    let director = $('.modal-film-add input[name="director"]').val()
    let actor = $('.modal-film-add input[name="actor"]').val()
    let type = $('.modal-film-add input[name="type"]').val()
    let makeArea = $('.modal-film-add input[name="makeArea"]').val()
    let language = $('.modal-film-add input[name="language"]').val()
    let time = $('.modal-film-add input[name="time"]').val()
    let introduce = $('.modal-film-add textarea[name="introduce"]').val()
    let releaseDate = $('.modal-film-add input[name="releaseDate"]').val()
    let status = $('.modal-film-add input[name="status"]').is(':checked') ? 1 : 0
    $.post({
        url: api + '/admin/film/add',
        data: {
            name: name, director: director, actor: actor, type: type, releaseDate: releaseDate,
            makeArea: makeArea, language: language, time: time, introduce: introduce, status: status
        },
        success: (result) => {
            console.log(result)
        }
    })
}

//
function filmSelect(index) {
    $('.modal-film-select').show()
    let cells = $('.table .table-row').eq(index).find('.table-cell')
    let id = cells.eq(0).children('input[name="id"]').val()
    let status = cells.eq(0).children('input[name="status"]').val()
    let name = cells.eq(1).children('span').text()
    let director = cells.eq(2).children('span').text()
    let actor = cells.eq(3).children('span').text()
    let type = cells.eq(4).children('span').text()
    let makeArea = cells.eq(5).children('span').text()
    let language = cells.eq(6).children('span').text()
    let time = cells.eq(7).children('span').text()
    let introduce = cells.eq(8).children('span').text()
    let releaseDate = cells.eq(9).children('span').text()
    log(id, status, name, director, actor, type, makeArea, language, time, introduce, releaseDate)
    $('.modal-film-select input[name="id"]').val(id)
    $('.modal-film-select input[name="name"]').val(name)
    $('.modal-film-select input[name="director"]').val(director)
    $('.modal-film-select input[name="actor"]').val(actor)
    $('.modal-film-select input[name="type"]').val(type)
    $('.modal-film-select input[name="makeArea"]').val(makeArea)
    $('.modal-film-select input[name="language"]').val(language)
    $('.modal-film-select input[name="time"]').val(time)
    $('.modal-film-select textarea[name="introduce"]').val(introduce)
    $('.modal-film-select input[name="releaseDate"]').val(releaseDate.split(' ').join('T'))
    $('.modal-film-select input[name="status"]').attr("checked", status === 'true')
}

function submitFilmSelect() {
    closeModal()
    let film_id = $('.modal-film-select input[name="id"]').val()
    $.post({
        url: api + '/cinema/film/add',
        data: {cid: 1, fid: film_id},
        success: (result) => {
            console.log(result)
        }
    })
}

function roomAdd() {
    $('.modal-room-add').show()
//     $('.modal-room-add input[name="name"]').val('3号厅')
//     $('.modal-room-add input[name="seat"]').val(148)
//     $('.modal-room-add input[name="type"]').val('原版 2D')
//     $('.modal-room-add input[name="detail"]').val()
}

function submitRoomAdd() {
    closeModal()
    let name = $('.modal-room-add input[name="name"]').val()
    let seat = $('.modal-room-add input[name="seat"]').val()
    let type = $('.modal-room-add input[name="type"]').val()
    let detail = $('.modal-room-add input[name="detail"]').val()
    $.post({
        url: api + '/cinema/room/add',
        data: {name: name, seat: seat, type: type, detail: detail, cinemaId: 1},
        success: (result) => {
            console.log(result)
        }
    })
}

function roomEdit(index) {
    $('.modal-room-edit').show()
    let item = $('.room-body .table-row').eq(index).find('.table-cell')
    let id = item.eq(0).children('input').val()
    let name = item.eq(1).children('span').text()
    let seat = item.eq(2).children('span').text()
    let type = item.eq(3).children('span').text()
    let detail = item.eq(4).children('span').text()
    $('.modal-room-edit input[name="id"]').val(id)
    $('.modal-room-edit input[name="name"]').val(name)
    $('.modal-room-edit input[name="seat"]').val(seat)
    $('.modal-room-edit input[name="type"]').val(type)
    $('.modal-room-edit input[name="detail"]').val(detail)
}

function submitRoomEdit() {
    closeModal()
    let id = $('.modal-room-edit input[name="id"]').val()
    let name = $('.modal-room-edit input[name="name"]').val()
    let seat = $('.modal-room-edit input[name="seat"]').val()
    let type = $('.modal-room-edit input[name="type"]').val()
    let detail = $('.modal-room-edit input[name="detail"]').val()
    $.post({
        url: api + '/cinema/room/update',
        data: {id: id, name: name, seat: seat, type: type, detail: detail},
        success: (result) => {
            console.log(result)
        }
    })
}

$(function () {
    if ($('.received').length === 0)
        return
    let rt = $('.received-title span')
    let rc = $('.received-content ul')
    let rt1 = rt.eq(0), rt2 = rt.eq(1)
    let rc1 = rc.eq(0), rc2 = rc.eq(1)
    rt1.click(function () {
        rt1.css('color', '')
        rt1.css('border-bottom', '2px solid red')
        rt2.css('color', '#808695')
        rt2.css('border-bottom', '')
        rc1.css('display', '')
        rc2.css('display', 'none')
    })
    rt2.click(function () {
        rt2.css('color', '')
        rt2.css('border-bottom', '2px solid red')
        rt1.css('color', '#808695')
        rt1.css('border-bottom', '')
        rc2.css('display', '')
        rc1.css('display', 'none')
    })
    //
    let rank_films = $('.content-list .film').children()
    let prev_film = rank_films.eq(0).children('.content-list-item')
    rank_films.mousemove(function () {
        prev_film.eq(1).css('display', 'none')
        prev_film.eq(0).css('display', '')
        let items = $(this).children('.content-list-item')
        items.eq(0).css('display', 'none')
        items.eq(1).css('display', '')
        prev_film = items
    })
    //
    let rank_cinemas = $('.content-list .cinema').children()
    let prev_buynow
    rank_cinemas.mousemove(function () {
        if (prev_buynow)
            prev_buynow.css('display', 'none')
        prev_buynow = $(this).find('.buynow')
        prev_buynow.css('display', '')
    })
})
// filmDetail
$(function () {
    if ($('.buy-ticket').length === 0)
        return
    $('.buy-ticket-select .more').click(function () {
        $(this).prev().css('height', '')
        $(this).css('display', 'none')
    })

    let items = $('.buy-ticket-select').children()
    bindRegion(items.eq(0).children('div').children())
    bindCinema(items.eq(1).children('div').children())
    bindDate(items.eq(2).children('div').children())
    refresh()

    function refresh() {
        // let rid = $('.buy-ticket-select input[name="rid"]').val()
        let cinemaId = $('.buy-ticket-select input[name="cid"]').val()
        $('.buy-ticket-select').next().css('display', cinemaId === '' ? 'none' : '')
        if (cinemaId !== '') {
            getCinema(cinemaId, (result) => {
                let cinema = result.data
                let obj = $('.buy-ticket-select').next()
                obj.children('span').text(cinema.name)
                obj.children('p').text('地址：' + cinema.address)
            })
            refreshArrange()
        }
    }

    function refreshCinema(rid) {
        let rid_input = $('.buy-ticket-select input[name="rid"]')
        if (rid_input.val() === rid)
            return
        rid_input.val(rid)
        getCinemas(rid, (result) => {
            let cinemas = result.data
            let html = '';
            $.each(cinemas, (i, cinema) => {
                html += '<a class="' + (i === 0 ? 'selected' : '') + '" cid="' + cinema.id + '">' + cinema.name + '</a>'
            })
            let content = $('.buy-ticket-select li:eq(1)').children('div')
            content.html(html)
            bindCinema(content.children())
            $('.buy-ticket-select .more').css('display', '')
            //
            $('.buy-ticket-select input[name="cid"]').val(cinemas.length === 0 ? '' : cinemas[0].id)
            refresh()
        })
    }

    function refreshArrange() {
        let filmId = $('input[name="fid"]').val()
        let date = $('.buy-ticket-select input[name="date"]').val()
        let cinemaId = $('.buy-ticket-select input[name="cid"]').val()
        getArranges(filmId, cinemaId, date, (result) => {
            let arranges = result.data
            let tbody = $('.buy-ticket .table tbody')
            let html = ''
            let time = $('input[name="time"]').val()
            let language = $('input[name="language"]').val()
            $.each(arranges, (index, item) => {
                let date = new Date(item.time)
                html +=
                    '<tr class="table-row">' +
                    '<td><div class="table-cell">' +
                    '<span style="font-weight: bold;font-size: 22px">' + date.format('hh:mm') + '</span>' +
                    '<span style="font-size: 13px">预计' + new Date(item.time + time * 60 * 1000).format('hh:mm') + '散场</span>' +
                    '</div></td>' +
                    '<td><div class="table-cell"><span>' + language + ' ' + item.room.type + '</span></div></td>' +
                    '<td><div class="table-cell"><span>' + item.room.name + '</span></div></td>' +
                    '<td><div class="table-cell"><span>宽松</span></div></td>' +
                    '<td><div class="table-cell">' +
                    '<span style="font-size: 18px;color: red;font-weight: bold;display: inline">' + item.price + ' </span>' +
                    '<span style="font-size: 14px;text-decoration: line-through;display: inline">' + item.discountPrice + '</span>' +
                    '</div></td>' +
                    '<td><a href="/seatOrder?arrangeId=' + item.id + '" style="background: red;color: white;width: 128px;display: block;padding: 6px 0;text-align: center">选座购票</a></td>' +
                    '</tr>'
            })
            tbody.html(html)
        })
    }

    function bindRegion(items) {
        items.click(function () {
            items.removeClass('selected')
            $(this).addClass('selected')
            refreshCinema($(this).attr('rid'))
        })
    }

    function bindCinema(items) {
        items.click(function () {
            items.removeClass('selected')
            $(this).addClass('selected')
            $(this).parent().prev().val($(this).attr('cid'))
            refresh()
        })
    }

    function bindDate(items) {
        items.click(function () {
            items.removeClass('selected')
            $(this).addClass('selected')
            $(this).parent().prev().val($(this).attr('data-date'))
            refreshArrange()
        })
    }
})

$(function () {
    if ($('.arrange').length === 0)
        return
    let prev_item
    $('.film-body .table-row').click(function () {
        if (prev_item)
            prev_item.css('background', '')
        $(this).css('background', '#f0faff')
        prev_item = $(this)
    })

})

// seatOrder
$(function () {
    if ($('.seat-view').length === 0)
        return
    let detail_list_seat = $('.detail .detail-list-seat')
    let seat_num = $('.seat-num')
    let rows = $('.seat-view .seat-select li')
    let seats = rows.find('a')
    let num = 0
    let price_value = parseFloat($('input[name="price"]').val())
    let price_count = $('.price-count')

    seats.click(function () {
        if ($(this).attr('disable'))
            return
        let temp = $(this).attr('data-index')
        let rc = temp.split('-')
        let row = parseInt(rc[0])
        let col = parseInt(rc[1])
        let target = rows.eq(row).find('a').eq(col)
        if (target.hasClass('selected')) {
            target.removeClass('selected')
            detail_list_seat.children().remove('[data-index="' + temp + '"]')
            num--
        } else {
            target.addClass('selected')
            detail_list_seat.append('<a data-index="' + temp + '">' + (row + 1) + '排' + (col + 1) + '座</a>')
            num++
        }
        price_count.text(Math.ceil(price_value * num * 100) / 100)
        seat_num.text(num)
    })

    let arrangeId = $('input[name="id"]').val()
    $('.submit').click(function () {
        let sel_seats = new Array()
        rows.find('.selected').each((index, seat) => {
            if ($(seat).attr('disable'))
                return
            let rc = $(seat).attr('data-index').split('-')
            sel_seats.push({
                row: rc[0],
                col: rc[1],
                arrangeId: arrangeId,
                order: {
                    userId: $('input[name="userId"]').val()
                }
            })
        })
        $.post({
            url: api + '/cinema/seat/lock',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(sel_seats),
            success: (result) => {
                if (result.status === 200) {
                    window.location.href = '/payOrder?orderId=' + result.data
                }
            }
        })

    })

    $.get({
        url: api + '/cinema/seat/list',
        data: {arrangeId: arrangeId},
        success: (result) => {
            let json = result.data
            $.each(json, (index, seat) => {
                rows.eq(seat.row).find('a').eq(seat.col).addClass('selected').attr('disable', true)
            })
        }
    })

})

$(function () {
    if ($('.order-info').length === 0)
        return
    let orderId = $('input[name="orderId"]').val()
    $('.submit').click(function () {
        $.post({
            url: api + '/cinema/pay',
            data: {id: orderId},
            success: (result) => {
                if (result.status === 200)
                    window.location.href = '/paySuccess?orderId=' + orderId
            }
        })
    })

})

Date.prototype.format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (let k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}
