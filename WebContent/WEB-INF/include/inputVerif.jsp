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
		var date = field.value;
		if (date == "") {
			underline(field, false);
		} else {
			var dateCheck = field.value.match(/^\d{4}-\d{2}-\d{2}$/);
			if (!dateCheck) {
				underline(field, true);
				alert("Please enter a date (YYYY-MM-DD)");
			} else {
				var month = parseInt(date.substr(5, 2));
				var day = parseInt(date.substr(8, 2));
				if (month<1 || month>12) {
					alert("Invalid month: " + month);
					field.value = "";
				} else {
					if (month == 2) {
						if (day<0||day>29) {
							alert("Invalid day: " + day);
							field.value = "";
						}
					} else {
						if (month == 4 || month == 6 || month == 9
								|| month == 11) {
							if (day<0||day>30) {
								alert("Invalid day: " + day);
								field.value = "";
							}
						} else {
							if (day<0||day>31) {
								alert("Invalid day: " + day);
								field.value = "";
							}
						}
					}
				}
			}
		}
	}
</script>