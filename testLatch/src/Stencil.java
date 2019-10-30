public class Stencil {

    private double[] array;
    private int num_iterations;
    private double[] aux_array;

    Stencil(double []array,int num_iterations){
        this.array = array;
        this.num_iterations = num_iterations;
    }

    private void transform(int idx){
        this.array[idx] = (aux_array[idx-1]+aux_array[idx+1])/2;
    }


    public void iterate(){
        for(int i=0; i<num_iterations;i++){
            this.aux_array = this.array.clone();
            for (int j = 1; j <this.array.length-1 ; j++) {
                transform(j);
            }
        }
        display();
    }


    private void display(){
        for (int i = 0; i <array.length ; i++) {
            System.out.println(array[i]);
        }
    }
}
