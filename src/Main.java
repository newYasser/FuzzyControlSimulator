import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int op = 0;
        while(op != 2) {
            System.out.println("Fuzzy Logic Toolbox");
            System.out.println("===================");
            System.out.println("1- Create a new fuzzy system\n" +
                    "2- Quit");

            Scanner input = new Scanner(System.in);
            op = input.nextInt();
            if (op == 1) {
                System.out.println("Enter the system’s name and a brief description:");
                System.out.print("-------------------------------------------------\n");
                input.nextLine();
                String systemNameAndDescription = input.nextLine();
                input.nextLine();
            }
            if(op == 2){
                continue;
            }
            String menuOption = " ";
            List<LinguisticVariable> linguisticVariables = new ArrayList<>();
            List<Rule> rules = new ArrayList<>();
            while (!menuOption.equalsIgnoreCase("Close")) {
                System.out.println("Main Menu:");
                System.out.println("==========");
                System.out.println("1- Add variables.\n" +
                        "2- Add fuzzy sets to an existing variable.\n" +
                        "3- Add rules.\n" +
                        "4- Run the simulation on crisp values.\n");
                menuOption = input.nextLine();
                if (menuOption.equalsIgnoreCase("")) {
                    menuOption = input.nextLine();
                }
                switch (menuOption) {
                    case "1":
                        System.out.println("Enter the variable’s name, type (IN/OUT) and range ([lower, upper]):\n" +
                                "(Press x to finish)");
                        String variableName, variableType;
                        Integer upperBound, lowerBound;
                        variableName = input.next();
                        while (!variableName.equalsIgnoreCase("x")) {
                            variableType = input.next();
                            upperBound = input.nextInt();
                            lowerBound = input.nextInt();
                            LinguisticVariable linguisticVariable = new LinguisticVariable(variableType, variableName, upperBound, lowerBound);
                            linguisticVariables.add(linguisticVariable);
                            System.out.println("The variable added");
                            variableName = input.next();
                        }

                        break;
                    case "2":
                        if (linguisticVariables.isEmpty()) {
                            System.out.println("Not enough variables add more");
                            break;
                        }
                        for (LinguisticVariable variable : linguisticVariables) {
                            System.out.println("Enter the fuzzy set for the " + variable.getName());
                            System.out.println("Press x to finish");
                            String fuzzySetName = input.next();
                            String fuzzySetType;
                            List<FuzzySet> fuzzySets = new ArrayList<>();
                            while (!fuzzySetName.equalsIgnoreCase("x")) {
                                List<FuzzySetPoint> fuzzySetPoints = new ArrayList<>();
                                fuzzySetType = input.next();
                                int iterations = 0;
                                if (fuzzySetType.equalsIgnoreCase("TRI")) {
                                    iterations = 3;
                                } else if (fuzzySetType.equalsIgnoreCase("TRAP")) {
                                    iterations = 4;
                                }
                                for (int i = 0; i < iterations; ++i) {
                                    Integer x = input.nextInt();
                                    FuzzySetPoint point = new FuzzySetPoint();
                                    point.setX(x);
                                    point.setY(0);
                                    fuzzySetPoints.add(point);
                                }
                                if(fuzzySetType.equalsIgnoreCase("TRI")){
                                    fuzzySetPoints.get(1).setY(1);
                                }
                                if(fuzzySetType.equalsIgnoreCase("TRAP")){
                                    fuzzySetPoints.get(1).setY(1);
                                    fuzzySetPoints.get(2).setY(1);
                                }

                                fuzzySets.add(new FuzzySet(fuzzySetName, fuzzySetType, fuzzySetPoints));
                                System.out.println("Fuzzy set added");
                                fuzzySetName = input.next();
                            }
                            variable.setFuzzySetList(fuzzySets);
                        }
                        break;

                    case "3":
                        System.out.println("Enter the rules in this format: (Press x to finish)\n" +
                                " IN_variable set operator IN_variable set => OUT_variable set");
                        String rulestatement = input.nextLine();
                        while (!rulestatement.equalsIgnoreCase("x")) {
                            Rule rule = new Rule(rulestatement);
                            rules.add(rule);
                            rulestatement = input.nextLine();
                        }
                        break;

                    case "4":
                        Fuzzifier fuzzifier = new Fuzzifier();
                        System.out.println("Enter the crisp values: ");
                        System.out.println("--------------------------");
                        String outputName = "";
                        for (LinguisticVariable linguisticVariable : linguisticVariables) {
                            if (linguisticVariable.getType().equalsIgnoreCase("OUT")) {
                                fuzzifier.addElementToVariablesList(linguisticVariable);
                                continue;
                            }
                            System.out.print(linguisticVariable.getName() + ": ");
                            Double crispValue = input.nextDouble();
                            fuzzifier.fuzzification(linguisticVariable, crispValue);
                        }
                        fuzzifier.populateVariablesMap();
                        Map<String, LinguisticVariable> fuzzifiedVariables = fuzzifier.getVariablesNames();

                        Inference inference = new Inference(fuzzifiedVariables, rules);
                        LinguisticVariable outputVariable = inference.solveRules();

                        Defuzzifier defuzzifier = new Defuzzifier(outputVariable);
                        Double result = defuzzifier.performDefuzzification();
                        fuzzifier.fuzzification(outputVariable,result);
                        String prediction= " ";
                        Double max = 0.0;
                        for(Map.Entry<String,List<Double>> answers:outputVariable.getFuzzificationAnswer().entrySet()){
                            for(Double answer:answers.getValue()){
                                if(answer > max){
                                    max = answer;
                                    prediction = answers.getKey();
                                }
                            }
                        }
                        System.out.println("predicted " +  outputVariable.getName() +" is " + prediction);
                        System.out.println(result);
                        break;

                }
            }
        }
    }
    }
