DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;
DROP TABLE IF EXISTS meals;
DROP INDEX IF EXISTS id_index;

CREATE SEQUENCE global_seq START WITH 100000;
CREATE SEQUENCE meals_id_seq;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                           NOT NULL,
  email            VARCHAR                           NOT NULL,
  password         VARCHAR                           NOT NULL,
  registered       TIMESTAMP           DEFAULT now() NOT NULL,
  enabled          BOOL                DEFAULT TRUE  NOT NULL,
  calories_per_day INTEGER             DEFAULT 2000  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE meals
(
  id          INTEGER PRIMARY KEY DEFAULT nextval('meals_id_seq'),
  date_Time   TIMESTAMP NOT NULL,
  description VARCHAR   NOT NULL,
  calories    INTEGER   NOT NULL,
  user_Id     INTEGER   NOT NULL,
  FOREIGN KEY (user_Id) REFERENCES topjava."public".users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX meals_date_index ON meals (date_Time);