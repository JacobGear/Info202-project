/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  geaja121
 * Created: 4/08/2020
 */

create table Product (
	productID varchar(50),
	name varchar(50) not null,
	description varchar(50) not null,
	category varchar(20) not null,
	listprice decimal(4,2) not null,
	quantityInStock int not null,
	constraint Product_PK primary key (productID)
);

create table Customer (
    customerID bigint auto_increment(1000),
    username varchar(15) unique,
    firstName varchar(20) not null,
    surname varchar(20) not null,
    password varchar(30) not null,
    emailAddress varchar(30) not null,
    shippingAddress varchar(100),
    constraint Customer_PK primary key (customerID)
);

create table Sale (
    saleID decimal auto_increment(500),
    customerID bigint,
    date timestamp default current_timestamp not null,
    status varchar(100),
    constraint Sale_PK primary key (saleID),
    constraint Sale_Customer foreign key (customerID) references Customer
);

create table SaleItem (
    saleID decimal not null,
    productID varchar(50), 
    quantityPurchased bigint not null,
    salePrice decimal(4,2) not null,       
    constraint SaleItem_PK primary key (saleID, productID),
    constraint SaleItem_Product foreign key (productID) references Product,
    constraint SaleItem_Sale foreign key (saleID) references Sale
);


insert into sale (saleID, customerID, date, status) values (default, 1234, default, 'status');

INSERT INTO Customer (username, firstName, surname, password, emailAddress, shippingAddress)
VALUES ('boris', 'Boris', 'McNorris', 'guest', 'boris@example.net', '123 Some Street, North East Valley Dunedin');

INSERT INTO Customer (username, firstName, surname, password, emailAddress, shippingAddress)
VALUES ('doris', 'Doris', 'Dolores', 'guest', 'doris@example.net', '321 Anywere Ave, St Clair, Dunedin');

