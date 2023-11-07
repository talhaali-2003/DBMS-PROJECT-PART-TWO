<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Client Portal</title>
    <style>
        .center {
            text-align: center;
        }
    </style>
</head>

<body>

 	<center>
        <a href="login.jsp" target="_self">Logout</a><br><br>
    </center>


    <div class="center">
        <h1>Client Portal</h1>
    </div>

	<div class="center">
	    <h2>Submit a Quote Request</h2>
	    <form action="registerQuote" method="post">
	        <table border="1">
	            <tr>
	                <th>Initial Price:</th>
	                <td align="center">
	                    <input type="text" name="initialPrice" size="45" value="" onfocus="this.value='';">
	                </td>
	            </tr>
	            <tr>
	                <th>Time Window:</th>
	                <td align="center">
	                    <input type="text" name="timeWindow" size="45" value="" onfocus="this.value='';">
	                </td>
	            </tr>
	            <tr>
	                <th>Note:</th>
	                <td align="center">
	                    <input type="text" name="note" size="45" value="" onfocus="this.value='';">
	                </td>
	            </tr>
	            <tr>
	                <td align="center" colspan="2">
	                    <input type="submit" value="Register a Quote">
	                </td>
	            </tr>
	        </table>
	    </form>
	</div>

	<center>
        <a href="currentQuoteRequests.jsp" target="_self">View Quote Requests</a><br><br>
    </center>

 
</body>
</html>
