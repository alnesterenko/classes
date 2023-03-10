package dz.stepik.summerscull;

public class Main {
    public static void main(String[] args) {

        Car bmw = new Car();
        bmw.fueling(50);
        bmw.go(700);
        System.out.println(bmw.levelOfFuel);
        System.out.println(bmw.countOfKm);
    }
}

class Car{
    int countOfKm = 0;
    int levelOfFuel = 0;
    public void fueling(int liters){
        levelOfFuel = (levelOfFuel + liters) > 60 ? 60 : (levelOfFuel + liters);
    }
    public void go(int km){
        // 1 литр на 10 км
        int tempFuel = km / 10;
        if (tempFuel > levelOfFuel){
            tempFuel = levelOfFuel;
        }
        levelOfFuel -= tempFuel;
        countOfKm += tempFuel * 10;
    }

}