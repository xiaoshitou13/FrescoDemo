package test.bwie.com.frescodemo;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView mSimple;
    private SimpleDraweeView mSimple2;
    /**
     * Button
     */
    private Button mButton;

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSimple = (SimpleDraweeView) findViewById(R.id.simple);

        mSimple2 = (SimpleDraweeView) findViewById(R.id.jianjing);

         String str3_progressive = "http://pooyak.com/p/progjpeg/jpegload.cgi?o=1";
        String url ="http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg";
        final Uri uri =  Uri.parse(url);
        Uri uris = Uri.parse(str3_progressive);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());

//        GenericDraweeHierarchy hierarchy = builder
//                .setProgressBarImage(R.mipmap.www)
//               .build();



            //渐进式
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uris)
                .setProgressiveRenderingEnabled(true)
                .build();


        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(mSimple2.getController())
                .setControllerListener(new ControllerListener<ImageInfo>() {
                    @Override
                    public void onSubmit(String id, Object callerContext) {

                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        Toast.makeText(MainActivity.this, "加载成功了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                        Toast.makeText(MainActivity.this, "加载过程中失败了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIntermediateImageFailed(String id, Throwable throwable) {

                    }

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "加载失败了", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onRelease(String id) {

                    }
                })
                .build();





        DraweeController controller = Fresco.newDraweeControllerBuilder()

                .setUri(uri)           //设置原来  地址
                .setTapToRetryEnabled(true)   //是否开启 重新加载
                .setOldController(mSimple.getController()).build();  //构建一下就好
        mSimple.setImageURI(uri);
        mSimple.setController(controller);  //摄入   app:retryImage="@mipmap/cj" app:retryImageScaleType="centerCrop"
        mSimple2.setController(controller2);
    }

  //  http://blog.csdn.net/y1scp/article/details/49245535  网址在此  加载图片

  //  http://blog.csdn.net/teamomylife/article/details/53908068  渐进 图片加载


  //  http://www.cnblogs.com/liushilin/p/5659301.html  监听事件

}
