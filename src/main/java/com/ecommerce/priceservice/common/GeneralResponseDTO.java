package com.ecommerce.priceservice.common;

import java.util.List;

public class GeneralResponseDTO<T> {
    private String codigo;
    private String message;
    private T data;
    private List<String> errors;
    private Boolean warnings;

    public GeneralResponseDTO(String codigo, String message, T data, List<String> errors, Boolean warnings) {
        this.codigo = codigo;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.warnings = warnings;
    }

    // Getters and Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Boolean getWarnings() {
        return warnings;
    }

    public void setWarnings(Boolean warnings) {
        this.warnings = warnings;
    }

    // Builder pattern
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private String codigo;
        private String message;
        private T data;
        private List<String> errors;
        private Boolean warnings;

        public Builder<T> codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> errors(List<String> errors) {
            this.errors = errors;
            return this;
        }

        public Builder<T> warnings(Boolean warnings) {
            this.warnings = warnings;
            return this;
        }

        public GeneralResponseDTO<T> build() {
            return new GeneralResponseDTO<>(codigo, message, data, errors, warnings);
        }
    }
}
