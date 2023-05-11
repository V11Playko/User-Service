INSERT INTO `user` (
    `id`,
    `birthdate`,
    `dni_number`,
    `mail`,
    `name`,
    `password`,
    `phone`,
    `surname`,
    `id_role`
  )
VALUES
  (
    '1',
    '2000-04-28',
    '123',
    'heinner@gmail.com',
    'Heinner',
    '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S',
    '+57 3134647980',
    'Vega',
    '1'
  );


INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('3', 'ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('4', 'ROLE_CLIENT', 'ROLE_CLIENT');