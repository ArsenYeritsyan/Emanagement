package org.procode.management.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 *  Object that represents an Employee.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "firstname")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.employeeEntity.firstname}")
    private String firstname;

    @Column(name = "surname")
    @NotBlank(message = "{Required}")
    @Size(min = 2, message = "{Size.employeeEntity.surname}")
    private String surname;

    @Column(name = "middlename")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.employeeEntity.middlename}")
    private String middlename;

    @Column(name = "department")
    @NotBlank(message = "{Required}")
    @Size(min = 2, message = "{Size.employeeEntity.department}")
    private String department;

    @Column(name = "position")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.employeeEntity.position}")
    private String position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<TaskEntity> tasks;

    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="manager_id")
    private Manager manager;

    public void addTaskToEmployee(TaskEntity task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        task.setEmployee(this);
    }
}
