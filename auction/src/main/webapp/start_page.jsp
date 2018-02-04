<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="Bundle" var="lang"/>

<fmt:message bundle="${lang}" key="button.auction" var="button"/>
<fmt:message bundle="${lang}" key="title.auction" var="title"/>

<fmt:message bundle="${lang}" key="welcome.auction" var="welcome"/>
<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>

<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>
<fmt:message bundle="${lang}" key="language.local.language" var="localLanguage"/>

<fmt:message bundle="${lang}" key="start.page.login.button" var="loginButton"/>
<fmt:message bundle="${lang}" key="start.page.register.button" var="registrationButton"/>
<fmt:message bundle="${lang}" key="start.info.header" var="infoHeader"/>
<fmt:message bundle="${lang}" key="start.info.paragraph1" var="infoParagraph1"/>
<fmt:message bundle="${lang}" key="start.info.paragraph2" var="infoParagraph2"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.startpage" var="contextPath"/>
<c:set var="redirectPage" value="${contextPath}" scope="session"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Auction House</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Pattaya|Russo+One&amp;subset=cyrillic,latin-ext"
          rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<div class="header col-12 col-m-12">
    <div class="col-9 col-m-9">
        <h1><c:out value="${title}"/></h1>
        <p><c:out value="${welcome}"/></p>
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
    <div class="col-12 col-m-12">
        <div class="form">
            <div>
                <h1><c:out value="${infoHeader}"/></h1>
                <p><c:out value="${infoParagraph1}"/></p>
                <p><c:out value="${infoParagraph2}"/></p>
            </div>
        </div>
    </div>
    <div class="col-4 col-m-4">
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller?command=login_page">
            <input id="loginSubmit" class="regButton" type="submit" value="<c:out value="${loginButton}"/>"/>
        </form>
    </div>
    <div class="col-4 col-m-4">
    </div>
    <div class="col-4 col-m-4">
        <form name="registrationForm" method="POST"
              action="${pageContext.request.contextPath}/controller?command=registration_page">
            <input id="registrationSubmit" class="regButton" type="submit"
                   value="<c:out value="${registrationButton}"/>"/>
        </form>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
</body>
</html>
