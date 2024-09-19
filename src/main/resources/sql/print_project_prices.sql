SELECT PROJECT.ID AS project_id,
       SUM(WORKER.SALARY)*DATEDIFF('MONTH',START_DATE,FINISH_DATE) AS price
FROM PROJECT
JOIN PROJECT_WORKER ON PROJECT.ID = PROJECT_WORKER.PROJECT_ID
JOIN WORKER ON PROJECT_WORKER.WORKER_ID = WORKER.ID
GROUP BY project_id
ORDER BY price DESC