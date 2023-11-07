<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1>List all users, clients, quotes, tree requests, work orders, and bills</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User Name</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.username}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.role}" /></td>
            </c:forEach>
        </table>
                 <table border="1" cellpadding="6">
            <caption><h2>List of Clients</h2></caption>
            <tr>
			        <th>Client ID</th>
			        <th>First Name</th>
			        <th>Last Name</th>
			        <th>Address</th>
			        <th>Credit Card Info</th>
			        <th>Phone Number</th>
       			    <th>Email</th>
            </tr>
             <c:forEach var="Client" items="${listClients}">
                <tr style="text-align:center">
                    <td><c:out value="${Client.clientID}" /></td>
		            <td><c:out value="${Client.firstName}" /></td>
		            <td><c:out value="${Client.lastName}" /></td>
		            <td><c:out value="${Client.address}" /></td>
		            <td><c:out value="${Client.creditCardInfo}" /></td>
		            <td><c:out value="${Client.phoneNumber}" /></td>
		            <td><c:out value="${Client.email}" /></td>
        </tr>
            </c:forEach>
        </table>
          <table border="1" cellpadding="6">
            <caption><h2>List of Quotes</h2></caption>
            <tr>
            	<th>Quote ID</th>
                <th>Initial Price</th>
                <th>Note<th>
                <th>Time Window</th>
            </tr>
             <c:forEach var="Quote" items="${listQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${Quote.quoteID}" /></td>
                    <td><c:out value="${Quote.initialprice}" /></td>
                    <td><c:out value="${Quote.note}" /></td>
                    <td><c:out value="${Quote.timeWindow}" /></td>
            </c:forEach>
        </table>
        <table border="1" cellpadding="6">
            <caption><h2>List of Tree Requests</h2></caption>
            <tr>
            	<th>Request ID</th>
                <th>Size</th>
                <th>Height</th>
                <th>Location</th>
                <th>Near House</th>
                <th>Note</th>
                <th>Rejected</th>
            </tr>
             <c:forEach var="request" items="${listTreeRequests}">
                <tr style="text-align:center">
                    <td><c:out value="${request.requestID}" /></td>
                    <td><c:out value="${request.size}" /></td>
                    <td><c:out value="${request.height}" /></td>
                    <td><c:out value="${request.location}" /></td>
                    <td><c:out value="${request.nearHouse}" /></td>
                    <td><c:out value="${request.note}" /></td>
                    <td><c:out value="${request.rejected}" /></td>
            </c:forEach>
        </table>   
                <table border="1" cellpadding="6">
            <caption><h2>List of Orders</h2></caption>
            <tr>
			        <th>Order ID</th>
			        <th>Work Status</th>
            </tr>
             <c:forEach var="order" items="${listOrderOfWork}">
                <tr style="text-align:center">
		            <td><c:out value="${order.orderID}" /></td>
		            <td><c:out value="${order.workStatus}" /></td>
            </c:forEach>
        </table>
                </table>   
                <table border="1" cellpadding="6">
            <caption><h2>List of Bills</h2></caption>
            <tr>
			        <th>Bill ID</th>
			        <th>Bill Status</th>
			        <th>Note</th>
            </tr>
             <c:forEach var="Bill" items="${listBills}">
                <tr style="text-align:center">
		            <td><c:out value="${Bill.billID}" /></td>
		            <td><c:out value="${Bill.billStatus}" /></td>
		            <td><c:out value="${Bill.note}" /></td>
            </c:forEach>
        </table>      
	</div>
	</div>
	</div>

</body>
</html>