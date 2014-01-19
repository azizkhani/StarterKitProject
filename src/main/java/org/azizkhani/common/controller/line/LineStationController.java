package org.azizkhani.common.controller.line;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.azizkhani.core.QueryResult;
import org.azizkhani.model.line.Line;
import org.azizkhani.model.line.LineStation;
import org.azizkhani.model.terminal.Terminal;
import org.azizkhani.service.line.ILineService;
import org.azizkhani.service.line.ILineStationService;
import org.azizkhani.service.terminal.ITerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/baseinfo/lineStation")
public class LineStationController {

	@Autowired(required = true)
	private ILineStationService lineStationService;

	@RequestMapping("/list/{lineId}")
	@ResponseBody
	public List<LineStation> list(@PathVariable int lineId) {

		return lineStationService.getAll(lineId);

	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleUncaughtException(Exception ex, WebRequest request,
			HttpServletResponse response) throws IOException {
		if (isAjaxRequest(request)) {
			response.setHeader("Content-Type", "application/json");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "Unknown error occurred: " + ex.getMessage();
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					ex.getMessage());
			return null;
		}
	}

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith)
				: false;
	}

	@RequestMapping("/load/{Id}")
	@ResponseBody
	public LineStation load(@PathVariable int Id) {
		return lineStationService.loadByEntityId(Id);
	}

	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean delete(@PathVariable int Id) {
		lineStationService.deleteByEntityId(Id);
		return true;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean save(@RequestBody LineStation entity) {
		lineStationService.save(entity);
		return true;
	}

}
