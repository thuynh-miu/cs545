INSERT INTO Users (name) VALUES
                             ('Alice'),
                             ('Bob'),
                             ('Charlie');

INSERT INTO Post (title, content, author, user_id) VALUES
                                              ('Introduction to Spring Boot', 'Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.', 'Alice', 1),
                                              ('Understanding Dependency Injection', 'Dependency Injection is a design pattern used to implement IoC, in which the control of objects is transferred to a container.', 'Bob', 2),
                                              ('RESTful APIs with Spring Boot', 'Creating RESTful APIs in Spring Boot is straightforward and can be achieved using @RestController annotations.', 'Charlie', 3),
                                              ('Advanced Spring Data JPA', 'Spring Data JPA provides a powerful way to access and manipulate data in Spring applications.', 'Bob', 2);

