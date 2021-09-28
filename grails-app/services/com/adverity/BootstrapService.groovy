package com.adverity

import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class BootstrapService {

    void populateFromCsvFile() {
        if (Metric.count() == 0) {
            def csvFile = this.class.classLoader.getResource("Adverity_metrics.csv").file
            BufferedReader br
            String line
            try {
                br = new BufferedReader(new FileReader(csvFile))
                br.readLine() // read the first line
                while((line = br.readLine()) != null) {
                    log.debug(line)
                    def fieldList = line.tokenize(',')
                    Datasource datasource = Datasource.findByName(fieldList[0])
                    if (!datasource) {
                        datasource = new Datasource(name: fieldList[0]).save(flush: true)
                    }
                    Campaign campaign = Campaign.findByName(fieldList[1])
                    if (!campaign) {
                        campaign = new Campaign(name: fieldList[1]).save(flush: true)
                    }
                    Date actionDate = new SimpleDateFormat("MM/dd/yy").parse(fieldList[2])
                    new Metric(datasource: datasource, campaign: campaign, actionDate: actionDate, clicks: fieldList[3].toLong(), impressions: fieldList[4].toLong()).save(failOnError: true)
                }
            } catch (IOException ioe){
                log.error ioe.printStackTrace()
            } catch (Exception e){
                log.error e.printStackTrace()
            } finally {
                if (br != null) br.close()
            }
        }
    }

    void createDatasources() {
        if (Datasource.count() == 0) {
            new Datasource(name: 'DS1').save()
            new Datasource(name: 'DS2').save()
            new Datasource(name: 'DS3').save()
            new Datasource(name: 'DS4').save()
            new Datasource(name: 'DS5').save()
        }
    }

    void createCampaigns() {
        if (Campaign.count() == 0) {
            new Campaign(name: 'C1').save()
            new Campaign(name: 'C2').save()
            new Campaign(name: 'C3').save()
            new Campaign(name: 'C4').save()
            new Campaign(name: 'C5').save()
        }
    }

    void createMetrics() {
        if (Metric.count() == 0) {
            new Metric(datasource: Datasource.findByName('DS1'), campaign: Campaign.findByName('C1'), actionDate: new Date(), clicks: 123, impressions: 123000).save(failOnError: true)
            new Metric(datasource: Datasource.findByName('DS2'), campaign: Campaign.findByName('C2'), actionDate: new Date(), clicks: 90, impressions: 90000).save()
            new Metric(datasource: Datasource.findByName('DS3'), campaign: Campaign.findByName('C3'), actionDate: new Date(), clicks: 45, impressions: 45000).save()
            new Metric(datasource: Datasource.findByName('DS4'), campaign: Campaign.findByName('C4'), actionDate: new Date(), clicks: 19, impressions: 19000).save()
            new Metric(datasource: Datasource.findByName('DS5'), campaign: Campaign.findByName('C5'), actionDate: new Date(), clicks: 76, impressions: 76000).save()
        }
    }
}
