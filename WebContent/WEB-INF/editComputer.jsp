<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="computer.database.domain.*"%>
<jsp:include page="include/header.jsp" />
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
							<option value="${company.id}">${company.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Edit" class="btn primary" name="Action">
			<input type="submit" value="Delete" class="btn danger" name="Action">
			<a href="computerList.aspx" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="include/footer.jsp" />