$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateEmployeeForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "EmployeeAPI",
		type : type,
		data : $("#formEmployee").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onEmployeeSaveComplete(response.responseText, status);
		}
	});
});

function onEmployeeSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divEmployeeGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidEmployeeIDSave").val("");
	$("#formEmployee")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidEmployeeIDSave").val($(this).closest("tr").find('#hidEmployeeIDUpdate').val());
			$("#employeeName").val($(this).closest("tr").find('td:eq(0)').text());
			$("#employeeEmail").val($(this).closest("tr").find('td:eq(1)').text());
			$("#empAge").val($(this).closest("tr").find('td:eq(2)').text());
			$("#phone").val($(this).closest("tr").find('td:eq(3)').text());
			$("#nic").val($(this).closest("tr").find('td:eq(4)').text());
		});

// REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "EmployeeAPI",
		type : "DELETE",
		data : "employeeNumber=" + $(this).data("employeenumber"),
		dataType : "text",
		complete : function(response, status) {
			onEmployeeDeleteComplete(response.responseText, status);
		}
	});
});

function onEmployeeDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divEmployeeGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL=========================================================================
function validateEmployeeForm() {
	// NAME
	if ($("#employeeName").val().trim() == "") {
		return "Insert Employee Name.";
	}

	// Email------------------------
	if ($("#employeeEmail").val().trim() == "") {
		return "Insert employee Email.";
	}

	// Age------------------------
	if ($("#empAge").val().trim() == "") {
		return "Insert Employee Age.";
	}

	// PhoneNo------------------------
	if ($("#phone").val().trim() == "") {
		return "Insert Phone Number.";
	}

	// NIC------------------------
	if ($("#nic").val().trim() == "") {
		return "Insert NIC.";
	}
	
	return true;
}