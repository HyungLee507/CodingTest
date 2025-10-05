SELECT o.ORDER_ID, o.PRODUCT_ID, DATE_FORMAT(o.OUT_DATE, '%Y-%m-%d') as OUT_DATE,
    CASE 
        WHEN DATE_FORMAT(o.OUT_DATE, '%Y-%m-%d')  <= '2022-05-01' THEN '출고완료'
        WHEN DATE_FORMAT(o.OUT_DATE, '%Y-%m-%d')  > '2022-05-01' THEN '출고대기'
        ELSE '출고미정'
    END as '출고여부'
FROM FOOD_ORDER o 
ORDER BY o.order_id;