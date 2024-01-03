CREATE TABLE IF NOT EXISTS `cards` (
`card_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
`mobile_number` varchar(20) NOT NULL,
`card_number` varchar(100) NOT NULL,
`card_type` varchar(100) NOT NULL,
`total_limit` BIGINT NOT NULL,
`amount_used` BIGINT NOT NULL,
`available_amount` BIGINT NOT NULL,
`created_at` date NOT NULL,
`created_by` varchar(20) NOT NULL,
`updated_at` date DEFAULT NULL,
`updated_by` varchar(20) DEFAULT NULL
);
