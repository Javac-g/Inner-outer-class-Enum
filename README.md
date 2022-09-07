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
<pre>
Suppose we have the next class:
<code>
class NameList{
    
    private String[] names = {"Mike","Emily","Nick","Patric","Sara"};
    
    public Iterator getIterator(){
        return new Iterator();
    }
}
</code>
Create public inner class named Iterator inside NameList class that correspond the next class diagram:
                  Iterator
- counter: int = 0
- Iterator()
+ hasNext():boolean //returns true if the next element exist in the list,otherwise returns false
+ next(): String //return current element and add 1 to counter
</pre>
############ Task 3 ############
############ Task 4 ############
############ Task 5 ############
############ Task 6 ############
