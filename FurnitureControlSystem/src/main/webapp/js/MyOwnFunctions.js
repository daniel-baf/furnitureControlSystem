$(document).ready(function () {

    $("#shw-usr-hde-frn-faap").click(showUsrFAAP);
    $("#shw-frn-hde-usr-faap").click(showFntFAAP);
    $("#toggle-result-faap").click(function () {
        alert("borrar el texto de los inputs");
    });

//    l("html, body").stop().animate({
//        scrollTop: l(o.attr("href")).offset().top
//    }, 1e3, "easeInOutExpo"), e.preventDefault()
});

function showFntFAAP() {
    $("#insert-usr-FAAP").removeClass("d-none");
    $("#insert-frn-FAAP").addClass("d-none");
}

function showUsrFAAP() {
    $("#insert-usr-FAAP").addClass("d-none");
    $("#insert-frn-FAAP").removeClass("d-none");
}
