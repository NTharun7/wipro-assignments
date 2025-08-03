<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="jsp.BankAccount" %>
<jsp:useBean id="account" class="jsp.BankAccount" scope="request" />
<jsp:setProperty name="account" property="*" />

<%
    // Store accountType in session
    String accType = request.getParameter("accountType");
    session.setAttribute("accountType", accType);
%>

<html>
<head>
    <title>Account Info</title>
</head>
<body>
    <h2>Bank Account Details</h2>
    <p><b>Account No:</b> ${account.accountNo}</p>
    <p><b>Name:</b> ${account.name}</p>
    <p><b>Balance:</b> ₹${account.balance}</p>
    <p><b>Account Type (from session):</b> ${sessionScope.accountType}</p>

    <h3>Balance Status:</h3>
    <p>
        ${account.balance >= 5000 ? "Sufficient" : "Insufficient"}
    </p>
</body>
</html>
