-- syntax to select minimum profit table
-- https://stackoverflow.com/questions/3839982/row-with-minimum-value-of-a-column

USE homework11;

SELECT
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id

GROUP BY projects.project_id
ORDER BY (cost - SUM(developers.salary)) ASC LIMIT 1;

SELECT
  company_name,
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id
  INNER JOIN companies_developers ON developers.id = companies_developers.developer_id
  INNER JOIN companies ON companies_developers.company_id = companies.company_id

GROUP BY projects.project_id, company_name
ORDER BY (cost - SUM(developers.salary)) ASC;




SELECT
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id

GROUP BY projects.project_id
ORDER BY 'Profit' ASC LIMIT 1;


SELECT
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id

  WHERE cost = (select MIN(cost) from projects) -- here I want to add the developers table
GROUP BY projects.project_id
ORDER BY 'Profit' ASC;

SELECT
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id

WHERE (cost - SUM(developers.salary)) = (SELECT
                project_name,
                project_description,
                (cost - SUM(developers.salary)) AS 'Profit'

              FROM projects
                INNER JOIN project_developers ON projects.project_id = project_developers.project_id
                INNER JOIN developers ON project_developers.developer_id = developers.id

              GROUP BY projects.project_id
              ORDER BY 'Profit' ASC) -- here I want to add the developers table
GROUP BY projects.project_id
ORDER BY 'Profit' ASC;

SELECT
  company_name,
  customer_name,
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id
  INNER JOIN companies_developers ON developers.id = companies_developers.developer_id
  INNER JOIN companies ON companies_developers.company_id = companies.company_id
  INNER JOIN customers_projects ON projects.project_id = customers_projects.project_id
  INNER JOIN customers ON customers_projects.customer_id = customers.customer_id

GROUP BY companies.company_id, customers.customer_id, projects.project_id
ORDER BY company_name ASC, customer_name, 'Profit' DESC;