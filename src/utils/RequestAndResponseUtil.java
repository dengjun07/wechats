package utils;

import common.MessageType;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号消息推送、回复，菜单栏创建
 */

/**
 * 消息处理工具类
 * 接收和
 * 返回
 */
public class RequestAndResponseUtil {


    /**
     * 解析微信发来的请求（XML）
     *
     * @param request 封装了请求信息的HttpServletRequest对象
     * @return map 解析结果
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        System.out.println("正在解析微信传过来的xml");
        for (Element e : elementList) {
            System.out.println(e.getName() + "|" + e.getText());
            map.put(e.getName(), e.getText());
        }
        System.out.println("微信传过来的xml解析完毕");

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 根据消息类型构造返回消息
     *
     * @param map 封装了解析结果的Map
     * @return responseMessage(响应消息)
     */
    public static String buildResponseMessage(Map map) {
        //响应消息
        String responseMessage = "";
        //得到消息类型
        String msgType = map.get("MsgType").toString();
        System.out.println("MsgType:" + msgType);
        //消息类型
        MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());
        switch (messageEnumType) {
            case TEXT:
                //处理文本消息
                responseMessage = HandleMessagesUtil.handleTextMessage(map);
                break;
            case IMAGE:
                //处理图片消息
                responseMessage = HandleMessagesUtil.handleImageMessage(map);
                break;
            case VOICE:
                //处理语音消息
                responseMessage = HandleMessagesUtil.handleVoiceMessage(map);
                break;
            case VIDEO:
                //处理视频消息
                responseMessage = HandleMessagesUtil.handleVideoMessage(map);
                break;
            case SHORTVIDEO:
                //处理小视频消息
                responseMessage = HandleMessagesUtil.handleSmallVideoMessage(map);
                break;
            case LOCATION:
                //处理位置消息
                responseMessage = HandleMessagesUtil.handleLocationMessage(map);
                break;
            case LINK:
                //处理链接消息
                responseMessage = HandleMessagesUtil.handleLinkMessage(map);
                break;
            case EVENT:
                //处理事件消息,用户在关注与取消关注公众号时，微信会向我们的公众号服务器发送事件消息,开发者接收到事件消息后就可以给用户下发欢迎消息
                responseMessage = HandleMessagesUtil.handleEventMessage(map);
            default:
                break;
        }
        //返回响应消息
        return responseMessage;
    }

}

