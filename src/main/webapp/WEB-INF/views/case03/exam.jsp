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
<body class="exambody"> <!-- �]�w�d��(���M�|�H�b�e�����~�ؤW��) -->
	<table class="examtable">
		<tr>
			<!-- �s��Exam Form -->
			<td valign="top"><!-- �a�W��� -->
				<%@ include file="exam_form.jspf" %>
			</td>
			<!-- �s��Exam List -->
			<td valign="top">
				<%@ include file="exam_list.jspf" %>
			</td>
		</tr>
	</table>
	<%@include file="include/footer.jspf" %>
</body>
</html>