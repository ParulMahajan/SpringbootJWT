DROP TABLE IF EXISTS Employee;
 
CREATE TABLE Employee (
  employeename VARCHAR(250)   PRIMARY KEY,
  password VARCHAR(250) NOT NULL,
  email VARCHAR(250) ,
  mobile VARCHAR(250) 
);
 
INSERT INTO Employee (employeename, password, email, mobile) VALUES
  ('Parul', 'Mahajan', 'Billionaire', 'Industrialist'),
  ('Tanuj', 'Mahajan', 'Billionaire', 'Entrepreneur'),
  ('Jatin', 'Kumar', 'Billionaire', 'Magnate');
  
  
 