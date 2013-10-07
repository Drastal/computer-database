Kim Guilhem Florian Leroy

------------------------------------------------------------------
CORRECTION+CODE REVIEW:
------------------------------------------------------------------

-------------------------
Expérience utilisateur:
-------------------------

Je lance: Ca marche, les images A-Z et Z-A sont assez parlantes, pas de problemes.
La recherche fonctionne aussi.
Attention, quand je change l'ordre et que j'etais sur une page != 1, je reviens vers la page 1 automatiquement.
Results per page: bien

Add Computer: il n'est pas tres solide: si je rentre une date fausse (exemple 3019-01-01) je n'ai pas d'exception meme si mon ordi ne sera pas ajoute en bdd. Autre exemple: quand je rentre un mauvais champ (ex date) celui ci sera efface apres apparition du message d'erreur... j'aurais aime le conserver pour pouvoir juste supprimer la lettre qui n'allait pas par exemple.

Edit et delete, meme remarques, avec une autre pour le delete: si j'ai clique par erreur, j'aurai un ordinateur qui est quand meme supprime! c'est une bonne pratique de demander une confirmation avant de supprimer l'ordinateur.

-------------------------
Le code:
-------------------------
-Commentaires: ok, aurait pu etre plus explicite en utilisant les annotations @return @param et davantage de javadoc en general. Sinon, les commentaires in code sont satisfaisants
	
-Protection des jsp: bien.
	
-Utilisation des builder: oui

-Utilisation des enums pour les singletons: yes!

-Controller: Vous avez 5 setAttribute envoyes a votre page. Ce serait pertient de faire un wrapper qui contiendrait tout (PageComputer par exemple). L'utilisation de Calendar: bravo! c'est pas mal. Par contre vous avez vu que le cas de donner un an 3000 ne fonctionne pas malgre le fait qu'il ne renvoie pas d'erreur...

-Services: RAS

-Dao: RAS

-Domain: RAS

-JSP: Pas mal, votre code est bien organise. Vous auriez pu utiliser des taglib pour la pagination et pour l'affichage des ordinateurs, ca vous permet de faire un include pre compile avec seulement quelques arguments.


-------------------------
Bilan: 
-------------------------
Votre projet est pas mal, ca marche sans fioritures. Bon point pour avoir utilise l'API Calendar.
Bonne continuation, essayez d'approfondir ca si ovus avez le temps, notamment en revoyant votre logique de gestion de parsing de date, d'affichage des erreurs, et de securite en general.
