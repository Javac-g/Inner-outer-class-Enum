

public class Pizza {
    private String cheese,meat,seafood,vegetable, mushroom;

    private Pizza() { }

    public String getCheese() {
        return cheese;
    }
    public String getMeat() {
        return meat;
    }
    public String getSeafood() {
        return seafood;
    }
    public String getVegetable() {
        return vegetable;
    }
    public String getMushroom() {
        return mushroom;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public void setSeafood(String seafood) {
        this.seafood = seafood;
    }

    public void setVegetable(String vegetable) {
        this.vegetable = vegetable;
    }

    public void setMushroom(String mushroom) {
        this.mushroom = mushroom;
    }

    public static PizzaBuilder base() {
        return new PizzaBuilder();
    }

    public static class  PizzaBuilder{
        private Pizza pizza;
       
        private PizzaBuilder(){
            pizza = new Pizza();
        }

        public Pizza getPizza() {
            return pizza;
        }

        public void setPizza(Pizza pizza) {
            this.pizza = pizza;
        }

        public PizzaBuilder addCheese(String cheese){

            pizza.setCheese(cheese);

            return this;
        }
        public PizzaBuilder addMeat(String meat){

            pizza.setMeat(meat);

            return this;
        }
        public PizzaBuilder addSeafood(String seafood ){
            pizza.setSeafood(seafood);
            return this;
        }
        public PizzaBuilder addVegetable(String vegetable) {
            pizza.setVegetable(vegetable);
            return this;
        }
        public PizzaBuilder addMushroom(String mushroom) {
            pizza.setMushroom(mushroom);
            return this;
        }
        public Pizza build(){
            return pizza;
        }



    }

}

public class Oven {
    
    public static Pizza cook() {
        return Pizza.base().addCheese("Feta").addSeafood("Caviar").addVegetable("Cucumber").build();

    }
}
