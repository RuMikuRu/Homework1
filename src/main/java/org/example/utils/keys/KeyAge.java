package org.example.utils.keys;

public class KeyAge{
    int key;
    public KeyAge(int key){
        this.key = key;
    }

    @Override
    public int hashCode()
    {
        int hash = key;
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
