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