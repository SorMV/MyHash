package Profession1;

import java.util.ArrayList;

/**
 * Created by User on 020 20.10.17.
 */
public class Pair<K extends java.lang.Number, V> {

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public V getV() {
        return v;
    }

    public ArrayList<Pair<K, V>> isPair(Pair<K, V> a) {
        if (a != null) {
            ArrayList<Pair<K, V>> array = new ArrayList<>();
            array.add(a);
            return array;
        }
        return null;
    }

    public ArrayList<Pair<K, V>> isPair(ArrayList<Pair<K, V>> a) {
        if (a != null) {
            return a;
        }
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getK() + "] " + "\t" + "[" + this.getV() + "]".toString();
    }

    private K k;
    private V v;
}
