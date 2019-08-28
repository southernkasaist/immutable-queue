package com.github.southernkasaist.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class StackImmutableQueueTest {
    @Test
    public void head_on_empty() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        assertThatThrownBy(target::head).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void deQueue_on_empty() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        assertThatThrownBy(target::deQueue).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void isEmpty_on_empty() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        assertThat(target.isEmpty()).isTrue();
    }

    @Test
    public void enQueue_null() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        assertThatThrownBy(() -> target.enQueue(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void enQueue_empty() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        final Queue<Integer> a = target.enQueue(1);
        assertThat(a).isNotSameAs(target);
        assertThat(a.isEmpty()).isFalse();
        assertThat(a.head()).isEqualTo(1);
        assertThat(target.isEmpty()).isTrue();
    }

    @Test
    public void enQueue_deQueue_enQueue() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        final Queue<Integer> a = target.enQueue(1).enQueue(2);
        assertThat(a.head()).isEqualTo(1);

        // dequeue to empty out
        final Queue<Integer> b = a.deQueue();
        assertThat(b.isEmpty()).isFalse();
        assertThat(b.head()).isEqualTo(2);

        // enqueue again
        final Queue<Integer> c = b.enQueue(3);
        assertThat(c.head()).isEqualTo(2);
    }

    @Test
    public void deQueue_multiple_elements() {
        final Queue<Integer> target = new StackImmutableQueue<>();

        final Queue<Integer> a = target.enQueue(1).enQueue(2).enQueue(3);
        assertThat(a.head()).isEqualTo(1);
        assertThat(a.deQueue().head()).isEqualTo(2);
        assertThat(a.deQueue().deQueue().head()).isEqualTo(3);
        assertThat(a.deQueue().deQueue().deQueue().isEmpty()).isTrue();
    }
}
