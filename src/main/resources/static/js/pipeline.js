
console.log("JS on pipeline details page loaded.");


$(document).ready(function () {

    document.getElementById("userNotesSave").addEventListener("click", function(){
        postUpdatedNotes();
    });
});

function postUpdatedNotes() {
    var restApiUrl = "/notes";
    var content = document.getElementById("userNotes").innerHTML;
    $("#userNotesSave").prop("disabled", true);
    var jsonNotes = {"notes": content};
    console.log(content);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: restApiUrl,
        data: JSON.stringify(jsonNotes),
        dataType: 'json',
        cache: false,
        timeout: 12000,
        success: function (data) {
            console.log("notes updated sucesfully: ", data);
            $("#userNotesSave").prop("disabled", false);
        },
        error: function (e) {
            console.log("error updating the notes: ", e);
            $("#userNotesSave").prop("disabled", false);
        }
    });
}
