<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Zaplanuj Jedzonko</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>

    <body>
        <%@ include file="fragments/headerDashboard.jsp" %>

        <section class="dashboard-section">
            <div class="row dashboard-nowrap">
                <%@ include file="fragments/sideMenuDashboard.jsp" %>

                <div class="m-4 p-3 width-medium text-color-darker">
                    <div class="m-4 border-dashed view-height">
                        ${empty requestScope.errorMessage ? "" : requestScope.errorMessage}
                        <form method="post" action="/app/user/edit">
                            <div class="mt-4 ml-4 mr-4">
                                <div class="row border-bottom border-3">
                                    <div class="col"><h3 class="color-header text-uppercase">Edytuj dane</h3></div>
                                    <div class="col d-flex justify-content-end mb-2">
                                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz
                                        </button>
                                    </div>
                                </div>

                                <table class="table borderless">
                                    <tbody>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2"><h4>Imię</h4></th>
                                        <td class="col-7">
                                            <input class="w-100 p-1" value=${currentUser.firstName} name="firstName">
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2"><h4>Nazwisko</h4></th>
                                        <td class="col-7">
                                            <input class="w-100 p-1" value=${currentUser.lastName} name="lastName">
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2"><h4>Email</h4></th>
                                        <td class="col-3">
                                            <input class="p-1 w-100" type="text" value=${currentUser.email} name="email">
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="fragments/scripts.jsp" %>
    </body>
</html>
