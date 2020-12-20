CREATE TABLE Accounts (account_id SERIAL NOT NULL, account_login varchar(255) NOT NULL, account_password varchar(255) NOT NULL, account_email_address varchar(255) NOT NULL, PRIMARY KEY (account_id));

CREATE TABLE Addresses (address_id SERIAL NOT NULL, country varchar(255) NOT NULL, city varchar(255) NOT NULL, street_name varchar(255) NOT NULL, house_number varchar(10) NOT NULL, postal_code varchar(16) NOT NULL, PRIMARY KEY (address_id));

CREATE TABLE Cars (car_id SERIAL NOT NULL, car_brand varchar(255) NOT NULL, car_model varchar(255) NOT NULL, car_color varchar(255) NOT NULL, car_engine_type varchar(255) NOT NULL, car_engine_capacity float4 NOT NULL, car_country_of_origin varchar(255), car_mileage float4 NOT NULL, car_production_year int4 NOT NULL, car_vin varchar(255) NOT NULL, car_price int4, car_is_available bool NOT NULL, PRIMARY KEY (car_id));

CREATE TABLE Clients (client_id SERIAL NOT NULL, client_first_name varchar(255) NOT NULL, client_last_name varchar(255) NOT NULL, account_id int4 NOT NULL, address_id int4 NOT NULL, PRIMARY KEY (client_id));

CREATE TABLE Employees (employee_id SERIAL NOT NULL, employee_pesel bigint NOT NULL, employee_first_name varchar(255) NOT NULL, employee_last_name varchar(255) NOT NULL, job varchar(255) NOT NULL, salary float4 NOT NULL, address_id int4 NOT NULL, account_id int4 NOT NULL, PRIMARY KEY (employee_id));

CREATE TABLE Loans (loan_id SERIAL NOT NULL, loan_start_date date, loan_end_date date, loan_price float4, client_id int4 NOT NULL, car_id int4 NOT NULL, PRIMARY KEY (loan_id));

ALTER TABLE Loans ADD CONSTRAINT FKLoans348432 FOREIGN KEY (client_id) REFERENCES Clients (client_id);

ALTER TABLE Loans ADD CONSTRAINT FKLoans434961 FOREIGN KEY (car_id) REFERENCES Cars (car_id);

ALTER TABLE Employees ADD CONSTRAINT FKEmployees477375 FOREIGN KEY (address_id) REFERENCES Addresses (address_id);

ALTER TABLE Employees ADD CONSTRAINT FKEmployees620423 FOREIGN KEY (account_id) REFERENCES Accounts (account_id);

ALTER TABLE Clients ADD CONSTRAINT FKClients928633 FOREIGN KEY (account_id) REFERENCES Accounts (account_id);

ALTER TABLE Clients ADD CONSTRAINT FKClients71682 FOREIGN KEY (address_id) REFERENCES Addresses (address_id);

CREATE PROCEDURE sort_cars_by_price_asc()
LANGUAGE SQL
AS $$
SELECT * FROM CARS
ORDER BY car_price;
$$;


CREATE PROCEDURE sort_cars_by_price_desc()
LANGUAGE SQL
AS $$
SELECT * FROM CARS
ORDER BY car_price DESC;
$$;

CREATE PROCEDURE sort_cars_by_availability_asc()
LANGUAGE SQL
AS $$
SELECT * FROM CARS
ORDER BY car_is_available;
$$;

CREATE PROCEDURE sort_cars_by_availability_desc()
LANGUAGE SQL
AS $$
SELECT * FROM CARS
ORDER BY car_is_available DESC;
$$;

CREATE PROCEDURE insert_new_account(a varchar(255), b varchar(255), c varchar(255))
LANGUAGE SQL
AS $$
INSERT INTO Accounts(account_login,account_password,account_email_address) VALUES (a, b, c);
$$;

CREATE FUNCTION dates_check () RETURNS trigger AS $dates_check$
BEGIN
    IF NEW.loan_start_date > NEW.loan_end_date THEN
        RAISE EXCEPTION 'Data rozpoczęcia nie może być wcześniejsza niż data zakończenia wypożyczenia';
    END IF;
    RETURN NEW;
END;
$dates_check$ LANGUAGE plpgsql;

CREATE TRIGGER dates_check BEFORE INSERT OR UPDATE ON Loans
    FOR EACH ROW EXECUTE FUNCTION dates_check();

CREATE FUNCTION salary_check () RETURNS trigger AS $salary_check$
BEGIN
    IF NEW.salary IS NULL THEN
        RAISE EXCEPTION ' Wypłata nie może być zerem';
    END IF;

    IF NEW.salary < 0 THEN
        RAISE EXCEPTION ' Wypłata nie może być ujemna';
    END IF;

    RETURN NEW;
END;
$salary_check$ LANGUAGE plpgsql;

CREATE TRIGGER salary_check BEFORE INSERT OR UPDATE ON Employees
    FOR EACH ROW EXECUTE FUNCTION salary_check();