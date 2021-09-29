package com.adverity

class Metric {

    Datasource datasource

    Campaign campaign

    Date actionDate

    Long clicks

    Long impressions

    BigDecimal crt

    static constraints = {
        datasource nullable: false
        campaign nullable: false
        actionDate nullable: false
        clicks nullable: false
        impressions nullable: false
        crt nullable: false
    }

    static mapping = {
        autoTimestamp false
        crt formula: '(CLICKS / IMPRESSIONS) * 100'
    }

    static namedQueries = {
        getClicksByFilters { ClickFiltersCommand filters ->
            projections {
                sum('clicks')
            }
            if (filters.datasource) {
                datasource {
                    eq("name", filters.datasource)
                }
            }
            if (filters.campaign) {
                campaign {
                    eq("name", filters.campaign)
                }
            }
            if (filters.start && filters.end) {
                between("actionDate", filters.start, filters.end)
            }
        }

        getCrtByFilters { CrtFiltersCommand filters ->
            projections {
                sum('crt')
            }
            if (filters.datasource) {
                datasource {
                    eq("name", filters.datasource)
                }
            }
            if (filters.campaign) {
                campaign {
                    eq("name", filters.campaign)
                }
            }
            if (filters.start && filters.end) {
                between("actionDate", filters.start, filters.end)
            }
        }

        getImpressionsByFilters { ImpressionFiltersCommand filters ->
            projections {
                sum('impressions')
            }
            if (filters.datasource) {
                datasource {
                    eq("name", filters.datasource)
                }
            }
            if (filters.campaign) {
                campaign {
                    eq("name", filters.campaign)
                }
            }
            if (filters.actionDate) {
                eq("actionDate", filters.actionDate)
            }
        }

        getMetricsByFilters { MetricFiltersCommand filters ->
            if (filters.datasource) {
                datasource {
                    eq("name", filters.datasource)
                }
            }
            if (filters.campaign) {
                campaign {
                    eq("name", filters.campaign)
                }
            }
            if (filters.start && filters.end) {
                between("actionDate", filters.start, filters.end)
            }
            if (filters.groupBy) {
                filters.groupBy.each {groupField ->
                    groupProperty(groupField)
                }
            }
        }
    }
}
