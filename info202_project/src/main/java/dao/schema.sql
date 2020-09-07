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
	listprice decimal not null,
	quantityInStock decimal not null,
	constraint Product_PK primary key (productID)
);

create table Customer (
    username varchar(15),
    firstName varchar(20) not null,
    surname varchar(20) not null,
    password varchar(30) not null,
    emailAddress varchar(30) not null,
    shippingAddress varchar(100) not null,
    constraint Customer_PK primary key (username)
);