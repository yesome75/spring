<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("와우 ! 입력됐다");
		location.href="deptList.html";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("똑바로 해, 으이그");
		history.back();
	</script>
</c:if>
<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("있는 부서인데, 왜그래 !!");
		history.back();
	</script>
</c:if>
</body>
</html>