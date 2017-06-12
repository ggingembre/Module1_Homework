USE homework11;

ALTER TABLE projects
  ADD cost DOUBLE DEFAULT NULL
  AFTER project_name;

UPDATE projects SET cost=10000 WHERE project_name like 'Pay System';
UPDATE projects SET cost=8000 WHERE project_name like 'Site Dev';
UPDATE projects SET cost=6000 WHERE project_name like 'Analysis tools';