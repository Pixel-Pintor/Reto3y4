$(document).ready(function () {
    console.log("document ready!");
    let searchParams = new URLSearchParams(window.location.search)
    if (searchParams.has('id')){
        // console.log("entre"+ searchParams.get('id'));
        let id3 = searchParams.get('id')
        $("#id").val(id3);
    }
    console.log(id3);


});