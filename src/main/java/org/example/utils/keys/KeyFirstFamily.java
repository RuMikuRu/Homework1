package org.example.utils.keys;

public class KeyFirstFamily{
    char key;
    public KeyFirstFamily(char key){
        this.key = key;
    }

    @Override
    public int hashCode()
    {
        int hash =(int)key;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyFirstFamily that = (KeyFirstFamily) o;
        return key == that.key;
    }


}
