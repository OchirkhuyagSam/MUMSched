<%@page import="mum.sched.model.entity.Course"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.model.entity.Faculty"%>
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
		<div class="error">Failed. The course exists in your list!</div>
	<%}
	if(request.getParameter("success") != null) {%>
		<div class="success">A course is added successfully.</div>
	<%}%>
	<%
	List<Course> allCoursesOfFaculty = (List<Course>)request.getAttribute("allCoursesOfFaculty");
	List<Course> allOtherCourses = (List<Course>)request.getAttribute("allOtherCourses");
	if(allCoursesOfFaculty.size() == 0) {%>
		<div class="nothing">You have no course</div><%
	} else {%>
		<table class="tbl">
			<th>Course number</th><th>Course name</th><th>Prerequisite</th>
			<%
			for(Course course : allCoursesOfFaculty) {%>
				<tr>
					<td><%=course.getCourseNumber() %></td>
					<td><%=course.getCourseName() %></td>
					<td><% if(course.getPrerequisites() == null) {%>-<%}
						   else {%><%=course.getPrerequisites().getCourseName() %><%}%>
					</td>
				</tr><%
			}%>	
		</table>
	<%} %>
	
<form role="form" action="/faculty/course/add" method="post">
	<h2>Add course</h2>
	<div>
    	<label for="course">Courses:</label>
    	<select name="course" id="course">
    		<option value="0">-</option>
    		<% 
    		for(Course course : allOtherCourses) {%>
    			<option value="<%=course.getId() %>"><%=course.getCourseNumber() %>-<%=course.getCourseName() %></option>
    		<%}%>
    	</select>
    </div>
    <button type="submit">Add</button>
</form>
</body>
</html>