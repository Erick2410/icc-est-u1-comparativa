public class Results {

    private String functionName;
    private long duracionNs;
    private String sample;


    public Results(String functionName, long duracionNs, String sample2) {
        this.functionName = functionName;
        this.duracionNs = duracionNs;
        this.sample = sample2;
    }


    public String getFunctionName() {
        return functionName;
    }


    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }


    public long getDuracionNs() {
        return duracionNs;
    }


    public void setDuracionNs(long duracionNs) {
        this.duracionNs = duracionNs;
    }


    public String getSample() {
        return sample;
    }


    public void setSample(String sample) {
        this.sample = sample;
    }




    @Override
    public String toString() {

        long segundos = duracionNs/1000000000;
        return "Results [functionName=" + functionName + ", duracion=" + duracionNs + "ns, " + segundos + "s" + ", sample=" + sample + "]";
    }

    


    
    
}
