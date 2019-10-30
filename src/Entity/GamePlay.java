package Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GamePlay {
    @SerializedName("id")
    private int GID;

    @SerializedName("card")
    private String CardString;

    public GamePlay(int GID,String cardString){
        this.GID = GID;
        this.CardString=cardString;
    }

    public String getCard(){
        return CardString;
    }
}
