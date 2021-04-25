BEGIN;

DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS categories CASCADE;

CREATE TABLE categories (id bigserial, title varchar(255), PRIMARY KEY (id));

CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int,  category_id integer REFERENCES categories (id));


INSERT INTO categories (title) VALUES
('TV'),
('Smartphone'),
('Game console');

INSERT INTO products (title, price, category_id) VALUES
('LG', 25000, 1),
('Sony', 80000, 1),
('Iphone', 100000, 2),
('Huawei', 24000, 2),
('PlayStation 5', 68000, 3),
('XBOX', 45000, 3);

COMMIT;