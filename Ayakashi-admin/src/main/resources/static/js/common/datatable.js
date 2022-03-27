function datatable ({
    selector,
    columns= [],
    pageSize = 0,
    totalRow = 0,
    currentPage = 0,
    totalPages = 0,
    pagination = false,
    tdTag = '',
    data = []
}) {
    const self = $(selector)[0]; // convert to jquery variable
    if (tdTag.length === 0){
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
            for (let i = 0; i < totalPages; i++){
                let clazz  = "page-item page-num";
                if (i === currentPage) clazz += " active";
                let li = '<li class="' + clazz + '">' +
                    '                <a class="page-link" data-page="'+i+'" href="#">' + (i+1) + '</a>' +
                    '            </li>';
                $(ul).find('li[class*="next-page"]').before(jQuery.parseHTML(li));
            }
        }
    }
}
