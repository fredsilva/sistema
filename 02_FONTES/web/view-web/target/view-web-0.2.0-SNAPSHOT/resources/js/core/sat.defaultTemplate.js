function loadSelectedModule() {
    $("#modules-menus").find(".modules-menu").hide();
    $("#" + $("#optModulo").val()).fadeIn("fast");
}

$(document).ready(function () {
    loadSelectedModule();
});