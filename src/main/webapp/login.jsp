<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
    <h1>Log in</h1>

<c:if test="${not empty error}">
	<div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
	<div class="msg">${msg}</div>
</c:if>

<form role="form" action="/login" method="post">
    <div>
        <label for="username">UserID</label>
        <input type="username" name="username" id="username" value="" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <button type="submit">Sign in</button>
</form>
</body>
</html>