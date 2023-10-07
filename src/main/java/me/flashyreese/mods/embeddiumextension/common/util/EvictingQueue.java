package me.flashyreese.mods.embeddiumextension.common.util;

import java.util.LinkedList;

public class EvictingQueue<E> extends LinkedList<E> {
    private final int limit;

    public EvictingQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) {
            super.remove();
        }
        return true;
    }
}