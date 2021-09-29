package com.adverity

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DatasourceSpec extends Specification implements DomainUnitTest<Datasource> {

    def setup() {
    }

    def cleanup() {
    }

    void "check all constraints"() {
        expect:
            new Datasource(name: name).validate() == valid
        where:
            name                         | valid
            "Google Ads"                 |  true
            ""                           |  false
            null                         |  false
    }
}
