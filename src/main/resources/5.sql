USE homework11;

SELECT
  -- companies.company_id,
  companies.company_name,
  -- customers.customer_id,
  customers.customer_name,
  MIN((projects.cost) - SUM(developers.salary)) AS `profit`
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

GROUP BY companies.company_id, customers.customer_id, projects.project_id
ORDER BY `profit` DESC;