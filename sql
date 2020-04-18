-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: community
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `commentator` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) NOT NULL,
  `like_count` bigint(20) DEFAULT '0',
  `content` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,2,1,7,1586486914914,1586486914914,0,'测试回复'),(2,4,1,7,1586489704195,1586489704195,0,'aaa'),(3,4,1,7,1586489704195,1586489704195,0,'aaa'),(4,4,1,7,1586489704195,1586489704195,0,'aaa'),(5,4,1,7,1586489704195,1586489704195,0,'aaa'),(6,4,1,7,1586489704195,1586489704195,0,'aaa'),(7,4,1,7,1586489704195,1586489704195,0,'aaa'),(8,4,1,7,1586489704195,1586489704195,0,'aaa'),(9,18,1,7,1586501636806,1586501636806,0,'我要回复你'),(10,18,1,7,1586502316703,1586502316703,0,'再回复一次吧'),(11,18,1,7,1586503074302,1586503074302,0,''),(12,18,1,7,1586503831980,1586503831980,0,''),(13,18,1,7,1586503953216,1586503953216,0,''),(14,18,1,7,1586505398616,1586505398616,0,'回复测试'),(15,18,1,7,1586505611668,1586505611668,0,'时间测试'),(16,18,1,7,1586517339236,1586517339236,0,'还是一条测试'),(17,16,2,7,1586571877736,1586571877736,0,'二级评论测试'),(18,16,2,7,1586584296212,1586584296212,0,'测试'),(19,16,2,7,1586584332926,1586584332926,0,'测试二'),(23,18,1,7,1586590888313,1586590888313,0,'测试'),(27,23,2,7,1586591833480,1586591833480,0,'回复'),(28,23,2,7,1586592784235,1586592784235,0,'ceshi'),(29,3,1,7,1586772496821,1586772496821,0,'通知消息测试'),(30,29,2,7,1586772525129,1586772525129,0,'通知消息测试'),(31,29,2,7,1586827677196,1586827677196,0,'程序员的小宇宙'),(32,3,2,7,1586830713038,1586830713038,0,'bbb'),(33,3,1,7,1586831635069,1586831635069,0,'aaa'),(34,33,2,7,1586831642701,1586831642701,0,'111'),(35,29,2,7,1586832354703,1586832354703,0,'测试'),(36,23,2,7,1586864372420,1586864372420,0,'回复');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) NOT NULL COMMENT '通知者\n',
  `receiver` int(11) NOT NULL COMMENT '被通知者',
  `type` int(11) NOT NULL COMMENT '标识回复的问题还是评论',
  `status` int(11) DEFAULT '0' COMMENT '''0''代表未读\n‘1’代表已读',
  `gmt_create` bigint(20) NOT NULL,
  `outer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,7,7,1,1,1586771177102,20),(2,7,7,1,1,1586771405814,19),(3,7,7,1,1,1586771661175,19),(4,7,7,1,1,1586771661175,19),(5,7,7,1,1,1586772496821,3),(6,7,7,2,1,1586772525129,29),(7,7,7,2,1,1586827677196,29),(8,7,7,2,1,1586830713038,3),(9,7,7,1,1,1586831635069,3),(10,7,7,2,1,1586831642701,33),(11,7,7,2,1,1586832354703,29);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8_unicode_ci,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (2,'怎么快速学会Spring Boot开发','测试修改问题',1585898058021,1586230042781,7,6,79,0,'springboot'),(3,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,4,31,0,'springboot'),(4,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,1,13,0,'springboot'),(5,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,2,0,'springboot'),(6,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,3,0,'springboot'),(7,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(8,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(9,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,1,0,'springboot'),(10,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(11,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(12,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,1,0,'springboot'),(13,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(14,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(15,'怎么快速学会Spring Boot开发','点击进入看 小匠 录制的视频',1585898058021,1585898058021,7,0,0,0,'springboot'),(16,'测试拦截器','springboot 拦截器测试',1586149246479,1586149246479,7,0,4,0,'springboot'),(17,'测试','测试发布功能是否正常',1586230191022,1586230191022,7,0,6,0,'测试'),(18,'测试一哈','如题：测试一哈',1586501616217,1586594094197,7,9,200,0,'测试,springboot,java'),(19,'正则表达式','正则表达式匹配规则',1586746070830,1586746070830,7,0,14,0,'java,java-ee'),(20,'测试一哈','如题：测试一哈',1586747468911,1586747468911,7,0,23,0,'java,intellij-idea'),(21,'测试','```javascript\r\nfunction test(){\r\n	console.log(\"Hello world!\");\r\n}\r\n \r\n(function(){\r\n    var box = function(){\r\n        return box.fn.init();\r\n    };\r\n\r\n    box.prototype = box.fn = {\r\n        init : function(){\r\n            console.log(\'box.init()\');\r\n\r\n			return this;\r\n        },\r\n\r\n		add : function(str){\r\n			alert(\"add\", str);\r\n\r\n			return this;\r\n		},\r\n\r\n		remove : function(str){\r\n			alert(\"remove\", str);\r\n\r\n			return this;\r\n		}\r\n    };\r\n    \r\n    box.fn.init.prototype = box.fn;\r\n    \r\n    window.box =box;\r\n})();\r\n\r\nvar testBox = box();\r\ntestBox.add(\"jQuery\").remove(\"jQuery\");\r\n```',1586836705969,1586836705969,7,0,3,0,'javascript'),(22,'帮忙看看我这段代码有什么问题','![](/images/wechat.jpg)\r\n![](http://smallcosmos.oss-cn-beijing.aliyuncs.com/hmily/communnity/0010244e3696460ebca01df9349cf7be.png?Expires=1587180132&OSSAccessKeyId=LTAI4FcnPBfgNQY3PMZcnDgU&Signature=8%2BWnIPBedHWKYru432TxkIbhRuc%3D)\r\n```java\r\npublic boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\r\n        Cookie[] cookies = request.getCookies();\r\n        if (cookies != null && cookies.length > 0) {\r\n            for (Cookie cookie : cookies) {\r\n                if (\"token\".equals(cookie.getName())) {\r\n                    String token = cookie.getValue();\r\n                    User user = userService.getUserByToken(token);\r\n                    request.getSession().setAttribute(\"user\", user);\r\n                    break;\r\n                }\r\n            }\r\n        }\r\n        return true;\r\n    }\r\n```',1586836980374,1586864788684,7,0,17,0,'java,intellij-idea');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `category_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `category_tag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'001','Java开发','java'),(2,'001','Java开发','java-ee'),(3,'001','Java开发','jar'),(4,'001','Java开发','spring'),(5,'001','Java开发','hibernate\n'),(6,'001','Java开发','struts'),(7,'001','Java开发','tomcat'),(8,'001','Java开发','maven'),(9,'001','Java开发','eclipse'),(10,'001','Java开发','intellij-idea'),(11,'002','开发语言','java'),(12,'002','开发语言','c'),(13,'002','开发语言','c++'),(14,'002','开发语言','php'),(15,'002','开发语言','perl'),(16,'002','开发语言','python'),(17,'002','开发语言','javascript'),(18,'002','开发语言','c#'),(19,'002','开发语言','go'),(20,'002','开发语言','lua'),(21,'002','开发语言','node.js'),(22,'002','开发语言','erlang'),(23,'002','开发语言','scala'),(24,'002','开发语言','bash'),(25,'002','开发语言','actionscript'),(26,'002','开发语言','golang'),(27,'002','开发语言','typescript'),(28,'002','开发语言','flutter'),(29,'003','数据库','mysql'),(30,'003','数据库','sqlite'),(31,'003','数据库','oracle'),(32,'003','数据库','sql'),(33,'003','数据库','nosql'),(34,'003','数据库','redis'),(35,'003','数据库','mongodb'),(36,'003','数据库','postgresql'),(37,'004','Python开发','python'),(38,'004','Python开发','List'),(39,'004','Python开发','django'),(40,'004','Python开发','flask'),(41,'004','Python开发','List'),(42,'004','Python开发','django'),(43,'004','Python开发','flask'),(44,'004','Python开发','tornado'),(45,'004','Python开发','web.py'),(46,'004','Python开发','sqlalchemy'),(47,'004','Python开发','virtualenv'),(48,'005','前端开发','html\n'),(49,'005','前端开发','html5'),(50,'005','前端开发','css'),(51,'005','前端开发','css3'),(52,'005','前端开发','javascript'),(53,'005','前端开发','jquery'),(54,'005','前端开发','json'),(55,'005','前端开发','ajax'),(56,'005','前端开发','正则表达式'),(57,'005','前端开发','bootstrap'),(58,'006','JavaScript开发','javascript'),(59,'006','JavaScript开发','jquery'),(60,'006','JavaScript开发','node.js'),(61,'006','JavaScript开发','chrome'),(62,'006','JavaScript开发','firefox'),(63,'006','JavaScript开发','internet-explorer'),(64,'006','JavaScript开发','angular.js'),(65,'006','JavaScript开发','typescript'),(66,'006','JavaScript开发','ecmascript'),(67,'006','JavaScript开发','react.js'),(68,'006','JavaScript开发','vue.js');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` char(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `bio` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `avatar_url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'32722446','程序员的小宇宙','591c9485-f2ad-46e6-a2be-0dc3bfb16d2e',1585890854778,1586864653419,NULL,'https://avatars2.githubusercontent.com/u/32722446?v=4');
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

-- Dump completed on 2020-04-15 20:53:14
