package com.go2reach.sample.ads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.go2reach.sample.R;
import com.reach.AdServiceManager;
import com.reach.IAd;
import com.reach.IAdItem;
import com.reach.IAdService;
import com.reach.ICallback;
import com.reach.IMethod;
import com.reach.INativeAd;
import com.reach.IServiceCallback;
import com.reach.ServicesManager;

import java.util.Map;

public class NativeAdBeforeVideoActivity extends Activity {

    FrameLayout frameLayout;
    INativeAd ad;
    ProgressDialog dialog;
    View adView;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad_before_video);

        ImageView iv_like = (ImageView) findViewById(R.id.ic_like);
        ImageView iv_dislike = (ImageView) findViewById(R.id.ic_dislike);
        final TextView tv_like = (TextView) findViewById(R.id.tv_like);
        final TextView tv_dislike = (TextView) findViewById(R.id.tv_dislike);

        iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt((String) tv_like.getText());
                int now = num+1;
                tv_like.setText(""+now);
            }
        });

        iv_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt((String) tv_dislike.getText());
                int now = num+1;
                tv_dislike.setText(""+now);
            }
        });

        frameLayout = (FrameLayout) findViewById(R.id.container_video);
        frameLayout.setVisibility(View.VISIBLE);
        dialog = new ProgressDialog(NativeAdBeforeVideoActivity.this);
        dialog.setMessage("Waiting for video ad...");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();

        videoView = (VideoView) findViewById(R.id.video);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.pv_go2reach));

        AdServiceManager.get(getApplicationContext(), new IServiceCallback<IAdService>() {
            @Override
            public void call(IAdService service) {
                ad = service.getNativeAd("native.ad.media.video", -1, 250, 3, new String[]{IAdItem.VIDEO,"@.vps.show.countdown=true","@.vps.show.progress=false","@.vps.show.cover=false"});
                ad.setAutoplayMode(true);
                ad.setOnLoadLisenter(new ICallback() {
                    @Override
                    public void call(int i) {
                        dialog.dismiss();
                        if(i == IAd.OK && ad.getCount() > 0){
                            adView = getLayoutInflater().inflate(R.layout.item_video_ad_before, null);
                            ad.getAdItem(0).bind(adView,
                                    new String[]{IAdItem.MEDIA_CONTAINER},
                                    new int[]{R.id.flContainer}
                            );
                            frameLayout.addView(adView);
                        }else{
                            videoView.setVisibility(View.VISIBLE);
                            videoView.start();
                        }
                    }
                });
                ServicesManager.hook("callback://video.progress/native.ad.media.video", new IMethod() {
                    @Override
                    public Object run(Map<String, ?> params) {
                        int per = 0;
                        if (params != null){
                            Object p = params.get("percent");
                            per = (int) p;
                        }
                        if (per == 100){
                            frameLayout.removeView(adView);
                            videoView.setVisibility(View.VISIBLE);
                            videoView.start();
                        }
                        return null;
                    }
                });
                ad.load();
            }
        });
    }
}
