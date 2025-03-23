CREATE DATABASE  IF NOT EXISTS `huadea` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `huadea`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: huadea
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `module_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1261 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (2,1,2),(3,1,3),(4,1,4),(6,1,6),(55,1,7),(56,1,8),(57,1,9),(58,1,10),(59,1,11),(60,1,12),(61,1,13),(62,1,14),(63,1,15),(121,1,16),(147,1,17),(275,1,99999999),(276,1,99999990),(277,1,99999980),(278,1,18),(339,1,99999970),(340,1,99999960),(343,1,99999950),(345,1,19),(346,1,20),(347,1,21),(348,1,22),(349,1,23),(350,1,24),(447,1,26),(448,1,27),(449,1,28),(847,1,29),(848,1,30),(849,1,31),(850,1,32),(863,1,800),(866,1,33),(867,1,34),(868,1,35),(869,1,36),(870,1,37),(871,1,38),(967,33,10),(968,33,31),(969,33,34),(970,33,35),(971,33,36),(972,33,37),(976,33,14),(977,33,24),(978,33,25),(979,33,15),(980,33,17),(981,33,12),(1143,36,8),(1144,36,10),(1145,36,9),(1146,36,14),(1147,36,13),(1148,36,24),(1221,32,8),(1222,32,10),(1223,32,16),(1224,32,11),(1225,32,9),(1226,32,14),(1227,32,13),(1228,32,24),(1229,32,25),(1230,32,15),(1231,32,17),(1232,32,12),(1233,32,19),(1234,32,21),(1235,32,22),(1236,32,7),(1237,32,23),(1238,32,1),(1239,32,2),(1240,32,3),(1241,32,4),(1242,32,99999999),(1243,32,99999970),(1244,32,99999960),(1245,32,99999950),(1246,37,8),(1247,37,10),(1248,37,16),(1249,37,11),(1250,37,25),(1251,37,15),(1252,37,17),(1253,37,12),(1254,37,19),(1255,37,21),(1256,37,22),(1257,37,7),(1258,37,23),(1259,37,99999999),(1260,37,99999970);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `alias` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `parent_id` int NOT NULL,
  `sort` int NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES (1,'系统管理',NULL,-1,99,'images/index/folder.png',1),(2,'用户管理','user',1,1,'images/index/user.png',1),(3,'角色管理','role',1,2,'images/index/role.png',1),(4,'操作日志','optLog',1,3,'images/index/sysLog.png',1),(6,'系统设置','sysConfig',1,5,'images/index/sysConfig.png',1),(7,'设备管理','pairDevice',22,1,'images/index/tlog.png',1),(8,'数据管理',NULL,-1,1,'images/index/folder.png',1),(9,'考核管理',NULL,-1,5,'images/index/folder.png',1),(10,'数据管理','media',8,1,'images/index/media.png',1),(11,'照片下发','mediaPush',8,4,'images/index/tlog.png',1),(12,'拍摄时间统计','timeCount',25,3,'images/index/count.png',1),(13,'单位考核','groupCount',9,2,'images/index/count.png',1),(14,'警员考核','policeCount',9,1,'images/index/count.png',1),(15,'设备统计','deviceCount',25,1,'images/index/count.png',1),(16,'执法类型','tag',8,2,'images/index/push.png',1),(17,'分类统计','typeCount',25,2,'images/index/count.png',1),(19,'站点管理',NULL,-1,9,'images/index/folder.png',1),(21,'站点日志','stationLog',19,3,'images/index/sysLog.png',1),(22,'设备管理',NULL,-1,11,'images/index/folder.png',1),(23,'设备日志','dsjLog',22,2,'images/index/sysLog.png',1),(24,'执法类型考核','policeCheck',9,3,'images/index/count.png',1),(25,'统计分析',NULL,-1,7,'images/index/folder.png',1);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `creator_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'系统超级管理员',-1),(32,'系统管理员',1),(33,'警员',1),(36,'执法人员',6),(37,'执法人员1',13);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `police_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `passwd` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `group_id` int NOT NULL,
  `role_id` int NOT NULL,
  `phone_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sex` tinyint NOT NULL DEFAULT '1',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `creator_id` int NOT NULL,
  `uid` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sn` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `disk` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE,
  KEY `police_number` (`police_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','系统超级管理员','21232f297a57a5a743894a0e4a801fc3',1,1,'',1,'',1,'','',100),(2,'1228622862','秦红兵','4d4b24dd8affa60e86fa5133444aa132',1,36,'',1,'',1,'','',500),(3,'1228692869','华中强','5acfe7f744f699f5331fc120607ca86e',1,36,'',1,'',1,NULL,NULL,100),(4,'1231353135','张明敏','843b05a27c74552a1676ccc29d0833d9',1,36,'',1,'',1,NULL,NULL,100),(5,'1228472847','汪道发','e8da58d28c0b33ed418847b3092d2ea1',1,36,'',1,'',1,NULL,NULL,100),(8,'1228642864','罗瑞斌','f5165854247abcb1fb51b94e722e27a6',1,36,'',1,'',1,NULL,NULL,100),(9,'1228272827','皮庆远','99e9817a34140b6996a16913c8a57fcb',1,36,'13986772149',1,'',6,NULL,NULL,100),(10,'1231313131','唐义群','31b7f329f2d714a796dc9b3fdc165e7c',1,36,'',1,'',1,NULL,NULL,100),(13,'1209800980','俞俊杰','43068e2b0b0bae4bf54e2272ac50094e',1,32,'',1,'',1,NULL,NULL,100),(14,'1240124012','姜云蛟','58fca7d2f2da60d0be73740291d2497a',1,37,'',1,'',13,NULL,NULL,100),(15,'1238303830','张中原','cbaa9556f1122de7599dbab80f83e021',1,36,'',1,'',1,NULL,NULL,100),(16,'1234743474','袁忠良','32f6baa7ef345cea9b2b4597a6299eda',1,36,'',1,'',1,NULL,NULL,100),(17,'1238263826','刘邵钦','bcd5e756fd960c2c6f7d1353dbbbf062',1,37,'',1,'',16,NULL,NULL,100),(18,'1209870987','严卫东','5ea7a9eb06778875cefce1ca698e25a3',1,37,'',1,'',16,NULL,NULL,100),(19,'1239223922','陈勇','3e4a9f41349d5d134b194bc08cebad1a',1,37,'',1,'',15,NULL,NULL,100),(21,'1209400940','谈应宁','1cb27532d9f8b1d4e80e11a5cfbc998e',1,33,'',1,'',1,NULL,NULL,100),(22,'1209860986','郑军民','bc37dec4a53bcc5c87119bad76d950ca',1,33,'',1,'',1,NULL,NULL,100),(23,'1209270927','端木志刚','716d9a4fa1232016fb572d9e8448c5c1',1,33,'',1,'',1,NULL,NULL,100),(24,'1241904190','彭仁政','e3d42be3e87fea89ca5f861ca624b038',1,32,'',1,'',1,NULL,NULL,100),(25,'1238513851','陈池章','58181637f679cc23c028c21d0c995eea',1,32,'',1,'',1,NULL,NULL,100),(29,'999888','陈章','$2a$10$PAnPA0KwKiQaE01uJZu/2.6qmkPn0gxvfaUaLuNt/LM90iixOlR7a',1,32,NULL,1,NULL,1,NULL,NULL,100);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-23 17:14:57
