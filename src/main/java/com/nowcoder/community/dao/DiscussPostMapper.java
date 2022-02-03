package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    // offset 每一页起始行号 limit是每一页最多放多少行
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    // @Param注解是用于给参数起个别名
    //如果只有一个参数，并且在<if>中使用，就必须使用别名
    int selectDiscussPostRows(@Param("userId") int userId);


}
