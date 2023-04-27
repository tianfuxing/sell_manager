/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : orderfood

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2023-04-03 10:41:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authority_department`
-- ----------------------------
DROP TABLE IF EXISTS `authority_department`;
CREATE TABLE `authority_department` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `masterid` varchar(36) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_department
-- ----------------------------
INSERT INTO `authority_department` VALUES ('04b3ed49-5ed4-4a85-8ed3-62e9e547120f', '研发部', '0', '1520486a-1a55-4b73-a32e-94121711a64e', '2022-08-29 17:58:53');
INSERT INTO `authority_department` VALUES ('1520486a-1a55-4b73-a32e-94121711a64e', '集团总部', '100', '', '2022-08-25 14:56:33');
INSERT INTO `authority_department` VALUES ('15fcaf31-c4a3-457a-9ef3-3f55ced932d9', '研发一部', '100', '04b3ed49-5ed4-4a85-8ed3-62e9e547120f', '2022-08-29 17:59:41');
INSERT INTO `authority_department` VALUES ('3b0d3288-7f10-408c-8e05-a5bfd265a9c1', '财务部', '0', '1520486a-1a55-4b73-a32e-94121711a64e', '2022-08-25 14:59:55');
INSERT INTO `authority_department` VALUES ('6117ae08-095b-49b2-85a6-afd08d39c46e', '研发二部', '80', '04b3ed49-5ed4-4a85-8ed3-62e9e547120f', '2022-08-29 17:59:53');
INSERT INTO `authority_department` VALUES ('976b3fde-db63-4eb2-8dfb-904bbb0e871e', '人力资源部', '1', '1520486a-1a55-4b73-a32e-94121711a64e', '2022-08-25 15:05:16');

-- ----------------------------
-- Table structure for `authority_menu`
-- ----------------------------
DROP TABLE IF EXISTS `authority_menu`;
CREATE TABLE `authority_menu` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `hidden` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `masterid` varchar(36) DEFAULT NULL,
  `route` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('1af7496d-4787-4048-9160-71475ba17a18', '菜品管理', 'el-icon-food', '0', '99', '83bd5f06-8bbe-4789-82e8-46ad59c5c048', '/home/wxProgram/foodManage', '2023-02-10 16:44:31');
INSERT INTO `authority_menu` VALUES ('1eed39b0-1350-4473-8a36-9cd7b9db977d', '用户管理', 'el-icon-user', '0', '1', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/userManage', '2023-03-17 08:36:01');
INSERT INTO `authority_menu` VALUES ('2ae71bc0-3ea2-487d-b872-9993cea9a092', '部门管理', 'el-icon-wind-power', '0', '95', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/departmentManage', '2022-08-23 13:47:30');
INSERT INTO `authority_menu` VALUES ('305ee66d-ff75-4271-b9b6-8ccbfbcf9e8b', '管理台', 'el-icon-pie-chart', '0', '200', '', '/home/managerConsole', '2023-04-02 20:47:57');
INSERT INTO `authority_menu` VALUES ('5175ece3-04db-4661-8c83-5b7c11a6bd4f', '菜品种类', 'el-icon-document-copy', '0', '100', '83bd5f06-8bbe-4789-82e8-46ad59c5c048', '/home/wxProgram/foodType', '2023-02-10 16:43:13');
INSERT INTO `authority_menu` VALUES ('62963b9a-ce69-4316-ae70-b338593f6522', '角色管理', 'el-icon-s-check', '0', '98', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/roleManage', '2022-08-15 16:41:28');
INSERT INTO `authority_menu` VALUES ('83bd5f06-8bbe-4789-82e8-46ad59c5c048', '小程序管理', 'el-icon-s-promotion', '0', '99', '', '/home/wxProgram', '2023-02-10 16:41:14');
INSERT INTO `authority_menu` VALUES ('854ab22b-8ce5-4e95-bdd2-692b20d899fe', '权限管理', 'el-icon-postcard', '0', '100', '', '/home/authority', '2022-08-15 15:20:47');
INSERT INTO `authority_menu` VALUES ('b6db4b4d-9019-4ace-899e-837654f4b262', '菜单管理', 'el-icon-menu', '0', '99', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/menuManage', '2022-08-15 16:40:16');
INSERT INTO `authority_menu` VALUES ('bfbc8b32-0e44-4871-adf3-596e7a8383df', '订单管理', 'el-icon-folder-add', '0', '96', '83bd5f06-8bbe-4789-82e8-46ad59c5c048', '/home/wxProgram/orderManage', '2023-02-10 17:07:46');
INSERT INTO `authority_menu` VALUES ('ea24c600-b1d8-4a27-ac8e-e57a7a2a6057', '会员等级', 'el-icon-top-right', '0', '95', '83bd5f06-8bbe-4789-82e8-46ad59c5c048', '/home/wxProgram/userLevel', '2023-02-10 16:53:47');
INSERT INTO `authority_menu` VALUES ('ea2b59b9-2164-4284-b0a0-388fdf69427f', '权限管理', 'el-icon-postcard', '0', '97', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '/home/authority/authorityManage', '2022-08-15 16:41:59');

-- ----------------------------
-- Table structure for `authority_role`
-- ----------------------------
DROP TABLE IF EXISTS `authority_role`;
CREATE TABLE `authority_role` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_role
-- ----------------------------
INSERT INTO `authority_role` VALUES ('1663b611-7ddb-47f0-a81e-8ac181deac8c', '超级管理员', '拥有所有权限的管理员', '2022-08-15 17:22:48');

-- ----------------------------
-- Table structure for `authority_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `authority_role_menu`;
CREATE TABLE `authority_role_menu` (
  `id` varchar(36) NOT NULL,
  `roleid` varchar(36) DEFAULT NULL,
  `menuid` varchar(36) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_role_menu
-- ----------------------------
INSERT INTO `authority_role_menu` VALUES ('202d54b3-7395-4412-8f84-4ba18dd99692', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '1eed39b0-1350-4473-8a36-9cd7b9db977d', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('428cb18f-4076-4be7-a86d-eca31159f758', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'ea24c600-b1d8-4a27-ac8e-e57a7a2a6057', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('481f3e55-9e7e-4f4c-8d94-f17b7afc142a', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '305ee66d-ff75-4271-b9b6-8ccbfbcf9e8b', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('6b877b31-9bc2-4861-971d-26e284e57963', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'ea2b59b9-2164-4284-b0a0-388fdf69427f', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('7a06cf7e-b4a2-4273-8e64-46169cbd98c9', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '5175ece3-04db-4661-8c83-5b7c11a6bd4f', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('85999314-c034-412a-bada-41bd0348585a', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '854ab22b-8ce5-4e95-bdd2-692b20d899fe', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('8b4c4a98-d723-43e6-a034-6e5b52efbd07', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '2ae71bc0-3ea2-487d-b872-9993cea9a092', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('98dc919d-ede5-42aa-abf1-f9ca50b86897', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'bfbc8b32-0e44-4871-adf3-596e7a8383df', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('9ad0d0f7-6f6b-429c-b74e-07f80b99e2ef', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '83bd5f06-8bbe-4789-82e8-46ad59c5c048', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('a2a8c042-f9a5-482a-a393-a93a2c8118a7', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '1af7496d-4787-4048-9160-71475ba17a18', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('b4cba6b0-7111-49db-8a3e-601827c734de', '1663b611-7ddb-47f0-a81e-8ac181deac8c', 'b6db4b4d-9019-4ace-899e-837654f4b262', '0', '2023-04-02 20:48:03');
INSERT INTO `authority_role_menu` VALUES ('f794f0c0-fe12-4dd0-a200-4c737aaaf006', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '62963b9a-ce69-4316-ae70-b338593f6522', '0', '2023-04-02 20:48:03');

-- ----------------------------
-- Table structure for `authority_user`
-- ----------------------------
DROP TABLE IF EXISTS `authority_user`;
CREATE TABLE `authority_user` (
  `id` varchar(36) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `lastlogintime` datetime DEFAULT NULL,
  `departmentid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_user
-- ----------------------------
INSERT INTO `authority_user` VALUES ('b2041da2-9373-42a5-9b83-6388fd3710a6', '超级管理员', 'admin', 'admin', '2022-08-17 15:01:33', '2023-04-02 21:32:51', '1520486a-1a55-4b73-a32e-94121711a64e');

-- ----------------------------
-- Table structure for `authority_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `authority_user_role`;
CREATE TABLE `authority_user_role` (
  `id` varchar(36) NOT NULL,
  `userid` varchar(36) DEFAULT NULL,
  `roleid` varchar(36) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority_user_role
-- ----------------------------
INSERT INTO `authority_user_role` VALUES ('04ea4f16-3b78-4d5e-b700-972f7c67c7ee', 'b2041da2-9373-42a5-9b83-6388fd3710a6', '1663b611-7ddb-47f0-a81e-8ac181deac8c', '2022-08-26 10:32:16');

-- ----------------------------
-- Table structure for `wx_address`
-- ----------------------------
DROP TABLE IF EXISTS `wx_address`;
CREATE TABLE `wx_address` (
  `id` varchar(36) NOT NULL,
  `userid` varchar(36) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `defaults` varchar(10) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_address
-- ----------------------------

-- ----------------------------
-- Table structure for `wx_food`
-- ----------------------------
DROP TABLE IF EXISTS `wx_food`;
CREATE TABLE `wx_food` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `typeid` varchar(36) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_food
-- ----------------------------
INSERT INTO `wx_food` VALUES ('08403a02-b13f-402b-8196-dd0f426618a9', '雪花勇闯天涯', '10.00', '8d520ecc-11e7-4b6a-8e6a-7453887b0904', '0', '60211120-f1ea-451b-876e-1cbe0c07852d_1679319980532.jpg', '2023-03-20 21:46:44');
INSERT INTO `wx_food` VALUES ('160c2a89-4e89-42a9-a7c2-544610ecc5e5', '鸳鸯锅底', '20.00', '2c5926b4-c59a-4dbe-8d19-9d7dfce01481', '1', 'b7d46efb-5bb3-4b3c-a95b-6dac49a65d09_333.jpg', '2023-03-20 21:14:12');
INSERT INTO `wx_food` VALUES ('2d1d0838-94f5-4d77-a5d7-0381079c1e26', '精品肥羊', '42.00', '33424052-84d7-4341-a42e-4ccaa3ac5f71', '0', '184a75a5-3de2-4b11-a9f9-3c1656104687_fy.jpg', '2023-03-20 21:17:33');
INSERT INTO `wx_food` VALUES ('2f357e9c-205a-4fc7-8a0d-ef4dd99427db', '鲜豆腐', '6.00', 'a7745da2-a44c-49db-9ff5-d308433e4fc9', '3', '168374bc-56f1-491d-9762-b76177f354ee_1679320556985.jpg', '2023-03-20 21:56:26');
INSERT INTO `wx_food` VALUES ('3178a976-41d7-4149-b2ab-98602003ce55', '手打虾滑', '25.00', '54d60994-7e8a-4ab2-862f-c155a3f9da8c', '0', '5c3817df-1284-47fb-87a7-85c595a77078_1679319160624.jpg', '2023-03-20 21:33:06');
INSERT INTO `wx_food` VALUES ('42c6984c-382b-4bb1-9a85-a0dcdae6e57f', '农夫山泉', '3.00', '8d520ecc-11e7-4b6a-8e6a-7453887b0904', '1', 'd5a5e696-46de-4f7a-8c25-f36bfcec0b74_1679320085936.jpg', '2023-03-20 21:48:24');
INSERT INTO `wx_food` VALUES ('484d7f55-f030-439e-9786-cd5e2ee9ebb4', '清汤锅底', '20.00', '2c5926b4-c59a-4dbe-8d19-9d7dfce01481', '0', 'c5f1bd32-403c-4185-bded-2fd6ab6fa852_1679317336997.jpg', '2023-03-20 21:02:58');
INSERT INTO `wx_food` VALUES ('490fa548-fc1e-4972-bc65-fc55eab7b2bc', '皮蛋豆腐', '20.00', 'dd9b479a-2af1-47ba-9155-85294ba5a601', '0', '947584e3-e5de-4351-9eef-3c8bfba963fc_1679319856724.jpg', '2023-03-20 21:44:37');
INSERT INTO `wx_food` VALUES ('49b366d2-eaa2-4fa7-829b-d92a3a67b251', '蔬菜面', '8.00', '691c5a7f-c3f6-4f7d-91ca-aa03f70c615b', '0', '1a8d3cdc-3270-4c88-be7d-5fa69011be84_1679319546987.jpg', '2023-03-20 21:39:29');
INSERT INTO `wx_food` VALUES ('52acd318-7ccf-4757-8d79-4cdf43eeeefb', '手打鲜牛肉丸', '30.00', '33424052-84d7-4341-a42e-4ccaa3ac5f71', '4', '73179639-29ad-4ca8-801d-cf70ca3659c0_123.jpg', '2023-03-20 21:25:19');
INSERT INTO `wx_food` VALUES ('6711c782-3937-4fda-a472-6b8e0cab2379', '可乐', '6.00', '8d520ecc-11e7-4b6a-8e6a-7453887b0904', '4', 'cc38d193-9974-46cc-b876-5cab0b50ddee_1679383070979.png', '2023-03-21 15:18:08');
INSERT INTO `wx_food` VALUES ('6be8bc53-660c-46b6-8083-8c302f2d8793', '雪碧', '6.00', '8d520ecc-11e7-4b6a-8e6a-7453887b0904', '3', 'ad3ca1c2-4e31-4d68-9872-d83ec3ed6a91_1679383020610.png', '2023-03-21 15:17:29');
INSERT INTO `wx_food` VALUES ('71a8f3ed-8835-4940-badc-452b0745fc6f', '葱油饼', '4.00', '729b17da-f128-4f3e-8c37-ef5fbb111be7', '3', 'b4d52d94-bc84-4b66-b185-fd64a9b6da5d_1679382952637.png', '2023-03-21 15:16:26');
INSERT INTO `wx_food` VALUES ('7eb8c392-a9d1-4ca3-b344-3629942a1688', '手擀面', '8.00', '691c5a7f-c3f6-4f7d-91ca-aa03f70c615b', '2', '47394c82-1fe2-463d-a344-34e252fc2a1a_1679320311114.jpg', '2023-03-20 21:52:13');
INSERT INTO `wx_food` VALUES ('8b2ea544-f675-45bf-bf11-57075f184b79', '精品肥牛', '50.00', '33424052-84d7-4341-a42e-4ccaa3ac5f71', '1', '67bcac81-c4fb-40b7-8a6e-f4a68bc5720f_fn.jpg', '2023-03-20 21:17:57');
INSERT INTO `wx_food` VALUES ('968d494f-ea3a-44e9-99c1-2094983421ae', '手切鲜羊肉', '45.00', '33424052-84d7-4341-a42e-4ccaa3ac5f71', '3', '44af4ff8-716f-4d9e-9083-a33bfc731d34_sqy.jpg', '2023-03-20 21:21:10');
INSERT INTO `wx_food` VALUES ('9be1bd93-c283-4eb8-befc-4280e3f627ba', '干豆皮', '10.00', 'a7745da2-a44c-49db-9ff5-d308433e4fc9', '5', 'd9e6a0ce-7635-4788-bd26-d6efa7e6b6ad_1679319641739.jpg', '2023-03-20 21:41:14');
INSERT INTO `wx_food` VALUES ('a2f2f7f0-30b5-4a82-b8d4-bdccf4038335', '方便面', '5.00', '691c5a7f-c3f6-4f7d-91ca-aa03f70c615b', '1', 'c544f8b9-c4a2-44e3-8ae7-2eee15b3bad6_1679320250419.jpg', '2023-03-20 21:51:10');
INSERT INTO `wx_food` VALUES ('b37a48e0-ae9a-4a00-b16b-4e9453732497', '大白菜', '5.00', 'a7745da2-a44c-49db-9ff5-d308433e4fc9', '4', 'd0c5a56e-dd7a-4f13-bd77-26c7cd3b3bdf_1679320615821.jpg', '2023-03-20 21:57:24');
INSERT INTO `wx_food` VALUES ('b850a436-9643-430f-be8a-f9003f90c23b', '红油锅底', '20.00', '2c5926b4-c59a-4dbe-8d19-9d7dfce01481', '3', '148d1aa6-a80d-4504-b09e-5181efaf7dbc_111.jpg', '2023-03-20 21:16:09');
INSERT INTO `wx_food` VALUES ('b8af64ae-db6e-4dd3-8668-6b031463b710', '土豆片', '5.00', 'a7745da2-a44c-49db-9ff5-d308433e4fc9', '2', '38e0706c-2fdb-4f36-879b-1982ff606aeb_1679320505549.jpg', '2023-03-20 21:55:24');
INSERT INTO `wx_food` VALUES ('bb3ec075-60ea-491e-a984-cb3871585605', '牛肚', '35.00', '33424052-84d7-4341-a42e-4ccaa3ac5f71', '5', '3cec61e5-dc73-4fa5-8d0c-d12938cf9c81_1679319317346.jpg', '2023-03-20 21:35:54');
INSERT INTO `wx_food` VALUES ('c4c21997-a495-4653-8172-ea16a74b69b0', '海螺肉', '20.00', '54d60994-7e8a-4ab2-862f-c155a3f9da8c', '5', '547c45ae-88cb-42aa-9272-072fc905c72f_1679383232972.png', '2023-03-21 15:21:05');
INSERT INTO `wx_food` VALUES ('c52722bd-1f6c-4df2-86b7-710746584133', '鱿鱼须', '25.00', '54d60994-7e8a-4ab2-862f-c155a3f9da8c', '3', '4601db94-ba1e-4d93-aece-456e34bc0abf_1679383164875.png', '2023-03-21 15:19:50');
INSERT INTO `wx_food` VALUES ('d6126d68-2fb8-42e3-9831-7bff59e93c1c', '金针菇', '12.00', 'a7745da2-a44c-49db-9ff5-d308433e4fc9', '1', '91eac796-d081-40ba-b3c7-364715d23faf_1679320423942.jpg', '2023-03-20 21:54:29');
INSERT INTO `wx_food` VALUES ('d9ff403d-3a7f-4137-a6be-93a6d19d49de', '小酥肉', '23.00', '729b17da-f128-4f3e-8c37-ef5fbb111be7', '0', '512577e0-765b-4c4d-964b-43ed2ff003bd_f14046ac345ea8aba3cdfca457edd345.jpg', '2023-03-20 21:38:11');
INSERT INTO `wx_food` VALUES ('fbace8ed-8ef5-4dd2-8f18-8fb216a6e2e8', '茴香小油条', '14.00', '729b17da-f128-4f3e-8c37-ef5fbb111be7', '5', '406aa3d7-084b-4ccb-8eb9-85c4c6741709_12312.png', '2023-03-21 15:12:46');

-- ----------------------------
-- Table structure for `wx_food_type`
-- ----------------------------
DROP TABLE IF EXISTS `wx_food_type`;
CREATE TABLE `wx_food_type` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_food_type
-- ----------------------------
INSERT INTO `wx_food_type` VALUES ('2c5926b4-c59a-4dbe-8d19-9d7dfce01481', '锅底', '1', '2023-03-20 20:52:25');
INSERT INTO `wx_food_type` VALUES ('33424052-84d7-4341-a42e-4ccaa3ac5f71', '牛羊肉类', '2', '2023-03-20 20:49:30');
INSERT INTO `wx_food_type` VALUES ('54d60994-7e8a-4ab2-862f-c155a3f9da8c', '海鲜类', '3', '2023-03-20 20:53:16');
INSERT INTO `wx_food_type` VALUES ('691c5a7f-c3f6-4f7d-91ca-aa03f70c615b', '主食类', '6', '2023-03-20 20:50:26');
INSERT INTO `wx_food_type` VALUES ('729b17da-f128-4f3e-8c37-ef5fbb111be7', '小吃类', '5', '2023-03-20 20:50:19');
INSERT INTO `wx_food_type` VALUES ('8d520ecc-11e7-4b6a-8e6a-7453887b0904', '酒水饮料', '8', '2023-03-20 20:50:57');
INSERT INTO `wx_food_type` VALUES ('a7745da2-a44c-49db-9ff5-d308433e4fc9', '素菜类', '4', '2023-03-20 20:50:08');
INSERT INTO `wx_food_type` VALUES ('dd9b479a-2af1-47ba-9155-85294ba5a601', '凉菜类', '7', '2023-03-20 20:50:42');

-- ----------------------------
-- Table structure for `wx_order`
-- ----------------------------
DROP TABLE IF EXISTS `wx_order`;
CREATE TABLE `wx_order` (
  `id` varchar(36) NOT NULL,
  `userid` varchar(36) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `totalmoney` decimal(11,2) DEFAULT NULL,
  `totalnum` int(11) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_order
-- ----------------------------

-- ----------------------------
-- Table structure for `wx_order_info`
-- ----------------------------
DROP TABLE IF EXISTS `wx_order_info`;
CREATE TABLE `wx_order_info` (
  `id` varchar(36) NOT NULL,
  `orderid` varchar(36) DEFAULT NULL,
  `foodid` varchar(36) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `totalprice` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_order_info
-- ----------------------------

-- ----------------------------
-- Table structure for `wx_user`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` varchar(36) NOT NULL,
  `openid` varchar(36) NOT NULL,
  `score` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `lastlogintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wx_user_openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_user
-- ----------------------------

-- ----------------------------
-- Table structure for `wx_user_level`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_level`;
CREATE TABLE `wx_user_level` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `maxscore` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_user_level
-- ----------------------------
INSERT INTO `wx_user_level` VALUES ('bd2cc52a-3d3e-4bff-8031-d6f1db1e89bb', '黄金会员', '300', '2023-03-06 21:46:43');
INSERT INTO `wx_user_level` VALUES ('d7b8f156-bdfc-4a89-af3d-1dcafcb0915a', '钻石会员', '500', '2023-03-06 21:47:05');
INSERT INTO `wx_user_level` VALUES ('db3994ea-52b0-44b6-b464-bcad56527221', '白银会员', '100', '2023-03-06 21:46:35');
