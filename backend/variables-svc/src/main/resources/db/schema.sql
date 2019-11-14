use sally_variable;

DROP TABLE IF EXISTS `variable`;
CREATE TABLE `variable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `type` varchar (128) NOT NULL COMMENT '类型',
  `name` varchar (128) NOT NULL COMMENT '变量名',
  `description` varchar (128) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
) COMMENT '变量表'  ENGINE=InnoDB DEFAULT CHARSET=utf8;
