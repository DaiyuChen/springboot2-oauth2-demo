DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `archived` bit(1) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `last_modified_datetime` datetime(6) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `archived` bit(1) DEFAULT NULL,
  `created_datetime` datetime(6) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `last_modified_datetime` datetime(6) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `access_rights` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `oauth_client_details` VALUES (1,'\0','2020-08-13 16:48:42.969000','ca966d45-dd41-11ea-88d2-596aed71d9bc','2020-08-13 16:48:42.993000',0,86400,NULL,'ROLE_TEST','authorization_code,client_credentials,password,refresh_token',NULL,'test','$2a$10$0ah7zpCAvrP/a65xPWgK0.pbZ0Zv2ec3TQkKcLStCHX17N465gTHm',259200,'http://localhost:8000/auth/callback',NULL,'USER,ORDER,TEST');
INSERT INTO `user` VALUES (1,'\0','2020-08-05 11:13:20.566000','9d656609-d6c9-11ea-aed9-01b93a686bc2','2020-08-05 11:13:20.585000',0,'ADMIN,USER,API','$2a$10$JtmJ70AMzSvira/LT2dVAuWS4OF/NM0BWIWY8rOdtjO5qwwybzV.2','admin');