CREATE DATABASE `restaurant` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*创建员工信息表*/
CREATE TABLE `worker` (
  `w_id` char(5) NOT NULL,
  `w_password` char(10) NOT NULL,
  `w_name` char(10) NOT NULL,
  `w_type` char(5) DEFAULT NULL,
  `w_sex` char(3) NOT NULL,
  `w_workTime` float(3,1) DEFAULT NULL,
  `w_path` varchar(255) DEFAULT NULL,
  `w_filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`w_id`,`w_name`),
  KEY `index_w_name` (`w_name`),
  KEY `index_w_id` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*创建菜品信息表*/
CREATE TABLE `menu` (
  `m_id` char(5) NOT NULL,
  `m_name` char(10) NOT NULL,
  `m_price` float(6,2) NOT NULL,
  `m_inventory` int(6) NOT NULL,
  `m_type` char(10) DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `index_m_id` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*创建餐桌信息表*/
CREATE TABLE `diningtable` (
  `d_id` char(5) NOT NULL,
  `d_no` char(5) NOT NULL,
  `d_status` char(5) NOT NULL,
  `d_num` int(6) DEFAULT '0',
  `d_cost` float(7,2) DEFAULT '0.00',
  PRIMARY KEY (`d_id`),
  KEY `index_d_id` (`d_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*创建餐厅信息表*/
CREATE TABLE `information` (
  `info_id` char(5) NOT NULL,
  `info_name` char(15) NOT NULL,
  `w_name` char(10) DEFAULT NULL,
  `info_address` char(25) NOT NULL,
  `info_startDate` datetime NOT NULL,
  `info_businessHours` char(15) NOT NULL,
  PRIMARY KEY (`info_id`),
  KEY `fk_info_owner` (`w_name`),
  CONSTRAINT `fk_info_owner` FOREIGN KEY (`w_name`) REFERENCES `worker` (`w_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*创建订单信息表*/
CREATE TABLE `orderinfo` (
  `o_id` char(5) NOT NULL,
  `o_price` float(7,2) NOT NULL,
  `o_serverTime` datetime NOT NULL,
  `w_id` char(5) DEFAULT NULL,
  `m_id` char(5) DEFAULT NULL,
  `d_id` char(5) DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  KEY `fk_o_workerid` (`w_id`),
  KEY `fk_o_menuid` (`m_id`),
  KEY `fk_o_tableid` (`d_id`),
  KEY `index_o_id` (`o_id`),
  KEY `index_o_serverTime` (`o_serverTime`),
  CONSTRAINT `fk_o_menuid` FOREIGN KEY (`m_id`) REFERENCES `menu` (`m_id`),
  CONSTRAINT `fk_o_tableid` FOREIGN KEY (`d_id`) REFERENCES `diningtable` (`d_id`),
  CONSTRAINT `fk_o_workerid` FOREIGN KEY (`w_id`) REFERENCES `worker` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*创建结账流水表*/
CREATE TABLE `chargeinfo` (
  `c_id` char(5) NOT NULL,
  `o_id` char(5) DEFAULT NULL,
  `w_id` char(5) DEFAULT NULL,
  `d_id` char(5) DEFAULT NULL,
  `o_serverTime` datetime DEFAULT NULL,
  `c_requestReceive` float(7,2) DEFAULT '0.00',
  `c_factReceive` float(7,2) DEFAULT '0.00',
  `c_returnMoney` float(7,2) DEFAULT '0.00',
  `c_giveBillflg` char(3) DEFAULT NULL,
  `c_remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_c_orderid` (`o_id`),
  KEY `fk_c_workerid` (`w_id`),
  KEY `fk_c_tableid` (`d_id`),
  KEY `fk_c_serverTime` (`o_serverTime`),
  CONSTRAINT `fk_c_orderid` FOREIGN KEY (`o_id`) REFERENCES `orderinfo` (`o_id`),
  CONSTRAINT `fk_c_serverTime` FOREIGN KEY (`o_serverTime`) REFERENCES `orderinfo` (`o_serverTime`),
  CONSTRAINT `fk_c_tableid` FOREIGN KEY (`d_id`) REFERENCES `diningtable` (`d_id`),
  CONSTRAINT `fk_c_workerid` FOREIGN KEY (`w_id`) REFERENCES `worker` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


