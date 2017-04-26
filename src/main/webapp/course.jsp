<!DOCTYPE html>
<%@page import="mum.sched.model.entity.Course"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Course</title>
</head>
<body>
	<%
	if(request.getParameter("exists") != null) {%>
		<div class="error">Failed. The course exists!</div>
	<%}
	if(request.getParameter("success") != null) {%>
		<div class="success">A new course is added successfully.</div>
	<%}%>
	
	<%
	List<Course> courseList = (List<Course>)request.getAttribute("allCourseList");
	if(courseList.size() > 0) {%>
		<table class="tbl">
			<th>Course number</th><th>Course name</th><th>Prerequisite</th>
			<%
			for(Course course : courseList) {%>
				<tr>
					<td><%=course.getCourseNumber() %></td>
					<td><%=course.getCourseName() %></td>
					<td><% if(course.getPrerequisites() == null) {%>-<%
						} else {%><%=course.getPrerequisites().getCourseName() %> <%} %>
					</td>
				</tr><%
			}%>	
		</table>
	<%} else {%>
	<div class="nothing">No courses registered</div>
	<%} %>
	
<form role="form" action="/course/create" method="post">
    <div>
        <label for="course_name">Course name:</label>
        <input type="text" name="course_name" id="course_name" value="" required autofocus/>
    </div>
    <div>
        <label for="course_number">Course number:</label>
        <input type="text" name="course_number" id="course_number" required/>
    </div>
    <div>
    	<label for="pre_req">Prerequisite:</label>
    	<select name="pre_course" id="pre_course">
    		<option value="0">-</option>
    		<% 
    		for(Course course : courseList) {%>
    			<option value="<%=course.getId() %>"><%=course.getCourseNumber() %>-<%=course.getCourseName() %></option>
    		<%}%>
    	</select>
    </div>
    <button type="submit">Save</button>
</form>
</body>
</html>