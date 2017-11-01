package utils;

import entry.Music;
import entry.Video;

import java.util.Map;

/**
 * 消息处理类
 * 根据不同的消息类型处理
 * Created by b6762 on 2017/6/14.
 */
class HandleMessagesUtil {
    /**
     * 接收到文本消息后处理
     * @param map 封装了解析结果的Map
     * @return
     */
    static String handleTextMessage(Map<String, String> map) {
        //响应消息
        String responseMessage;
        // 消息内容
        String content = map.get("Content").trim();
        switch (content) {
            case "博客园":
                String msgText = "欢迎朋友们访问我在博客园上面写的博客\n" +
                        "<a href=\"http://www.cnblogs.com/hyyq/\">敲代码的小松鼠的博客</a>";
                responseMessage = BuildResponseMessagesUtil.buildTextMessage(map, msgText);
                break;
            case "图片":
                //通过素材管理接口上传图片时得到的media_id
                String imgMediaId = "kQUS8tNnWA_7dX48D-4VBzxHU6wmCNZYPWI0HT9imVWOnaUpQgOeNNFLtVKA3NnC";
                responseMessage = BuildResponseMessagesUtil.buildImageMessage(map, imgMediaId);
                break;
            case "语音":
                //通过素材管理接口上传语音文件时得到的media_id
                String voiceMediaId = "F9u5ymVfal_vvr67zOODCfQKFcdr1RKFBGP-PmX3RPI3Ukn41nGaMSO3LnKTh-a7";
                responseMessage = BuildResponseMessagesUtil.buildVoiceMessage(map,voiceMediaId);
                break;
            case "图文":
                responseMessage = BuildResponseMessagesUtil.buildNewsMessage(map);
                break;
            case "音乐":
                Music music = new Music();
                music.title = "赵丽颖、许志安 - 乱世俱灭";
                music.description = "电视剧《蜀山战纪》插曲";
                music.musicUrl = "http://ii9eu8.natappfree.cc/media/music/music.mp3";
                music.hqMusicUrl = "http://ii9eu8.natappfree.cc/media/music/music.mp3";
                responseMessage = BuildResponseMessagesUtil.buildMusicMessage(map, music);
                break;
            case "视频":
                Video video = new Video();
                video.mediaId = "WdCwqe_R98ueL9chPFGeVEHDXg3pSv276VliNjtPbAqaxGz_RnhT4NXERDk06dfx";
                video.title = "小苹果";
                video.description = "小苹果搞笑视频";
                responseMessage = BuildResponseMessagesUtil.buildVideoMessage(map, video);
                break;
            default:
                responseMessage = BuildResponseMessagesUtil.buildWelcomeTextMessage(map);
                break;

        }
        //返回响应消息
        return responseMessage;
    }

    /**
     * 处理接收到图片消息
     *
     * @param map
     * @return
     */
    static String handleImageMessage(Map<String, String> map) {
        String picUrl = map.get("PicUrl");
        String mediaId = map.get("MediaId");
        System.out.print("picUrl:" + picUrl);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("已收到您发来的图片，图片Url为：%s\n图片素材Id为：%s", picUrl, mediaId);
        return BuildResponseMessagesUtil.buildTextMessage(map, result);
    }

    /**
     * 处理接收到语音消息
     * @param map
     * @return
     */
    static String handleVoiceMessage(Map<String, String> map) {
        String format = map.get("Format");
        String mediaId = map.get("MediaId");
        System.out.print("format:" + format);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("已收到您发来的语音，语音格式为：%s\n语音素材Id为：%s", format, mediaId);
        return BuildResponseMessagesUtil.buildTextMessage(map, result);
    }

    /**
     * 处理接收到的视频消息
     * @param map
     * @return
     */
     static String handleVideoMessage(Map<String, String> map) {
        String thumbMediaId = map.get("ThumbMediaId");
        String mediaId = map.get("MediaId");
        System.out.print("thumbMediaId:" + thumbMediaId);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("已收到您发来的视频，视频中的素材ID为：%s\n视频Id为：%s", thumbMediaId, mediaId);
        return BuildResponseMessagesUtil.buildTextMessage(map, result);
    }

    /**
     * 处理接收到的小视频消息
     * @param map
     * @return
     */
     static String handleSmallVideoMessage(Map<String, String> map) {
        String thumbMediaId = map.get("ThumbMediaId");
        String mediaId = map.get("MediaId");
        System.out.print("thumbMediaId:" + thumbMediaId);
        System.out.print("mediaId:" + mediaId);
        String result = String.format("已收到您发来的小视频，小视频中素材ID为：%s,\n小视频Id为：%s", thumbMediaId, mediaId);
        return BuildResponseMessagesUtil.buildTextMessage(map, result);
    }

    /**
     * 处理接收到的地理位置消息
     * @param map
     * @return
     */
     static String handleLocationMessage(Map<String, String> map) {
        String latitude = map.get("Location_X");  //纬度
        String longitude = map.get("Location_Y");  //经度
        String label = map.get("Label");  //地理位置精度
        String result = String.format("纬度：%s\n经度：%s\n地理位置：%s", latitude, longitude, label);
        return BuildResponseMessagesUtil.buildTextMessage(map, result);
    }

    /**
     * 处理接收到的链接消息
     * @param map
     * @return
     */
     static String handleLinkMessage(Map<String, String> map) {
        String title = map.get("Title");
        String description = map.get("Description");
        String url = map.get("Url");
        String result = String.format("已收到您发来的链接，链接标题为：%s,\n描述为：%s\n,链接地址为：%s", title, description, url);
        return BuildResponseMessagesUtil.buildTextMessage(map, result);
    }

    /**
     * 处理消息Message
     * @param map 封装了解析结果的Map
     * @return
     */
     static String handleEventMessage(Map<String, String> map) {
        String responseMessage = BuildResponseMessagesUtil.buildWelcomeTextMessage(map);
        return responseMessage;
    }

}
