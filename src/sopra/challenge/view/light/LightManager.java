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
package sopra.challenge.view.light;

public interface LightManager {
	/**
	 * Augumente la luminausité
	 */
	public void decreaseLight();
	/**
	 * Diminue la luminausité
	 */
	public void increaseLight();
	
	/**
	 * Test si l'on est la nuit.
	 * @return true si c'est la nuit, false si c'est le jour.
	 */
	public boolean isNight();
	
	/**
	 * Passe à la nuit.
	 */
	public void night();
	
	/**
	 * Passe au jour.
	 */
	public void day();
}
