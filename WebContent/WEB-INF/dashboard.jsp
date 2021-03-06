<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="computer.database.domain.*"%>
<jsp:include page="include/header.jsp" />

	<!-- Fichier JSP principal associe a la page comprenant la liste des ordinateurs
	Presente sous forme de tableau la liste des machines -->
	
<section id="main">
	<h1 id="homeTitle">${requestScope.sizeList} Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<!-- Champ de recherche par nom -->
			<input type="search" id="searchbox" name="search" value=""
				placeholder="Search name"> <input type="submit" id="searchsubmit"
				value="Search" class="btn primary" name="action">
		</form>
		<a class="btn success" id="add" href="addComputer.aspx">Add	Computer</a>
	</div>

	<!-- Tableau des ordinateurs -->
	<table class="computers zebra-striped">
		<thead>
			<tr>
				<!-- En-t�tes -->
				<th>Computer Name
					<a href="computerList.aspx?sort=byName&asc=true">
					<img src="img/sort_alpha_asc_mini.png" style="vertical-align: middle;"/></a>
					<a href="computerList.aspx?sort=byName&asc=false">
					<img src="img/sort_alpha_desc_mini.png" style="vertical-align: middle;"/></a></th>
				<th>Introduced Date
					<a href="computerList.aspx?sort=byIntroduced&asc=true">
					<img src="img/sort_num_asc_mini.png" style="vertical-align: middle;"/></a>
					<a href="computerList.aspx?sort=byIntroduced&asc=false">
					<img src="img/sort_num_desc_mini.png" style="vertical-align: middle;"/></a></th>
				<th>Discontinued Date
					<a href="computerList.aspx?sort=byDiscontinued&asc=true">
					<img src="img/sort_num_asc_mini.png" style="vertical-align: middle;"/></a>
					<a href="computerList.aspx?sort=byDiscontinued&asc=false">
					<img src="img/sort_num_desc_mini.png" style="vertical-align: middle;"/></a></th>
				<th>Company
					<a href="computerList.aspx?sort=byCompany&asc=true">
					<img src="img/sort_alpha_asc_mini.png" style="vertical-align: middle;"/></a>
					<a href="computerList.aspx?sort=byCompany&asc=false">
					<img src="img/sort_alpha_desc_mini.png" style="vertical-align: middle;"/></a></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.machines}" var="machine">
				<!-- Liste des ordinateurs -->
				<tr>
					<td style="width: 30%;"><a href="<c:url value="editComputer.aspx?id=${machine.id}"/>"
						onclick="">${machine.name}</a></td>
					<td style="width: 25%;">${machine.introducedAsString}</td>
					<td style="width: 25%;">${machine.discontinuedAsString}</td>
					<td style="width: 20%;">${machine.company.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<%--Pagination--%>
	<span class="pagination"> <!-- Recuperation du style present dans le bootstrap -->
		<ul><!-- Utilisation de ul pour le style associe dans le bootstrap -->
			
			<%--Lien precedent si necessaire --%>
			<c:if test="${pageNumber > 1}">
				<li><a href="computerList.aspx?page=${pageNumber - 1}">Previous</a></li>
			</c:if>
			
			<!-- Affichage des numeros de page et en fait des liens -->
			<c:forEach begin="1" end="${totalPage}" var="i">
				<c:choose>
					<c:when test="${pageNumber eq i}">
						<li><a href="" style="color: black; font-weight: bold;">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="computerList.aspx?page=${i}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<%--Lien suivant si necessaire --%>
			<c:if test="${pageNumber lt totalPage}">
				<li><a href="computerList.aspx?page=${pageNumber + 1}">Next</a></li>
			</c:if>
		</ul>
	</span>

	<!-- Nombre de resultats par page -->
	<form action="" method="GET">
		<span class="input" style="float: right;">
			<label for="resultNb">Results per page:&nbsp;</label>
			<select	name="resultsNb" style="width: 60px">
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
			</select>
			<input type="submit" value="Validate" class="btn primary" name="action">
		</span>
	</form>
</section>

<jsp:include page="include/footer.jsp" />
