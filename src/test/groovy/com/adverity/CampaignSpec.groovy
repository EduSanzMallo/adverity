package com.adverity

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CampaignSpec extends Specification implements DomainUnitTest<Campaign> {

    def setup() {
    }

    def cleanup() {
    }

    void "check all constraints"() {
        expect:
            new Campaign(name: name).validate() == valid
        where:
            name                         | valid
            "Adventmarkt Touristik"      |  true
            ""                           |  false
            null                         |  false
    }
}
