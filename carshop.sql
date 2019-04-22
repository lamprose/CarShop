/*
Navicat MySQL Data Transfer

Source Server         : CarShop
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : carshop

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-04-21 19:37:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brands
-- ----------------------------
DROP TABLE IF EXISTS `brands`;
CREATE TABLE `brands` (
  `brandId` char(10) NOT NULL COMMENT '品牌ID',
  `brandName` char(30) NOT NULL COMMENT '品牌名',
  `display` char(200) DEFAULT NULL COMMENT '展示图',
  `logo` char(200) DEFAULT NULL COMMENT '商标',
  PRIMARY KEY (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of brands
-- ----------------------------
INSERT INTO `brands` VALUES ('BaoJun', '宝骏', '/Shops/宝骏/display/', '/Shops/宝骏/logo.jpg');
INSERT INTO `brands` VALUES ('Buick', '别克', '/Shops/别克/display/', '/Shops/别克/logo.jpg');
INSERT INTO `brands` VALUES ('Cadillac', '凯迪拉克', '/Shops/凯迪拉克/display/', '/Shops/凯迪拉克/logo.jpg');
INSERT INTO `brands` VALUES ('Chevrolet', '雪佛兰', '/Shops/雪佛兰/display/', '/Shops/雪佛兰/logo.jpg');
INSERT INTO `brands` VALUES ('MAXUS', '大通', '/Shops/大通/display/', '/Shops/大通/logo.jpg');
INSERT INTO `brands` VALUES ('MG', '名爵', '/Shops/名爵/display/', '/Shops/名爵/logo.jpg');
INSERT INTO `brands` VALUES ('ROEWE', '荣威', '/Shops/荣威/display/', '/Shops/荣威/logo.jpg');
INSERT INTO `brands` VALUES ('SGMW', '五菱', '/Shops/五菱/display/', '/Shops/五菱/logo.jpg');
INSERT INTO `brands` VALUES ('SKODA', '斯柯达', '/Shops/斯柯达/display/', '/Shops/斯柯达/logo.jpg');
INSERT INTO `brands` VALUES ('superAdmin', 'superAdmin', null, null);
INSERT INTO `brands` VALUES ('Volkswagen', '大众', '/Shops/大众/display/', '/Shops/大众/logo.jpg');

-- ----------------------------
-- Table structure for cars
-- ----------------------------
DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (
  `carId` char(30) NOT NULL COMMENT '车辆ID',
  `carName` char(30) NOT NULL COMMENT '车辆名',
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `image` char(200) DEFAULT NULL COMMENT '配图',
  `evaluation` double(3,2) DEFAULT NULL COMMENT '总评',
  `brandId` char(10) DEFAULT NULL COMMENT '品牌ID',
  `paraId` int(11) DEFAULT NULL COMMENT '参数ID',
  PRIMARY KEY (`carId`),
  KEY `brandId` (`brandId`),
  KEY `paraId` (`paraId`),
  CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`brandId`) REFERENCES `brands` (`brandId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cars_ibfk_2` FOREIGN KEY (`paraId`) REFERENCES `parameters` (`paraId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cars
-- ----------------------------
INSERT INTO `cars` VALUES ('BaoJun310', '宝骏310', '4.98', '/Shops/宝骏/宝骏310/', '3.50', 'BaoJun', '1');
INSERT INTO `cars` VALUES ('BaoJun560', '宝骏560', '10.88', '/Shops/宝骏/宝骏560/', '0.00', 'BaoJun', '2');
INSERT INTO `cars` VALUES ('BaoJun730', '宝骏730', '10.28', '/Shops/宝骏/宝骏730/', '0.00', 'BaoJun', '3');
INSERT INTO `cars` VALUES ('Buick ATW', '别克昂特威', '30.99', '/Shops/别克/别克昂科威/', '0.00', 'Buick', '4');
INSERT INTO `cars` VALUES ('Buick JY', '别克君越', '30.98', '/Shops/别克/别克君越/', '0.00', 'Buick', '5');
INSERT INTO `cars` VALUES ('Buick WL', '别克威朗', '15.29', '/Shops/别克/别克威朗/', '0.00', 'Buick', '6');
INSERT INTO `cars` VALUES ('Cadillac', '凯迪拉克XT5', '32.74', '/Shops/凯迪拉克/凯迪拉克XT5/', '0.00', 'Cadillac', '15');
INSERT INTO `cars` VALUES ('Cadillac CT6', '凯迪拉克CT6', '81.88', '/Shops/凯迪拉克/凯迪拉克CT6/', '0.00', 'Cadillac', '13');
INSERT INTO `cars` VALUES ('Cadillac XT4', '凯迪拉克XT4', '28.87', '/Shops/凯迪拉克/凯迪拉克XT4/', '0.00', 'Cadillac', '14');
INSERT INTO `cars` VALUES ('Chevrolet KWL', '雪佛兰科沃兰', '9.09', '/Shops/雪佛兰/雪佛兰科沃兰/', '0.00', 'Chevrolet', '28');
INSERT INTO `cars` VALUES ('Chevrolet TJZ', '雪佛兰探界者', '24.59', '/Shops/雪佛兰/雪佛兰探界者/', '0.00', 'Chevrolet', '29');
INSERT INTO `cars` VALUES ('Chevrolet WLD', '雪佛兰沃兰多', '14.49', '/Shops/雪佛兰/雪佛兰沃兰多/', '0.00', 'Chevrolet', '30');
INSERT INTO `cars` VALUES ('MAXUS D90', '大通D90', '15.67', '/Shops/大通/上汽大通D90/', '0.00', 'MAXUS', '7');
INSERT INTO `cars` VALUES ('MAXUS T60', '大通T60', '15.38', '/Shops/大通/上汽大通T60/', '0.00', 'MAXUS', '8');
INSERT INTO `cars` VALUES ('MAXUS V80', '大通V80', '17.88', '/Shops/大通/上汽大通V80/', '0.00', 'MAXUS', '9');
INSERT INTO `cars` VALUES ('MG 6', '名爵6', '17.88', '/Shops/名爵/MG名爵6/', '0.00', 'MG', '16');
INSERT INTO `cars` VALUES ('MG HS', '名爵HS', '12.98', '/Shops/名爵/MG名爵HS/', '0.00', 'MG', '17');
INSERT INTO `cars` VALUES ('MG ZS', '名爵ZS', '8.98', '/Shops/名爵/MG名爵ZS/', '0.00', 'MG', '18');
INSERT INTO `cars` VALUES ('ROEWE eRX5', '荣威eRX5', '26.59', '/Shops/荣威/荣威eRX5/', '0.00', 'ROEWE', '19');
INSERT INTO `cars` VALUES ('ROEWE RX5', '荣威RX5', '13.68', '/Shops/荣威/荣威RX5/', '0.00', 'ROEWE', '20');
INSERT INTO `cars` VALUES ('ROEWE RX8', '荣威RX8', '22.68', '/Shops/荣威/荣威RX8/', '0.00', 'ROEWE', '21');
INSERT INTO `cars` VALUES ('SGMW S', '五菱宏光S', '5.28', '/Shops/五菱/五菱宏光S/', '0.00', 'SGMW', '25');
INSERT INTO `cars` VALUES ('SGMW S1', '五菱宏光S1', '6.98', '/Shops/五菱/五菱宏光S1/', '0.00', 'SGMW', '26');
INSERT INTO `cars` VALUES ('SGMW V', '五菱荣光V', '3.58', '/Shops/五菱/五菱荣光V/', '0.00', 'SGMW', '27');
INSERT INTO `cars` VALUES ('SKODA QXJR', '斯柯达全新晶锐', '7.99', '/Shops/斯柯达/斯柯达全新晶锐/', '0.00', 'SKODA', '22');
INSERT INTO `cars` VALUES ('SKODA SP', '斯柯达速派', '21.98', '/Shops/斯柯达/斯柯达速派/', '0.00', 'SKODA', '23');
INSERT INTO `cars` VALUES ('SKODA XD', '斯柯达昕动', '11.99', '/Shops/斯柯达/斯柯达昕动/', '0.00', 'SKODA', '24');
INSERT INTO `cars` VALUES ('Volkswagen PST', '大众帕莎特', '30.39', '/Shops/大众/上汽大众帕莎特/', '3.00', 'Volkswagen', '10');
INSERT INTO `cars` VALUES ('Volkswagen TG', '大众途观', '23.78', '/Shops/大众/上汽大众途观/', '0.00', 'Volkswagen', '11');
INSERT INTO `cars` VALUES ('Volkswagen XSTL', '大众新桑塔纳', '12.99', '/Shops/大众/上汽大众新桑塔纳/', '0.00', 'Volkswagen', '12');

-- ----------------------------
-- Table structure for evaluations
-- ----------------------------
DROP TABLE IF EXISTS `evaluations`;
CREATE TABLE `evaluations` (
  `evaId` int(11) NOT NULL AUTO_INCREMENT COMMENT '评价ID',
  `id` char(30) NOT NULL COMMENT '用户ID',
  `carId` char(30) NOT NULL COMMENT '车辆ID',
  `grade` smallint(6) DEFAULT NULL COMMENT '评分',
  `evaluate` char(255) DEFAULT NULL COMMENT '评价内容',
  `evaTime` char(16) NOT NULL,
  PRIMARY KEY (`evaId`),
  KEY `id` (`id`),
  KEY `carId` (`carId`),
  CONSTRAINT `evaluations_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `evaluations_ibfk_2` FOREIGN KEY (`carId`) REFERENCES `cars` (`carId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluations
-- ----------------------------
INSERT INTO `evaluations` VALUES ('40', 'normal', 'BaoJun310', '3', '服务很好！！！', '20190416181721');
INSERT INTO `evaluations` VALUES ('41', 'normal', 'BaoJun310', '4', '服务很好！！！', '20190416181721');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `id` char(30) NOT NULL COMMENT '用户ID',
  `carId` char(30) NOT NULL COMMENT '车辆ID',
  `amount` smallint(6) DEFAULT NULL COMMENT '数量',
  `orderStatus` char(30) DEFAULT NULL COMMENT '订单状态',
  `orderTime` char(16) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `id` (`id`),
  KEY `carId` (`carId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`carId`) REFERENCES `cars` (`carId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('16', 'normal', 'BaoJun560', '1', 'paid', '20190416165849');
INSERT INTO `orders` VALUES ('17', 'normal', 'BaoJun560', '1', 'returning', '20190416165937');
INSERT INTO `orders` VALUES ('18', 'normal', 'BaoJun730', '2', 'returned', '20190416165937');
INSERT INTO `orders` VALUES ('19', 'normal', 'BaoJun560', '1', 'delivering', '20190416170710');
INSERT INTO `orders` VALUES ('20', 'normal', 'BaoJun730', '2', 'delivered', '20190416170710');
INSERT INTO `orders` VALUES ('21', 'normal', 'BaoJun560', '1', 'unpaid', '20190416170808');
INSERT INTO `orders` VALUES ('22', 'normal', 'BaoJun730', '2', 'unpaid', '20190416170808');
INSERT INTO `orders` VALUES ('23', 'normal', 'BaoJun560', '1', 'unpaid', '20190416170917');
INSERT INTO `orders` VALUES ('24', 'normal', 'BaoJun730', '2', 'unpaid', '20190416170917');
INSERT INTO `orders` VALUES ('25', 'normal', 'BaoJun560', '1', 'unpaid', '20190416171159');
INSERT INTO `orders` VALUES ('26', 'normal', 'BaoJun730', '2', 'unpaid', '20190416171159');
INSERT INTO `orders` VALUES ('27', 'normal', 'BaoJun560', '1', 'unpaid', '20190416172207');
INSERT INTO `orders` VALUES ('28', 'normal', 'BaoJun730', '2', 'unpaid', '20190416172207');
INSERT INTO `orders` VALUES ('29', 'normal', 'BaoJun560', '1', 'unpaid', '20190416172827');
INSERT INTO `orders` VALUES ('30', 'normal', 'BaoJun730', '2', 'unpaid', '20190416172827');
INSERT INTO `orders` VALUES ('31', 'normal', 'BaoJun560', '1', 'unpaid', '20190416172904');
INSERT INTO `orders` VALUES ('32', 'normal', 'BaoJun730', '2', 'unpaid', '20190416172904');
INSERT INTO `orders` VALUES ('33', 'normal', 'BaoJun560', '1', 'unpaid', '20190416173059');
INSERT INTO `orders` VALUES ('34', 'normal', 'BaoJun730', '2', 'unpaid', '20190416173059');
INSERT INTO `orders` VALUES ('35', 'normal', 'BaoJun560', '1', 'unpaid', '20190416173116');
INSERT INTO `orders` VALUES ('36', 'normal', 'BaoJun730', '2', 'unpaid', '20190416173116');
INSERT INTO `orders` VALUES ('37', 'normal', 'BaoJun560', '1', 'unpaid', '20190416181625');
INSERT INTO `orders` VALUES ('38', 'normal', 'BaoJun730', '2', 'unpaid', '20190416181721');
INSERT INTO `orders` VALUES ('39', 'normal', 'Buick ATW', '4', 'unpaid', '20190416181721');
INSERT INTO `orders` VALUES ('40', 'normal', 'BaoJun310', '1', 'evaluated', '20190416181721');
INSERT INTO `orders` VALUES ('41', 'normal', 'BaoJun310', '2', 'evaluated', '20190416181721');
INSERT INTO `orders` VALUES ('45', 'normal1', 'Volkswagen PST', '1', 'paid', '20190419112417');

-- ----------------------------
-- Table structure for parameters
-- ----------------------------
DROP TABLE IF EXISTS `parameters`;
CREATE TABLE `parameters` (
  `paraId` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数ID',
  `carSize` char(30) DEFAULT NULL COMMENT '车身尺寸',
  `engine` char(30) DEFAULT NULL COMMENT '发动机',
  `level` char(10) DEFAULT NULL COMMENT '级别',
  `structure` char(30) DEFAULT NULL COMMENT '车身结构',
  `assurance` char(30) DEFAULT NULL COMMENT '整车质保',
  `oilWear` double(4,2) DEFAULT NULL COMMENT '油耗',
  `manufacturer` char(10) DEFAULT NULL COMMENT '厂商',
  `gearbox` char(10) DEFAULT NULL COMMENT '变速箱',
  `maxSpeed` int(4) DEFAULT NULL COMMENT '最高车速',
  PRIMARY KEY (`paraId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parameters
-- ----------------------------
INSERT INTO `parameters` VALUES ('1', '4032*1680*1450', '-', 'SUV', '5门5座两厢车', '-', '5.30', '上海通用五菱', '手动', null);
INSERT INTO `parameters` VALUES ('2', '4620*1810*1765', '1.5T 150马力 L4', 'SUV', '5门5座SUV', '三年或6万公里', '7.70', '上汽通用五菱', '双离合', null);
INSERT INTO `parameters` VALUES ('3', '4780*1820*1755', 'P-tec DVVT', 'MPV', '5门7座MPV', '三年或6万公里', '7.40', '上汽通用五菱', 'AMT', '160');
INSERT INTO `parameters` VALUES ('4', '4667*1839*1696', '2.0T 260马力 L4', 'SUV', '5门5座SUV', '三年或10万公里', '8.80', '上汽通用别克', '手自一体', '210');
INSERT INTO `parameters` VALUES ('5', '5038*1866*1469', '2.0T SIDI直喷涡轮增压发动机', '中型车', '4门5座三厢车', '三年或10万公里', '7.50', '上汽通用别克', '手自一体', '160');
INSERT INTO `parameters` VALUES ('6', '4718*1802*1466', '1.5L 118马力 L4', '紧凑型车', '4门5座三厢车', '三年或10万公里', '6.30', '上汽通用别克', '手自一体', '180');
INSERT INTO `parameters` VALUES ('7', '5005*1932*1875', '2.0T 224马力 L4', 'SUV', '-', '两年或5万公里', null, '上汽商用车', '手动', null);
INSERT INTO `parameters` VALUES ('8', '5365*1900*1845', '2.8T 150马力 L4', '客车', '-', '两年或5万公里', null, '上汽商用车', '6档自动', null);
INSERT INTO `parameters` VALUES ('9', '5785*1998*2345', '2.5T 136马力 L4', 'MPV', '-', '两年或5万公里', null, '上汽商用车', 'AMT', null);
INSERT INTO `parameters` VALUES ('10', '4872*1834*1484', '第三代EA888 TSI涡轮增压缸内直喷汽油发动机', '中型车', '4门5座三厢车', '三年或10万公里', '7.00', '上汽大众', '双离合', '238');
INSERT INTO `parameters` VALUES ('11', '4506*1809*1685', 'TSI水冷直列4缸16气门直喷发动机', '紧凑型SUV', '5门5座SUV', '三年或10万公里', '8.60', '上汽大众', '手自一体', '192');
INSERT INTO `parameters` VALUES ('12', '4286*1720*1481', '自然吸气发动机', '紧凑型车', '5门5座两厢车', '三年或10万公里', null, '上汽大众', '自动', '185');
INSERT INTO `parameters` VALUES ('13', '5179*1879*1500', '可变排量3.0TT LGW V6直喷发动机', '大型车', '4门5座三厢车', '三年不限公里', null, '上汽通用凯迪拉克', '手自一体', '240');
INSERT INTO `parameters` VALUES ('14', '4600*1881*1627', '2.0T 241马力 L4', 'SUV', '5门5座SUV', '三年不限公里', '7.60', '上汽通用凯迪拉克', '手自一体', '210');
INSERT INTO `parameters` VALUES ('15', '4812*1903*1680', '2.0T 250马力 L4', '中型SUV', '5门5座SUV', '三年不限公里', '8.20', '上汽通用凯迪拉克', '手自一体', '210');
INSERT INTO `parameters` VALUES ('16', '4695*1848*1458', '1.0T 125马力 L3', '紧凑型SUV', '5门5座三厢车', '三年或10万公里', null, '上汽乘用车', '电动车单速变速箱', null);
INSERT INTO `parameters` VALUES ('17', '4574*1876*1685', '2.0T 231马力 L4', 'SUV', '5门5座SUV', '三年或10万公里', '8.20', '上汽乘用车', '双离合', '210');
INSERT INTO `parameters` VALUES ('18', '4314*1809*1648', '自然吸气发动机', 'SUV', '5门5座SUV', '三年或10万公里', '6.10', '上汽乘用车', '手动', null);
INSERT INTO `parameters` VALUES ('19', '4554*1855*1719', '1.5TGI缸内中置直喷涡轮增压发动机', 'SUV', '5门5座SUV', '五年或10万公里', '1.60', '上汽乘用车', 'EDU智能电驱动单元', '200');
INSERT INTO `parameters` VALUES ('20', '4545*1855*1719', '1.5T 169马力 L4', 'SUV', '5门5座SUV', '三年或10万公里', '6.80', '上汽乘用车', '双离合', null);
INSERT INTO `parameters` VALUES ('21', '4923*1930*1840', '2.0T 224马力 L4', 'SUV', '5门7座SUV', '三年或10万公里', '9.90', '上汽乘用车', '手自一体', null);
INSERT INTO `parameters` VALUES ('22', '3993*1732*1487', '1.4升直列4缸16气门', '小型车', '5门5座两厢车', '三年或10万公里', '6.00', '上汽大众斯柯达', '自动', '178');
INSERT INTO `parameters` VALUES ('23', '4861*1865*1489', '1.8T 180马力 L4', '中型车', '5门5座掀背车', '三年或10万公里', '7.30', '上汽大众斯柯达', '双离合', '225');
INSERT INTO `parameters` VALUES ('24', '4312*1706*1475', '1.4T 131马力 L4', '紧凑型车', '5门5座两厢车', '三年或10万公里', '5.60', '上汽大众斯柯达', '双离合', '200');
INSERT INTO `parameters` VALUES ('25', '4420*1685*1770', '-', '客车', '客车', '-', null, '上汽通用五菱', '手动', '140');
INSERT INTO `parameters` VALUES ('26', '4500*1721*1730', '1.5T 112马力 L4', 'MVP', '客车', '三年或6万公里', '6.60', '上汽通用五菱', '手动', '160');
INSERT INTO `parameters` VALUES ('27', '4051*1575*1780', '-', '客车', '客车', '-', null, '上汽通用五菱', '手动', '140');
INSERT INTO `parameters` VALUES ('28', '4544*1779*2600', '1.5T DVVT直列四缸发动机', '紧凑型车', '4门5座三厢车', '三年或10万公里', '5.80', '上海通用雪佛兰', '自动', '180');
INSERT INTO `parameters` VALUES ('29', '4652*1853*1661', '直喷涡轮发动机', 'SUV', '4门5座SUV', '三年或10万公里', '8.30', '上海通用雪佛兰', '手自一体', '190');
INSERT INTO `parameters` VALUES ('30', '4684*1807*1627', '1.3T 163马力 L3', '紧凑型车', '5门5座两厢车', '三年或10万公里', null, '上海通用雪佛兰', '手自一体', '190');

-- ----------------------------
-- Table structure for securityquestion
-- ----------------------------
DROP TABLE IF EXISTS `securityquestion`;
CREATE TABLE `securityquestion` (
  `id` char(30) NOT NULL COMMENT '用户ID',
  `questionType` char(2) NOT NULL DEFAULT '' COMMENT '问题类型',
  `answer` char(100) DEFAULT NULL COMMENT '答案',
  PRIMARY KEY (`id`,`questionType`),
  CONSTRAINT `securityquestion_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of securityquestion
-- ----------------------------
INSERT INTO `securityquestion` VALUES ('normal', '0', '1');
INSERT INTO `securityquestion` VALUES ('normal', '1', '1');
INSERT INTO `securityquestion` VALUES ('normal', '2', '1');
INSERT INTO `securityquestion` VALUES ('normal1', '0', '1');
INSERT INTO `securityquestion` VALUES ('normal1', '2', '2');
INSERT INTO `securityquestion` VALUES ('normal1', '5', '3');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart` (
  `cartId` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车单号',
  `id` char(30) NOT NULL COMMENT '用户ID',
  `carId` char(30) NOT NULL COMMENT '车辆ID',
  `amount` smallint(6) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`cartId`),
  KEY `id` (`id`),
  KEY `carId` (`carId`),
  CONSTRAINT `shoppingcart_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shoppingcart_ibfk_2` FOREIGN KEY (`carId`) REFERENCES `cars` (`carId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES ('16', 'normal', 'Volkswagen PST', '1');
INSERT INTO `shoppingcart` VALUES ('17', 'normal', 'Buick JY', '1');
INSERT INTO `shoppingcart` VALUES ('20', 'normal1', 'Volkswagen PST', '1');

-- ----------------------------
-- Table structure for shops
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `shopId` char(30) NOT NULL COMMENT '商户ID',
  `shopName` char(30) NOT NULL COMMENT '商户名',
  `password` char(200) DEFAULT NULL,
  `brandId` char(10) NOT NULL COMMENT '品牌ID',
  `phone` char(30) DEFAULT NULL COMMENT '联系电话',
  `token` char(200) DEFAULT NULL,
  `status` char(30) NOT NULL COMMENT '登陆状态',
  PRIMARY KEY (`shopId`),
  KEY `brandId` (`brandId`),
  CONSTRAINT `shops_ibfk_1` FOREIGN KEY (`brandId`) REFERENCES `brands` (`brandId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shops
-- ----------------------------
INSERT INTO `shops` VALUES ('BaoJun', '宝骏', 'QkpAmXTtPessj0U1dSGcrbvXnTAvOhrcgd5DoyLT8/+8hoCb8QXF8JmBFxLowA35bcJ2d5Pqo/CI6bojQey5S6qTOceah76rhhKM26Gd4amBONTqpDANJboUboztkex55OW5FNjnfOBJdNW6gUW40sd6EFtv1PMwO6/RJlFPFxE=', 'BaoJun', '133XXXXXXXX', 'AIgTgMg5ldgRc66/bE4MvEG62RCasYNWEtkY5OzVx3eBhUgPmZENUmmFKz3fKctinphaOK74VT94Cfsmb4Bo7lALtjcmXknaxZvTg/GOXO48V9B1tQkivSdE4MpkbYMBHKOuBRHL4TUmc133qOfcwv1zSFYXQA9pfawymQ0yfxMc', 'online');
INSERT INTO `shops` VALUES ('Buick', '别克', 'GfaqtE2mPx9vOXj+BJY1rCfPxUYNLHnxWmP6A4kc0xgrKWjnm22TIp9nP+dFOKNCQOjLbsUnAZxNhkCmCdOAat70oi6rMyNdI6qCpsAPnFScQF3WgYg9N+w/zQjIy5nzndzllmewS1i4duGx6vde99q6l0DEX4afvwAaRyKY1kU=', 'Buick', '133XXXXXXXX', 'f7V0ywPH/DFsEIYzvflAfqFePJ7AObd7DaRw7Y33qcg30jU+2a5mLcEzCa7aoH8cIhXlcXEYR52g4Nbf6ljJPcYxm26uSJZsHz1+Y/iN6EBO0LolVGV+9Wv9PhVdzlwcKHV8o25FM+z1+775BkjhKYh0h7WUPZb/Tt9+dR80AJE=', 'offline');
INSERT INTO `shops` VALUES ('Cadillac', '凯迪拉克', 'OhTmggCXntHnb7rUa7NYsM5i19HHJaoJzt4yhnYKi4+NpoxtDIuFK5HAsR+FatkTxIKjgiUfiEaUl2DzZWyPVShiPEY4I86xZ1TgGgEmCvUolmvIJNSDdDAbDkv9oHflvThtO6lDdyjCWTaHUdAX6PWz7qtC/gOyS7I+m2UZpJs=', 'Cadillac', '133XXXXXXXX', 'AIW6xw/lmoGVguwLTREU9OCe/4Qrl9lEfAxqPfcx/RlkIuxokXw0VBmHd0nMbD+6l8o3itjjX1GBaVHltwwDk+EZvw0/S6EeKj7nrzXI+sNnFIrHP/IRpXBf8jNung/TrlsKKS3/YdApA1Idt5xBKtbtqdmLpMF47em1Ne+z3VpX', 'offline');
INSERT INTO `shops` VALUES ('Chevrolet', '雪佛兰', 'AIhsTbo4enB5tSbrM+VduVpC7T0lXEqULIR1a/wR2VPgEeJ2nE8iVmHtloT3dnlsBoeZ8qVoFANxqRtyopD4h9bUf0PiPCIWwzpXGEntv7tjFzuzfwritoCLUAiCQMmH/0vYKo7rBmT3/aOzD+zrmkiKkKgX/ebPmso62OodOtLS', 'Chevrolet', '133XXXXXXXX', 'XrYP666Ye88sF2wo4Kjq/1vO5pEKBtkO2iFP1SUpEN1A7Y2Z/DDsd202pEtvRwZccnhlfrdLqASlUBHvLnrBAeT2IyMln4YdeYVH0qWUGdJLrIgXYZNRni68es3Y29nPuvXt1mXN/oeBv5nNHAjRsbhqXRh9aaRyZnmcmEMTA9g=', 'offline');
INSERT INTO `shops` VALUES ('MAXUS', '大通', 'arepLhCeOEpOtTx8pUNODSaXcOZzWnCDjY6TS3GvrQtV/rBM+KajvPw8weaHGB7Eatr25O5mDvYjYcpWq1c7Om/k7D6pP7nvNrG6RDNgBhLLr8xywhF0PGtzyXi6h0djRz05ddt4sBztWXewN5VfUXFzB/tpK2VEPN/qnVPcCao=', 'MAXUS', '133XXXXXXXX', 'TzyQO6A55aRCYGNinTd+DvUEQF8o7dvW4bsD4ZyddeK5KGheb+euRG8bD0JX5P+oiftODIbET7y3CGUuvS8Tm1z0PB1Pp7tuqQCoqnxwWVanIkYN8hbKkQRBY5+Jr02a97EgOiGAlBWtjzli2nKkaHbwVf3kUOe4CKX0jDh2Onc=', 'offline');
INSERT INTO `shops` VALUES ('MG', '名爵', 'AJEg9a44uyElUwLMxJaxD2Mk7ACfj7XvmlnD/MIExIIx4Oeyjq2mqW9mBELEgjQyldV8UKE9xNVI0hP1sMx0vI1OlEwRH1R6MnjHuyRFctGwyw7PevCrEHmC4El2ezqAcpCxdZgZQIedMWDN5G8qowXk6lLr0bOKidmm7G1rvbBt', 'MG', '133XXXXXXXX', 'Or16GqCxPYY2BYqIaH5Ss82QT2Hc7i2zw/6i2AXkpA2jbHTjMM9Rs9Vhp9rgAthM2Bt+DF2mkXNlnRbZZH/UxCnDNOcmOAhkUYWGJYZ9Yz23f/yPcErQc7pXH4L3b78MFZWn/mWxnJEp0EXNz6Ps1KmDVhWfZ876A8aViJgfhNA=', 'offline');
INSERT INTO `shops` VALUES ('ROEWE', '荣威', 'AJBEH5Tv1jd0aFRT63aYjKaiABgC5N465gfF3R/20Zo5itrswBdaNHidoFuicH3f4nl4ZMSEYgIeWeZ0r9MBw+GXlidVqIvtZsEBJrvWPVE2WhMczl7iRQYKU8OEbCppwNMQOY/k9Ii8/7Ae7FQ1XcqiM+ivtqP9HhyDq3xlSA4P', 'ROEWE', '133XXXXXXXX', 'FYzKeXbx6gYj0i6AMQiJf1xflXH8LejGz/0a5FpEdNc6HQr5gChvCSUKqgXTtVuHJlIjjVfp/lu4clUN8heF4ZHo9RtHBE7W7gxBnpUZl3fCYvY++nirT17b0wx+yjEU/2QepE0FQYul2fA3WDeq5qZy68MZYHDjs1agm+ZRQuE=', 'offline');
INSERT INTO `shops` VALUES ('SGMW', '五菱', 'HPb2qksIzxUSXg+WQxNbcy/xsS8Riq9u/fE+/zCG8BGDe1VR9Tz521vrRH6Ex/lDfEi9RECCnPbCKi10LTd6TlQFJEWFpKGKbKoBCzl6DSQZ+vgKpX6EqriqKv8TVwy8Cpkh8MRj3Ea7aIArupegdpO0hP5kjnciAg0vXDXZ0cw=', 'SGMW', '133XXXXXXXX', 'A75ZwrMvdUARLahYUhQmNZ3ca+FXXfZcc9u0/lF1siSqU4NyXG6gqElbADAI5GdLjTyZyCS7ZzrmCRHnKRj7LTYoWD+G7C2pP9+SCR2AgUC2rB/iwBcz8boC/w55UrMXW4E5UkHorbd/knOCoEXVPWWphGS6/ISZczfNV0OGTqg=', 'offline');
INSERT INTO `shops` VALUES ('SKODA', '斯柯达', 'QXLfdc9mDWyYidWY+/2W/PZDlv+c4ewZmwCMX7lzTQbvBWuSyqSbrCMl88jsikYVh+0XuYOOiCeib5TBCWm0YepxtA6A8cMjmvKBuosafnY8wm7lJ1xxrwtBI5/rGGFLfnLOSbVPB6WmSL8EzHLSTFvA+gysJiJdWLYp+MGhPVo=', 'SKODA', '133XXXXXXXX', 'VlJZLPhiqUajhSdtLmsxXDtcmOraoWfYvAgVigjpvNuh11crrE42U9kZ1/NI6vaHNGYLat55iKn601a3karl/0QbZ+CCLluDQTIR0aJb4Qc4+KxlAW3TmxK9SHk9FBdhmya7FvftZ8udvLpp9nFTU4yzn18qSHQaHf8an6sAHlk=', 'offline');
INSERT INTO `shops` VALUES ('superAdmin', 'superAdmin', 'NOJBi83BkIG0L6oZecIMqpxkhJIhGh/x7NpZ7CViqA2WX4iUl/T4G7CrS4YIyD/bspMiPfRXK/8YMHy9mjLVE2SO9+M5B62xlXUAK6+OXLsAuECbALTmAHw3dUFzO+u6F+Gpvn7ZOUiv3mKbWCLc7R9tAwbeCpqEyveO5sUaGG4=', 'superAdmin', '133XXXXXXXY', 'Zhr6Xj0/j53YnymhMnKNfuCDpWTRmL+00MnvPRq+sKlgEnCJpvILdeDx1RamSs7+8fKEER3HgrN9tQlRB+TkDGQjTe+Z8PILvoMw/m2xvTJe0lXTIZfHhBgS/va0PKwonNGGXBj5VJi4LUZn+yVAkbwEpew3WnKEOLeJOZpE1D8=', 'online');
INSERT INTO `shops` VALUES ('Volkswagen', '大众', 'bVjK9sq+diHrwFyMvt7FMstHPRorhVNVuF6yRS8zr1pNVMZkvn7FsonPTKwkOmXZTGjUVD9QmdvfxCuntFn0I2x6aEvTC/zLWvUfMgE/+U4uJ8obCzGlvG2WH3PxF1yHp6RsivUum1PQ6ymSVke0u6svIlH37BUmmFI7hbMHHJE=', 'Volkswagen', '133XXXXXXXX', 'DnFzhWQ3qmbSV88GqXw5g+99CqNbrPb1zHTUUJJfjzAFYH10ze4tceq3T9tKZA/PZaXO5X1HK1UeOM/5mtS6amH0i2oT++IDOElVfhqSvGfMQsVQG7UyBG/Kk6rq/t7x2P/pmQlApaQOaoXcAgnd2BUWuViphS+8Ns22ZEeMd5E=', 'offline');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(30) NOT NULL COMMENT '用户ID',
  `name` char(30) DEFAULT NULL COMMENT '用户名',
  `password` char(200) DEFAULT NULL,
  `sex` char(10) DEFAULT NULL COMMENT '性别',
  `phone` char(20) DEFAULT NULL COMMENT '联系电话',
  `loc` char(100) DEFAULT NULL COMMENT '收货地址',
  `token` char(200) DEFAULT NULL,
  `status` char(30) NOT NULL COMMENT '登陆状态',
  `avatar` char(200) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('normal', 'normal', 'd6Z9U0QHFR49vBzAGlXpUKbfzYoNrYdFSeEVckJYo3ELONupcBtot6egRXunprXI94aivzaI3ikQdhfoHW+WdOVyKsWVRHWeOD61TrwYVr1UsqBNJR1crQvFCcR3m5QGUmvlOJW/237W1ZGdT0ZKvAZ2zq40pOVWmFkvAtTJe0M=', '男', '133XXXXXXXX', '上海市|市辖区|松江区|文汇路', 'PE8X/o6WhBxM+y1elJE97FvGMVl5cfrc1o0KK6039cSYPlfUe62kj+OMEFHzm5f3NiRpbjELUiswTe0PXYjUNBKDekcsltKvAjVqjxtFbIf0XL+U+Z0dkNohfmDAC+eCLK1ZKHEtEjA0elHa4cbok4o1KZLqC9ozYNS80fmjd+M=', 'online', '/UserAvatar/normal_20190419102014.jpg');
INSERT INTO `user` VALUES ('normal1', null, 'd6Z9U0QHFR49vBzAGlXpUKbfzYoNrYdFSeEVckJYo3ELONupcBtot6egRXunprXI94aivzaI3ikQdhfoHW+WdOVyKsWVRHWeOD61TrwYVr1UsqBNJR1crQvFCcR3m5QGUmvlOJW/237W1ZGdT0ZKvAZ2zq40pOVWmFkvAtTJe0M=', null, null, null, 'YraL3fLyyuATTU1iHyvtMQBkS+MrXsMz2va8LVHRYWLcFpaUL8/FvlDdyllYfRBo0RiCmuBBDSxI4iNUq9dCNp/4sF+JN38SAZwF90ohiMoCAU/RJDUu22Qw/yyTlNRWNZnI8hZp+Rzh+Hf3qAWBLWPFCkcJKQKq7FSF5lfTcso=', 'offline', '/UserAvatar/normal1_20190419112618.jpg');
