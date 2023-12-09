import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinguisticVariable project = new LinguisticVariable("IN", "proj_funding", 0, 100);
        LinguisticVariable exp = new LinguisticVariable("IN", "exp_level", 0, 60);
        LinguisticVariable risk = new LinguisticVariable("OUT", "risk", 0, 100);


        // beginner exp
        FuzzySetPoints bpoint1 = new FuzzySetPoints(0, 0);
        FuzzySetPoints bpoint2 = new FuzzySetPoints(15, 1);
        FuzzySetPoints bpoint3 = new FuzzySetPoints(30, 0);
        List<FuzzySetPoints> bfuzzySetPoints = new ArrayList<>();
        bfuzzySetPoints.add(bpoint1);
        bfuzzySetPoints.add(bpoint2);
        bfuzzySetPoints.add(bpoint3);
        FuzzySet bexp = new FuzzySet("beginner", "TRI", bfuzzySetPoints);

        //intermediate exp
        FuzzySetPoints ipoint1 = new FuzzySetPoints(15, 0);
        FuzzySetPoints ipoint2 = new FuzzySetPoints(30, 1);
        FuzzySetPoints ipoint3 = new FuzzySetPoints(45, 0);
        List<FuzzySetPoints> ifuzzySetPoints = new ArrayList<>();
        ifuzzySetPoints.add(ipoint1);
        ifuzzySetPoints.add(ipoint2);
        ifuzzySetPoints.add(ipoint3);
        FuzzySet iexp = new FuzzySet("intermediate", "TRI", ifuzzySetPoints);


        // expert exp
        FuzzySetPoints epoint1 = new FuzzySetPoints(30, 0);
        FuzzySetPoints epoint2 = new FuzzySetPoints(60, 1);
        FuzzySetPoints epoint3 = new FuzzySetPoints(60, 0);
        List<FuzzySetPoints> efuzzySetPoints = new ArrayList<>();
        efuzzySetPoints.add(epoint1);
        efuzzySetPoints.add(epoint2);
        efuzzySetPoints.add(epoint3);

        FuzzySet eexp = new FuzzySet("expert", "TRI", efuzzySetPoints);

        List<FuzzySet> fuzzySetListExp = new ArrayList<>();
        fuzzySetListExp.add(eexp);
        fuzzySetListExp.add(iexp);
        fuzzySetListExp.add(bexp);

        exp.setFuzzySetList(fuzzySetListExp);


        // setting the variables of project

        FuzzySetPoints vl1 = new FuzzySetPoints(0, 0);
        FuzzySetPoints vl2 = new FuzzySetPoints(0, 1);
        FuzzySetPoints vl3 = new FuzzySetPoints(10, 1);
        FuzzySetPoints vl4 = new FuzzySetPoints(30, 0);
        List<FuzzySetPoints> veryLowPoints = new ArrayList<>();
        veryLowPoints.add(vl1);
        veryLowPoints.add(vl2);
        veryLowPoints.add(vl3);
        veryLowPoints.add(vl4);
        FuzzySet very_low = new FuzzySet("very_low", "TRAP", veryLowPoints);


        FuzzySetPoints l1 = new FuzzySetPoints(10, 0);
        FuzzySetPoints l2 = new FuzzySetPoints(30, 1);
        FuzzySetPoints l3 = new FuzzySetPoints(40, 1);
        FuzzySetPoints l4 = new FuzzySetPoints(60, 0);

        List<FuzzySetPoints> LowPoints = new ArrayList<>();

        LowPoints.add(l1);
        LowPoints.add(l2);
        LowPoints.add(l3);
        LowPoints.add(l4);
        FuzzySet low = new FuzzySet("low", "TRAP", LowPoints);


        FuzzySetPoints m1 = new FuzzySetPoints(40, 0);
        FuzzySetPoints m2 = new FuzzySetPoints(60, 1);
        FuzzySetPoints m3 = new FuzzySetPoints(70, 1);
        FuzzySetPoints m4 = new FuzzySetPoints(90, 0);

        List<FuzzySetPoints> mediumPoints = new ArrayList<>();
        mediumPoints.add(m1);
        mediumPoints.add(m2);
        mediumPoints.add(m3);
        mediumPoints.add(m4);

        FuzzySet medium = new FuzzySet("medium", "TRAP", mediumPoints);


        FuzzySetPoints h1 = new FuzzySetPoints(70, 0);
        FuzzySetPoints h2 = new FuzzySetPoints(90, 1);
        FuzzySetPoints h3 = new FuzzySetPoints(100, 1);
        FuzzySetPoints h4 = new FuzzySetPoints(100, 0);

        List<FuzzySetPoints> highPoints = new ArrayList<>();
        highPoints.add(h1);
        highPoints.add(h2);
        highPoints.add(h3);
        highPoints.add(h4);

        FuzzySet high = new FuzzySet("high", "TRAP", highPoints);


        List<FuzzySet> fuzzySetListProject = new ArrayList<>();
        fuzzySetListProject.add(very_low);
        fuzzySetListProject.add(low);
        fuzzySetListProject.add(medium);
        fuzzySetListProject.add(high);
        project.setFuzzySetList(fuzzySetListProject);


        // risk
        FuzzySetPoints b1 = new FuzzySetPoints(0, 0);
        FuzzySetPoints b2 = new FuzzySetPoints(25, 1);
        FuzzySetPoints b3 = new FuzzySetPoints(50, 0);
        List<FuzzySetPoints> riskSetPoints = new ArrayList<>();
        riskSetPoints.add(b1);
        bfuzzySetPoints.add(b2);
        riskSetPoints.add(b3);
        FuzzySet test = new FuzzySet("low", "TRI", riskSetPoints);

        FuzzySetPoints p2 = new FuzzySetPoints(25, 0);
        FuzzySetPoints p3 = new FuzzySetPoints(50, 1);
        FuzzySetPoints p4 = new FuzzySetPoints(75, 0);
        List<FuzzySetPoints> normal = new ArrayList<>();
        normal.add(p2);
        ifuzzySetPoints.add(p3);
        normal.add(p4);
        FuzzySet test2 = new FuzzySet("normal", "TRI", normal);


        FuzzySetPoints h21 = new FuzzySetPoints(50, 0);
        FuzzySetPoints h22 = new FuzzySetPoints(100, 1);
        FuzzySetPoints h23 = new FuzzySetPoints(100, 0);
        List<FuzzySetPoints> high1 = new ArrayList<>();
        high1.add(h21);
        efuzzySetPoints.add(h22);
        high1.add(h23);

        FuzzySet test3 = new FuzzySet("high", "TRI", high1);

        List<FuzzySet> fuzzySetListRisk = new ArrayList<>();
        fuzzySetListRisk.add(test);
        fuzzySetListRisk.add(test2);
        fuzzySetListRisk.add(test3);

        risk.setFuzzySetList(fuzzySetListRisk);

        Fuzzifier fuzzifier = new Fuzzifier();
        fuzzifier.AddElementToVariablesList(exp);
        fuzzifier.fuzzification(exp, 40.0);

        fuzzifier.AddElementToVariablesList(project);
        fuzzifier.fuzzification(project, 50.0);
        fuzzifier.AddElementToVariablesList(risk);

        fuzzifier.populateVariablesMap();

        Map<String, LinguisticVariable> linguisticVariableMap = fuzzifier.getVariablesNames();
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("proj_funding high or exp_level expert => risk low"));
        rules.add(new Rule("proj_funding medium and exp_level intermediate => risk normal"));
        rules.add(new Rule("proj_funding medium and exp_level beginner => risk normal"));
        rules.add(new Rule("proj_funding low and exp_level beginner => risk high"));
        rules.add(new Rule("proj_funding very_low and not exp_level expert => risk high"));

        Inference inference = new Inference(linguisticVariableMap, rules);
        LinguisticVariable outputlinguisticVariable = inference.solveRules();
//        for (Map.Entry<String, LinguisticVariable> entry : linguisticVariableMap.entrySet()) {
//            System.out.println("Key = " + entry.getKey());
//            LinguisticVariable l = entry.getValue();
//            for (Map.Entry<String, List<Double>> f : l.getFuzzificationAnswer().entrySet()) {
//                System.out.println("fuzzy set" + f.getKey());
//                for (Double num : f.getValue()) {
//                    System.out.println(num);
//                }
//            }
//        }
        for (Map.Entry<String, List<Double>> entry : outputlinguisticVariable.getFuzzificationAnswer().entrySet()){
            System.out.println("Key = " + entry.getKey());
            List<Double>l = entry.getValue();
            for(Double i:l){
                System.out.println(i);
            }
        }

        Defuzzifier defuzzifier = new Defuzzifier(outputlinguisticVariable);
        System.out.println(defuzzifier.PerformDefuzzification());


}
}