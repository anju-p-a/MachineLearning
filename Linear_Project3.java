import java.util.Random;

public class Linear_Project3 {
	
	public static void main(String[] args) {
	int [] y = new int[12];
	double [] y1 = new double[12];
	//input generated randomly 
	int[] x = { 6,3,10,8,5,9,-1,4,3,7,2,1};
	double sum = 0;
	//found weight by derivative method and calculating MSE
	double w0 = -2.11;
	double w1 = 9.48;
	for(int i=0; i<x.length; i++) {
	
		y[i] = x[i]*x[i]+10;
		y1[i] = w0 + w1*x[i];		
		double squareDiff = (y1[i]-y[i])*(y1[i]-y[i]);
		sum = sum + squareDiff;
		
		
	}
	System.out.println("InSampleError in linear regression " + sum/12 );
	
	}
	
	
	
	
	
	
	
	

//find summation of x and y
public static int sum(int[] array){
	int sum = 0;
	for(int i = 0;i<12;i++){
		sum = sum+array[i];
	}
	return sum;
}

//find summation of x*y and x*x
public static int productSum(int[] array1,int[] array2){
	int sum = 0;
	for(int i = 0;i<12;i++){
		sum = sum+array1[i]*array2[i];
	}
	return sum;
}

public static int returnX(int[] array1,int[] array2){
	
return  0;	
}
}