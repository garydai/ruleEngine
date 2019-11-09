DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ''自增id'',
  `username` varchar (128) NOT NULL COMMENT ''用户名'',
  `password` varchar (128) NOT NULL COMMENT ''密码'',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT ''更新时间'',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uk_username`(`username`)
) COMMENT ''用户表''  ENGINE=InnoDB DEFAULT CHARSET=utf8;
