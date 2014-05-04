<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- the page for user to upload algorithm -->
	<h2>Upload Algorithm</h2>
	<form:form modelAttribute="algorithmInfo" action="">
		<fieldset>
			<table>
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><textarea></textarea></td>
				</tr>
				<tr>
					<td>File</td>
					<td><input type="file" /></td>
				</tr>


				<tr>
					<td></td>
					<td><input type="submit" id="upload" value="Upload" /></td>
				</tr>
			</table>
		</fieldset>
	</form:form>
</body>
</html>