<%@ page isErrorPage="true" %><!-- 不會只限定是post, get, head, 全系列的http方法都會支援 -->
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css" >
<meta charset="BIG5">
<title>Stock Form</title>
<style type="text/css">
	.errors{
		color: #FF0000
	}
	.info{
		color: #005100
	}
</style>
</head>
<body style="padding: 15px"> <!-- 設定留白(不然會黏在畫面的外框上面) -->
	<spform:form class="pure-form" 
				 method="post" 
				 modelAttribute="stock"
				 action="${pageContext.request.contextPath }/mvc/case04/stock/">
    	<fieldset>
      	 	<legend>Stock Form</legend>
      	 	代號：<spform:input path="symbol"/>
      	 		<spform:errors path="symbol" cssClass="errors" /><p />
      	 	價格：<spform:input path="price"/>
      	 		<spform:errors path="price" cssClass="errors" /><p />
      	 	數量：<spform:input path="amount"/>
      	 		<spform:errors path="amount" cssClass="errors" /><p />
      	  	<button type="submit" class="pure-button pure-button-primary">新增</button><p />
      	 	<spform:errors path="*" cssClass="info" /><p />
    	</fieldset>
	</spform:form>
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>index</th>
				<th>stock</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="status" var="stock" items="${stocks }">
				<tr>
					<td>${status.index }</td>
					<td>${stock	 }</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</body>
</html>







