package dz.stepik.summerscull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//Для тестов логики боя:
        //Hero myHero = new Warrior();
//            Hero myHero = new Archer();
            Hero myHero = new Magician();

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(30, 20, 1200)); // 1 - Пряничный человек
        enemies.add(new Enemy(2018, 1, 350)); // 2 - Туча комаров
        enemies.add(new Enemy(280, 50, 3050)); // 3 - Вор
        enemies.add(new Enemy(100500, 100500, 100500)); // 4 - Гальватрон

        //найден клад
        for (int i = 0; i < 300; i++){
            Item item = new Item(0, 0, i % 3); // i%3 - будет задавать предмету тип 0, 1, 2
            myHero.openItem(item);  // герои получает предмет в руки
        }
                System.out.println("Всего собрано: "+myHero.items.size());


        //
//        System.out.println(myHero.getSimpleClassName());
//        for (Item item :
//                myHero.items) {
//            System.out.println(item.toString());
//        }
//        System.out.println(myHero.getIndexOfNextItem());

        // бой!
        int count = 1;
        for (Enemy enemy : enemies){
            System.out.println( count++ + "-й бой:");
            System.out.println("  Герои { hp=" + myHero.hp + " mana=" + myHero.mana + " }");
            System.out.println("  Враг { hp=" + enemy.hp + " }");
            int counter = 1;
            while (myHero.hp > 0 & enemy.hp > 0){
                System.out.println( counter++ + " раунд:");
                myHero.attack(enemy);
                myHero.defense(enemy);
                System.out.println("    Герои { hp=" + myHero.hp + " mana=" + myHero.mana + " }");
                System.out.println("    Враг { hp=" + enemy.hp + " }");
            }
            System.out.println(myHero.hp > 0 ? "Победа, герой получил опыт "+ enemy.exp : "Поражение");
            System.out.printf("Герой { HP=%d, MANNA=%d, damage=%d, exp=%d }\n", myHero.hp, myHero.mana, myHero.damage, myHero.exp);
        }

        System.out.println("Осталось итемов: " + myHero.items.size());


    }
}



class Item{
    // тип 2 -- заклинание
    // тип 1 -- холодное оружие
    // тип 0 -- нихрена!
    int price, weight, type;
    public Item(int price, int weight, int type){
        this.price = price;
        this.weight = weight;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "type=" + type +
                '}';
    }
}

class Enemy{
    int hp, damage;
    int exp;
    public Enemy(int hp, int damage, int exp){
        this.hp = hp;
        this.damage = damage;
        this.exp = exp;
    }
}

abstract class Hero{

    protected String nickName;
    protected int s, a, i, exp, hp, mana; // сила, ловкость, интеллект, опыт, здоровье, мана небесная
    protected int x, y; // координаты героя на карте
    protected ArrayList<Item> items = new ArrayList<>(); // список поднятых предметов
    protected int damage; // урон


    public int getMana(){
        return mana;
    }
    public int getDamage(){
        return damage;
    }
    public int getHp(){
        return hp;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void goToCursor(int xx, int yy){
        x = xx; y = yy;
    }

    public abstract void attack(Enemy enemy); // этот метод должнен быть определен в классах наследниках

    public abstract void defense(Enemy enemy);// этот метод должнен быть определен в классах наследниках

    public void openItem(Item item){
        Random random = new Random();
        if ((random.nextInt(100) + 1) < 50){
            items.add(item);
        }
        // метод должен добавлять предмет в список с вероятностью 50 %
        // для осуществления вероятностных процессов можно использовать случайное число от 0 до 100.
    }
    public int getIndexOfNextItem(){

        if (getSimpleClassName().equals("Warrior")){
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).type == 1){
                    return j;
                }
            }
            return -1;
        }else if (getSimpleClassName().equals("Magician")){
            for (int j = 0; j < items.size(); j++) {
                if (items.get(j).type == 2){
                    return j;
                }
            }
            return -1;
        }else{
            return -1;
        }
    }
    public void removeNextItem(int index){
        if (index > -1){
            items.remove(index);
        }
    }
    public String getSimpleClassName(){
        return this.getClass().getSimpleName();
    }
    public int getLevel(){
        return this.exp / 500;
    }
    public int getNewLevels(int newExp){
        int temp = this.exp % 500;
        return (newExp + temp) / 500;
    }
}

class Warrior extends Hero{
    public Warrior(){
        this.nickName = "Абапел";
        this.s = 100;
        this.a = 50; //ловкость сука! ))))
        this.i = 1;
        this.exp = 0;
        this.hp = 500;
        this.mana = 10;
        this.x = 0;
        this.y = 0;
        this.damage = 150;
    }
    public void attack(Enemy enemy){
        //System.out.println("Воин еблызь!");

        //    В методе атаки у воина нужно проверить, что оба соперника живы.
//    Герои должен нанести урон врагу, с учетом его способностей и бонусов.
//    Потом нужно проверить не умер ли враг.
//    Если умер - вызвать метод начисления опыта, проверить получил ли герои уровень,
//    вызвать метод для повышения базовых характеристик.
//    Если враг не умер, то дальнейшие действия описывать в методе защиты, т.к. враг даст ответную атаку

        if (this.getHp() > 0 && enemy.hp > 0){
            //начинается замес
            int haveNextItem = this.getIndexOfNextItem();
            enemy.hp -= (this.damage + ((haveNextItem > -1) ? 100 : 0));
            this.removeNextItem(haveNextItem);
            if (enemy.hp <= 0){
                this.exp += enemy.exp;
                this.damage += 20 * getNewLevels(enemy.exp);
                this.s += 10 * getNewLevels(enemy.exp);
                this.a += 10 * getNewLevels(enemy.exp);
                this.i += 10 * getNewLevels(enemy.exp);
                this.hp += 200 * getNewLevels(enemy.exp);
            }
        }

    }
    public void defense(Enemy enemy){
//        System.out.println("Враг еблызь!");
        if (enemy.hp > 0){
            this.hp -= enemy.damage;
        }
    }
            /*
класс должен обладать всеми свойствами героя при создании воин должен обладать следующими характеристиками:
 здоровье - 500, мана - 10, сила - 100, ловкость - 50, интеллект - 1, опыт 0, урон - 150.
 При атаке герой наносит цели урон и получает урон в ответ от цели,
 действия повторяются пока кто то не победит.
 В случае победы герой получает опыт цели, каждые 500 единиц опыта герой получает новый уровень.
 Новый уровень дает +10 ко всем характеристикам, +200 здоровья и +20 урона.
 Если воин имеет предмет 1 типа, к его урону добавляется 100.
            Уже мертвая цель - урона герою не наносит!!!
            */
}

class Archer extends Hero{
    public Archer(){
        this.nickName = "Ушлёпок";
        this.s = 20;
        this.a = 150; //ловкость сука! ))))
        this.i = 30;
        this.exp = 0;
        this.hp = 200;
        this.mana = 50;
        this.x = 0;
        this.y = 0;
        this.damage = 200;
    }

    public void attack(Enemy enemy){
        //System.out.println("Воин еблызь!");

        //    В методе атаки у воина нужно проверить, что оба соперника живы.
//    Герои должен нанести урон врагу, с учетом его способностей и бонусов.
//    Потом нужно проверить не умер ли враг.
//    Если умер - вызвать метод начисления опыта, проверить получил ли герои уровень,
//    вызвать метод для повышения базовых характеристик.
//    Если враг не умер, то дальнейшие действия описывать в методе защиты, т.к. враг даст ответную атаку

        if (this.getHp() > 0 && enemy.hp > 0){
            //начинается замес
            int haveNextItem = this.getIndexOfNextItem();
            enemy.hp -= (this.damage + ((haveNextItem > -1) ? 100 : 0));
            this.removeNextItem(haveNextItem);
            if (enemy.hp <= 0){
                this.exp += enemy.exp;
                this.damage += 50 * getNewLevels(enemy.exp);
                this.s += 10 * getNewLevels(enemy.exp);
                this.a += 30 * getNewLevels(enemy.exp);
                this.i += 10 * getNewLevels(enemy.exp);
                this.hp += 50 * getNewLevels(enemy.exp);
            }
        }

    }
    public void defense(Enemy enemy){
//        System.out.println("Враг еблызь!");
        if (enemy.hp > 0){
            Random r = new Random();
            if ((r.nextInt(100) + 1) > 30)
            this.hp -= enemy.damage;
        }
    }

    @Override
    public void openItem(Item item){
        // Ушлёпок забирает все итемы без вариантов (((
            items.add(item);
        }
            /*
                класс должен обладать всеми свойствами героя при создании лучник
                должен обладать следующими характеристиками:
                здоровье - 200, мана - 50, сила - 20, ловкость - 150, интеллект - 30, опыт 0, урон - 200.
            атака по аналогии с воином, но в процессе атаки лучник имеет 30% шанс избежать урон
            лучник всегда открывает предмет - 100%
            Новый уровень дает +10 ко всем характеристикам, +50 здоровья и +50 урона и 30 ловкости.
            */

}

class Magician extends Hero{
    public Magician(){
        this.nickName = "Чмырдяй";
        this.s = 5;
        this.a = 30; //ловкость сука! ))))
        this.i = 300;
        this.exp = 0;
        this.hp = 100;
        this.mana = 5000;
        this.x = 0;
        this.y = 0;
        this.damage = 40;

        eduAllCasts();
    }
    private ArrayList<Item> casts = new ArrayList<>();
    public void eduAllCasts(){
        for (int j = 0; j < items.size(); j++) {
            if (items.get(j).type == 2){
                eduCast(items.get(j));
            }
        }
    }
    /*
        класс должен обладать всеми свойствами героя
        при создании маг должен обладать следующими характеристиками:
        здоровье - 100, мана - 5000, сила - 5, ловкость - 30, интеллект - 300, опыт 0, урон - 40.
    атака по аналогии с воином
    помимо обычной атаки, маг имеет возможность бить заклинанием в процессе атаки
    защита мага осуществляется магическим щитом, который полгащает весь урон,
    но вместо здоровья тратит ману.
    Новый уровень дает +10 ко всем характеристикам, +30 здоровья, 1000 маны и +10 урона.
    В случае возможности убить врага с руки - маг бьет с руки!!! в Первую очередь
    */
    public void makeCast(Enemy enemy){
        // если в списке есть заклятия можно его произнести потратив 100 маны и нанеся 1000 урона цели.
        // после произнесения предмет из списка пропадает
        enemy.hp -= 1000;
        this.setMana(this.getMana() - 100);
    }

    public void eduCast(Item item) {
        casts.add(item);
                /*
                если предмет или предметы из списка имеют тип 2, это заклинания и их нужно добавить в список
заклинаний мага
                */
    }
        public void attack(Enemy enemy){
            if (this.getHp() > 0 && enemy.hp > 0){
                //начинается замес
                if (powerOfHits(this.getHp(), enemy.damage) < powerOfHits(enemy.hp, this.getDamage()) && this.getMana() >= 100){
                    int haveNextItem = this.getIndexOfNextItem();
                    this.makeCast(enemy);
                    this.removeNextItem(haveNextItem);
                }else {
                    enemy.hp -= this.getDamage();
                }

                if (enemy.hp <= 0){
                    this.exp += enemy.exp;
                    this.damage += 10 * getNewLevels(enemy.exp);
                    this.s += 10 * getNewLevels(enemy.exp);
                    this.a += 10 * getNewLevels(enemy.exp);
                    this.i += 10 * getNewLevels(enemy.exp);
                    this.hp += 30 * getNewLevels(enemy.exp);
                    this.setMana(this.getMana() + 1000);
                }
            }
        }

    public void defense(Enemy enemy){
//        System.out.println("Враг еблызь!");
        if (enemy.hp > 0){
            if (this.getMana() > enemy.damage){
                this.setMana(this.getMana() - enemy.damage);
            }else if (this.getMana() > 0){
                int tempDamage = enemy.damage - this.getMana();
                this.setMana(0);
                this.hp = this.getHp() - tempDamage;
            }else {
                this.hp = this.getHp() - enemy.damage;
            }
        }
    }

        public double powerOfHits(int hp, int damage){
        return (double) hp / damage;

        }


}