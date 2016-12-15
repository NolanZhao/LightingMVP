package me.wuxm.lightingmvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wuxm.lightingmvp.editarticle.EditArticleActivity;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bmb)
    BoomMenuButton mBoomMenuButton;

    public static final int TEXT = 0;
    public static final int IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBoomMenuButton();


    }

    private void initBoomMenuButton() {
        mBoomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.HAM_2);
        mBoomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.HAM_2);
        mBoomMenuButton.setBottomHamButtonTopMargin(Util.dp2px(50));

        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(R.mipmap.ic_launcher)
                .normalText("文本")
                .textSize(20)
                .containsSubText(true)
                .listener(index ->
                       startActivity(new Intent().setClass(HomeActivity.this, EditArticleActivity.class).setFlags(TEXT))
                );
        mBoomMenuButton.addBuilder(builder);

        HamButton.Builder builder1 = new HamButton.Builder()
                .normalImageRes(R.mipmap.ic_launcher)
                .normalText("图文")
                .textSize(20)
                .containsSubText(true)
                .listener(index ->
                        startActivity(new Intent().setClass(HomeActivity.this, EditArticleActivity.class).setFlags(IMAGE))
                );
        mBoomMenuButton.addBuilder(builder1);

    }

}
