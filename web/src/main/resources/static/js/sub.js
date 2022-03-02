$(window).ready(() => {
    $('.select').each(function () {
        let dropdown = $(this).children('.select-dropdown')
        let selection = $(this).children('.select-selection')
        $(this).click(function () {
            if (dropdown.css('display') === 'none') {
                dropdown.slideDown(250)
            } else {
                dropdown.slideUp(250)
            }
        })
        let dropdown_list = $(dropdown).children('.select-dropdown-list')
        let dropdown_list_items = dropdown_list.children()
        let value = selection.find('.select-selected-value');
        let placeholder = value.siblings('.select-selected-placeholder');
        let input = selection.children('input')
        dropdown_list.click(function (e) {
            if ('select-item' !== e.target.classList[0])
                return
            let target = $(e.target)
            if (placeholder.css('display') === 'inline')
                placeholder.hide()
            target.siblings().removeClass('select-selected')
            target.addClass('select-selected')
            input.val(target.attr('value'))
            value.text(target.text())
            // fallback
            if (selection.attr('back') !== undefined)
                eval(selection.attr('back') + '($(target))')
        })
        let selected = $(dropdown_list).children('.select-selected')
        if (selected.length !== 0) {
            placeholder.text('')
            input.val(selected.val())
            value.text(selected.text())
        }
    })
    //
    $('.modal-mask').each(function () {
        let mask = $(this)
        mask.click(function (e) {
            // console.log(e.target.classList[0])
            if ('modal-mask' === e.target.classList[0] || 'modal' === e.target.classList[0])
                mask.hide()
        })
    })
    //
    let banner = $('.banner');
    let banner_item = $('.banner ul li');
    let len = banner_item.length
    let items = []
    let index = 0;
    let temp;
    for (let i = 0; i < len; i++)
        items[i] = banner_item.eq(i)
    $('.banner-move-left').click(function () {
        if (index === 0) {
            temp = items[len - 1]
            for (let i = len - 2; i >= 0; i--)
                items[i + 1] = items[i]
            items[0] = temp
            items[0].css('left', '-100%')
        } else {
            index--
        }
        bannerRoll(banner_item, parseInt(banner.css('width')), -1, 1)
    })

    $('.banner-move-right').click(function () {
        if (index === len - 1) {
            temp = items[0]
            for (let i = 0; i < len - 1; i++)
                items[i] = items[i + 1]
            items[len - 1] = temp
            items[len - 1].css('left', '100%')
        } else {
            index++
        }
        bannerRoll(banner_item, parseInt(banner.css('width')), 1, 1)
    })
})

function bannerRoll(items, width, direction, n) {
    items.each(function () {
        let item = $(this)
        let left = (parseInt(item.css('left')) / width) - direction;
        item.animate({left: (left * 100 * n) + '%'})
    })
}