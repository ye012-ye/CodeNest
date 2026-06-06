package com.github.paicoding.forum.api.model.vo.user.wx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 简单文本请求
 *
 * @author yihui
 * @link <a href="https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_standard_messages.html"/>
 * @date 2022/6/20
 */
@Data
@JacksonXmlRootElement(localName = "xml")
public class WxTxtMsgReqVo {
    /**
     * 接收方帐号（收到的OpenID）
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;
    /**
     * 发送方帐号（开发者微信号）
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;
    /**
     * 消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    private Long createTime;
    /**
     * 消息类型
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;
    /**
     * 事件类型
     */
    @JacksonXmlProperty(localName = "Event")
    private String event;
    /**
     * 事件KEY值
     */
    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @JacksonXmlProperty(localName = "Ticket")
    private String ticket;
    /**
     * 文本消息内容
     */
    @JacksonXmlProperty(localName = "Content")
    private String content;
    /**
     * 消息id，64位整型
     */
    @JacksonXmlProperty(localName = "MsgId")
    private String msgId;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @JacksonXmlProperty(localName = "MsgDataId")
    private String msgDataId;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     */
    @JacksonXmlProperty(localName = "Idx")
    private String idx;
}
