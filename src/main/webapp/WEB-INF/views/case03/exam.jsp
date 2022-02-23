<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="include/header.jspf" %>
</head>
<body class="exambody"> <!-- 設定留白(不然會黏在畫面的外框上面) -->
	<table class="examtable">
		<tr>
			<!-- 存放Exam Form -->
			<td valign="top"><!-- 靠上對齊 -->
				<%@ include file="exam_form.jspf" %>
			</td>
			<!-- 存放Exam List -->
			<td valign="top">
				<%@ include file="exam_list.jspf" %>
			</td>
		</tr>
	</table>
	<%@include file="include/footer.jspf" %>
</body>
</html>