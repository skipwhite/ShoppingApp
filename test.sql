select * from AB_USER;
select * from AB_PRODUCT;
UPDATE AB_PRODUCT SET inventory = 43, sales_qty=1 where product_id='00001';
UPDATE AB_PRODUCT SET inventory = 42, sales_qty = 2 where product_id='00001';


select * from AB_ORDER;
select * from AB_ORDER_DTL;
TRUNCATE TABLE AB_ORDER;

show full columns from AB_USER;
drop table AB_USER;
CREATE TABLE AB_USER
(
	user_id 	VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE comment '使用者代碼',
	name    	VARCHAR(30) NOT NULL comment '名稱',
	email		VARCHAR(40) NOT NULL UNIQUE comment 'Email',
	password	CHAR(32) NOT NULL comment '密碼',
	zip_code	VARCHAR(40) comment '郵遞區號',
	address		VARCHAR(40) comment '地址',
	phone		VARCHAR(10) comment '電話',
	-- birth   	TIMESTAMP comment '生日',
	-- email_push  BIT NOT NULL DEFAULT 1 comment 'Email推播設定',
	ship_store		VARCHAR(20) comment '常用店家', -- 只做7-11的便利商店
	role 		VARCHAR(10) comment '角色'
) comment = '使用者資料檔';



CREATE TABLE AB_PRODUCT
(
  	product_id  CHAR(5) NOT NULL PRIMARY KEY UNIQUE comment '產品代碼',
	name		VARCHAR(100) NOT NULL comment '產品名稱',
	dscr 		VARCHAR(1000) NOT NULL comment '描述',
  	category    VARCHAR(20) NOT NULL comment '產品分類', -- 堅果油, 沖泡粉, 抹醬, 堅果
	price		INT UNSIGNED NOT NULL DEFAULT 0 comment '價格',
	view_count	INT UNSIGNED NOT NULL DEFAULT 0 comment '點閱次數',
	inventory	INT UNSIGNED NOT NULL DEFAULT 0 comment '庫存',
	tag    		VARCHAR(20) comment '促銷標籤', -- 商家另外分類用, 買一送一優惠區, 只能有一種促銷
	discount	NUMERIC(4,2) NOT NULL DEFAULT 1 comment '促銷比率',
	sales_qty	INT UNSIGNED NOT NULL DEFAULT 0 comment '銷售次數',
	launched 	BIT NOT NULL DEFAULT 0 comment '已上架'
) comment = '產品資料檔';

CREATE TABLE AB_PRODUCT_IMG
(
	product_id  CHAR(5) NOT NULL comment '產品代碼',
	item		CHAR(1) NOT NULL comment '項次',
	img			MEDIUMBLOB NOT NULL comment '圖片檔',
	CONSTRAINT PRIMARY KEY (product_id, item)
) comment = '產品圖片檔';

CREATE TABLE AB_SETTING
(
	lookup_type		VARCHAR(20) NOT NULL comment '查詢類型', -- ship_id
	lookup_code 	VARCHAR(5) NOT NULL comment '查詢代碼', -- 0
	lookup_dscr		VARCHAR(20) comment '描述', -- 宅配
	value			VARCHAR(50) NOT NULL comment '查詢內容', -- 宅配
	CONSTRAINT PRIMARY KEY (lookup_type, lookup_code)
) comment = '設定檔';

insert into AB_SETTING value ("ship_id", "0", "宅配", "宅配");
insert into AB_SETTING value ("ship_id", "1", "便利商店", "便利商店");
insert into AB_SETTING value ("pay_id", "0", "信用卡", "信用卡");
insert into AB_SETTING value ("pay_id", "1", "便利商店貨到付款", "便利商店貨到付款");

CREATE TABLE AB_ORDER
(
	po_no           	CHAR(14) NOT NULL PRIMARY KEY UNIQUE comment '訂單編號', -- 20181201xxxxxx 流水號
	user_id				VARCHAR(20) NOT NULL comment '使用者買方',
	name 			   	VARCHAR(30) BINARY NOT NULL comment '名稱',
	phone				VARCHAR(10) NOT NULL comment '電話',
	-- vendor_user_id		VARCHAR(20) NOT NULL comment '使用者賣方',
	ship_id 			VARCHAR(20) NOT NULL comment '運送代碼', -- 寫在設定檔
	ship_id_value		VARCHAR(50) NOT NULL comment '運送方式', -- 寫在設定檔
	ship_store			VARCHAR(20) comment '常用店家', -- 只做7-11的便利商店
	pay_id   			VARCHAR(20) NOT NULL comment '付款代碼', -- 寫在設定檔
	pay_id_value		VARCHAR(20) NOT NULL comment '付款方式', -- 寫在設定檔
	zip_code			VARCHAR(6) comment '郵遞區號',
	address				VARCHAR(40) comment '地址',
	total_price	 		INT NOT NULL DEFAULT 0 comment '訂單總額',
	status				VARCHAR(20) NOT NULL DEFAULT '收到訂單' comment '訂單狀態',
	invalid				BIT NOT NULL DEFAULT 0 comment '訂單取消',
	is_commented		BIT NOT NULL DEFAULT 0 comment '已評論'
	-- coupon_id   		VARCHAR(20) comment '優惠券代碼',
) comment = '訂單資料檔';

CREATE TABLE AB_ORDER_DTL
(
	item				VARCHAR(6) NOT NULL comment '訂單項次',
	po_no           	CHAR(14) NOT NULL comment '訂單編號', 
	product_id  		CHAR(5) NOT NULL comment '產品代碼',
	category    		VARCHAR(20) NOT NULL comment '分類',
	-- spec    			VARCHAR(20) NOT NULL comment '規格代碼', -- 從設定檔查詢規格名稱
	qty		 			INT NOT NULL DEFAULT 0 comment '數量',
	price				INT NOT NULL DEFAULT 0 comment '單價',
	CONSTRAINT PRIMARY KEY (item, po_no)
) comment = '訂單明細檔';