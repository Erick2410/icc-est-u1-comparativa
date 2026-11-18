public class Results {

    private String functionName;
    private long duracion;
    private String sample;


    public Results(String functionName, long duracion, String sample) {
        this.functionName = functionName;
        this.duracion = duracion;
        this.sample = sample;
    }


    public String getFunctionName() {
        return functionName;
    }


    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }


    public long getDuracion() {
        return duracion;
    }


    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }


    public String getSample() {
        return sample;
    }


    public void setSample(String sample) {
        this.sample = sample;
    }




    @Override
    public String toString() {

        long segundos = duracion/1000000000;
        return "Results [functionName=" + functionName + ", duracion=" + duracion + "ns, " + segundos + "s" + ", sample=" + sample + "]";
    }

    


    
    
}
