import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fuzzifier
{


    private List<Variables>variablesList;



    List<Variables> fuzzification(Variables currentVariable, Double crispValue)
    {
        

        return variablesList;
    }

    Fuzzifier()
    {
        variablesList = new ArrayList<>();
    }

    private void AddElementToVariablesList(Variables variable)
    {
        variablesList.add(variable);
    }


    public List<Variables> getVariablesList()
    {
        return variablesList;
    }

    public void setVariablesList(List<Variables> variablesList)
    {
        this.variablesList = variablesList;
    }
}
