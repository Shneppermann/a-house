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
<fmt:message bundle="${lang}" key="edit.button.back" var="backButton"/>
<fmt:message bundle="${lang}" key="edit.user.header" var="editUserHeader"/>
<fmt:message bundle="${lang}" key="edit.user.admin" var="editAdminRole"/>
<fmt:message bundle="${lang}" key="edit.user.customer" var="editCustomerRole"/>
<fmt:message bundle="${lang}" key="edit.user.banned" var="editBanRole"/>
<fmt:message bundle="${lang}" key="edit.user.choose" var="editSelectRole"/>
<fmt:message bundle="${lang}" key="edit.user.submit" var="editSubmitRole"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.useredit" var="contextPath"/>
<c:set var= "redirectPage" value="${contextPath}" scope ="session"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User edit page</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Pattaya|Russo+One&amp;subset=cyrillic,latin-ext"
          rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<div class="header col-12 col-m-12">
    <div class="col-9 col-m-9">
        <h1><c:out value="${title}"/></h1>
        <p> </p>
    </div>
    <div class="col-3 col-m-3">
        <form id="changeLanguage" method="post" action="${pageContext.request.contextPath}/controller?command=changeLang">
            <input hidden name="contextPath" value="<c:out value="${contextPath}"/>"/>
            <button class="lang-button" type ="submit" form="changeLanguage" name="local" value="<c:out value="${languagePar}"/>"><c:out value="${language}"/></button>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-12 col-m-12 menu">
        <ul class="col-12 col-m-12">
            <li class="col-3 col-m-12">
                <form id="lotManage" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=user_manage">
                    <button type="submit" form="lotManage"><c:out value="${backButton}"/></button>
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
        <h1> <c:out value="${editUserHeader}"/> </h1>
        <form id="editUserForm" method="POST" action="${pageContext.request.contextPath}/controller?command=user_edit">
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
                    <tr>
                        <td><c:out value="${userEdit.id}"/></td>
                        <td><c:out value="${userEdit.login}"/></td>
                        <td><c:out value="${userEdit.name}"/></td>
                        <td><c:out value="${userEdit.surname}"/></td>
                        <td>
                            <select id="newRole" name="newRole">
                                <option disabled><c:out value="${editSelectRole}"/></option>
                                <option value="Admin"><c:out value="${editAdminRole}"/></option>
                                <option value="Customer"><c:out value="${editCustomerRole}"/></option>
                                <option value="Banned user"><c:out value="${editBanRole}"/></option>
                            </select>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                <tr class="tfoot">
                    <td class="tfoot" colspan="5">
                        <button class="col-12 col-m-12" type="submit" form="editUserForm"><c:out value="${editSubmitRole}"/></button>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>
</div>

<div class="footer">
    <p><c:out value="${footer}"/></p>
</div>
<script src="/css/auction_bid.js"></script>
</body>
</html>
