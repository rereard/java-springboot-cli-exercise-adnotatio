INSERT INTO Regions (RegionName)
VALUES
    ('North'),
    ('East'),
    ('West'),
    ('South');

INSERT INTO Deliveries (DeliveryName, Cost)
VALUES
    ('Express Delivery', 5000),
    ('North Delivery', 10000);

INSERT INTO Customers (FirstName, LastName, Balance)
VALUES
    ('Alice', 'Johnson', 50000000),
    ('Ben', 'Kornel', 50000000),
    ('Charles', 'Lee', 50000000);

INSERT INTO Contacts (CustomerId, PhoneNumber, Email)
VALUES
    (1, '123-456-7890', 'alice@gmail.com'),
    (2, '124-456-7890', 'ben@gmail.com'),
    (3, '125-456-7890', 'charles@gmail.com');

INSERT INTO Products (ProductName, Price, UnitStock, Deactivated)
VALUES
    ('Keyboard', 400000, 9, 0),
    ('Mouse', 100000, 9, 0),
    ('Monitor', 100000, 5, 0),
    ('Laptop', 7000000, 3, 0),
    ('Pearbook Mini', 10000000, 2, 0);

INSERT INTO Orders (OrderDate, ArrivalDate, CustomerId, DeliveryId, DeliveryFee)
VALUES
    ('2020-01-03', '2020-01-01', 1, 1, 5000);

INSERT INTO OrderDetails (OrderId, ProductId, UnitPrice, Quantity)
VALUES
    (1, 1, 400000, 1),
    (1, 2, 100000, 1);
