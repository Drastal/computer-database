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
					<td>${machine.name}</td>
					<c:choose>
						<c:when test="${machine.introduced!=NULL }">
							<td>${machine.introducedAsString}</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${machine.discontinued!=NULL }">
							<td>${machine.discontinuedAsString}</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${machine.company!=NULL }">
							<td>${machine.company.name}</td>
						</c:when>
						<c:otherwise>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="" method="GET">
		<div class="input" style="float: right;">
			<label for="resultNb">Results per page: </label> <select
				name="resultsNb">
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
		</div>

	</form>
</section>

<jsp:include page="include/footer.jsp" />
