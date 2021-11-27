INSERT INTO products(title, total_investing_amount, started_at, finished_at)
VALUES ('You can be elon musk', 100000000, CURDATE() - 1, CURDATE() + 1),
       ('You also can be bill gates', 500000000, CURDATE() + 1 , CURDATE() + 2),
       ('or jobs?', 300000000, CURDATE() + 2, CURDATE() + 3),
       ('TOBE-RICH of Warren Buffett', 600000000, CURDATE() - 2, CURDATE() + 2)
;