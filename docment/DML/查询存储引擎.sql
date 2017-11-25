show ENGINES;
SHOW VARIABLES LIKE 'storage_engine';

show table status from zbx where name in('article_main','buser','buser_limit','captcha','comment','cus_buy_history','customer','login_history','menu','tclassify','video_main','price','keywords','keyrelation');

--alter table article_main engine=innodb;