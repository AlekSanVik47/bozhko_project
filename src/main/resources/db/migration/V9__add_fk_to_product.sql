INSERT INTO category (category) VALUES ('category_1');
INSERT INTO category (category) VALUES ('category_2');
INSERT INTO category (category) VALUES ('category_3');
INSERT INTO category (category) VALUES ('category_4');
INSERT INTO category (category) VALUES ('category_5');
ALTER TABLE product ADD CONSTRAINT product_category FOREIGN KEY (category_id) REFERENCES category (id)
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE product ADD CONSTRAINT product_image FOREIGN KEY (image_id) REFERENCES image (id)
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE product ADD CONSTRAINT product_brand FOREIGN KEY (brand_id) REFERENCES brand (id)
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;