public class perceptron_learning_algorithm {
	public static final int TOTAL_DATAPOINTS = 50;
	public static void main(String[] args)
	{
		//defining the training data of 50 points (index:1-50), considering the line equation as a*x1 + b*x2 + c = 0; 
		int trainingArray_x1[] = new int[51];
		int trainingArray_x2[] = new int[51];
		int testArray_x1[] = new int[31];
		int testArray_x2[] = new int[31];
		int dataClass[] = new int[51]; 

		//calling a function to create training,test and training data class
		dataCreation(trainingArray_x1,trainingArray_x2,testArray_x1,testArray_x2,dataClass);

		// defining initial weights and constants for the equation D = w0 + w1*x1 + w2*x2;
		double D;
		double w0 = 1; //weights w0,w1,w2
		double w1 =1;
		double w2 = 1;
		double d = 0.5; // constant d
		int x1 ,x2;
        int iteration = 0;
		int sign = 1;  // sign +1 or -1
		int count = 0; 

		//Assuming that initially all data-points are wrongly classified
		int wrongly_classified = TOTAL_DATAPOINTS;
		while (wrongly_classified>0)
		{
			iteration++;
			wrongly_classified = TOTAL_DATAPOINTS;
			for (int i = 1;i <= TOTAL_DATAPOINTS;i++ ) {

				x1 = trainingArray_x1[i];
				x2 = trainingArray_x2[i];
				D = w0 + (w1 *x1) + (w2 * x2) ;
				// compare sign of D with sign of dataClass[i]
				if((D >0 && dataClass[i]>0) || (D<0 && dataClass[i] <0) ){
					wrongly_classified--;
				}else
				{	
					count++;
					if (D>0 && dataClass[i] < 0 ) { // change weights according to the value of D
						sign = -1;

					}else if (D < 0 && dataClass[i]>0){
						sign = 1;
					}
					
					// updating the weights
					w0 =  w0 + (sign * d );
					w1 =  w1 + (sign * d * x1);
					w2 =  w2 + (sign * d * x2);

				}
			}
			
			// break if all the points are classified correctly in the training data set
			if(wrongly_classified==0){
				break;
			}
			wrongly_classified = TOTAL_DATAPOINTS;	
		}
		System.out.println("w0 is " + w0 +", w1 is " +  w1 +  ", w2 is "+ w2 + ", number of times loop runs  " + iteration +"\n" 
				+ "number of updates " + count);

		// testing the obtained result with the test data 
		System.out.println("Test data point classification ");
		count = 0;
		for ( int k = 1;k<=30;k++) {

			x1 = testArray_x1[k];
			x2 = testArray_x2[k];

			D = w0 + w1*x1 + w2*x2;

			if(D >0){
				System.out.print("1 ");
				count ++ ;
			}else 
				System.out.print("-1 ");

		}
		System.out.println("\n\nOne test dataset had " + count + " points classified and the other had " + (30-count) + "points" );

	} 

	public static void dataCreation(int trainingArray_x1[],int trainingArray_x2[],int testArray_x1[],int testArray_x2[],int dataClass[]){

		// creating training data set for 50 points 
		for(int i =1;i<=25;i++) {
			trainingArray_x1[i] = i;
			trainingArray_x2[i] = i-1;
			dataClass[i] = 1;

		}
		for(int i = 26;i<=50;i++){

			trainingArray_x1[i] = i-1;
			trainingArray_x2[i] = i;
			dataClass[i] = -1;

		}
		// creating test data points such that 15 of them lie in one class and 15 lie in the other 
		for (int i = 1;i<= 30;i++) {
			testArray_x1[i] = i;
			testArray_x2[i] = -i;
		}
		for(int i = 16;i<=30;i++){
			testArray_x1[i] = -i;
			testArray_x2[i] = i;
		}
	}
}
