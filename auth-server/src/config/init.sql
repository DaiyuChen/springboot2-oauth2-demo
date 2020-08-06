CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(2560) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

INSERT INTO `oauth_client_details` VALUES ('test', null, '$2a$10$kJRyqJa9vjb9CEs2ewHLLeU/hH5iVBQpi1Nm8nQbMrL5nsoHWJ0qO', 'ALL,USER,ORDER', 'authorization_code,client_credentials,password,refresh_token', 'http://localhost:8000/auth/callback', 'ROLE_USER', '1800', '86400', null, 'false');
INSERT INTO `user` VALUES (1,'\0','2020-08-05 11:13:20.566000','9d656609-d6c9-11ea-aed9-01b93a686bc2','2020-08-05 11:13:20.585000',0,'ADMIN,USER,API','$2a$10$JtmJ70AMzSvira/LT2dVAuWS4OF/NM0BWIWY8rOdtjO5qwwybzV.2','admin');