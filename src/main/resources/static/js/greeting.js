$(document).ready(function(){
    $('select').formSelect();
});

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, {edge: "right"});
});

console.log("greeting.js loaded")
