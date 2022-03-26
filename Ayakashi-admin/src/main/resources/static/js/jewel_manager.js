let pageSize = 10;
let curPage = 0;
let table = null;
let local = [];
$(document).ready(function () {
  $('#search_form').submit(false); // disable submit event on form
  setupActionDataTable();
  search(); // init table


  /**
   * reset default state
   */
  $('#btn_clear').click(function (e) {
    let parent = e.currentTarget.parentNode.parentNode;
    for (let select of parent.getElementsByTagName("select")) {
      select.value = 0;
    }
  });

  /**
   * Clear all added row
   */
  $('#btn_clear_all').click(function (e) {
    for (let el of $(".form-condition")) {
      remove(el, false);
    }
    $('#btn_clear').trigger(e);
  });

  /**
   * search action
   */
  $('#btn_search').click(function () {
      // $.post()
    search();
  });


});

/**
 * remote one row
 * @param isChild true => remove parent too
 * @param ele: element
 */
const remove = function(ele, isChild=true) {
  console.log("hee" + ele);
  if (isChild) {
    ele.parentNode.parentNode.remove(); // remove row on click button
  } else {
    ele.remove();
  }
}

/**
 * clone new search condition
 */
function addConditions() {
  console.log("Cloning form_condition_root");
  let parent = document.getElementById("form_condition_root").parentNode;
  let ele = document.getElementById("form_condition_root").cloneNode(true);
  ele.setAttribute("id", '');
  ele.setAttribute('class', ele.getAttribute('class') + " " + 'form-condition');
  ele.getElementsByTagName("button")[0].setAttribute("onclick", "remove(this)");
  ele.getElementsByTagName("button")[0].setAttribute("id", '');
  parent.insertBefore(ele, document.getElementById("button-group").previousSibling); // insert new row
}

function setupActionDataTable() {
  $('select[name="data_table_length"]').change((function (){
    search();
  }));

}


function search() {
  let filter = [];
  for (let ele of $('.form-group-filter')) {
    let valid = true;
    for (let select of ele.getElementsByClassName('input-filter')) {
      if (select.value === '0') {
        valid = false;
        break;
      }
    }
    if (valid) {
      let selects = ele.getElementsByClassName('input-filter');
      filter.push({
        "field": selects[0].value,
        "condition": selects[1].getElementsByTagName("option")[selects[1].value].text,
        "keyword": selects[2].value,
      });
    }
  }
  // ajax
  pageSize = $('select[name="data_table_length"]').val();
  curPage = $('.pagination > li.paginate_button.page-item.active').val();
  if (!pageSize) pageSize = 10;
  if (!curPage) curPage = 0;
  let data = {
    "filter": filter,
    "currentPage": curPage,
    "pageSize": pageSize,
    "sortField": "jp.id",
    "isASC": true
  }
  console.log(JSON.stringify(data));
  post("/api/search-jewel", data, function (res) {
    local = res.content;
    if (table) {
      table.clear();
      for (let row of local) {
        table.row.add(row);
      }
      table.page.len(res.totalPages);
      table.page(curPage+1);
      table.draw();
    } else {
      table = $('#data_table').DataTable({
        data:  res.content,
        paging: true,
        page: curPage+1,
        len: res.totalPages,
        searching: false,
        "initComplete": function(_settings, _json) {
          console.log( 'DataTables has finished its initialisation.' );
          setupActionDataTable();
        },
        columns: [
          { "data": "id" },
          { "data": "clientId" },
          { "data": "productCode" },
          { "data": "walletName" },
          { "data": "number" },
          { "data": "bonusWalletName" },
          { "data": "bonusNumber" },
          { "data": "isBought" }
        ]
      });
    }
      },
      function (a, _b, _c) {
        console.error(a)
      }
  );
}