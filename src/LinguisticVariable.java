import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinguisticVariable
{



    private String type, name;

    private Integer upperRang, lowerRange;

    private List<FuzzySet>fuzzySetList;

    private Map<String,List<Double>>fuzzificationAnswers;


    LinguisticVariable(String type, String name, Integer upperRang, Integer lowerRange)
    {
        setType(type);
        setName(name);
        setLowerRange(lowerRange);
        setUpperRang(upperRang);
        fuzzySetList = new ArrayList<>();
        fuzzificationAnswers = new HashMap<>();
    }
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getLowerRange()
    {
        return lowerRange;
    }

    public void setLowerRange(Integer lowerRange)
    {
        this.lowerRange = lowerRange;
    }



    public Integer getUpperRang()
    {
        return upperRang;
    }

    public void setUpperRang(Integer upperRang)
    {
        this.upperRang = upperRang;
    }


    public void AddElementToFuzzyList(FuzzySet fuzzySet)
    {
        fuzzySetList.add(fuzzySet);
    }
    public List<FuzzySet> getFuzzySetList()
    {
        return fuzzySetList;
    }

    public void setFuzzySetList(List<FuzzySet> fuzzySetList) {
        this.fuzzySetList = fuzzySetList;
    }

    public Map<String, List<Double>> getFuzzificationAnswer() {
        return fuzzificationAnswers;
    }

    public void setFuzzificationAnswer(Map<String, List<Double>> fuzzificationAnswer) {
        this.fuzzificationAnswers = fuzzificationAnswer;
    }
    public int getFuzzySetIdxByName(String fuzzySetName){
        for(int i = 0; i < fuzzySetList.size();++i){
            if(fuzzySetList.get(i).getName().equals(fuzzySetName)){
                return i;
            }
        }
        return -1; // doesn't exists
    }

    public FuzzySet getFuzzySetByName(String fuzzySetName){
        for(FuzzySet fuzzySet:fuzzySetList){
            if(fuzzySet.getName().equals(fuzzySetName))
                return fuzzySet;
        }
        return null;
    }

}
