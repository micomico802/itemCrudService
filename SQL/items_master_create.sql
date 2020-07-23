create database test_db;

/*
データベース作成後はtest_dbに接続しなおしてから、
以下のテーブル構築を行うこと。
*/

create table items_master(
	item_id int not null primary key AUTO_INCREMENT,
	item_name varchar(255) not null,
	maker_name varchar(255) not null,
	price int not null,
	created_at datetime default current_timestamp,
	updated_at datetime default current_timestamp on update current_timestamp,
	deleted_at datetime default null	
);

INSERT INTO items_master(item_name, maker_name, price) VALUES('VAIO1', 'sony', 50000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('VAIO2', 'sony', 100000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('VAIO3', 'sony', 150000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('VAIO4', 'sony', 200000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('VAIO5', 'sony', 250000);

INSERT INTO items_master(item_name, maker_name, price) VALUES('MACBOOK1', 'apple', 50000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('MACBOOK2', 'apple', 100000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('MACBOOK3', 'apple', 150000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('MACBOOK4', 'apple', 200000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('MACBOOK5', 'apple', 250000);

INSERT INTO items_master(item_name, maker_name, price) VALUES('LAVIE1', 'nec', 50000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('LAVIE2', 'nec', 100000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('LAVIE3', 'nec', 150000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('LAVIE4', 'nec', 200000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('LAVIE5', 'nec', 250000);

INSERT INTO items_master(item_name, maker_name, price) VALUES('SURFACE1', 'microsoft', 50000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('SURFACE2', 'microsoft', 100000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('SURFACE3', 'microsoft', 150000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('SURFACE4', 'microsoft', 200000);
INSERT INTO items_master(item_name, maker_name, price) VALUES('SURFACE5', 'microsoft', 250000);


	
