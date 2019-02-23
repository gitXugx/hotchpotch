package design.mode.dm.creational.bp.v2;

/**
 * @author ：ex-xugaoxiang001
 * @description ：
 * @copyright ：	Copyright 2018 yowits Corporation. All rights reserved.
 * @create ：2018/12/25 16:25
 */
public class Menu {

    private Drink drink;

    private Dishe dishe;

    private Food food;

    public static class ProductBuild{

        private Drink drink;

        private Dishe dishe;

        private Food food;

        public ProductBuild drink(Drink drink) {
            this.drink = drink;
            return this;
        }

        public ProductBuild dishe(Dishe dishe) {
            this.dishe = dishe;
            return this;

        }

        public ProductBuild food(Food food) {
            this.food = food;
            return this;
        }

        private ProductBuild(){}

        public static ProductBuild builder(){
            return new ProductBuild();
        }

        public Menu build(){
            Menu menu = new Menu();
            menu.dishe =  this.dishe;
            menu.drink = this.drink;
            menu.food = this.food;
            return menu;
        }
    }

    public void showMenu(){
        if(food != null){
            food.showFood();
        }
        if(drink != null){
            drink.showSmell();
        }
        if(dishe != null){
            dishe.showDishes();
        }
    }

}
