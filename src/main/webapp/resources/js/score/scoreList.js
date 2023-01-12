
/**
 * @code.js
 * @Description : 점수관리 js
 * @Modification 
 *     수정일      	수정자      	  	수정내용
 *  =========== =========  =====================
 * 	
 * @author 정윤선
 * **/

window.onload = function(){

		$(".open").click(function() {
			$(".modal").fadeIn();
		});

		window.onclick = function(e) {
			if (e.target == modal) {
				modal.style.visibility = "hidden";
				modal.style.opacity = 0;
			}
		}

		const openButton = document.querySelector(".open");
		const modal = document.querySelector(".modal");
		const overlay = modal.querySelector(".modal_overlay");
		const openModal = function() {
			console.log("hi");
		}
		const closeModal = function() {
			modal.classList.add();
		}

		openButton.addEventListener("click", openModal);

		//모달창 닫기//
		$(".close-btn").click(function() {
			$(".modal").fadeOut();

		})
	}
	
