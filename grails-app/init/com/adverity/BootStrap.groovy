package com.adverity

class BootStrap {

    def bootstrapService

    def init = { servletContext ->
        bootstrapService.populateFromCsvFile()
    }
    def destroy = {
    }
}
