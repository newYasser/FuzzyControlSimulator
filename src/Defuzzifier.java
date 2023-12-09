import java.rmi.dgc.Lease;
import java.util.List;
import java.util.Map;

public class Defuzzifier {
    private LinguisticVariable outputLinguisticVariable;

    public Defuzzifier(LinguisticVariable outputLinguisticVariable){
        this.outputLinguisticVariable = outputLinguisticVariable;
    }

    public Double PerformDefuzzification(){
        Double numerator = 0.0,denominator = 0.0,sum = 0.0;
        for(FuzzySet fuzzySet: outputLinguisticVariable.getFuzzySetList()){
            Double operation = 0.0;
            for(FuzzySetPoints fuzzySetPoints : fuzzySet.getValues()){
                operation += fuzzySetPoints.getX();
            }
            operation /= fuzzySet.getValues().size();
            List<Double> fuzzySetAnswer = outputLinguisticVariable.getFuzzificationAnswer().get(fuzzySet.getName());
            for(Double i : fuzzySetAnswer){
                sum += i;
            }
            sum /= fuzzySetAnswer.size();
            denominator += sum;
            operation *= sum;
            numerator += operation;
        }
        return numerator/denominator;
    }

}
