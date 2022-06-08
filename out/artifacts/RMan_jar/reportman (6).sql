-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2022 at 05:17 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reportman`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `categoryName` varchar(250) NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp(),
  `updatedAt` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `categoryName`, `createdAt`, `updatedAt`) VALUES
(3, 'IT', '2022-05-15', NULL),
(19, 'comm', '2022-05-18', NULL),
(20, 'company', '2022-05-18', NULL),
(21, 'industrial', '2022-05-18', NULL),
(22, 'financial', '2022-05-18', NULL),
(23, 'newCat', '2022-05-19', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `duties`
--

CREATE TABLE `duties` (
  `id` int(11) NOT NULL,
  `description` varchar(300) NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp(),
  `updatedAt` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` int(11) NOT NULL,
  `reportId` int(11) NOT NULL,
  `projectsText` mediumtext NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `reportId`, `projectsText`, `createdAt`) VALUES
(12, 41, 'wdw', '2022-06-03'),
(13, 42, 'rr', '2022-06-03'),
(14, 0, 'illill', '2022-06-03'),
(15, 43, '444', '2022-06-03'),
(16, 44, 'ee', '2022-06-03');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `reportDate` date NOT NULL,
  `catId` int(11) NOT NULL,
  `subCatId` int(11) NOT NULL,
  `reportText` longtext NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp(),
  `title` varchar(300) NOT NULL,
  `isRead` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `reportDate`, `catId`, `subCatId`, `reportText`, `createdAt`, `title`, `isRead`) VALUES
(41, '2022-06-03', 3, 55, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">wdwd</font></p></body></html>', '2022-06-03', 'wwww', 1),
(42, '2022-06-03', 3, 55, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">rrr</font></p></body></html>', '2022-06-03', 'rr', 0),
(43, '2022-06-03', 3, 55, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">561651</font></p></body></html>', '2022-06-03', '1651', 1),
(44, '2022-06-03', 19, 60, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font face=\"Segoe UI\">eeee</font></p></body></html>', '2022-06-03', 'eee', 1);

-- --------------------------------------------------------

--
-- Table structure for table `subcat`
--

CREATE TABLE `subcat` (
  `id` int(11) NOT NULL,
  `catId` int(11) NOT NULL,
  `subCatName` varchar(250) NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subcat`
--

INSERT INTO `subcat` (`id`, `catId`, `subCatName`, `createdAt`) VALUES
(55, 3, 'Analysis', '2022-05-15'),
(60, 19, 'cable', '2022-05-18'),
(61, 19, 'internet', '2022-05-18'),
(62, 19, 'wifi', '2022-05-18'),
(63, 19, 'troubleshooting troubleshooting troubleshooting troubleshooting', '2022-05-18'),
(65, 20, 'branch', '2022-05-18'),
(66, 23, 'subCat', '2022-05-19'),
(67, 23, 'subCat2', '2022-05-19'),
(68, 23, 'subCat3', '2022-05-19'),
(69, 23, 'subCat4', '2022-05-19');

-- --------------------------------------------------------

--
-- Table structure for table `suggestions`
--

CREATE TABLE `suggestions` (
  `id` int(11) NOT NULL,
  `reportId` int(11) NOT NULL,
  `suggestionText` mediumtext NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `suggestions`
--

INSERT INTO `suggestions` (`id`, `reportId`, `suggestionText`, `createdAt`) VALUES
(12, 41, 'wdw', '2022-06-03'),
(13, 42, 'rr', '2022-06-03'),
(14, 0, 'lioliol', '2022-06-03'),
(15, 43, '2222', '2022-06-03'),
(16, 44, 'ee', '2022-06-03');

-- --------------------------------------------------------

--
-- Table structure for table `uploadedimages`
--

CREATE TABLE `uploadedimages` (
  `id` int(11) NOT NULL,
  `reportId` int(11) NOT NULL,
  `imageName` varchar(300) NOT NULL,
  `imageNewName` varchar(300) NOT NULL,
  `createdAt` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `uploadedimages`
--

INSERT INTO `uploadedimages` (`id`, `reportId`, `imageName`, `imageNewName`, `createdAt`) VALUES
(24, 0, '1652402033753.png', '1654266962563.png', '2022-06-03'),
(25, 0, '1652402430242.png', '1654266962633.png', '2022-06-03'),
(26, 0, 'logo.png', '1654266962647.png', '2022-06-03'),
(27, 43, '1652402033753.png', '1654267017783.png', '2022-06-03'),
(28, 43, '1652402430242.png', '1654267017848.png', '2022-06-03'),
(29, 43, 'logo.png', '1654267017862.png', '2022-06-03');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `duties`
--
ALTER TABLE `duties`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subcat`
--
ALTER TABLE `subcat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `suggestions`
--
ALTER TABLE `suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uploadedimages`
--
ALTER TABLE `uploadedimages`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `duties`
--
ALTER TABLE `duties`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `subcat`
--
ALTER TABLE `subcat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `suggestions`
--
ALTER TABLE `suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `uploadedimages`
--
ALTER TABLE `uploadedimages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
