USE homework11;

SELECT
  projects.project_id,
  projects.project_name,
  projects.project_description,
  SUM(developers.salary) sum_salary
FROM
  developers
  INNER JOIN
  project_developers ON developers.id = project_developers.developer_id
  INNER JOIN
  projects ON project_developers.project_id = projects.project_id
GROUP BY projects.project_id
ORDER BY sum_salary DESC;