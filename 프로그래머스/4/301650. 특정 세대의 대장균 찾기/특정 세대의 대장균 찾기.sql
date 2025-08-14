SELECT c2.id
FROM ECOLI_DATA r         
JOIN ECOLI_DATA c1 ON c1.parent_id = r.id   
JOIN ECOLI_DATA c2 ON c2.parent_id = c1.id 
WHERE r.parent_id IS NULL
ORDER BY c2.id;
