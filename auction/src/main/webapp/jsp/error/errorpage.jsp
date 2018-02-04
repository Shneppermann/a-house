<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="Bundle" var="lang"/>
<fmt:message bundle="${lang}" key="title.auction" var="title"/>
<fmt:message bundle="${lang}" key="title.auction" var="title"/>
<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>
<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>

<fmt:message bundle="${lang}" key="error.page.message" var="errorMessage"/>
<fmt:message bundle="${lang}" key="error.button.return" var="returnButton"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.errorpage" var="contextPath"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error!!!</title>
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
    <div class="col-12 col-m-12">
        <form name="returnForm" method="POST" action="${pageContext.request.contextPath}/controller?command=back">
            <div class="form">
                <div class="err">
                    <c:out value="${errorMessage}"/>
                </div>
                <div>
                    <input id="submit" class="regButton" type="submit" value="<c:out value="${returnButton}"/>"/>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
</body>
</html>