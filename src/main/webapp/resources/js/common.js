/**
 * ajax 통신함수
 * @param {Function} callback 함수
 * @param {String} method
 * @param {String} 요청 url
 * @param {Object} 서버로 전송할 object
 *  */
function makeRequest(callback, method, url, sendItem){
	
	//HTTP request 기능 제공하는 Object 생성
	httpRequest = new XMLHttpRequest();
	
	function LoadingWithMask() {
	    //화면의 높이와 너비를 구합니다.
	    var maskHeight = $(document).height();
	    var maskWidth  = window.document.body.clientWidth;
	     
	    //화면에 출력할 마스크를 설정해줍니다.
	    var mask       = "<div id='mask' style='position:absolute; z-index:9000; background-color:#FFFFFF; display:flex; align-items: center; justify-content: center; left:0; top:0;'></div>";
	    var loadingImg = '';
	      
	    loadingImg += " <img src='/resources/images/Spinner-1s-200px.gif' style='position: absolute; display: block; margin: 0px auto;'/>";
	 
	    //화면에 레이어 추가
	    $('body')
	        .append(mask)
	 
	    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
	    $('#mask').css({
	            'width' : maskWidth,
	            'height': maskHeight,
	            'opacity' : '0.5'
	    }); 
	  
	    //마스크 표시
	    //로딩중 이미지 표시
	    $('#mask').append(loadingImg);
	    $('#mask').show();
	}
	 
	function closeLoadingWithMask() {
	    $('#mask, #loadingImg').hide();
	    $('#mask, #loadingImg').empty();  
	}
	
	if(url.endsWith('upload')){
		httpRequest.addEventListener('loadstart', LoadingWithMask());
	}
	
	if(!httpRequest){
		alert('XMLHTTP 인스턴스 만들기 실패');
		return false;
	}
	//요청에 대한 상태가 변화할 때(응답 받은 후에) 지정 함수를 부른다
	httpRequest.onreadystatechange = function(){
		//DONE: 이상 없음, 응답 받았음
		if(httpRequest.readyState === XMLHttpRequest.DONE){
			//200: OK, 이상없음
			if(httpRequest.status === 200){
				callback();
			} else {
				//처리하는 과정에서 문제 발생. 404, 500 등
				alert('request에 문제 발생'+httpRequest.status);
			}
		}
	}
	//.open, .send로 요청하기
	//.open(request method, URL, [true | false]) : 세번째 파라미터는 비동기성. 기본값은 true
	httpRequest.open(method, url, false);
	httpRequest.send(sendItem); 
}
/**
 * ajax jquery 통신함수
 * @param {Function} callback 함수
 * @param {String} method
 * @param {String} 요청 url
 * @param {Object} 서버로 전송할 object
 *  */
function ajaxCmm(type, url, param, callback) {
	$.ajax({
		type : type,
		url : url,
		data : param,
		success : function (data, status, xr) {
			return callback(data);
		},
		error : function (xhr, status, error) {
			return callback(data);
		}
	});
}
