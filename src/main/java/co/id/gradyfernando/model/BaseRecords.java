package co.id.gradyfernando.model;

import java.util.Objects;

public class BaseRecords<T> {
    private T records;

    // Getter
    public T getRecords() {
        return records;
    }

    // Data Class boilerplate
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseRecords<?> that = (BaseRecords<?>) o;
        return Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }

    @Override
    public String toString() {
        return "BaseRecords{" +
                "records=" + records +
                '}';
    }
}