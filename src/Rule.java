public class Rule {
    private String[] splitedRuleStatement;

    public Rule(String statement){
        SplitRuleStatement(statement);
    }
    private void SplitRuleStatement(String statement){
        if(statement.split(" ").length <= 8){
            this.splitedRuleStatement = statement.split(" ");
        }
        else{
            System.out.println("Enter the rule in the right format");
        }
    }

    public String[]getRule(){
        return splitedRuleStatement;
    }



}
