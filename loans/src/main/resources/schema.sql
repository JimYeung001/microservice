CREATE TABLE IF NOT EXISTS `loans` (
`loan_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
`mobile_number` varchar(20) NOT NULL,
`loan_number` varchar(100) NOT NULL,
`loan_type` varchar(100) NOT NULL,
`total_loan` BIGINT NOT NULL,
`amount_paid` BIGINT NOT NULL,
`outstanding_amount` BIGINT NOT NULL,
`created_at` date NOT NULL,
`created_by` varchar(20) NOT NULL,
`updated_at` date DEFAULT NULL,
`updated_by` varchar(20) DEFAULT NULL
);
