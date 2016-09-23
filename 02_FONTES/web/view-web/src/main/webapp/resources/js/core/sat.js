document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
}, false);

function openPopup(url) {
    var w = window.screen.availWidth;
    var h = window.screen.availHeight * 0.9;
    var specs = "height=" + h + ",width=" + w + ",status=yes,toolbar=no,menubar=no,location=no,toolbar=no";
    window.open(url, null, specs);
}
