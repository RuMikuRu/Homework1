package org.example.utils.keys;

public class KeyGroupNum{
    int key;
    public KeyGroupNum(int key){
        this.key = key;
    }

    @Override
    public int hashCode()
    {
        int hash = this.key;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyGroupNum that = (KeyGroupNum) o;
        return key == that.key;
    }
}
