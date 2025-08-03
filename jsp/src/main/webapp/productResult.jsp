<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="jsp.Product" %>  <%-- Updated package name --%>
<jsp:useBean id="product" class="jsp.Product" scope="request" />  <%-- Updated class --%>
<jsp:setProperty name="product" property="*" />

<html>
<head>
    <title>Product Details</title>
</head>
<body>
    <h2>Product Details Submitted</h2>
    <p><b>Product ID:</b> <jsp:getProperty name="product" property="productId" /></p>
    <p><b>Name:</b> <jsp:getProperty name="product" property="name" /></p>
    <p><b>Price:</b> <jsp:getProperty name="product" property="price" /></p>
    <p><b>Quantity:</b> <jsp:getProperty name="product" property="quantity" /></p>
</body>
</html>
