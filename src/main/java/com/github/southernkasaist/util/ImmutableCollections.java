package com.github.southernkasaist.util;

/**
 * The static factory of immutable collections.
 */
public final class ImmutableCollections {
    private ImmutableCollections() {}

    public static <T> Queue<T> newQueue() {
        return new StackImmutableQueue<>();
    }

    public static <T> Stack<T> newStack() {
        return new LinkedImmutableStack<>();
    }
}
