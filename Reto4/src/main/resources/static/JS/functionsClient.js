function checkInfoClient() {
    $.ajax({
        url: "http://localhost:8080/api/Client/all",
        type: "GET",
        dataType: "JSON",
        success:function(answer) {
            console.log(answer);
            printClientAnswer(answer);
        }
    });
}

function printClientAnswer(items) {
    let clientTable = "<table>";
        clientTable += "<tr>";
        clientTable += "<th>ID</th>";
        clientTable += "<th>EMAIL</th>";
        clientTable += "<th>PASSWORD</th>";
        clientTable += "<th>NAME</th>";
        clientTable += "<th>AGE</th>";
        clientTable += "<th>ACTIONS</th>";
        clientTable += "</tr>";
    for (let i = 0; i < items.length; i++) {
        clientTable += "<tr>";
        clientTable += "<td><a class='link-update' href='HTML/detailClient.html?id=" + items[i].idClient + "'>" + items[i].idClient + "</a></td>";
        clientTable += "<td>" + items[i].email + "</td>";
        clientTable += "<td>" + items[i].password + "</td>";
        clientTable += "<td>" + items[i].name + "</td>";
        clientTable += "<td>" + items[i].age + "</td>";
        clientTable += "<td><button class='btn-delete' onclick='deleteClient(" + items[i].idClient + ")'>DELETE</button>";
        clientTable += "</tr>";
    }
    clientTable += "</table>";
    $("#table-client").html(clientTable);
}

function saveInfoClient() {
    let dataClient = {
        email:$("#cli-email").val(),
        password:$("#cli-pass").val(),
        name:$("#cli-name").val(),
        age:$("#cli-age").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        data: JSON.stringify(dataClient),

        url: "http://localhost:8080/api/Client/save",

        success:function(response) {
            console.log(response);
            console.log("Client saved successfully.");
            alert("Client saved successfully.");
            window.location.reload()
        },

        error:function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("Unsaved Client.");
        }
    });
}

function updateClient() {
    let myData = {
        email:$("#cli-email-upd").val(),
        password:$("#cli-password-upd").val(),
        name:$("#cli-name-upd").val(),
        age:$("#cli-age-upd").val()
    };

    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://localhost:8080/api/Client/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Client updated successfully.");
        }
    });
}

function deleteClient(idClient) {
    console.log(idClient);
    $.ajax({
        url: "http://localhost:8080/api/Client/" + idClient,
        type: "DELETE",
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Client deleted.");
        }
    });
}