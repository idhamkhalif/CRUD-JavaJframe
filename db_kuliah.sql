-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2023 at 04:13 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kuliah`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mhs`
--

CREATE TABLE `tbl_mhs` (
  `nim` char(50) NOT NULL,
  `nama` varchar(500) NOT NULL,
  `Jenjang` varchar(50) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `nohp` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_mhs`
--

INSERT INTO `tbl_mhs` (`nim`, `nama`, `Jenjang`, `gender`, `alamat`, `nohp`) VALUES
('1111', 'Alexa1', 'S1', 'Laki - Laki', 'Jakarta', '111'),
('112233', 'Chintiya Dewi Anggriani', 'S1', 'Perempuan', 'Ciputat', '11111'),
('123', 'as', 'S1', 'Laki - Laki', 'as', 'as'),
('135150301111142', 'Mhd. Idham Khalif', 'S1', 'Laki - Laki', 'Malang', '083835361588'),
('2006494501', 'Mhd. Idham Khalif', 'S2', 'Laki - Laki', 'Depok', '082287339661');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_mhs`
--
ALTER TABLE `tbl_mhs`
  ADD PRIMARY KEY (`nim`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
