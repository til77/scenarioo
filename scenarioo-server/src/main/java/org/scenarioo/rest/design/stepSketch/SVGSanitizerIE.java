/* scenarioo-server
 * Copyright (C) 2014, scenarioo.org Development Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scenarioo.rest.design.stepSketch;

import org.apache.log4j.Logger;

public class SVGSanitizerIE {
	
	private static final Logger LOGGER = Logger.getLogger(StepSketchResource.class);
	
	/**
	 * Sanitize the SVG, removing browser-specific crap.
	 * @param svg The SVG to be sanitized as a string.
	 * @return The sanitized SVG string.
	 */
	public static String sanitize(String svg){
		svg = removeXMLNS(svg);
		svg = removeNS1(svg);
		return svg;
	}
	
	/**
	 * Removes the extraneous XMLNS declarations that IE introduces.
	 */
	private static String removeXMLNS(String svg){
		String xmlns = "xmlns=\"http://www.w3.org/2000/svg\"";
		int count = 0, lastIndex = 0;
		while ((lastIndex = svg.indexOf(xmlns, lastIndex)) != -1) {
	        count++;
	        lastIndex += xmlns.length() - 1;
	    }
		if (count == 2){
			svg = svg.replaceFirst("xmlns=.*?\".*?\" ", "");
		}
		if (count > 2 || count < 1){
			LOGGER.error("Unexpected number of xmlns declarations");
		}
		return svg;
	}
	
	/**
	 * Removes the NS1 declarations that IE introduces.
	 */
	private static String removeNS1(String svg){
		svg = svg.replaceAll("[^ ]*NS1[^ ]* ", "");
		return svg;
	}
}