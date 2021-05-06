<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@include file="../include/header.jsp" %>

<form id="" action="${contextPath}/main/login" method="post">
	<input type="submit" id="test" value="로그인페이지" />
</form>



<!-- header.jsp -->
</div></div></div>
</body>
<script>
$("#test1").on("click", function(){
	location.href="${contextPath}/main/login";
});

</script>


</html>