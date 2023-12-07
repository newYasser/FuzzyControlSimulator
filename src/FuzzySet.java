import java.util.ArrayList;
import java.util.List;

public class FuzzySet
{


    private String name,type;
    private List<FuzzySetPoints>values;

    FuzzySet(String name, String type,List<FuzzySetPoints> values)
    {
        setName(name);
        setType(type);
        if(type.equals("TRI")) values = new ArrayList<>(3);
        else values = new ArrayList<>(4);

        setValues(values);

    }

    public List<FuzzySetPoints> getValues()
    {
        return values;
    }

    public void setValues(List<FuzzySetPoints> values)
    {
        this.values = values;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }



    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


}
