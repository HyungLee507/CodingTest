-- 코드를 입력하세요
SELECT b.BOOK_ID, au.AUTHOR_NAME, DATE_FORMAT(b.PUBLISHED_DATE,'%Y-%m-%d') as PUBLISHED_DATE
FROM BOOK as b
JOIN AUTHOR as au 
on b.author_id = au.author_id
WHERE b.category = '경제'
ORDER BY b.PUBLISHED_DATE; 
