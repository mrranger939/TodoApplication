package com.shujath.todoapp.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "todo_lists",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "todo_lists_user_id_name_uindex",
                        columnNames = {"user_id", "name"}
                )
        }
)
@Getter
@Setter
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "todo_lists_users_id_fk")
    )
    private User user;
}
