package AI;

import javafx.util.Pair;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private static int[][] card={
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
    };

    public static List<Pair<Integer,Integer>> cardListOfRearPier=new ArrayList<>();
    public static List<Pair<Integer,Integer>> theBestCardListofRearPier=new ArrayList<>();
    public static List<String> RearPier=new ArrayList<>();

    public static List<Pair<Integer,Integer>> cardListOfMidPier=new ArrayList<>();
    public static List<Pair<Integer,Integer>> tempMidPier=new ArrayList<>();
    public static List<Pair<Integer,Integer>> theBestCardListofMidPier=new ArrayList<>();
    public static List<String> MidPier=new ArrayList<>();

    public static List<Pair<Integer,Integer>> cardListOfFrontPier=new ArrayList<>();
    public static List<Pair<Integer,Integer>> theBestCardListofFrontPier=new ArrayList<>();
    public static List<String> FrontPier=new ArrayList<>();

    public static List<String> getAllPier(String inputs)
    {
        List<String> allPire=new ArrayList<>();

        init(inputs);

        getRearPier();
        renewCardArray(theBestCardListofRearPier);

        List<String> list=getStrList(theBestCardListofRearPier);
//        System.out.println(list.toString());

//        for(int i=2;i<=14;i++){
//            for(int j=0;j<=3;j++){
//                System.out.print(card[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();

        if(!theBestCardListofMidPier.isEmpty()){
            MidPier=getStrList(theBestCardListofMidPier);
            System.out.println(MidPier.toString());

            renewCardArray(theBestCardListofMidPier);

//            for(int i=2;i<=14;i++){
//                for(int j=0;j<=3;j++){
//                    System.out.print(card[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();

            directGetFrontPier();
        }
        else {
//            System.out.println("111111");
//            System.out.println("寻找前墩和中墩开始");
            getFrontPier();
//            System.out.println("寻找前墩和中墩结束");
        }

        FrontPier=getStrList(theBestCardListofFrontPier);
        MidPier=getStrList(theBestCardListofMidPier);
        RearPier=getStrList(theBestCardListofRearPier);

//        System.out.println(FrontPier.toString());
//        System.out.println(MidPier.toString());
//        System.out.println(RearPier.toString());

        String pier=FrontPier.toString().replace("[","\"").replace("]","\"").replace(",","");
        //System.out.println(pier);
        allPire.add(pier);

        pier=MidPier.toString().replace("[","\"").replace("]","\"").replace(",","");
        //System.out.println(pier);
        allPire.add(pier);

        pier=RearPier.toString().replace("[","\"").replace("]","\"").replace(",","");
        //System.out.println(pier);
        allPire.add(pier);


        return allPire;
    }

    public static void main(String[] args) {
        String string="&10 #9 &5 $10 &2 $8 #6 $2 #8 #4 *2 $3 *A";
        System.out.println(string);
        List<String> list=getAllPier(string);
        System.out.println(list.toString());
    }

    public static void init(String str){
        String[] poker;
        String delimeter=" ";
        poker=str.split(delimeter);
        for(String s:poker){
            Pair<Integer,Integer> p=setPokerColorAndNum(s);
            //System.out.println(p.getKey()+" "+p.getValue());
            card[p.getKey()][p.getValue()]=1;
        }
    }

    //获取后墩
    public static void getRearPier(){
        int i1,j1,i2,j2,i3,j3,i4,j4,i5,j5;
        int[][] book=new int[15][4];
        int maxScore=0;
        int score;
        int scoreFromMidPier;

        for(i1=0;i1<=14;i1++)
        {
            for(j1=0;j1<4;j1++)
                book[i1][j1]=0;
        }

        for(i1=14;i1>=2;i1--){
            for(j1=3;j1>=0;j1--){
                if(card[i1][j1]==1&&book[i1][j1]==0){
                    book[i1][j1]=1;

                    for(i2=14;i2>=2;i2--){
                        for(j2=3;j2>=0;j2--){
                            if((i2+j2<i1+j1)&&card[i2][j2]==1&&book[i2][j2]==0){
                                book[i2][j2]=1;

                                for(i3=14;i3>=2;i3--){
                                    for(j3=3;j3>=0;j3--){
                                        if((i3+j3<i2+j2)&&card[i3][j3]==1&&book[i3][j3]==0){
                                            book[i3][j3]=1;

                                            for(i4=14;i4>=2;i4--){
                                                for(j4=3;j4>=0;j4--){
                                                    if((i4+j4<i3+j3)&&card[i4][j4]==1&&book[i4][j4]==0){
                                                        book[i4][j4]=1;

                                                        for(i5=14;i5>=2;i5--){
                                                            for(j5=3;j5>=0;j5--){
                                                                if((i5+j5<i4+j4)&&card[i5][j5]==1&&book[i5][j5]==0){
                                                                    book[i5][j5]=1;

                                                                    if(!cardListOfRearPier.isEmpty()){
                                                                        cardListOfRearPier.clear();
                                                                    }

                                                                    if(!tempMidPier.isEmpty()){
                                                                        tempMidPier.clear();
                                                                    }

                                                                    Pair<Integer,Integer> pair1=new Pair<>(i1,j1);
                                                                    Pair<Integer,Integer> pair2=new Pair<>(i2,j2);
                                                                    Pair<Integer,Integer> pair3=new Pair<>(i3,j3);
                                                                    Pair<Integer,Integer> pair4=new Pair<>(i4,j4);
                                                                    Pair<Integer,Integer> pair5=new Pair<>(i5,j5);

                                                                    cardListOfRearPier.add(pair1);
                                                                    cardListOfRearPier.add(pair2);
                                                                    cardListOfRearPier.add(pair3);
                                                                    cardListOfRearPier.add(pair4);
                                                                    cardListOfRearPier.add(pair5);

                                                                    if((score=isTongHuaShun(cardListOfRearPier,10))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(1111100000);
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isZhaDan(cardListOfRearPier,9))>0){
                                                                        renewCardArray(cardListOfRearPier);

                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(1111122222);
//                                                                            System.out.println(maxScore);

                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isHuLu(cardListOfRearPier,8))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(111113333);
//                                                                            List<String> list=getStrList(theBestCardListofRearPier);
//                                                                            System.out.println(list.toString());
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isTongHua(cardListOfRearPier,7))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        scoreFromMidPier=getMidPier();
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(1111144444);
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isZhaShun(cardListOfRearPier,6))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

                                                                            System.out.println(111115555);
//
//                                                                            List<String> list=getStrList(theBestCardListofRearPier);
//                                                                            System.out.println(list.toString());
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isSanTiao(cardListOfRearPier,5))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(1111166666);
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isLiangDui(cardListOfRearPier,4))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(111117777);
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }
                                                                    else if((score=isYiDui(cardListOfRearPier,3))>0){
                                                                        renewCardArray(cardListOfRearPier);
                                                                        if((score>(scoreFromMidPier=getMidPier()))&&(maxScore<=score+scoreFromMidPier)){
                                                                            maxScore=score+scoreFromMidPier;
                                                                            renewBestCardListOfMidPier();
                                                                            renewBestCardListOfRearPier();

//                                                                            System.out.println(111118888);
                                                                        }
                                                                        backCardArray(cardListOfRearPier);
                                                                    }


                                                                    book[i5][j5]=0;
                                                                }
                                                            }
                                                        }
                                                        book[i4][j4]=0;
                                                    }
                                                }
                                            }

                                            book[i3][j3]=0;
                                        }
                                    }
                                }

                                book[i2][j2]=0;
                            }
                        }
                    }

                    book[i1][j1]=0;
                }
            }
        }
       // System.out.println(maxScore);
    }

    //获取中墩
    public static int getMidPier(){
        int i1,j1,i2,j2,i3,j3,i4,j4,i5,j5;
        int[][] book=new int[15][4];
        int score;
        int maxScore=0;

        for(i1=0;i1<=14;i1++)
        {
            for(j1=0;j1<4;j1++)
                book[i1][j1]=0;
        }

        for(i1=14;i1>=2;i1--){
            for(j1=3;j1>=0;j1--){
                if(card[i1][j1]==1&&book[i1][j1]==0){
                    book[i1][j1]=1;

                    for(i2=14;i2>=2;i2--){
                        for(j2=3;j2>=0;j2--){
                            if((i2+j2<=i1+j1)&&card[i2][j2]==1&&book[i2][j2]==0){
                                book[i2][j2]=1;

                                for(i3=14;i3>=2;i3--){
                                    for(j3=3;j3>=0;j3--){
                                        if((i3+j3<=i2+j2)&&card[i3][j3]==1&&book[i3][j3]==0){
                                            book[i3][j3]=1;

                                            for(i4=14;i4>=2;i4--){
                                                for(j4=3;j4>=0;j4--){
                                                    if((i4+j4<=i3+j3)&&card[i4][j4]==1&&book[i4][j4]==0){
                                                        book[i4][j4]=1;

                                                        for(i5=14;i5>=2;i5--){
                                                            for(j5=3;j5>=0;j5--){
                                                                if((i5+j5<=i4+j4)&&card[i5][j5]==1&&book[i5][j5]==0){
                                                                    book[i5][j5]=1;

                                                                    if(!cardListOfMidPier.isEmpty()){
                                                                        cardListOfMidPier.clear();
                                                                    }

                                                                    Pair<Integer,Integer> pair1=new Pair<>(i1,j1);
                                                                    Pair<Integer,Integer> pair2=new Pair<>(i2,j2);
                                                                    Pair<Integer,Integer> pair3=new Pair<>(i3,j3);
                                                                    Pair<Integer,Integer> pair4=new Pair<>(i4,j4);
                                                                    Pair<Integer,Integer> pair5=new Pair<>(i5,j5);

                                                                    cardListOfMidPier.add(pair1);
                                                                    cardListOfMidPier.add(pair2);
                                                                    cardListOfMidPier.add(pair3);
                                                                    cardListOfMidPier.add(pair4);
                                                                    cardListOfMidPier.add(pair5);

                                                                    if((score=isTongHuaShun(cardListOfMidPier,10))>0){
                                                                        if(maxScore<score){
//                                                                            System.out.println(11111);

                                                                            if(!tempMidPier.isEmpty()){
                                                                                tempMidPier.clear();
                                                                            }
                                                                            tempMidPier.addAll(cardListOfMidPier);
                                                                            maxScore=score;
                                                                        }
                                                                    }
                                                                    else if((score=isZhaDan(cardListOfMidPier,9))>0){
                                                                        if(maxScore<score){
//                                                                            System.out.println(2222);

                                                                            if(!tempMidPier.isEmpty()){
                                                                                tempMidPier.clear();
                                                                            }
                                                                            tempMidPier.addAll(cardListOfMidPier);

                                                                            maxScore=score;
                                                                        }
                                                                    }
                                                                    else if((score=isHuLu(cardListOfMidPier,8))>0){
                                                                        if(maxScore<score){
//                                                                            System.out.println(3333);

                                                                            if(!tempMidPier.isEmpty()){
                                                                                tempMidPier.clear();
                                                                            }
                                                                            tempMidPier.addAll(cardListOfMidPier);

                                                                            maxScore=score;
                                                                        }
                                                                    }

                                                                    book[i5][j5]=0;
                                                                }
                                                            }
                                                        }
                                                        book[i4][j4]=0;
                                                    }
                                                }
                                            }

                                            book[i3][j3]=0;
                                        }
                                    }
                                }

                                book[i2][j2]=0;
                            }
                        }
                    }

                    book[i1][j1]=0;
                }
            }
        }

        //System.out.println(maxScore);
        return maxScore;
    }

    //获取前墩
    public static void getFrontPier(){
        int i1,j1,i2,j2,i3,j3;
        int[][] book=new int[15][4];
        int score;
        int scoreFromMidPier;
        int maxScore=0;
        int finalScore=0;

        for(i1=0;i1<=14;i1++)
        {
            for(j1=0;j1<4;j1++)
                book[i1][j1]=0;
        }

        for(i1=2;i1<=14;i1++){
            for(j1=0;j1<=3;j1++){
                if(card[i1][j1]==1&&book[i1][j1]==0){
                    book[i1][j1]=1;

                    for(i2=2;i2<=14;i2++){
                        for(j2=0;j2<=3;j2++){
                            if((i2+j2>=i1+j1)&&card[i2][j2]==1&&book[i2][j2]==0){
                                book[i2][j2]=1;

                                for(i3=2;i3<=14;i3++){
                                    for(j3=0;j3<=3;j3++){
                                        if((i3+j3>=i2+j2)&&card[i3][j3]==1&&book[i3][j3]==0){
                                            book[i3][j3]=1;

                                            if(!cardListOfFrontPier.isEmpty()){
                                                cardListOfFrontPier.clear();
                                            }

                                            Pair<Integer,Integer> pair1=new Pair<>(i1,j1);
                                            Pair<Integer,Integer> pair2=new Pair<>(i2,j2);
                                            Pair<Integer,Integer> pair3=new Pair<>(i3,j3);

                                            cardListOfFrontPier.add(pair1);
                                            cardListOfFrontPier.add(pair2);
                                            cardListOfFrontPier.add(pair3);

                                            if((score=isSanTiao(cardListOfFrontPier,5))>0){
                                                renewCardArray(cardListOfFrontPier);

                                                scoreFromMidPier=getMidPierScore();
                                               // System.out.println("1 "+scoreFromMidPier);
                                                if((score<scoreFromMidPier)&&maxScore<score&&finalScore<score+scoreFromMidPier){
                                                    renewBestCardListOfFrontPier();
                                                    renewBestCardListOfMidPier2();
                                                    maxScore=score;
                                                    finalScore=score+scoreFromMidPier;
                                                }
                                                backCardArray(cardListOfFrontPier);
                                            }
                                            else if((score=isYiDui(cardListOfFrontPier,3))>0){
                                                renewCardArray(cardListOfFrontPier);

                                                scoreFromMidPier=getMidPierScore();

                                                //System.out.println("2 "+scoreFromMidPier);

                                                if((score<scoreFromMidPier)&&maxScore<score&&finalScore<score+scoreFromMidPier){
                                                    renewBestCardListOfFrontPier();
                                                    renewBestCardListOfMidPier2();
                                                    maxScore=score;
                                                    finalScore=score+scoreFromMidPier;
                                                }
                                                backCardArray(cardListOfFrontPier);
                                            }
                                            //乌龙
                                            else {
                                                score=100+cardListOfFrontPier.get(cardListOfFrontPier.size()-1).getKey();
                                                renewCardArray(cardListOfFrontPier);

                                                scoreFromMidPier=getMidPierScore();

                                               // System.out.println("3 "+scoreFromMidPier);

                                                if((score<scoreFromMidPier)&&maxScore<score&&finalScore<score+scoreFromMidPier){
                                                    renewBestCardListOfFrontPier();
                                                    renewBestCardListOfMidPier2();
                                                    maxScore=score;
                                                    finalScore=score+scoreFromMidPier;
                                                }
                                                backCardArray(cardListOfFrontPier);
                                            }

                                            book[i3][j3]=0;
                                        }
                                    }
                                }

                                book[i2][j2]=0;
                            }
                        }
                    }

                    book[i1][j1]=0;
                }
            }
        }
    }

    public static void directGetFrontPier(){
        int i,j;

        if(!cardListOfFrontPier.isEmpty()){
            cardListOfFrontPier.clear();
        }

        for(i=14;i>=2;i--){
            for(j=3;j>=0;j--){
                if(card[i][j]==1){
                    Pair<Integer,Integer> pair=new Pair<>(i,j);
                    cardListOfFrontPier.add(pair);
                }
            }
        }

        renewBestCardListOfFrontPier();
    }

    public static int getMidPierScore(){
        int i,j;
        int score=0;

        if(!cardListOfMidPier.isEmpty()){
            cardListOfMidPier.clear();
        }

        for(i=14;i>=2;i--){
            for(j=3;j>=0;j--){
                if(card[i][j]==1){
                    Pair<Integer,Integer> pair=new Pair<>(i,j);
                    cardListOfMidPier.add(pair);
                }
            }
        }

        if((score=isTongHua(cardListOfMidPier,7))>0){
            return score;
        }
        else if((score=isZhaShun(cardListOfMidPier,6))>0){
            return score;
        }
        else if((score=isSanTiao(cardListOfMidPier,5))>0){
            return score;
        }
        else if((score=isLiangDui(cardListOfMidPier,4))>0){
            return score;
        }
        else if((score=isYiDui(cardListOfMidPier,3))>0){
            return score;
        }
        else{
            return 100+cardListOfMidPier.get(cardListOfMidPier.size()-1).getKey();
        }
    }

    public static int isTongHuaShun(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int i,j,k;
        int count;
        for(i=0;i<=14;i++)
        {
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(j=0;j<=3;j++){
            for(i=0;i<=14;i++){
                count=0;
                for(k=i;k<=14;k++){
                    if(cardArray[k][j]==0){
                        break;
                    }
                    count++;
                }
                book[count]++;
                i=k;
            }
        }

        if(book[5]==1)
            return 100*score+list.get(0).getKey();
        return 0;
    }

    public static int isZhaDan(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j;
        int count;
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(cardArray[i][j]==1){
                    count++;
                }
            }
            if(count==4){
                if(list.get(0).getKey()==list.get(1).getKey())
                    return 100*score+list.get(0).getKey();
                else
                    return 100*score+list.get(list.size()-1).getKey();
            }
        }

        return 0;
    }

    public static int isHuLu(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j;
        int count;
        int maxLine=0;
        int[] book={0,0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(cardArray[i][j]==1)
                    count++;
            }
            if(count==3){
                if(maxLine<i)
                    maxLine=i;
            }
            book[count]++;
        }

        if(book[3]==1&&book[2]==1){
            return 100*score+maxLine;
        }
        return 0;
    }

    public static int isTongHua(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j;
        int count;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(j=0;j<=3;j++){
            count=0;
            for(i=2;i<=14;i++){
                if(cardArray[i][j]==1)
                    count++;
            }
            book[count]++;
        }
        if(book[5]==1)
            return 100*score+list.get(0).getKey();
        return 0;
    }

    public static int isZhaShun(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j,k;
        int count;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(cardArray[i][j]==1)
                    count++;
            }
            book[i]=count;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(k=i;k<=14;k++){
                if(book[k]==0)
                    break;

                count++;
            }
            if(count==5)
                return 100*score+list.get(0).getKey();
        }
        return 0;
    }

    public static int isSanTiao(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j,k=0;
        int count;
        int[] book={0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(cardArray[i][j]==1)
                    count++;
            }
            if(count==3)
                k=i;
            book[count]++;
        }

        if(book[3]==1&&(book[1]==2||book[1]==0))
            return 100*score+k;

        return 0;
    }

    public static int isLiangDui(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j,k;
        int count;
        int maxLine=0;
        int[] book={0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(cardArray[i][j]==1)
                    count++;
            }
            if(count==2){
                if(i>maxLine){
                    maxLine=i;
                }
            }
            book[count]++;
        }

        if(book[2]==2&&book[1]==1)
            return 100*score+maxLine;

        return 0;
    }

    public static int isYiDui(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j;
        int maxLine=0;
        int count;
        int[] book={0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(cardArray[i][j]==1)
                    count++;
            }
            if(count==2){
                if(maxLine<i)
                    maxLine=i;
            }
            book[count]++;
        }

        if(book[2]==1&&(book[1]==1||book[1]==3))
            return 100*score+maxLine;

        return 0;
    }

    public static void renewCardArray(List<Pair<Integer,Integer>> list){
        int i,j;
        for(i=0;i<list.size();i++){
            card[list.get(i).getKey()][list.get(i).getValue()]--;
        }
    }

    public static void backCardArray(List<Pair<Integer,Integer>> list){
        int i,j;
        for(i=0;i<list.size();i++){
            card[list.get(i).getKey()][list.get(i).getValue()]++;
        }
    }

    public static void renewBestCardListOfMidPier(){
        if(!theBestCardListofMidPier.isEmpty()){
            theBestCardListofMidPier.clear();
        }
        theBestCardListofMidPier.addAll(tempMidPier);
    }

    public static void renewBestCardListOfMidPier2(){
        if(!theBestCardListofMidPier.isEmpty()){
            theBestCardListofMidPier.clear();
        }

        List<Pair<Integer,Integer>> pairList=new ArrayList<>();
        for(int i=14;i>=2;i--){
            for(int j=3;j>=0;j--){
                if(card[i][j]==1){
                    Pair<Integer,Integer> pair=new Pair<>(i,j);
                    pairList.add(pair);
                }
            }
        }

        theBestCardListofMidPier.addAll(pairList);
    }

    public static void renewBestCardListOfRearPier(){
        if(!theBestCardListofRearPier.isEmpty()){
            theBestCardListofRearPier.clear();
        }
        theBestCardListofRearPier.addAll(cardListOfRearPier);
    }

    public static void renewBestCardListOfFrontPier(){
        if(!theBestCardListofFrontPier.isEmpty()){
            theBestCardListofFrontPier.clear();
        }
        theBestCardListofFrontPier.addAll(cardListOfFrontPier);
    }

    public static Pair<Integer,Integer> setPokerColorAndNum(String str){
        int color=0;
        int num=0;

        for(int i=0;i<str.length();i++){
            if(i==0){
                char c=str.charAt(i);
                if(c=='$')
                    color=0;
                else if(c=='*')
                    color=1;
                else if(c=='&')
                    color=2;
                else
                    color=3;
            }

            if(i==1){
                char c=str.charAt(i);
                if(c=='J')
                    num=11;
                else if(c=='Q')
                    num=12;
                else if(c=='K')
                    num=13;
                else if(c=='A')
                    num=14;
                else{
                    if(str.length()==3)
                        num=10;
                    else
                        num=c-48;
                }
            }
        }
        Pair<Integer,Integer> pair=new Pair<>(num,color);
        return pair;
    }

    public static String getPokerStr(Pair<Integer,Integer> pair){
        String output="";
        if(pair.getValue()==0)
            output+="$";
        else if(pair.getValue()==1)
            output+="*";
        else if(pair.getValue()==2)
            output+="&";
        else
            output+="#";

        if(pair.getKey()==10)
            output+="10";
        else if(pair.getKey()==11)
            output+="J";
        else if(pair.getKey()==12)
            output+="Q";
        else if(pair.getKey()==13)
            output+="K";
        else if(pair.getKey()==14)
            output+="A";
        else{
            output+=pair.getKey();
        }

        return output;
    }

    public static List<String> getStrList(List<Pair<Integer,Integer>> pairList){
        int i;
        String string="";
        List<String> outputList=new ArrayList<>();
        for(i=0;i<pairList.size();i++){
            string=getPokerStr(pairList.get(i));
            outputList.add(string);
        }

        return outputList;
    }
}
