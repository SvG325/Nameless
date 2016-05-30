package rsi.nameless;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Used to display conversations
 */
public class npcActivity extends AppCompatActivity {
    private ImageView iV;
    private Button o1,o2,o3;
    private TextView tV;
    private Conversation conversation;
    private Link currentLink;
    private Battle possibleBattle;
    private Character player;
    private final int BATTLE_KEY = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npc);
        conversation = (Conversation) getIntent().getSerializableExtra("CURRENT_CONVERSATION");
        player = (Character) getIntent().getSerializableExtra("CURRENT_PLAYER");

        iV = (ImageView) findViewById(R.id.imageViewNPC);
        o1 = (Button) findViewById(R.id.option1);
        o2 = (Button) findViewById(R.id.option2);
        o3 = (Button) findViewById(R.id.option3);
        tV = (TextView) findViewById(R.id.textNPC);
        currentLink = conversation.getLink();

        Drawable d = ResourcesCompat.getDrawable(getResources(),conversation.getImgID() , null);
        iV.setImageDrawable(d);
        update();
    }

    public void update(){
        Log.d("NPC", currentLink.getOption(0));
        o1.setText(currentLink.getOption(0));
        o2.setText(currentLink.getOption(1));
        o3.setText(currentLink.getOption(2));
        tV.setText(currentLink.getText());
    }

    public void option1(View v){
        currentLink = currentLink.getNext(0);
        if(currentLink.isLast())
            endActivity();
        else
            update();
    }

    public void option2(View v){
        currentLink = currentLink.getNext(1);
        if(currentLink.isLast())
            endActivity();
        else
            update();
    }

    public void option3(View v){
        currentLink = currentLink.getNext(2);
        if(currentLink.isLast())
            endActivity();
        else
            update();
    }

    public void endActivity(){
        if(currentLink.isBattle()){
            possibleBattle = new Battle(player, conversation.getEnemy());
            Intent intent = new Intent(this, BattleActivity.class);
            intent.putExtra("CURRENT_BATTLE", possibleBattle);
            startActivityForResult(intent, BATTLE_KEY);
            finish();
        }
        else if(currentLink.isReward()){
            finish();
        }
        else{
            finish();
        }
    }
}
