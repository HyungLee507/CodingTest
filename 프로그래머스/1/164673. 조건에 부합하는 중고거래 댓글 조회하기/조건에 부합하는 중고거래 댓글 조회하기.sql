SELECT 
  ugb.title, 
  ugr.board_id, 
  ugr.reply_id, 
  ugr.writer_id, 
  ugr.contents, 
  date_format(ugr.created_date, '%Y-%m-%d') 
FROM USED_GOODS_BOARD AS ugb
JOIN USED_GOODS_REPLY AS ugr
  ON ugb.board_id = ugr.board_id
WHERE DATE_FORMAT(ugb.created_date, '%Y-%m') = '2022-10'
ORDER BY ugr.created_date ASC, ugb.title ASC;