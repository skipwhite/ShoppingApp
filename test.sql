CREATE TABLE AB_USER
(
	user_id 	VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE comment '使用者代碼',
	name    	VARCHAR(5) BINARY NOT NULL comment '名稱',
	email		VARCHAR(40) NOT NULL UNIQUE comment 'Email',
	password	VARCHAR(20) BINARY NOT NULL comment '密碼',
	salt 		VARCHAR(20) BINARY NOT NULL comment '鹽值'
) comment = '使用者資料檔';

CREATE TABLE AB_TEST_TABLE
(
  	id  		CHAR(5) NOT NULL PRIMARY KEY UNIQUE comment '產品代碼',
	num			INT UNSIGNED NOT NULL DEFAULT 0 comment '數字',
	deci			NUMERIC(4,2) NOT NULL DEFAULT 1 comment '小數點',
	bin 		BIT NOT NULL DEFAULT 0 comment '零一',
	timestamp		TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '時間'
) comment = '測試檔';
select * from AB_TEST_TABLE;

CREATE TABLE AB_PRODUCT
(
  	product_id  CHAR(5) NOT NULL PRIMARY KEY UNIQUE comment '產品代碼',
	name		VARCHAR(100) NOT NULL comment '產品名稱',
	dscr 		VARCHAR(100) NOT NULL comment '描述',
  	category    VARCHAR(20) NOT NULL comment '產品分類', -- 堅果油, 沖泡粉, 抹醬, 堅果
	price		INT UNSIGNED NOT NULL DEFAULT 0 comment '價格',
	view_count	INT UNSIGNED NOT NULL DEFAULT 0 comment '點閱次數',
	inventory	INT UNSIGNED NOT NULL DEFAULT 0 comment '庫存',
	tag    		VARCHAR(20) comment '促銷標籤', -- 商家另外分類用, 買一送一優惠區, 只能有一種促銷
	discount	NUMERIC(4,2) NOT NULL DEFAULT 1 comment '促銷比率',
	sales_qty	INT UNSIGNED NOT NULL DEFAULT 0 comment '銷售次數',
	launched 	BIT NOT NULL DEFAULT 0 comment '已上架'
) comment = '產品資料檔';
insert into AB_PRODUCT VALUE ('00001', '黃金亞麻仁油', '自家烘培、冷壓榨成油！', '堅果油', 250,
							  0, 10, '買2送1', 1, 0, 1);
insert into AB_PRODUCT VALUE ('00003', '黃金亞麻仁油3', '自家烘培、冷壓榨成油！', '堅果油', 250,
							  0, 10, '買2送1', 1, 0, 1);

CREATE TABLE AB_ORDER
(
	po_no           	CHAR(14) NOT NULL PRIMARY KEY UNIQUE comment '訂單代碼', -- 20181201xxxxxx 流水號
	user_id  			VARCHAR(20) NOT NULL comment '使用者買方',
	total		 		INT UNSIGNED NOT NULL DEFAULT 0 comment '總價'
) comment = '訂單資料檔';

CREATE TABLE AB_ORDER_DTL
(
	item				VARCHAR(6) NOT NULL,
	po_no           	CHAR(14) NOT NULL comment '訂單代碼', -- 20181201xxxxxx 流水號
	product_id  		CHAR(5) NOT NULL comment '產品代碼',
	qty					INT UNSIGNED NOT NULL DEFAULT 0 comment '數量',
	CONSTRAINT PRIMARY KEY (item, po_no)
) comment = '訂單明細檔';