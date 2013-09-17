<script type="text/javascript">
	/* Fonctions de verification des champs du formulaire */

	function underline(field, error) {
		//Change le fond d'un champ de saisie en rouge si erreur de saisie
		if (error) {
			field.style.backgroundColor = "#fba";
		} else
			field.style.backgroundColor = "";
	}
	
	function verifName(field) {
		//Verifie si le champ de saisie d'un nom pour l'ordinateur est rempli ou non
		if (field.value == "") {
			alert("Computer name required.");
			underline(field, true);
		} else {
			underline(field, false);
		}
	}

	function verifDate(field) {
		//Verifie si la date entree existe ou non
		if (field.value != "") {
			var date_temp = field.value.split('-', 3);
			var dateVerif = new Date(date_temp[0], date_temp[1] - 1, date_temp[2]);
			if (!(dateVerif.getFullYear() == date_temp[0]
					&& (dateVerif.getMonth() + 1) == parseInt(date_temp[1])
					&& dateVerif.getDate() == parseInt(date_temp[2]))) {
				alert('Date non valable !');
				field.value = "";
			}
		}
	}
</script>