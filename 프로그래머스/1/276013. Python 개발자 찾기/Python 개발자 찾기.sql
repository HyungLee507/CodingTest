-- 코드를 작성해주세요
select id, email, first_name, last_name
from developer_infos
WHERE 'Python' IN (skill_1, skill_2, skill_3)
order by id;