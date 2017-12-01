package Profession1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 031 31.10.17.
 */
public class MyHash<K extends Number, V> {

    private static double n = 3;

    private static void setCapacity(int capacity) {
        MyHash.capacity = (int) Math.pow(2, n);
    }

    private static int capacity = (int) Math.pow(2, n);
    private static Object[] table = new Object[capacity];

    private static final float loadfactor = 0.75f;
    private int treshold = Math.round(capacity * loadfactor);

    public int hash(K k) {
        if (k != null)
            return (int) Math.floor(capacity % k.intValue());
        else
            return 0;
    }

    public void put(K k, V v) {
        Pair p = new Pair<>(k, v);
        while (hash(k) >= table.length) {
            table = hashsize(table);
        }
        if (table[hash(k)] != null) {
            ArrayList<Pair> ar = new ArrayList<>();
            try {
                ar = (ArrayList<Pair>) table[hash(k)];
                if (ar.size() > treshold || hash(k) >= table.length) {
                    table = hashsize(table);
                    ar = (ArrayList<Pair>) table[hash(k)];
                }
            } catch (ClassCastException e) {
                ar.add((Pair) table[hash(k)]);
            } finally {
                boolean flg = true;
                for (Pair par : ar) {
                    if (par.getK().equals(p.getK())) {
                        ar.set(ar.indexOf(par), p);
                        table[hash(k)] = ar;
                        flg = false;
                    }
                }
                if (flg) {
                    ar.add(p);
                    table[hash(k)] = ar;
                }
            }
        } else table[hash(k)] = p;
    }

    public V get(K k) {
        try {
            for (Pair p : (ArrayList<Pair>) table[hash(k)]) {
                if (p.getK().equals(k))
                    return (V) p.getV();
            }
        } catch (ClassCastException e) {
            return (V) ((Pair) table[hash(k)]).getV();
        }
        return null;
    }

    private Object[] hashsize(Object[] table) {
        n = n + 1;
        Object[] newMap = table;
        table = Arrays.copyOf(new Object[table.length * 2], table.length * 2);
        setCapacity(capacity);
        for (int i = 0; i < newMap.length; i++) {
            ArrayList<Pair> newArr = new ArrayList<>();
            try {
                newArr = ((ArrayList<Pair>) newMap[i]);
            } catch (ClassCastException e) {
                newArr.add((Pair) newMap[i]);
            } finally {
                if (newArr != null) {
                    for (Pair par : newArr) {
                        if (table[hash((K) par.getK())] == null) {
                            table[hash((K) par.getK())] = newArr;
                            break;
                        } else {
                            ArrayList<Pair> ar = new ArrayList<>();
                            try {
                                ar.addAll((ArrayList<Pair>) table[hash((K) par.getK())]);
                            } catch (ClassCastException e) {
                                ar.add((Pair) table[hash((K) par.getK())]);
                            } finally {
                                ar.addAll(newArr);
                                table[hash((K) par.getK())] = ar;
                            }
                        }
                    }
                }
            }
        }
        return table;
    }

    public static void print(MyHash map) {
        for (Object element : map.table) {
            if (element != null) {
                for (int i = 0; i < ((ArrayList) element).size(); i++) {
                    System.out.println(((ArrayList) element).get(i).toString());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyHash createMap = new MyHash();
        createMap.put(2, 3);
        createMap.put(4, 7);
        createMap.put(2, 5);
        createMap.put(4, 8);
        createMap.put(3, 56);
        createMap.put(4, 11);
        createMap.put(3, 15);
        createMap.put(5, 100);
        createMap.put(6, 6);
        createMap.put(5, 99);
        createMap.put(7, 49);
        createMap.put(9, 49);
        createMap.put(1, 77);
        createMap.put(16, 0);
        createMap.put(50, 500);
        createMap.put(60, 600);
        createMap.put(70, 700);
        createMap.put(70, "gbcmrf");
        createMap.put(10.5, "jfcsdjf");
        createMap.put(10.5, "lalka, sasai");
        System.out.println(createMap.get(2));
        System.out.println(createMap.get(4));
        print(createMap);
    }
}







