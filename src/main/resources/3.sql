USE homework11;

SELECT
  SUM(developers.salary) AS Sum_Salaries_Java_Developers
FROM
  developers
  INNER JOIN developers_skills on developers.id = developers_skills.developer_id
  INNER JOIN skills on developers_skills.skill_id = skills.id

WHERE
  skills.skillName LIKE 'java';
