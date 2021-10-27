'use strict';

function checkInfoMessage() {
    $.ajax({
        url: "http://localhost:8080/api/Message/all",
        type: "GET",
        dataType: "JSON",
        success:function(answer) {
            console.log(answer);
            printMessageAnswer(answer);
        }
    });
}

function printMessageAnswer(items) {
    let messageTable = "<table>";
        messageTable += "<tr>";
        messageTable += "<th>ID</th>";
        messageTable += "<th>MESSAGE TEXT</th>";
        messageTable += "<th>ACTIONS</th>";
        messageTable += "</tr>";
    for (let i = 0; i < items.length; i++) {
        messageTable += "<tr>";
        messageTable += "<td><a class='link-update' href='HTML/detailMessage.html?id=" + items[i].idMessage + "'>" + items[i].idMessage + "</a></td>";
        messageTable += "<td>" + items[i].messageText + "</td>";
        messageTable += "<td><button class='btn-delete' onclick='deleteMessage(" + items[i].idMessage + ")'>DELETE</button>";
        messageTable += "</tr>";
    }
    messageTable += "</table>";
    $("#table-message").html(messageTable);
}

function saveInfoMessage() {
    let dataMessage = {
        messageText:$("#mess-text").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        data: JSON.stringify(dataMessage),

        url: "http://localhost:8080/api/Message/save",

        success:function(response) {
            console.log(response);
            console.log("Message saved successfully.");
            alert("Message saved successfully.");
            window.location.reload()
        },

        error:function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("Unsaved Message.");
        }
    });
}

function updateMessage() {
    let myData = {
        messageText:$("#mess-text-upd").val()
    };

    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://localhost:8080/api/Message/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Computer updated successfully.");
        }
    });
}

function deleteMessage(idMessage) {
    console.log(idMessage);
    $.ajax({
        url: "http://localhost:8080/api/Message/" + idMessage,
        type: "DELETE",
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Message deleted.");
        }
    });
}