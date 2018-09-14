/** Anju Puthenveetil
 * Project4 _ Stochastic Gradient
 * 11-09-2017
 */

import java.util.Random;
import java.util.Scanner;
public class Stochastic_Gradient {

		public static void main(String args[]) {
			int[] y = {0,1,0,1,0,1,1,1};   // Initializing the given data
			int[] x1 = { 1,2,3,4,5,6,7,8};
			double w0,w1; 
			w0 = w1 = 0;  // Assuming initial value of w's as 0 and constant as 0.009
			double c = 0.0009	;
			double oldW0,oldW1 ;
			double error0,error1;
			double averageError0 = 0.0;
			double averageError1 = 0.0;
			double previousError0 = 0.0;
			double previousError1 = 0.0;
			int thresholdCount = 30;
			int count = 1;
			
			int j=1;
			while(true) { // run the loop till exit condition is satisfied 
				Random rand = new Random();
				int a = rand.nextInt(8);
				
				double derivativeW0 = partialDerivative(y,w0,w1,x1,0, a); // find the value of derivative wrt w0
				double derivativeW1 = partialDerivative(y,w0,w1,x1,1,a); //  find the value of derivative wrt w1
				//noting the values of old w's
				oldW0 = w0;     
	            oldW1 = w1; 
	            
	            //finding the new updated weights
				w0 = w0 + c * derivativeW0;
				w1 = w0 + c * derivativeW1;
				
				//Calculating the error and condition to break /*Stops when there is no change in the average error 
				error0 = w0-oldW0;
				error1 = w0-oldW1;
				previousError1 = averageError1;
				previousError0 = averageError0; 
				//finding the average error
			    averageError0 = (averageError0*(count-1) + Math.abs((error0)))/count;
			    averageError1 = (averageError1*(count-1) + Math.abs((error1)))/count;
			    count++;
					//check if the difference in error is below 0.000001 for certain number of  successive iterations
					if(Math.abs((averageError1 - previousError1)) <= 0.000001 
							&& Math.abs((averageError0 - previousError0)) <= 0.000001){
								j++;
							}else {
								j = 1;					
							}
							 
						
					if(j==thresholdCount) { break;}
	
				
			}
			//printing the results and probability
			System.out.printf( "The weights obtained are w0 is %.2f and w1 is %.2f at count %d\n", w0,w1,count  );
			System.out.println("Enter an integer value for which you want to find the probability of failing");
			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			
			double probablity = findProbability(w0,w1,x);
			System.out.printf("The probability of failing is %.2f\n", probablity);
			

		}


		//function to calculate the value of partial derivatives
		public static double  partialDerivative(int[] y,double w0,double w1,int x[],int whichDerivative,int index) {
			double value = 0;
			int x0 = 1;
			
			if(whichDerivative == 0 ) 
			{
				
					double exponent = w0*x0 +w1*x[index];
					value = value +  x0* (y[index] -  ( Math.exp(exponent)/(1 + Math.exp(exponent)) ));
							
			}else {
	
					double exponent = w0*x0 +w1*x[index];
					value = value +  x[index]* ( y[index] -  ( Math.exp(exponent)/(1 + Math.exp(exponent)) ));			
				
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






