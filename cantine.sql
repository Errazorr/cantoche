-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 25 avr. 2020 à 14:05
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
CREATE DATABASE IF NOT EXISTS `cantine` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cantine`;

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
  `jours` varchar(11) NOT NULL,
  `regime` varchar(20) NOT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `role` varchar(5) NOT NULL,
  `prix_mensuel` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `nom`, `prenom`, `identifiant`, `classe`, `DP`, `jours`, `regime`, `mdp`, `role`, `prix_mensuel`) VALUES
(1, 'Goncalves', 'Nathan', 'test', NULL, 'non', '', '', 'test', 'admin', 0),
(2, 'Guo', 'Loïc', NULL, NULL, 'non', '', '', NULL, 'prof', 0),
(3, 'Birba', 'Enzo', NULL, 'BTS SLAM 1', 'oui', 'L,Ma,Me', '', NULL, 'eleve', 72),
(4, 'Aggoun', 'Adam', NULL, 'BTS SLAM 2', 'oui', 'L,Ma', 'Hallal', NULL, 'eleve', 48),
(5, 'Serva', 'Théo', NULL, 'BTS SLAM 2', 'Oui', 'Me,V', '', NULL, 'eleve', 48),
(6, 'Dauwe', 'Marc', NULL, 'BTS SLAM 1', 'oui', 'L,Ma,Me,J,V', '', NULL, 'eleve', 120),
(7, 'Charles', 'Maxime', NULL, 'TSTI2D', 'oui', 'V', '', NULL, 'eleve', 24),
(8, 'Remacle', 'Denis', NULL, 'BTS SLAM 2', 'non', '', '', NULL, 'eleve', 0),
(10, 'Carmone', 'Alexandre', NULL, NULL, 'oui', 'L,Me', '', NULL, 'prof', 48),
(12, 'Gobert', 'P', NULL, NULL, 'oui', 'V', 'Vegetarienne', NULL, 'prof', 24),
(13, 'a', 'a', NULL, 'BTS', 'oui', 'L,Ma', '', NULL, 'eleve', 48),
(14, 'z', 'z', NULL, 'BTS SISR', 'oui', 'L,Ma,Me', '', NULL, 'eleve', 72);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
