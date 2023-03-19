drop database PanyaPanya; 
create database PanyaPanya; 
use PanyaPanya; 

-- 1. 회원
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
select * from panya_member_table;


-- 2. 게시판
drop table board_table;
create table board_table(
  board_idx int not null primary key, 
  board_name  varchar(50) not null
);
insert into board_table values(1, '공지사항');
insert into board_table(board_idx, board_name) values(2, '고객센터');
select * from board_table;
commit;

-- 3. 게시글
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

-- 4. 댓글
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

-- 5. 상품
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
insert into product_table values(1, '바게트', 2500, 1, '상온보관', '제조일로부터 10일', 'baguettes.png'); 
insert into product_table values(2, '쫄깃쫄깃 베이글', 2500, 1, '상온보관', '제조일로부터 10일', 'begel.png'); 
insert into product_table values(3, '밤식빵	', 4000, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 7일', 'chestnut.png');    
insert into product_table values(4, '아몬드 크림치즈', 1500, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 5일', 'cream_cheese_amond.png'); 
insert into product_table values(5, '애플 크림치즈', 1500, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 5일', 'cream_cheese_apple.png');  
insert into product_table values(6, '크로와상', 1000, 1, '상온보관', '제조일로부터 10일', 'croissant.png');   
insert into product_table values(7, '카레빵', 1000, 1, '냉장보관', '제조일로부터 3일', 'curry.png'); 
insert into product_table values(8, '마요에그', 1000, 1, '냉장보관', '제조일로부터 3일', 'mayo_egg.png'); 
insert into product_table values(9, '멜론빵', 1500, 1, '상온보관', '제조일로부터 10일', 'melon_basic.png'); 
insert into product_table values(10, '초코 멜론빵', 1500, 1, '상온보관', '제조일로부터 10일', 'melon_choco.png'); 
insert into product_table values(11, '맛차 멜론빵', 1500, 1, '상온보관', '제조일로부터 10일', 'melon_matcha.png'); 
insert into product_table values(12, '올리브 스틱', 2000, 1, '상온보관', '제조일로부터 10일', 'olive_stick.png');
insert into product_table values(13, '식빵', 2500, 1, '상온보관',  '제조일부터 10일', 'plain_bread.png');
insert into product_table values(14, '그린티 롤 케이크', 4000, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 5일', 'roll_greentea.png'); 
insert into product_table values(15, '라이스 롤 케이크', 4000, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 5일', 'roll_rice.png'); 
insert into product_table values(16, '롤 케이크 세트(5개/세트)', 10000, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 5일', 'roll_set.png');  
insert into product_table values(17, '참깨 만쥬', 1500, 1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 5일', 'sesame.png'); 
insert into product_table values(18, '시오빵', 1500, 1, '냉장보관', '제조일로부터 10일', 'sio.png'); 
insert into product_table values(19, '야끼소바빵', 3000, 1, '냉장보관', '제조일로부터 3일', 'soba.png');  
insert into product_table values(20, '단팥빵', 1500, 1, '직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)', '제조일로부터 10일', 'sweet_redbean.png');  
insert into product_table values(21, '바스크 치즈 케이크', 10000, 2, '냉장보관', '제조일로 부터 5일', 'bask_cheese_cake.png'); 
insert into product_table values(22, '진주를 먹은 초코 케이크', 35000, 2, '냉장보관', '제조일로 부터 5일', 'chocolet_cake.png'); 
insert into product_table values(23, '후르츠 크림 치즈 케이크', 35000, 2, '냉장보관', '제조일로 부터 5일', 'cream_cheese_cake.png');  
insert into product_table values(24, '망고 생크림 케이크', 30000, 2, '냉장보관', '제조일로 부터 5일', 'mango_cake.png'); 
insert into product_table values(25, '귀여운 말티즈' , 6000, 2, '냉장보관', '제조일로 부터 5일', 'puggy_cake.png'); 
insert into product_table values(26, '찰리와 친구들', 30000, 2, '냉장보관', '제조일로 부터 5일', 'snoopy_friends_cake.png');  
insert into product_table values(27, '스트로 베리 생크림 케이크', 30000, 2, '냉장보관', '제조일로 부터 5일', 'straw_cake.png'); 
insert into product_table values(28, '나비파이', 2000, 3, '상온보관', '제조일로부터 10일', 'butterfly_pie.png'); 
insert into product_table values(29, '유자 마카롱', 2500, 3, '상온보관', '제조일로부터 10일', 'macaron_citron.png'); 
insert into product_table values(30, '마카롱 세트(10개/세트)', 20000, 3, '상온보관', '제조일로부터 10일', 'macaron_set.png'); 
insert into product_table values(31, '스콘세트(2개/세트)', 4000, 3, '상온보관', '제조일로부터 10일', 'scone.png'); 
insert into product_table values(32, '아리가토 수제 비스킷 세트(10개/세트)', 6000, 3, '상온보관', '제조일로부터 10일', 'thanks.png');  

alter table product_table add likeButton int default 0; 
select * from product_table order by product_idx;

-- 6. 장바구니
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

-- 7. 주문 
drop table order_table;
create table order_table(
    order_idx       varchar(50) primary key, -- 주문결제번호 인덱스
    member_id       varchar(100), 
    paymentPrice    int, -- 배송비제외한 총금액 
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
    circumstance VARCHAR(50) DEFAULT '결제완료(배송준비 전)'
);
select * from order_table;

-- 8.주문상세 테이블
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
