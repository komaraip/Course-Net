-- 1
CREATE TABLE Customer9 (
    CustomerId char(5) PRIMARY KEY CHECK (CustomerId LIKE 'CU[0-9][0-9][0-9]'),
    CustomerName varchar(50) NOT NULL CHECK (LEN(CustomerName) BETWEEN 5 AND 25),
    CustomerGender varchar(10) CHECK (CustomerGender IN ('Male', 'Female')),
    CustomerPhone varchar(13),
    CustomerAddress varchar(100)
);

-- 2
ALTER TABLE Customer9 WITH NOCHECK ADD CONSTRAINT CustomerNameLength CHECK (LEN(CustomerName) BETWEEN 5 AND 25);

-- 3
INSERT INTO Customer9 (CustomerId, CustomerName, CustomerGender, CustomerPhone, CustomerAddress)
VALUES
	('CU001', 'Zackys', 'Male', '081112328748', 'Jalan Nangka No. 5'),
	('CU002', 'Kevin Axellino Triantio', 'Male', '081267381930', 'Jalan Jelambar No. 17'),
	('CU003', 'Kerin Augustin', 'Male', '081387162832', 'Jalan Salam No. 20'),
	('CU004', 'Fernando Lioexander', 'Male', '081232328555', 'Jalan Sudirman No. 5'),
	('CU005', 'Naufal Hafiz', 'Male', '081387161005', 'Jalan Belong No. 8'),
	('CU006', 'Arya Thomas', 'Male', '081275162832', 'Jalan Rawa No. 10');

-- 4
UPDATE Customer9
SET CustomerAddress = REPLACE(CustomerAddress, 'Jalan', 'Jl.');
SELECT * FROM Customer9;

-- 5
DELETE FROM Customer9
WHERE CHARINDEX(' ', CustomerName) = 0;
SELECT * FROM Customer9;

-- 6
CREATE TABLE Staff9 (
    StaffId INT PRIMARY KEY,
    StaffName VARCHAR(50),
    TransactionDate DATE
);

INSERT INTO Staff9 (StaffId, StaffName, TransactionDate)
VALUES
    (1, 'Louis Rudy Valen', '2017-12-21'),
    (2, 'Nathaniel Putera', '2017-12-21'),
    (3, 'John Doe', '2017-12-22');

SELECT
	UPPER(LEFT(StaffName, 1) + SUBSTRING(StaffName, CHARINDEX(' ', StaffName) + 1, 1)) AS Initial,
    StaffName,
    TransactionDate AS Day
FROM
    Staff9
WHERE
    DATEPART(WEEKDAY, TransactionDate) = 5; 

-- 7
CREATE TABLE Transaction9 (
    TransactionID CHAR(5) PRIMARY KEY,
    CustomerName VARCHAR(50),
	CustomerId CHAR(5) REFERENCES Customers(CustomerId),
    ItemName VARCHAR(50) NOT NULL,
	TransactionDate DATE,
    Price DECIMAL(18, 2)
);

INSERT INTO Transaction9 (TransactionID, CustomerName, CustomerId, ItemName, TransactionDate, Price)
VALUES
    ('TR001', 'Kevin Axellino Triantio', 'CU002', 'Keyboard', '2017-12-20', '170000'),
    ('TR001', 'Kevin Axellino Triantio', 'CU002', 'Bicycle', '2017-12-20', '2300000'),
    ('TR002', 'Kerin Augustin', 'CU003', 'Monitor', '2017-12-21', '150000'),
    ('TR002', 'Kerin Augustin', 'CU003', 'Television', '2017-12-21', '130000'),
    ('TR003', 'Fernando Lioexander', 'CU004', 'Handphone', '2017-12-21', '30000'),
    ('TR003', 'Fernando Lioexander', 'CU004', 'Webcam', '2017-12-21', '70000'),
    ('TR004', 'Naufal Hafiz', 'CU005', 'Camera', '2017-12-21', '9000000'),
    ('TR004', 'Naufal Hafiz', 'CU005', 'Lamp', '2017-12-21', '95000'),
    ('TR004', 'Naufal Hafiz', 'CU005', 'Bicycle', '2017-12-21', '2300000'),
    ('TR004', 'Naufal Hafiz', 'CU005', 'Diving', '2017-12-21', '4400000'),
    ('TR005', 'Arya Thomas', 'CU006', 'Table', '2017-12-21', '190000'),
    ('TR006', 'Kevin Axellino Triantio', 'CU002', 'Chair', '2017-12-21', '110000'),
    ('TR006', 'Kevin Axellino Triantio', 'CU002', 'Keychain', '2017-12-21', '100000'),
    ('TR007', 'Kerin Augustin', 'Cable', 'CU003', '2017-12-21', '80000'),
    ('TR007', 'Kerin Augustin', 'Gamepad', 'CU003', '2017-12-21', '75000'),
    ('TR008', 'Fernando Lioexander', 'CU004', 'Mouse', '2017-12-21', '50000'),
    ('TR008', 'Fernando Lioexander', 'Bracket', 'CU004', '2017-12-21', '45000'),
    ('TR009', 'Arya Thomas', 'CU006', 'Lanyard', '2017-12-21', '300000'),
    ('TR009', 'Arya Thomas', 'CU006', 'Bottle', '2017-12-21', '400000');

SELECT
    TransactionID,
    CustomerName,
    COUNT(*) AS [Total Item per Transaction]
FROM
    Transaction9
GROUP BY
    TransactionID,CustomerName;

-- 8
SELECT
    C.CustomerName,
    T.TransactionDate,
    T.ItemName,
    CAST(REPLACE(T.Price, 'Rp. ', '') AS DECIMAL(18, 2)) AS Price
FROM
    Transaction9 AS T
INNER JOIN
    Staff9 AS S ON S.StaffID = S.StaffID
INNER JOIN
    Customer9 AS C ON T.CustomerID = C.CustomerID
WHERE
    DATENAME(WEEKDAY, T.TransactionDate) = 'Wednesday'
    AND S.StaffName LIKE '%Putera%'
ORDER BY
    T.TransactionDate DESC;

-- 9
SELECT
    REPLACE(TransactionID, 'TR', 'Trans') AS TransactionID,
    T.TransactionDate AS TransactionDate,
    S.StaffName,
    C.CustomerName
FROM
    Transaction9 AS T
INNER JOIN
    Staff9 AS S ON S.StaffID = S.StaffID
INNER JOIN
    Customer9 AS C ON T.CustomerID = C.CustomerID
WHERE
    DATEDIFF(DAY, '2017-12-31', T.TransactionDate) = 10;

-- 10
SELECT
    C.CustomerName,
    C.CustomerGender,
    C.CustomerPhone,
    C.CustomerAddress
FROM
    Customer9 AS C
WHERE
    LEN(C.CustomerName) > 15
    AND EXISTS (
        SELECT 1
        FROM Transaction9 AS T
        WHERE
            T.CustomerID = C.CustomerID
            AND DATENAME(WEEKDAY, T.TransactionDate) = 'Thursday'
    );