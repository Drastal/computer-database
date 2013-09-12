<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="computer.database.domain.*"%>
<jsp:include page="include/header.jsp" />

<script type="text/javascript">

	/* Fonctions de verification des champs du formulaire */
	function underline(field, error) {
		if (error){
			field.style.backgroundColor = "#fba";
		}else
			field.style.backgroundColor = "";
	}
	function verifName(field) {
		if (field.value=="") {
			alert("Computer name required.");
			underline(field, true);
			/* return false; */
		} else {
			underline(field, false);
			/* return true; */
		}
	}
	
	function verifDate(field) {
		var date = field.value;
		if(date==""){
			underline(field, false);
		}else{
			var dateCheck = field.value.match(/^\d{4}-\d{2}-\d{2}$/);
			if(!dateCheck){
				underline(field, true);
				alert("Please enter a date (YYYY-MM-DD)");
			} else{
				var month = parseInt(date.substr(5,2));
				var day = parseInt(date.substr(8,2));
				if(month<1 || month>12){
					alert("Invalid month: " + month);
					field.value="";
				}else{
					if(month==2){
						if(day<0||day>29){
							alert("Invalid day: " + day);
							field.value="";
						}
					}else {
						if(month==4||month==6||month==9||month==11){
							if(day<0||day>30){
								alert("Invalid day: " + day);
								field.value="";
							}
						}else{
							if(day<0||day>31){
								alert("Invalid day: " + day);
								field.value="";
							}
						}
					}
				}
			}
		}
	}
</script>

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