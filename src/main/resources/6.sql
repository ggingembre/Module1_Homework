-- Вычислить, среднюю ЗП программистов в проекте, который приносит наименьшую прибыль.
-- how to insert this condition... where

USE homework11;

SELECT
  projects.project_name,
  projects.project_description,
  AVG(developers.salary) AS 'average salary'

FROM
    projects
  INNER JOIN
    projects_developers ON projects.project_id = projects_developers.project_id
  INNER JOIN
      developers ON projects_developers.developer_id = developers.id

  WHERE project_name IN
        (
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
                                                                                                                       projects_developers ON projects_developers.developer_id = developers.id
                                                                                                                                             INNER JOIN
                                                                                                                                             projects ON projects_developers.project_id = projects.project_id
                                                                                                                                                      INNER JOIN
          customers_projects ON projects.project_id = customers_projects.project_id
          INNER JOIN
                customers ON customers_projects.customer_id = customers.customer_id

          GROUP BY companies.company_id, customers.customer_id, projects.project_id
                                                                ORDER BY `profit` DESC)

GROUP BY projects.project_id
ORDER BY 'average salary' DESC;
