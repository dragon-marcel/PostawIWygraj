
$(document).ready(function () {

	$('#tableCustomer').dataTable({

		ajax: {
			url: "http://localhost:8080/api/customers",
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
				data: "name",
				width: "35%"
			},
			{
				data: "email",
				width: "20%"
			},
			{
				data: "nrTel",
				width: "20%"
			},
			{
				data: "id",
				render: function (data) {

					return "<a href= '/api/customers/" + data + "' id='editCustomer' type='button' class='btn btn-warning btn-icon-split '><i class='fa fa-edit fa-fw'></i></a>"
				},
			width: "10%"
			},
			{
				data: "id",
				render: function (data) {

					return "<a href= '/api/customers/" + data + "' id='deleteCustomer' type='button' class='btn btn-danger btn-icon-split '><i class='fa fa-edit fa-fw'></i></a>"
				},
			width: "10%"
			}
		],
	});
	
	
	addCustomerModal

});

