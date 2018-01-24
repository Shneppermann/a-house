<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="Bundle" var="lang"/>
<fmt:message bundle="${lang}" key="title.auction" var="title"/>

<fmt:message bundle="${lang}" key="wrong.login.message" var="wrongLogin"/>
<fmt:message bundle="${lang}" key="wrong.password.message" var="wrongPass"/>
<fmt:message bundle="${lang}" key="wrong.login.message.latin" var="wrongLatin"/>
<fmt:message bundle="${lang}" key="wrong.login.message.another.char" var="wrongChar"/>
<fmt:message bundle="${lang}" key="enter.login.auction" var="enterLogin"/>
<fmt:message bundle="${lang}" key="enter.pass.auction" var="enterPass"/>
<fmt:message bundle="${lang}" key="enter.button" var="enterButton"/>

<fmt:message bundle="${lang}" key="registration.title" var="regTitle"/>
<fmt:message bundle="${lang}" key="registration.name" var="regName"/>
<fmt:message bundle="${lang}" key="registration.enter.name" var="regEnterName"/>
<fmt:message bundle="${lang}" key="registration.surname" var="regSurname"/>
<fmt:message bundle="${lang}" key="registration.enter.surname" var="regEnterSurname"/>
<fmt:message bundle="${lang}" key="registration.enter.password" var="regEnterPass"/>
<fmt:message bundle="${lang}" key="registration.password" var="regPass"/>
<fmt:message bundle="${lang}" key="registration.enter.login" var="regEnterLogin"/>
<fmt:message bundle="${lang}" key="registration.login" var="regLogin"/>
<fmt:message bundle="${lang}" key="registration.confirm.password" var="regPassConfirm"/>

<fmt:message bundle="${lang}" key="registration.name.message.first" var="regNameMessage1"/>
<fmt:message bundle="${lang}" key="registration.name.message.second" var="regNameMessage2"/>
<fmt:message bundle="${lang}" key="registration.surname.message" var="regSurmameMessage"/>
<fmt:message bundle="${lang}" key="registration.login.message.first" var="regLoginMessage1"/>
<fmt:message bundle="${lang}" key="registration.login.message.second" var="regLoginMessage2"/>
<fmt:message bundle="${lang}" key="registration.login.message.third" var="regLoginMessage3"/>
<fmt:message bundle="${lang}" key="registration.login.message.fourth" var="regLoginMessage4"/>
<fmt:message bundle="${lang}" key="registration.pass.message.first" var="regPassMessage1"/>
<fmt:message bundle="${lang}" key="registration.pass.message.second" var="regPassMessage2"/>
<fmt:message bundle="${lang}" key="registration.pass.message.third" var="regPassMessage3"/>

<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>


<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>
<fmt:message bundle="${lang}" key="language.local.language" var="localLanguage"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.registration" var="contextPath"/>
<c:set var="redirectPage" value="${contextPath}" scope="session"/>
<c:set var="bannedMessage" value="${bannedUserMessage}" scope="page"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
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
            <input hidden name="contextPath" value="<c:out value="${contextPath}"/>"/>
            <button class="lang-button" type="submit" form="changeLanguage" name="local"
                    value="<c:out value="${languagePar}"/>"><c:out value="${language}"/></button>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-12 col-m-12">
        <form id="registrationForm" class="registrationForm" method="POST"
              action="${pageContext.request.contextPath}/controller?command=registration">
            <div class="form">
                <div>
                    <h1><c:out value="${regTitle}"/></h1>
                    <input hidden type="text" id="local" name="local" value="<c:out value="${localLanguage}"/>"/>
                </div>
                <div>
                    <p><c:out value="${regEnterName}"/></p>
                    <input type="text" id="name" name="name" pattern="^[A-Za-zА-Яа-я]{2,}$" required placeholder=" "/>
                    <label for="name"> <c:out value="${regName}"/> </label>
                    <div class="requirements">
                        <c:out value="${regNameMessage1}"/> <br/>
                        <c:out value="${regNameMessage2}"/> <br/>
                    </div>
                </div>
                <div>
                    <p><c:out value="${regEnterSurname}"/></p>
                    <input type="text" id="surname" name="surname" pattern="^[A-Za-zА-Яа-я]{2,}$" required
                           placeholder=" "/>
                    <label for="surname"><c:out value="${regSurname}"/></label>
                    <div class="requirements">
                        <c:out value="${regSurmameMessage}"/> <br/>
                        <c:out value="${regNameMessage2}"/> <br/>
                    </div>
                </div>
                <div>
                    <p><c:out value="${regEnterLogin}"/></p>
                    <input type="text" id="login" name="login" pattern="^[A-Z]{1}[A-Za-z0-9_]{4,16}$" required
                           placeholder=" "/>
                    <label for="login"><c:out value="${regLogin}"/></label>
                    <div class="requirements">
                        <c:out value="${regLoginMessage1}"/> <br/>
                        <c:out value="${regLoginMessage2}"/> <br/>
                        <c:out value="${regLoginMessage3}"/><br/>
                        <c:out value="${regLoginMessage4}"/>
                    </div>
                </div>
                <div>
                    <p><c:out value="${regEnterPass}"/></p>
                    <input type="password" id="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
                           required placeholder=" "/>
                    <label for="password"><c:out value="${regPass}"/></label>
                    <div class="requirements">
                        <c:out value="${regPassMessage1}"/> <br/>
                        <c:out value="${regPassMessage2}"/> <br/>
                        <c:out value="${regPassMessage3}"/>
                    </div>
                </div>
                <div>
                    <input id="submit" class="regButton" form="registrationForm" type="submit"
                           value="<c:out value="${regTitle}"/>"/>
                    <c:out value="${bannedMessage}"/>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
</body>
</html>
