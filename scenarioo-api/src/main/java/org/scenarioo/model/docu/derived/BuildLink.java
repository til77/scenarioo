/* scenarioo-api
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

package org.scenarioo.model.docu.derived;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.scenarioo.model.docu.entities.Build;

/**
 * Represents a build that might also be linked in file system under a different name (used for tagging builds). Usually
 * the linkName should be the same as the real build name, but could be different in case a build is linked under a
 * directory with a different name.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BuildLink implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String linkName;
	
	private Build build;
	
	public BuildLink() {
	}
	
	/**
	 * Create build that is linked in file system under given linkName (might be the same as name but does not have to
	 * be).
	 */
	public BuildLink(final Build build, final String linkName) {
		this.build = build;
		this.linkName = linkName;
	}
}
