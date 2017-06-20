-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.56-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema admin
--

CREATE DATABASE IF NOT EXISTS admin;
USE admin;

--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` longtext NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `FIRSTNAME` varchar(30) NOT NULL,
  `LASTNAME` varchar(30) NOT NULL,
  `PHONE_NO` varchar(255) NOT NULL,
  `SEX` varchar(255) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`USER_ID`,`ADDRESS`,`EMAIL`,`FIRSTNAME`,`LASTNAME`,`PHONE_NO`,`SEX`) VALUES 
 (40,'Indore','kamlesh.solanki@gmail.com','Kamlesh','Solanki','9876789867','M'),
 (41,'Bhopal (M.P)','shubhamsethi@gmail.com','Shubham','Sethi','9845673452','M'),
 (42,'Noida','rajputrahul234@gmail.com','Rahul','Rajput','9067845986','M'),
 (43,'Raipur','nirmitmal@gmail.com','Nirmit','Malviya','9897680945','M'),
 (45,'Jabalpur','sachinkirar@gmail.com','Sachin','Kirar','9078563456','M');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
