CREATE TABLE Users
(
    id    BIGINT NOT NULL,
    name  VARCHAR(255),
    email VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);