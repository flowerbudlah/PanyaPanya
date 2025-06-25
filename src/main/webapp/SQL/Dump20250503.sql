-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: hopper.proxy.rlwy.net    Database: railway
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board_table`
--

DROP TABLE IF EXISTS `board_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_table` (
  `board_idx` int NOT NULL,
  `board_name` varchar(50) NOT NULL,
  PRIMARY KEY (`board_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_table`
--

LOCK TABLES `board_table` WRITE;
/*!40000 ALTER TABLE `board_table` DISABLE KEYS */;
INSERT INTO `board_table` VALUES (1,'공지사항'),(2,'고객센터');
/*!40000 ALTER TABLE `board_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_table`
--

DROP TABLE IF EXISTS `cart_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_table` (
  `cart_idx` int NOT NULL AUTO_INCREMENT,
  `member_id` varchar(100) DEFAULT NULL,
  `product_idx` int NOT NULL,
  `amount` int DEFAULT NULL,
  `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_idx`),
  KEY `member_id` (`member_id`),
  KEY `product_idx` (`product_idx`),
  CONSTRAINT `cart_table_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `panya_member_table` (`member_id`) ON DELETE CASCADE,
  CONSTRAINT `cart_table_ibfk_2` FOREIGN KEY (`product_idx`) REFERENCES `product_table` (`product_idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_table`
--

LOCK TABLES `cart_table` WRITE;
/*!40000 ALTER TABLE `cart_table` DISABLE KEYS */;
INSERT INTO `cart_table` VALUES (18,'admin',1,3,'2025-02-06 12:53:39'),(19,'admin',2,4,'2025-02-08 11:40:15'),(24,'admin',17,7,'2025-02-20 11:11:41'),(28,'gomdunge',31,1,'2025-04-30 10:09:09'),(29,'gomdunge',27,1,'2025-04-30 10:09:35'),(30,'gomdunge',18,5,'2025-04-30 10:09:53'),(31,'gomdunge',38,1,'2025-05-02 12:38:44'),(32,'gomdunge',19,1,'2025-05-02 13:57:53'),(33,'gomdunge',29,10,'2025-05-02 13:58:12'),(36,'admin',23,1,'2025-05-02 14:04:26'),(37,'lky',25,1,'2025-05-03 04:11:59');
/*!40000 ALTER TABLE `cart_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail_table`
--

DROP TABLE IF EXISTS `order_detail_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail_table` (
  `order_detail_idx` int NOT NULL AUTO_INCREMENT,
  `order_idx` varchar(50) NOT NULL,
  `product_idx` int DEFAULT NULL,
  `amount` int DEFAULT NULL,
  PRIMARY KEY (`order_detail_idx`),
  KEY `order_idx` (`order_idx`),
  CONSTRAINT `order_detail_table_ibfk_1` FOREIGN KEY (`order_idx`) REFERENCES `order_table` (`order_idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail_table`
--

LOCK TABLES `order_detail_table` WRITE;
/*!40000 ALTER TABLE `order_detail_table` DISABLE KEYS */;
INSERT INTO `order_detail_table` VALUES (17,'20250204_070671',11,2),(18,'20250204_070671',2,1),(19,'20250204_070671',21,1),(20,'20250211_229093',2,3),(21,'20250211_229093',15,1),(22,'20250211_229093',22,2),(23,'20250430_561946',38,1),(24,'20250430_561946',26,1),(26,'20250502_595550',11,1),(27,'20250502_595550',14,1);
/*!40000 ALTER TABLE `order_detail_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_table`
--

DROP TABLE IF EXISTS `order_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_table` (
  `order_idx` varchar(50) NOT NULL,
  `member_id` varchar(100) DEFAULT NULL,
  `paymentPrice` int DEFAULT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
  `sender_address` varchar(100) DEFAULT NULL,
  `sender_postcode` int DEFAULT NULL,
  `sender_tel` varchar(50) DEFAULT NULL,
  `recipient_name` varchar(50) DEFAULT NULL,
  `recipient_address` varchar(100) DEFAULT NULL,
  `recipient_postcode` int DEFAULT NULL,
  `recipient_tel` varchar(50) DEFAULT NULL,
  `order_date` timestamp NULL DEFAULT NULL,
  `memo` varchar(500) DEFAULT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `circumstance` varchar(50) DEFAULT '결제완료(배송준비 전)',
  PRIMARY KEY (`order_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_table`
--

LOCK TABLES `order_table` WRITE;
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
INSERT INTO `order_table` VALUES ('20250204_070671','flowerbudlah',18500,'이아현','부산 해운대구 청사포로 87',48116,'09013529508','곰둥','제주특별자치도 서귀포시 가가로 44-7',63534,'01058249508','2025-02-04 11:32:55','냉동포장 부탁드립니다. ','계좌이체','배송중'),('20250211_229093','flowerbudlah',84500,'러키','경기 안성시 가사길 7-5',17585,'01022167746','이아현','서울 성북구 대사관로1길 32',2822,'01022167746','2025-02-11 02:41:58','조심스럽게 배송해주세요!','신용카드','결제완료(배송준비 전)'),('20250430_561946','gomdung',63000,'장곰둥','경기 의정부시 충의로57번길 15',11789,'1234-5678','광자','광주 남구 대남대로 321',61613,'010-1234-5678','2025-04-30 09:39:04','','계좌이체','결제완료(배송준비 전)'),('20250502_595550','gomdung',8500,'장곰둥','충남 천안시 서북구 원두정1길 7',31107,'010-2216-7746','이영임','경기 김포시 감암로 1',10099,'010-1234-5678','2025-05-02 14:02:00','','계좌이체','결제완료(배송준비 전)');
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panya_member_table`
--

DROP TABLE IF EXISTS `panya_member_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `panya_member_table` (
  `member_idx` int NOT NULL AUTO_INCREMENT,
  `member_name` varchar(50) NOT NULL,
  `member_id` varchar(100) NOT NULL,
  `member_pw` varchar(100) NOT NULL,
  `member_email` varchar(100) NOT NULL,
  `member_tel` varchar(50) NOT NULL,
  `member_address` varchar(100) NOT NULL,
  `postcode` int NOT NULL,
  `question` varchar(500) NOT NULL,
  `answer` varchar(500) NOT NULL,
  `registerDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`member_idx`),
  UNIQUE KEY `member_id` (`member_id`),
  UNIQUE KEY `member_email` (`member_email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panya_member_table`
--

LOCK TABLES `panya_member_table` WRITE;
/*!40000 ALTER TABLE `panya_member_table` DISABLE KEYS */;
INSERT INTO `panya_member_table` VALUES (1,'관리자','admin','1111','flowerbudlah_project@naver.com','01058249508','인천 서구 서곶로 12, 한신 그랜드 힐 빌리지 22동 507호',22797,'감명깊게 본 책은 무엇인가요?','관세사','2023-03-18 00:00:00'),(2,'이아현','flowerbudlah','1111','flowerbudlah@nate.com','01058249508','인천 서구 서곶로 12, 한신 그랜드 힐 빌리지 22동 507호 ',22797,'별명은 무엇인가요?','러키맘','2023-03-20 00:00:00'),(3,'이러키','lky','1111','flowerbudlah@gmail','01058249508','부산 해운대구 달맞이길 184',48116,'별명은 무엇인가요?','포메','2025-02-03 00:00:00'),(4,'곰둥이','gomdung','1111','flowerbudah@nate.com','01058249508','경기 용인시 수지구 달맞이로 42',16874,'별명은 무엇인가요?','비숑프리제','2025-02-03 00:00:00'),(5,'이곰둥','gomdunge','1111','gdi@nate.com','09013529508','인천 중구 신도시남로141번길 6',22371,'고향은 어디인가요?','광주광역시','2025-02-09 00:00:00');
/*!40000 ALTER TABLE `panya_member_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_table`
--

DROP TABLE IF EXISTS `post_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_table` (
  `post_idx` int NOT NULL AUTO_INCREMENT,
  `post_subject` varchar(100) NOT NULL,
  `post_text` varchar(4000) NOT NULL,
  `post_file` varchar(500) DEFAULT NULL,
  `post_writer_idx` int NOT NULL,
  `post_board_idx` int NOT NULL,
  `post_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `post_read_count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`post_idx`),
  KEY `post_board_idx` (`post_board_idx`),
  KEY `post_writer_idx` (`post_writer_idx`),
  CONSTRAINT `post_table_ibfk_1` FOREIGN KEY (`post_board_idx`) REFERENCES `board_table` (`board_idx`) ON DELETE CASCADE,
  CONSTRAINT `post_table_ibfk_2` FOREIGN KEY (`post_writer_idx`) REFERENCES `panya_member_table` (`member_idx`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_table`
--

LOCK TABLES `post_table` WRITE;
/*!40000 ALTER TABLE `post_table` DISABLE KEYS */;
INSERT INTO `post_table` VALUES (1,'베이커리 쇼핑몰 팡야팡야','어서오세요! \r\n',NULL,2,2,'2023-03-21 07:13:29',42),(2,'사진 변경 ','우왕 ','https://i.imgur.com/BSBzQwH.jpeg',2,2,'2023-03-21 08:40:13',71),(3,'[공지사항] 관리자 ID: admin 비밀번호: 1111','관리자모드로 로그인해서 이 쇼핑몰을 살펴봐주세요! \r\n\r\n\r\n\r\nPlease, Sign in as administrator to confirm this shopping mall.\r\n\r\nID: admin\r\nPassword: 1111',NULL,1,1,'2023-05-06 11:53:05',19),(4,'[お知らせ]Admin login is required. ','このサイトの機能を確認するため、\r\n管理者モードでログインする必要があります。 \r\n\r\n管理者IDは　admin\r\nパスワードは　1111です。\r\n\r\n何卒宜しくお願い致します。',NULL,1,1,'2025-04-29 12:26:34',3),(5,'お問い合わせはこちらです。','こちらはお問い合わせ窓口です。\r\n早急に対応いたします。',NULL,3,2,'2025-05-03 06:08:29',1);
/*!40000 ALTER TABLE `post_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_reply_table`
--

DROP TABLE IF EXISTS `product_reply_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reply_table` (
  `product_reply_idx` int NOT NULL AUTO_INCREMENT,
  `product_idx` int DEFAULT NULL,
  `product_reply_content` varchar(1000) DEFAULT NULL,
  `product_replyer_id` varchar(100) DEFAULT NULL,
  `regdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_reply_idx`),
  KEY `product_idx` (`product_idx`),
  KEY `product_replyer_id` (`product_replyer_id`),
  CONSTRAINT `product_reply_table_ibfk_1` FOREIGN KEY (`product_idx`) REFERENCES `product_table` (`product_idx`) ON DELETE CASCADE,
  CONSTRAINT `product_reply_table_ibfk_2` FOREIGN KEY (`product_replyer_id`) REFERENCES `panya_member_table` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reply_table`
--

LOCK TABLES `product_reply_table` WRITE;
/*!40000 ALTER TABLE `product_reply_table` DISABLE KEYS */;
INSERT INTO `product_reply_table` VALUES (1,1,'댓글 작성 테스트','flowerbudlah','2025-04-28 10:02:11'),(2,1,'센다이 하코다테 아바리시 아오모리','flowerbudlah','2025-04-28 10:02:54'),(3,2,'런던 베이글 가고싶어요. 대파크림 치즈 베이글 먹고싶어요. ','gomdung','2025-04-30 09:49:12'),(4,2,'중국 진시황제 무덤 보러갈거에요!','gomdung','2025-04-30 09:51:08');
/*!40000 ALTER TABLE `product_reply_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_rereply_table`
--

DROP TABLE IF EXISTS `product_rereply_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_rereply_table` (
  `product_rereply_idx` int NOT NULL AUTO_INCREMENT,
  `product_reply_idx` int NOT NULL,
  `product_idx` int NOT NULL,
  `product_rereply_content` varchar(1000) NOT NULL,
  `product_rereplyer_id` varchar(100) NOT NULL,
  `regdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`product_rereply_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_rereply_table`
--

LOCK TABLES `product_rereply_table` WRITE;
/*!40000 ALTER TABLE `product_rereply_table` DISABLE KEYS */;
INSERT INTO `product_rereply_table` VALUES (1,2,1,'이집트 그리스','flowerbudlah','2025-04-28 10:03:08'),(2,2,1,'이스라엘 요르단 성지순례','flowerbudlah','2025-04-28 10:03:26'),(3,1,1,'Bye Bye','gomdunge','2025-04-28 10:15:11'),(4,3,2,'센다이, 홋카이도 아바리시, 하코다테, 와카야마 고야인 나치폭포','gomdung','2025-04-30 09:50:31'),(5,3,2,'그리스, 이집트 갈거에요!','gomdung','2025-04-30 09:50:49'),(6,2,1,'Hello, Gomdung! ','gomdunge','2025-04-30 10:10:45');
/*!40000 ALTER TABLE `product_rereply_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_table`
--

DROP TABLE IF EXISTS `product_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_table` (
  `product_idx` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_price` int NOT NULL,
  `category_idx` int NOT NULL,
  `storage_method` varchar(100) DEFAULT NULL,
  `expiration_date` varchar(100) DEFAULT NULL,
  `product_img` varchar(100) DEFAULT NULL,
  `likeButton` int DEFAULT '0',
  PRIMARY KEY (`product_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_table`
--

LOCK TABLES `product_table` WRITE;
/*!40000 ALTER TABLE `product_table` DISABLE KEYS */;
INSERT INTO `product_table` VALUES (1,'바게트',3000,1,'상온보관','제조일로부터 10일','baguettes.png',10),(2,'쫄깃쫄깃 베이글',2500,1,'상온보관','제조일로부터 10일','begel.png',13),(3,'밤식빵	',4000,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 7일','chestnut.png',1),(4,'아몬드 크림치즈',1500,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 5일','cream_cheese_amond.png',2),(5,'애플 크림치즈',1500,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 5일','cream_cheese_apple.png',4),(6,'크로와상',1000,1,'상온보관','제조일로부터 10일','croissant.png',0),(7,'카레빵',1000,1,'냉장보관','제조일로부터 3일','curry.png',0),(8,'마요에그',1000,1,'냉장보관','제조일로부터 3일','mayo_egg.png',1),(9,'멜론빵',1500,1,'상온보관','제조일로부터 10일','melon_basic.png',0),(10,'초코 멜론빵',1500,1,'상온보관','제조일로부터 10일','melon_choco.png',0),(11,'맛차 멜론빵',1500,1,'상온보관','제조일로부터 10일','melon_matcha.png',0),(12,'올리브 스틱',2000,1,'상온보관','제조일로부터 10일','olive_stick.png',0),(13,'식빵',2500,1,'상온보관','제조일부터 10일','plain_bread.png',0),(14,'그린티 롤 케이크',4000,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 5일','roll_greentea.png',0),(15,'라이스 롤 케이크',4000,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 5일','roll_rice.png',1),(16,'롤 케이크 세트(5개/세트)',10000,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 5일','roll_set.png',0),(17,'참깨 만쥬',1500,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 5일','sesame.png',0),(18,'소금빵',1500,1,'냉장보관','제조일로부터 10일','sio.png',0),(19,'야끼소바빵',3000,1,'냉장보관','제조일로부터 3일','soba.png',0),(20,'단팥빵',1500,1,'직사광선을 피하고 서늘한 곳 보관(하절기 냉장보관)','제조일로부터 10일','sweet_redbean.png',1),(21,'바스크 치즈 케이크',10000,2,'냉장보관','제조일로 부터 5일','bask_cheese_cake.png',1),(22,'진주를 먹은 초코 케이크',35000,2,'냉장보관','제조일로 부터 5일','chocolet_cake.png',2),(23,'후르츠 크림 치즈 케이크',35000,2,'냉장보관','제조일로 부터 5일','cream_cheese_cake.png',0),(24,'망고 생크림 케이크',30000,2,'냉장보관','제조일로 부터 5일','mango_cake.png',1),(25,'귀여운 말티즈',6000,2,'냉장보관','제조일로 부터 5일','puggy_cake.png',0),(26,'찰리와 친구들',30000,2,'냉장보관','제조일로 부터 5일','snoopy_friends_cake.png',1),(27,'스트로 베리 생크림 케이크',30000,2,'냉장보관','제조일로 부터 5일','straw_cake.png',1),(28,'나비파이',2000,3,'상온보관','제조일로부터 10일','butterfly_pie.png',0),(29,'유자 마카롱',2500,3,'상온보관','제조일로부터 10일','macaron_citron.png',0),(30,'마카롱 세트(10개/세트)',20000,3,'상온보관','제조일로부터 10일','macaron_set.png',0),(31,'스콘세트(2개/세트)',4000,3,'상온보관','제조일로부터 10일','scone.png',0),(32,'아리가토 수제 비스킷 세트(10개/세트)',6000,3,'상온보관','제조일로부터 10일','thanks.png',1),(38,'샤퀴테리 플래터',30000,3,'냉동','제조일로부터 5일','https://i.imgur.com/guABMMO.jpeg',2);
/*!40000 ALTER TABLE `product_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_table`
--

DROP TABLE IF EXISTS `reply_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply_table` (
  `reply_idx` int NOT NULL AUTO_INCREMENT,
  `post_idx` int NOT NULL,
  `reply_content` varchar(1000) DEFAULT NULL,
  `replyer_id` varchar(100) DEFAULT NULL,
  `regdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reply_idx`),
  KEY `post_idx` (`post_idx`),
  KEY `replyer_id` (`replyer_id`),
  CONSTRAINT `reply_table_ibfk_1` FOREIGN KEY (`post_idx`) REFERENCES `post_table` (`post_idx`) ON DELETE CASCADE,
  CONSTRAINT `reply_table_ibfk_2` FOREIGN KEY (`replyer_id`) REFERENCES `panya_member_table` (`member_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_table`
--

LOCK TABLES `reply_table` WRITE;
/*!40000 ALTER TABLE `reply_table` DISABLE KEYS */;
INSERT INTO `reply_table` VALUES (1,2,'test','admin','2023-03-22 02:34:18'),(2,1,'1','flowerbudlah','2023-04-04 08:38:13'),(3,1,'2','flowerbudlah','2023-04-04 08:38:17'),(4,1,'3','flowerbudlah','2023-04-04 08:38:21'),(8,1,'7','flowerbudlah','2023-04-04 08:38:37'),(9,1,'8','flowerbudlah','2023-04-04 08:38:40'),(12,2,'사랑스러워 말랑이!','flowerbudlah','2025-02-04 11:26:56'),(13,2,'안녕?','gomdung','2025-04-27 08:00:52');
/*!40000 ALTER TABLE `reply_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-03 15:52:38
