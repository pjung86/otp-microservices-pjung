INSERT INTO client (id, name, email)
VALUES
    (1, 'Teszt Aladár', 'teszt.aladar@optmobil.com'),
    (2, 'Teszt Benedek', 'teszt.benedek@optmobil.com'),
    (3, 'Teszt Cecília', 'teszt.cecilia@optmobil.com');

INSERT INTO client_device (id, device_hash, client_id)
VALUES
    (1, 'F67C2BCBFCFA30FCCB36F72DCA22A817', 1),
    (2, '0F1674BD19D3BBDD4C39E14734FFB876', 1),
    (3, '3AE5E9658FBD7D4048BD40820B7D227D', 1),
    (4, 'FADDFEA562F3C914DCC81956682DB0FC', 2),
    (5, 'E68560872BDB2DF2FFE7ADC091755378', 3);

INSERT INTO client_token (id, token, client_id)
VALUES
    (1, 'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJkY2N0MyQkNCRkNGQTMwRkNDQjM2RjcyRENBMjJBODE3', 1),
    (2, 'dGVzenQuYmVuZWRla0BvdHBtb2JpbC5jb20mMjAwMCZGQURERkVBNTYyRjNDOTE0RENDODE5NTY2ODJEQjBGQw==', 2),
    (3, 'dGVzenQuY2VjaWxpYUBvdHBtb2JpbC5jb20mMzAwMCZFNjg1NjA4NzJCREIyREYyRkZFN0FEQzA5MTc1NTM3OA==', 3),
    (4, 'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJjBGMTY3NEJEMTlEM0JCREQ0QzM5RTE0NzM0RkZCODc2', 1),
    (5, 'dGVzenQuYWxhZGFyQG90cG1vYmlsLmNvbSYxMDAwJjNBRTVFOTY1OEZCRDdENDA0OEJENDA4MjBCN0QyMjdE', 1);

INSERT INTO client_bank_card (id, card_number, cvc_code, name, amount, currency, client_id)
VALUES
    (1, '5299706965433676', 123, 'Teszt Aladár', 1000, 'HUF', 1),
    (2, '5390508354245119', 456, 'Teszt Benedek', 2000, 'HUF', 2),
    (3, '4929088924014470', 123, 'Teszt Cecília', 3000, 'HUF', 3);