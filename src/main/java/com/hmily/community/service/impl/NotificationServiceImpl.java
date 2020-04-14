package com.hmily.community.service.impl;

import com.hmily.community.domain.Comment;
import com.hmily.community.domain.Notification;
import com.hmily.community.domain.Question;
import com.hmily.community.dto.NotificationDTO;
import com.hmily.community.dto.PageBean;
import com.hmily.community.enums.NotificationTypeEnum;
import com.hmily.community.mapper.CommentMapper;
import com.hmily.community.mapper.NotificationMapper;
import com.hmily.community.mapper.QuestionMapper;
import com.hmily.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知表(Notification)表服务实现类
 *
 * @author makejava
 * @since 2020-04-13 13:46:01
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Notification queryById(Long id) {
        return this.notificationMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Notification> queryAllByLimit(int offset, int limit) {
        return this.notificationMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param notification 实例对象
     * @return 实例对象
     */
    @Override
    public Notification insert(Notification notification) {
        this.notificationMapper.insert(notification);
        return notification;
    }

    /**
     * 修改数据
     *
     * @param notification 实例对象
     * @return 实例对象
     */
    @Override
    public Notification update(Notification notification) {
        this.notificationMapper.update(notification);
        return this.queryById(notification.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.notificationMapper.deleteById(id) > 0;
    }

    @Override
    public Integer queryUnreadCount(Integer id) {
        return   notificationMapper.queryUnreadCount(id);
    }

    @Override
    public PageBean<NotificationDTO> getNotificationDTOListByUserId(Integer id, Integer page, Integer pageSize) {
        Integer totalCount = notificationMapper.queryAllCount(id);
        PageBean<NotificationDTO> pageBean = new PageBean<>(page, pageSize, totalCount);
        List<NotificationDTO> list = notificationMapper.getNotificationDTOListByUserId(id, pageBean.getOffset(), pageBean.getPageSize());
        for (NotificationDTO notification : list) {
            if(notification.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()){
                //问题回复
                Question question = questionMapper.getQuestionById(notification.getOuterId());
                notification.setQuestion(question);
            }else {
                //评论回复
                Comment comment = commentMapper.selectCommentById(notification.getOuterId());
                notification.setComment(comment);
            }
        }
        pageBean.setList(list);
        return pageBean;

    }
}