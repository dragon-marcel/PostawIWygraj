
$(document).ready(function () {

	$('#tableUsers').dataTable({

		ajax: {
			url: "http://localhost:8080/api/users",
			type: 'GET',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			dataSrc: ""
		},
		columns: [
			{
			    data: "id",
			    render: function (data, type, row, meta) {
			        return meta.row + meta.settings._iDisplayStart + 1;
			    	},
			width: "5%"
			},
			{
				data: "username",
				width: "20%"
			},
			{
				data: "name",
				width: "15%"
			},
			{
				data: "surname",
				width: "15%"
			},
			{
				data: "email",
				width: "15%"
			},
			{
				data: "workplace",
				width: "20%"
			},
			{
				data: "blocked",
				render: function (data) {
					if (data == true) {
						return "<span class='text-danger'>" + "tak" + "</span>";
					} else {
						return "<span>" + "nie" + "</span>";
					}
				},
				width: "5%"
			},
			{
				data: "id",
				render: function (data) {

					return "<a href= '/api/users/" + data + "' id='editUser' type='button' class='btn btn-warning btn-icon-split p-0'><i class='fa fa-edit fa-fw p-0'></i></a>"
				},
				width: "5%"
			}
		],
	});


	function reloadTable() {
		$('#tableUsers').DataTable().ajax.reload();
	}

	function info(textInfo) {
		var element = document.getElementById("info");
		element.innerHTML = textInfo;
		element.classList.add("text-center");
		element.classList.add("alert");
		element.classList.add("alert-success");

	}
	// addUserModal
	$("#addUserModal form #addSubmit").on('click', function (e) {
		e.preventDefault();
		var apiurl = "http://localhost:8080/api/users";
		var data = {
			username: $("#username").val().trim(),
			name: $("#name").val().trim(),
			surname: $("#surname").val().trim(),
			email: $("#email").val().trim(),
			workplace: $("#workplace").val().trim(),
			password: $("#password").val().trim(),
			passwordConfirm: $("#passwordConfirm").val().trim(),
		}
		$.ajax({
			url: apiurl,
			type: 'POST',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data),
			success: function (response) {
				var textInfo = "Użytkownik " + response.username + " został poprawnie dodany.";
				reloadTable();
				info(textInfo);
				$('#addUserModal').modal('hide');
				document.getElementsByTagName("form")[0].reset();
				var small = document.getElementsByTagName("small");
				for (i = 0; i < small.length; i++) {
					small[i].innerHTML = "";
				}
			},
			error: function (xhr) {
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

	//edit modal
	$("#tableUsers tbody").on('click', '#editUser', function (event) {
		event.preventDefault();
		var apihref = $(this).attr('href');
		
		$.ajax({
			url: apihref,
			type: 'GET',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function (user) {
				$('#edituserid').val(user.id);
				$('#editusername').val(user.username);
				$('#editemail').val(user.email);
				$('#editname').val(user.name);
				$('#editsurname').val(user.surname);
				$('#editusername').val(user.username);
				$('#editworkplace').val(user.workplace);
				$('#editBlockdCheck').prop('checked', user.blocked);
				
				$('#editUserModal').modal();
			},
			error: function (xhr) {
				var text = JSON.parse(xhr.responseText);
				alert("Bład:" + text.message);
			}
		});

	});


	$("#editUserModal #editUserSubmit").on('click', function (e) {
		e.preventDefault();
		var apiurl = "http://localhost:8080/api/users";
		var data = {
			id: $("#edituserid").val().trim(),
			username: $("#editusername").val().trim(),
			name: $("#editname").val().trim(),
			surname: $("#editsurname").val().trim(),
			email: $("#editemail").val().trim(),
			workplace: $("#editworkplace").val().trim(),
			blocked: $("#editBlockdCheck").is(':checked'),

		}
		$.ajax({
			url: apiurl,
			type: 'PUT',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			data: JSON.stringify(data),
			success: function (response) {
				$('#editUserModal').modal('hide');
				var textInfo = "Użytkownik " + response.username + " został poprawnie edytowany.";
				reloadTable();
				info(textInfo);
			},
			error: function (xhr) {
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


	$("form #close").on('click', function (e) {
		var small = document.getElementsByTagName("small");
		for (i = 0; i < small.length; i++) {
			small[i].innerHTML = "";
		}
	});
	
	$("#profile").on('click', function (event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$.get(href, function (user, status) {
			$('#edituserid').val(user.id);
			$('#editusername').val(user.username);
			$('#editemail').val(user.email);
			$('#editname').val(user.name);
			$('#editsurname').val(user.surname);
			$('#editusername').val(user.username);
			$('#editworkplace').val(user.workplace);
			$('#editBlockdCheck').prop('checked', user.blocked);
		});

		$('#editUserModal').modal();
	});
	
	
	
});

