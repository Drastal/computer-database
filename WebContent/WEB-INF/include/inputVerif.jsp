<script type="text/javascript">
	/* Fonctions de verification des champs du formulaire */
	function underline(field, error) {
		if (error) {
			field.style.backgroundColor = "#fba";
		} else
			field.style.backgroundColor = "";
	}
	function verifName(field) {
		if (field.value == "") {
			alert("Computer name required.");
			underline(field, true);
			/* return false; */
		} else {
			underline(field, false);
			/* return true; */
		}
	}

	function verifDate(field) {
		if (field.value != "") {
			var date_temp = field.value.split('-', 3);
			var dateVerif = new Date(date_temp[0], date_temp[1]-1, date_temp[2]);
			if(!(dateVerif.getFullYear()==date_temp[0] && (dateVerif.getMonth()+1)==parseInt(date_temp[1]) && dateVerif.getDate()==parseInt(date_temp[2]))){
			    alert('Date non valable !');
			    field.value = "";
			}
		}
	}
</script>