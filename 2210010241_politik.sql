-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 14, 2025 at 06:38 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2210010241_politik`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_anggota`
--

CREATE TABLE `tb_anggota` (
  `id_anggota` int(11) NOT NULL,
  `nik` varchar(16) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') NOT NULL,
  `status` enum('Aktif','NonAktif') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_anggota`
--

INSERT INTO `tb_anggota` (`id_anggota`, `nik`, `nama`, `alamat`, `tgl_lahir`, `jenis_kelamin`, `status`) VALUES
(1001, '0128763', 'Khairul Fahmi', 'Jl Tanjung Selatan', '2004-01-03', 'Laki-laki', 'Aktif'),
(1002, '0128', 'Enoel', 'Bumi', '2004-01-03', 'Perempuan', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `tb_berkas`
--

CREATE TABLE `tb_berkas` (
  `id_berkas` int(11) NOT NULL,
  `id_anggota` int(11) NOT NULL,
  `tgl_upload` date NOT NULL,
  `status_berkas` enum('Diterima','Ditolak') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_berkas`
--

INSERT INTO `tb_berkas` (`id_berkas`, `id_anggota`, `tgl_upload`, `status_berkas`) VALUES
(1, 1001, '2025-01-12', 'Diterima');

-- --------------------------------------------------------

--
-- Table structure for table `tb_dprd`
--

CREATE TABLE `tb_dprd` (
  `id_dprd` int(11) NOT NULL,
  `id_anggota` int(11) NOT NULL,
  `periode_jabatan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_dprd`
--

INSERT INTO `tb_dprd` (`id_dprd`, `id_anggota`, `periode_jabatan`) VALUES
(1, 1001, '2006 - 2011');

-- --------------------------------------------------------

--
-- Table structure for table `tb_periode_kegiatan`
--

CREATE TABLE `tb_periode_kegiatan` (
  `id_periode_kegiatan` int(11) NOT NULL,
  `id_dprd` int(11) NOT NULL,
  `status` enum('Aktif','NonAktif') NOT NULL,
  `tgl_kegiatan` date NOT NULL,
  `jam_kegiatan` time NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `keperluan` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_periode_kegiatan`
--

INSERT INTO `tb_periode_kegiatan` (`id_periode_kegiatan`, `id_dprd`, `status`, `tgl_kegiatan`, `jam_kegiatan`, `lokasi`, `keperluan`) VALUES
(124, 1, 'NonAktif', '2012-01-12', '23:33:59', 'Bumi Selatan', 'Inspeksi'),
(125, 12, 'Aktif', '2013-01-12', '23:33:59', 'Bumi Utaraa', 'Inspeksi'),
(126, 12, 'Aktif', '2013-01-12', '23:33:59', 'Bumi Utaraa', 'Inspeksi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_anggota`
--
ALTER TABLE `tb_anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indexes for table `tb_berkas`
--
ALTER TABLE `tb_berkas`
  ADD PRIMARY KEY (`id_berkas`);

--
-- Indexes for table `tb_dprd`
--
ALTER TABLE `tb_dprd`
  ADD PRIMARY KEY (`id_dprd`);

--
-- Indexes for table `tb_periode_kegiatan`
--
ALTER TABLE `tb_periode_kegiatan`
  ADD PRIMARY KEY (`id_periode_kegiatan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
