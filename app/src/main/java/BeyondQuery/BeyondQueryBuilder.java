package BeyondQuery;

import android.content.Context;


import com.example.yudbet.longingformao.R;
import com.example.yudbet.longingformao.items.Recommandation;

import java.util.ArrayList;

/**
 * Created by admin on 2017/6/13.
 */

public class BeyondQueryBuilder {
    public static final int numOfQuestion = 101;
    public static final double questGamma = 0.5;
    public static final double userGamma = 0.5;
    public static final String matrixFileName = "COA_OpenData.txt";;


    private InteractiveRecommendation ir;
    private int[] currTagCountOfUser;
    private int[] idxOfItem;
    private int[] answer;
    private int[] results;

    public BeyondQueryBuilder(int[] currTagCountOfUser, int[] idxOfItem, int[] answer, Context context) {
        this.currTagCountOfUser = currTagCountOfUser;
        this.idxOfItem = idxOfItem;
        this.answer = answer;

        ir = new InteractiveRecommendation(matrixFileName,
                                           numOfQuestion,
                                           questGamma,
                                           userGamma,
                                           context);
    }

    public int[] computeRecommandResults() {
        results = new int[5];

        ir.autoInteractiveSearchTargetItem(currTagCountOfUser, idxOfItem, answer);
        int[] recommandResult = ir.getIndexOfPossibleItems();

        for(int i = 0; i < 5; i++) {
            results[i] = recommandResult[i];
        }
        return results;
    }

    public ArrayList<Recommandation> genRecomendations() {
        ArrayList<Recommandation> recommandations = new ArrayList<>();

        int[][] itemToTagMatrix = ir.getItemToTagMatrix();
        String[] itemNames = ir.getItemNames();
        String[] tagNames = ir.getTagNames();

        int[] drawables = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
                          R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10,
                          R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15,
                          R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20};

        for(int i = 0; i < 5; i++) {
            String type = "";
            String sex = "";
            String county = "";
            String age = "";

            for(int j = 0; j < tagNames.length; j++) {
                if(itemToTagMatrix[i][j] == 1) {

                    if(tagNames[j].split(":")[0].compareTo("種類") == 0) {
                        type = tagNames[j].split(":")[1];
                    }
                    if(tagNames[j].split(":")[0].compareTo("性別") == 0) {
                        sex = tagNames[j].split(":")[1];
                    }
                    if(tagNames[j].split(":")[0].compareTo("縣市") == 0) {
                        county = tagNames[j].split(":")[1];
                    }
                    if(tagNames[j].split(":")[0].compareTo("年紀") == 0) {
                        age = tagNames[j].split(":")[1];
                    }
                }
            }

            recommandations.add(new Recommandation(i, drawables[i] , type, sex, county, age));
        }

        return recommandations;
    }

    public ArrayList<Recommandation> genRecomendations(int[] idxOfAnimal) {
        ArrayList<Recommandation> recommandations = new ArrayList<>();

        int[][] itemToTagMatrix = ir.getItemToTagMatrix();
        String[] itemNames = ir.getItemNames();
        String[] tagNames = ir.getTagNames();

        int[] drawables = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5,
                R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10,
                R.drawable.p11, R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15,
                R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20};

        for(int i = 0; i < idxOfAnimal.length; i++) {
            String type = "";
            String sex = "";
            String county = "";
            String age = "";

            for(int j = 0; j < tagNames.length; j++) {
                if(itemToTagMatrix[idxOfAnimal[i]][j] == 1) {

                    if(tagNames[j].split(":")[0].compareTo("種類") == 0) {
                        type = tagNames[j].split(":")[1];
                    }
                    if(tagNames[j].split(":")[0].compareTo("性別") == 0) {
                        sex = tagNames[j].split(":")[1];
                    }
                    if(tagNames[j].split(":")[0].compareTo("縣市") == 0) {
                        county = tagNames[j].split(":")[1];
                    }
                    if(tagNames[j].split(":")[0].compareTo("年紀") == 0) {
                        age = tagNames[j].split(":")[1];
                    }
                }
            }

            recommandations.add(new Recommandation(idxOfAnimal[i], drawables[idxOfAnimal[i]] , type, sex, county, age));
        }

        return recommandations;
    }

    public InteractiveRecommendation getInteractiveRecommendation() {
        return ir;
    }

}
