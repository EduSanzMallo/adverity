package com.adverity

import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class MetricService {

    List<Metric> getClicksByFilters(ClickFiltersCommand filters) {
        return Metric.getClicksByFilters(filters).list()
    }

    List<Metric> getCrtByFilters(CrtFiltersCommand filters) {
        return Metric.getCrtByFilters(filters).list()
    }

    List<Metric> getImpressionsByFilters(ImpressionFiltersCommand filters) {
        return Metric.getImpressionsByFilters(filters).list()
    }

    List<Metric> getMetricsByFilters(MetricFiltersCommand filters) {
        return Metric.getMetricsByFilters(filters).list()
    }
}
