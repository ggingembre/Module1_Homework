
USE homework11;

SELECT
  SUM (projects.cost - developers.salary) AS `profit`

FROM
  projects
  INNER JOIN project_developers ON projects.project_id = project_developers.project_id
  INNER JOIN developers ON project_developers.developer_id = developers.id

GROUP BY projects.project_id
ORDER BY profit DESC;
