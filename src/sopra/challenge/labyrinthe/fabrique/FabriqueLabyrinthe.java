/*
Copyright Sopra Steria (2015)
contributor(s) : see contributors.txt

adrien.duroy@soprasteria.com

This file is part of SopraMaze.

SopraMaze is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

<programm name> is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SopraMaze.  If not, see <http://www.gnu.org/licenses/>.
*/
package sopra.challenge.labyrinthe.fabrique;

import sopra.challenge.labyrinthe.Labyrinthe;
import sopra.challenge.labyrinthe.generateurLabyrinthe.GenerateurLabyrinthe2D;

/**
 * Fabrique pour les différents labyrinthes.
 */
public class FabriqueLabyrinthe {

	/**
	 * Crée un labyrinthe défini de manière fixe.
	 * @return le labyrinthe généré. (instance de singleton)
	 */
	public static Labyrinthe labyrintheFixe() {
		GenerateurLabyrinthe2D generateur= new GenerateurLabyrinthe2D();
		generateur.initialiserLabyrintheTest(20,20);
		generateur.definirMursIndestructibles();
		generateur.placerDepart(10, 10);
		generateur.placerArriver(19,18);
		/*Création du chemin*/
		generateur.rendreLibre(9,10);
		generateur.rendreLibre(8,10);
		generateur.rendreLibre(7,10);
		generateur.rendreLibre(6,10);
		generateur.rendreLibre(5,10);
		generateur.rendreLibre(4,10);
		generateur.rendreLibre(3,10);
		generateur.rendreLibre(2,10);
		generateur.rendreLibre(1,10);
		generateur.rendreLibre(1,9);
		generateur.rendreLibre(1,8);
		generateur.rendreLibre(1,7);
		generateur.rendreLibre(1,6);
		generateur.rendreLibre(1,5);
		generateur.rendreLibre(1,4);
		generateur.rendreLibre(1,3);
		generateur.rendreLibre(1,2);
		generateur.rendreLibre(1,2);
		generateur.rendreLibre(1,2);
		generateur.rendreLibre(1,1);
		generateur.rendreLibre(2,1);
		generateur.rendreLibre(3,1);
		generateur.rendreLibre(4,1);
		generateur.rendreLibre(5,1);
		generateur.rendreLibre(6,1);
		generateur.rendreLibre(7,1);
		generateur.rendreLibre(8,1);
		generateur.rendreLibre(9,1);
		generateur.rendreLibre(10,1);
		generateur.rendreLibre(11,1);
		generateur.rendreLibre(12,1);
		generateur.rendreLibre(13,1);
		generateur.rendreLibre(14,1);
		generateur.rendreLibre(15,1);
		generateur.rendreLibre(16,1);
		generateur.rendreLibre(17,1);
		generateur.rendreLibre(18,1);
		generateur.rendreLibre(3,3);
		generateur.rendreLibre(3,4);
		generateur.rendreLibre(3,5);
		generateur.rendreLibre(3,6);
		generateur.rendreLibre(3,7);
		generateur.rendreLibre(3,8);
		generateur.rendreLibre(4,8);
		generateur.rendreLibre(5,8);
		generateur.rendreLibre(6,8);
		generateur.rendreLibre(7,8);
		generateur.rendreLibre(8,8);
		generateur.rendreLibre(9,8);
		generateur.rendreLibre(10,8);
		generateur.rendreLibre(18,2);
		generateur.rendreLibre(18,3);
		generateur.rendreLibre(18,4);
		generateur.rendreLibre(18,5);
		generateur.rendreLibre(18,6);
		generateur.rendreLibre(18,7);
		generateur.rendreLibre(18,8);
		generateur.rendreLibre(18,9);
		generateur.rendreLibre(18,10);
		generateur.rendreLibre(18,11);
		generateur.rendreLibre(18,12);
		generateur.rendreLibre(18,13);
		generateur.rendreLibre(18,14);
		generateur.rendreLibre(18,15);
		generateur.rendreLibre(18,16);
		generateur.rendreLibre(18,17);
		generateur.rendreLibre(18,18);
		
		generateur.rendreLibre(10,11);
		generateur.rendreLibre(10,12);
		generateur.rendreLibre(10,13);
		generateur.rendreLibre(10,14);
		generateur.rendreLibre(10,15);
		generateur.rendreLibre(10,16);
		generateur.rendreLibre(10,17);
		generateur.rendreLibre(10,18);

		generateur.rendreLibre(8,11);
		generateur.rendreLibre(8,12);
		generateur.rendreLibre(8,13);
		generateur.rendreLibre(8,14);
		generateur.rendreLibre(8,15);
		generateur.rendreLibre(8,16);
		generateur.rendreLibre(8,17);
		generateur.rendreLibre(8,18);
		
		generateur.rendreLibre(7,18);
		generateur.rendreLibre(6,18);
		generateur.rendreLibre(5,18);
		generateur.rendreLibre(4,18);
		generateur.rendreLibre(3,18);
		generateur.rendreLibre(2,18);
		generateur.rendreLibre(1,18);
		generateur.rendreLibre(1,17);
		generateur.rendreLibre(1,16);
		generateur.rendreLibre(1,15);
		generateur.rendreLibre(1,14);
		generateur.rendreLibre(1,13);
		generateur.rendreLibre(1,12);
		return Labyrinthe.getInstance();
	}
}
