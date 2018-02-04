<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="Bundle" var="lang"/>
<fmt:message bundle="${lang}" key="title.auction" var="title"/>
<fmt:message bundle="${lang}" key="logout.auction" var="logout"/>
<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>
<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>
<fmt:message bundle="${lang}" key="manage.button.lots" var="lotManageButton"/>
<fmt:message bundle="${lang}" key="manage.header.users" var="headerUserManage"/>
<fmt:message bundle="${lang}" key="manage.column.id" var="idColumn"/>
<fmt:message bundle="${lang}" key="manage.column.login" var="loginColumn"/>
<fmt:message bundle="${lang}" key="manage.column.name" var="nameColumn"/>
<fmt:message bundle="${lang}" key="manage.column.surname" var="surnameColumn"/>
<fmt:message bundle="${lang}" key="manage.column.role" var="roleColumn"/>
<fmt:message bundle="${lang}" key="manage.button.edit.user" var="editUserButton"/>

<fmt:message bundle="${lang}" key="user.manage.info.title" var="infoTitle"/>
<fmt:message bundle="${lang}" key="user.manage.info.admin" var="infoAdmin"/>
<fmt:message bundle="${lang}" key="user.manage.info.customer" var="infoCustomer"/>
<fmt:message bundle="${lang}" key="user.manage.info.banned" var="infoBanned"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.usermanage" var="contextPath"/>
<c:set var="redirectPage" value="${contextPath}" scope="session"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User management page</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Pattaya|Russo+One&amp;subset=cyrillic,latin-ext"
          rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<div class="header col-12 col-m-12">
    <div class="col-9 col-m-9">
        <h1><c:out value="${title}"/></h1>
    </div>
    <div class="col-3 col-m-3">
        <form id="changeLanguage" method="post"
              action="${pageContext.request.contextPath}/controller?command=changeLang">
            <input hidden="true" name="contextPath" value="<c:out value="${contextPath}"/>"/>
            <button class="lang-button" type="submit" form="changeLanguage" name="local"
                    value="<c:out value="${languagePar}"/>"><c:out value="${language}"/></button>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-12 col-m-12 menu">
        <ul class="col-12 col-m-12">
            <li class="col-3 col-m-12">
                <form id="lotManage" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=lot_manage">
                    <button type="submit" form="lotManage"><c:out value="${lotManageButton}"/></button>
                </form>
            </li>
            <li class="col-6 col-m-12">
            </li>
            <li class="col-3 col-m-12">
                <form id="logout" method="POST" action="${pageContext.request.contextPath}/controller?command=logout">
                    <button type="submit" form="logout"><c:out value="${logout}"/></button>
                </form>
            </li>
        </ul>
    </div>

    <div class="col-9 col-m-9">
        <h1><c:out value="${headerUserManage}"/></h1>
        <form id="editUserForm" method="POST"
              action="${pageContext.request.contextPath}/controller?command=user_edit_page">
            <table class="userTable">
                <thead>
                <tr>
                    <td><c:out value="${idColumn}"/></td>
                    <td><c:out value="${loginColumn}"/></td>
                    <td><c:out value="${nameColumn}"/></td>
                    <td><c:out value="${surnameColumn}"/></td>
                    <td><c:out value="${roleColumn}"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="elem" items="${userList}" varStatus="status">
                    <tr name="${elem.login}" onclick="radiobuttonClick(this)">
                        <td onclick="selectFunction(this)"><c:out value="${elem.id}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.login}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.name}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.surname}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.role}"/></td>
                        <td hidden="true"><input id="${elem.login}" type="radio" name="userId" value="${elem.id}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="tfoot">
                    <td class="tfoot" colspan="5">
                        <button class="col-12 col-m-12" type="submit" form="editUserForm"><c:out
                                value="${editUserButton}"/></button>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>

    <div class="col-3 col-m-12">
        <div class="aside">
            <h1><c:out value="${infoTitle}"/></h1>
            <ul>
                <li><c:out value="${infoAdmin}"/></li>
                <li><c:out value="${infoCustomer}"/></li>
                <li><c:out value="${infoBanned}"/></li>
            </ul>
        </div>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
<script src="/css/auction_bid.js"></script>
</body>
</html>
