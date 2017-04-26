<%@page import="mum.sched.utils.StudentTrack"%>
<%@page import="mum.sched.model.entity.Entry"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.model.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage student</title>
</head>
<body>
<%
	if(request.getParameter("exists") != null) {%>
		<div class="error">Failed. The student exists!</div>
	<%}
	if(request.getParameter("success") != null) {%>
		<div class="success">A new student is added successfully.</div>
	<%}%>
	<%
	List<Student> studentList = (List<Student>)request.getAttribute("allStudentList");
	List<Entry> entryList = (List<Entry>)request.getAttribute("allEntryList");
	if(studentList.size() > 0) {%>
		<table class="tbl">
			<th>First name</th><th>Last name</th><th>Student ID</th><th>Birthday</th><th>Student type</th><th>Entry</th>
			<%
			for(Student student : studentList) {%>
				<tr>
					<td><%=student.getStudentNumber() %></td>
					<td><%=student.getFirstName() %></td>
					<td><%=student.getLastName() %></td>					
					<td><%=new SimpleDateFormat("MMM dd, yyyy").format(student.getDateOfBirth()) %></td>
					<td><%=student.getType().toString() %></td>
					<td><%=student.getEntry().getName() %></td>
				</tr><%
			}%>	
		</table>
	<%} else {%>
	<div class="nothing">No student registered</div>
	<%} %>
	
<form role="form" action="/student/create" method="post">
	<h2>New student</h2>
    <div>
        <label for="first_name">First name:</label>
        <input type="text" name="first_name" id="first_name" value="" required autofocus/>
    </div>
    <div>
        <label for="last_name">Last name:</label>
        <input type="text" name="last_name" id="last_name" value="" required/>
    </div>
    <div>
        <label for="student_id">Student ID:</label>
        <input type="number" name="student_id" id="student_id" value="" required/>
    </div>
    <div>
        <label for="birthday">Birthday:</label>
        <input type="date" name="birthday" id="birthday" value="" required/>
    </div>
    <div>
        <label for="student_type">Student track:</label>
        <select name="student_type" id="student_type">
        	<% 
    		for(StudentTrack type : StudentTrack.values()) {%>
    			<option value="<%=type %>"><%=type.toString() %></option>
    		<%}%>
        </select>
    </div>
    <div>
        <label for="entry">Student type:</label>
        <select name="entry" id="entry">
        	<% 
    		for(Entry entry : entryList) {%>
    			<option value="<%=entry.getId() %>"><%=entry.getName() %></option>
    		<%}%>
        </select>
    </div>
    <button type="submit">Save</button>
</form>
</body>
</html>