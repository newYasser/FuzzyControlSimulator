import java.util.*;

public class Inference {
    private List<Rule>rules;
    private LinguisticVariable outputVariable;
    private Map<String, LinguisticVariable>fuzzifiedVariables;
    public Inference(Map<String, LinguisticVariable> fuzzifiedVariables,List<Rule>rules){
        this.rules = rules;
        this.fuzzifiedVariables = fuzzifiedVariables;
        String[]temp = rules.get(0).splitTheRule(); // to get the output variable
        outputVariable = fuzzifiedVariables.get(temp[temp.length - 2]);
    }
    private LinguisticVariable getOutputVariable(String outputVariable){
        return fuzzifiedVariables.get(outputVariable);
    }

    private Double getValueFromFuzzifiedVariables(String LinguisticVariable,String fuzzySet){
        Double value = fuzzifiedVariables.get(LinguisticVariable)
                .getFuzzificationAnswer()
                .get(fuzzySet).get(0);  
        return value;
    }
    private LinguisticVariable solveRules(){
        Map<String, List<Double>> inferencedFuzzySets = null;
        for(Rule rule:rules){
            String[] temp = rule.splitTheRule();
            List<Double>tempList = new ArrayList<>();
            inferencedFuzzySets.put(temp[temp.length - 1],tempList);
        }
        for(Rule i:rules){
            String[]rule = i.splitTheRule();
            Double[] answers = new Double[rule.length];
            Arrays.fill(answers,0.0);
            Double value1,value2,outputAnswer = 0.0;
            for(int j = 0; rule[j].equals("=>");++j){
                if(rule[j].equals("not")){
                    value1 = getValueFromFuzzifiedVariables(rule[j+1],rule[j+2]);
                    answers[j] = 1-value1;
                }
            }
            for(int j = 0;rule[j].equals("=>");++j){
                if(rule[j].equals("and")){
                    value1 = getValueFromFuzzifiedVariables(rule[j-2],rule[j-1]);
                    if(rule[j + 1].equals("not")){
                        answers[j] = Double.min(answers[j+1],value1);
                    }
                    else{
                        value2 = getValueFromFuzzifiedVariables(rule[j+1],rule[j+2]);
                        answers[j] = Double.min(value1,value2);
                    }
                }
            }
            for(int j = 0; j < answers.length;++j){
                outputAnswer = Double.max(outputAnswer,answers[0]);
            }

            inferencedFuzzySets.get(rule[rule.length-1]).add(outputAnswer);
        }
        outputVariable.setFuzzificationAnswer(inferencedFuzzySets);
        return outputVariable;
    }


}
//proj_funding high or exp_level expert => risk low
// low -> 1-(inputVar->fuzzySet->answer)
// min(low,)