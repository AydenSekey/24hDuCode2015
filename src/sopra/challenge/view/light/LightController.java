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
package sopra.challenge.view.light;

import java.util.Date;

import com.ardor3d.math.Vector3;
import com.ardor3d.renderer.queue.RenderBucketType;
import com.ardor3d.scenegraph.Node;
import com.ardor3d.ui.text.BasicText;

public class LightController implements LightTimeController {
	private static final long LIGHT_REFRESH_TIME = 10;// temps entre les refreshs de la lumière (0.01s)
	private final long dureeDemiJournee;
	private final LightManager lightManager;
	private long lastDemiJourneeTime;
	private long lastTime;
	// boolean pour savoir si le jour se leve ou baisse.
	private boolean leveeJour;
	private LightListener lightListener;
	private boolean oldIsNight;
	private Node textNode;

	public LightController(LightManager lightManager, LightListener lightListener, Node textNode) {
		this.lightManager = lightManager;
		leveeJour = false;
		dureeDemiJournee = 40000;// 20 secondes
		lastDemiJourneeTime = new Date().getTime();
		lastTime = lastDemiJourneeTime;
		oldIsNight = lightManager.isNight();
		this.lightListener = lightListener;
		this.textNode = textNode;
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
			// rafraichissement de la lumière
			final boolean isNight = lightManager.isNight();
			if(leveeJour) {
				// le jour est en train de se lever
				lightManager.increaseLight();
				if(lightListener != null && !isNight && oldIsNight) {
					// passage de la nuit au jour
					lightListener.dayStart();
				}
			} else {
				// la nuit est en train de tomber
				lightManager.decreaseLight();
				if(lightListener != null && isNight && !oldIsNight) {
					// passage du jour à la nuit
					lightListener.nightStart();
				}
			}
			lastTime = time;
			oldIsNight = isNight;
			updateText();
		}
	}
	
	private void updateText() {
		textNode.detachAllChildren();
		String text = String.format("%.0f%%", lightManager.tauxAvancementDayOrNight());
		final BasicText info = BasicText.createDefaultTextLabel("compteur", text, 16);
		info.getSceneHints().setRenderBucketType(RenderBucketType.Ortho);
		info.setTranslation(new Vector3(10, 25, 0));
		textNode.attachChild(info);
	}
}
