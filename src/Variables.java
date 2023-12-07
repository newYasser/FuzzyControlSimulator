import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variables
{



    private String type, name;

    private Integer upperRang, lowerRange;

    private List<FuzzySet>fuzzySetList;

    private Map<String,Double>fuzzificationAnswer;


    Variables(String type, String name, Integer upperRang, Integer lowerRange)
    {
        setType(type);
        setName(name);
        setLowerRange(lowerRange);
        setUpperRang(upperRang);
        fuzzySetList = new ArrayList<>();
        fuzzificationAnswer = new HashMap<>();
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

    public Map<String, Double> getFuzzificationAnswer() {
        return fuzzificationAnswer;
    }

    public void setFuzzificationAnswer(Map<String, Double> fuzzificationAnswer) {
        this.fuzzificationAnswer = fuzzificationAnswer;
    }
}
