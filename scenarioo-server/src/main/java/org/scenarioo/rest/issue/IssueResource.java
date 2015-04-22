/* scenarioo-server
 * Copyright (C) 2015, scenarioo.org Development Team
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

package org.scenarioo.rest.issue;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.scenarioo.model.design.entities.Issue;

@Path("/rest/branch/{branchName}/issues")
public class IssueResource {
	/*
	 * @GET
	 *
	 * @Produces({ "application/xml", "application/json" })
	 * public IssueProposals readIssueProposals(@PathParam("branchName") final String branchName,
	 * @PathParam("issueName") final String issueName) {
	 *
	 * BuildIdentifier buildIdentifier = ScenarioDocuBuildsManager.INSTANCE.resolveBranchAndBuildAliases(branchName,
	 * buildName);
	 * // TODO: Create a way to read all props of an issue, knowing only the branch, not the build
	 *
	 * return aggregatedDataReader.loadIssueProposals(buildIdentifier, issueName);
	 * }
	 */

	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Issue> loadIssues(@PathParam("branchName") final String branchName) {
		List<Issue> result = new LinkedList<Issue>();
		result.add(new Issue("TestIssue", "This is a Test!"));
		return result;
	}
}