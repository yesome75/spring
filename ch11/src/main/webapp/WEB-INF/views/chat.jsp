<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>Insert title here</title>
<style type="text/css">
	table { height: 450px; border: 2px solid green; 
		table-layout:fixed; overflow: hidden; }
	#chatMessage { height: 400px; overflow: scroll; }	
</style>
<script type="text/javascript">
	var websocket; // function밖으로 빼변 전역변수로 활용
	var nickname;  // 별명
	$(function() {
		$('#enterBtn').click(function() {	connect();		});
		$('#exitBtn').click(function() {	disconnect();	});
		$('#sendBtn').click(function() {	send();			});
		$('#message').click(function() {
			
		});
	});
	function connect() {
		//                            server ip           servlet-context에 등록된 이름
		websocket = new WebSocket("ws://172.30.1.44:8080/ch11/chat-ws.do");
		websocket.onopen = Open;
		websocket.onmessage = onMessage;
		websocket.onclose = onClose;
	}
	function disconnect() {
		
	}
	function send() {
		
	}
	function Open() {
		nickname = $('#nickname').val(); // 별명 가져오기
		appendMessage(name+"님이 입장 하였습니다");
	}
	function onMessage(event) {
		var msg = event.data;   // 메세지가 event에 data속성으로 들어 온다
		appendMessage(msg);
	}
	function onClose() {
		appendMessage(name+"님이 퇴장 하였습니다");
	}
	function appendMessage(msg) {
		$('#chatMessage').append(msg+"<br>");
	}
</script>
</head><body>
<div class="container">
<table class="table table-hover">
	<tr><td width="20%">별명</td><td><input type="text" id="nickname">
		<input type="button" id="enterBtn" value="입장" class="btn btn-info btn-sm">
		<input type="button" id="exitBtn" value="퇴장" class="btn btn-sm btn-warning"></td></tr>
	<tr><td>메세지</td><td><input type="text" id="message">
		<input type="button" id="sendBtn" value="전송" class="btn btn-sm btn-success"></td></tr>
	<tr><td>대화영역</td><td><div id="chatMessage"></div></td></tr>
</table>
</div>
</body>
</html>