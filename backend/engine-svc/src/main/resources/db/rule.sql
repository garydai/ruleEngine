use sally_rule;

DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uuid` varchar (64) NOT NULL COMMENT 'rule唯一id',
  `input` text NOT NULL COMMENT '前端输入',
  `drl` text NOT NULL COMMENT 'drl',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
) COMMENT '规则表'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
