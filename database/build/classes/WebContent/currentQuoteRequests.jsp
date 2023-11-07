<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quote Requests</title>
</head>
<body>
	   <div class="center">
        <table border="1">
            <thead>
                <tr>
                    <th>Initial Price</th>
                    <th>Time Window</th>
                    <th>Note</th>
                </tr>
            </thead>
            <tbody>
     		 <c:forEach var="quote" items="${listQuote}">
                <tr style="text-align:center">
                    <td><c:out value="${quote.quoteID}" /></td>
                    <td><c:out value="${quote.initialPrice}" /></td>
                    <td><c:out value="${quote.note}" /></td>
                    <td><c:out value="${quote.timeWindow}" /></td>
                        <td>
                            <form action="AgreeServlet" method="post">
                                <input type="submit" name="decision" value="AGREE">
                            </form>
                            <form action="QuitServlet" method="post">
                                <input type="submit" name="decision" value="QUIT">
                            </form>
                            <form action="RespondToQuoteServlet" method="post">
                                <input type="hidden" name="quoteID" value="${quote.quoteID}">
                                <label for="response">Response:</label>
                                <input type="text" name="response" id="response">
                                <input type="submit" value="RESPONSE">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>