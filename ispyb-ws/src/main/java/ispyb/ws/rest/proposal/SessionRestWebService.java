package ispyb.ws.rest.proposal;

import ispyb.server.common.services.ws.rest.session.SessionService;
import ispyb.server.common.util.ejb.Ejb3ServiceLocator;
import ispyb.ws.rest.RestWebService;

import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

@Path("/")
public class SessionRestWebService extends RestWebService {
	private final static Logger logger = Logger.getLogger(SessionRestWebService.class);

	@RolesAllowed({ "User", "Manager", "Localcontact" })
	@GET
	@GZIP
	@Path("{token}/proposal/{proposal}/session/list")
	@Produces({ "application/json" })
	public Response getSessionByProposalId(@PathParam("token") String token, @PathParam("proposal") String proposal) throws Exception {
		String methodName = "getSessionList";
		long id = this.logInit(methodName, logger, token, proposal);
		try {
			List<Map<String, Object>> result = getSessionService().getSessionViewByProposalId(this.getProposalId(proposal));
			this.logFinish(methodName, id, logger);
			return sendResponse(result);
		} catch (Exception e) {
			return this.logError(methodName, e, id, logger);
		}
	}

	@RolesAllowed({ "User", "Manager", "Localcontact" })
	@GET
	@GZIP
	@Path("{token}/proposal/{proposal}/session/sessionId/{sessionId}/list")
	@Produces({ "application/json" })
	public Response getSessionById(@PathParam("token") String token, @PathParam("proposal") String proposal,
			@PathParam("sessionId") int sessionId) throws Exception {
		String methodName = "getSessionById";
		long id = this.logInit(methodName, logger, token, proposal, sessionId);
		try {
			List<Map<String, Object>> result = getSessionService().getSessionViewBySessionId(this.getProposalId(proposal), sessionId);
			this.logFinish(methodName, id, logger);
			return sendResponse(result);
		} catch (Exception e) {
			return this.logError(methodName, e, id, logger);
		}
	}
	
	/**
	 * Returns the session list that will take place between start and end date
	 * 
	 * @param token
	 * @param proposal name of the proposal
	 * @param start format is YYYYMMDD
	 * @param end format is YYYYMMDD
	 * @return
	 * @throws Exception
	 */
	@RolesAllowed({ "User", "Manager", "Localcontact" })
	@GET
	@GZIP
	@Path("{token}/proposal/{proposal}/session/date/{startdate}/{enddate}/list")
	@Produces({ "application/json" })
	public Response getSessionByDate(@PathParam("token") String token, @PathParam("proposal") String proposal,
			@PathParam("startdate") String start,
			@PathParam("enddate") String end) throws Exception {
		String methodName = "getSessionByDate";
		long id = this.logInit(methodName, logger, token, proposal, start, end);
		try {
			List<Map<String, Object>> result = getSessionService().getSessionViewByProposalAndDates(this.getProposalId(proposal), start, end);
			this.logFinish(methodName, id, logger);
			return sendResponse(result);
		} catch (Exception e) {
			return this.logError(methodName, e, id, logger);
		}
	}
	

	private SessionService getSessionService() throws NamingException {
		return (SessionService) Ejb3ServiceLocator.getInstance().getLocalService(SessionService.class);
	}

	@RolesAllowed({ "Manager", "Localcontact" })
	@GET
	@GZIP
	@Path("{token}/proposal/session/date/{startdate}/{enddate}/list")
	@Produces({ "application/json" })
	public Response getSessionsByDate(
			@PathParam("token") String token, 
			@PathParam("startdate") String start,
			@PathParam("enddate") String end) throws Exception {
		String methodName = "getSessionsByDate";
		long id = this.logInit(methodName, logger, token, start, end);
		try {
			List<Map<String, Object>> result = getSessionService().getSessionViewByDates(start, end);
			this.logFinish(methodName, id, logger);
			return sendResponse(result);
		} catch (Exception e) {
			return this.logError(methodName, e, id, logger);
		}
	}
	
	
	@RolesAllowed({ "Manager", "Localcontact" })
	@GET
	@GZIP
	@Path("{token}/proposal/session/beamlineoperator/{beamlineOperator}/list")
	@Produces({ "application/json" })
	public Response getSessionsByBeamlineOperator(
			@PathParam("token") String token, 
			@PathParam("beamlineOperator") String beamlineOperator) throws Exception {
		String methodName = "getSessionsByBeamlineOperator";
		long id = this.logInit(methodName, logger, token, beamlineOperator);
		try {
			List<Map<String, Object>> result = getSessionService().getSessionViewByBeamlineOperator(beamlineOperator);
			this.logFinish(methodName, id, logger);
			return sendResponse(result);
		} catch (Exception e) {
			return this.logError(methodName, e, id, logger);
		}
	}
	
}
