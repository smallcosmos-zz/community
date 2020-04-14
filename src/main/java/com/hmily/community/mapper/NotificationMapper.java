package com.hmily.community.mapper;

import com.hmily.community.domain.Notification;
import com.hmily.community.dto.NotificationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 通知表(Notification)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-13 13:45:59
 */
@Mapper
public interface NotificationMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Notification queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Notification> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param notification 实例对象
     * @return 对象列表
     */
    List<Notification> queryAll(Notification notification);

    /**
     * 新增数据
     *
     * @param notification 实例对象
     * @return 影响行数
     */
    int insert(Notification notification);

    /**
     * 修改数据
     *
     * @param notification 实例对象
     * @return 影响行数
     */
    int update(Notification notification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    Integer queryUnreadCount(Integer id);

    Integer getTotalCountByUserId(Integer id);

    List<NotificationDTO> getNotificationDTOListByUserId(Integer id, Integer offset, Integer pageSize);

    Integer queryAllCount(Integer id);
}