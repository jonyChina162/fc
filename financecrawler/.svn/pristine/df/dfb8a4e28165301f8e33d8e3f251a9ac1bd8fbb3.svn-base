/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/12/16 19:33:52                          */
/*==============================================================*/
drop database if exists finance;

create database finance;

use finance;

drop table if exists FinancialReport;

drop table if exists FundFlowHistory;

drop table if exists IndustryReport;

drop table if exists StockNews;

drop table if exists StockReport;

drop table if exists TransactionHistory;

drop table if exists stockIndustry;

drop table if exists stockIndex;

drop table if exists stockIndex;

drop table if exists IndexTransactionHistory

/*==============================================================*/
/* Table: FinancialReport                                       */
/*==============================================================*/
create table FinancialReport
(
   stockID              char(6) not null,
   date                 date not null,
   income               int ,
   cost                 int ,
   operProfit           int ,
   profitAmount         int ,
   incomeTax            int ,
   netProfit            int ,
   EPS                  float ,
   monCap               int ,
   currentAsset         int ,
   fixAsset             int ,
   totalAsset           int ,
   currentLiab          int ,
   fixLiab              int ,
   totalLiab            int ,
   totalEquity          int ,
   cashBalance          int ,
   busActFund           int ,
   investActFund        int ,
   financeActFund       int ,
   CashIncrease         int ,
   finalCashBalance     int ,
   primary key (date, stockID)
);

/*==============================================================*/
/* Table: FundFlowHistory                                       */
/*==============================================================*/
CREATE TABLE `fundflowhistory` (
  `stockID` char(6) NOT NULL,
  `date` date NOT NULL,
  `fundIn` mediumint(9) DEFAULT '0',
  `fundOut` mediumint(9) DEFAULT '0',
  `netIn` mediumint(9) DEFAULT '0',
  `mainIn` mediumint(9) DEFAULT '0',
  `mainOut` mediumint(9) DEFAULT '0',
  `mainNetIn` mediumint(9) DEFAULT '0',
  `allmfi1` float DEFAULT '0',
  `allmfi7` float DEFAULT '0',
  `mainmfi1` float DEFAULT '0',
  `mainmfi7` float DEFAULT '0',
  `AllMFI14` float DEFAULT '0',
  `AllMFI14dummy` int(11) DEFAULT '0',
  `AllMFI1dummy` int(11) DEFAULT '0',
  `AllMFI7dummy` int(11) DEFAULT '0',
  `MainMFI14` float DEFAULT '0',
  `MAINMFI14dummy` int(11) DEFAULT '0',
  `MAINMFI1dummy` int(11) DEFAULT '0',
  `MAINMFI7dummy` int(11) DEFAULT '0',
  PRIMARY KEY (`stockID`,`date`)
) ;

/*==============================================================*/
/* Table: IndustryReport                                        */
/*==============================================================*/
create table IndustryReport
(
   industryName         varchar(12) not null,
   date                 date not null,
   title             varchar(255) not null,
   linkURL				varchar(255),
   grade                tinyint ,
   gradeChange          tinyint ,
   orgName              varchar(8) ,
   orgWeight            tinyint ,
   primary key (date, industryName, linkURL)
);

/*==============================================================*/
/* Table: StockNews                                             */
/*==============================================================*/
create table StockNews
(
   stockID              char(6) not null,
   date                 date not null,
   title                varchar(255) ,
   linkURL              varchar(255) ,
   extendNosql          varchar(255) ,
   tendency             tinyint ,
   primary key (stockID, date)
);

/*==============================================================*/
/* Table: StockReport                                           */
/*==============================================================*/
create table StockReport
(
   stockID              char(6) not null,
   date                 date not null,
   title             varchar(255) not null,
   linkURL				varchar(255),
   stockName            varchar(12) ,
   grade                tinyint ,
   gradeChange          tinyint ,
   orgName              varchar(8) ,
   preIncomeThis        float ,
   prePEratioThis       float ,
   preIncomeNext        float ,
   prePEratioNext       float ,
   primary key (stockID, date, linkURL)
);

/*==============================================================*/
/* Table: TransactionHistory                                    */
/*==============================================================*/
CREATE TABLE `transactionhistory` (
  `stockID` char(6) NOT NULL,
  `date` date NOT NULL,
  `stockName` varchar(12) DEFAULT NULL,
  `highPrice` float DEFAULT '0',
  `lowPrice` float DEFAULT '0',
  `openPrice` float DEFAULT '0',
  `beforeClo` float DEFAULT '0',
  `changeAmount` float DEFAULT '0',
  `changeRatio` float DEFAULT '0',
  `turnoverRate` float DEFAULT '0',
  `turnover` bigint(20) DEFAULT '0',
  `turnoverAmount` float DEFAULT '0',
  `MKTcap` float DEFAULT '0',
  `EFAMC` float DEFAULT '0',
  `cloPrice` float DEFAULT '0',
  `rsi` float DEFAULT '0',
  `atr7` float DEFAULT '0',
  `atr14` float DEFAULT '0',
  `macd` float DEFAULT '0',
  `trade` int(11) DEFAULT '0',
  `iftradeup` int(11) DEFAULT '0',
  `iftradedown` int(11) DEFAULT '0',
  `rsi6` float DEFAULT '0',
  `rsi6dummy` int DEFAULT '0',
  `rsi12` float DEFAULT '0',
  `rsi12dummy` int DEFAULT '0',
  `rsi24` float DEFAULT '0',
  `rsi24dummy` int DEFAULT '0',
  PRIMARY KEY (`stockID`,`date`)
);

/*==============================================================*/
/* Table: stockIndustry                                        */
/*==============================================================*/
create table stockIndustry
(
   stockID              char(6) not null,
   industryName         varchar(12) ,
   stockName            varchar(12) ,
   region               varchar(8) ,
   stockType            tinyint ,
   primary key (stockID)
);

/*==============================================================*/
/* Table: stockIndex                                        */
/*==============================================================*/
create table stockIndex
(
   id              char(6) not null,
   name            varchar(12) ,
   primary key (id)
);

/*==============================================================*/
/* Table: IndexTransactionHistory                                    */
/*==============================================================*/
create table IndexTransactionHistory
(
   stockID              char(6) not null,
   date                 date not null,
   name           		varchar(12) ,
   cloPrice              float ,
   highPrice            float ,
   lowPrice             float ,
   openPrice            float ,
   beforeClo            float,
   changeAmount         float ,
   changeRatio          float ,
   turnover             bigint ,
   turnoverAmount       float ,
   primary key (stockID, date)
);

create table InstitutionName
(
   institutionID        char(9) not null,
   institutionName      varchar(20) not null
   primary key (institutionID,institutionName)
);

