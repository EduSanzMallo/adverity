package com.adverity

import grails.testing.gorm.DataTest
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MetricSpec extends Specification implements DomainUnitTest<Metric>, DataTest {

    Class<?>[] getDomainClassesToMock() {
        return [Metric, Datasource, Campaign] as Class[]
    }

    def setup() {
    }

    def cleanup() {
    }

    void "check all constraints"() {
        expect:
            new Metric(datasource: datasource, campaign: campaign, actionDate: actionDate, clicks: clicks, impressions: impressions).validate() == valid
        where:
            datasource                            | campaign                               | actionDate | clicks | impressions | valid
        new Datasource(name: 'Google Ads').save() |   null                                 |    null    |  null  |     null    | false
        new Datasource(name: 'Google Ads').save() |new Campaign(name: 'Remarketing').save()|    null    |  null  |     null    | false
        new Datasource(name: 'Google Ads').save() |new Campaign(name: 'Remarketing').save()| new Date() |  null  |     null    | false
        new Datasource(name: 'Google Ads').save() |new Campaign(name: 'Remarketing').save()| new Date() |    3   |     null    | false
        new Datasource(name: 'Google Ads').save() |new Campaign(name: 'Remarketing').save()| new Date() |    3   |     100     | true
    }
}
