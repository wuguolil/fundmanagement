-- Create MYSCHEMA schema.
CREATE SCHEMA MYSCHEMA;

 --create table MYSCHEMA.fund
 --(
 --    fund_id int auto_increment
--         primary key,
--    name varchar(255) not null,
--     manager_id int not null,
--     constraint FKitpvsy366stvgbkrp9qxwnuwa
--         foreign key (manager_id) references manager (employee_id)
-- );
 create table MYSCHEMA.manager
 (
     employee_id int auto_increment
         primary key,
     first_name varchar(255) not null,
     last_name varchar(255) not null
 );
-- create table MYSCHEMA.position
-- (
--     position_id int auto_increment
--         primary key,
--     date_purchased date not null,
--     quantity int not null,
--     fund_id int not null,
--     security_id int null,
--     constraint FK52w1kr0uofrity60twone535t
--         foreign key (security_id) references security (security_id),
--     constraint FK6g64huq2hevw1489n2t99r1il
--         foreign key (fund_id) references fund (fund_id)
-- );
-- create table MYSCHEMA.security
-- (
--     security_id int auto_increment
--         primary key,
--     symbol varchar(255) not null
-- );
--
-- -- Populate tables.
-- USE MYSCHEMA;
--
 -- Populate EMPLOYEES table.
 INSERT INTO MYSCHEMA.manager (employee_id, first_name, last_name) VALUES
 	(1, 'Guoli', 'Wu'),
 	(2, 'Jialong', 'Zou'),
 	(3,   'Long', 'Zhou');

-- new Fund(1,"Olympic Memorial Fund", savedManagers.get(0), new HashSet<>()),
--                     new Fund(2,"UK Overseas Income Fund", savedManagers.get(0), new HashSet<>()),
--                     new Fund(3,"North America Growth", savedManagers.get(1), new HashSet<>()),
--                     new Fund(4, "Global Tech Fund", savedManagers.get(2), new HashSet<>()),
--                     new Fund(5,"Olympic Memorial Fund", savedManagers.get(0), new HashSet<>())
--
-- -- Populate EMPLOYEES2 table.
-- INSERT INTO MYSCHEMA.fund (fund_id, name, manager_id) VALUES
-- 	(1,  'Baijiu Fund',1),
-- 	(2,  'Xinnengyuan Fund',1),
-- 	(3,  'Zhongyao Fund',2),
-- 	(4,  'Global Tech Fund',3),
-- 	(5,  'Baijiu Fund',1);
--
--
-- -- Populate SKILLS table.
-- INSERT INTO MYSCHEMA.SKILLS (EmployeeID, Description, Level) VALUES
-- 	(1, 'Football', 5),
-- 	(1, 'Skiing',   3),
-- 	(1, 'Running',  3),
-- 	(2, 'Sales',    4),
-- 	(2, 'Skiing',   4),
-- 	(2, 'Football', 4),
-- 	(3, 'Maths',    5),
-- 	(3, 'Singing',  4),
-- 	(3, 'Teaching', 5),
-- 	(3, 'Running',  2);
--
--
-- -- Populate CONTRACTS table.
-- INSERT INTO MYSCHEMA.CONTRACTS (EmployeeID, StartDate, StartSalary) VALUES
-- 	(1,  '2008-1-1',  10000),
-- 	(2,  '2009-1-2',  20000),
-- 	(3,  '2009-1-3',  30000),
-- 	(4,  '2009-1-4',  40000),
-- 	(5,  '2009-1-5',  50000),
-- 	(6,  '2009-1-6',  60000),
-- 	(7,  '2009-1-7',  70000),
-- 	(8,  '2009-1-8',  80000),
-- 	(9,  '2009-1-9',  90000);
--
--
-- -- Populate PERSONS table.
-- INSERT INTO MYSCHEMA.PERSONS (FirstName, LastName, Address1, Address2, Address3) VALUES
-- 	('John', 'Smith', '1 Main St', 'Weston', 'Bath'),
-- 	('Jane', 'Evans', '2 High St', 'Newton', 'Neath'),
-- 	('Bill', 'Jones', '3 Oaks St', 'Denton', 'Leeds');
--
--
-- -- Populate DEPARTMENTS table.
-- INSERT INTO MYSCHEMA.DEPARTMENTS(RegulatoryName, CommonName) VALUES
-- 	('MKT', 'Markets'),
-- 	('ACC',  NULL),
-- 	('HR',   NULL),
-- 	(NULL,  'R&D'),
-- 	(NULL,  'Admin');
