package com.hmily.community.service.impl;

import com.hmily.community.domain.Tag;
import com.hmily.community.dto.TagDTO;
import com.hmily.community.mapper.TagMapper;
import com.hmily.community.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Tag)表服务实现类
 *
 * @author makejava
 * @since 2020-04-12 13:38:01
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tag queryById(Long id) {
        return this.tagMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Tag> queryAllByLimit(int offset, int limit) {
        return this.tagMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag insert(Tag tag) {
        this.tagMapper.insert(tag);
        return tag;
    }

    /**
     * 修改数据
     *
     * @param tag 实例对象
     * @return 实例对象
     */
    @Override
    public Tag update(Tag tag) {
        this.tagMapper.update(tag);
        return this.queryById(tag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tagMapper.deleteById(id) > 0;
    }

    @Override
    public List<Tag> queryByCategoryCode(String categoryCode) {
        return tagMapper.queryByCategoryCode(categoryCode);
    }

    @Override
    public Set<String> queryAllCategoryName() {
        List<String> categoryNameList = tagMapper.queryAllCategoryName();
        Set<String> categoryNameSet = new HashSet<>(categoryNameList);
        return categoryNameSet;
    }

    @Override
    public List<TagDTO> queryAll() {
        List<String> categoryNameList = tagMapper.queryAllCategoryName();
        Set<String> categoryNameSet = new HashSet<>(categoryNameList);
        List<TagDTO> tagDTOS = new ArrayList<>();
        Iterator<String> iterator = categoryNameSet.iterator();
        while (iterator.hasNext()) {
            TagDTO tagDTO = new TagDTO();
            String categoryName = iterator.next();
            List<String> tags = tagMapper.queryTagsByCategoryName(categoryName);
            tagDTO.setCategoryName(categoryName);
            tagDTO.setCategoryTags(tags);
            tagDTOS.add(tagDTO);
        }


        return tagDTOS;
    }
}