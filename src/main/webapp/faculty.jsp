<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.entity.model.Faculty"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Page</title>
</head>
<body>
	<%
	if(request.getParameter("exists") != null) {%>
		<div class="error">Failed. The faculty exists!</div>
	<%}
	if(request.getParameter("success") != null) {%>
		<div class="success">A new faculty is added successfully.</div>
	<%}%>
	<%
	List<Faculty> facultyList = (List<Faculty>)request.getAttribute("allFacultyList");
	if(facultyList.size() > 0) {%>
		<table class="tbl">
			<th>First name</th><th>Last name</th><th>Faculty ID</th><th>Birthday</th>
			<%
			for(Faculty faculty : facultyList) {%>
				<tr>
					<td><%=faculty.getFirstName() %></td>
					<td><%=faculty.getLastName() %></td>
					<td><%=faculty.getFacultyNumber() %></td>
					<td><%=new SimpleDateFormat("MMM dd, yyyy").format(faculty.getDateOfBirth()) %>
					</td>
				</tr><%
			}%>	
		</table>
	<%} else {%>
	<div class="nothing">No faculty registered</div>
	<%} %>
	
<form role="form" action="/faculty/create" method="post">
	<h2>New faculty</h2>
    <div>
        <label for="first_name">First name:</label>
        <input type="text" name="first_name" id="first_name" value="" required autofocus/>
    </div>
    <div>
        <label for="last_name">Last name:</label>
        <input type="text" name="last_name" id="last_name" value="" required/>
    </div>
    <div>
        <label for="faculty_id">Professor ID:</label>
        <input type="text" name="faculty_id" id="faculty_id" value="" required/>
    </div>
    <div>
        <label for="birthday">Birthday:</label>
        <input type="date" name="birthday" id="birthday" value="" required/>
    </div>
    <button type="submit">Save</button>
</form>
</body>
</html>