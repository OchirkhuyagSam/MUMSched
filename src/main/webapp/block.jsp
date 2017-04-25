<%@page import="mum.sched.entity.model.Block"%>
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
		if (request.getParameter("exists") != null) {
	%>
	<div class="error">Failed. The block exists!</div>
	<%
		}
		if (request.getParameter("success") != null) {
	%>
	<div class="success">A new block is added successfully.</div>
	<%
		}
	%>
	<%
		List<Entry> entryList = (List<Entry>) request.getAttribute("allEntryList");
		List<Block> blockList = (List<Block>) request.getAttribute("allBlockList");
		
		if(entryList.size() == 0) {
	%>
	<div class="nothing">No entry and block registered. In order to create a new block, you must create a new entry before.</div>
	<%
		} else {
			if(blockList.size() == 0) {
				%>
				<div class="nothing">No block registered.</div>
				<%
			} else {
				%>
				<table class="tbl">
					<th>Block name</th>
					<th>Begin date</th>
					<th>End date</th>
					<th>Entry</th>
					<%
						for (Block block : blockList) {
					%>
					<tr>
						<td><%=block.getName()%></td>
						<td><%=new SimpleDateFormat("MMM dd, yyyy").format(block.getBeginDate())%></td>
						<td><%=new SimpleDateFormat("MMM dd, yyyy").format(block.getEndDate())%></td>
						<td><%=block.getEntry().getName() %></td>
					</tr>
					<%
						}
					%>
				</table>
				<%
			}
			%>
	<form role="form" action="/block/create" method="post">
		<h2>New block</h2>
		<div>
			<label for="entry">Entry:</label>
			<select name="entry" id="entry"> <%
					for (Entry entry : entryList) {%>
				<option value="<%=entry.getId()%>">
					<%=entry.getName()%>-<%=new SimpleDateFormat("MMM dd, yyyy").format(entry.getBeginDate())%>
				</option>
				<%
					}
				%>
			</select>
		</div>
		
		<div>
			<label for="block_name">Block name:</label>
			<input type="text" name="block_name" id="block_name" value="" required autofocus />
		</div>
		<div>
			<label for="begin_date">Begin date:</label>
			<input type="date" name="begin_date" id="begin_date" value="" required />
		</div>
		<div>
			<label for="end_date">End date:</label>
			<input type="date" name="end_date" id="end_date" value="" required />
		</div>
		<button type="submit">Save</button>
	</form>
			<%
			
		}%>
</body>
</html>