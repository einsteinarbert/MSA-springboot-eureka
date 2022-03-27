function datatable({
                       selector,
                       columns = [],
                       pageSize = 0,
                       totalRow = 0,
                       currentPage = 1,
                       totalPages = 0,
                       pagination = false,
                       tdTag = '',
                       data = [],
                       jumpFunc, // callback
                   }) {
    const self = $(selector)[0]; // convert to jquery variable
    if (tdTag.length === 0) {
        tdTag = '<td class="py-1 table-td">#content</td>';
    }
    let tr = '<tr class="table-tr"></tr>';
    // $('.table-responsive > table > tbody')[2].remove();
    // find <table>
    let tbody = self.getElementsByTagName('table')[0].getElementsByTagName('tbody')[0];
    $(tbody).find('tr').remove();
    for (let row of data) {
        let tableRow = jQuery.parseHTML(tr);
        for (let key of columns) {
            let td = tdTag.replace('#content', row[key]);
            $(tableRow).append(td);
        }
        $(tbody).append(tableRow);
    }

    // pagination
    if (pagination) {
        let pagingElement = $(self).find('.datatable-pagination');
        let ul = $(pagingElement).find('ul[class*="pagination"]');
        $(ul).find('li[class*="page-num"]').remove();
        if (totalPages < 2) {
            let li = '<li class="page-item active page-num">' +
                '                <a class="page-link" href="#">1</a>' +
                '            </li>';
            $(ul).find('li[class*="next-page"]').before(jQuery.parseHTML(li));
        } else {
            for (let i = 0; i < totalPages; i++) {
                let clazz = "page-item page-num";
                if (i === currentPage) clazz += " active";
                let li = '<li class="' + clazz + '">' +
                    '                <a class="page-link" data-page="' + i + '" href="#">' + (i + 1) + '</a>' +
                    '            </li>';
                $(ul).find('li[class*="next-page"]').before(jQuery.parseHTML(li));
            }
        }
        $(ul).find('a.page-link.page-pre').unbind('click');
        $(ul).find('a.page-link.page-next').unbind('click');
        $(ul).find('a[class*="page-link"]').click(function (e) {
            let href = e.target.hash;
            let page = parseInt($(ul).find('li.page-item.page-num.active > a').text().trim());
            let jump;
            if (href === "#pre") {
                if (page > 1) {
                    jump = page - 1;
                } else {
                    jump = null;
                }
            } else if (href === "#next") {
                if (page + 1 <= totalPages) {
                    jump = page + 1;
                } else {
                    jump = null;
                }
            } else {
                jump = parseInt(e.target.innerHTML);
            }
            // callback
            if (null != jump)
                jumpFunc(jump);
        });
        activePage(ul, currentPage);
    }
}

function activePage(ul, jump) {
    $(ul).find('li[class*="page-num"]').each(function (index, element){
        element.classList.remove('active');
        if ((index + 1).toString() === (jump).toString()) element.classList.add('active');
    });
}
