CREATE TABLE AB_USER
(
	user_id 	VARCHAR(20) NOT NULL PRIMARY KEY UNIQUE comment '使用者代碼',
	name    	VARCHAR(30) BINARY NOT NULL comment '名稱',
	email		VARCHAR(40) NOT NULL UNIQUE comment 'Email',
	password	VARCHAR(20) BINARY NOT NULL comment '密碼',
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