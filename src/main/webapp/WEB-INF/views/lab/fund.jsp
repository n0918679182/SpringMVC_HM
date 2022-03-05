<%@ page isErrorPage="true"%><!-- 不會只限定是post, get, head, 全系列的http方法都會支援 -->
<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function updateFund(fid){
	document.getElementById('fund').action = '/springmvcgit/mvc/lab/fund/' + fid;
	document.getElementById('fund').submit();
}
function deleteFund(fid){
	document.getElementById('_method').value = 'DELETE';
	updateFund(fid);
}
function resetFund(){
	document.getElementById('fundForm').reset();
}

</script>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="BIG5">
<title>Fund Form</title>
<style type="text/css">
.errors {
	color: #FF0000
}
</style>
<!-- ajax -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- util.js -->
<script src="${pageContext.request.contextPath }/js/util.js"></script>
<!-- Google Chart -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body style="padding: 15px">
	<!-- 設定留白(不然會黏在畫面的外框上面) -->
	<table>
		<tr>
			<!-- Fund Form ---------------------------------------------------------------->
			<td valign="top"><spform:form class="pure-form" method="post"
					modelAttribute="fund"
					action="${pageContext.request.contextPath }/mvc/lab/fund/">
					<fieldset>
						<legend>
							<a href="${pageContext.request.contextPath }/mvc/lab/fundstock/">Fundstock Form |</a>
							Fund Form
						</legend>
						<input type="hidden" id="_method" name="_method"
							value="${_method }"> 
						
						基金序號：
						<spform:input path="fid"/>
						<spform:errors path="fid" cssClass="errors" />
						<p />
						
						基金名稱：
						<spform:input path="fname" />
						<spform:errors path="fname" cssClass="errors" />
						<p />
						
							<button type="submit" class="pure-button pure-button-primary"
								${_method == 'POST'?'':'disabled' }>
					      	 		新增
					      	 	</button>
					      	 	
					      	 	<button type="submit"
								class="pure-button pure-button-primary"
								${_method == 'PUT'?'':'disabled' }
								onclick="updateFund(${fid })">
					      	 		修改
					      	 	</button>
					      	 	
					      	 	<button type="button" class="pure-button pure-button-primary" 
					      	 	${ _method == 'PUT'?'':'disabled' } 
					      	 	onclick="deleteFund(${fid })">
					      	 		刪除
					      	  	</button>
					      	  	
					      	  	<button type="reset" id="fundForm" class="pure-button pure-button-primary" 
					      	 	onclick="resetFund()">
					      	 		重置
					      	  	</button>
					      	  	
						<p />
					      	 	<spform:errors path="*" cssClass="errors" />
						<p />
				    	</fieldset>
				</spform:form>
		</td>
		
		<!-- Fund List ---------------------------------------------------------------->
		<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>
							Fund List&nbsp;|&nbsp;
							<a
								href="${pageContext.request.contextPath }/mvc/lab/fund/page/0">全部</a>
							&nbsp;|&nbsp;
							<c:forEach var="num" begin="1" end="${pageTotalCount+1 }">
								<a
									href="${pageContext.request.contextPath }/mvc/lab/fund/page/${num }">${num }</a>
							</c:forEach>
						
						</legend>
						<table class="pure-table pure-table-bordered">
						<thead>
							<tr>
								<th>基金序號</th>
								<th>基金名稱</th>
								<th>建立日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="fund" items="${funds }">
								<tr>
									<td><a
											href="${pageContext.request.contextPath }/mvc/lab/fund/${fund.fid }">
										${fund.fid }</a></td>
									<td>${fund.fname }</td>  
									<td>${fund.createtime }</td> 
								</tr>
							</c:forEach>	
						</tbody>
						</table>
					</fieldset>
				</form>
		</td>
	</tr>
</table>
	
	
</body>
</html>







