<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@include file="include.jsp" %>
<%--
  ~ Copyright (c) 2010-2012. Axon Framework
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<html>
<head>
    <title>Welcome to the iCoin</title>
</head>
<body>
<content tag="title">Welcome</content>
<content tag="tagline">Have fun playing with the iCoin Exchange</content>
<content tag="herounit">
    <div class="hero-unit">
        <h1>The trader</h1>

        <p>Welcome to the proof of concept of Axon Trader. This sample is created to showcase axon capabilities. Next to
            that we wanted to create a cool app with a nice front-end that we can really use as a showcase.</p>

        <p>If you are logged in, you can go to your dashboard.</p>

        <p><a class="btn primary large" href="${ctx}/dashboard">Dashboard &raquo;</a></p>
    </div>
</content>

<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <div class="jumbotron">
                <h2>Trade with confidence on Bitcoin exchange!</h2>
                <p class="lead">iCoin is the world's most established Bitcoin exchange. You can quickly and securely trade bitcoins with other people around the world!</p>
                <sec:authorize access="isAnonymous()">
                <p><a class="btn btn-success btn-wide" href="/signup" role="button">Sign up today</a></p>
                </sec:authorize>
            </div>
        </div>
        <div class="col-lg-6" id="sidebar" role="navigation">
            <div class="left carousel-control" data-slide="prev"></div>
            <div class="right carousel-control" data-slide="next"></div>
            <h4 class="text-center">News Feed</h4>
            <ul class="list-group">
                <li class="list-group-item"><span class="fui-eye"></span> <a href="#"> <strong>BTC goes up like a rockit</strong></a></li>
                <li class="list-group-item"><span class="fui-eye"></span> <a href="#"><strong>LTC is So HOT</strong></a> </li>
                <li class="list-group-item"><span class="fui-eye"></span> <a href="#"><strong>XPM is rising crazily</strong></a></li>
                <li class="list-group-item"><span class="fui-eye"></span> <a href="#"><strong>Risk of the Coin Trading</strong></a></li>
                <li class="list-group-item"><span class="fui-eye"></span> <a href="#"><strong>Risk of the Coin Trading</strong></a></li>
            </ul>

        </div>
    </div>
</div>


<div class="container">

<div class="row">
<div class="col-md-9">
<div class="masthead">
    <ul class="nav nav-justified">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Trade</a></li>
        <li><a href="#">News</a></li>
        <li><a href="#">Terms</a></li>
        <li><a href="#">FAQ</a></li>
        <li><a href="#">Support</a></li>
    </ul>
</div>
<div class="well-new">
    <%--<p>--%>
        <%--<button type="button" class="btn btn-lg btn-danger">BTC/CNY</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">LTC/BTC</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">LTC/CNY</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">XPM/CNY</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">XPM/BTC</button>--%>
    <%--</p>--%>
    <%--<p>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">FTC/CNY</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">FTC/BTC</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">PPC/CNY</button>--%>
        <%--<button type="button" class="btn btn-lg btn-primary">PPC/CNY</button>--%>
    <%--</p>--%>

    <div id="charts" style="height: 400px; min-width: 350px"></div>
</div>

<!-- Example row of columns -->

<div class="row">
    <div class="col-md-6">
        <div class="panel panel-sell">
            <div class="panel-body">
                <form:form commandName="sellOrder" id="sellOrder" action="/sell/BTC" class="form-horizontal" role="form">
                    <form:hidden id="sellCoinId" path="coinId"/>
                    <form:hidden id="sellCoinName" path="coinName"/>
                    <form:hidden id="balance" path="balance"/>

                    <div class="form-group">
                        <label for="highestBid" class="col-sm-5 control-label">Highest Bid Price</label>
                        <label id="highestBid" class="col-sm-5  form-control-static text-success">
                            <fmt:formatNumber value="${sellOrder.suggestedPrice}" type="number" pattern="#.###"/>
                        </label>
                        <label class="col-sm-1  form-control-static text-info">CNY</label>
                    </div>
                    <div class="form-group">
                        <label for="balanceToSell" class="col-sm-5 control-label">Balance</label>
                        <label id="balanceToSell" class="col-sm-5  form-control-static text-success">
                            <fmt:formatNumber value="${sellOrder.balance}" type="number" pattern="#.####"/>
                        </label>
                        <label class="col-sm-1  form-control-static text-info">BTC</label>
                    </div>
                    <div class="form-group">
                        <label for="amountToSell" class="col-sm-5 control-label"><spring:message code="order.sellAmount"/></label>
                        <div class="input-group col-sm-7">
                            <form:input path="tradeAmount" class="form-control sellGreaterThan" placeholder="Sell Amount" id="amountToSell" name="amountToSell"/>
                            <span class="input-group-addon alert-warning">BTC</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="priceToSell" class="col-sm-5 control-label"><spring:message code="order.sellPrice"/></label>
                        <div class="input-group col-sm-7">
                            <form:input path="itemPrice" type="text" class="form-control" placeholder="Price" id="priceToSell" name="priceToSell"/>
                            <span class="input-group-addon alert-warning">CNY</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="totalToSell" class="col-sm-5 control-label">Total</label>
                        <label id="totalToSell" class="col-sm-5  form-control-static text-danger">0</label>
                        <label class="col-sm-1  form-control-static text-info">BTC</label>
                    </div>
                    <div class="form-group">
                        <label for="feeToSell" class="col-sm-5 control-label">Fee</label>
                        <label id="feeToSell" class="col-sm-5  form-control-static text-danger">0</label>
                        <label class="col-sm-1  form-control-static text-info">BTC</label>
                    </div>
                    <spring:hasBindErrors name="sellOrder">
                        <div class="alert alert-warning alert-dismissable">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <form:errors path="*" element="div"/>
                        </div>
                    </spring:hasBindErrors>

                    <div id="sellModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="sellPassword" class="col-sm-4 control-label">Exchange password</label>
                                        <div class="input-group col-sm-6">
                                            <input id="sellPassword" name="tradingPassword" placeholder="Your exchange password" class="form-control" type="password" value=""/>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-5 col-md-offset-5">
                                            <div class="btn-group">
                                                <button type="submit" class="btn btn-primary btn-sm">Confirm</button>
                                                <button type="button" class="btn btn-warning btn-sm" data-dismiss="modal">
                                                    Cancel
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>

                    <%--<input class="btn primary" type="submit" name="submit" value="Place Order"/>--%>
                    <button type="submit" id="sellSubmit" data-toggle="modal" data-target="#sellModal"
                            class="btn btn-danger col-lg-offset-5 col-lg-3">Sell</button>
                </form:form>
            </div>
        </div>
    </div> <%--end of sell panel--%>

    <div class="col-md-6">
        <div class="panel panel-buy">
            <div class="panel-body">
                <form:form commandName="buyOrder" id="buyOrder" action="/buy/BTC" class="form-horizontal" role="form">
                <form:hidden id="buyerCoinId" path="coinId"/>
                <form:hidden id="buyCoinName" path="coinName"/>
                <form:hidden id="balance" path="balance"/>

                <div class="form-group">
                    <label for="lowestAsk" class="col-sm-5 control-label">Lowest Ask Price</label>
                    <label id="lowestAsk" class="col-sm-5  form-control-static text-success">
                        <fmt:formatNumber value="${buyOrder.suggestedPrice}" type="number" pattern="#.###"/>
                    </label>
                    <label class="col-sm-1  form-control-static text-info">CNY</label>
                </div>
                <div class="form-group">
                    <label for="balanceToBuy" class="col-sm-5 control-label">Balance</label>
                    <label id="balanceToBuy" class="col-sm-5  form-control-static text-success">
                        <fmt:formatNumber value="${buyOrder.balance}" type="number" pattern="#.###"/>
                    </label>
                    <label class="col-sm-1  form-control-static text-info">CNY</label>
                </div>
                <div class="form-group">
                    <label for="amountToBuy" class="col-sm-5 control-label"><spring:message code="order.buyAmount"/></label>
                    <div class="input-group col-sm-7">
                        <form:input path="tradeAmount" class="form-control buyGreaterThan" placeholder="Buy Amount" id="amountToBuy" />
                        <span class="input-group-addon alert-warning">BTC</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="priceToBuy" class="col-sm-5 control-label"><spring:message code="order.buyPrice"/></label>
                    <div class="input-group col-sm-7">
                        <form:input path="itemPrice" type="text" class="form-control buyGreaterThan" placeholder="Price" id="priceToBuy"/>
                        <span class="input-group-addon alert-warning">CNY</span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="totalToBuy" class="col-sm-5 control-label">Total</label>
                    <label id="totalToBuy" class="col-sm-5  form-control-static text-danger">0</label>
                    <label class="col-sm-1  form-control-static text-info">CNY</label>
                </div>
                <div class="form-group">
                    <label for="feeToBuy" class="col-sm-5 control-label">Fee</label>
                    <label id="feeToBuy" class="col-sm-5  form-control-static text-danger">0</label>
                    <label class="col-sm-1  form-control-static text-info">CNY</label>
                </div>
                <spring:hasBindErrors name="buyOrder">
                    <div class="alert alert-warning alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        <form:errors path="*" element="div"/>
                    </div>
                </spring:hasBindErrors>

<div id="buyModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="buyPassword" class="col-sm-4 control-label">Exchange password</label>
                                        <div class="input-group col-sm-6">
                                            <input id="buyPassword" name="tradingPassword" placeholder="Your exchange password" class="form-control" type="password" value=""/>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-5 col-md-offset-5">
                                            <div class="btn-group">
                                                <button type="submit" class="btn btn-primary btn-sm">Confirm</button>
                                                <button type="button" class="btn btn-warning btn-sm" data-dismiss="modal">
                                                    Cancel
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                        <!-- /.modal-dialog -->
                    </div>

                    <button type="submit" id="buySubmit" data-toggle="modal" data-target="#buyModal"
                            class="btn btn-success col-lg-offset-5 col-lg-3">Buy</button>
                </form:form>
            </div>
        </div>
    </div> <%--end of buy panel--%>
</div>

<div class="row">
    <div class="col-md-6">
        <div class="panel panel-sell">
            <div class="panel-heading text-center">
                <h3 class="panel-title">Selling Orders</h3>
            </div>
            <div class="panel-body">
                <table class="table table-condensed table-hover">
                    <thead>
                    <tr>
                        <th class="text-center">Price</th>
                        <th class="text-center">Amount</th>
                        <th class="text-center">CNY</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sellOrders}" var="order">
                        <tr>
                            <td class="text-center">
                                <fmt:formatNumber value="${order.price.amount}" type="number" pattern="#.##"/>
                            </td>
                            <td class="text-center">
                                <fmt:formatNumber value="${order.sumUpAmountPerPrice.amount}" type="number" pattern="#.####"/>
                            </td>
                            <td class="text-center">
                                <fmt:formatNumber value="${order.total.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="panel panel-buy">
            <div class="panel-heading text-center">
                <h3 class="panel-title">Buying Orders</h3>
            </div>
            <div class="panel-body">
                <table class="table table-condensed table-hover">
                    <thead>
                    <tr>
                        <th class="text-center">Price</th>
                        <th class="text-center">Amount</th>
                        <th class="text-center">CNY</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${buyOrders}" var="order">
                        <tr>
                            <td class="text-center">
                                <fmt:formatNumber value="${order.price.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                            </td>
                            <td class="text-center">
                                <fmt:formatNumber value="${order.sumUpAmountPerPrice.amount}" type="number" pattern="#.####"/>
                            </td>
                            <td class="text-center">
                                <fmt:formatNumber value="${order.total.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <div class="panel panel-trading">
        <div class="panel-heading text-center">
            <h3 class="panel-title">Your current active orders</h3>
        </div>
        <div class="panel-body">
            <table class="table table-condensed table-hover">
                <thead>
                <tr>
                    <th class="text-center">Buy/Sell</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Amount</th>
                    <th class="text-center">CNY</th>
                    <th class="text-center">Date</th>
                    <th class="text-center">Action</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${activeOrders}" var="trade">
                    <c:choose>
                        <c:when test="${trade.type == 'SELL'}">
                            <tr class="danger">
                        </c:when>
                        <c:otherwise>
                            <tr class="success">
                        </c:otherwise>
                    </c:choose>

                    <td class="text-center"><c:out value="${trade.type}"/></td>
                    <td class="text-center">
                        <fmt:formatNumber value="${trade.itemPrice.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                    </td>
                    <td class="text-center">
                        <fmt:formatNumber value="${trade.itemRemaining.amount}" type="number" pattern="#.####"/>
                    </td>
                    <td class="text-center">
                        <fmt:formatNumber value="${trade.itemRemaining.amount * trade.itemPrice.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                    </td>
                    <td class="text-center">
                        <fmt:formatDate pattern="MM-dd HH:mm:ss" value="${trade.placedDate}" />
                    </td>
                    <td class="text-center"><a href="#">Undo</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</sec:authorize>



<div class="panel panel-history">
    <div class="panel-heading text-center">
        <h3 class="panel-title">Trade history</h3>
    </div>

    <div class="panel-body">
        <table class="table table-condensed table-hover">
            <thead>
            <tr>
                <th class="text-center">Date</th>
                <th class="text-center">Buy/Sell</th>
                <th class="text-center">Price</th>
                <th class="text-center">Amount</th>
                <th class="text-center">CNY</th>
            </tr>
            </thead>
            <tbody>


            <c:forEach items="${executedTrades}" var="trade">
                <c:choose>
                    <c:when test="${trade.tradeType == 'BUY'}">
                        <tr class="success">
                    </c:when>
                    <c:otherwise>
                        <tr class="danger">
                    </c:otherwise>
                </c:choose>

                    <td class="text-center">
                        <fmt:formatDate pattern="MM-dd HH:mm:ss" value="${trade.tradeTime}" />
                    </td>
                    <td class="text-center"><c:out value="${trade.tradeType}"/></td>
                    <td class="text-center">
                        <fmt:formatNumber value="${trade.tradedPrice.amount}" type="number" pattern="#.####"/>
                    </td>
                    <td class="text-center">
                        <fmt:formatNumber value="${trade.tradedAmount.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                    </td>
                    <td class="text-center">
                        <fmt:formatNumber value="${trade.tradedAmount.amount * trade.tradedPrice.amount}" type="number" pattern="#.##" currencyCode="CNY"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>


<div class="col-md-3">
    <div class="well-new">
        <h4 class="text-center text-info">Our advantages</h4>
        <ul class="list-group">
            <li class="list-group-item palette-pumpkin text-inverse"> <span class="fui-lock"></span> Safe</li>
            <li class="list-group-item palette-carrot text-inverse"> <span class="glyphicon glyphicon-briefcase"></span> Professional</li>
            <li class="list-group-item palette-alizarin text-inverse"> <span class="glyphicon glyphicon-flash"></span> Quick</li>
            <li class="list-group-item palette-pomegranate text-inverse"> <span class="fui-heart"></span> Preferential</li>
            <li class="list-group-item palette-carrot text-inverse"><span class="glyphicon glyphicon-fire"></span> Hot</li>
        </ul>

        <hr/>

        <h4 class="text-center text-info">Contact</h4>
        <ul class="list-group">
            <li class="list-group-item palette-peter-river text-inverse"><span class="fui-mail"></span> liougehooa@163.com</li>
            <li class="list-group-item palette-green-sea text-inverse"><span class="glyphicon glyphicon-earphone"></span> 021 3456789</li>
            <li class="list-group-item palette-amethyst text-inverse"> <span class="fui-user"> QQ<sup>1</sup></span> 232845696</li>
            <li class="list-group-item palette-wisteria text-inverse"> <span class="fui-user"> QQ<sup>2</sup></span> 232845696</li>
            <li class="list-group-item palette-turquoise text-inverse"> <span class="fui-user"> Tech<sup></sup></span> 232845696</li>
        </ul>
    </div>
</div>
</div>
</div>

</body>
<c:set var="lang" value="<%= LocaleContextHolder.getLocale().getLanguage()%>"/>
<content tag="additionalJs">
    <script type="text/javascript" src="${ctx}/js/localization/messages_${lang}.js"></script>
    <script src="${ctx}/js/highstock.js"></script>
    <script src="${ctx}/js/exporting.js"></script>
    <script src="${ctx}/js/icoin-charts.js"></script>
    <script src="${ctx}/js/jquery.number.js"></script>
    <script src="${ctx}/js/trading.js"></script>
</content>
</html>
