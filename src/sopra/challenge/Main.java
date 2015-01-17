/*
Copyright SopraSteria (2015)
contributor(s) : see contributors.txt

[e-mail of the author(s)]

This file is part of <programm name>.

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
package sopra.challenge;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		System.out.println("Hello java 8 world !");

		List<String> strs = new LinkedList<String>(); strs.add("str1"); strs.add("str2");

		strs.forEach(t -> System.out.println("lambda : " + t));

		LocalDate date = LocalDate.now();

		System.out.println(date);

		}
}
