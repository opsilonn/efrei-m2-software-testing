-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  Dim 03 jan. 2021 à 13:58
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `edg`
--

-- --------------------------------------------------------

--
-- Structure de la table `contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract` (
  `idContract` int(11) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `electricalConsumption` float DEFAULT NULL,
  `user_idUser` int(11) NOT NULL,
  PRIMARY KEY (`idContract`),
  KEY `fk_contract_user_idx` (`user_idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `contract`
--

INSERT INTO `contract` (`idContract`, `price`, `date`, `electricalConsumption`, `user_idUser`) VALUES
(1, 120, '2020-12-24', 125, 1),
(2, 120, '2020-12-27', 125, 1),
(6, 120, '2017-05-10', 1400, 4),
(7, 120, '2016-05-11', 100, 5);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `civility` varchar(45) DEFAULT NULL,
  `nameFirst` varchar(35) DEFAULT NULL,
  `nameLast` varchar(35) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `zipCode` varchar(10) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `civility`, `nameFirst`, `nameLast`, `address`, `zipCode`, `city`, `date_creation`) VALUES
(1, 'Mr.', 'francois', 'Boni', '31 route de la borde', '75024', 'Paris', '2020-11-24'),
(3, 'Mr.', 'Alexandre ', 'Codarin', '26 Boulevard La Chapelle', '75019', 'Paris', '2020-11-24'),
(4, 'Mr.', 'Mariano', 'Boni', '31 boulebard carnot', '78400', 'chatou', '1997-03-10'),
(5, 'Mme', 'pinpin', 'le lutin', '189 route de la republique', '94800', 'villejuif', '2020-03-10'),
(8, 'Mme', 'Isabel', 'Le goff', '40 rue jacq ophenbach', '75016', 'Paris', '2020-11-28'),
(9, 'Mr.', 'John', 'DOE', '30-32, Avenue de la République', '94800', 'Villejuif', '2016-08-24');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `fk_contract_user` FOREIGN KEY (`user_idUser`) REFERENCES `user` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
