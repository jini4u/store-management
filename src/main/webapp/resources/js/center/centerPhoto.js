const mainTable = document.querySelector(".click");
mainTable.onclick = disappearTable;
function disappearTable() {
    const searchTable = document.querySelector(".search");
    const searchImg = document.querySelector("#photo-right-frame");
    searchTable.style.display = "block";
    searchImg.style.display = "block";	
};
const modalAppear = document.querySelector("#modal");
const openModal = document.querySelector("#insert-center-modal");
openModal.onclick = modalOpen;
function modalOpen() {
    modalAppear.style.display ="block";
};
const closeModal = document.querySelector("#modal-close-btn");
closeModal.onclick = modalClose;
function modalClose() {
    modalAppear.style.display = "none";
};