package ru.VaolEr.meetingroomschedule.util;

import com.sun.istack.NotNull;
import ru.VaolEr.meetingroomschedule.model.abstractentity.AbstractBaseEntity;
import ru.VaolEr.meetingroomschedule.util.exceptions.IllegalRequestDataException;
import ru.VaolEr.meetingroomschedule.util.exceptions.NotFoundException;

import java.util.Optional;

public class ValidationUtil {

    public static <T> T checkNotFound(@NotNull Optional<T> optional, String msg) {
        return optional.orElseThrow(() -> new NotFoundException("Not found entity with " + msg));
    }

    public static String addMessageDetails(String entityType, String identifier) {
        return String.format("type is '%s' and identifier is '%s'", entityType, identifier);
    }

    public static String addMessageDetails(String entityType, Integer entityId) {
        return addMessageDetails(entityType, entityId.toString());
    }

    public static <E extends AbstractBaseEntity> void assureIdConsistent(E entity, Integer id) {
        // http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else {
            assert entity.getId() != null;
            if (!entity.getId().equals(id)) {
                throw new IllegalRequestDataException(entity + " must be with id = " + id);
            }
        }
    }

}
