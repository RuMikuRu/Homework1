package org.example.utils;

import org.example.model.Person;

public class KeyAge{
    int key;
    public KeyAge(int key){
        this.key = key;
    }

    @Override
    public int hashCode()
    {
        int hash = key;
        System.out.println("hashCode for key: "
                + key + " = " + hash);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyAge keyAge = (KeyAge) o;
        return key == keyAge.key;
    }
}
