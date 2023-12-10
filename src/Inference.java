import java.util.*;

public class Inference {
    private List<Rule>rules;
    private LinguisticVariable outputVariable;
    private Map<String, LinguisticVariable>fuzzifiedVariables;
    public Inference(Map<String, LinguisticVariable> fuzzifiedVariables,List<Rule>rules){
        this.rules = rules;
        this.fuzzifiedVariables = fuzzifiedVariables;
        String[]temp = rules.get(0).getSplitTheRule(); // to get the output variable
        this.outputVariable = getOutputVariable(temp[temp.length - 2]);
    }
    private LinguisticVariable getOutputVariable(String outputVariableName){
        return fuzzifiedVariables.get(outputVariableName);
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
            String[] temp = rule.getSplitTheRule();
            List<Double>tempList = new ArrayList<>();
            inferencedFuzzySets.put(temp[temp.length - 1],tempList);
        }
        for(Rule i:rules){
            String[]rule = i.getSplitTheRule();
            Double[] answers = new Double[rule.length];
            Arrays.fill(answers,0.0);
            Double outputAnswer = 0.0;
            List<Integer>orsIndexes = new ArrayList<>();
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
                        orsIndexes.add(j);
                    }
                    else{
                        answers[j] = Double.min(answers[j - 1],answers[j +  2]);
                        answers[j - 1] = 0.0;
                        answers[j +  2] = 0.0;
                        orsIndexes.add(j);
                    }
                }
            }
            if(thereIsOr) {
                for (int j = 0; !rule[j].equals("=>"); ++j) {
                       outputAnswer = Double.max(outputAnswer, answers[j]);
                }
            }
            else {
                outputAnswer = answers[orsIndexes.get(0)];
                for (int j = 1; j < orsIndexes.size(); ++j) {
                       outputAnswer = Double.min(outputAnswer, answers[orsIndexes.get(j)]);
                }
            }
            inferencedFuzzySets.get(rule[rule.length-1]).add(outputAnswer);
        }
        this.outputVariable.setFuzzificationAnswer(inferencedFuzzySets);
        return outputVariable;
    }


}
//proj_funding high or exp_level expert => risk low
// low -> 1-(inputVar->fuzzySet->answer)
// min(low,)