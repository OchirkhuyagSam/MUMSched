<%@page import="mum.sched.model.entity.Faculty"%>
<%@page import="mum.sched.model.entity.Course"%>
<%@page import="mum.sched.model.entity.Block"%>
<%@page import="mum.sched.model.entity.Section"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.model.entity.Entry"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MUMSched - Section</title>
</head>
<body>
<%
	if(request.getParameter("exists") != null) {%>
		<div class="error">Failed. The section exists!</div>
	<%}
	if(request.getParameter("success") != null) {%>
		<div class="success">A new section is added successfully.</div>
	<%}
	if(request.getParameter("faculty-busy") != null) {%>
		<div class="error">Failed. The faculty is not available!</div><%
	}%>
	<%
	List<Block> blockList = (List<Block>)request.getAttribute("blockList");
	Entry entry = (Entry)request.getAttribute("entry");
	List<Course> courseFacultyList = (List<Course>)request.getAttribute("courseFacultyList");
	
	if(blockList.size() == 0) {%>
		<div class="nothing">No block registered in the entry (<%=entry.getName() %>). <a href="/block">Click here</a> to view and create block.</div>
	<%} else {%>
		<table border="2">
			<tr>
				<th>Block</th>
				<th>Date</th>
				<th>Course</th>
				<th>Faculty</th>
				<th>Max students</th>
			</tr>
			<%
			for(Block block : blockList) {%>
			<tr>
				<td rowspan="<%=block.getSections().size() %>"><%=block.getName() %></td>
				<td rowspan="<%=block.getSections().size() %>"><%=block.getDuration("MMM dd, 2017") %></td><%
				if(block.getSections().size() == 0) {%>
					<td colspan="3">No sections in this block</td>
				<%
				} else {
					int count = 0;
					for(Section section : block.getSections()) {			
						if(count != 0) {%><tr><%} %>
					
				<td>(<%=section.getCourse().getCourseNumber() %>) <%=section.getCourse().getCourseName() %></td>
				<td><%=section.getFaculty().getFullName() %></td>
				<td><%=section.getNumberOfStudent() %></td></tr><%
						count ++;
					}
				}
			}%>
		</table>
<form role="form" action="/section/create" method="post">
	<h2>New section</h2>
	<div>
		<label for="block">Block:</label>
		<select name="block" id="block" required autofocus><%
			for(Block block : blockList) { %>
			
			<option value="<%=block.getId() %>"><%=block.getName() %> (<%=block.getDuration("MMM dd, yyyy") %>)</option> <%
			} %>
		</select>
	</div>
    <div>
        <label for="course_faculty">Course/professor:</label>
        <select name="course_faculty" id="course_faculty" required><%
        	for(Course course : courseFacultyList) {
        		for(Faculty faculty : course.getFaculties()) { %>
        		
        	<option value="<%=course.getId() %>/<%=faculty.getId() %>">(<%=course.getCourseNumber()%>)<%=course.getCourseName() %>/<%=faculty.getFullName() %></option>
        	
        		<%	
        		}
			} %>
        </select>
    </div>
    <div>
        <label for="location">Location:</label>
        <input type="text" name="location" id="location" value="" required/>
    </div>
    <div>
        <label for="max_student">Max student number:</label>
        <input type="number" name="max_student" id="max_student" value="" required/>
    </div>
    <button type="submit">Save</button>
</form>
			
	<%} %>
	

</body>
</html>