public class FuzzySetPoint
{
    private Integer x;
    private Integer y;

    FuzzySetPoint()
    {
        setX(0);
        setY(0);
    }
    FuzzySetPoint(Integer x, Integer y)
    {
        setX(x);
        setY(y);
    }
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }



}
