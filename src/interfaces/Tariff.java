package interfaces;

public interface Tariff {
    public int getId();
    public void setId(int id);
    public String getName();
    public double getPrice();
    public String getInsertDerivedQuery();
    public String getFindByNameQuery();
    public String getFindByIdQuery();
    public String getInsertBasicQuery();
    public Object[] getInsertBasicParams();
    public Object[] getInsertDerivedParams();

}
