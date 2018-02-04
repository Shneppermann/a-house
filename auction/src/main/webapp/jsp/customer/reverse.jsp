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

<fmt:message bundle="${lang}" key="direct.auction" var="direct"/>
<fmt:message bundle="${lang}" key="reverse.auction" var="reverse"/>
<fmt:message bundle="${lang}" key="bidding.auction" var="bidding"/>
<fmt:message bundle="${lang}" key="lots.auction" var="allLots"/>
<fmt:message bundle="${lang}" key="add.auction" var="addLots"/>
<fmt:message bundle="${lang}" key="logout.auction" var="logout"/>
<fmt:message bundle="${lang}" key="makeBid.auction" var="makeBid"/>
<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>
<fmt:message bundle="${lang}" key="welcome.auction" var="welcome"/>
<fmt:message bundle="${lang}" key="welcomePar.auction" var="welcomePar"/>
<fmt:message bundle="${lang}" key="how.auction" var="how"/>
<fmt:message bundle="${lang}" key="howPar.auction" var="howPar"/>
<fmt:message bundle="${lang}" key="headerLotName.auction" var="headerName"/>
<fmt:message bundle="${lang}" key="headerBiddingStep.auction" var="headerStep"/>
<fmt:message bundle="${lang}" key="headerLastBid.auction" var="headerLastBid"/>
<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>
<fmt:message bundle="${lang}" key="table.header.direct" var="headerReverse"/>
<fmt:message bundle="${lang}" key="user.balance" var="userBalance"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.reverse" var="contextPath"/>
<c:set var="redirectPage" value="${contextPath}" scope="session"/>
<c:set var="lst" value="${lst}" scope="session"/>
<c:set var="notEnoughMoney" value="${notEnoughMessage}" scope="page"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reverse auction</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Pattaya|Russo+One&amp;subset=cyrillic,latin-ext"
          rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<div class="header col-12 col-m-12">
    <div class="col-9 col-m-9">
        <h1><c:out value="${title}"/></h1>
        <p><c:out value="${reverse}"/></p>
    </div>
    <div class="col-3 col-m-3">
        <form id="changeLanguage" method="post"
              action="${pageContext.request.contextPath}/controller?command=changeLang">
            <input hidden="true" name="contextPath" value="<c:out value="${contextPath}"/>"/>
            <button class="lang-button" type="submit" form="changeLanguage" name="local"
                    value="<c:out value="${languagePar}"/>"><c:out value="${language}"/></button>
        </form>
    </div>
    <div class="col-3 col-m-3">
        <span> <span> <c:out value="${user.name} "/>  <c:out value="${user.surname} "/></span></span>
    </div>
    <div class="col-3 col-m-3">
        <span> <c:out value="${userBalance} "/>  <c:out value="${user.balance} "/></span>
    </div>
</div>

<div class="row">
    <div class="col-12 col-m-12 menu">
        <ul class="col-12 col-m-12">
            <li class="col-2 col-m-12">
                <form id="reverseAuction" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=direct">
                    <button type="submit" form="reverseAuction"><c:out value="${direct}"/></button>
                </form>
            </li>
            <li class="col-2 col-m-12">
                <form id="allLots" method="POST" action="${pageContext.request.contextPath}/controller?command=lots">
                    <button type="submit" form="allLots"><c:out value="${allLots}"/></button>
                </form>
            </li>
            <li class="col-2 col-m-12">
                <form id="allBids" method="POST" action="${pageContext.request.contextPath}/controller?command=bidding">
                    <button type="submit" form="allBids"><c:out value="${bidding}"/></button>
                </form>
            </li>
            <li class="col-2 col-m-12">
                <form id="addLot" method="POST" action="${pageContext.request.contextPath}/controller?command=add_lot">
                    <button type="submit" form="addLot"><c:out value="${addLots}"/></button>
                </form>
            </li>
            <li class="col-2 col-m-12">
                <form id="logout" method="POST" action="${pageContext.request.contextPath}/controller?command=logout">
                    <button type="submit" form="logout"><c:out value="${logout}"/></button>
                </form>
            </li>
        </ul>
    </div>

    <div class="col-9 col-m-9">
        <h1><c:out value="${headerReverse}"/></h1>
        <form id="bidForm" method="POST" action="${pageContext.request.contextPath}/controller?command=reverse_bid">
            <table class="bidTable">
                <thead>
                <tr>
                    <td><c:out value="${headerName}"/></td>
                    <td><c:out value="${headerStep}"/></td>
                    <td><c:out value="${headerLastBid}"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="elem" items="${lst}" varStatus="status">
                    <tr name="${elem.lotName}" onclick="radiobuttonClick(this)">
                        <td onclick="selectFunction(this)"><c:out value="${elem.lotName}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.step}"/></td>
                        <td onclick="selectFunction(this)"><c:out value="${elem.bid}"/></td>
                        <td hidden="true"><input id="${elem.lotName}" type="radio" name="bid" value="${elem.id}"/>СТАВКА</td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr class="tfoot">
                    <td class="tfoot" colspan="2">
                        <c:out value="${notEnoughMoney}"/>
                    </td>
                    <td class="tfoot" colspan="1">
                        <button class="col-12 col-m-12" type="submit" form="bidForm"><c:out
                                value="${makeBid}"/></button>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>

    <div class="col-3 col-m-12">
        <div class="aside">
            <h2><c:out value="${welcome}"/></h2>
            <p><c:out value="${welcomePar}"/></p>
            <h2><c:out value="${how}"/></h2>
            <p><c:out value="${howPar}"/></p>
        </div>
    </div>

</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
<script src="/css/auction_bid.js"></script>
</body>
</html>
