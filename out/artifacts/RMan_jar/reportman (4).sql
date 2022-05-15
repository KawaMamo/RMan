-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 01:51 AM
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
(4, 'Communication', '2022-05-15', NULL),
(5, 'AI', '2022-05-15', NULL);

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
(55, 1, 'The Barrister is ultimately woken by', '2022-05-15'),
(56, 1, 'The Barrister', '2022-05-15');

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
  `title` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `reportDate`, `catId`, `subCatId`, `reportText`, `createdAt`, `title`) VALUES
(1, '2022-05-15', 3, 55, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><i style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\"><b><a href=\"https://en.wikipedia.org/wiki/The_Hunting_of_the_Snark\" title=\"The Hunting of the Snark\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none;\">The Hunting of the Snark</a></b></i><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">&nbsp;is a&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Nonsense_verse\" title=\"Nonsense verse\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">nonsense poem</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">&nbsp;by the English writer&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Lewis_Carroll\" title=\"Lewis Carroll\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">Lewis Carroll</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">, telling the story of ten characters who cross the ocean to hunt a mysterious creature known as the Snark. The poem was published in 1876 with illustrations by&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Henry_Holiday\" title=\"Henry Holiday\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">Henry Holiday</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">. This is the eighth plate from Holiday\'s illustrations, accompanying \"Fit the Sixth: The Barrister\'s Dream\". The Barrister, one of the crew members, sleeps and dreams of witnessing the trial of a pig accused of deserting its&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Sty\" title=\"Sty\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">sty</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">. The Snark is depicted in the foreground, acting as the defence&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Barrister\" title=\"Barrister\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">barrister</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">&nbsp;and dressed in robe and wig – the nearest to an illustration of the creature in the set. The Barrister is ultimately woken by the Bellman\'s bell ringing in his ear, as seen in the bottom left.</span></font></p></body></html>', '2022-05-15', 'The Hunting of the Snark');

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
(54, 3, 'Software', '2022-05-15'),
(55, 3, 'Analysis', '2022-05-15'),
(56, 4, 'wired', '2022-05-15'),
(57, 4, 'wireless', '2022-05-15'),
(58, 5, 'Computer vision', '2022-05-15'),
(59, 5, 'PlateNumber Recognition', '2022-05-15');

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
(1, 1, 'The Barrister is ultimately woken by', '2022-05-15'),
(2, 1, 'ultimately woken by', '2022-05-15');

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
(1, 1, '1652402020215.png', '1652569488150.png', '2022-05-15'),
(2, 1, '1652402033753.png', '1652569488248.png', '2022-05-15'),
(3, 1, '1652402430242.png', '1652569488302.png', '2022-05-15'),
(4, 1, 'logo.png', '1652569488316.png', '2022-05-15'),
(5, 1, 'sadA5D.png', '1652569488381.png', '2022-05-15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `subcat`
--
ALTER TABLE `subcat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `suggestions`
--
ALTER TABLE `suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `uploadedimages`
--
ALTER TABLE `uploadedimages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
