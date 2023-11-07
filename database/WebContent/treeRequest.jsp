<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tree Request</title>
</head>

<body>
    <center>
        <h1>Tree Request</h1>
    </center>

    <center>
        <a href="clientportal.jsp" target="_self">Go Back</a><br><br>
    </center>

    <div align="center">
        <form action="registerTreeRequest" method="post">
            <table border="1">
                <tr>
                    <th>Size (Decimal, e.g., 10.2):</th>
                    <td align="center">
                        <input type="text" name="size" size="45" value="" onfocus="this.value='';">
                    </td>
                </tr>
                <tr>
                    <th>Height (Decimal, e.g., 15):</th>
                    <td align="center">
                        <input type="text" name="height" size="45" value="" onfocus="this.value='';">
                    </td>
                </tr>
                <tr>
                    <th>Location:</th>
                    <td align="center">
                        <input type="text" name="location" size="45" value="" onfocus="this.value='';">
                    </td>
                </tr>
                <tr>
                    <th>Near House (True/False):</th>
                    <td align="center">
                        <select name="nearHouse">
                            <option value="true">True</option>
                            <option value="false">False</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>Note:</th>
                    <td align="center">
                        <textarea name="note" rows="4" cols="40"></textarea>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <input type="submit" value="Submit Request">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
