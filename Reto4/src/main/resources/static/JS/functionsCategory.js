function checkInfoCategory() {
    $.ajax({
        url:"http://localhost:8080/api/Category/all",
        type: "GET",
        dataType: "JSON",
        success:function(answer) {
            console.log(answer);
            printCategoryAnswer(answer);
        }
    });
}

function printCategoryAnswer(items) {
    let categoryTable = "<table>";
        categoryTable += "<tr>";
        categoryTable += "<th>ID</th>";
        categoryTable += "<th>NAME</th>";
        categoryTable += "<th>DESCRIPTION</th>";
        categoryTable += "<th>ACTIONS</th>";
        categoryTable += "</tr>";
    for (let i = 0; i < items.length; i++) {
        categoryTable += "<tr>";
        categoryTable += "<td><a class='link-update' href='HTML/detailCategory.html?id=" + items[i].id + "'>" + items[i].id + "</a></td>";
        categoryTable += "<td>" + items[i].name + "</td>";
        categoryTable += "<td>" + items[i].description + "</td>";
        categoryTable += "<td><button class='btn-delete' onclick='deleteCategory(" + items[i].id + ")'>DELETE</button>";
        categoryTable += "</tr>";
    }
    categoryTable += "</table>";
    $("#table-category").html(categoryTable);
}

function saveInfoCategory() {
    let dataCategory = {
        name:$("#cat-name").val(),
        description:$("#cat-desc").val()
    };

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: "JSON",
        data: JSON.stringify(dataCategory),

        url: "http://localhost:8080/api/Category/save",

        success:function(response) {
            console.log(response);
            console.log("Category saved successfully.");
            alert("Category saved successfully.");
            window.location.reload()
        },

        error:function(jqXHR, textStatus, errorThrown) {
            window.location.reload()
            alert("Unsaved Category.");
        }
    });
}

function updateCategory() {
    let myData = {
        name:$("#cat-name-upd").val(),
        description:$("#cat-desc-upd").val()
    };

    let dataToSend = JSON.stringify(myData);
    $.ajax({
        url: "http://localhost:8080/api/Category/update",
        type: "PUT",
        data: dataToSend,
        dataType: "JSON",
        success:function(answer) {
            alert("Category updated successfully.");
        }
    });
}

function deleteCategory(idCategory) {
    console.log(idCategory);
    $.ajax({
        url: "http://localhost:8080/api/Category/" + idCategory,
        type: "DELETE",
        contentType: "application/JSON",
        dataType: "JSON",
        success:function(answer) {
            alert("Category deleted.");
        }
    });
}