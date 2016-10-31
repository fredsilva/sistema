function loadSelectedModule() {
    $("#modules-menus").find(".modules-menu").hide();
    $("#" + $("#optModulo").val()).fadeIn("fast");
}

$(document).ready(function () {
    var activeOpcao = $("#modules-menus").find("ul li ul li.active");
    if (activeOpcao[0]) {
        $("#optModulo").val(activeOpcao.parents(".modules-menu").attr('id'));
        activeOpcao.parent().collapse('show');
    }
    loadSelectedModule();
    $('#change-password-modal').on('hidden.bs.modal', function () {
        clearFields("change-password-form");
    });
});