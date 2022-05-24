-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2022 at 04:48 PM
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
(56, 1, 'The Barrister', '2022-05-15'),
(57, 2, 's are available, and who is eligible?', '2022-05-19'),
(58, 2, 'ooooo', '2022-05-19');

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
(1, '2022-05-15', 3, 55, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><font size=\"4\"><i style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\"><b><a href=\"https://en.wikipedia.org/wiki/The_Hunting_of_the_Snark\" title=\"The Hunting of the Snark\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none;\">The Hunting of the Snark</a></b></i><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">&nbsp;is a&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Nonsense_verse\" title=\"Nonsense verse\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">nonsense poem</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">&nbsp;by the English writer&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Lewis_Carroll\" title=\"Lewis Carroll\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">Lewis Carroll</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">, telling the story of ten characters who cross the ocean to hunt a mysterious creature known as the Snark. The poem was published in 1876 with illustrations by&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Henry_Holiday\" title=\"Henry Holiday\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">Henry Holiday</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">. This is the eighth plate from Holiday\'s illustrations, accompanying \"Fit the Sixth: The Barrister\'s Dream\". The Barrister, one of the crew members, sleeps and dreams of witnessing the trial of a pig accused of deserting its&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Sty\" title=\"Sty\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">sty</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">. The Snark is depicted in the foreground, acting as the defence&nbsp;</span><a href=\"https://en.wikipedia.org/wiki/Barrister\" title=\"Barrister\" style=\"text-decoration: none; color: rgb(6, 69, 173); background-image: none; background-color: rgb(250, 245, 255); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2;\">barrister</a><span style=\"color: rgb(32, 33, 34); font-family: sans-serif; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(250, 245, 255); text-decoration-thickness: initial;\">&nbsp;and dressed in robe and wig – the nearest to an illustration of the creature in the set. The Barrister is ultimately woken by the Bellman\'s bell ringing in his ear, as seen in the bottom left.</span></font></p></body></html>', '2022-05-15', 'The Hunting of the Snark'),
(2, '2022-05-19', 23, 69, '<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><div class=\"el__leafmedia el__leafmedia--sourced-paragraph\" style=\"box-sizing: border-box; color: rgb(38, 38, 38); font-family: CNN, &quot;Helvetica Neue&quot;, Helvetica, Arial, Utkal, sans-serif; font-size: 15px; font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(254, 254, 254); text-decoration-thickness: initial;\"><p class=\"zn-body__paragraph speakable\" data-paragraph-id=\"paragraph_807861B3-BADE-A247-8BD4-D4ECE48D2CA9\" style=\"box-sizing: border-box; margin: 0px 0px 15px; -webkit-font-smoothing: antialiased; font-size: 1.2rem; line-height: 1.66667;\"><cite class=\"el-editorial-source\" style=\"box-sizing: border-box; font-family: CNN, &quot;Helvetica Neue&quot;, Verdana, Geneva, sans-serif; font-style: normal; font-weight: 700;\">(CNN)</cite>Covid-19 infections are on the rise, with&nbsp;<a href=\"https://www.cnn.com/2022/05/03/health/fitter-omicron-descendants-covid-variants/index.html\" target=\"_blank\" style=\"box-sizing: border-box; color: rgb(0, 101, 152); text-decoration: none; transition: color 0.2s ease 0s;\">most US states</a>&nbsp;reporting an increase in cases. According to the US Centers for Disease Control and Prevention, the highly contagious BA.2.1.21 subvariant of Omicron is now the dominant strain of coronavirus nationwide.</p></div><div class=\"zn-body__paragraph\" data-paragraph-id=\"paragraph_5FCAF0DF-F09A-0226-6F73-D4ECE48E5B22\" style=\"box-sizing: border-box; margin-bottom: 15px; font-family: CNN, &quot;Helvetica Neue&quot;, Helvetica, Arial, Utkal, sans-serif; -webkit-font-smoothing: antialiased; font-size: 1.2rem; line-height: 1.66667; margin-right: 0px; color: rgb(38, 38, 38); font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(254, 254, 254); text-decoration-thickness: initial;\">Two years into the pandemic, many aren\'t sure what to do after testing positive for Covid-19. Should they isolate, and if so, for how long? How important is it to see a doctor? What therapies are available, and who is eligible?</div><div class=\"zn-body__paragraph\" data-paragraph-id=\"paragraph_FF4A6C7B-026F-72A5-C632-D4ECE48F5482\" style=\"box-sizing: border-box; margin-bottom: 15px; font-family: CNN, &quot;Helvetica Neue&quot;, Helvetica, Arial, Utkal, sans-serif; -webkit-font-smoothing: antialiased; font-size: 1.2rem; line-height: 1.66667; margin-right: 0px; color: rgb(38, 38, 38); font-variant-ligatures: normal; orphans: 2; widows: 2; background-color: rgb(254, 254, 254); text-decoration-thickness: initial;\">To help answer these and other questions, I spoke with CNN Medical Analyst Dr. Leana Wen, an emergency physician and professor of health policy and management at the George Washington University Milken Institute School of Public Health. She is also author of \"Lifelines: A Doctor\'s Journey in the Fight for Public Health\" and the mother of two young children.</div></body></html>', '2022-05-19', 'title');

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
(60, 19, 'cable', '2022-05-18'),
(61, 19, 'internet', '2022-05-18'),
(62, 19, 'wifi', '2022-05-18'),
(63, 19, 'troubleshooting', '2022-05-18'),
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
(1, 1, 'The Barrister is ultimately woken by', '2022-05-15'),
(2, 1, 'ultimately woken by', '2022-05-15'),
(3, 2, 's are , and who is eligible?', '2022-05-19');

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
(5, 1, 'sadA5D.png', '1652569488381.png', '2022-05-15'),
(6, 2, '1652402020215.png', '1652966732298.png', '2022-05-19'),
(7, 2, '1652402033753.png', '1652966732402.png', '2022-05-19'),
(8, 2, '1652402430242.png', '1652966732457.png', '2022-05-19'),
(9, 2, 'logo.png', '1652966732470.png', '2022-05-19'),
(10, 2, 'sadA5D.png', '1652966732533.png', '2022-05-19');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `subcat`
--
ALTER TABLE `subcat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `suggestions`
--
ALTER TABLE `suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `uploadedimages`
--
ALTER TABLE `uploadedimages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
