-- ============================
-- TABELAS PRINCIPAIS
-- ============================

-- Departamentos
CREATE TABLE tb_departament (
                                departament_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(255),
                                description TEXT,
                                location VARCHAR(255),
                                status VARCHAR(50),
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                budget DECIMAL(19,2)
);

-- Setores
CREATE TABLE tb_sector (
                           sector_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255),
                           efficiency FLOAT,
                           departament_id BIGINT,
                           CONSTRAINT fk_sector_department FOREIGN KEY (departament_id)
                               REFERENCES tb_departament (departament_id)
);

-- Usuários
CREATE TABLE tb_user (
                         user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         password VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         username VARCHAR(255)
);

-- Cargos/Roles
CREATE TABLE tb_roles (
                          role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL
);

-- Funcionários
CREATE TABLE tb_employees (
                              employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              photo VARCHAR(255),
                              employeeID INT,
                              user_id BIGINT UNIQUE,
                              name VARCHAR(255),
                              shift VARCHAR(50),
                              status VARCHAR(50),
                              sector_id BIGINT,
                              availability BOOLEAN DEFAULT TRUE,
                              CONSTRAINT fk_employee_user FOREIGN KEY (user_id) REFERENCES tb_usuario (user_id),
                              CONSTRAINT fk_employee_sector FOREIGN KEY (sector_id) REFERENCES tb_sector (sector_id)
);

-- Tabela de ligação Funcionário x Roles
CREATE TABLE tb_employee_roles (
                                   employee_id BIGINT,
                                   role_id BIGINT,
                                   PRIMARY KEY (employee_id, role_id),
                                   CONSTRAINT fk_employee_roles_employee FOREIGN KEY (employee_id) REFERENCES tb_employees (employee_id),
                                   CONSTRAINT fk_employee_roles_role FOREIGN KEY (role_id) REFERENCES tb_roles (role_id)
);

-- Modelos de máquina
CREATE TABLE tb_machine_model (
                                  machine_model_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  model_name VARCHAR(255),
                                  model_description TEXT
);

-- Máquinas
CREATE TABLE tb_machine (
                            machine_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            photo VARCHAR(255),
                            last_maintenance DATE,
                            oee FLOAT,
                            throughput INT,
                            serie_number INT,
                            sector_id BIGINT,
                            machine_model_id BIGINT,
                            status VARCHAR(50),
                            CONSTRAINT fk_machine_sector FOREIGN KEY (sector_id) REFERENCES tb_sector (sector_id),
                            CONSTRAINT fk_machine_model FOREIGN KEY (machine_model_id) REFERENCES tb_machine_model (machine_model_id)
);

-- Histórico de alteração do estado da máquina
CREATE TABLE tb_alteracao_estado_maquina (
                                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             changed_machine BIGINT,
                                             old_status VARCHAR(50),
                                             new_status VARCHAR(50),
                                             change_date DATE DEFAULT CURRENT_DATE,
                                             changed_by_employee BIGINT,
                                             CONSTRAINT fk_hist_maquina FOREIGN KEY (maquina_alterada) REFERENCES tb_machine (machine_id),
                                             CONSTRAINT fk_hist_funcionario FOREIGN KEY (funcionario_alterou) REFERENCES tb_employees (employee_id)
);

-- Alocação de funcionários em máquinas
CREATE TABLE tb_allocation_employees_machine (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 employees_id BIGINT NOT NULL,
                                                 machine_id BIGINT NOT NULL,
                                                 allocation_date DATE DEFAULT CURRENT_DATE,
                                                 entry_time TIME,
                                                 departure_time TIME,
                                                 changed_employee BIGINT,
                                                 CONSTRAINT fk_allocation_employee FOREIGN KEY (employees_id) REFERENCES tb_employees (employee_id),
                                                 CONSTRAINT fk_allocation_machine FOREIGN KEY (machine_id) REFERENCES tb_machine (machine_id),
                                                 CONSTRAINT fk_changed_employee FOREIGN KEY (changed_employee) REFERENCES tb_employees (employee_id)
);
