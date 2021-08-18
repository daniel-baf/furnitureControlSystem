-- INSERTS
-- Furniture
INSERT INTO
   `Furniture`
VALUES
   ('isla moderna de corte','500'),
   ('mesa cuadrada metal','125'),
   ('mesa redonda madera','120'),
   ('silla madera rustica','40'),
   ('silla metal minimalista','50');

-- FURNITURE PIECES
INSERT INTO
   `FurniturePiece` (`name`,`cost`)
VALUES
   ('base cuadrada metal','190'),
   ('base cuadrada metal','196'),
   ('base cuadrada metal','204'),
   ('base cuadrada metal','180'),
   ('plancha vidrio para isla','93'),
   ('plancha vidrio para isla','102'),
   ('plancha vidrio para isla','90'),
   ('plancha liza metal para mesa','40'),
   ('plancha liza metal para mesa','43'),
   ('plancha liza metal para mesa','34'),
   ('plancha liza metal para mesa','47'),
   ('plancha liza metal para mesa','36'),
   ('patas metal para mesa circulares','35'),
   ('patas metal para mesa circulares','35'),
   ('patas metal para mesa circulares','34'),
   ('patas metal para mesa circulares','33'),
   ('patas metal para mesa circulares','35'),
   ('patas metal para mesa circulares','36'),
   ('patas metal para mesa circulares','38'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','25'),
   ('plancha redonda madera para mesa','26'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('pata cuadrada madera','10'),
   ('respaldo silla rustica','12'),
   ('respaldo silla rustica','12'),
   ('respaldo silla rustica','13'),
   ('respaldo silla rustica','10'),
   ('respaldo silla rustica','11'),
   ('pata rustica de madera','3'),
   ('pata rustica de madera','4'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('pata rustica de madera','5'),
   ('plancha metal para banco','13'),
   ('plancha metal para banco','13'),
   ('plancha metal para banco','13'),
   ('plancha metal para banco','13'),
   ('plancha metal para banco','13'),
   ('plancha metal para banco','13'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('plancha lateral para banco de metal','6'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4'),
   ('base madera redonda para mesa','4');

-- Users
INSERT INTO
   `User`
VALUES
   ('bjvv123','12345asf123','2'),
   ('cfof123','12345asf123','3'),
   ('cjbf123','12345asf123','2'),
   ('debf123','12345asf123','1'),
   ('fasf123','12345asf123','2'),
   ('gcuf123','12345asf123','2'),
   ('Klgm092','12345asf123','1'),
   ('lhKy082','12345asf123','3'),
   ('ljha092','12345asf123','1'),
   ('lkce091','12345asf123','3'),
   ('lmas123','12345asf123','1'),
   ('malo123','12345asf123','2'),
   ('mjma123','12345asf123','1'),
   ('zxds213','12345asf123','3');

-- Client
INSERT INTO
   `Client`
VALUES
   ('04912341','Javier Vasquez','1a calle 20 -12 zona 6'),
   ('09182132','Ana Vega','1a calle 20 -12 zona 6'),
   ('09213102','Marco Munguia','1a calle 20-12 zona 6'),
   ('10912398','Daniel Bautista','1a calle 20-12 zona 6'),
   ('10927841','Mario Andres','1a calle 20-12 zona 6'),
   ('74512322','Lilith Alvarez','1a calle 20-12 zona 6');

-- Piece assembly
INSERT INTO
   `PieceAssembly`
VALUES
   ('isla moderna de corte','base cuadrada metal','1'),
   ('isla moderna de corte','plancha vidrio para isla','1'),
   ('mesa cuadrada metal','patas metal para mesa circulares','4'),
   ('mesa cuadrada metal','plancha liza metal para mesa','1'),
   ('mesa redonda madera','base madera redonda para mesa','1'),
   ('mesa redonda madera','pata cuadrada madera','1'),
   ('mesa redonda madera','plancha redonda madera para mesa','1'),
   ('silla madera rustica','pata rustica de madera','4'),
   ('silla madera rustica','respaldo silla rustica','1'),
   ('silla metal minimalista','plancha lateral para banco de metal','2'),
   ('silla metal minimalista','plancha metal para banco','1');

-- Furniture assembly
INSERT INTO
   `FurnitureAssembly` (`userName`,`date`,`furnitureName`,`assemblyPrice`)
VALUES
   ('bjvv123','2020-12-10','isla moderna de corte','259'),
   ('bjvv123','2020-12-10','isla moderna de corte','259'),
   ('mjma123','2020-10-01','mesa redonda madera','112'),
   ('lmas123','2021-04-12','mesa cuadrada metal','92'),
   ('lmas123','2021-01-11','isla moderna de corte','240'),
   ('zxds213','2018-01-14','mesa redonda madera','98'),
   ('zxds213','2019-01-11','isla moderna de corte','213'),
   ('debf123','2020-10-01','silla madera rustica','21'),
   ('mjma123','2021-01-11','silla madera rustica','25'),
   ('cfof123','2021-08-17','silla metal minimalista','23');

INSERT INTO
   `FurnitureAssembly` (`userName`,`date`,`sold`,`furnitureName`,`assemblyPrice`)
VALUES
   ('bjvv123','2021-08-17','1','isla moderna de corte','230'),
   ('lmas123','2021-01-11','1','silla metal minimalista','50'),
   ('zxds213','2001-03-10','1','mesa redonda madera','100');

-- BILL
INSERT INTO
   `Bill` (`userName`, `furnitureAssembyId`, `ammount`, `clientNIT`, `buyDate`)
VALUES
   ('malo123','11','500','09213102','2021-08-19'),
   ('Klgm092','12','50','10927841','2021-02-13'),
   ('lmas123','13','120','74512322','2001-05-25');

-- refund
INSERT INTO
   `Refund`
VALUES
   ('2', '2021-02-17', '50');