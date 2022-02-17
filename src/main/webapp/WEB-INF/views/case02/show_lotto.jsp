<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css" >
<meta charset="BIG5">
<title>Show Lotto</title>
</head>
<body style="padding: 15px"> <!-- �]�w�d��(���M�|�H�b�e�����~�ؤW��) -->
	<!-- �︹���s -->
	<form class="pure-form" method="post" action="./add">
    	<fieldset>
      	  <button type="submit" class="pure-button pure-button-primary">
      	 	�q���︹
      	  </button>
    	</fieldset>
	</form>
	<p/>
	
	
	<!-- �@�~ -->
	<form class="pure-form" method="post" action="./count">
		<fieldset>
      	  <button type="submit" class="pure-button pure-button-primary">
      	 	�έp�C�@���X�X�{������
      	  </button>
    	</fieldset>
    	���X=�X�{����<p />
    	${countAllBalls}
	</form>
	<p/>
	
	
	<!-- �ֳz���X�}���� -->
	<table class="pure-table pure-table-bordered">
		<!-- �}���ϼ��D -->
		<thead>
			<tr>
				<th>index</th><th>�ֳz���X</th><th>�ק�</th><th>�R��</th>
			</tr>
		</thead>
		<!-- �}�����X -->
		<tbody>
			<c:forEach varStatus="status" var="lotto" items="${lottos }">
				<tr>
					<td>${status.index }</td><td>${lotto }</td>
					<td>
						<button type="bottom" 
								onclick="window.location.href='./update/${status.index}';"
								class="pure-button pure-button-primary">
				      	 	�ק�
				      	</button>
					</td>
					<td>
						<button type="bottom" 
								onclick="window.location.href='./delete/${status.index}';"
								class="pure-button pure-button-primary">
				      	 	�R��
				      	</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>