# adverity
Simple Data Warehouse (extract, transform, load, query)

You are going to write a simple backend application that exposes data - extracted from a csv file via an API.
The file can be found here
http://adverity-challenge.s3-website-eu-west-1.amazonaws.com/PIxSyyrIKFORrCXfMYqZBI.csv

Transform it to your needs so it can be loaded into your favorite data store.

The API should make it possible to query the data in a generic and efficient way.

Possible queries might look like this:
- Total Clicks for a given Datasource for a given Date range
- Click-Through Rate (CTR) per Datasource and Campaign
- Impressions over time (daily)

And the data looks like this:
- a time dimension (Date)
- regular dimensions (Campaign, Datasource)
- metrics (Clicks, Impressions)

As a hint, the API consumes these parameters
- a set of metrics (plus calculated ones) to be aggregated on
- an optional set of dimensions to be grouped by
- an optional set of dimension filters to be filtered on

Tech / architecture
Feel free to
- Choose any JVM based language and framework
- Design any architecture that fits the problem/usecase best
- Model the API contract on your behalf
- Use any additional tech that helps extracting, transforming, loading and querying data

Deliverable
- Publish your solution on GitHub
- Deploy it somewhere so that the API can be publicly queried from



**TO IMPROVE**
- Include authentication
- Tweak results with Optional<> to avoid null results
- Error message enhacenments
- Consult registration
- Install Jaeger plugin


**HOW TO CONSUME API (examples)**
- Clicks
  1. filters: both datasource, campaign, start and end are optional

.../metrics/getClicksByFilters?datasource=Google%20Ads&campaign=Remarketing&start=01-09-2019&end=30-09-2019

- CRT
  1. filters: both datasource, campaign, start and end are optional

.../metrics/getCrtByFilters?datasource=Google%20Ads&campaign=Remarketing&start=01-09-2019&end=30-09-2019

- Impressions
  1. filters: both datasource, campaign, actionDate are optional

.../metrics/getImpressionsByFilters?datasource=Google%20Ads&campaign=Remarketing&actionDate=19-09-2019

- Metrics
  1. filters: both datasource, campaign, start and end are optional
  2. projections: fields to include
  3. groupBy: fields to group by

.../metrics/getMetricsByFilters?datasource=Google%20Ads&campaign=Remarketing&start=01-09-2019&end=30-09-2019&projections=datasource,campaign,actionDate,clicks&groupBy=campaign


*NOTICE*
Needed to create a file called "adverity-development-config.properties" in /etc/adverity/ with the following content:

ADVERITY_JDBC_CONNECTION_STRING='jdbc:mysql://127.0.0.1:3306/adverity?autoreconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull&useUnicode=yes&characterEncoding=UTF8'
ADVERITY_JDBC_CONNECTION_USER='XXX'
ADVERITY_JDBC_CONNECTION_PASSWORD='XXX'
