<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<div class="container">
	<h2 class="text-primary">구구단 선택</h2>
<form action="gugu2">
<table class="table table-striped">
	<tr><td><label for="g2">2</label>
		<input type="radio" name="num" value="2" id="g2" checked="checked"><br>
		<label for="g3">3</label>
		<input type="radio" name="num" value="3" id="g3"><br>
		<label for="g4">4</label>
		<input type="radio" name="num" value="4" id="g4"><br>
		<label for="g5">5</label>
		<input type="radio" name="num" value="5" id="g5"><br>
		<label for="g6">6</label>
		<input type="radio" name="num" value="6" id="g6"><br>
		<label for="g7">7</label>
		<input type="radio" name="num" value="7" id="g7"><br>
		<label for="g8">8</label>
		<input type="radio" name="num" value="8" id="g8"><br>
		<label for="g9">9</label>
		<input type="radio" name="num" value="9" id="g9"></td></tr>
		<tr><td><input type="submit" value="확인"></td></tr>
</table>
</form>
</div>
</body>
</html>