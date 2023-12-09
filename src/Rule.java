public class Rule {
    private String[] splitedRuleStatement;

    public Rule(String statement){
        this.splitedRuleStatement = statement.split(" ");
    }

    public String[] splitTheRule(){
        return splitedRuleStatement;
    }



}
