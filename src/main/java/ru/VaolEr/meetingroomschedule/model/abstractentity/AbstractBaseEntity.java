package ru.VaolEr.meetingroomschedule.model.abstractentity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    public AbstractBaseEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }
}
