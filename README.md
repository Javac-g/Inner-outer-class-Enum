# -Javac-g-Inner-outer-class.-Enum-
Practice tasks.

############ Task 1 ############
Suppose we have the next class:
<pre>
<code>
public class Pizza{
    
    private String cheese;
    private String meat;
    private String seafood;
    private String vegetable;
    private String mushroom;
    
    public Pizza(){
        
    }
    public static PizzaBuilder  base(){
        
        return new PizzaBuilder();
        
    }
    
}

</code>
</pre>
Create public static inner class named PizzaBuilder inside Pizza class that correspond the next class diagram:
<pre>
                PizzaBuilder
- pizza: Pizza
- PizzaBuilder()
+ addCheese(cheese:String): PizzaBuilder
+ addMeat(meat:String): PizzaBuilder
+ addSeafood(seafood:String): PizzaBuilder
+ addVegetablee(vegetable:String): PizzaBuilder
+ addMushroom(mushroom:String): PizzaBuilder
+ build():Pizza

</pre>
############ Task 2 ############
############ Task 3 ############
############ Task 4 ############
############ Task 5 ############
############ Task 6 ############
