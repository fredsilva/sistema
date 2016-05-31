function clearFields(id) {
    id = '#'+id;

    // Campos HIDDEN não devem ser limpos pos o JSF usa diverssos inputs hidden para se achar.
    $(id).find('input:text, input:password, input:file, select, textarea').val('');
    $(id).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
    $(id).find('select').each(function() {
        $(this).val($(this).find('option:first').val());
    });
}

$(document).on("keypress", ".justInteger", function(e) {
    return (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) ? false : true;
});

$(document).ready(function (){
    $(".decimal").maskMoney({thousands:'.', decimal:',', allowZero:true});
});

function showBtnSave(formId) {
    var form = $('#' + formId);

    form.find('.btn-save').show();
    form.find('.btn-update').hide();
}

function showBtnUpdate(formId) {
    var form = $('#' + formId);

    form.find('.btn-save').hide();
    form.find('.btn-update').show();
}

function findTab(navId, tabId) {
    if (tabId) {
        return $("#" + navId).find("[href='#" + tabId + "']");
    }
    tabId = navId;

    return $("[href='#" + tabId + "']");
}

function disableTab(navId, tabId) {
    findTab(navId, tabId).removeAttr("data-toggle").parents("li").addClass("disabled");
}

function enableTab(navId, tabId) {
    findTab(navId, tabId).attr("data-toggle", "tab").parents("li").removeClass("disabled");
}