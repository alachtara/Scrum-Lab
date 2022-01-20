<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Szczegóły planu</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
              integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
              crossorigin="anonymous">
    </head>

    <body>
        <%@ include file="fragments/headerDashboard.jsp" %>

        <section class="dashboard-section">
            <div class="row dashboard-nowrap">
                <%@ include file="fragments/sideMenuDashboard.jsp" %>

                <div class="m-4 p-3 width-medium ">
                    <div class="dashboard-content border-dashed p-3 m-4">
                        <div class="row border-bottom border-3 p-1 m-1">
                            <div class="col noPadding">
                                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                            </div>
                            <div class="col d-flex justify-content-end mb-2 noPadding">
                                <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                            </div>
                        </div>

                        <div class="schedules-content">
                            <div class="schedules-content-header">
                                <div class="form-group row">
                                        <span class="col-sm-2 label-size col-form-label">
                                            Nazwa planu
                                        </span>
                                    <div class="col-sm-10">
                                        <p class="schedules-text">${plan.name}</p>
                                    </div>
                                </div>
                                <div class="form-group row">
                                        <span class="col-sm-2 label-size col-form-label">
                                            Opis planu
                                        </span>
                                    <div class="col-sm-10">
                                        <p class="schedules-text">
                                            ${plan.description}
                                        </p>
                                    </div>
                                </div>
                            </div>

                            <c:forEach items="${day_meal}" var="day"  varStatus="theCount" >
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
            </div>
        </section>

        <%@include file="fragments/scripts.jsp"%>
    </body>
</html>
