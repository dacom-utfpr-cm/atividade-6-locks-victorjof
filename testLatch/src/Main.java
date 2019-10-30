public class Main {
    public static void main(String[] args) {
        double [] a = new double[]{5,0,5};
        Stencil stencil = new Stencil(a,1000000);
        stencil.iterate();
    }
}