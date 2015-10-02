INSERT into reports.aggregation(campaign_id, ad_id, company_name, ds_id, clicks, page_views, unique_clicks, unique_views, views, visit_time)
VALUES(maxTimeuuid('2013-01-01 00:05+0000'),maxTimeuuid('2014-01-01 00:05+0000'),'Nike',20150929,4,2,3,5,15,6345);

INSERT into reports.aggregation(campaign_id, ad_id, company_name, ds_id, clicks, page_views, unique_clicks, unique_views, views, visit_time)
VALUES(maxTimeuuid('2013-01-01 00:05+0000'),maxTimeuuid('2014-01-02 00:05+0000'),'Nike',20150929,5,3,4,6,16,345);

INSERT into reports.aggregation(campaign_id, ad_id, company_name, ds_id, clicks, page_views, unique_clicks, unique_views, views, visit_time)
VALUES(maxTimeuuid('2013-01-01 00:05+0000'),maxTimeuuid('2014-01-01 00:05+0000'),'Nike',20150930,3,1,2,4,14,5345);

INSERT into reports.aggregation(campaign_id, ad_id, company_name, ds_id, clicks, page_views, unique_clicks, unique_views, views, visit_time)
VALUES(maxTimeuuid('2013-01-01 00:05+0000'),maxTimeuuid('2014-01-02 00:05+0000'),'Nike',20150930,5,3,3,7,14,3345);

INSERT into reports.aggregation(campaign_id, ad_id, company_name, ds_id, clicks, page_views, unique_clicks, unique_views, views, visit_time)
VALUES(maxTimeuuid('2013-01-01 00:05+0000'),maxTimeuuid('2014-01-01 00:05+0000'),'Nike',20151001,4,2,2,5,16,6345);

INSERT into reports.aggregation(campaign_id, ad_id, company_name, ds_id, clicks, page_views, unique_clicks, unique_views, views, visit_time)
VALUES(maxTimeuuid('2013-01-01 00:05+0000'),maxTimeuuid('2014-01-02 00:05+0000'),'Nike',20151001,2,3,2,4,11,2345);


INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Text', 20150929, 100, 200, 2);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Hashtag', 20150929, 100, 200, 1);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Text', 20150930, 100, 200, 3);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Hashtag', 20150930, 100, 200, 1);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Text', 20151001, 100, 200, 2);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Hashtag', 20151001, 100, 200, 3);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Text', 20150929, 200, 300, 1);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Hashtag', 20150929, 200, 300, 0);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Text', 20150930, 200, 300, 1);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Hashtag', 20150930, 200, 300, 1);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Text', 20151001, 200, 300, 1);

INSERT into reports.tweets_analysis(keyword, type, ds_id, start_date, end_date, count)
VALUES('Nike', 'Hashtag', 20151001, 200, 300, 2);