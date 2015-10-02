create table reports.aggregation (
 campaign_id timeuuid,
 ad_id uuid,
 company_name text,
 ds_id bigint,
 views bigint,
 unique_views int,
 clicks bigint,
 unique_clicks int,
 page_views int,
 visit_time bigint,
 urls map<varchar, int>,
 updated_at bigint,
 primary key (campaign_id, ad_id, company_name, ds_id)
)

create table reports.tweets_analysis (
 keyword text,
 type text,
 ds_id bigint,
 start_date bigint,
 end_date bigint,
 count int,
 primary key (keyword, type, ds_id, start_date)
)