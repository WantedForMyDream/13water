import AI.GameLogic;
import AI.HttpUtil;
import net.sf.json.JSONObject;

import java.util.List;

import static java.lang.Thread.sleep;

public class Game {
    private static String loginURL = "http://api.revth.com/auth/login";
    private static String registerURL = "http://api.revth.com/auth/register2";
    private static String logoutURL = "http://api.revth.com/auth/logout";

    private static String joinURL = "http://api.revth.com/game/open";
    private static String submitURL = "http://api.revth.com/game/submit";

    private static String rankURL="http://api.revth.com/rank";

    private static String Token;
    private static int id;
    private static String card;

//    public static void main(String args[]) throws InterruptedException {
//        String result="";
//        HttpUtil httpUtil=new HttpUtil();
////
////        //注册
//////        JSONObject jsonObject=new JSONObject();
//////        jsonObject.put("username","ys");
//////        jsonObject.put("password","123");
//////        jsonObject.put("student_number","031702628");
//////        jsonObject.put("student_password","CSBT34DIAN");
//////        result=httpUtil.sendPost(registerURL,jsonObject,1);
//////        System.out.println(result);
////
//        //登录
//        JSONObject jsonObject1=new JSONObject();
//        jsonObject1.put("username","ys");
//        jsonObject1.put("password","123");
//        result=httpUtil.sendPost(loginURL,jsonObject1,1);
//        JSONObject jsonObject2=JSONObject.fromObject(result);
//        if(jsonObject2.get("status").toString().equals("0")){
//            Token=jsonObject2.getJSONObject("data").getString("token");
////            System.out.println(Token);
//        }
////
////
////        for(int i=0;i<50;i++){
////            sleep(1000);
////
////            //开启战局
////            result=httpUtil.sendPost(joinURL,null,2,Token);
////            JSONObject jsonObject3=JSONObject.fromObject(result);
////            if(jsonObject3.get("status").toString().equals("0")){
////                id=jsonObject3.getJSONObject("data").getInt("id");
////                card=jsonObject3.getJSONObject("data").getString("card");
////            }
//////            System.out.println(id+" "+card);
////
////            GameLogic gameLogic=new GameLogic();
////
////            List<String> list=gameLogic.getAllPier(card);
////            System.out.println(list.toString());
////
////            JSONObject jsonObject4=new JSONObject();
////            jsonObject4.put("id",id);
////            jsonObject4.put("card",list.toString());
////            System.out.println(jsonObject4.toString());
////            result=httpUtil.sendPost(submitURL,jsonObject4,1,Token);
////
////        }
////
////        //注销
////        result=httpUtil.sendPost(logoutURL,null,0,Token);
////
////
////        result=httpUtil.sendGet(rankURL,2,Token);
//
//
////
////        card="*K $Q #8 #9 *7 &A $K &7 &10 *8 *J *3 #7";
////        GameLogic gameLogic=new GameLogic();
////        List<String> list=gameLogic.getAllPier(card);
////        JSONObject jsonObject4=new JSONObject();
////        jsonObject4.put("id",1111);
////        jsonObject4.put("card",list.toString());
////        System.out.println(jsonObject4.toString());
//    }
}
