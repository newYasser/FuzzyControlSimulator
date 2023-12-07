import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fuzzifier
{


    private List<Variables>variablesList;

    private boolean checkWithinRange(Double crisp, List<Integer>fuzzySetValues)
    {
        for(int i = 0; i < fuzzySetValues.size()-1; ++i)
        {
            if(crisp > fuzzySetValues.get(i) &&crisp < fuzzySetValues.get(i+1))return true;
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
            List<Integer>fuzzySetValues = new ArrayList<>();
            fuzzySetValues = fuzzySetList.get(i).getValues();

            if(checkWithinRange(crispValue,fuzzySetValues))
            {
                int[] point1 = new int[2];
                int[] point2 = new int[2];
                double slope,b,y;
                for(int j = 0; j < fuzzySetValues.size()-1; ++j)
                {
                    if(crispValue > fuzzySetValues.get(j) && crispValue < fuzzySetValues.get(j+1))
                    {
                        if(currentVariable.getType().equals("TRAP") )
                        {
                            if(j == 1)
                            {
                                y = 1;continue;
                            }
                            point1[0] = fuzzySetValues.get(j);
                            point2[0] = fuzzySetValues.get(j+1);
                            if((j+1) == 1 || j == 2)point1[1] = point2[1] =1;
                            else point1[1] = point2[1] =0;
                        }
                        else
                        {
                            point1[0] = fuzzySetValues.get(j);
                            point2[0] = fuzzySetValues.get(j+1);

                            if(j == 1)point1[1] = point2[1] =1;
                            else point1[1] = point2[1] =0;
                        }

                    }
                }
                //y = ax + b
                // calculating the line equation
                slope = (double) ((double)point2[1] - point1[1]) / (point2[0] - point1[0]);
                b = point2[1] - (slope * point2[0]);
                y = slope * crispValue + b;
                ans.put(fuzzySetList.get(i).getName(),y);
            }
            else ans.put(fuzzySetList.get(i).getName(),0.0);
        }

        currentVariable.setFuzzificationAnswer(ans);


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
