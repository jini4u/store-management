const mainTable = document.querySelector(".click");
mainTable.onclick = disappearTable;
function disappearTable() {
    const searchTable = document.querySelector(".search");
    if(searchTable.style.display !== "none") {
        alert("pass");
        searchTable.style.display = "none"
    }else {
        searchTable.style.display ="block";
    }
};


$(function(){ 

     $("#open").click(function(){
       $(".modal").fadeIn();
     });
     
     $(".modal_content").click(function(){
       $(".modal").fadeOut();
     });
     
   });



const openButton = document.querySelector("#insert-center-modal");
const modal = document.querySelector(".modal");
const overlay = modal.querySelector(".modal_overlay");
const closeBtn = modal.querySelector("#closeBtn");
const openModal = () => {
	modal.classList.remove("hidden");
}
const closeModal = () => {
	modal.classList.add("hidden");
}
//overlay.addEventListener("click", closeModal);
//closeBtn.addEventListner("click", closeModal);
openButton.addEventListener("click", openModal);