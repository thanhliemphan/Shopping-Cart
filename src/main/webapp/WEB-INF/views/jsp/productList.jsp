<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <html>
        <style>
          <%@include file="/resources/css/main.css" %>
        </style>
        <jsp:include page="header.jsp" />

        <body>
          <div class="contain pt-10">
            <!-- Input group -->
            <div class="mt-10">
              <p>
                <c:out value="${msg}" />
              </p>
              <c:if test="${empty productList}">
                <p>No data</p>
              </c:if>
              <c:if test="${not empty productList}">
                <table class="table">
                  <thead class="thead-dark">
                    <tr>
                      <th scope="col">Product Name</th>
                      <th scope="col">Product Description</th>
                      <th scope="col">Unit Price</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="product" items="${productList}" varStatus="e">
                      <tr>
                        <td>${product.productName}</td>
                        <td>${product.productDescription}</td>
                        <td>${product.unitPrice}</td>
                        <td><button class="btn btn-sm btn-primary"
                            onclick="location.href='add/${product.productId}'">Add To Cart</button></td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </c:if>

              <div>
              </div>
        </body>

        </html>