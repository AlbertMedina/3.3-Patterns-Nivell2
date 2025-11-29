package menu;

public abstract class EntityMenuHandler<T> extends AbstractMenuHandler {

    protected final T entity;

    protected EntityMenuHandler(T entity) {
        if (entity == null) {
            throw new NullPointerException("Entity cannot be null");
        }
        this.entity = entity;
    }
}
