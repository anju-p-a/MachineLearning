import java.util.Arrays;

public class lasso {
	static double w0[] ={0,0,0,0};
	static double w1[] = {0,0,0,0};
	public static void main(String[] args) {
		//initialising the inputs
		double[] x = { 6,3,10,8,5,9,-1,4,3,7,2,1};
		double [] y = new double[12];
		double[] y1 = new double[12];
		double [] y1_set = new double[8];
		double [] y1_testSet = new double[4];
		double [] y_testSet1= new double[4];
		double [] y_testSet2= new double[4];
		double [] y_testSet3= new double[4];
		double[] y_set1 = new double[8];
		double[] y_set2 = new double[8];
		double[] y_set3 = new double[8];
		double testError1,testError2,testError3;
		double inSampleError1,inSampleError2,inSampleError3;
		int count = 0,j=0,k=0;
		for(int i=0; i<x.length; i++) {		
			y[i] = x[i]*x[i]+10;

		}

		//the training  sets for 3 fold  cross validation
		double x_set1[] = {6,3,10,8,5,9,-1,4};
		double x_set2[]	= {5,9,-1,4,3,7,2,1};
		double x_set3[] = {3,7,2,1,6,3,10,8};


		//the test sets
		double x_testSet1[] = { 3,7,2,1};
		double x_testSet2[] = {6,3,10,8};
		double x_testSet3[]= {5,9,-1,4};

		j=0;
		for(int i = 0;i< 8;i++){
			y_set1[i] = x_set1[i]*x_set1[i] +10;
			y_set2[i] = x_set2[i]*x_set2[i] +10;
			y_set3[i] = x_set3[i]*x_set3[i] +10;

			if(j<4){
				y_testSet1[j] =  x_testSet1[j] * x_testSet1[j]+ 10;
				y_testSet2[j] =  x_testSet1[j] * x_testSet1[j]+ 10;
				y_testSet3[j] =  x_testSet1[j] * x_testSet1[j]+ 10;
				j++;
			}
		}

		double[] lambda = { 0.1,1,10,100};

		for(j = 0 ;j < lambda.length;j++){
			//For each lambda doing it for all sets
			//set1
			y1_set = predictedOutput(x_set1,w0[j],w1[j]);
			double rho = findRho(x_set1,y_set1,y1_set,lambda[j],w1[j]);
			w0[j] = rho;
			for(int i = 0;i<10;i++){
				findCostFunction(x_set1,y1_set,lambda[j],w0[j],w1[j]);
				rho = findRho(x_set1,y_set1,y1_set,lambda[j],w1[j]);
				w1[j] = updateWeights(rho,lambda[j],x_set1);

			}
			System.out.println("Weights for set 1 and lambda as " + lambda[j]+"are " +w0[j] + "..." + w1[j]);
			y1_testSet = predictedOutput(x_testSet1,w0[j],w1[j]);
			testError1 = calculateTestError(y_testSet1,y1_testSet);
			inSampleError1 = calculateTestError(y_set1,y1_set);

			//set2
			w0[j] = 0;w1[j] = 0;
			y1_set = predictedOutput(x_set2,w0[j],w1[j]);
			rho = findRho(x_set2,y_set2,y1_set,lambda[j],w1[j]);
			w0[j] = rho;
			for(int i = 0;i<10;i++){

				findCostFunction(x_set2,y1_set,lambda[j],w0[j],w1[j]);
				rho = findRho(x_set2,y_set2,y1_set,lambda[j],w1[j]);
				w1[j] = updateWeights(rho,lambda[j],x_set2);		

			}

			System.out.println("Weights for set 2 and lambda as " + lambda[j]+ "are " +w0[j] + "..." + w1[j]);

			y1_testSet = predictedOutput(x_testSet2,w0[j],w1[j]);
			testError2 = calculateTestError(y_testSet2,y1_testSet);
			inSampleError2 = calculateTestError(y_set2,y1_set);

			//set3
			w0[j] = 0;w1[j] = 0;
			y1_set = predictedOutput(x_set3,w0[j],w1[j]);
			rho = findRho(x_set3,y_set3,y1_set,lambda[j],w1[j]);
			w0[j] = rho;
			for(int i = 0;i<10;i++){

				findCostFunction(x_set3,y1_set,lambda[j],w0[j],w1[j]);
				rho = findRho(x_set3,y_set3,y1_set,lambda[j],w1[j]);
				w1[j] = updateWeights(rho,lambda[j],x_set3);		

			}
			System.out.println("Weights for set 3 and lambda as " + lambda[j]+ "are " +w0[j] + "..." + w1[j]);
			y1_testSet = predictedOutput(x_testSet3,w0[j],w1[j]);
			testError3 = calculateTestError(y_testSet3,y1_testSet);
			inSampleError3 = calculateTestError(y_set3,y1_set);
			System.out.println("Cross Validation Error is " + calculateCV(testError1,testError2,testError3));
			System.out.println("InSampleError is    " + calculateInSample(inSampleError1,inSampleError2,inSampleError3));


		}
		double w0 = 0; double w1 = 0;
		//final calculation for lambda as 10
		y1 = predictedOutput(x,w0,w1);
		double rho = findRho(x,y,y1,lambda[2],w1);
		w0= rho;
		for(int i = 0;i<10;i++){

			findCostFunction(x,y1,lambda[2],w0,w1);
			rho = findRho(x,y,y1,lambda[2],w1);
			w1 = updateWeights(rho,lambda[2],x);		

		}
		System.out.println("Weights for final set  and lambda as " + lambda[2]+ "are " +w0+ "..." + w1);

		double finalInSampleError = calculateTestError(y,y1);
		System.out.println("InSampleError is    " + finalInSampleError);


	}


	public static double calculateInSample(double s1,double s2,double s3 ){
		double s = s1+s2+s3/3; 
		return s;
	}
	public static double calculateCV(double t1,double t2,double t3){
		return t1+t2+t3/3;
	}

	public static double calculateTestError(double[] y,double[] y1){
		double t = 0;
		t = findSumOfSquareDiff(y,y1)/y.length;
		return t;

	}
	public static double findRho(double [] x,double[] y,double[] y1,double lambda,double w1){
		double result = 0.0;
		result = findProductSum(x,y) - findProductSum(x,y1)+ (w1*findProductSum(x,x)); 

		return result;

	}





	public static double findSumOfSquareDiff(double[] y,double[] predictedY){
		double sum = 0;
		for(int i = 0;i<y.length;i++){
			sum = sum + (y[i] - predictedY[i])*(y[i] - predictedY[i]);
		}
		return sum;
	}

	//function to update the weights 
	public static double updateWeights(double rho,double lambda,double[] x){
		double lambdaBy2  = lambda/2;
		if(rho < -lambdaBy2){
			return (rho+(lambdaBy2))/findProductSum(x,x);
		}
		else if(rho >= -lambdaBy2 && rho <= lambdaBy2 ){
			return 0;
		}
		else{//rho>lambdaBy2
			return (rho-lambdaBy2)/findProductSum(x,x);
		}
	}

	//calculating y for the given weights 
	public static double[] predictedOutput(double[] x, double w0,double w1){
		double[] predictedy = new double[x.length];
		for(int i = 0;i<x.length;i++){
			predictedy[i] = w0+ w1*x[i];


		}
		return predictedy;
	}
	//finding the cost 
	public static double findCostFunction(double[] x,double[] y,double lambda,double w0,double w1){
		double[] predictedY = predictedOutput(x,w0, w1);

		double cost = 0;

		cost = cost + findSumOfSquareDiff(y,predictedY) +  (Math.abs(w0)+Math.abs(w1))*lambda;
		return cost;

	}

	//finding the sum of the products
	public static double findProductSum(double[] x , double[] y){
		double sum = 0.0;
		for(int i = 0;i< x.length;i++){

			sum = sum + x[i]*y[i];
		}
		return sum;
	}

	//find sum
	public static double findSum(double[] x){
		double sum = 0.0;
		for(int i = 0;i< x.length;i++){
			sum = sum + x[i];
		}
		return sum;
	}



}
