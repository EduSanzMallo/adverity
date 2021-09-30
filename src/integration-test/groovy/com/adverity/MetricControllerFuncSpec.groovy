package com.adverity

import grails.testing.spock.OnceBefore
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import grails.testing.mixin.integration.Integration

@Integration
class MetricControllerFuncSpec extends Specification {

    @Shared
    @AutoCleanup
    private HttpClient client

    @OnceBefore
    void init() {
        String baseUrl = "http://localhost:$serverPort"
        this.client  = HttpClient.create(baseUrl.toURL())
    }

    void 'test getClicksByFilters request'() {
        when:
            Map<String, Object> queryParams = [datasource : 'Google%20Ads', campaign : 'Remarketing', start : '01-11-2019', end : '30-11-2019']
            def response = client.toBlocking().exchange(HttpRequest.GET("/metrics/getClicksByFilters?datasource=${queryParams.datasource}&campaign=${queryParams.campaign}&start=${queryParams.start}&end=${queryParams.end}").contentType(MediaType.APPLICATION_JSON_TYPE), Map)

        then:
            response.body().clicks
            response.status() == HttpStatus.OK
    }

    void 'test getCrtByFilters request'() {
        when:
            Map<String, Object> queryParams = [datasource : 'Google%20Ads', campaign : 'Remarketing', start : '01-11-2019', end : '30-11-2019']
            def response = client.toBlocking().exchange(HttpRequest.GET("/metrics/getCrtByFilters?datasource=${queryParams.datasource}&campaign=${queryParams.campaign}&start=${queryParams.start}&end=${queryParams.end}").contentType(MediaType.APPLICATION_JSON_TYPE), Map)

        then:
            response.body().crt
            response.status() == HttpStatus.OK
    }

    void 'test getImpressionsByFilters request'() {
        when:
            Map<String, Object> queryParams = [datasource : 'Google%20Ads', campaign : 'Remarketing', actionDate : '19-11-2019']
            def response = client.toBlocking().exchange(HttpRequest.GET("/metrics/getImpressionsByFilters?datasource=${queryParams.datasource}&campaign=${queryParams.campaign}&actionDate=${queryParams.actionDate}").contentType(MediaType.APPLICATION_JSON_TYPE), Map)

        then:
            response.body().impressions
            response.status() == HttpStatus.OK
    }

    void 'test getMetricsByFilters request'() {
        when:
            Map<String, Object> queryParams = [datasource : 'Google%20Ads', campaign : 'Remarketing', start : '01-11-2019', end : '30-11-2019']
            def response = client.toBlocking().exchange(HttpRequest.GET("/metrics/getMetricsByFilters?datasource=${queryParams.datasource}&campaign=${queryParams.campaign}&start=${queryParams.start}&end=${queryParams.end}").contentType(MediaType.APPLICATION_JSON_TYPE), Map)

        then:
            response.body().metrics
            response.status() == HttpStatus.OK
    }

    void 'forcing to fail getMetricsByFilters request'() {
        when:
            Map<String, Object> queryParams = [datasource : 'Google%20Ads', campaign : 'Remarketing', start : '01-2019', end : '30-11-2019']
            def response = client.toBlocking().exchange(HttpRequest.GET("/metrics/getMetricsByFilters?datasource=${queryParams.datasource}&campaign=${queryParams.campaign}&start=${queryParams.start}&end=${queryParams.end}").contentType(MediaType.APPLICATION_JSON_TYPE), Map)

        then:
            !response
            HttpClientResponseException e = thrown()
            e.message == 'Invalid format for start field in the request'
    }
}
