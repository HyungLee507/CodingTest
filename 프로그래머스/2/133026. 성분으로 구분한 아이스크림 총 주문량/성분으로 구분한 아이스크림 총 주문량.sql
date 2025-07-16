-- 코드를 입력하세요
SELECT INGREDIENT_TYPE, sum(TOTAL_ORDER) as TOTAL_ORDER
from FIRST_HALF as fh
join ICECREAM_INFO i
on i.FLAVOR = fh.FLAVOR
group by i.INGREDIENT_TYPE
order by sum(total_order) ;