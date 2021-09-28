package com.adverity

class BootStrap {

    def bootstrapService

    def init = { servletContext ->
        bootstrapService.populateFromCsvFile()
        //bootstrapService.createDatasources()
        //bootstrapService.createCampaigns()
        //bootstrapService.createMetrics()
    }
    def destroy = {
    }
}
