package org.example.utils;

import org.example.model.Person;

public class KeyFirstFamily{
    char key;
    public KeyFirstFamily(char key){
        this.key = key;
    }

    @Override
    public int hashCode()
    {
        int hash =(int)key;
        System.out.println("hashCode for key: "
                + key + " = " + hash);
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
