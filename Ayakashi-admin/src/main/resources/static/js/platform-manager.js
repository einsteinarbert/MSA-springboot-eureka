$(document).ready(function () {
    init();
});

/**
 * clone new search condition
 */
function addConditions() {
    let newIndex = $("#form_condition_root #keyword").last().data("index") + 1;
    $("#form_condition_root").append(`<div class="row m-left-10x form-group-filter">
                                    <div class="form-group row pb-2 select-w-312x">
                                        <select name="filterList[${newIndex}].field" data-index = "${newIndex}" class="form-control input-filter" id="field">
                                            <option value="" >Choose Item</option>
                                            <option value="ID" >ID</option>
                                            <option value="platform_token" >platform Token</option>
                                            <option value="name" >platform Name</option>
                                            <option value="platform_type" >platform Type</option>
                                        </select>
                                    </div>
                                    <div class="form-group row pb-2 select-w-312x" >
                                        <select name="filterList[${newIndex}].condition" data-index = "${newIndex}"  class="form-control input-filter" id="condition">
                                            <option value="">Choose Item</option>
                                            <option value="=">=</option>
                                            <option value="contain">contain</option>
                                            <option value=">=" >&gt;=</option>
                                            <option value="<=">&lt;=</option>
                                            <option value="<>" >&lt;&gt;</option>
                                        </select>
                                    </div>
                                    <div class="form-group row pb-2 m-left-2 select-w-312x" >
                                        <input name="filterList[${newIndex}].keyword" data-index = "${newIndex}" type="text"  class="input-w-312x input-filter" id="keyword">
                                    </div>
                                    <div class="form-group row pb-2">
                                        <button type="button" class="btn btn-dark m-left-btn-30x btn_remove" id="btn_clear">削除</button>
                                    </div>
                                </div>
                            </div>`);
    $("#form_condition_root").on("click", "#btn_clear", function(){
        if($('#form_condition_root .form-group-filter').length != 1) {
            $(this).parent().parent().remove();
        }
    });
}

function init() {

    $("#btn_clear").click(function () {
        if($('#form_condition_root .form-group-filter').length != 1) {
            $(this).parent().parent().remove();
        }
    });
    /**
     * Clear all added row
     */
    $("#btn_clear_all").click(function () {
        $('#form_condition_root .form-group-filter').not(':first').remove();
        $("#field").val('');
        $("#condition").val('');
        $("#keyword").val('');
    });
}

