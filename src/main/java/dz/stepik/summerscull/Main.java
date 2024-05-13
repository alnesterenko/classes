package dz.stepik.summerscull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        ParametrList<Integer> arrInt = new ParametrList<>();
//        arrInt.add(123);
//        arrInt.add(456);
//        arrInt.add(901);
//        System.out.println(arrInt.remove(1));
//        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            arrInt.add(i);
        }
        arrInt.add(101);
        arrInt.add(102);

        System.out.println(arrInt.remove(2));
        System.out.println(arrInt.toString());


    }
}

class ParametrList<E>{
    private Object [] data;
    private int size;
    private int razm = 100;

    public ParametrList(){
        size = 0;
        data = new Object[razm];
    }

    public int size(){
        return size;
    }

    public void add(E element){
        if (size >= razm){
            Object [] tempData = new Object[size() + 1];
            for (int i = 0; i < size(); i++) {
                tempData[i] = data[i];
            }
            tempData[size()] = element;
            size++;
            data = tempData;
        }else {
            data[size] = element;
            size++;
//            razm = size();
        }
    }

    public E get(int index){
        return (E) data[index];
    }

    public void set(int index, E value){
        data[index] = value;
    }

    public E remove(int index){
        E temp = (E) data[index];
//        data[index] = null;
        Object [] tempData = new Object[razm];
        int i, j;
        for (i = 0, j = 0; i < size(); i++, j++) {
            if (i == index){
                j--;
                continue;
            }else {
                tempData[j] = data[i];
            }
        }
        size--;
        data = tempData;
        return temp;
    }

    public void insert(int index, E value){
        E temp = value;
        int newRazm;
        if (size > razm){
            newRazm = size + 1;
        }else {
            newRazm = razm;
        }

        Object [] tempData = new Object[newRazm];
        int i, j;
        for (i = 0, j = 0; i < size(); i++, j++) {
            if (j == index){
                tempData[index] = temp;
                i--;

            }else {
                tempData[j] = data[i];
            }
        }
        size++;
        data = tempData;
    }

    public int find(E value){
        for (int i = 0; i < size; i++) {
            if (value == data[i]){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(data[i]).append(" ");
        }
        return s.toString();
    }
}
