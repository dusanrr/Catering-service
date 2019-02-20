-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2017 at 02:19 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE IF NOT EXISTS `korisnik` (
  `korisnik_id` int(4) NOT NULL,
  `korisnik_imeprezime` varchar(35) NOT NULL,
  `korisnik_username` varchar(25) NOT NULL,
  `korisnik_password` varchar(20) NOT NULL,
  `korisnik_email` varchar(35) NOT NULL,
  `korisnik_telefon` varchar(12) NOT NULL,
  `korisnik_adresa` varchar(20) NOT NULL,
  `power` varchar(100) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`korisnik_id`, `korisnik_imeprezime`, `korisnik_username`, `korisnik_password`, `korisnik_email`, `korisnik_telefon`, `korisnik_adresa`, `power`) VALUES
(1, 'Dusan Rajkovic', 'adm', 'adm', 'dusan@gmail.com', '064243434', 'Stefana Markovica 1', 'Administrator'),
(2, 'Stefan Markovic', 'gm', 'gm', 'sm@gmail.com', '065243434', 'Stefana Markovica 1', 'Glavni menadzer'),
(3, 'Marko Pejcinovic', 'sk', 'sk', 'mp@gmail.com', '061243434', 'Ranka Puskarica 1', 'Sef kuhinje'),
(4, 'Zdravko Kuzmanovic', 'sp', 'sp', 'zk@gmail.com', '066243434', 'Puskinova 34', 'Sef poslovnice'),
(5, 'Aleksandra Grujic', 'kl', 'kl', 'ag@gmail.com', '063243434', 'Puskinova 38', 'Klijent');

-- --------------------------------------------------------

--
-- Table structure for table `korpa`
--

CREATE TABLE IF NOT EXISTS `korpa` (
  `id` int(11) NOT NULL,
  `korisnik_id` int(11) NOT NULL,
  `korisnik_imeprezime` varchar(50) NOT NULL,
  `proizvod_id` int(11) NOT NULL,
  `proizvod_naziv` varchar(30) NOT NULL,
  `proizvod_cena` double NOT NULL,
  `kolicina` int(11) NOT NULL,
  `ukupna_cena` varchar(100) NOT NULL,
  `adresa_dostave` varchar(100) NOT NULL,
  `datum_dostave` varchar(10) NOT NULL,
  `kategorija` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `narudzbine`
--

CREATE TABLE IF NOT EXISTS `narudzbine` (
  `id` int(11) NOT NULL,
  `korisnik_id` int(100) NOT NULL,
  `korisnik_imeprezime` varchar(50) NOT NULL,
  `proizvod_id` int(11) NOT NULL,
  `proizvod_naziv` varchar(100) NOT NULL,
  `proizvod_cena` double NOT NULL,
  `kolicina` int(100) NOT NULL,
  `ukupna_cena` double NOT NULL,
  `adresa_dostave` varchar(100) NOT NULL,
  `datum_dostave` varchar(100) NOT NULL,
  `kategorija` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `proizvodi`
--

CREATE TABLE IF NOT EXISTS `proizvodi` (
  `id_proizvoda` int(4) NOT NULL,
  `naziv_proizvoda` varchar(60) NOT NULL,
  `cena_proizvoda` double NOT NULL,
  `kolicina_proizvoda` int(50) NOT NULL,
  `kategorija_proizvoda` varchar(50) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proizvodi`
--

INSERT INTO `proizvodi` (`id_proizvoda`, `naziv_proizvoda`, `cena_proizvoda`, `kolicina_proizvoda`, `kategorija_proizvoda`) VALUES
(1, 'Pitice sa vocem', 300, 5, 'slatko'),
(2, 'Rolovana sunka', 500, 5, 'slano'),
(3, 'Pita sa jabukama', 330, 5, 'slatko'),
(4, 'Krempita', 345, 5, 'slatko'),
(5, 'Sushi od testenina', 370, 5, 'slano'),
(6, 'Cokoladna pita', 400, 5, 'slatko'),
(7, 'Pita sa raznim vocem', 350, 5, 'slatko');

-- --------------------------------------------------------

--
-- Table structure for table `reklamacije`
--

CREATE TABLE IF NOT EXISTS `reklamacije` (
  `reklamacija_id` int(100) NOT NULL,
  `reklamirao` varchar(100) NOT NULL,
  `proizvod_za_reklamaciju` varchar(100) NOT NULL,
  `status_reklamacije` varchar(100) NOT NULL,
  `opis_reklamacije` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`korisnik_id`);

--
-- Indexes for table `korpa`
--
ALTER TABLE `korpa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `narudzbine`
--
ALTER TABLE `narudzbine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `proizvodi`
--
ALTER TABLE `proizvodi`
  ADD PRIMARY KEY (`id_proizvoda`);

--
-- Indexes for table `reklamacije`
--
ALTER TABLE `reklamacije`
  ADD PRIMARY KEY (`reklamacija_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `korisnik_id` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `korpa`
--
ALTER TABLE `korpa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `narudzbine`
--
ALTER TABLE `narudzbine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `proizvodi`
--
ALTER TABLE `proizvodi`
  MODIFY `id_proizvoda` int(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `reklamacije`
--
ALTER TABLE `reklamacije`
  MODIFY `reklamacija_id` int(100) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
