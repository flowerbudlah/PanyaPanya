drop database PanyaPanya; 
create database PanyaPanya; 
use PanyaPanya; 

-- 1. ȸ��
drop table panya_member_table;
create table panya_member_table(
	member_idx int not null auto_increment primary key,
	member_name  varchar(50) not null,
	member_id    varchar(100) unique not null,
	member_pw    varchar(100) not null, 
	member_email  varchar(100) unique not null,
	member_tel varchar(50) not null,
	member_address varchar(100) not null, 
	postcode int not null, 
	question varchar(500) not null, 
	answer varchar(500) not null, 
	registerDate date
);

-- 2. �Խ���
drop table board_table;
create table board_table(
  board_idx int not null primary key, 
  board_name  varchar(50) not null
);
insert into board_table values(1, '��������');
insert into board_table(board_idx, board_name) values(2, '��������');
select * from board_table;
commit;

-- 3. �Խñ�
drop table post_table;
create table post_table(
    post_idx	int not null auto_increment primary key,
    post_subject	varchar(100) not null,
    post_text	varchar(4000) not null,
    post_file	varchar(500),
    post_writer_idx	int not null,
    post_board_idx	int not null, 
    post_date	date not null, 
    post_read_count int not null DEFAULT 0, 
	foreign key(post_board_idx) references board_table(board_idx) on delete cascade, 
    foreign key(post_writer_idx) references panya_member_table(member_idx) on delete cascade
);

select * from post_table;
COMMIT;

-- 4. ���
drop table reply_table; 
create table reply_table(
	reply_idx int not null auto_increment primary key,
	post_idx int not null,   
	reply_content varchar(1000),
	replyer_id varchar(100),
	regdate date,
    foreign key(post_idx) references  post_table(post_idx) on delete cascade, 
    foreign key(replyer_id) references panya_member_table(member_id) on delete cascade
);
commit;
select * from reply_table; 

-- 5. ��ǰ
drop table product_table;
create table product_table(
    product_idx int not null auto_increment primary key,
    product_name varchar(100) not null,
    product_price int not null,
    category_idx int not null, 
    storage_method varchar(100), 
    expiration_date varchar(100), 
    product_img varchar(100)
);
insert into product_table values(1, '�ٰ�Ʈ', 2500, 1, '��º���', '�����Ϸκ��� 10��', 'baguettes.png'); 
insert into product_table values(2, '�̱��̱� ���̱�', 2500, 1, '��º���', '�����Ϸκ��� 10��', 'begel.png'); 
insert into product_table values(3, '��Ļ�	', 4000, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 7��', 'chestnut.png');    
insert into product_table values(4, '�Ƹ�� ũ��ġ��', 1500, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 5��', 'cream_cheese_amond.png'); 
insert into product_table values(5, '���� ũ��ġ��', 1500, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 5��', 'cream_cheese_apple.png');  
insert into product_table values(6, 'ũ�οͻ�', 1000, 1, '��º���', '�����Ϸκ��� 10��', 'croissant.png');   
insert into product_table values(7, 'ī����', 1000, 1, '���庸��', '�����Ϸκ��� 3��', 'curry.png'); 
insert into product_table values(8, '���信��', 1000, 1, '���庸��', '�����Ϸκ��� 3��', 'mayo_egg.png'); 
insert into product_table values(9, '��л�', 1500, 1, '��º���', '�����Ϸκ��� 10��', 'melon_basic.png'); 
insert into product_table values(10, '���� ��л�', 1500, 1, '��º���', '�����Ϸκ��� 10��', 'melon_choco.png'); 
insert into product_table values(11, '���� ��л�', 1500, 1, '��º���', '�����Ϸκ��� 10��', 'melon_matcha.png'); 
insert into product_table values(12, '�ø��� ��ƽ', 2000, 1, '��º���', '�����Ϸκ��� 10��', 'olive_stick.png');
insert into product_table values(13, '�Ļ�', 2500, 1, '��º���',  '�����Ϻ��� 10��', 'plain_bread.png');
insert into product_table values(14, '�׸�Ƽ �� ����ũ', 4000, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 5��', 'roll_greentea.png'); 
insert into product_table values(15, '���̽� �� ����ũ', 4000, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 5��', 'roll_rice.png'); 
insert into product_table values(16, '�� ����ũ ��Ʈ(5��/��Ʈ)', 10000, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 5��', 'roll_set.png');  
insert into product_table values(17, '���� ����', 1500, 1,'���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 5��', 'sesame.png'); 
insert into product_table values(18, '�ÿ���', 1500, 1, '���庸��', '�����Ϸκ��� 10��', 'sio.png'); 
insert into product_table values(19, '�߳��ҹٻ�', 3000, 1, '���庸��', '�����Ϸκ��� 3��', 'soba.png');  
insert into product_table values(20, '���ϻ�', 1500, 1, '���籤���� ���ϰ� ������ �� ����(������ ���庸��)', '�����Ϸκ��� 10��', 'sweet_redbean.png');  
insert into product_table values(21, '�ٽ�ũ ġ�� ����ũ', 10000, 2, '���庸��', '�����Ϸ� ���� 5��', 'bask_cheese_cake.png'); 
insert into product_table values(22, '���ָ� ���� ���� ����ũ', 35000, 2, '���庸��', '�����Ϸ� ���� 5��', 'chocolet_cake.png'); 
insert into product_table values(23, '�ĸ��� ũ�� ġ�� ����ũ', 35000, 2, '���庸��', '�����Ϸ� ���� 5��', 'cream_cheese_cake.png');  
insert into product_table values(24, '���� ��ũ�� ����ũ', 30000, 2, '���庸��', '�����Ϸ� ���� 5��', 'mango_cake.png'); 
insert into product_table values(25, '�Ϳ��� ��Ƽ��' , 6000, 2, '���庸��', '�����Ϸ� ���� 5��', 'puggy_cake.png'); 
insert into product_table values(26, '������ ģ����', 30000, 2, '���庸��', '�����Ϸ� ���� 5��', 'snoopy_friends_cake.png');  
insert into product_table values(27, '��Ʈ�� ���� ��ũ�� ����ũ', 30000, 2, '���庸��', '�����Ϸ� ���� 5��', 'straw_cake.png'); 
insert into product_table values(28, '��������', 2000, 3, '��º���', '�����Ϸκ��� 10��', 'butterfly_pie.png'); 
insert into product_table values(29, '���� ��ī��', 2500, 3, '��º���', '�����Ϸκ��� 10��', 'macaron_citron.png'); 
insert into product_table values(30, '��ī�� ��Ʈ(10��/��Ʈ)', 20000, 3, '��º���', '�����Ϸκ��� 10��', 'macaron_set.png'); 
insert into product_table values(31, '���ܼ�Ʈ(2��/��Ʈ)', 4000, 3, '��º���', '�����Ϸκ��� 10��', 'scone.png'); 
insert into product_table values(32, '�Ƹ����� ���� ��Ŷ ��Ʈ(10��/��Ʈ)', 6000, 3, '��º���', '�����Ϸκ��� 10��', 'thanks.png');  

alter table product_table add likeButton int default 0; 
select * from product_table order by product_idx;

-- 6. ��ٱ���
DROP TABLE CART_TABLE; 
CREATE TABLE cart_table(
    cart_idx int not null auto_increment PRIMARY KEY, 
	member_id VARCHAR(100),
	product_idx int not null,
    amount int, 
	regDate DATE, 
    FOREIGN KEY (member_id) REFERENCES panya_member_table(member_id) on delete cascade, 
    FOREIGN KEY (product_idx) REFERENCES product_table(product_idx) on delete cascade
); 

-- 7. �ֹ� 
drop table order_table;
create table order_table(
    order_idx       varchar(50) primary key, -- �ֹ�������ȣ �ε���
    member_id       varchar(100), 
    paymentPrice    int, -- ��ۺ������� �ѱݾ� 
    sender_name     varchar(50),  
    sender_address  varchar(100), 
    sender_postcode int, 
    sender_tel      varchar(50),
    recipient_name  varchar(50),
    recipient_address varchar(100), 
    recipient_postcode int,
    recipient_tel   varchar(50), 
    order_date      date, 
    memo varchar(500), 
    payment_method	varchar(50), 
    circumstance VARCHAR(50) DEFAULT '�����Ϸ�(����غ� ��)'
);
select * from order_table;

-- 8.�ֹ��� ���̺�
drop table order_detail_table;
create table order_detail_table(
    order_detail_idx	int auto_increment primary key,
    order_idx			varchar(50) not null,
    product_idx 		int,
    amount 				int,
    foreign key(order_idx) references order_table(order_idx) on delete cascade
);

select * from order_table; 
select * from order_detail_table; 