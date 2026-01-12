package co.id.gradyfernando.model;

import java.util.Objects;

public class BaseResponseModel<T> {
    private T data;
    private int error;
    private String error_message;

    // Getters
    public T getData() {
        return data;
    }

    public int getError() {
        return error;
    }

    // Setter (Only for 'error' since it was 'var' in Kotlin)
    public void setError(int error) {
        this.error = error;
    }

    public String getError_message() {
        return error_message;
    }

    // Data Class boilerplate
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResponseModel<?> that = (BaseResponseModel<?>) o;
        return error == that.error && 
               Objects.equals(data, that.data) && 
               Objects.equals(error_message, that.error_message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, error, error_message);
    }

    @Override
    public String toString() {
        return "BaseResponseModel{" +
                "data=" + data +
                ", error=" + error +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}