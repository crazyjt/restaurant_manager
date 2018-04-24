CREATE DATABASE `restaurant` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*员工信息表*/
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

/*餐厅信息表*/
CREATE TABLE `information` (
  `info_id` char(5) NOT NULL,
  `info_name` char(15) NOT NULL,
  `w_name` char(10) DEFAULT NULL,
  `info_address` char(25) NOT NULL,
  `info_startDate` datetime NOT NULL,
  `info_businessHours` char(15) NOT NULL,
  PRIMARY KEY (`info_id`),
  KEY `FK7556752C7AA68484` (`w_name`),
  CONSTRAINT `fk_info_owner` FOREIGN KEY (`w_name`) REFERENCES `worker` (`w_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*菜单信息表*/
CREATE TABLE `menu` (
  `m_id` char(5) NOT NULL,
  `m_name` char(10) NOT NULL,
  `m_price` float(6,2) NOT NULL,
  `m_inventory` int(6) NOT NULL,
  `m_type` char(10) DEFAULT NULL,
  `m_path` varchar(255) DEFAULT NULL,
  `m_filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `index_m_id` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*餐桌信息表*/
CREATE TABLE `diningtable` (
  `d_id` char(5) NOT NULL,
  `d_no` char(5) NOT NULL,
  `d_status` char(5) NOT NULL,
  `d_num` int(6) DEFAULT '0',
  PRIMARY KEY (`d_id`),
  KEY `index_d_id` (`d_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*订单信息表*/
CREATE TABLE `orderinfo` (
  `o_id` char(5) NOT NULL,
  `o_price` float(7,2) NOT NULL,
  `o_serverTime` datetime NOT NULL,
  `w_id` char(5) DEFAULT NULL,
  `d_id` char(5) DEFAULT NULL,
  `o_pay` varchar(5) NOT NULL,
  `m_order` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  KEY `fk_o_tableid` (`d_id`),
  KEY `index_o_id` (`o_id`),
  KEY `index_o_serverTime` (`o_serverTime`),
  KEY `fk_o_workerid` (`w_id`),
  CONSTRAINT `fk_o_tableid` FOREIGN KEY (`d_id`) REFERENCES `diningtable` (`d_id`),
  CONSTRAINT `fk_o_workerid` FOREIGN KEY (`w_id`) REFERENCES `worker` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*订单菜品中间表*/
CREATE TABLE `m_o_table` (
  `o_id` varchar(255) NOT NULL,
  `m_id` varchar(255) NOT NULL DEFAULT 'null',
  PRIMARY KEY (`m_id`,`o_id`),
  KEY `FK46333A2CC05C2374` (`o_id`),
  KEY `FK46333A2C2D60AFFF` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*结账流水表*/
CREATE TABLE `chargeinfo` (
  `c_id` char(5) NOT NULL,
  `o_id` char(5) NOT NULL,
  `w_id` char(5) DEFAULT NULL,
  `d_id` char(5) DEFAULT NULL,
  `c_requestReceive` float(7,2) DEFAULT '0.00',
  `c_factReceive` float(7,2) DEFAULT '0.00',
  `c_returnMoney` float(7,2) DEFAULT '0.00',
  `c_giveBillflg` char(3) DEFAULT NULL,
  `c_remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_c_workerid` (`w_id`),
  KEY `fk_c_tableid` (`d_id`),
  KEY `fk_c_orderid` (`o_id`),
  CONSTRAINT `fk_c_orderid` FOREIGN KEY (`o_id`) REFERENCES `orderinfo` (`o_id`),
  CONSTRAINT `fk_c_tableid` FOREIGN KEY (`d_id`) REFERENCES `diningtable` (`d_id`),
  CONSTRAINT `fk_c_workerid` FOREIGN KEY (`w_id`) REFERENCES `worker` (`w_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
