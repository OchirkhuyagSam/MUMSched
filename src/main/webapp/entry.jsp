<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.entity.model.Entry"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage entry</title>
</head>
<body>
<%
	if(request.getParameter("exists") != null) {%>
		<div class="error">Failed. The entry exists!</div>
	<%}
	if(request.getParameter("success") != null) {%>
		<div class="success">A new entry is added successfully.</div>
	<%}%>
	<%
	List<Entry> entryList = (List<Entry>)request.getAttribute("allEntryList");
	if(entryList.size() > 0) {%>
		<table class="tbl">
			<th>Entry name</th><th>Begin date</th><th>MPP students</th><th>FPP students</th>
			<%
			for(Entry entry : entryList) {%>
				<tr>
					<td><%=entry.getName() %></td>
					<td><%=new SimpleDateFormat("MMM dd, yyyy").format(entry.getBeginDate()) %></td>
					<td><%=entry.getNumberOfFppStudents() %></td>
					<td><%=entry.getNumberOfMppStudents() %></td>
				</tr><%
			}%>
		</table>
	<%} else {%>
	<div class="nothing">No entry registered</div>
	<%} %>
	
<form role="form" action="/entry/create" method="post">
	<h2>New entry</h2>
    <div>
        <label for="entry_name">Entry name:</label>
        <input type="text" name="entry_name" id="entry_name" value="" required autofocus/>
    </div>
    <div>
        <label for="begin_date">Begin date:</label>
        <input type="date" name="begin_date" id="begin_date" value="" required/>
    </div>
    <div>
        <label for="mpp">MPP students:</label>
        <input type="number" name="mpp" id="mpp" value="" required/>
    </div>
    <div>
        <label for="birthday">FPP students:</label>
        <input type="number" name="fpp" id="fpp" value="" required/>
    </div>
    <button type="submit">Save</button>
</form>
</body>
</html>