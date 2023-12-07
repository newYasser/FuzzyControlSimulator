import java.util.List;
import java.util.Map;

public class Inference {
    private List<Rule>rules;
    private Variables outputVariable;
    private Map<String,Variables>fuzzifiedVariables;
    public Inference(Map<String,Variables> fuzzifiedVariables){
        this.fuzzifiedVariables = fuzzifiedVariables;
    }
    private Variables getOutputVariable(String outputVariable){
        return fuzzifiedVariables.get(outputVariable);
    }
    private void solveRules(){
        for(Rule i:rules){
            String[]rule= i.getRule();
            for(int j = 0; j < rule.length;++j){
                if(rule[j].equals("not")){
                    // get the fuzzication answer and update it 
                }
            }
        }
    }


}
