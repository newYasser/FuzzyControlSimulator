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


    public LinguisticVariable solveRules(){
        Map<String, List<Double>> inferencedFuzzySets = new HashMap<>();
        for(Rule rule:rules){
            String[] temp = rule.splitTheRule();
            List<Double>tempList = new ArrayList<>();
            inferencedFuzzySets.put(temp[temp.length - 1],tempList);
        }
        for(Rule i:rules){
            String[]rule = i.splitTheRule();
            Double[] answers = new Double[rule.length];
            Arrays.fill(answers,0.0);
            Double outputAnswer = 0.0;
            boolean thereIsOr = false;
            for(int j = 0; !rule[j].equals("=>");++j){
                if(!(rule[j].equals("or") ||rule[j].equals("and") || rule[j].equals("not"))){
                    answers[j + 1] = getValueFromFuzzifiedVariables(rule[j],rule[j+1]);
                    j++;
                }
            }
            for(int j = 0; !rule[j].equals("=>");++j){
                if(rule[j].equals("not")){
                    answers[j + 1] = 1 - answers[j + 1];
                }
                if(rule[j].equals("or")) thereIsOr = true;
            }
            for(int j = 0; !rule[j].equals("=>");++j){
                if(rule[j].equals("and")){
                    if(rule[j + 1].equals("not")){
                        answers[j] = Double.min(answers[j + 3],answers[j-1]);
                        answers[j + 3] = 0.0;
                        answers[j - 1] = 0.0;
                    }
                    else{
                        answers[j] = Double.min(answers[j - 1],answers[j +  2]);
                        answers[j - 1] = 0.0;
                        answers[j +  2] = 0.0;
                    }
                }
            }
            if(thereIsOr) {
                for (int j = 0; !rule[j].equals("=>"); ++j) {
                    outputAnswer = Double.max(outputAnswer, answers[j]);
                }
            }
            else {
                for (int j = 0; !rule[j].equals("=>"); ++j) {
                    outputAnswer = Double.min(outputAnswer, answers[j]);
                }
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