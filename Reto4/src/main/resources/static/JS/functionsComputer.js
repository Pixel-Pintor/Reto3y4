'use strict';

function checkInfoComputer() {
    $.ajax({
        url: "http://localhost:8080/api/Computer/all",
        type: "GET",
        dataType: "JSON",
        success:function(answer) {
            console.log(answer);
            printComputerAnswer(answer);
        }
    });
}

function printComputerAnswer(items) {
    let computerTable = "<table>";
        computerTable += "<tr>";
        computerTable += "<th>ID</th>";
        computerTable += "<th>NAME</th>";
        computerTable += "<th>BRAND</th>";
        computerTable += "<th>YEAR</th>";
        computerTable += "<th>DESCRIPTION</th>";
        computerTable += "<th>ACTIONS</th>";
        computerTable += "</tr>";
    for (let i = 0; i < items.length; i++) {
        computerTable += "<tr>";
        computerTable += "<td><a class='link-update' href='HTML/detailComputer.html?id=" +  items[i].id + "'>" + items[i].id + "</a></td>";
        computerTable += "<td>" + items[i].name + "</td>";
        computerTable += "<td>" + items[i].brand + "</td>";
        computerTable += "<td>" + items[i].year + "</td>";
        computerTable += "<td>" + items[i].description + "</td>";
        computerTable += "<td><button class='btn-delete' onclick='deleteComputer(" + items[i].id + ")'>DELETE</button>";
        computerTable += "</tr>";
    }
    computerTable += "</table>";
    $("#table-computer").html(computerTable);
}

function saveInfoComputer() {
    let dataComputer = {
        name:$("#com-name").val(),
        brand:$("#com-brand").val(),
        year:$("#com-year").val(),
        description:$("#com-desc").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        data: JSON.stringify(dataComputer),

        url: "http://localhost:8080/api/Computer/save",

        success:function(response) {
            console.log(response);
            console.log("Computer saved successfully.");
            alert("Computer saved successfully.");
            window.location.reload()
        },

        error:function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("Unsaved Computer.");
        }
    });
}

function updateComputer() {
    let myData = {
        name:$("#com-name-upd").val(),
        brand:$("#com-brand-upd").val(),
        year:$("#com-year-upd").val(),
        description:$("#com-desc-upd").val()
    };

    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://localhost:8080/api/Computer/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Computer updated successfully.");
        }
    });
}

function deleteComputer(idComputer) {
    console.log(idComputer);
    $.ajax({
        url: "http://localhost:8080/api/Computer/" + idComputer,
        type: "DELETE",
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Computer deleted.");
        }
    });
}