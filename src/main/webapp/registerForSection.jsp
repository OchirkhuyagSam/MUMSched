<%@page import="mum.sched.entity.model.Faculty"%>
<%@page import="mum.sched.entity.model.Course"%>
<%@page import="mum.sched.entity.model.Block"%>
<%@page import="mum.sched.entity.model.Section"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.entity.model.Entry"%>
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
	Entry entry = (Entry)request.getAttribute("entry");
	List<Block> blockList = entry.getBlocks();
	
	if(blockList.size() == 0) {%>
		<div class="nothing">No blocks registered in your entry (<%=entry.getName() %>). <a href="/block">Click here</a> to view and create block.</div>
	<%} else {%>
		<table border="2">
			<tr>
				<th>Block</th>
				<th>Date</th>
				<th>Course</th>
				<th>Faculty</th>
				<!-- <th>Seat available</th> -->
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
				<%-- <td><%=section.getAvailableSeat() %></td> --%>
				<td><%=section.getNumberOfStudent() %></td></tr><%
						count ++;
					}
				}
			}%>
		</table>			
	<%} %>
</body>
</html>