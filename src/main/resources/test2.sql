-- syntax to select minimum profit table

USE homework11;

SELECT
  project_name,
  project_description,
  (cost - SUM(developers.salary)) AS 'Profit'

FROM projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id

GROUP BY projects.project_id
ORDER BY 'Profit' ASC;

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

              GROUP BY projects.project_id) -- here I want to add the developers table
GROUP BY projects.project_id
ORDER BY 'Profit' ASC;