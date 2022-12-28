const mainTable = document.querySelector(".click");
mainTable.onclick = disappearTable;
function disappearTable() {
    const searchTable = document.querySelector(".search");
    if(searchTable.style.display !== "none") {
        searchTable.style.display = "none"
    }else {
        searchTable.style.display ="block";
    }
};