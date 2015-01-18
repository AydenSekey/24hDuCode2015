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

import java.util.Date;

public class LightController implements LightTimeController {
	private static final long LIGHT_REFRESH_TIME = 10;// temps entre les refreshs de la lumière (0.01s)
	private final long dureeDemiJournee;
	private final LightManager lightManager;
	private long lastDemiJourneeTime;
	private long lastTime;
	// boolean pour savoir si le jour se leve ou baisse.
	private boolean leveeJour;

	public LightController(LightManager lightManager) {
		this.lightManager = lightManager;
		leveeJour = false;
		dureeDemiJournee = 20000;// 20 secondes
		lastDemiJourneeTime = new Date().getTime();
		lastTime = lastDemiJourneeTime;
	}
	
	@Override
	public void timeRun() {
		long time = new Date().getTime();
		if(time - lastDemiJourneeTime >= dureeDemiJournee) {
			// bascule en milieu de journée entre levée et descente du jour.
			leveeJour = !leveeJour;
			lastDemiJourneeTime = time;
		}
		if(time - lastTime >= LIGHT_REFRESH_TIME) {
			if(leveeJour) {
				lightManager.increaseLight();
			} else {
				lightManager.decreaseLight();
			}
			lastTime = time;
		}
	}
}