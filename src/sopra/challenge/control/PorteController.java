/*
Copyright Sopra Steria (2015)
contributor(s) : see contributors.txt

adrien.duroy@soprasteria.com

This file is part of SopraMaze.

<programm name> is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SopraMaze is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SopraMaze.  If not, see <http://www.gnu.org/licenses/>.
*/
package sopra.challenge.control;

import java.util.HashSet;
import java.util.Set;

import sopra.challenge.labyrinthe.Porte;
import com.ardorcraft.world.BlockWorld;

/**
 * Contrôleur gérant les portes d'un monde.
 */
public class PorteController {
	private final BlockWorld world;
	private final Set<Porte> portes;

	/**
	 * Crée un controleur de porte d'un monde.
	 * @param blockWorld le monde
	 */
	public PorteController(BlockWorld blockWorld) {
		world = blockWorld;
		portes = new HashSet<Porte>();
	}
	
	/**
	 * Indique au contrôleur une nouvelle porte à contrôler.
	 * @param porte la nouvelle porte à gérer.
	 */
	public void enregistrePorte(Porte porte) {
		portes.add(porte);
	}
	
	/**
	 * Retire une porte à gérer du contrôleur.
	 * @param porte la porte à retirer.
	 */
	public void desenregistrePorte(Porte porte) {
		portes.remove(porte);
	}
	
	/**
	 * Ferme toutes les portes enregistrées
	 */
	public void closePortes() {
		portes.forEach(porte -> porte.close());
		updatePorteViews();
	}
	
	/**
	 * Ouvre toutes les portes enregistrées.
	 */
	public void openPortes() {
		portes.forEach(porte -> porte.open());
		updatePorteViews();
	}
	
	private void updatePorteViews() {
		// TODO modifier les murs "porte" du monde
		// Utiliser world.setBlock(x,y,z, TypeLayer.VOID);
	}
}
