package BeyondQuery;

public class InteractiveRecommendationTest {

	public static void main(String[] args) {
		
		System.out.println("Start!");
		
		String matrixFileName = "BeyondQuery/testSample_20_10_0.2.txt";
		//String matrixFileName = "COA_OpenData.txt";
		int numOfQuestion = 10;
		double questGamma = 0.5;
		double userGamma = 0.5;
		
		
		int[] currTagCountOfUser = new int[10];
		int[] idxOfItem = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int[] answer = {1, 1, 0, 1, 0, 1, 1, 0, 1, 0};
		
		
		
		InteractiveRecommendation ir;
		ir = new InteractiveRecommendation(matrixFileName,
                                           numOfQuestion,
                                           questGamma,
                                            userGamma,
				                             null);
		ir.autoInteractiveSearchTargetItem(currTagCountOfUser, idxOfItem, answer);

		int[] reuslts = ir.getIndexOfPossibleItems();
		
		System.out.println("Done!");
		
	}
	
}
