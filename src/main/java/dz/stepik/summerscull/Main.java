package dz.stepik.summerscull;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {


        int matematicBalsOfTown = 0;
        int russianLanguageBalsOfTown = 0;
        int informaticBalsOfTown = 0;
        int allBalsOfTown = 0;

        int maxBalsInMath = 0;
        int maxBalsInRusLang = 0;
        int maxBalsInInform = 0;

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        ArrayList <Student> listOfStudents = new ArrayList<>();
        ArrayList <Scool> listOfScools = new ArrayList<>();
        ArrayList<Student> listBestsInMath = new ArrayList<>();
        ArrayList<Student> listBestsInRusLang = new ArrayList<>();
        ArrayList<Student> listBestsInInform = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String [] nextLine = scanner.nextLine().trim().split(" ");
            listOfStudents.add(new Student(nextLine[0], nextLine[1], Integer.parseInt(nextLine[2]), Integer.parseInt(nextLine[3]), Integer.parseInt(nextLine[4]), Integer.parseInt(nextLine[5])));
           if(listOfScools.size() == 0){
               listOfScools.add(new Scool(Integer.parseInt(nextLine[2]), 1, Integer.parseInt(nextLine[3]), Integer.parseInt(nextLine[4]), Integer.parseInt(nextLine[5])));
           }else {
               boolean haveInList = false;
               for (int j = 0; j < listOfScools.size(); j++) {
                   if (listOfScools.get(j).getNumber() == Integer.parseInt(nextLine[2])){
                       listOfScools.get(j).addNewStudent(Integer.parseInt(nextLine[3]), Integer.parseInt(nextLine[4]), Integer.parseInt(nextLine[5]));
                       haveInList = true;
                       break;
                   }
               }
               if (haveInList == false){
                   listOfScools.add(new Scool(Integer.parseInt(nextLine[2]), 1, Integer.parseInt(nextLine[3]), Integer.parseInt(nextLine[4]), Integer.parseInt(nextLine[5])));
               }
           }
           //Подсчёт среднегородского бала

            matematicBalsOfTown += Integer.parseInt(nextLine[3]);
           russianLanguageBalsOfTown += Integer.parseInt(nextLine[4]);
           informaticBalsOfTown += Integer.parseInt(nextLine[5]);
           allBalsOfTown += Integer.parseInt(nextLine[3]) + Integer.parseInt(nextLine[4]) + Integer.parseInt(nextLine[5]);

            //Конец подсчёта среднегородского бала
        }

        // Подсчёт студентов с лучшими балами
        for (int i = 0; i < listOfStudents.size(); i++) {
            if (i == 0){
                maxBalsInMath = listOfStudents.get(i).getMatematicBal();
                maxBalsInRusLang = listOfStudents.get(i).getRussianLanguageBal();
                maxBalsInInform = listOfStudents.get(i).getInformaticBal();
            }
            if (listOfStudents.get(i).getMatematicBal() > maxBalsInMath){
                maxBalsInMath = listOfStudents.get(i).getMatematicBal();
            }
            if (listOfStudents.get(i).getRussianLanguageBal() > maxBalsInRusLang){
                maxBalsInRusLang = listOfStudents.get(i).getRussianLanguageBal();
            }
            if (listOfStudents.get(i).getInformaticBal() > maxBalsInInform){
                maxBalsInInform = listOfStudents.get(i).getInformaticBal();
            }
        }
        // Забиваем студентов с лучшими балами в списки
        for (int i = 0; i < listOfStudents.size(); i++) {

            if (listOfStudents.get(i).getMatematicBal() == maxBalsInMath){
                listBestsInMath.add(listOfStudents.get(i));
            }
            if (listOfStudents.get(i).getRussianLanguageBal() == maxBalsInRusLang){
                listBestsInRusLang.add(listOfStudents.get(i));
            }
            if (listOfStudents.get(i).getInformaticBal() == maxBalsInInform){
                listBestsInInform.add(listOfStudents.get(i));
            }
        }
        // Сортируем списки лучших школоблогеров )))))))
        listBestsInMath.sort(Comparator.comparing(Student::getLastname)
                        .thenComparing(Student::getName));

        listBestsInRusLang.sort(Comparator.comparing(Student::getLastname)
                .thenComparing(Student::getName));

        listBestsInInform.sort(Comparator.comparing(Student::getLastname)
                .thenComparing(Student::getName));

        // ***********************************


        // Вывод

        System.out.println("Отчет по городу: математика - " + String.format(Locale.ENGLISH,"%.1f", ((double)matematicBalsOfTown) / n) + ", русский язык - " + String.format(Locale.ENGLISH,"%.1f", ((double)russianLanguageBalsOfTown) / n) + ", инфрматика - " + String.format(Locale.ENGLISH,"%.1f", ((double)informaticBalsOfTown) / n) + ", общий средний балл - " + String.format(Locale.ENGLISH,"%.1f", ((double)allBalsOfTown) / (n * 3)));
        System.out.println("Отчет по школам:");

        // Сортировка школ
        listOfScools.sort(Comparator.comparing(Scool::getAllSrBalsInThisScool)
                .thenComparing(Scool::getMatematicSrBals).reversed()
                .thenComparing(Scool::getRussianLanguageSrBals).reversed()
                .thenComparing(Scool::getInformaticSrBals).reversed()
                .thenComparing(Scool::getNumber).reversed());
        // Конец сортировки школ

        for (Scool oneScool :
                listOfScools) {
            System.out.println(oneScool.toString());
        }

        // Вывод лучших школоблогеров

        for (Student oneStudent :
                listBestsInMath) {
            System.out.println("Лучший результат по математике - "
                    + oneStudent.getLastname() + " "
                    + oneStudent.getName() + " - "
                    + oneStudent.getMatematicBal());
        }
        for (Student oneStudent :
                listBestsInRusLang) {
            System.out.println("Лучший результат по русскому языку - "
                    + oneStudent.getLastname() + " "
                    + oneStudent.getName() + " - "
                    + oneStudent.getRussianLanguageBal());
        }
        for (Student oneStudent :
                listBestsInInform) {
            System.out.println("Лучший результат по информатике - "
                    + oneStudent.getLastname() + " "
                    + oneStudent.getName() + " - "
                    + oneStudent.getInformaticBal());
        }

        // ВСЁ!!!! Вывод ушлёпков закончен

    }

}

class Student{
    private String lastname, name;
    private int scoolNumber, matematicBal, russianLanguageBal, informaticBal;

    public Student(String lastname,String name, int scoolNumber, int matematicBal, int russianLanguageBal, int informaticBal) {
        this.lastname = lastname;
        this.name = name;
        this.scoolNumber = scoolNumber;
        this.matematicBal = matematicBal;
        this.russianLanguageBal = russianLanguageBal;
        this.informaticBal = informaticBal;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScoolNumber() {
        return scoolNumber;
    }

    public void setScoolNumber(int scoolNumber) {
        this.scoolNumber = scoolNumber;
    }

    public int getMatematicBal() {
        return matematicBal;
    }

    public void setMatematicBal(int matematicBal) {
        this.matematicBal = matematicBal;
    }

    public int getRussianLanguageBal() {
        return russianLanguageBal;
    }

    public void setRussianLanguageBal(int russianLanguageBal) {
        this.russianLanguageBal = russianLanguageBal;
    }

    public int getInformaticBal() {
        return informaticBal;
    }

    public void setInformaticBal(int informaticBal) {
        this.informaticBal = informaticBal;
    }

    @Override
    public String toString() {
        return lastname + " " + name + " " + scoolNumber + " " + matematicBal + " " + russianLanguageBal + " " + informaticBal;
    }
}
class Scool{
    private int number;
    int countOfStuding;
    private int matematicCountBals, russianLanguageCountBals, informaticCountBals, allCountBalsInThisScool;
    private double matematicSrBals, russianLanguageSrBals, informaticSrBals, allSrBalsInThisScool;

    public Scool(int number, int countOfStuding,  int matematicCountBals, int russianLanguageCountBals, int informaticCountBals) {
        this.number = number;
        this.countOfStuding = countOfStuding;
        this.matematicCountBals = matematicCountBals;
        this.russianLanguageCountBals = russianLanguageCountBals;
        this.informaticCountBals = informaticCountBals;

        setAllCountBalsInThisScool(this.getMatematicCountBals(), this.getRussianLanguageCountBals(), this.getInformaticCountBals());
        // Подсчёт средних балов
        setMatematicSrBals(this.getMatematicCountBals());
        setRussianLanguageSrBals(this.getRussianLanguageCountBals());
        setInformaticSrBals(this.getInformaticCountBals());
        setAllSrBalsInThisScool(this.getAllCountBalsInThisScool());
        //********************************************************
    }
    public void addNewStudent(int matematicCountBals, int russianLanguageCountBals, int informaticCountBals){
        this.countOfStuding++;
        setMatematicCountBals(matematicCountBals);
        setRussianLanguageCountBals(russianLanguageCountBals);
        setInformaticCountBals(informaticCountBals);

        setAllCountBalsInThisScool(this.getMatematicCountBals(), this.getRussianLanguageCountBals(), this.getInformaticCountBals());
        // Подсчёт средних балов
        setMatematicSrBals(this.getMatematicCountBals());
        setRussianLanguageSrBals(this.getRussianLanguageCountBals());
        setInformaticSrBals(this.getInformaticCountBals());
        setAllSrBalsInThisScool(this.getAllCountBalsInThisScool());
        //********************************************************
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountOfStuding() {
        return countOfStuding;
    }

    public void setCountOfStuding(int countOfStuding) {
        this.countOfStuding++;
    }

    public int getMatematicCountBals() {
        return matematicCountBals;
    }

    public void setMatematicCountBals(int matematicCountBals) {
        this.matematicCountBals += matematicCountBals;
    }

    public int getRussianLanguageCountBals() {
        return russianLanguageCountBals;
    }

    public void setRussianLanguageCountBals(int russianLanguageCountBals) {
        this.russianLanguageCountBals += russianLanguageCountBals;
    }

    public int getInformaticCountBals() {
        return informaticCountBals;
    }

    public void setInformaticCountBals(int informaticCountBals) {
        this.informaticCountBals += informaticCountBals;
    }

    public int getAllCountBalsInThisScool() { return allCountBalsInThisScool; }

    public void setAllCountBalsInThisScool(int matematicCountBals, int russianLanguageCountBals, int informaticCountBals) {
        this.allCountBalsInThisScool = matematicCountBals + russianLanguageCountBals + informaticCountBals;
    }
    //********************************************************************************************


    public double getMatematicSrBals() {
        return matematicSrBals;
    }

    public void setMatematicSrBals(int matematicCountBals) {
        this.matematicSrBals = ((double)matematicCountBals / countOfStuding);
    }

    public double getRussianLanguageSrBals() {
        return russianLanguageSrBals;
    }

    public void setRussianLanguageSrBals(int russianLanguageCountBals) {
        this.russianLanguageSrBals =  ((double)russianLanguageCountBals / countOfStuding);
    }

    public double getInformaticSrBals() {
        return informaticSrBals;
    }

    public void setInformaticSrBals(int informaticCountBals) {
        this.informaticSrBals = ((double)informaticCountBals / countOfStuding);
    }

    public double getAllSrBalsInThisScool() {
        return allSrBalsInThisScool;
    }

    public void setAllSrBalsInThisScool(int allCountBalsInThisScool) {
        this.allSrBalsInThisScool = ((double)allCountBalsInThisScool / (getCountOfStuding() * 3));
    }
    //***********************************************************************************************************

    @Override
    public String toString() {

        return "Школа № " + getNumber() +
        ": математика - " + String.format(Locale.ENGLISH,"%.1f", getMatematicSrBals()) +
                ", русский язык - " + String.format(Locale.ENGLISH,"%.1f", getRussianLanguageSrBals()) +
                ", инфрматика - " + String.format(Locale.ENGLISH,"%.1f", getInformaticSrBals()) +
                ", общий средний балл - " + String.format(Locale.ENGLISH,"%.1f", getAllSrBalsInThisScool());
    }
}
