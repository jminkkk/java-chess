DROP DATABASE chess;
CREATE DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
SHOW TABLES in chess;
USE chess;
SHOW COLUMNS FROM Piece;
SELECT *
FROM Piece;

CREATE TABLE Turn
(
    turn_id INT AUTO_INCREMENT PRIMARY KEY,
    turn    VARCHAR(255) NOT NULL
);

CREATE TABLE Piece
(
    piece_id  INT AUTO_INCREMENT PRIMARY KEY,
    color     VARCHAR(255) NOT NULL,
    pieceType VARCHAR(255) NOT NULL,
    `rank`    INT          NOT NULL,
    file      INT          NOT NULL
);

