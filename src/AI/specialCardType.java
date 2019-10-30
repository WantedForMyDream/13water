package AI;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class specialCardType {
    private static int[][] card={
            {0,0,0,0},      //0
            {0,0,0,0},      //1
            {0,0,0,0},      //2
            {0,0,0,0},      //3
            {0,0,0,0},      //4
            {0,0,0,0},      //5
            {0,0,0,0},      //6
            {0,0,0,0},      //7
            {0,0,0,0},      //8
            {0,0,0,0},      //9
            {0,0,0,0},      //10
            {0,0,0,0},      //J
            {1,1,1,0},      //Q
            {0,0,0,0},      //K
            {1,1,0,0},      //A
    };

//    public static void main(String args[]){
////        specialCardType s=new specialCardType();
////        System.out.println(s.checkIsSanTongHua());
//
//        int i,j;
//        List<Pair<Integer,Integer>> testList=new ArrayList<>();
//        for(i=14;i>=2;i--){
//            for(j=3;j>=0;j--){
//                if(specialCardType.card[i][j]==1){
//                    Pair<Integer,Integer> pair=new Pair<>(i,j);
//                    testList.add(pair);
//                }
//            }
//        }
//
//        int num=isHuLu(testList,7);
//        System.out.println(num);
//    }

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

    public static int isTongHua(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j;
        int count;
        int[] book={0,0,0,0,0,0};
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

    public static int isHuLu(List<Pair<Integer,Integer>> list,int score){
        int[][] cardArray=new int[15][4];
        int i,j;
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

    public static int isShunzi(List<Pair<Integer,Integer>> list, int score,int num){
        int[][] cardArray=new int[15][4];
        int i,j,k;
        int count;
        int[] book={0,0,0,0,0,0};
        for(i=0;i<=14;i++){
            for(j=0;j<=3;j++)
                cardArray[i][j]=0;
        }

        for(i=0;i<list.size();i++){
            cardArray[list.get(i).getKey()][list.get(i).getValue()]=1;
        }

        for(j=0;j<=3;j++){
            for(i=2;i<=14;i++){
                count=0;
                for(k=i;i<=14;k++){
                    if(cardArray[k][j]==0)
                        break;

                    count++;
                }
                book[count]++;
                i=k;
            }

        }

        if((num==5&&book[5]==1)||(num==3&&book[3]==1))
            return 100*score+list.get(0).getKey();
        return 0;
    }



    //至尊青龙
    public boolean checkIsKingDragen(){
        int flag=0;
        int count;
        for(int j=0;j<=3;j++){
            count=0;
            for(int i=2;i<=14;i++)
            {
                if(card[i][j]!=1)
                    break;

                count++;
                if(count==13)
                    return true;
            }
        }
        return false;
    }

    //一条龙
    public boolean checkIsDragen(){
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=2;i<=14;i++){
            for(int j=0;j<=3;j++)
            {
                    book[i]+=card[i][j];
            }
            if(book[i]!=1)
                return false;
        }
        return true;
    }

    //十二皇族
    public boolean checkIsTwelveRoyalty(){
        int count=0;
        for(int i=10;i<=14;i++){
            for(int j=0;j<=3;j++)
            {
                count+=card[i][j];
            }
        }
        if(count<12)
            return false;

        return true;
    }

    //三同花
    public boolean checkIsThreeFlush(){
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int count;
        int i,j,k;
        for(j=0;j<=3;j++)
        {
            for(i=2;i<=14;i++)
            {
                count=0;
                for(k=i;k<=14;k++)
                {
                    if(card[k][j]==0)
                    {
                        break;
                    }
                    count++;
                }
                book[count]++;
                i=k;
            }
        }

        for(i=2;i<=14;i++)
        {
            System.out.println(i+" "+book[i]);
        }

        if(book[3]==1&&book[10]==1)
            return true;
        if(book[3]==1&&book[5]==2)
            return true;
        if(book[5]==1&&book[8]==1)
            return true;
        return false;
    }

    //三分天下
    public boolean checkIsDividByThree()
    {
        int i,j;
        int count;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
            book[count]++;
        }

        if(book[4]==3&&book[1]==1)
            return true;

        return false;
    }

    //全大
    public boolean checkIsAllBig(){
        int i,j;
        int count=0;

        for(i=8;i<=14;i++){
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
        }

        System.out.println(count);
        if(count==13)
            return true;

        return false;
    }

    //全小
    public boolean checkIsAllSmall(){
        int i,j;
        int count=0;

        for(i=2;i<=8;i++){
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
        }

        if(count==13)
            return true;

        return false;
    }

    //凑一色
    public boolean checkIsAlike(){
        int i,j;
        int count=0;

        for(j=0;j<=3;j++){
            count=0;
            for(i=2;i<=14;i++){
                if(card[i][j]==1)
                    count++;
            }
            if(count==13)
                return true;
        }

        return false;
    }

    //双怪冲三
    public boolean checkIsShuangGuai(){
        int i,j;
        int count=0;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
            book[count]++;
        }

        if(book[3]==2&&book[2]==3)
            return true;
        return false;
    }

    //四套三条
    public boolean checkIsFourThree(){
        int i,j;
        int count=0;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
            book[count]++;
        }

        if(book[3]==4&&book[1]==1)
            return true;
        return false;
    }

    //五对三条
    public boolean checkIsFiveThree(){
        int i,j;
        int count=0;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
            book[count]++;
        }

        if(book[2]==5&&book[3]==1)
            return true;
        return false;
    }

    //六对半
    public boolean checkIsSixAndHalfOne(){
        int i,j;
        int count=0;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(i=2;i<=14;i++){
            count=0;
            for(j=0;j<=3;j++){
                if(card[i][j]==1)
                    count++;
            }
            book[count]++;
        }

        if(book[2]==6&&book[1]==1)
            return true;
        return false;
    }

    //三顺子 不会

    //三同花
    public boolean checkIsSanTongHua(){
        int i,j;
        int count=0;
        int[] book={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for(j=0;j<=3;j++){
            count=0;
            for(i=2;i<=14;i++){
                if(card[i][j]==1)
                    count++;
            }
            book[count]++;
        }

        if((book[3]==1&&book[5]==2)||(book[3]==1&&book[10]==1)||(book[8]==1&&book[5]==1))
            return true;
        return false;
    }
}
