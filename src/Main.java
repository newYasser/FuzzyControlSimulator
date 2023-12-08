import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        LinguisticVariable project = new LinguisticVariable("IN","Proj_fund",0,100);
        LinguisticVariable exp = new LinguisticVariable("IN","exp_level",0,60);

        // beginner exp
        FuzzySetPoints bpoint1 = new FuzzySetPoints(0,0);
        FuzzySetPoints bpoint2 = new FuzzySetPoints(15,1);
        FuzzySetPoints bpoint3 = new FuzzySetPoints(30,0);
        List<FuzzySetPoints>bfuzzySetPoints = new ArrayList<>();
        bfuzzySetPoints.add(bpoint1);bfuzzySetPoints.add(bpoint2);
        bfuzzySetPoints.add(bpoint3);
        FuzzySet bexp = new FuzzySet("beginner","TRI",bfuzzySetPoints);

        //intermediate exp
        FuzzySetPoints ipoint1 = new FuzzySetPoints(15,0);
        FuzzySetPoints ipoint2 = new FuzzySetPoints(30,1);
        FuzzySetPoints ipoint3 = new FuzzySetPoints(45,0);
        List<FuzzySetPoints>ifuzzySetPoints = new ArrayList<>();
        ifuzzySetPoints.add(ipoint1);ifuzzySetPoints.add(ipoint2);
        ifuzzySetPoints.add(ipoint3);
        FuzzySet iexp = new FuzzySet("intermediate","TRI",ifuzzySetPoints);


        // expert exp
        FuzzySetPoints epoint1 = new FuzzySetPoints(30,0);
        FuzzySetPoints epoint2 = new FuzzySetPoints(60,1);
        FuzzySetPoints epoint3 = new FuzzySetPoints(60,0);
        List<FuzzySetPoints>efuzzySetPoints = new ArrayList<>();
        efuzzySetPoints.add(epoint1);efuzzySetPoints.add(epoint2);
        efuzzySetPoints.add(epoint3);

        FuzzySet eexp = new FuzzySet("expert","TRI",efuzzySetPoints);

        List<FuzzySet>fuzzySetListExp = new ArrayList<>();
        fuzzySetListExp.add(eexp);fuzzySetListExp.add(iexp);
        fuzzySetListExp.add(bexp);

        exp.setFuzzySetList(fuzzySetListExp);


        // setting the variables of project

        FuzzySetPoints vl1 = new FuzzySetPoints(0,0);
        FuzzySetPoints vl2 = new FuzzySetPoints(0,1);
        FuzzySetPoints vl3 = new FuzzySetPoints(10,1);
        FuzzySetPoints vl4 = new FuzzySetPoints(30,0);
        List<FuzzySetPoints>veryLowPoints = new ArrayList<>();
        veryLowPoints.add(vl1);veryLowPoints.add(vl2);
        veryLowPoints.add(vl3);veryLowPoints.add(vl4);
        FuzzySet very_low = new FuzzySet("very_low","TRAP",veryLowPoints);



        FuzzySetPoints l1 = new FuzzySetPoints(10,0);
        FuzzySetPoints l2 = new FuzzySetPoints(30,1);
        FuzzySetPoints l3 = new FuzzySetPoints(40,1);
        FuzzySetPoints l4 = new FuzzySetPoints(60,0);

        List<FuzzySetPoints>LowPoints = new ArrayList<>();

        LowPoints.add(l1);LowPoints.add(l2);
        LowPoints.add(l3);LowPoints.add(l4);
        FuzzySet low = new FuzzySet("low","TRAP",LowPoints);


        FuzzySetPoints m1 = new FuzzySetPoints(40,0);
        FuzzySetPoints m2 = new FuzzySetPoints(60,1);
        FuzzySetPoints m3 = new FuzzySetPoints(70,1);
        FuzzySetPoints m4 = new FuzzySetPoints(90,0);

        List<FuzzySetPoints>mediumPoints = new ArrayList<>();
        mediumPoints.add(m1);mediumPoints.add(m2);
        mediumPoints.add(m3);mediumPoints.add(m4);

        FuzzySet medium = new FuzzySet("medium","TRAP",mediumPoints);



        FuzzySetPoints h1 = new FuzzySetPoints(70,0);
        FuzzySetPoints h2 = new FuzzySetPoints(90,1);
        FuzzySetPoints h3 = new FuzzySetPoints(100,1);
        FuzzySetPoints h4 = new FuzzySetPoints(100,0);

        List<FuzzySetPoints>highPoints = new ArrayList<>();
        highPoints.add(h1);highPoints.add(h2);
        highPoints.add(h3);highPoints.add(h4);

        FuzzySet high = new FuzzySet("high","TRAP",highPoints);


        List<FuzzySet>fuzzySetListProject = new ArrayList<>();
        fuzzySetListProject.add(very_low);fuzzySetListProject.add(low);
        fuzzySetListProject.add(medium);fuzzySetListProject.add(high);
        project.setFuzzySetList(fuzzySetListProject);




        Fuzzifier fuzzifier = new Fuzzifier();
        fuzzifier.AddElementToVariablesList(exp);
        fuzzifier.fuzzification(exp,40.0);

        fuzzifier.AddElementToVariablesList(project);
        fuzzifier.fuzzification(project,50.0);



    }
}