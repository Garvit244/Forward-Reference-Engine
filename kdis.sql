-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 25, 2013 at 03:59 PM
-- Server version: 5.1.36
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kdis`
--

-- --------------------------------------------------------

--
-- Table structure for table `factbase`
--

CREATE TABLE IF NOT EXISTS `factbase` (
  `facts` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `factbase`
--

INSERT INTO `factbase` (`facts`) VALUES
('ANIMAL HAS HAIR'),
('ANIMAL FLIES VERY WELL'),
('ANIMAL HAS LONG LEGS'),
('ANIMAL GIVES MILK'),
('ANIMAL HAS FEATHERS'),
('ANIMAL LAYS EGGS'),
('ANIMAL HAS DARK SPOTS'),
('ANIMAL HAS LONG NECK'),
('ANIMAL HAS TAWNY COLOR'),
('ANIMAL CHEWS GRASS'),
('ANIMAL IS A OSTRICH'),
('ANIMAL IS A TIGER');

-- --------------------------------------------------------

--
-- Table structure for table `inferencearray`
--

CREATE TABLE IF NOT EXISTS `inferencearray` (
  `facts` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inferencearray`
--

INSERT INTO `inferencearray` (`facts`) VALUES
('ANIMAL IS A MAMMAL'),
('ANIMAL IS A HERBIVORE'),
('ANIMAL IS A GIRAFFE'),
('ANIMAL IS A BIRD'),
('ANIMAL IS A OSTRICH'),
('ANIMAL IS A ALBATROSS');

-- --------------------------------------------------------

--
-- Table structure for table `kbtable1`
--

CREATE TABLE IF NOT EXISTS `kbtable1` (
  `indx` varchar(3) DEFAULT NULL,
  `facts` varchar(50) DEFAULT NULL,
  `basic_derived` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `rules_in_lhs` varchar(20) DEFAULT NULL,
  `rules_in_rhs` varchar(20) DEFAULT NULL,
  `facts_derived_from` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kbtable1`
--

INSERT INTO `kbtable1` (`indx`, `facts`, `basic_derived`, `level`, `rules_in_lhs`, `rules_in_rhs`, `facts_derived_from`) VALUES
('F1', 'ANIMAL HAS HAIR', 0, 0, 'R1', 'NIL', 'NIL'),
('F2', 'ANIMAL GIVES MILK', 0, 0, 'R2', 'NIL', 'NIL'),
('F3', 'ANIMAL HAS FEATHERS', 0, 0, 'R3', 'NIL', 'NIL'),
('F4', 'ANIMAL FLIES', 0, 0, 'R4,R11', 'NIL', 'NIL'),
('F5', 'ANIMAL LAYS EGGS', 0, 0, 'R4', 'NIL', 'NIL'),
('F6', 'ANIMAL EATS MEAT', 0, 0, 'R5', 'NIL', 'NIL'),
('F7', 'ANIMAL CHEWS GRASS', 0, 0, 'R6', 'NIL', 'NIL'),
('F8', 'ANIMAL HAS TAWNY COLOR', 0, 0, 'R7,R8,R9,R10', 'NIL', 'NIL'),
('F9', 'ANIMAL HAS DARK SPOTS', 0, 0, 'R7,R9', 'NIL', 'NIL'),
('F10', 'ANIMAL HAS BLACK STRIPES', 0, 0, 'R8,R10', 'NIL', 'NIL'),
('F11', 'ANIMAL HAS LONG LEGS', 0, 0, 'R11', 'NIL', 'NIL'),
('F12', 'ANIMAL HAS LONG NECK', 0, 0, 'R11', 'NIL', 'NIL'),
('F13', 'ANIMAL FLIES VERY WELL', 0, 0, 'R12', 'NIL', 'NIL'),
('F15', 'ANIMAL IS A BIRD', 1, 1, 'R11,R12', 'R3,R4', 'F3+F4.F5'),
('F14', 'ANIMAL IS A MAMMAL', 1, 1, 'R5,R6', 'R1,R2', 'F1+F2'),
('F16', 'ANIMAL IS A CARNIVORE', 1, 2, 'R7,R8', 'R5', 'F14.F6'),
('F17', 'ANIMAL IS A HERBIVORE', 1, 2, 'R9,R10', 'R6', 'F14.F7'),
('F18', 'ANIMAL IS A CHEETAH', 1, 3, 'NIL', 'R7', 'F16.F8.F9'),
('F19', 'ANIMAL IS A TIGER', 1, 3, 'NIL', 'R8', 'F16.F8.F10'),
('F20', 'ANIMAL IS A GIRAFFE', 1, 3, 'NIL', 'R9', 'F17.F8.F9'),
('F21', 'ANIMAL IS A ZEBRA', 1, 3, 'NIL', 'R10', 'F17.F8.F10'),
('F22', 'ANIMAL IS A OSTRICH', 1, 3, 'NIL', 'R11', 'F15.!F4.F11.F12'),
('F23', 'ANIMAL IS A ALBATROSS', 1, 3, 'NIL', 'R12', 'F15.F13');

-- --------------------------------------------------------

--
-- Table structure for table `kbtable2`
--

CREATE TABLE IF NOT EXISTS `kbtable2` (
  `rule_name` varchar(3) DEFAULT NULL,
  `Dependent_facts` varchar(20) DEFAULT NULL,
  `previous_rule` varchar(20) DEFAULT NULL,
  `derived_facts` varchar(20) DEFAULT NULL,
  `next_rule` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kbtable2`
--

INSERT INTO `kbtable2` (`rule_name`, `Dependent_facts`, `previous_rule`, `derived_facts`, `next_rule`) VALUES
('R1', 'F1', 'NIL', 'F14', 'R5,R6'),
('R2', 'F2', 'NIL', 'F14', 'R5,R6'),
('R3', 'F3', 'NIL', 'F15', 'R11,R12'),
('R4', 'F4.F5', 'NIL', 'F15', 'R11,R12'),
('R6', 'F14.F7', 'R1,R2', 'F17', 'R9,R10'),
('R5', 'F14.F6', 'R1,R2', 'F16', 'R7,R8'),
('R7', 'F16.F8.F9', 'R5', 'F18', 'NIL'),
('R8', 'F16.F8.F10', 'R5', 'F19', 'NIL'),
('R9', 'F17.F8.F9', 'R6', 'F20', 'NIL'),
('R10', 'F17.F8.F10', 'R6', 'F21', 'NIL'),
('R11', 'F15.!F4.F11.F12', 'R3,R4', 'F22', 'NIL'),
('R12', 'F15.F13', 'R3,R4', 'F23', 'NIL');

-- --------------------------------------------------------

--
-- Table structure for table `tem`
--

CREATE TABLE IF NOT EXISTS `tem` (
  `derived` varchar(20) DEFAULT NULL,
  `dependent` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tem`
--

