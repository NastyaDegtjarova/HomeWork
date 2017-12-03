CREATE TABLE IF NOT EXISTS developer (
id_developer INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(100) NOT NULL
);

CREATE TABLE project(
id_projects INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name_projects VARCHAR(50) NOT NULL
);

CREATE TABLE skills(
id_skills INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
specialty VARCHAR(50) NOT NULL
);

CREATE TABLE companie(
id_companies INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
name_companies VARCHAR(50) NOT NULL
);

CREATE TABLE customer(
id_customers INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
first_name_customers VARCHAR(50) NOT NULL,
last_name_customers VARCHAR(100) NOT NULL
);

CREATE TABLE developer_skill(
id_developer INT NOT NULL,
id_skill INT NOT NULL,

FOREIGN KEY(id_developer) REFERENCES developer(id_developer),
FOREIGN KEY(id_skill) REFERENCES skills(id_skills),

UNIQUE(id_developer, id_skill)
);

CREATE TABLE compani_project(
id_compani INT NOT NULL,
id_project INT NOT NULL,

FOREIGN KEY(id_compani) REFERENCES companie(id_companies),
FOREIGN KEY(id_project) REFERENCES project(id_projects),

UNIQUE(id_compani, id_project)
);

CREATE TABLE customer_project(
id_customer INT NOT NULL,
id_project INT NOT NULL,

FOREIGN KEY(id_customer) REFERENCES customer(id_customers),
FOREIGN KEY(id_project) REFERENCES project(id_projects),

UNIQUE(id_customer, id_project)
);

CREATE TABLE project_developer(
id_project INT NOT NULL,
id_developer INT NOT NULL,

FOREIGN KEY(id_project) REFERENCES project(id_projects),
FOREIGN KEY(id_developer) REFERENCES developer(id_developer),

UNIQUE(id_project, id_developer)
);
