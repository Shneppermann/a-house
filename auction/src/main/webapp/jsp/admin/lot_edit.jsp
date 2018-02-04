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

<fmt:message bundle="${lang}" key="manage.lot.column.id" var="columnLotId"/>
<fmt:message bundle="${lang}" key="manage.lot.column.name" var="columnLotName"/>
<fmt:message bundle="${lang}" key="manage.lot.column.step" var="columnBidStep"/>
<fmt:message bundle="${lang}" key="manage.lot.column.bid" var="columnLastBid"/>
<fmt:message bundle="${lang}" key="manage.lot.column.type" var="columnType"/>
<fmt:message bundle="${lang}" key="manage.lot.column.state" var="columnState"/>
<fmt:message bundle="${lang}" key="manage.lot.button.edit.lot" var="buttonLotEdit"/>
<fmt:message bundle="${lang}" key="manage.lot.button.delete" var="buttonLotDelete"/>

<fmt:message bundle="${lang}" key="edit.button.back" var="backButton"/>
<fmt:message bundle="${lang}" key="edit.lot.header" var="editLotHeader"/>
<fmt:message bundle="${lang}" key="edit.lot.offered" var="editStateOffered"/>
<fmt:message bundle="${lang}" key="edit.lot.bidding" var="editStateBidding"/>
<fmt:message bundle="${lang}" key="edit.lot.purchased" var="editStatePurchased"/>
<fmt:message bundle="${lang}" key="edit.lot.banned" var="editStateBanned"/>
<fmt:message bundle="${lang}" key="edit.lot.button.edit" var="editButtonEdit"/>

<fmt:setBundle basename="Pages" var="config"/>
<fmt:message bundle="${config}" key="path.page.lotedit" var="contextPath"/>
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
        <p></p>
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
        <h1><c:out value="${editLotHeader}"/></h1>
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
            <tr>
                <td><c:out value="${lot.id}"/></td>
                <td><c:out value="${lot.lotName}"/></td>
                <td><c:out value="${lot.step}"/></td>
                <td><c:out value="${lot.bid}"/></td>
                <td><c:out value="${lot.auctionType}"/></td>
                <td>
                    <select form="editLotForm" id="newState" name="newState">
                        <option disabled><c:out value="${editLotHeader}"/></option>
                        <option value="offered"><c:out value="${editStateOffered}"/></option>
                        <option value="bidding_lot"><c:out value="${editStateBidding}"/></option>
                        <option value="purchased"><c:out value="${editStatePurchased}"/></option>
                        <option value="banned"><c:out value="${editStateBanned}"/></option>
                    </select>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr class="tfoot">
                <td class="tfoot" colspan="3">
                    <form id="deleteLotForm" method="POST"
                          action="${pageContext.request.contextPath}/controller?command=delete_lot">
                        <button class="col-12 col-m-12" type="submit" form="deleteLotForm"><c:out
                                value="${buttonLotDelete}"/></button>
                    </form>
                </td>
                <td class="tfoot" colspan="3">
                    <form id="editLotForm" method="POST"
                          action="${pageContext.request.contextPath}/controller?command=lot_edit">
                        <button class="col-12 col-m-12" type="submit" form="editLotForm"><c:out
                                value="${buttonLotEdit}"/></button>
                    </form>
                </td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>
<%@ include file="/jsp/jspf/footer.jspf" %>
<script src="/css/auction_bid.js"></script>
</body>
</html>