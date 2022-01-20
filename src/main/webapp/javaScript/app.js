window.addEventListener("DOMContentLoaded", function () {
    const buttonAdd = document.getElementById("buttonAdd");
    const addForm = document.getElementById("addForm");

    buttonAdd.addEventListener("click", function () {
        addForm.submit();
        alert("Recipe has been added.");
    })

})