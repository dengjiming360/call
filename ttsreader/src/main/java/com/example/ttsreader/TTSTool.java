package com.example.ttsreader;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;

import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechSynthesizer;
import com.unisound.client.SpeechSynthesizerListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;


public class TTSTool {
    private static final String TAG = "TTSTool";
    private static volatile TTSTool instance = null;
    private boolean isInitSuccess = false;
    private SpeechSynthesizer mTTSPlayer;
    private static String SAMPLE_DIR  = "";
    private static final String FRONTEND_MODEL = "frontend_model";
    private static final String BACKEND_MODEL = "backend_lzl";
    private static final String FOLDER="OfflineTTSModels/";
    private static final String APPKEY = Config.appKey;
    private static final String SECRET = Config.secret;
    Context context;
    public TTSTool(Context context) {
        this.context=context;
        File file=new File(context.getExternalFilesDir(null).toString()+"/assets/");
        if(!file.exists()){
            file.mkdirs();
        }
        SAMPLE_DIR=context.getExternalFilesDir(null).toString()+"/assets/";
        File file2=new File(SAMPLE_DIR+FOLDER);
        if(!file2.exists()){
            file2.mkdirs();
        }
    }


    public void init(Context  context) {
        SharedPreferences sharedPreferences=context.getSharedPreferences("jingwei",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        mTTSPlayer = new SpeechSynthesizer(context, APPKEY, SECRET);

        // 设置本地合成
        mTTSPlayer.setOption(SpeechConstants.TTS_SERVICE_MODE, SpeechConstants.TTS_SERVICE_MODE_LOCAL);
        File _FrontendModelFile = new File(SAMPLE_DIR +FOLDER+ FRONTEND_MODEL);
        if (!_FrontendModelFile.exists()) {
            copyFilesFassets(context, FOLDER+FRONTEND_MODEL, SAMPLE_DIR+FOLDER + FRONTEND_MODEL);
        }
        File _BackendModelFile = new File(SAMPLE_DIR+FOLDER + BACKEND_MODEL);
        if (!_BackendModelFile.exists()) {
            copyFilesFassets(context, FOLDER+BACKEND_MODEL, SAMPLE_DIR +FOLDER+ BACKEND_MODEL);
        }
        // 设置前端模型
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_FRONTEND_MODEL_PATH, SAMPLE_DIR + FOLDER+FRONTEND_MODEL);
        // 设置后端模型
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_BACKEND_MODEL_PATH, SAMPLE_DIR+FOLDER+  BACKEND_MODEL);
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_NAME,"xiaoli");



        // 设置合成语速
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_SPEED, 50);
        // 设置合成音高
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_PITCH, 50);
        // 设置合成音量
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_VOLUME, 100);
        // 设置回调监听
        mTTSPlayer.setTTSListener(new SpeechSynthesizerListener() {
            @Override
            public void onEvent(int i) {
                switch (i) {
                    case SpeechConstants.TTS_EVENT_INIT:
                        isInitSuccess = true;
                        break;
                    case SpeechConstants.TTS_EVENT_SYNTHESIZER_START:
                        // 开始合成回调
                        Log.i(TAG, "beginSynthesizer");
                        break;
                    case SpeechConstants.TTS_EVENT_SYNTHESIZER_END:
                        // 合成结束回调
                        Log.i(TAG, "endSynthesizer");
                        break;
                    case SpeechConstants.TTS_EVENT_BUFFER_BEGIN:
                        // 开始缓存回调
                        Log.i(TAG, "beginBuffer");
                        break;
                    case SpeechConstants.TTS_EVENT_BUFFER_READY:
                        // 缓存完毕回调
                        Log.i(TAG, "bufferReady");
                        break;
                    case SpeechConstants.TTS_EVENT_PLAYING_START:
                        // 开始播放回调
                        Log.i(TAG, "onPlayBegin");
                        editor.putBoolean("so",true);
                        editor.putBoolean("su",false);
                        editor.commit();
                        break;
                    case SpeechConstants.TTS_EVENT_PLAYING_END:
                        // 播放完成回调
                        Log.i(TAG, "onPlayEnd");
                        editor.putBoolean("su",true);
                        editor.putBoolean("so",false);
                        editor.putString("once","once");
                        editor.commit();
                        break;
                    case SpeechConstants.TTS_EVENT_PAUSE:
                        // 暂停回调
                        Log.i(TAG, "pause");
                        break;
                    case SpeechConstants.TTS_EVENT_RESUME:
                        // 恢复回调
                        Log.i(TAG, "resume");
                        break;
                    case SpeechConstants.TTS_EVENT_STOP:
                        // 停止回调
                        Log.i(TAG, "stop");
                        break;
                    case SpeechConstants.TTS_EVENT_RELEASE:
                        // 释放资源回调
                        Log.i(TAG, "release");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        // 初始化合成引擎
        mTTSPlayer.init("");
    }

    public void speak(String msg,Context context) {
        if (isInitSuccess) {
            mTTSPlayer.playText(msg);
        }else {
            init(context);
        }
    }

    public void stop() {
        mTTSPlayer.stop();
    }

    public void pause() {
        mTTSPlayer.pause();
    }

    public void resume() {
        mTTSPlayer.resume();
    }

    public void release() {
        if (null != mTTSPlayer) {
            // 释放离线引擎
            mTTSPlayer.release(SpeechConstants.TTS_RELEASE_ENGINE, null);
        }
    }
    public void syn(String msg){
        mTTSPlayer.synthesizeText(msg);
    }

    public void copyFilesFassets(Context context, String oldPath, String newPath) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);

            if (fileNames.length > 0) {
                File file = new File(newPath);
                file.mkdirs();
                for (String fileName : fileNames) {
                    copyFilesFassets(context,oldPath + "/" + fileName,newPath+"/"+fileName);
                }
            } else {
                if(new File(newPath).exists()){
                    return;
                }
                InputStream is = context.getAssets().open(oldPath);

                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount=0;
                while((byteCount=is.read(buffer))!=-1) {
                    fos.write(buffer, 0, byteCount);

                }
                fos.flush();
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}

