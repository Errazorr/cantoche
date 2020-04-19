-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 01 avr. 2020 à 15:02
-- Version du serveur :  10.4.10-MariaDB
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
-- Base de données :  `cantine`
--

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `identifiant` varchar(50) DEFAULT NULL,
  `classe` varchar(20) DEFAULT NULL,
  `DP` char(3) NOT NULL,
  `jours` varchar(11) DEFAULT NULL,
  `regime` varchar(20) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `role` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `nom`, `prenom`, `identifiant`, `classe`, `DP`, `jours`, `regime`, `mdp`, `role`) VALUES
(1, 'Goncalves', 'Nathan', 'test', NULL, 'non', NULL, NULL, 'test', 'admin'),
(2, 'Guo', 'Loïc', NULL, NULL, 'non', NULL, NULL, NULL, 'prof'),
(3, 'Aggoun', 'Adam', NULL, 'BTS SIO SLAM 1', 'oui', 'L,Ma', 'Pas de porc', NULL, 'eleve'),
(5, 'Birba', 'Enzo', NULL, NULL, 'Oui', 'L,Ma,J', '', NULL, 'prof'),
(6, 'Serva', 'Théo', NULL, 'STI2D', 'Oui', 'Lu,V', 'Vegan', NULL, 'eleve'),
(7, 'a', 'z', NULL, 'e', 'oui', 'L', '', NULL, 'eleve');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
