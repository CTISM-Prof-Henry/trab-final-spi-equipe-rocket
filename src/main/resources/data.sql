-- Alunos (IDs gerados automaticamente)
INSERT INTO aluno (nome, email, foto) VALUES
                                          ('Maria Silva', 'maria@email.com', 'maria.png'),
                                          ('João Pereira', 'joao.pereira@gmail.com', 'joao.png'),
                                          ('Ana Costa', 'ana.costa@gmail.com', 'ana.png'),
                                          ('Carlos Eduardo', 'carlos.eduardo@gmail.com', 'carlos.png'),
                                          ('Fernanda Lima', 'fernanda.lima@gmail.com', 'fernanda.png');

-- Coordenadores (IDs gerados automaticamente)
INSERT INTO coordenador (nome, email, senha, foto) VALUES
                                                       ('Admin', 'admin@admin.com', 'admin', 'adm.jpg'),
                                                       ('Leonardo Andrade', 'leonardo@gmail.com', '1234', 'leonardo.jpg'),
                                                       ('Mariana Silva', 'mariana.silva@gmail.com', 'senha123', 'mariana.png');

-- Cursos (IDs gerados automaticamente, referência ao coordenador via subquery)
INSERT INTO curso (nome, evasao_media, coordenador_id) VALUES
                                                           ('Engenharia de Software', 12.5, (SELECT matricula FROM coordenador WHERE email = 'admin@admin.com')),
                                                           ('Direito', 9.5, (SELECT matricula FROM coordenador WHERE email = 'leonardo@gmail.com')),
                                                           ('Administração', 7.8, (SELECT matricula FROM coordenador WHERE email = 'mariana.silva@gmail.com')),
                                                           ('Medicina', 5.2, (SELECT matricula FROM coordenador WHERE email = 'admin@admin.com')),
                                                           ('Arquitetura', 8.3, (SELECT matricula FROM coordenador WHERE email = 'leonardo@gmail.com'));

-- Matrículas (IDs gerados automaticamente, referências ao aluno e curso via subqueries)
INSERT INTO matricula (aluno_id, curso_id, historico_evasao) VALUES
                                                                 ((SELECT matricula FROM aluno WHERE email = 'maria@email.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Engenharia de Software'),
                                                                  '{"2020": 0.60, "2021": 0.35, "2022": 0.64, "2023": 0.26, "2024": 0.49}'),

                                                                 ((SELECT matricula FROM aluno WHERE email = 'joao.pereira@gmail.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Direito'),
                                                                  '{"2020": 0.55, "2021": 0.40, "2022": 0.50, "2023": 0.30, "2024": 0.45}'),

                                                                 ((SELECT matricula FROM aluno WHERE email = 'ana.costa@gmail.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Administração'),
                                                                  '{"2020": 0.40, "2021": 0.35, "2022": 0.38, "2023": 0.25, "2024": 0.20}'),

                                                                 ((SELECT matricula FROM aluno WHERE email = 'carlos.eduardo@gmail.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Engenharia de Software'),
                                                                  '{"2020": 0.70, "2021": 0.65, "2022": 0.60, "2023": 0.55, "2024": 0.50}'),

                                                                 ((SELECT matricula FROM aluno WHERE email = 'fernanda.lima@gmail.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Medicina'),
                                                                  '{"2020": 0.20, "2021": 0.15, "2022": 0.10, "2023": 0.05, "2024": 0.02}'),

                                                                 ((SELECT matricula FROM aluno WHERE email = 'maria@email.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Administração'),
                                                                  '{"2020": 0.50, "2021": 0.45, "2022": 0.55, "2023": 0.60, "2024": 0.65}'),

                                                                 ((SELECT matricula FROM aluno WHERE email = 'ana.costa@gmail.com'),
                                                                  (SELECT codigo FROM curso WHERE nome = 'Arquitetura'),
                                                                  '{"2020": 0.30, "2021": 0.25, "2022": 0.35, "2023": 0.20, "2024": 0.15}');
