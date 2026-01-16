package com.example.gestionstock.util;

import com.example.gestionstock.exceptions.MyResourceNotFoundException;

public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Check if some condition is true, otherwise throw exception.
     * @param expression boolean condition to check
     * @param message error message if condition is false
     * @throws MyResourceNotFoundException if expression is false
     */
    public static void checkFound(final boolean expression, final String message) {
        if (!expression) {
            throw new MyResourceNotFoundException(message); // Fixed: use the parameter
        }
    }

    /**
     * Check if resource exists, otherwise throw exception.
     * @param resource the resource to check
     * @return the resource if not null
     * @throws MyResourceNotFoundException if resource is null
     */
    public static <T> T checkFound(final T resource) {
        return checkFound(resource, "Resource not found"); // Delegate to the method below
    }

    /**
     * Check if resource exists with custom message, otherwise throw exception.
     * @param resource the resource to check
     * @param message custom error message
     * @return the resource if not null
     * @throws MyResourceNotFoundException if resource is null
     */
    public static <T> T checkFound(final T resource, final String message) {
        if (resource == null) {
            throw new MyResourceNotFoundException(message);
        }
        return resource;
    }
}
