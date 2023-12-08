import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fuzzifier
{


    private List<LinguisticVariable>variablesList;
    private Map<String, LinguisticVariable>variablesNames;

    private boolean checkWithinRange(Double crisp, List<FuzzySetPoints>fuzzySetValues)
    {
        for(int i = 0; i < fuzzySetValues.size()-1; ++i)
        {
            if(crisp > fuzzySetValues.get(i).getX() &&crisp < fuzzySetValues.get(i+1).getX())return true;
        }
        return false;
    }

    void fuzzification(LinguisticVariable currentVariable, Double crispValue)
    {
        List<FuzzySet> fuzzySetList = new ArrayList<>();
        fuzzySetList= currentVariable.getFuzzySetList();
        Map<String,List<Double>>ans = new HashMap<>();

        for(int i =0; i < fuzzySetList.size(); ++i)
        {
            List<FuzzySetPoints>fuzzySetValues = new ArrayList<>();
            fuzzySetValues = fuzzySetList.get(i).getValues();

            if(checkWithinRange(crispValue,fuzzySetValues))
            {
                int[] point1 = new int[2];
                int[] point2 = new int[2];

                for(int j = 0; j < fuzzySetValues.size()-1; ++j)
                {
                    if(crispValue > fuzzySetValues.get(j).getX() && crispValue < fuzzySetValues.get(j+1).getX())
                    {
                        if(fuzzySetList.get(i).getType().equals("TRAP"))
                        {
                            if(j == 1 )
                            {
                                List<Double>values =new ArrayList<>();
                                values.add(1.0);
                                ans.put(fuzzySetList.get(i).getName(),values);
                                break;
                            }
                        }
                        point1[0] = fuzzySetValues.get(j).getX();
                        point1[1] = fuzzySetValues.get(j).getY();
                        point2[0] = fuzzySetValues.get(j+1).getX();
                        point2[1] = fuzzySetValues.get(j+1).getY();
                    }
                }
                //y = ax + b
                // calculating the line equation
                double slope = (double) ((double)point2[1] - point1[1]) / (point2[0] - point1[0]);
                double b = point2[1] - (slope * point2[0]);
                Double y = slope * crispValue + b;
                List<Double>values =new ArrayList<>();
                values.add(y);
                ans.put(fuzzySetList.get(i).getName(),values);
            }
            else{
                List<Double>values =new ArrayList<>();
                values.add(0.0);
                ans.put(fuzzySetList.get(i).getName(),values);}
        }

        currentVariable.setFuzzificationAnswer(ans);


    }

    Fuzzifier()
    {
        variablesList = new ArrayList<>();
        variablesNames = new HashMap<>();
    }
    private void AddElementToVariablesList(LinguisticVariable variable)
    {
        variablesList.add(variable);
    }

    void populateVariablesMap()
    {
        for(LinguisticVariable linguisticVariable : variablesList)
            variablesNames.put(linguisticVariable.getName(),linguisticVariable);
    }


    public List<LinguisticVariable> getVariablesList()
    {
        return variablesList;
    }

    public void setVariablesList(List<LinguisticVariable> variablesList)
    {
        this.variablesList = variablesList;
    }

    public Map<String, LinguisticVariable> getVariablesNames()
    {
        return variablesNames;
    }

    public void setVariablesNames(Map<String, LinguisticVariable> variablesNames)
    {
        this.variablesNames = variablesNames;
    }
}
