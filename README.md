# shop_springboot
springboot+hibernate+postgres+thymeleaf+security

#custom DB table init:
CREATE TABLE products
(
  product_id   BIGSERIAL NOT NULL
    CONSTRAINT products_pkey
    PRIMARY KEY,
  product_name VARCHAR   NOT NULL,
  owner_name   VARCHAR,
  date_add     TIMESTAMP,
  date_update  TIMESTAMP,
  description  VARCHAR(255)
);
