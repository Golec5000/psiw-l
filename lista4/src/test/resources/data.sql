INSERT INTO server(version, ip, name, id, created_date, last_update_date, is_active)
VALUES (1, '111.112.113.114', 'A', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

INSERT INTO server(version, ip, name, id, created_date, last_update_date, is_active)
VALUES (1, '221.222.223.224', 'B', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

INSERT INTO server(version, ip, name, id, created_date, last_update_date, is_active)
VALUES (1, '68.69.70.71', 'C', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TRUE);

insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 30, 	1, '2', 	PARSEDATETIME('2018-02-03 03:18:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	1	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 550, 	1, '3', 	PARSEDATETIME('2018-02-03 13:08:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	2	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 210, 	1, '3', 	PARSEDATETIME('2018-02-03 22:28:06', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	3	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 890, 	1, '3', 	PARSEDATETIME('2018-12-04 03:28:06', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	4	, 'GET');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 350, 	1, '3', 	PARSEDATETIME('2018-12-04 03:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	5	, 'GET');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 4200, 	1, '3', 	PARSEDATETIME('2018-12-05 07:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	6	, 'GET');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 10, 	1, '3', 	PARSEDATETIME('2018-02-05 07:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	7	, 'GET');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 3, 		1, '22', 	PARSEDATETIME('2018-02-06 07:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	8	, 'GET');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 530, 	1, '22', 	PARSEDATETIME('2018-02-07 17:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	9	, 'GET');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 6530, 	1, '22', 	PARSEDATETIME('2018-02-08 17:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	10	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 230, 	1, '22', 	PARSEDATETIME('2018-12-08 17:25:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	11	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 180, 	1, '22', 	PARSEDATETIME('2018-12-09 17:25:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	12	, 'POST');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 320, 	1, '8', 	PARSEDATETIME('2018-02-09 13:25:06', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	13	, 'POST');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 320, 	1, '8', 	PARSEDATETIME('2018-02-09 13:05:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	14	, 'POST');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 320, 	1, '8', 	PARSEDATETIME('2018-02-09 13:05:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	15	, 'DELETE');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 6530, 	2, '22', 	PARSEDATETIME('2017-04-08 14:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	16	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 230, 	2, '22', 	PARSEDATETIME('2017-04-08 14:25:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	17	, 'PUT');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 180, 	2, '22', 	PARSEDATETIME('2017-04-09 04:24:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	18	, 'POST');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 320, 	2, '8', 	PARSEDATETIME('2017-04-09 04:24:06', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	19	, 'POST');
insert into request_event(analysis_required, duration, server_id, thread_id, time, user_id, id, method) values (false, 320, 	2, '8', 	PARSEDATETIME('2018-04-09 03:25:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	20	, 'POST');

insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 30, 		2, '2', 	PARSEDATETIME('2018-02-03 03:18:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	21,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 550, 		2, '3', 	PARSEDATETIME('2018-02-03 13:08:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	22,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 210, 		2, '3', 	PARSEDATETIME('2018-02-03 22:28:06', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	23,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 890, 		2, '3', 	PARSEDATETIME('2018-12-04 03:28:06', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	24,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 350, 		2, '3', 	PARSEDATETIME('2018-12-04 03:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	25,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 4200, 	2, '3', 	PARSEDATETIME('2018-12-05 07:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	26,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 10, 		2, '3', 	PARSEDATETIME('2018-02-05 07:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Jan', 	27,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 3, 		2, '22', 	PARSEDATETIME('2018-02-06 07:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	28,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 530, 		2, '22', 	PARSEDATETIME('2018-02-07 17:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	29,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 6530, 	3, '22', 	PARSEDATETIME('2018-02-08 17:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	30,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 230, 		3, '22', 	PARSEDATETIME('2018-12-08 17:25:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	31,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 180, 		3, '22', 	PARSEDATETIME('2018-12-09 17:25:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	32,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 320, 		3, '8', 	PARSEDATETIME('2018-02-09 13:25:06', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	33,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 320, 		3, '8', 	PARSEDATETIME('2018-02-09 13:05:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	34,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 320, 		3, '8', 	PARSEDATETIME('2018-02-09 13:05:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	35,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 6530, 	3, '22', 	PARSEDATETIME('2017-04-08 14:28:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	36,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 230, 		3, '22', 	PARSEDATETIME('2017-04-08 14:25:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	37,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 180, 		3, '22', 	PARSEDATETIME('2017-04-09 04:24:04', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	38,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (false, 320, 		3, '8', 	PARSEDATETIME('2017-04-09 04:24:06', 'yyyy-MM-dd HH:mm:ss'), 'Pawel', 	39,    'SELECT 1 FROM DUAL');
insert into sql_event(analysis_required, duration, server_id, thread_id, time, user_id, id, sql_query) values (true, 320, 		3, '8', 	PARSEDATETIME('2018-04-09 03:25:06', 'yyyy-MM-dd HH:mm:ss'), 'Adam', 	40,    'SELECT 1 FROM DUAL');
commit;


alter sequence server_seq restart with 1000;
alter sequence event_seq restart with 1000;
