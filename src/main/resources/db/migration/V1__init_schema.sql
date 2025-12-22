create table users
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) not null,
    username varchar(100) not null,
    password varchar(255) not null,
    constraint uq_users_email
        unique (email)
);

create table todo_lists
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) not null,
    user_id bigint       not null,
    constraint todo_lists_user_id_name_uindex
        unique (user_id, name),
    constraint todo_lists_users_id_fk
        foreign key (user_id) references users (id)
            on delete cascade
);

create table todo_items
(
    id           bigint auto_increment
        primary key,
    title        varchar(255)                                                   not null,
    status       enum ('CREATED', 'IN_PROGRESS', 'COMPLETED', 'NOT_APPLICABLE') not null,
    deadline     date                                                           null,
    todo_list_id bigint                                                         not null,
    constraint todo_items_todo_lists_id_fk
        foreign key (todo_list_id) references todo_lists (id)
            on delete cascade
);

