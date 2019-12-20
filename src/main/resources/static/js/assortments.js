$(document).ready(function() {
	
    $('#tableAssortment').dataTable({

        ajax: {
            url: "http://localhost:8080/api/assortments",
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            dataSrc: ""
        },
        columns: [{
                data: "id",
                render: function(data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                },
                width: "3%"
            },

            {
                data: "name",
                width: "77%"
            },  
            {
                data: "id",
                render: function(data) {

                    return "<a href= '/api/assortments/" + data + "' id='editAssortment' type='button' class='btn btn-warning btn-icon-split p-2 '><i class='fa fa-edit fa-fw p-0'></i></a>"
                },
                width: "5%"
            },
            {
                data: "id",
                render: function(data) {

                    return "<a href= '/api/assortments/" + data + "' id='deleteAssortment' type='button' class='btn btn-danger btn-icon-split pt-2'><i class='fa fa-trash fa-fw p-0'></i></a>"
                },
                width: "5%"
            }
        ],
    });

    function reloadTable() {
        $('#tableAssortment').DataTable().ajax.reload();
    }

    function info(textInfo, type) {
        var element = document.getElementById("info");
        element.innerHTML = textInfo;
        element.classList.add("text-center");
        element.classList.add("alert");
        if (type == "success") {
            element.classList.add("alert-success");
        } else {
            element.classList.add("alert-danger");
        }

    }

    $("#addAssortmentModal form #addSubmit").on('click', function(e) {
        e.preventDefault();
        var apiurl = "http://localhost:8080/api/assortments";
        var data = {
            name: $("#name").val().trim()
        }
        $.ajax({
            url: apiurl,
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function(response) {
                reloadTable();
                var textInfo = "Asortyment " + response.name + " został poprawnie dodany.";
                var type = "success";
                info(textInfo, type);
                $('#addAssortmentModal').modal('hide');
                document.getElementsByTagName("form")[0].reset();
                var small = document.getElementsByTagName("small");
                for (i = 0; i < small.length; i++) {
                    small[i].innerHTML = "";
                }
            },
            error: function(xhr) {
                var small = document.getElementsByTagName("small");
                for (i = 0; i < small.length; i++) {
                    small[i].innerHTML = "";
                }
                var text = JSON.parse(xhr.responseText);
                document.getElementById(text.pole).focus();
                document.getElementById(text.pole + "error").innerHTML = text.message;
            }
        });
    });



    $("#tableAssortment tbody").on('click', '#editAssortment', function(event) {
        event.preventDefault();
        var apihref = $(this).attr('href');

        $.ajax({
            url: apihref,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(assortment) {
                $('#editid').val(assortment.id);
                $('#editname').val(assortment.name);
                $('#editAssortmentModal').modal();
            },
            error: function(xhr) {
                var text = JSON.parse(xhr.responseText);
                alert("Bład:" + text.message);
            }
        });

    });

    $("#editAssortmentModal #editAssormentSubmit").on('click', function(e) {
        e.preventDefault();
        var apiurl = "http://localhost:8080/api/assortments";
        var data = {
            id: $("#editid").val().trim(),
            name: $("#editname").val().trim(),
        }
        $.ajax({
            url: apiurl,
            type: 'PUT',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function(response) {
                $('#editAssortmentModal').modal('hide');
                var textInfo = "Asortyment " + response.username + " został poprawnie edytowany.";
                var type = "success";
                reloadTable();
                info(textInfo, type);

                var small = document.getElementsByTagName("small");
                for (i = 0; i < small.length; i++) {
                    small[i].innerHTML = "";
                }
            },
            error: function(xhr) {
                var small = document.getElementsByTagName("small");
                for (i = 0; i < small.length; i++) {
                    small[i].innerHTML = "";
                }
                var text = JSON.parse(xhr.responseText);
                document.getElementById("edit" + text.pole).focus();
                document.getElementById("edit" + text.pole + "error").innerHTML = text.message;
            }
        });


    });

    $("form #close").on('click', function(e) {
        var small = document.getElementsByTagName("small");
        for (i = 0; i < small.length; i++) {
            small[i].innerHTML = "";
        }
    });

    $("#tableAssortment tbody").on('click', '#deleteAssortment', function(event) {
        event.preventDefault();
        var apihref = $(this).attr('href');
        $('#confirmDeleteAssortment').modal();

        $('#confirmDeleteAssortment #delate ').on('click', function() {

            $.ajax({
                url: apihref,
                type: 'DELETE',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(assortment) {
                    var textInfo = "Asortyment " + assortment.name + " został poprawnie usunięty.";
                    var type = "success";
                    reloadTable();
                    info(textInfo, type);;
                },
                error: function(xhr) {
                    var text = JSON.parse(xhr.responseText);
                    var textInfo = "Asortyment " + assortment.name + " nie został usunięty, ponieważ istnieje w innych operacjach.";
                    var type = "danger";
                    info(textInfo, type);;
                }
            });
            $('#confirmDeleteAssortment').modal('hide');
        });

        $('#confirmDeleteAssortment #close ').on('click', function() {

            $('#confirmDeleteAssortment').modal('hide');
        });
    });


});