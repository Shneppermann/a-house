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

<fmt:message bundle="${lang}" key="footer.auction" var="footer"/>

<fmt:message bundle="${lang}" key="language.auction" var="language"/>
<fmt:message bundle="${lang}" key="language.parameter.auction" var="languagePar"/>
<fmt:message bundle="${lang}" key="table.header.addlots" var="headeraddLots"/>
<fmt:message bundle="${lang}" key="offer.new.auction" var="offerNew"/>

<fmt:message bundle="${lang}" key="add.lot.name.auction" var="lotName"/>
<fmt:message bundle="${lang}" key="add.lot.start.price.auction" var="startPrice"/>
<fmt:message bundle="${lang}" key="add.lot.step.auction" var="step"/>
<fmt:message bundle="${lang}" key="add.auction.type" var="auctionType"/>
<fmt:message bundle="${lang}" key="add.auction.select" var="selectType"/>
<fmt:message bundle="${lang}" key="user.balance" var="userBalance"/>
<fmt:message bundle="${lang}" key="addlot.info.title" var="infoAddTitle"/>
<fmt:message bundle="${lang}" key="addlot.info.paragraph.first" var="infoAddPar1"/>
<fmt:message bundle="${lang}" key="addlot.info.paragraph.second" var="infoAddPar2"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.addlot" var="contextPath"/>
<c:set var="redirectPage" value="${contextPath}" scope="session"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add a new lot</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Pattaya|Russo+One&amp;subset=cyrillic,latin-ext"
          rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<div class="header col-12 col-m-12">
    <div class="col-9 col-m-9">
        <h1><c:out value="${title}"/></h1>
        <p><c:out value="${addLots}"/></p>
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
                <form id="allLots" method="POST" action="${pageContext.request.contextPath}/controller?command=reverse">
                    <button type="submit" form="allLots"><c:out value="${reverse}"/></button>
                </form>
            </li>
            <li class="col-2 col-m-12">
                <form id="allBids" method="POST" action="${pageContext.request.contextPath}/controller?command=bidding">
                    <button type="submit" form="allBids"><c:out value="${bidding}"/></button>
                </form>
            </li>
            <li class="col-2 col-m-12">
                <form id="addLot" method="POST" action="${pageContext.request.contextPath}/controller?command=lots">
                    <button type="submit" form="addLot"><c:out value="${allLots}"/></button>
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
        <h1><c:out value="${headeraddLots}"/></h1>
        <form id="addForm" method="POST" action="${pageContext.request.contextPath}/controller?command=offer_lot">
            <table class="bidTable">
                <thead>
                <tr>
                </tr>
                </thead>
                <tbody class="tfoot">
                <tr class="tfoot">
                    <td class="tfoot"><label for="lotName"><c:out value="${lotName}"/></label></td>
                    <td class="tfoot"><input class="addlot" id="lotName" type="text" name="lotName" required/></td>
                </tr>
                <tr class="tfoot">
                    <td class="tfoot"><label for="startPrice"><c:out value="${startPrice}"/></label></td>
                    <td class="tfoot"><input class="addlot" id="startPrice" type="number" name="startPrice" required/>
                    </td>
                </tr>
                <tr class="tfoot">
                    <td class="tfoot"><label for="step"><c:out value="${step}"/></label></td>
                    <td class="tfoot"><input class="addlot" id="step" type="number" name="step" required/></td>
                </tr>
                <tr class="tfoot">
                    <td class="tfoot"><label for="auctionType"><c:out value="${auctionType}"/></label></td>
                    <td class="tfoot">
                        <select class="addlot" id="auctionType" name="auctionType">
                            <option disabled><c:out value="${selectType}"/></option>
                            <option selected value="direct"><c:out value="${direct}"/></option>
                            <option value="reverse"><c:out value="${reverse}"/></option>
                        </select>
                    </td>
                </tr>
                </tbody>
                <tfoot>
                <tr class="tfoot">
                    <td class="tfoot" colspan="3">
                        <button class="col-12 col-m-12" type="submit" form="addForm"><c:out
                                value="${offerNew}"/></button>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>

    <div class="col-3 col-m-12">
        <div class="aside">
            <h2><c:out value="${infoAddTitle}"/></h2>
            <p><c:out value="${infoAddPar1}"/></p>
            <p><c:out value="${infoAddPar2}"/></p>
        </div>
    </div>

</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
<script src="/css/auction_bid.js"></script>
</body>
</html>