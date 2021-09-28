package com.adverity

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment
import org.springframework.core.env.MapPropertySource

class Application extends GrailsAutoConfiguration implements EnvironmentAware {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    void setEnvironment(Environment environment) {
        def file = new File("/etc/adverity/adverity-${grails.util.Environment.getCurrent().name}-config.properties")
        if(file.exists()) {
            def config = new ConfigSlurper().parse(file.text)
            environment.propertySources.addFirst(new MapPropertySource(grails.util.Environment.getCurrent().name, config))
        }
    }
}