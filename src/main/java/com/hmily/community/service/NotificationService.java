package com.hmily.community.service;


import com.hmily.community.domain.Notification;
import com.hmily.community.dto.NotificationDTO;
import com.hmily.community.dto.PageBean;

import java.util.List;

/**
 * 通知表(Notification)表服务接口
 *
 * @author makejava
 * @since 2020-04-13 13:46:00
 */
public interface NotificationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notification queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Notification> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param notification 实例对象
     * @return 实例对象
     */
    Notification insert(Notification notification);

    /**
     * 修改数据
     *
     * @param notification 实例对象
     * @return 实例对象
     */
    Notification update(Notification notification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**查询未读消息*/
    Integer queryUnreadCount(Integer id);

    PageBean<NotificationDTO> getNotificationDTOListByUserId(Integer id, Integer page, Integer pageSize);
}