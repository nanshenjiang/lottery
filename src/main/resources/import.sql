INSERT INTO user (id, username, password) VALUES (1, 'admin', '123456');
INSERT INTO user (id, username, password) VALUES (2, 'xie', '123456');
-- $2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi   是123456加密后的结果

INSERT INTO authority (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_WINNER');
INSERT INTO authority (id, name) VALUES (4, 'ROLE_VISITOR');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2)