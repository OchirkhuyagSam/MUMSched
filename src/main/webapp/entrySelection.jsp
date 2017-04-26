<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mum.sched.model.entity.Entry"%>
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
	List<Entry> entryList = (List<Entry>)request.getAttribute("entryList");
	if(entryList.size() == 0) {%>
		<div class="nothing">No entry registered</div> <%
	} else {%>
		<form role="form" action="/section" method="get">
			<div>
				<label for="entry">Choose entry:</label>
				<select name="entry" id="entry" required autofocus> <%
					for(Entry entry : entryList) {%>
					<option value="<%=entry.getId()%>"><%=entry.getName() %> (<%=new SimpleDateFormat("MMM dd, yyyy").format(entry.getBeginDate()) %>)</option>
					<%} %>
				</select>
			</div>
			<button type="submit">Select</button>
			</form>
	<%} %>	
</body>
</html>