document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
}, false);

function openPopup(url) {
    var w = window.screen.availWidth;
    var h = window.screen.availHeight * 0.9;
    var specs = "height=" + h + ",width=" + w + ",status=yes,toolbar=no,menubar=no,location=no,toolbar=no";
    window.open(url, null, specs);
}

$.fn.maskCnpjCpf = function() {
    var field = this;
    field.on("keydown", function (e) {

        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40) ||
            //Allow numerics
            (e.keyCode >= 48 && e.keyCode <= 57) ||
            (e.keyCode >= 96 && e.keyCode <= 105)) {

            var cpfCnpj = ($(this).val() + e.key).replace(/\D/g, '');
            var masks = ['00.000.000/0000-00', '000.000.000-00'];
            var mask = (cpfCnpj.length > 11) ? masks[0] : masks[1];
            field.mask(mask);
        } else if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });

    field.mask('000.000.000-00');
    field.attr('maxlength', '18');
};