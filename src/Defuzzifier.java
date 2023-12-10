import java.util.List;

public class Defuzzifier {
    private LinguisticVariable outputLinguisticVariable;

    public Defuzzifier(LinguisticVariable outputLinguisticVariable){
        this.outputLinguisticVariable = outputLinguisticVariable;
    }

    public Double performDefuzzification(){
        Double numerator = 0.0,denominator = 0.0;
        for(FuzzySet fuzzySet: outputLinguisticVariable.getFuzzySetList()){
            Double operation = 0.0,maxValue = 0.0;;
            for(FuzzySetPoint fuzzySetPoints : fuzzySet.getValues()){
                operation += fuzzySetPoints.getX();
            }
            operation /= fuzzySet.getValues().size();
            List<Double> fuzzySetAnswer = outputLinguisticVariable.getFuzzificationAnswer().get(fuzzySet.getName());
            for(Double i : fuzzySetAnswer){
                maxValue = Double.max(maxValue,i);
            }
            denominator += maxValue;
            operation *= maxValue;
            numerator += operation;
        }
        return numerator/denominator;
    }

}
