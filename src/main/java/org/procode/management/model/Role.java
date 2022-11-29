package org.procode.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.procode.management.config.ApplicationUserRole;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false, columnDefinition = "CHAR(14)")
    private ApplicationUserRole roleType;

    public Role(ApplicationUserRole roleType) {
        this.roleType = roleType;
    }
}
