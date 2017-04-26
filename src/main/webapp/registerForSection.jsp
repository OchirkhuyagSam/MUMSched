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
		<div class="error">You have already registered for this section!</div><%
	}
	if(request.getParameter("success") != null) {%>
		<div class="success">A new section is registered successfully.</div><%
	}
	if(request.getParameter("chosen-in-block") != null) {%>
		<div class="warning">You have already register for a section in this block!</div><%
	}
	if(request.getParameter("select-mpp") != null) {%>
		<div class="error">You must select MPP!</div><%
	}
	if(request.getParameter("select-fpp") != null) {%>
		<div class="error">You must select FPP!</div><%
	}
	if(request.getParameter("no-need") != null) {%>
		<div class="warning">You are MPP student. You don't need to register for section in last block!</div><%
	}
	if(request.getParameter("not-found-in-entry") != null) {%>
		<div class="error">Wrong entry!</div><%
	}
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
				<th>Seat available</th>
				<th colspan="2">Max students</th>
			</tr>
			<%
			for(Block block : blockList) {%>
			<tr>
				<td rowspan="<%=block.getSections().size() %>"><%=block.getName() %></td>
				<td rowspan="<%=block.getSections().size() %>"><%=block.getDuration("MMM dd, 2017") %></td><%
				if(block.getSections().size() == 0) {%>
					<td colspan="5">No sections in this block</td>
				<%
				} else {
					int count = 0;
					for(Section section : block.getSections()) {			
						if(count != 0) {%><tr><%} %>
					
				<td>(<%=section.getCourse().getCourseNumber() %>) <%=section.getCourse().getCourseName() %></td>
				<td><%=section.getFaculty().getFullName() %></td>
				<td><%=section.getAvailableSeat() %></td>
				<td><%=section.getNumberOfStudent() %></td>
				<td><a href=/student/section/add?section=<%=section.getId() %>>Add</td></tr><%
						count ++;
					}
				}
			}%>
		</table>			
	<%} %>
</body>
</html>