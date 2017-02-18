$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var search = {}
    search["query"] = $("#query").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
//            $('#feedback').html(json);

        $.each(data,function(key,value){

          var element=  '<ul class="event-list">'+
                        '<li>'+value.teaser+'</li>'+
                        '</ul>';

          $('results').html(element);

          /*
           var element= '<li>'+
                        '<time> <img src="boy-512.png" class="avatar img-responsive"/> </time>'+
                        '<div class="info" style="padding: 10px">'+
                        '<h2 class="title" th:text='+value.teaser+'></h2>'+
                        '</div>'+
                        '<p style="display: inline; float: left; width: 80%" class="desc" th:text='+value.shortText+'></p>'+
                        '<div align="right" style="display: inline; float: right; padding: 10px">'+
                        '<a class="btn btn-primary" href="#primary" data-toggle="modal"><h6>'+
                        '<i class="glyphicon glyphicon-search"></i> Leseprobe'+
                        '</h6></a></div>'+
                        '</li>';

             //append it to anywhere in DOM using selector*/

        });


            console.log("SUCCESS : ", data);
            $("#btn-search").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);

        }
    });

}