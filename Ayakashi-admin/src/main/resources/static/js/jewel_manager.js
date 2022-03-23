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