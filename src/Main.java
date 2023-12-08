import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        List<Rule>rules = new ArrayList<>();
        rules.add(new Rule("proj_funding high or exp_level expert => risk low"));
        rules.add(new Rule("proj_funding medium and exp_level intermediate => risk normal"));
        rules.add(new Rule("proj_funding medium and exp_level beginner => risk normal"));
        rules.add(new Rule("proj_funding low and exp_level beginner => risk high"));
        rules.add(new Rule("proj_funding very_low and_not exp_level expert => risk high"));

        


    }
}