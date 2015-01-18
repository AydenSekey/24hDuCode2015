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

import com.ardor3d.math.Vector3;
import com.ardor3d.renderer.queue.RenderBucketType;
import com.ardor3d.scenegraph.Node;
import com.ardor3d.ui.text.BasicText;

public class TextLightListener implements LightListener {
	private final Node textNode;
	
	public TextLightListener(Node nodeText) {
		textNode = nodeText;
	}
	
	@Override
	public void dayStart() {
		textNode.detachAllChildren();
		createText("JOUR", 10, 10);
	}

	@Override
	public void nightStart() {
		textNode.detachAllChildren();
		createText("NUIT", 10, 10);
	}
	
	private void createText(final String text, final int x, final int y) {
		final BasicText info = BasicText.createDefaultTextLabel("TextJour", text, 16);
		info.getSceneHints().setRenderBucketType(RenderBucketType.Ortho);
		info.setTranslation(new Vector3(x, y, 0));
		textNode.attachChild(info);
	}
}
