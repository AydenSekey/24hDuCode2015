/*
Copyright Sopra Steria (2015)
contributor(s) : see contributors.txt

adrien.duroy@soprasteria.com

This file is part of SopraMaze.

<programm name> is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

<programm name> is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with <programm name>.  If not, see <http://www.gnu.org/licenses/>.
*/
package sopra.challenge.labyrinthe;

public class Porte extends Bloc {

	private boolean open;
	
	public Porte(int x, int y) {
		super(x, y);
		open = false;
	}

	@Override
	public boolean isMur() {
		return false;
	}

	@Override
	public boolean isMurIndestructible() {
		return false;
	}

	@Override
	public boolean isMurNormal() {
		return false;
	}

	@Override
	public boolean isZone() {
		return false;
	}

	@Override
	public boolean isZoneArrivee() {
		return false;
	}

	@Override
	public boolean isZoneDepart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPorte() {
		return true;
	}
	
	/**
	 * Ouvre la porte
	 */
	public void open() {
		open = true;
	}
	
	/**
	 * Ferme la porte
	 */
	public void close() {
		open = false;
	}

	/**
	 * Test si la porte est ouverte.
	 * @return true si la porte est ouverte, false si elle est fermée.
	 */
	public boolean isOpen() {
		return open;
	}
}
