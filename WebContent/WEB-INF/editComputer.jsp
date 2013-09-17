<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="computer.database.domain.*"%>
<jsp:include page="include/header.jsp" />
<jsp:include page="include/inputVerif.jsp" />

	<!-- Page pour l'edition des donnees sur un ordinateur
	Verification des champs modifies en direct avec l'include inputVerif.jsp -->
	
<section id="main">

	<h1>Edit Computer</h1>
	
	<form action="editComputer.aspx" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input" onblur="verifName(this)">
					<input type="text" name="name" value="${machine.name}" onblur="verifName(this)"/>
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" value="${machine.introducedAsString}"  pattern="YYYY-MM-dd" onblur="verifDate(this)"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" value="${machine.discontinuedAsString}" pattern="YYYY-MM-dd" onblur="verifDate(this)"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">---No company selected---</option>
						<c:forEach items="${requestScope.companies}" var="company">
							<c:choose>
								<!-- Preselection de la compagnie de l'ordinateur deja renseignee -->
								<c:when test="${company.id==machine.company.id}">
									<option value="${company.id}" selected>${company.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${company.id}">${company.name}</option>
								</c:otherwise>
							</c:choose>						
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		
		<!-- Actions de la page
		Editer, Supprimer la machine de la BDD ou revenir a la liste des ordinateurs -->
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary" name="action">
			<input type="submit" value="Delete" class="btn danger" name="action">
			<a href="computerList.aspx" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />