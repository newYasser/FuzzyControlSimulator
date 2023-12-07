import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fuzzifier
{


    private List<Variables>variablesList;
    private Map<String, Variables>variablesNames;

    private boolean checkWithinRange(Double crisp, List<FuzzySetPoints>fuzzySetValues)
    {
        for(int i = 0; i < fuzzySetValues.size()-1; ++i)
        {
            if(crisp > fuzzySetValues.get(i).getX() &&crisp < fuzzySetValues.get(i+1).getX())return true;
        }
        return false;
    }

    void fuzzification(Variables currentVariable, Double crispValue)
    {
        List<FuzzySet> fuzzySetList = new ArrayList<>();
        fuzzySetList= currentVariable.getFuzzySetList();
        Map<String,Double>ans = new HashMap<>();

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
                                ans.put(fuzzySetList.get(i).getName(),1.0);
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
                double y = slope * crispValue + b;
                ans.put(fuzzySetList.get(i).getName(),y);
            }
            else ans.put(fuzzySetList.get(i).getName(),0.0);
        }

        currentVariable.setFuzzificationAnswer(ans);


    }

    Fuzzifier()
    {
        variablesList = new ArrayList<>();
        variablesNames = new HashMap<>();
    }
    private void AddElementToVariablesList(Variables variable)
    {
        variablesList.add(variable);
    }

    void populateVariablesMap()
    {
        for(Variables list : variablesList)variablesNames.put(list.getName(),list);
    }


    public List<Variables> getVariablesList()
    {
        return variablesList;
    }

    public void setVariablesList(List<Variables> variablesList)
    {
        this.variablesList = variablesList;
    }

    public Map<String, Variables> getVariablesNames()
    {
        return variablesNames;
    }

    public void setVariablesNames(Map<String, Variables> variablesNames)
    {
        this.variablesNames = variablesNames;
    }
}
