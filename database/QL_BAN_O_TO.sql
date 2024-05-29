CREATE DATABASE IF NOT EXISTS QL_BAN_O_TO;
-- DROP DATABASE IF EXISTS QL_BAN_O_TO;

USE QL_BAN_O_TO;  -- Đảm bảo sử dụng cơ sở dữ liệu đã tạo trước khi tạo bảng

DROP TABLE IF EXISTS `Account`;
CREATE TABLE IF NOT EXISTS `Account` (
    `id`        INT AUTO_INCREMENT PRIMARY KEY,
    `username`  VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(`username`) >= 6 AND LENGTH(`username`) <= 50),
    `email`     VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(`email`) >= 6 AND LENGTH(`email`) <= 50),
    `password`  VARCHAR(800) NOT NULL,
    `fullName`  NVARCHAR(50) NOT NULL,
    `role`      ENUM('Admin', 'User') DEFAULT 'User'
);
DROP TABLE IF EXISTS `Car`;
CREATE TABLE IF NOT EXISTS `Car` (
    `id`        INT AUTO_INCREMENT PRIMARY KEY,
    `name`      VARCHAR(50) NOT NULL UNIQUE CHECK (LENGTH(`name`) >= 6 AND LENGTH(`name`) <= 50),
    `price`     VARCHAR(255)  NOT NULL,
    `image`     VARCHAR(255)  NOT NULL,
    `color`     ENUM('red', 'black', 'gray', 'white')
);

-- Thêm dữ liệu cho bảng Account
INSERT INTO `Account` (`username`, `email`, `password`, `fullName`, `role`)
VALUES
    ('ducchien0908', 'nguyenducchien0908@.com', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Duc Chien', 'Admin'),
    ('ducanh01', 'luongducanh@.com', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'duc anh', 'User'),
    ('admin_user', 'admin@example.com', '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi', 'Admin User', 'Admin');



INSERT INTO `Car` (`name`, `price`, `image`, `color`)
VALUES
    ('Mazda 3', '25000', 'mazda3.jpg', 'red'),
    ('Mazda CX-5', '30000', 'cx5.jpg', 'black'),
    ('Mazda MX-5', '20000', 'mx5.jpg', 'white');
    -- Thêm 7 bản ghi khác cho bảng Cars

-- Thêm dữ liệu cho bảng car_images


