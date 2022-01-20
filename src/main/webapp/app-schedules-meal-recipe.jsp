<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Dodaj przepis do planu</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
              rel="stylesheet">
        <link href='<c:url value="/css/style.css"/>' rel="stylesheet" type="text/css">
        <script type="text/javascript" src="/javaScript/app.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    </head>

    <body>
        <%@ include file="fragments/headerDashboard.jsp" %>

        <section class="dashboard-section">
            <div class="row dashboard-nowrap">
                <%@ include file="fragments/sideMenuDashboard.jsp" %>
                <div class="m-4 p-3 width-medium">
                    <div class="dashboard-content border-dashed p-3 m-4 view-height">
                        <div class="row border-bottom border-3 p-1 m-1">
                            <div class="col noPadding">
                                <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                            </div>
                            <div class="col d-flex justify-content-end mb-2 noPadding">
                                <a href="#" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4" id="buttonAdd">Zapisz</a>
                            </div>
                        </div>

                        <div class="schedules-content">
                            <form id="addForm" action="/app/recipe/plan/add" method="post">
                                <div class="form-group row">
                                    <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                                        Wybierz plan
                                    </label>
                                    <div class="col-sm-3">
                                        <select class="form-control" id="choosePlan" name="choosePlan" required>
                                            <c:forEach var="plan" items="${requestScope.plans}">
                                                <option value="${plan.id}">${plan.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-sm-2 label-size col-form-label">
                                        Nazwa posiłku
                                    </label>
                                    <div class="col-sm-10">
                                        <select class="form-control" id="name" name="name" required>
                                            <option value="śniadanie">Śniadanie</option>
                                            <option value="drugie śniadanie">Drugie śniadanie</option>
                                            <option value="obiad">Obiad</option>
                                            <option value="podwieczorek">Podwieczorek</option>
                                            <option value="kolacja">Kolacja</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="number" class="col-sm-2 label-size col-form-label">
                                        Numer posiłku
                                    </label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="number" name="number" required>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="recipie" class="col-sm-2 label-size col-form-label">
                                        Przepis
                                    </label>
                                    <div class="col-sm-4">
                                        <select class="form-control" id="recipie" name="recipe" required>
                                            <c:forEach items="${requestScope.recipes}" var="recipe">
                                                <option value="${recipe.id}">${recipe.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="day" class="col-sm-2 label-size col-form-label">
                                        Dzień
                                    </label>
                                    <div class="col-sm-2">
                                        <select class="form-control" name="day" id="day">
                                            <c:forEach items="${requestScope.days}" var="day">
                                                <option value="${day.id}">${day.dayName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <%@ include file="fragments/scripts.jsp" %>
    </body>
</html>