package com.x.okr.assemble.control.jaxrs.statistic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonElement;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.okr.assemble.control.jaxrs.statistic.exception.ExceptionWrapInConvert;

public class ActionStReportContentListSubByFilter extends BaseAction {

	private static  Logger logger = LoggerFactory.getLogger(ActionStReportContentListSubByFilter.class);

	protected ActionResult<List<WoOkrStatisticReportContentCenter>> execute( HttpServletRequest request, EffectivePerson effectivePerson, String parentId, JsonElement jsonElement ) throws Exception {
		ActionResult<List<WoOkrStatisticReportContentCenter>> result = new ActionResult<>();
		Wi wrapIn = null;
		Boolean check = true;
		try {
			wrapIn = this.convertToWrapIn( jsonElement, Wi.class );
		} catch (Exception e ) {
			check = false;
			Exception exception = new ExceptionWrapInConvert( e, jsonElement );
			result.error( exception );
			logger.error( e, effectivePerson, request, null);
		}

		if( check ){
			try {
				result = new ActionFilterSubList().execute( request, effectivePerson, parentId, wrapIn );
			} catch (Exception e) {
				result = new ActionResult<>();
				result.error( e );
				logger.warn( "system excute ExcuteFilterSubList got an exception. " );
				logger.error( e );
			}
		}
		return result;
	}
	
	public static class Wi extends WrapInFilterOkrStatisticReportContent{
		
	}

}