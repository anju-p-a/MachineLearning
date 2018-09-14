import java.util.Scanner;

public class logistic_Regression {
	public static void main(String args[]) {
		int[] y = {0,1,0,1,0,1,1,1};   // Initializing the given data
		int[] x1 = { 1,2,3,4,5,6,7,8};
		double w0,w1;  
		w0 = w1 = 0;  // Assuming initial value of w's as 0 and constant as 0.009
		double c = 0.009	;
		double oldW0,oldW1 ;
		int count = 0;
		
		while(true) { // run the loop till exit condition is satisfied 
			
			count++;
			double derivativeW0 = partialDerivative(y,w0,w1,x1,0); // find the value of derivative wrt w0
			double derivativeW1 = partialDerivative(y,w0,w1,x1,1); //  find the value of derivative wrt w1
			//noting the values of old w's
			oldW0 = w0;     
            oldW1 = w1; 
            
            //finding the new updated weights
			w0 = w0 + c * derivativeW0;
			w1 = w0 + c * derivativeW1;
			
			//System.out.printf("changed weight w0 by: %.6f , Changed weight w1 by: %.6f,  Actual Weight w0 %.5f, w1 is %.5f\n"
			//	, derivativeW0 , derivativeW1,w0,w1 );
			
			//if there is no change in weights come out of loop
			if(w0-oldW0 == 0 && w1-oldW1 == 0){ break; }
			
		}
		System.out.printf( "The weights obtained are w0 is %.2f and w1 is %.2f and count is %d\n", w0,w1,count*8  );
		System.out.println("Enter an integer value for which you wantto find the probability of failing");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		
		double probablity = findProbability(w0,w1,x);
		System.out.printf("The probability of failing is %.2f\n", probablity);
		
		/***
		for (int i=0 ; i< 8;i++) {
		double Probability = findProbability(w0,w1,x1[i]);	
		System.out.println("prediction:"+ ((Probability>0.6)?1:0));
		} **/
	}


	//function to calculate the value of partial derivatives
	public static double  partialDerivative(int[] y,double w0,double w1,int x[],int whichDerivative) {
		double value = 0;
		int x0 = 1;
		if(whichDerivative == 0 ) 
		{
			for (int i =0;i<8;i++){
				double exponent = w0*x0 +w1*x[i];
				value = value +  x0* (y[i] -  ( Math.exp(exponent)/(1 + Math.exp(exponent)) ));
				// System.out.println("exponent:"+exponent + "  value:"+value);
			}
		}else {
			for (int i =0;i<8;i++){
				double exponent = w0*x0 +w1*x[i];
				value = value +  x[i]* ( y[i] -  ( Math.exp(exponent)/(1 + Math.exp(exponent)) ));
			}
		}
		return value;

	}
	
	//Find the probability of failure 
	
	public static double findProbability(double w0,double w1,int x1)
	{
		double value;
		int x0 = 1;
		double exponent = w0*x0 + w1*x1;
		value = 1 / (1+ Math.exp(-exponent));
		return value;
	}
}




