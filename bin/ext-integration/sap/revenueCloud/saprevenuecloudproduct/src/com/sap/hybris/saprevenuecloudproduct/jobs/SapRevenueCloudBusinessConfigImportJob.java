/*
 * [y] hybris Platform
 *
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.hybris.saprevenuecloudproduct.jobs;


import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.outboundservices.facade.OutboundServiceFacade;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import static de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService.RESPONSE_MESSAGE;
import static de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService.getPropertyValue;
import static de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService.isSentSuccessfully;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sap.hybris.saprevenuecloudproduct.service.SapRevenueCloudProductService;

import static de.hybris.platform.sap.sapcpiadapter.service.SapCpiOutboundService.*;

/**
 *
 */
public class SapRevenueCloudBusinessConfigImportJob extends AbstractJobPerformable<CronJobModel> {

	private final Logger LOG = LoggerFactory.getLogger(SapRevenueCloudBusinessConfigImportJob.class);

	private OutboundServiceFacade outboundServiceFacade;

	@Override
	public PerformResult perform(final CronJobModel job) {			
			// Send CronjobModel to SCPI
			getOutboundServiceFacade().send(job, "OutboundSapRevenueCloudBusinessConfigCronjob", "scpiBusinessConfigDestination")
					.subscribe(
							// onNext
							responseEntityMap -> {

								if (isSentSuccessfully(responseEntityMap)) {

									job.setResult(CronJobResult.SUCCESS);
									job.setStatus(CronJobStatus.FINISHED);
									LOG.info(String.format("Business config replication has been successfully completed with message [%n%s]", getPropertyValue(responseEntityMap, RESPONSE_MESSAGE)));

								} else {
									job.setResult(CronJobResult.FAILURE);
									job.setStatus(CronJobStatus.FINISHED);
									LOG.info(String.format("Business config replication failed with error message [%n%s]", getPropertyValue(responseEntityMap, RESPONSE_MESSAGE)));
								}

							}, error -> {
								job.setResult(CronJobResult.ERROR);
								job.setStatus(CronJobStatus.FINISHED);
								LOG.info(String.format("Business config replication failed with message [%n%s]", error.getMessage()));
							}
					);
		return new PerformResult(job.getResult(),job.getStatus());
	}



	public OutboundServiceFacade getOutboundServiceFacade() {
		return outboundServiceFacade;
	}

	public void setOutboundServiceFacade(OutboundServiceFacade outboundServiceFacade) {
		this.outboundServiceFacade = outboundServiceFacade;
	}

}