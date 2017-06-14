USE homework11;

SELECT
  customers.customer_id,
  customers.customer_name,
  SUM(projects.cost) - SUM(developers.salary) AS `profit`

FROM
  developers
  INNER JOIN
    project_developers ON project_developers.developer_id = developers.id
  INNER JOIN
    projects ON project_developers.project_id = projects.project_id
  INNER JOIN
    customers_projects ON projects.project_id = customers_projects.project_id
  INNER JOIN
    customers ON customers_projects.customer_id = customers.customer_id

  WHERE customers.customer_id = 20

GROUP BY customers.customer_id
ORDER BY profit DESC;