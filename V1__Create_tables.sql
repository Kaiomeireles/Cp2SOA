CREATE TABLE room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(10) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL,
    capacity INT NOT NULL,
    daily_rate DECIMAL(10,2) NOT NULL,
    status VARCHAR(10) NOT NULL
);

CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT NOT NULL,
    guest_name VARCHAR(255) NOT NULL,
    checkin_date DATE NOT NULL,
    checkout_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_value DECIMAL(10,2),
    CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES room(id)
);
