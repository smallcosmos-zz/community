package com.hmily.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageBean<T> {
    /**当前页*/
    private Integer page;
    /**每页数据条数*/
    private Integer pageSize;
    /**存放数数据的集合*/
    private List<T> list = new ArrayList<>();
    /**是否显示上一页按钮*/
    private boolean showPrevious;
    /**页码偏移量*/
    private Integer offset;
    /**数据总条数*/
    private Integer totalCount;
    /**总页数*/
    private Integer totalPage;
    /**页面展示页数*/
    private List<Integer> pages = new ArrayList<>();
    /**是否显示回到第一页按钮*/
    private boolean showFirstPage;
    /**是否显示下一页按钮*/
    private boolean showNext;
    /**是否显示跳转最后一页的按钮*/
    private boolean showEndPage;

    public PageBean(Integer page, Integer pageSize,Integer totalCount){
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        //计算总页数
        this.totalPage = (this.totalCount + this.pageSize -1)/this.pageSize;
        //控制分页的合理性
        if(page < 1){
            this.page = 1;
        }
        if (page > totalPage){
            this.page = totalPage;
        }
        //计算页码的偏移量
        this.offset = (this.page-1) * this.pageSize;
        //判断是否显示上一页按钮
        if(this.page == 1){
            //如果当前页为第一页不显示上一页按钮
            this.showPrevious = false;
        }else {
            //如果当前页不为第一页显示上一页按钮
            this.showPrevious = true;
        }
        //判断是否显示下一页按钮
        if(this.page.equals(this.totalPage)){
            //如果当前页为最后一页不显示下一页按钮
            this.showNext = false;
        }else{
            //如果当前页不为最后一页显示下一页按钮
            this.showNext = true;
        }
        //计算页面展示页数
        this.pages.add(this.page);
        for (int i = 1; i <= 3 ; i++) {
            if(this.page - i > 0){
                this.pages.add(0,this.page -i);
            }
            if(this.page + i <= this.totalPage){
                pages.add(this.page+i);
            }
        }
        //判断是否显示回到第一页的按钮
        if(this.pages.contains(1)){
            this.showFirstPage = false;
        }else{
            this.showFirstPage = true;
        }
        //判断是否显示回到最后一页的按钮
        if(this.pages.contains(this.totalPage)){
            this.showEndPage = false;
        }else{
            this.showEndPage = true;
        }
    }
}
