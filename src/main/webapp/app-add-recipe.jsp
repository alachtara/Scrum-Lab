<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
                    <div class="dashboard-content border-dashed p-3 m-4 view-height">
                        <form action="/app/recipe/add" method="post">
                            <div class="mt-4 ml-4 mr-4">
                                <div class="row border-bottom border-3">
                                    <div class="col"><h3 class="color-header text-uppercase">Nowy przepis</h3></div>
                                    <div class="col d-flex justify-content-end mb-2">
                                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                                    </div>
                                </div>

                                <table class="table borderless">
                                    <tbody>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Nazwa Przepisu</th>
                                        <td class="col-7">

                                            <input type="text" name="name" class="w-100 p-1" value="" required>
                                        </td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Opis przepisu</th>
                                        <td class="col-7"><textarea name="description" class="w-100 p-1" rows="5"></textarea></td>
                                    </tr>
                                    <tr class="d-flex">
                                        <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                                        <td class="col-3">
                                            <input type="number" name="preparation_time" class="p-1" type="number" value="" required>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class="row d-flex">
                                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób
                                        przygotowania</h3></div>
                                    <div class="col-2"></div>
                                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                                </div>
                                <div class="row d-flex">
                                    <div class="col-5 p-4">
                                        <textarea name="preparation" class="w-100 p-1" rows="10" required></textarea>
                                    </div>
                                    <div class="col-2"></div>

                                    <div class="col-5 p-4">
                                        <textarea name="ingredients" class="w-100 p-1" rows="10" required></textarea>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="fragments/scripts.jsp" %>
    </body>
</html>
