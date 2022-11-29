package org.procode.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 *  Object that represents a Task.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "header")
    @NotBlank(message = "{Required}")
    @Size(min = 4, max = 32, message = "{Size.taskEntity.name}")
    private String head;

    @Column(name = "body")
    @NotBlank(message = "{Required}")
    @Size(min = 4, max = 250, message = "{Size.taskEntity.description}")
    private String body;

    @Column(name = "priority")
    private String priority;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

}
