let pageSize = 10;
let curPage = 0;
let table = null;
$(document).ready(function () {
  init();
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

function init() {
  if (document.location.href.includes('/pages/jewel-manager')) {
    $('#search_form').submit(false); // disable submit event on form
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

    // DATA TABLE TRUNK
    $('select[name="data_table_length"]').change((function (){
      search();
    }));
    search();
  }

}


function search() {
  let data = getFilterSearch();
  post("/api/search-jewel", data,
  function (res) {
      renderDataTable(res, $('.container-table'));
    },
function (a, _b, _c) {
      console.error(a)
    }
  );
}

function getFilterSearch() {
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
  return {
    "filter": filter,
    "currentPage": curPage,
    "pageSize": pageSize,
    "sortField": "jp.id",
    "isASC": true
  };
}

function renderDataTable(response, selector) {
  datatable({
    selector: selector,
    columns: [
      "id",
      "clientId",
      "productCode",
      "walletName",
      "number",
      "bonusWalletName",
      "bonusNumber",
      "isBought"
    ],
    pagination: true,
    totalRow: response.totalElements,
    pageSize: response.pageable.pageSize,
    totalPages: response.totalPages,
    currentPage: response.pageable.offset,
    data: response.content
  });
}
