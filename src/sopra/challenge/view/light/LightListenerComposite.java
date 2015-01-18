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

import java.util.ArrayList;
import java.util.List;

public class LightListenerComposite implements LightListener {
	private final List<LightListener> listeners;

	public LightListenerComposite() {
		listeners = new ArrayList<LightListener>();
	}
	
	public void addListener(LightListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(LightListener listener) {
		listeners.remove(listener);
	}
	
	public void clear() {
		listeners.clear();
	}
	
	@Override
	public void dayStart() {
		listeners.forEach(listener -> listener.dayStart());
	}

	@Override
	public void nightStart() {
		listeners.forEach(listener -> listener.nightStart());
	}

}
