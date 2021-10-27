'use strict';

function checkInfoReservation() {
    $.ajax({
        url: "http://localhost:8080/api/Reservation/all",
        type: "GET",
        dataType: "JSON",
        success:function(answer) {
            console.log(answer);
            printReservationAnswer(answer);
        }
    });
}

function printReservationAnswer(items) {
    let reservationTable = "<table>";
        reservationTable += "<tr>";
        reservationTable += "<th>ID</th>";
        reservationTable += "<th>START</th>";
        reservationTable += "<th>DEVOLUTION</th>";
        reservationTable += "<th>STATUS</th>";
        reservationTable += "<th>SCORE</th>";
        reservationTable += "<th>ACTIONS</th>";
        reservationTable += "</tr>";
    for (let i = 0; i < items.length; i++) {
        reservationTable += "<tr>";
        reservationTable += "<td><a class='link-update' href='HTML/detailReservation.html?id=" + items[i].idReservation + "'>" + items[i].idReservation + "</a></td>";
        reservationTable += "<td>" + items[i].startDate + "</td>";
        reservationTable += "<td>" + items[i].devolutionDate + "</td>";
        reservationTable += "<td>" + items[i].status + "</td>";
        reservationTable += "<td>" + items[i].score + "</td>";
        reservationTable += "<td><button class='btn-delete' onclick='deleteReservation(" + items[i].idReservation + ")'>DELETE</button>";
        reservationTable += "</tr>";
    }
    reservationTable += "</table>";
    $("#table-reservation").html(reservationTable);
}

function saveInfoReservation() {
    let dataReservation = {
        startDate:$("#res-start").val(),
        devolutionDate:$("#res-devol").val(),
        score:$("#reserv-score").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        data: JSON.stringify(dataReservation),

        url: "http://localhost:8080/api/Reservation/save",

        success:function(response) {
            console.log(response);
            console.log("Reservation saved successfully.");
            alert("Reservation saved successfully.");
            window.location.reload()
        },

        error:function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("Unsaved Reservation.");
        }
    });
}

function updateReservation() {
    let myData = {
        startDate:$("#start-date").val(),
        devolutionDate:$("#devol-date").val(),
        score:$("#reserv-score").val()
    };

    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://localhost:8080/api/Reservation/update",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Reservation updated successfully.")
        }
    });
}

function deleteReservation(idReservation) {
    console.log(idReservation);
    $.ajax({
        url: "http://localhost:8080/api/Reservation/" + idReservation,
        type: "DELETE",
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Reservation deleted.");
        }
    })
}