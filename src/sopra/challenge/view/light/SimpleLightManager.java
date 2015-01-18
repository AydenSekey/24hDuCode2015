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

import com.ardorcraft.world.BlockWorld;

public class SimpleLightManager  implements LightManager {
	private final float limiteDayNight;
	private final BlockWorld world;
	private final float lightTauxChangement;
	
	/**
	 * Crée un manager de lumière simple pour un monde
	 * @param blocWorld le monde pour lequel géré la lumière
	 */
	public SimpleLightManager(BlockWorld blocWorld) {
		world = blocWorld;
		lightTauxChangement = 0.001f;
		limiteDayNight = 0.2f;
	}

	@Override
	public void decreaseLight() {
		float light = world.getGlobalLight() - lightTauxChangement;
		if(light < 0) {
			light = 0.0f;
		}
		world.setGlobalLight(light);
		
	}

	@Override
	public void increaseLight() {
		float light = world.getGlobalLight() + lightTauxChangement;
		if(light > 1) {
			light = 1.0f;
		}
		world.setGlobalLight(light);
	}

	@Override
	public boolean isNight() {
		return world.getGlobalLight() < limiteDayNight;
	}

	@Override
	public void night() {
		world.setGlobalLight(0f);
		
	}

	@Override
	public void day() {
		world.setGlobalLight(1f);
	}

	@Override
	public float tauxAvancementDayOrNight() {
		if(isNight()) {
			return (limiteDayNight - world.getGlobalLight()) / limiteDayNight * 100;
		} else {
			return (world.getGlobalLight() - limiteDayNight) / (1f - limiteDayNight) * 100;
		}
	}
	
	
}
