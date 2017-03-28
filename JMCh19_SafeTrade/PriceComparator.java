import java.lang.reflect.Field;

/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{

    public boolean ascending;


    public PriceComparator()
    {
        ascending = true;
    }


    public PriceComparator( boolean asc )
    {
        ascending = asc;
    }


    public int compare( TradeOrder order1, TradeOrder order2 )
    {
        if ( order1.isMarket() && order2.isMarket() )
        {
            return 0;
        }
        if ( order1.isMarket() && order2.isLimit() )
        {
            return -1;
        }
        if ( order1.isLimit() && order2.isMarket() )
        {
            return 1;
        }
        else
        {
            if ( ascending == true )
            {
                return (int)( ( order1.getPrice() - order2.getPrice() ) * 100 );
            }
            else
            {
                return (int)( ( order2.getPrice() - order1.getPrice() ) * 100 );
            }
        }
    }
    
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }

}
