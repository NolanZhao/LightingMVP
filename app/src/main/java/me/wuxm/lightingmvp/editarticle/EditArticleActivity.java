package me.wuxm.lightingmvp.editarticle;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wuxm.lightingmvp.HomeActivity;
import me.wuxm.lightingmvp.R;
import me.wuxm.lightingmvp.utils.ManagerUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.google.common.base.Preconditions.checkNotNull;

public class EditArticleActivity extends AppCompatActivity implements EditArticleContract.View {

    public static final String SDCARD_CACHDIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lightingtemp";

    public static final int TAKE_PICTURE = 91;
    public static final int LOAD_IMAGE = 92;
    public static final int RESULT_OK = -1;
    @Inject
    EditArticlePresenter mEditArticlePresenter;

    @BindView(R.id.et_title)
    EditText et_titile;

    @BindView(R.id.et_desc)
    EditText et_desc;

    @BindView(R.id.toolbar_home)
    Toolbar toolbar;

    @BindView(R.id.tv_publish)
    TextView tv_publish;

    @BindView(R.id.btn_take_photo)
    Button btn_take_photo;

    @BindView(R.id.btn_album)
    Button btn_album;

    @BindView(R.id.llayout_image_container)
    LinearLayout llayout_image_container;

    @BindView(R.id.iv_image)
    ImageView iv_image;

    EditArticleContract.Presenter mPresenter;

    private int article_type;

    private String mUploadImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);
        ButterKnife.bind(this);

        initData();
        initViews();
    }

    private void initData() {
        article_type = getIntent().getFlags() == HomeActivity.TEXT ? HomeActivity.TEXT : HomeActivity.IMAGE;
        DaggerEditArticleComponent.builder().editArticlePresenterModule(new EditArticlePresenterModule(this))
                .build().inject(this);
    }

    private void initViews() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tv_publish.setOnClickListener(view -> {
            if (article_type == HomeActivity.TEXT)
                mPresenter.uploadArticle("aaa", "bbb");
            else
                mPresenter.uploadArticle("aaa", "bbb");
        });

        if (article_type == HomeActivity.TEXT) initText();
        else initImage();
    }


    private void initText() {
        et_desc.setVisibility(View.VISIBLE);
        llayout_image_container.setVisibility(View.GONE);
    }

    private void initImage() {
        et_desc.setVisibility(View.GONE);
        llayout_image_container.setVisibility(View.VISIBLE);
        btn_take_photo.setOnClickListener(view -> takePhotoByCamera());
        btn_album.setOnClickListener(view ->  pickPhotoByAlbum());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setPresenter(EditArticleContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                // 照相
                if (resultCode == RESULT_OK) {
                    if (!TextUtils.isEmpty(mUploadImagePath)) {
                        Glide.with(this).load(mUploadImagePath).into(iv_image);
                    }
                }
                break;
            case LOAD_IMAGE:
                // 图库
                if (resultCode == RESULT_OK && data != null) {
                    Uri pickPhotoUri = data.getData();
                    if (pickPhotoUri != null) {
                        Glide.with(this).load(pickPhotoUri).into(iv_image);
                    }
                }
                break;
        }
    }

    /**
     * 拍照获取图片
     */
    protected void takePhotoByCamera() {
        try {
            Intent openCameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            String SdCardPathDir = SDCARD_CACHDIR;
            if (ManagerUtil.hasSdcard()) {
                // 有sd卡，是否有tempImage文件夹
                File fileDir = new File(SdCardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有Img文件
                mUploadImagePath = SdCardPathDir + System.currentTimeMillis() + ".JPEG";
                Uri photoUri = Uri.fromFile(new File(mUploadImagePath));
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从相册中选取图片
     */
    protected void pickPhotoByAlbum() {
        try {
            Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhotoIntent, LOAD_IMAGE);
        } catch (RuntimeException exception) {
            // 手机没有安装图库应用
            Toast.makeText(this, "手机未安装相册应用", Toast.LENGTH_SHORT).show();
        }
    }
}
