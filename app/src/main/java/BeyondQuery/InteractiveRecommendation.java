package BeyondQuery;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InteractiveRecommendation {
	
	private String matrixFileName = "";
	private int numOfQuestion;
	private double questGamma;
	private double userGamma;
	private Context context;
	
	private int numOfItem;
	private int numOfTag;
	private int numOfOne;
	
	private int[][] itemToTagMatrix;
	private String[] itemNames;
	private String[] tagNames;
	
	private ArrayList<Integer> tagHasNotBeenAsked;
	private double[] weightOfItems;
	private Random rand;
	
	public InteractiveRecommendation(String matrixFileName,
                                     int numOfQuestion,
                                     double questGamma,
                                     double userGamma,
									 Context context) {
		
		this.matrixFileName = matrixFileName;
		this.numOfQuestion = numOfQuestion;
		this.questGamma = questGamma;
		this.userGamma = userGamma;
		this.context = context;

		rand = new Random();
		
		readDataMatrixFromFile();
	}
	

	public int autoInteractiveSearchTargetItem_Greedy(int idxOfTargetItem, double userErrorRate) {
		
		Random rand = new Random();
		
		initTagHasNotBeenAsked();
		initWeightOfItem();
		
		for(int k = 0; k < numOfQuestion; k++) {
			int idxOfSelTag = generateNextQuestion_Greedy();
			if(idxOfSelTag == -1) {
				break;
			}
			
			int answer = -1;
			if(rand.nextDouble() > userErrorRate) {
				answer = itemToTagMatrix[idxOfTargetItem][idxOfSelTag];
			} else {
				answer = Math.abs(itemToTagMatrix[idxOfTargetItem][idxOfSelTag] - 1);
			}
			
			if(answer == 1) {
				decreaseWeightOfItemWithoutSelTag(idxOfSelTag, questGamma);
			} else if(answer == 0) {
				decreaseWeightOfItemWithSelTag(idxOfSelTag, questGamma);
			}
			
			removeSelTag(idxOfSelTag);
			
			if(idxOfTargetItem == getIndexOfMostPossibleItem()) {
				return k;
			}
		}
		
		return -1;
	}
	
	public int autoInteractiveSearchTargetItem_IHS_MMAS(int idxOfTargetItem, double userErrorRate) {
		
		Random rand = new Random();
		
		initTagHasNotBeenAsked();
		initWeightOfItem();
		
		for(int k = 0; k < numOfQuestion - 2; k++) {
			int idxOfSelTag = generateNextQuestion_IHS_MMAS();
			if(idxOfSelTag == -1) {
				break;
			}
			
			int answer = -1;
			if(rand.nextDouble() > userErrorRate) {
				answer = itemToTagMatrix[idxOfTargetItem][idxOfSelTag];
			} else {
				answer = Math.abs(itemToTagMatrix[idxOfTargetItem][idxOfSelTag] - 1);
			}
			
			if(answer == 1) {
				decreaseWeightOfItemWithoutSelTag(idxOfSelTag, questGamma);
			} else if(answer == 0) {
				decreaseWeightOfItemWithSelTag(idxOfSelTag, questGamma);
			}
			
			removeSelTag(idxOfSelTag);
			
			if(idxOfTargetItem == getIndexOfMostPossibleItem()) {
				return k;
			}
		}
		
		return -1;
	}
	
	public int autoInteractiveSearchTargetItem_SHS_MMAS(int idxOfTargetItem, double userErrorRate) {
		
		Random rand = new Random();
		
		initTagHasNotBeenAsked();
		initWeightOfItem();
		
		for(int k = 0; k < numOfQuestion/2 -1; k++) {
			int[] idxOfSelTag = generateNextQuestion_SHS_MMAS();
			if(idxOfSelTag == null) {
				break;
			}
			
			int answer = -1;
			int idxOfSelTagOne = idxOfSelTag[0];
			if(rand.nextDouble() > userErrorRate) {
				answer = itemToTagMatrix[idxOfTargetItem][idxOfSelTagOne];
			} else {
				answer = Math.abs(itemToTagMatrix[idxOfTargetItem][idxOfSelTagOne] - 1);
			}
			
			if(answer == 1) {
				decreaseWeightOfItemWithoutSelTag(idxOfSelTagOne, questGamma);
			} else if(answer == 0) {
				decreaseWeightOfItemWithSelTag(idxOfSelTagOne, questGamma);
			}
			
			removeSelTag(idxOfSelTagOne);
			
			int idxOfSelTagTwo = idxOfSelTag[1];
			if(rand.nextDouble() > userErrorRate) {
				answer = itemToTagMatrix[idxOfTargetItem][idxOfSelTagTwo];
			} else {
				answer = Math.abs(itemToTagMatrix[idxOfTargetItem][idxOfSelTagTwo] - 1);
			}
			
			if(answer == 1) {
				decreaseWeightOfItemWithoutSelTag(idxOfSelTagTwo, questGamma);
			} else if(answer == 0) {
				decreaseWeightOfItemWithSelTag(idxOfSelTagTwo, questGamma);
			}
			
			removeSelTag(idxOfSelTagTwo);
			
			if(idxOfTargetItem == getIndexOfMostPossibleItem()) {
				return k*2;
			}
		}
		
		return -1;
	}
	
	public void autoInteractiveSearchTargetItem(int[] currTagCountOfUser, int[] idxOfItem, int[] answer) {
		
		initTagHasNotBeenAsked();
		initWeightOfItem();

		currTagCountOfUser = getTagCountOfUser(currTagCountOfUser, idxOfItem, answer);
		double[] tagPreferenceOfUser = getTagPreferenceOfUser(currTagCountOfUser);

		for(int k = 0; k < numOfQuestion; k++) {
			int idxOfSelTag = generateNextQuestion_Greedy();
			if(idxOfSelTag == -1) {
				break;
			}
			
			if(tagPreferenceOfUser[idxOfSelTag] > rand.nextDouble()) {
				decreaseWeightOfItemWithoutSelTag(idxOfSelTag, questGamma);
			} else if(tagPreferenceOfUser[idxOfSelTag] < -rand.nextDouble()) {
				decreaseWeightOfItemWithSelTag(idxOfSelTag, questGamma);
			}
			
			removeSelTag(idxOfSelTag);
		}
	}
	
	public void manualInteractiveSearchTargetItem(int[] currTagCountOfUser, int[] idxOfItem, int[] answer) {
		
		Scanner input = new Scanner(System.in);
		
		initTagHasNotBeenAsked();
		initWeightOfItem();

		currTagCountOfUser = getTagCountOfUser(currTagCountOfUser, idxOfItem, answer);
		double[] tagPreferenceOfUser = getTagPreferenceOfUser(currTagCountOfUser);
		
		decreaseWeightOfItem(tagPreferenceOfUser, userGamma);
		removePreferenceTag(tagPreferenceOfUser);
		
		for(int k = 0; k < numOfQuestion; k++) {
			int idxOfSelTag = generateNextQuestion_Greedy();
			if(idxOfSelTag == -1) {
				break;
			}
			
			System.out.print("'" + tagNames[idxOfSelTag] + "'? ");
			String myAnswer = input.nextLine();
			
			if(myAnswer.compareTo("Y") == 0) {
				decreaseWeightOfItemWithoutSelTag(idxOfSelTag, questGamma);
			} else if(myAnswer.compareTo("N") == 0) {
				decreaseWeightOfItemWithSelTag(idxOfSelTag, questGamma);
			}
	
			displayPossibleItems(10);
			
			removeSelTag(idxOfSelTag);
		}
		
		input.close();
	}
	
	
	private void initTagHasNotBeenAsked() {
		
		tagHasNotBeenAsked = new ArrayList<Integer>();
		
		for(int j = 0; j < numOfTag; j++) {
			tagHasNotBeenAsked.add(j);
		}
	}
	
	private void initWeightOfItem() {
		
		weightOfItems = new double[numOfItem];
		
		for(int i = 0; i < numOfItem; i++) {
			weightOfItems[i] = 1.0;
		}
	}
	
	private int generateNextQuestion_Greedy() {
		
		int idxOfSelTag = -1;
		
		InteractiveHeuristicSearch_Greedy IHS_Greedy;
		IHS_Greedy = new InteractiveHeuristicSearch_Greedy(itemToTagMatrix,
                                                           weightOfItems,
                                                           tagHasNotBeenAsked);
		idxOfSelTag = IHS_Greedy.generateNextQuestion();
		
		return idxOfSelTag;
	}
	
	private int generateNextQuestion_IHS_MMAS() {
		
		int idxOfSelTag = -1;
		
		InteractiveHeuristicSearch_MMAS IHS_MMAS;
		IHS_MMAS = new InteractiveHeuristicSearch_MMAS(itemToTagMatrix,
                                                       weightOfItems,
                                                       tagHasNotBeenAsked);
		idxOfSelTag = IHS_MMAS.generateNextTwoQuestion()[0];
		
		return idxOfSelTag;
	}
	
	private int[] generateNextQuestion_SHS_MMAS() {
		
		int[] idxOfSelTag = null;
		
		InteractiveHeuristicSearch_MMAS SHS_MMAS;
		SHS_MMAS = new InteractiveHeuristicSearch_MMAS(itemToTagMatrix,
                                                       weightOfItems,
                                                       tagHasNotBeenAsked);
		idxOfSelTag = SHS_MMAS.generateNextTwoQuestion();
		
		return idxOfSelTag;
	}
	
	// New function!
	private double[] getTagPreferenceOfUser(int[] tagCountOfUser) {
		
		double[] tagPreferenceOfUser = new double[numOfTag];
		
		int max = 0;
		for(int i = 0; i < numOfTag; i++) {
			if(max < Math.abs(tagCountOfUser[i])) {
				max = Math.abs(tagCountOfUser[i]);
			}
		}
		
		for(int i = 0; i < numOfTag; i++) {
			tagPreferenceOfUser[i] = 1.0*tagCountOfUser[i]/max;
		}
		
		return tagPreferenceOfUser;
	}
	
	// New function
	public int[] getTagCountOfUser(int[] currTagCountOfUser, int[] idxOfItem, int[] answer) {
		
		int[] tagCountOfUser = new int[numOfTag];
		
		for(int i = 0; i < idxOfItem.length; i++) {
			for(int j = 0; j < numOfTag; j++) {
				if(itemToTagMatrix[idxOfItem[i]][j] == 1) {
					if(answer[i] == 1) {
						tagCountOfUser[j]++;
					} else if(answer[i] == 0){
						tagCountOfUser[j]--;
					}
				}
				
			}
			tagCountOfUser[i] += currTagCountOfUser[i];
		}
		return tagCountOfUser;
	}
	
	// New function!
	private void decreaseWeightOfItem(double[] tagPreferenceOfUser, double gamma) {
		
		for(int idxOfTag = 0; idxOfTag < numOfTag; idxOfTag++) {
			if(tagPreferenceOfUser[idxOfTag] > 0.5) {
				decreaseWeightOfItemWithoutSelTag(idxOfTag, gamma);
			} else if(tagPreferenceOfUser[idxOfTag] < -0.5) {
				decreaseWeightOfItemWithSelTag(idxOfTag, gamma);
			}
		}
	}
	
	
	private void decreaseWeightOfItemWithSelTag(int idxOfSelTag, double gamma) {
		
		for(int i = 0; i < numOfItem; i++) {
			if(itemToTagMatrix[i][idxOfSelTag] == 1) {
				weightOfItems[i] *= gamma;
			}
		}
	}

	private void decreaseWeightOfItemWithoutSelTag(int idxOfSelTag, double gamma) {

		for(int i = 0; i < numOfItem; i++) {
			if(itemToTagMatrix[i][idxOfSelTag] == 0) {
				weightOfItems[i] *= gamma;
			}
		}
	}
	
	// New function!
	private void removePreferenceTag(double[] tagPreferenceOfUser) {
		
		for(int i = 0; i < tagPreferenceOfUser.length; i++) {
			if((tagPreferenceOfUser[i] > 0.5)
			 || tagPreferenceOfUser[i] < -0.5) {
				removeSelTag(i);
			}
		}
	}

	private void removeSelTag(int idxOfSelTag) {
		
		for(int j = 0; j < tagHasNotBeenAsked.size(); j++) {
			if(tagHasNotBeenAsked.get(j) == idxOfSelTag) {
				tagHasNotBeenAsked.remove(j);
			}
		}
	}

	private void displayPossibleItems(int numOfItemToDisplay) {
		
		int[] idxOfPossibleItems = getIndexOfPossibleItems();
		System.out.println("Possible items:");
		for(int i = 0; i < numOfItemToDisplay; i++) {
			int idxOfPossibleItem = idxOfPossibleItems[i];
			System.out.printf("%s\t%.4f\t", itemNames[idxOfPossibleItem],
			                                weightOfItems[idxOfPossibleItem]);
			for(int j = 0; j < numOfTag; j++) {
				if(itemToTagMatrix[idxOfPossibleItem][j] == 1) {
					System.out.print(tagNames[j] + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int getIndexOfMostPossibleItem() {
	
		int idx = -1;
		double max = 0;
		
		for(int i = 0; i < numOfItem; i++) {
			if(weightOfItems[i] > max) {
				max = weightOfItems[i];
				idx = i;
			}
		}
		
		return idx;
	}

	public int[] getIndexOfPossibleItems() {
		
		int[] idxOfPossibleItems = new int[numOfItem];
		
		Pair[] possibleItems = new Pair[numOfItem];
		for(int i = 0; i < numOfItem; i++) {
			possibleItems[i] = new Pair(i, weightOfItems[i]);
		}
		Arrays.sort(possibleItems);
		
		for(int i = 0; i < numOfItem; i++) {
			idxOfPossibleItems[i] = possibleItems[i].index;
		}
		
		return idxOfPossibleItems;
	}

	public void readDataMatrixFromFile() {
		try {
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(context.getAssets().open(matrixFileName)));
			buffReader.readLine();
			numOfItem = Integer.parseInt(buffReader.readLine());
			numOfTag = Integer.parseInt(buffReader.readLine());
			numOfOne = Integer.parseInt(buffReader.readLine());
			buffReader.readLine();
			
			itemToTagMatrix = new int[numOfItem][numOfTag];
			itemNames = new String[numOfItem];
			tagNames = new String[numOfTag];
			
			for(int i = 0; i < numOfItem; i++) {
				itemNames[i] = buffReader.readLine();
			}
			buffReader.readLine();
			
			for(int j = 0; j < numOfTag; j++) {
				tagNames[j] = buffReader.readLine();
			}
			buffReader.readLine();
			
			for(int k = 0; k < numOfOne; k++) {
				String[] lineSplits =buffReader.readLine().split(",");
				int i = Integer.parseInt(lineSplits[0]);
				int j = Integer.parseInt(lineSplits[1]);
				itemToTagMatrix[i][j] = 1;
			}
			buffReader.readLine();
			
			buffReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int[][] getItemToTagMatrix() {
		return itemToTagMatrix;
	}

	public String[] getItemNames() {
		return itemNames;
	}

	public String[] getTagNames() {
		return tagNames;
	}
	
}
