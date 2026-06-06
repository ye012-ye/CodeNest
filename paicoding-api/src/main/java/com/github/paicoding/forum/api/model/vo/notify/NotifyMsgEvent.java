package com.github.paicoding.forum.api.model.vo.notify;

import com.github.paicoding.forum.api.model.enums.NotifyTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author YiHui
 * @date 2022/9/3
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
/**
 * 站内消息事件
 * ApplicationEvent 是Spring 框架提供的一个事件类，用于封装事件信息。
 */
public class NotifyMsgEvent<T> extends ApplicationEvent {

    private NotifyTypeEnum notifyType;

    private T content;


    public NotifyMsgEvent(Object source, NotifyTypeEnum notifyType, T content) {
        super(source);
        this.notifyType = notifyType;
        this.content = content;
    }


}
