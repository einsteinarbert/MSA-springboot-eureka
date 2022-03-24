$(document).ready(function () {
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
  $('#btn_search').click(function (e) {
      // $.post()
    let filter = [];
    for (let ele of $('.form-group-filter')) {
        let valid = true;
        for (let select of ele.getElementsByTagName('select')) {
          if (select.value === '0') {
            valid = false;
            break;
          }
        }
        if (valid) {
          let selects = ele.getElementsByTagName('select');
          filter.push({
            "field": selects[0].getElementsByTagName("option")[selects[0].value].text,
            "condition": selects[1].getElementsByTagName("option")[selects[1].value].text,
            "keyword": selects[2].getElementsByTagName("option")[selects[2].value].text,
          });
        }
    }
    console.log(filter);
    if (filter.length > 0) {
      // ajax
      $.post("/api/search-jewel", filter, function (res) {
        console.log("Ajax result:" + res);
      });
    }
  });


});

/**
 * remote one row
 * @param ele
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
