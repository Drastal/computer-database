<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="computer.database.domain.*"%>
<jsp:include page="include/header.jsp" />

<section id="main">
	<h1 id="homeTitle">${requestScope.sizeList}Computersfound</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit"
				id="searchsubmit" value="Filter by name" class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer.aspx">Add
			Computer</a>
	</div>

	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- Variable declarations for passing labels as parameters -->
				<!-- Table header for Computer Name -->
				<th>Computer Name</th>
				<th>Introduced Date</th>
				<!-- Table header for Discontinued Date -->
				<th>Discontinued Date</th>
				<!-- Table header for Company -->
				<th>Company</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.machines}" var="machine">
				<tr>
				<!-- Vérif si ok sans onclick -->
					<td><a href="<c:url value="editComputer.aspx?id=${machine.id}"/>" onclick="">${machine.name}</a></td>
					<td>${machine.introducedAsString}</td>
					<td>${machine.discontinuedAsString}</td>
					<td>${machine.company.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<%-- Boutons pour la pagination --%>
    <!-- <span class="pagination" >
        <ul> -->
            <%-- Bouton Previous --%>
            <%-- <c:choose>
                <c:when test="${requestScope.pageNumber <= 1}">
                    <li class="disabled"><a href="">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.pageNumber - 1}">Previous</a></li>
                </c:otherwise>
            </c:choose>

            <c:if test="${requestScope.pageNumber != 1
            && requestScope.pageNumber - 1 > 1
            && requestScope.pageNumber - 2 > 1}">
                <li><a href="dashboard.aspx?q=${requestScope.search}&page=1">1</a></li>
            </c:if>
            <c:if test="${requestScope.pageNumber != 1
            && requestScope.pageNumber - 1 > 1
            && requestScope.pageNumber - 2 > 1
            && requestScope.pageNumber - 3 > 1}">
                <li><a class="disabled" href="">...</a></li>
            </c:if>
            <c:if test="${requestScope.pageNumber - 2 > 0}">
                <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.pageNumber - 2}">${requestScope.pageNumber - 2}</a></li>
            </c:if>
            <c:if test="${requestScope.pageIndex - 1 > 0}">
                <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.pageNumber - 1}">${requestScope.pageNumber - 1}</a></li>
            </c:if>

            <li class="active"><a href="#">${requestScope.pageNumber}</a></li>

            <c:if test="${requestScope.pageNumber + 1 < requestScope.totalPage}">
                <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.pageNumber + 1}">${requestScope.pageNumber + 1}</a></li>
            </c:if>
            <c:if test="${requestScope.pageNumber + 2 < requestScope.pageCount}">
                <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.pageNumber + 2}">${requestScope.pageNumber + 2}</a></li>
            </c:if>
            <c:if test="${requestScope.pageNumber != requestScope.totalPage
            && requestScope.pageNumber + 1 < requestScope.totalPage
            && requestScope.pageNumber + 2 < requestScope.totalPage
            && requestScope.pageNumber + 3 < requestScope.totalPage}">
                <li><a class="disabled" href="">...</a></li>
            </c:if>
            <c:if test="${requestScope.pageNumber != requestScope.totalPage}">
                <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.totalPage}">${requestScope.totalPage}</a></li>
            </c:if> --%>

            <%-- Bouton Next --%>
            <%-- <c:choose>
                <c:when test="${requestScope.pageNumber >= requestScope.totalPage}">
                    <li class="disabled"><a href="">Next</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="dashboard.aspx?q=${requestScope.search}&page=${requestScope.pageNumber + 1}">Next</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </span> --%>
	
	<%--For displaying Previous link except for the 1st page --%>
    <%-- <c:if test="${pageNumber > 1}">
        <td><a href="dashboard.aspx?page=${pageNumber - 1}">Previous</a></td>
    </c:if> --%>
 
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <%-- <table>
        <tr>
            <c:forEach begin="1" end="${totalPage}" var="i">
                <c:choose>
                    <c:when test="${pageNumber eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="dashboard.aspx?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table> --%>
 
    <%--For displaying Next link --%>
    <%-- <c:if test="${pageNumber lt totalPage}">
        <td><a href="dashboard.aspx?page=${pageNumber + 1}">Next</a></td>
    </c:if> --%>
    
	<form action="" method="GET">
		<span class="input" style="float: right;">
			<label for="resultNb">Results per page:&nbsp;</label> <select 
				name="resultsNb" style="width: 60px">
				<c:choose>
					<c:when test="${requestScope.resultsQuantity==50}">
						<option value="50" selected="selected">50</option>
						<option value="100">100</option>
					</c:when>
					<c:otherwise>
						<option value="50">50</option>
						<option value="100" selected="selected">100</option>
					</c:otherwise>
				</c:choose>
			</select> <input type="submit" value="Validate" class="btn primary">
		</span>

	</form>
</section>

<jsp:include page="include/footer.jsp" />
