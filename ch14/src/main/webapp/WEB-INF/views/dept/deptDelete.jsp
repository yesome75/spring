<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제 되었습니다");
		location.href = "deptList.html";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("으이그 ! 똑바로 해");
		history.back();
	</script>
</c:if>
</body>
</html>