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

<fmt:message bundle="${lang}" key="button.auction" var="button"/>
<fmt:message bundle="${lang}" key="title.auction" var="title"/>
<fmt:message bundle="${lang}" key="login.auction" var="login"/>
<fmt:message bundle="${lang}" key="password.auction" var="password"/>

<fmt:message bundle="${lang}" key="wrong.login.message" var="wrongLogin"/>
<fmt:message bundle="${lang}" key="wrong.password.message" var="wrongPass"/>
<fmt:message bundle="${lang}" key="wrong.login.message.latin" var="wrongLatin"/>
<fmt:message bundle="${lang}" key="wrong.login.message.another.char" var="wrongChar"/>
<fmt:message bundle="${lang}" key="enter.login.auction" var="enterLogin"/>
<fmt:message bundle="${lang}" key="enter.pass.auction" var="enterPass"/>
<fmt:message bundle="${lang}" key="enter.button" var="enterButton"/>


<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>
<fmt:message bundle="${lang}" key="welcome.auction" var="welcome"/>
<fmt:message bundle="${lang}" key="welcomePar.auction" var="welcomePar"/>
<fmt:message bundle="${lang}" key="how.auction" var="how"/>
<fmt:message bundle="${lang}" key="howPar.auction" var="howPar"/>
<fmt:message bundle="${lang}" key="headerLotName.auction" var="headerName"/>

<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>
<fmt:message bundle="${lang}" key="language.local.language" var="localLanguage"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.login" var="contextPath"/>
<c:set var="redirectPage" value="${contextPath}" scope="session"/>
<c:set var="bannedMessage" value="${bannedUserMessage}" scope="page"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
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
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller?command=login">
            <div class="form">
                <input hidden="true" type="text" id="local" name="local" value="<c:out value="${localLanguage}"/>"/>
                <div>
                    <p><c:out value="${enterLogin}"/></p>
                    <input type="text" id="login" name="login" pattern="^[A-Za-z0-9_@\.]{2,}$" required
                           placeholder=" "/>
                    <label for="login">${login}</label>
                    <div class="requirements">
                        <c:out value="${wrongLogin}"/><br/>
                        <c:out value="${wrongLatin}"/><br/>
                        <c:out value="${wrongChar}"/>
                    </div>
                </div>
                <div>
                    <p><c:out value="${enterPass}"/></p>
                    <input type="password" id="password" name="password" pattern="^[\w@\.]{2,}$" required
                           placeholder=" "/>
                    <label for="password">${password}</label>
                    <div class="requirements">
                        <c:out value="${wrongPass}"/><br/>
                        <c:out value="${wrongLatin}"/><br/>
                        <c:out value="${wrongChar}"/>
                    </div>
                </div>
                <div>
                    <input id="submit" class="regButton" type="submit" value="<c:out value="${enterButton}"/>"/>
                </div>
                <div>
                    <div class="errorInfo">
                        <c:out value="${bannedMessage}"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
</body>
</html>


