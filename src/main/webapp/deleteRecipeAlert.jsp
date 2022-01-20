<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <table>
            <tr>
                <td style="text-align:right;">
                    DO YOU WANT CANCEL THIS RECIPE ?  REMEMBER IF YOUR RECIPE <BR>
                    IS A PART OF ONE SCHEDULE IT'S IMPOSSIBLE TO CANCEL IT
                </td>
            </tr>
            <tr>
                <td>
                    <a href="/app/recipe/delete?id=${id}"><input type="button" id="yes" value="Yes" /></a>
                    <a href="/app/recipe/list"><input type="button" id="no" value="No" /></a>
                </td>
            </tr>
        </table>
    </body>
</html>
