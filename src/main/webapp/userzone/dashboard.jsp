<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Zaplanuj Jedzonko</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>

    <body>
        <%@ include file="dashboardHeader.jsp" %>

        <section class="dashboard-section">
            <div class="row dashboard-nowrap">
                <%@ include file="dashboardSideMenu.jsp" %>

                <div class="m-4 p-4 width-medium">
                    <div class="dashboard-header m-4">
                        <div class="dashboard-menu">
                            <div class="menu-item border-dashed">
                                <a href="/app/recipe/add">
                                    <i class="far fa-plus-square icon-plus-square"></i>
                                    <span class="title">dodaj przepis</span>
                                </a>
                            </div>
                            <div class="menu-item border-dashed">
                                <a href="/app/plan/add">
                                    <i class="far fa-plus-square icon-plus-square"></i>
                                    <span class="title">dodaj plan</span>
                                </a>
                            </div>
                            <div class="menu-item border-dashed">
                                    <a href="/app/recipe/plan/add">
                                        <i class="far fa-plus-square icon-plus-square"></i>
                                        <span class="title">dodaj przepis do planu</span>
                                    </a>
                            </div>

                        </div>

                        <div class="dashboard-alerts">
                            <div class="alert-item alert-info">
                                <i class="fas icon-circle fa-info-circle"></i>
                                <span class="font-weight-bold">Liczba przepisów: ${numberOfRecipes}</span>
                            </div>
                            <div class="alert-item alert-light">
                                <i class="far icon-calendar fa-calendar-alt"></i>
                                <span class="font-weight-bold">Liczba planów: ${numberOfPlans}</span>
                            </div>
                        </div>
                    </div>
                    <div class="m-4 p-4 border-dashed">
                        <h2 class="dashboard-content-title">
                            <span>Ostatnio dodany plan:</span> ${nameOfLatestPlan}
                        </h2>
                        <c:forEach items="${mapaPosilkow}" var="day"  varStatus="theCount" >
                            <table class="table">
                                <thead>
                                <tr class="d-flex">
                                    <th class="col-2">${day.getKey()}</th>
                                    <th class="col-8"></th>
                                    <th class="col-2"></th>
                                </tr>
                                </thead>
                                <c:forEach items="${day.getValue()}" var="item"  varStatus="theCount" >
                                    <tbody>
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}, ${item.recipeDescription}</td>
                                        <td class="col-2"><a href="/app/recipe/details?id=${item.recipeId}" type="button" class="btn btn-primary rounded-1">Szczegóły</a></td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="../fragments/scripts.jsp" %>
    </body>
</html>
