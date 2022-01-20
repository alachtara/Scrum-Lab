<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Dodaj przepis</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link rel="stylesheet" href="./css/style.css">
        <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>

    <body>
        <%@ include file="fragments/headerDashboard.jsp" %>

        <section class="dashboard-section">
            <div class="row dashboard-nowrap">
                <%@ include file="fragments/sideMenuDashboard.jsp" %>

                <div class="m-4 p-3 width-medium">
                    <div class="dashboard-content border-dashed p-3 m-4 view-height">
                        <div class="row border-bottom border-3 p-1 m-1">
                            <div class="col noPadding"><h3 class="color-header text-uppercase">Lista Przepisów</h3></div>
                            <div class="col noPadding d-flex justify-content-end mb-2">
                                <a href="/app/recipe/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj przepis</a></div>
                        </div>
                        ${deleted ? "Przepis został popranie uspppunięty" : "Nie udało się usunąć przepisu. Najpierw należy usunąć przepis z planu posiłków."}
                        ${deleted ? deleted : "Nie."}

                        <table class="table border-bottom schedules-content">
                            <thead>
                            <tr class="d-flex text-color-darker">
                                <th scope="col" class="col-1">ID</th>
                                <th scope="col" class="col-2">NAZWA</th>
                                <th scope="col" class="col-7">OPIS</th>
                                <th scope="col" class="col-2 center">AKCJE</th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">

                            <c:forEach items="${recipes}" var="recipe">
                                <tr class="d-flex">
                                    <th scope="row" class="col-1">${recipe.id}</th>
                                    <td class="col-2">${recipe.name}</td>
                                    <td class="col-7">${recipe.description}</td>
                                    <input type="hidden" name="id" value="${recipe.id}"/>
                                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                        <a href='/app/recipe/delete/alert?id=${recipe.id}' class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                        <a href='/app/recipe/details?id=${recipe.id}' class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                        <a href='/app/recipe/edit?id=${recipe.id}' class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="fragments/scripts.jsp" %>
    </body>
</html>