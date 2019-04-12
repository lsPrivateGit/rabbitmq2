CREATE TABLE `t_order` (
  `id` VARCHAR(128) NOT NULL COMMENT '订单ID',
  `name` VARCHAR(128)  NOT NULL COMMENT '订单名称',
  `message_id` VARCHAR(128)  NOT NULL COMMENT '消息唯一ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `broker_message_log` (
  `message_id` VARCHAR(128)  NOT NULL COMMENT '消息唯一ID',
  `message` VARCHAR(4000)  DEFAULT NULL COMMENT '消息内容',
  `try_count` INT(4) NOT null DEFAULT '0' COMMENT '重试次数',
  `status` VARCHAR(10) DEFAULT '' COMMENT '消息投递状态 0投递中 1投递成功 2投递失败',
  `next_retry` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '下一次执行时间',
  `create_time` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL  DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`message_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



SELECT * FROM t_order;
SELECT * FROM broker_message_log;