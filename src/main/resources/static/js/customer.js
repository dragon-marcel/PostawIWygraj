$(document).ready(function() {

    $('#tableCustomer').dataTable({

        ajax: {
            url: "http://localhost:8080/api/customers",
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
                width: "39%"
            },
            {
                data: "email",
                width: "24%"
            },
            {
                data: "nrTel",
                width: "24%"
            },
            {
                data: "id",
                render: function(data) {

                    return "<a href= '/api/customers/" + data + "' id='editCustomer' type='button' class='btn btn-warning btn-icon-split p-2'><i class='fa fa-edit fa-fw p-0'></i></a>"
                },
                width: "5%"
            },
            {
                data: "id",
                render: function(data) {

                    return "<a href= '/api/customers/" + data + "' id='deleteCustomer' type='button' class='btn btn-danger btn-icon-split p-2'><i class='fa fa-trash fa-fw p-0'></i></a>"
                },
                width: "5%"
            }
        ],
    });

    function reloadTable() {
        $('#tableCustomer').DataTable().ajax.reload();
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

    $("#addCustomerModal form #addSubmit").on('click', function(e) {
        e.preventDefault();
        var apiurl = "http://localhost:8080/api/customers";
        var data = {
            name: $("#name").val().trim(),
            email: $("#email").val().trim(),
            nrTel: $("#nrTel").val().trim(),
        }
        $.ajax({
            url: apiurl,
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function(response) {
                reloadTable();
                var textInfo = "Klient " + response.name + " został poprawnie dodany.";
                var type = "success";
                info(textInfo, type);
                $('#addCustomerModal').modal('hide');
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



    $("#tableCustomer tbody").on('click', '#editCustomer', function(event) {
        event.preventDefault();
        var apihref = $(this).attr('href');

        $.ajax({
            url: apihref,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(customer) {
                $('#editid').val(customer.id);
                $('#editname').val(customer.name);
                $('#editemail').val(customer.email);
                $('#editnrTel').val(customer.nrTel);

                $('#editCustomerModal').modal();
            },
            error: function(xhr) {
                var text = JSON.parse(xhr.responseText);
                alert("Bład:" + text.message);
            }
        });

    });

    $("#editCustomerModal #editCustomerSubmit").on('click', function(e) {
        e.preventDefault();
        var apiurl = "http://localhost:8080/api/customers";
        var data = {
            id: $("#editid").val().trim(),
            name: $("#editname").val().trim(),
            email: $("#editemail").val().trim(),
            nrTel: $("#editnrTel").val().trim(),

        }
        $.ajax({
            url: apiurl,
            type: 'PUT',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data),
            success: function(response) {
                $('#editCustomerModal').modal('hide');
                var textInfo = "Klient " + response.username + " został poprawnie edytowany.";
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

    $("#tableCustomer tbody").on('click', '#deleteCustomer', function(event) {
        event.preventDefault();
        var apihref = $(this).attr('href');
        $('#confirmDeleteCustomer').modal();

        $('#confirmDeleteCustomer #delate ').on('click', function() {

            $.ajax({
                url: apihref,
                type: 'DELETE',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(customer) {
                    var textInfo = "Klient " + customer.name + " został poprawnie usunięty.";
                    var type = "success";
                    reloadTable();
                    info(textInfo, type);;
                },
                error: function(xhr) {
                    var text = JSON.parse(xhr.responseText);
                    var textInfo = "Klient " + customer.name + " nie został usunięty, ponieważ istnieje w innych operacjach.";
                    var type = "danger";
                    info(textInfo, type);;
                }
            });
            $('#confirmDeleteCustomer').modal('hide');
        });

        $('#confirmDeleteCustomer #close ').on('click', function() {

            $('#confirmDeleteCustomer').modal('hide');
        });
    });


});