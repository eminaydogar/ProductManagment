DROP TABLE IF EXISTS product;

CREATE TABLE product(
  id BIGINT(11) UNSIGNED NOT NULL,
  category VARCHAR(64) NOT NULL,
  categorytype VARCHAR(64) NOT NULL,
  name VARCHAR(128) NOT NULL,
  status VARCHAR(1) NOT NULL,
  count BIGINT UNSIGNED NOT NULL,
  price DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (id));