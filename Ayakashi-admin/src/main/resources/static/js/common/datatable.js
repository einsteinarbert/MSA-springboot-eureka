function datatable({
    selector,
    pageListLen = 10,
    pageSizeList = [],
    showPageSize = true,
    columns = [],
    pageSize = 0,
    totalRecord = 0,
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
        if (!showPageSize) {
            $('.page-size-ctrl').remove();
        } else {
            let select = $('.page-size-ctrl > select');
            if (pageSizeList.length > 0) {
                $(select).find('option').remove();
                for (let i = 0; i < pageSizeList.length; i ++) {
                    $(select).append(
                        jQuery.parseHTML('<option value="'+pageSizeList[i]+'">'+pageSizeList[i]+'</option>')
                    );
                }
            }
            $(select).unbind('change');
            $(select).val(pageSize);
            $(select).change(function (){
                jumpFunc(currentPage, this.value);
            });
        }
        $('.total-row-ctrl').text(totalRecord + " 件");
        let pagingElement = $(self).find('.datatable-pagination');
        let ul = $(pagingElement).find('ul[class*="pagination"]');
        $(ul).find('li[class*="page-num"]').remove();
        if (totalPages < 2) {
            let li = '<li class="page-item active page-num">' +
                '                <a class="page-link" href="#">1</a>' +
                '            </li>';
            $(ul).find('li[class*="next-page"]').before(jQuery.parseHTML(li));
        } else {
            let head = currentPage != null ? currentPage - Math.floor(pageListLen/2) - 1: 0;
            let tail = currentPage != null ? currentPage + Math.floor(pageListLen/2) + 1: totalPages - 1 ;
            for (let i = 0; i < totalPages; i++) {
                // if (i < head || i > tail) continue;
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
                jumpFunc(jump, $('.page-size-ctrl > select').val());
        });
        activePage(ul, currentPage);
    } else {
        $('.pagination-footer').remove();
    }
}

function activePage(ul, jump) {
    $(ul).find('li[class*="page-num"]').each(function (index, element){
        element.classList.remove('active');
        if ((index + 1).toString() === (jump).toString()) element.classList.add('active');
    });
}
