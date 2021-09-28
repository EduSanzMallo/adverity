package com.adverity

import com.adverity.exception.InvalidFormatException
import com.adverity.exception.MissingParameterException
import grails.databinding.BindingFormat
import grails.rest.*

class MetricController extends RestfulController {

	static responseFormats = ['json', 'xml']

    MetricController() {
        super(Metric)
    }

    MetricService metricService
	
    def getClicksByFilters(ClickFiltersCommand filters) {
        if (filters.validate()) {
            [clicks: metricService.getClicksByFilters(filters)]
        } else {
            errorHandling(filters)
        }
    }

    class ClickFiltersCommand implements grails.validation.Validateable {

        String datasource
        String campaign

        @BindingFormat("dd-MM-yyyy")
        Date start

        @BindingFormat("dd-MM-yyyy")
        Date end

        static constraints = {
            datasource nullable: true
            campaign nullable: true
            start nullable: true
            end nullable: true
        }
    }

    def getCrtByFilters(CrtFiltersCommand filters) {
        if (filters.validate()) {
            [crt: metricService.getCrtByFilters(filters)]
        } else {
            errorHandling(filters)
        }
    }

    class CrtFiltersCommand implements grails.validation.Validateable {

        String datasource
        String campaign

        @BindingFormat("dd-MM-yyyy")
        Date start

        @BindingFormat("dd-MM-yyyy")
        Date end

        static constraints = {
            datasource nullable: true
            campaign nullable: true
            start nullable: true
            end nullable: true
        }
    }

    def getImpressionsByFilters(ImpressionFiltersCommand filters) {
        if (filters.validate()) {
            [impressions: metricService.getImpressionsByFilters(filters)]
        } else {
            errorHandling(filters)
        }
    }

    class ImpressionFiltersCommand implements grails.validation.Validateable {

        String datasource
        String campaign

        @BindingFormat("dd-MM-yyyy")
        Date actionDate

        static constraints = {
            datasource nullable: true
            campaign nullable: true
            actionDate nullable: true
        }
    }

    def getMetricsByFilters(MetricFiltersCommand filters) {
        if (filters.validate()) {
            [metrics: metricService.getMetricsByFilters(filters), includes: filters?.projections?.tokenize(',')]
        } else {
            errorHandling(filters)
        }
    }

    class MetricFiltersCommand implements grails.validation.Validateable {

        String datasource
        String campaign

        @BindingFormat("dd-MM-yyyy")
        Date start

        @BindingFormat("dd-MM-yyyy")
        Date end

        String projections
        String groupBy

        static constraints = {
            datasource nullable: true
            campaign nullable: true
            start nullable: true
            end nullable: true
            projections nullable: true
            groupBy nullable: true
        }
    }

    /*
        Error handling section
     */
    private void errorHandling(Object filters) {
        def fieldError = filters.errors.getFieldError()
        def field = fieldError.getField()
        def bindingFailure = fieldError.bindingFailure
        if (bindingFailure) {
            if (field) {
                throw new InvalidFormatException("Invalid format for ${field} field in the request")
            }
        } else {
            if (field) {
                throw new MissingParameterException("Missing ${field} field in the request")
            }
        }
    }

    def handleMissingParameterException(MissingParameterException e) {
        render status: 500, message: e.message
    }

    def handleInvalidFormatException(InvalidFormatException e) {
        render(status: 500, message: e.message)
    }
}
