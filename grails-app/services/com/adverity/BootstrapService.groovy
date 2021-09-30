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
}
