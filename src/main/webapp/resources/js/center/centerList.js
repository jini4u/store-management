const leftTable = document.querySelector("#center-left");
var leftTableTr = leftTable.rows;
for(var i=1; i<leftTableTr.length; i++) {
    var row = leftTableTr[i];
    console.log("i"+i);
    
    row.onclick = function () {
        let centerCode = document.querySelector("#centerCode");
        let centerName = document.querySelector("#centerName");
        let centerTel = document.querySelector("#centerTel");
        let centerAddress = document.querySelector("#centerAddress");
        let centerOpeningDate = document.querySelector("#centerOpeningDate");
        let centerClosingDate = document.querySelector("#centerClosingDate");
        let centerGuide = document.querySelector("#centerGuide");
        centerCode.value = this.cells[0].innerHTML;
        centerName.value = this.cells[1].innerHTML;
        centerTel.value = this.cells[2].innerHTML;
        centerAddress.value = this.cells[3].innerHTML;
        centerOpeningDate.value = this.cells[4].innerHTML;
        centerGuide.value = this.cells[6].textContent;
        centerClosingDate = this.cells[7].textContent;
    }
}