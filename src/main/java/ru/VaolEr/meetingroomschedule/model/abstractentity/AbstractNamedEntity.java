package ru.VaolEr.meetingroomschedule.model.abstractentity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractEntity {

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public AbstractNamedEntity setName(String name) {
        this.name = name;
        return this;
    }
}
