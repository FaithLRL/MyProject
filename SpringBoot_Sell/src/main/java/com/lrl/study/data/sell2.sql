/*
Navicat MySQL Data Transfer

Source Server         : LRL
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : sell2

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-12-21 13:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) COLLATE utf8_bin NOT NULL COMMENT '小图(图标)',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1512977925797414192', '1512977925743466029', '112', '鸭公煲', '2.50', '50', 'http:www.baidu.com', '1512977925820', '1512977925820');
INSERT INTO `order_detail` VALUES ('1512977925830567250', '1512977925743466029', '980', '糖醋排骨', '2.50', '10', 'http:www.baidu.com', '1512977925832', '1512977925832');
INSERT INTO `order_detail` VALUES ('1512977998993452792', '1512977998919460278', '112', '鸭公煲', '2.50', '5', 'http:www.baidu.com', '1512977999008', '1512977999008');
INSERT INTO `order_detail` VALUES ('1512977999019971464', '1512977998919460278', '110', '大排饭', '2.50', '20', 'http:www.baidu.com', '1512977999021', '1512977999021');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `buyer_name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '买家姓名',
  `buyer_phone` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '买家id',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0是下单状态',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0支付',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单主表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1512977925743466029', '王四', '515106556', '温大', '111', '150.00', '0', '0', '1512977925838', '1512977925838');
INSERT INTO `order_master` VALUES ('1512977998919460278', '庄三', '34324234242', '温职', '222', '62.50', '0', '0', '1512977999029', '1512977999029');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '类目名称',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('3', '头条类', '8', '1512977466458', '1512977466458');
INSERT INTO `product_category` VALUES ('4', '生活用品类', '3', '1512977523070', '1512977523070');
INSERT INTO `product_category` VALUES ('5', '热搜类', '1', '1512977568728', '1512977568728');
INSERT INTO `product_category` VALUES ('6', '好评类', '2', '1512977654153', '1512977654153');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `product_icon` varchar(512) COLLATE utf8_bin NOT NULL COMMENT '小图',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `product_status` int(11) DEFAULT NULL COMMENT '上下架状况',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('110', '大排饭', '2.50', '80', '好吃！', 'http:www.baidu.com', '1', '0', '1512977841474', '1512977999042');
INSERT INTO `product_info` VALUES ('112', '鸭公煲', '2.50', '45', '好吃！', 'http:www.baidu.com', '1', '0', '1512977716687', '1512977999041');
INSERT INTO `product_info` VALUES ('119', '叫花鸡', '2.50', '100', '好吃！', 'http:www.baidu.com', '1', '1', '1513651175669', '1513651701581');
INSERT INTO `product_info` VALUES ('157', '糖醋里脊', '2.50', '100', '好吃！', 'http:www.baidu.com', '1', '0', '1512977802508', '1512977802508');
INSERT INTO `product_info` VALUES ('980', '糖醋排骨', '2.50', '90', '好吃！', 'http:www.baidu.com', '1', '0', '1512977684442', '1512977925851');
