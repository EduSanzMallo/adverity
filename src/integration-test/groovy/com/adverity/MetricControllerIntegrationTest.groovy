package com.adverity

import grails.testing.mixin.integration.Integration
import spock.lang.Shared

import java.text.SimpleDateFormat

@Integration
class MetricControllerIntegrationTest extends BaseControllerIntegrationSpec {

    String controllerName = "metric"

    MetricController controller

    @Shared Datasource datasource
    @Shared Campaign campaign

    def setup() {
        controller = autowire(MetricController)
    }

    def setupData() {
        datasource = new Datasource(name: 'Google Ads').save()
        campaign = new Campaign(name: 'Remarketing').save()
    }

    void "test getClicksByFilters method"() {
        when:
            def result = controller.getClicksByFilters(new ClickFiltersCommand(
                    datasource: datasource,
                    campaign: campaign,
                    start: new SimpleDateFormat("dd-MM-yyyy").parse("01-11-2019"),
                    end: new SimpleDateFormat("dd-MM-yyyy").parse("30-11-2019")))
        then:
            result.clicks
    }

    void "test getCrtByFilters method"() {
        when:
            def result = controller.getCrtByFilters(new CrtFiltersCommand(
                datasource: datasource,
                campaign: campaign,
                start: new SimpleDateFormat("dd-MM-yyyy").parse("01-11-2019"),
                end: new SimpleDateFormat("dd-MM-yyyy").parse("30-11-2019")))
        then:
            result.crt
    }

    void "test getImpressionsByFilters method"() {
        when:
            def result = controller.getImpressionsByFilters(new ImpressionFiltersCommand(
                datasource: datasource,
                campaign: campaign,
                actionDate: new SimpleDateFormat("dd-MM-yyyy").parse("19-11-2019")))
        then:
            result.impressions
    }

    void "test getMetricsByFilters method"() {
        when:
            def result = controller.getMetricsByFilters(new MetricFiltersCommand(
                datasource: datasource,
                campaign: campaign,
                start: new SimpleDateFormat("dd-MM-yyyy").parse("01-11-2019"),
                end: new SimpleDateFormat("dd-MM-yyyy").parse("30-11-2019")))
        then:
            result.metrics
    }
}
