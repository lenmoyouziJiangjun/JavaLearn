package factory.factory;
                                                         
public class StrawberryGardener implements FruitGardener 
{
    public Fruit factory()
    {
        return new Apple();
    }
}
