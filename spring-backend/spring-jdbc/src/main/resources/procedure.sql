CREATE PROCEDURE read_book(
  IN  in_id     VARCHAR(20),
  OUT out_title VARCHAR(255),
  OUT out_price DECIMAL(9, 2))
  BEGIN
    SELECT
      title,
      price
    INTO out_title, out_price
    FROM s_book
    WHERE id = in_id;
  END