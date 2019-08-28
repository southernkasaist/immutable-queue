package com.github.southernkasaist.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class LinkedImmutableStackTest {
    @Test
    public void peek_on_empty() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        assertThatThrownBy(target::peek).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void pop_on_empty() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        assertThatThrownBy(target::pop).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void isEmpty_on_empty() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        assertThat(target.isEmpty()).isTrue();
    }

    @Test
    public void push_null() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        assertThatThrownBy(() -> target.push(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void reverse_on_empty() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        final Stack<Integer> reversed = target.reverse();
        assertThat(reversed.isEmpty()).isTrue();
        assertThat(target.isEmpty()).isTrue();
        // don't create new instance on empty stack
        assertThat(target).isSameAs(reversed);
    }

    @Test
    public void push_and_pop() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        final Stack<Integer> a = target.push(1);
        assertThat(a.isEmpty()).isFalse();
        assertThat(a.peek()).isEqualTo(1);
        // different instance
        assertThat(target).isNotSameAs(a);
        assertThat(target.isEmpty()).isTrue();

        final Stack<Integer> b = a.push(2);
        assertThat(a.peek()).isEqualTo(1);
        assertThat(b.peek()).isEqualTo(2);
        // different instance
        assertThat(a).isNotSameAs(b);
        // previous linked node is a
        assertThat(b.pop()).isSameAs(a);
        // no effect to a
        assertThat(a.peek()).isEqualTo(1);
        // no effect to b
        assertThat(b.peek()).isEqualTo(2);
    }

    @Test
    public void reverse() {
        final Stack<Integer> target = new LinkedImmutableStack<>();

        final Stack<Integer> a = target.push(1).push(2);
        assertThat(a.peek()).isEqualTo(2);
        assertThat(a.pop().peek()).isEqualTo(1);
        assertThat(a.pop().pop().isEmpty()).isTrue();

        final Stack<Integer> reversed = a.reverse();
        assertThat(reversed).isNotSameAs(a);
        assertThat(reversed.peek()).isEqualTo(1);
        assertThat(reversed.pop().peek()).isEqualTo(2);
        assertThat(reversed.pop().pop().isEmpty()).isTrue();
    }
}
