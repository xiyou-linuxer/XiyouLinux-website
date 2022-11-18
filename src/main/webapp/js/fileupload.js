/**
 * Created by zhoupan on 9/4/16.
 */
function doUpload() {
    var formData = new FormData($( "#uploadForm" )[0]);
    $.ajax({
        url: 'http://localhost:63342/NewWebsite/' ,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            alert(returndata);
        },
        error: function (returndata) {
            alert(returndata);
        }
    });
}