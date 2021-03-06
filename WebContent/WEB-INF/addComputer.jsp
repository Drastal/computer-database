<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="computer.database.domain.*"%>
<jsp:include page="include/header.jsp" />
<jsp:include page="include/inputVerif.jsp" /> <!-- Verification des champs de saisie -->

	<!-- JSP correspondant a la page d'ajout d'un ordinateur
	Verification de chaque champ en direct via inputVerif.jsp et son JavaScript -->
	
<section id="main">

	<h1>Add Computer</h1>
	
	<form action="addComputer.aspx" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input" onblur="verifName(this)">
					<input type="text" name="name" onblur="verifName(this)"/>
					<span class="help-inline">Required</span>
				</div>
			</div>
	
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" pattern="YYYY-MM-dd" onblur="verifDate(this)"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" pattern="YYYY-MM-dd" onblur="verifDate(this)"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">---No company selected---</option>
						<c:forEach items="${requestScope.companies}" var="company">
							<option value="${company.id}">${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Add" class="btn primary">
			or <a href="computerList.aspx" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />