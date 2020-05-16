INSERT INTO `users` (`iduser`, `email`, `firstname`, `login`, `password`, `lastname`, `created_at`, `updated_at`, `address`, `num_tel`) VALUES
(1, 'nicddocavs@gmail.com', 'Nicolas', 'admin', '$2y$10$hD1rtWWv1qAjPBtNRLFGYua0wDMOcaEvms/MttsVEmVenXXqeQ7iy', 'Vera', NULL, NULL, '5 rue general leclerc','0612354125'),
(16, 'test@gmail.com', 'Compte', 'test', '$2y$10$6gCj5ux7eCyANd86qa86uuxqVCEguyfEmTxUN7eALmiO8kqTFjkDq', 'Test', NULL, NULL, '11 rue de la paix','0606060606'),
(24, 'nicolas.vera77@gmail.com', 'rezaraz', 'ezarza', '$2y$10$XyJ5HvjySoO4lCw4tMIgPeowKb2vYxWoZLFCBkEi2t6.hi7MPverW', 'rarez', NULL, NULL, '89 boulevard jaques anquetil','0741412563'),
(25, '123@gmail.com', 'xcvcxv', 'vfwcvxv', '$2y$10$KxZkDV6Fq.rutyMf2lgxWu73h/g03wJkkmcy5.2GkyAVREjRu6li.', 'cxvxv', NULL, NULL, '25 rue piquar','0125647896');

INSERT INTO `sensor` (`IDSENSOR`, `IDUSER`, `NAMESENSOR`) VALUES
(1, '1', 'Capteur CO2'),
(2, '1', 'Capteur CO'),
(3, '1', 'Capteur NOX'),
(4, '1', 'Capteur Rn'),
(100, '16', NULL);

INSERT INTO `datas` (`IDDATA`, `IDSENSOR`, `IDDATATYPE`, `DATETIMEDATA`, `DATASENSOR`) VALUES
(1, '1', 1, '2019-05-01 12:17:09', '40'),
(2, '1', 1, '2019-05-01 00:16:21', '31'),
(3, '3', 3, '2019-05-12 10:24:25', '19'),
(4, '4', 2, '2019-05-07 17:19:20', '80'),
(5, '100', 1, '2019-05-07 17:19:20', '28');

INSERT INTO `notification` (`idnotif`, `userid`, `textnotification`, `datenotif`) VALUES
(1, 1, 'Le capteur 1 a été ajouté', '2019-10-15 15:00:00'),
(2, 1, 'le capteur 2 a été ajouté', '2019-10-16 11:00:00');