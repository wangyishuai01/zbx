-- dstart 开始时间 dend 结束时间 dflag[hour|day|month|year] 时间间隔   productType 类型(文章/视频)  userID 用户Id
-- 返回每个时间段的时间和数量字符串 eg：时间1:数量1|时间2:数量2|时间3:数量3...
Drop function if exists function_statistics;
CREATE FUNCTION function_statistics(dstart TIMESTAMP, dend TIMESTAMP, dflag VARCHAR(10),productType INT(1),userID INT(11))
RETURNS VARCHAR(1000)
BEGIN
	DECLARE dateBox TIMESTAMP;
	DECLARE resultMap VARCHAR(1000);
	DECLARE dformat VARCHAR(20);
	SET dateBox = dstart, resultMap = '';
	CASE 
		WHEN dflag = 'hour' THEN SET dformat = '%Y-%m-%d %H';
		WHEN dflag = 'day' THEN SET dformat = '%Y-%m-%d';
		WHEN dflag = 'month' THEN SET dformat = '%Y-%m';
		WHEN dflag = 'year' THEN SET dformat = '%Y';
	END CASE;
	WHILE dateBox <= dend DO 
		IF userID = 0 THEN
			SELECT CONCAT(resultMap,'|',DATE_FORMAT(dateBox,dformat),':',count(1)) INTO resultMap FROM blog WHERE 
			DATE_FORMAT(create_date,dformat) = DATE_FORMAT(dateBox,dformat)
			AND product_type = productType;
		ELSE
			SELECT CONCAT(resultMap,'|',DATE_FORMAT(dateBox,dformat),':',count(1)) INTO resultMap FROM blog WHERE 
			DATE_FORMAT(create_date,dformat) = DATE_FORMAT(dateBox,dformat)
			AND product_type = productType
			AND user_id = userID;
		END IF;
		CASE 
			WHEN dflag = 'hour' THEN set dateBox = date_add(dateBox, interval 1 hour);
			WHEN dflag = 'day' THEN set dateBox = date_add(dateBox, interval 1 day);
			WHEN dflag = 'month' THEN set dateBox = date_add(dateBox, interval 1 month);
			WHEN dflag = 'year' THEN set dateBox = date_add(dateBox, interval 1 year);
		END CASE;
	END WHILE;
	RETURN SUBSTRING(resultMap,2);
END
