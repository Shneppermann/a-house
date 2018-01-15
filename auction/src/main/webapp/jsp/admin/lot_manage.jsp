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
<fmt:message bundle="${lang}" key="manage.button.users" var="userManageButton"/>
<fmt:message bundle="${lang}" key="manage.header.lots" var="headerLotsManage"/>
<fmt:message bundle="${lang}" key="manage.lot.column.id" var="columnLotId"/>
<fmt:message bundle="${lang}" key="manage.lot.column.name" var="columnLotName"/>
<fmt:message bundle="${lang}" key="manage.lot.column.step" var="columnBidStep"/>
<fmt:message bundle="${lang}" key="manage.lot.column.bid" var="columnLastBid"/>
<fmt:message bundle="${lang}" key="manage.lot.column.type" var="columnType"/>
<fmt:message bundle="${lang}" key="manage.lot.column.state" var="columnState"/>
<fmt:message bundle="${lang}" key="manage.lot.button.edit.lot" var="buttonLotEdit"/>

<fmt:message bundle="${lang}" key="lot.manage.info.types.title" var="infoTypesTitle"/>
<fmt:message bundle="${lang}" key="lot.manage.info.types.direct" var="infoTypesDirect"/>
<fmt:message bundle="${lang}" key="lot.manage.info.types.reverse" var="infoTypesReverse"/>
<fmt:message bundle="${lang}" key="lot.manage.info.states.title" var="infoStatesTitle"/>
<fmt:message bundle="${lang}" key="lot.manage.info.states.offered" var="infoStatesOffered"/>
<fmt:message bundle="${lang}" key="lot.manage.info.states.bidding" var="infoStatesBidding"/>
<fmt:message bundle="${lang}" key="lot.manage.info.states.purchase" var="infoStatesPurchase"/>
<fmt:message bundle="${lang}" key="lot.manage.info.states.banned" var="infoStatesBanned"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.lotmanage" var="contextPath"/>
<c:set var= "redirectPage" value="${contextPath}" scope ="session"/>

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
                <form id="userManage" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=user_manage">
                    <button type="submit" form="userManage"><c:out value="${userManageButton}"/></button>
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
        <h1> <c:out value="${headerLotsManage}"/> </h1>
        <form id="editLotsForm" method="POST" action="${pageContext.request.contextPath}/controller?command=lot_edit_page">
            <table class="lotTable">
                <thead>
                <tr>
                    <td><c:out value="${columnLotId}"/></td>
                    <td><c:out value="${columnLotName}"/></td>
                    <td><c:out value="${columnBidStep}"/></td>
                    <td><c:out value="${columnLastBid}"/></td>
                    <td><c:out value="${columnType}"/></td>
                    <td><c:out value="${columnState}"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="elem" items="${lst}" varStatus="status">
                    <tr name="${elem.lotName}" onclick="radiobuttonClick(this)">
                        <td onclick="selectFunction(this)"><c:out value="${elem.id}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.lotName}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.step}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.bid}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.auctionType}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.lotState}"/></td>
                        <td hidden><input id="${elem.lotName}" type="radio" name="lotId" value="${elem.id}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="tfoot">
                    <td class="tfoot" colspan="6">
                        <button class="col-12 col-m-12" type="submit" form="editLotsForm"><c:out value="${buttonLotEdit}"/></button>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>

    <div class="col-3 col-m-12">
        <div class="aside">
            <h1><c:out value="${infoTypesTitle}"/></h1>
            <ul>
                <li><c:out value="${infoTypesDirect}"/></li>
                <li><c:out value="${infoTypesReverse}"/></li>
            </ul>
            <h1><c:out value="${infoStatesTitle}"/></h1>
            <ul>
                <li><c:out value="${infoStatesOffered}"/></li>
                <li><c:out value="${infoStatesBidding}"/></li>
                <li><c:out value="${infoStatesPurchase}"/></li>
                <li><c:out value="${infoStatesBanned}"/></li>
            </ul>
        </div>
    </div>
</div>
<div class="footer">
    <p><c:out value="${footer}"/></p>
</div>
<script src="/css/auction_bid.js"></script>
</body>
</html>
