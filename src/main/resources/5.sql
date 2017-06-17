-- Найти клиента (customer), которая приносит меньше всего прибыли компании (company) для каждой из компаний .
-- first, for each company, find the project with least profit

USE homework11;

-- the problem with this query is that I do not know how to select the min profit per company
-- also, I do not know how to sum profit if one client has more than one project
-- finally, for some reason, the sorting by profit does not work

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

SELECT
  -- companies.company_id,
  companies.company_name,
  -- customers.customer_id,
  customers.customer_name,
  ((projects.cost) - SUM(developers.salary)) AS `profit`
-- when I run MIN((projects.cost) - SUM(developers.salary)) AS `profit` I get
-- an error message: "invalid use of group function
-- Without min, I am computing the profit per company, per customer, per project

FROM
    companies
  INNER JOIN
    companies_developers ON companies.company_id = companies_developers.company_id
  INNER JOIN
    developers ON companies_developers.developer_id = developers.id
  INNER JOIN
    project_developers ON project_developers.developer_id = developers.id
  INNER JOIN
    projects ON project_developers.project_id = projects.project_id
  INNER JOIN
    customers_projects ON projects.project_id = customers_projects.project_id
  INNER JOIN
    customers ON customers_projects.customer_id = customers.customer_id

   WHERE (projects.cost) - SUM(developers.salary) = (SELECT MIN(((projects.cost) - SUM(developers.salary))))

GROUP BY customers.customer_id, projects.project_id, companies.company_id
ORDER BY `profit` DESC;

