package com.fantasy.rabbitdada.mapper;

import com.fantasy.rabbitdada.model.dto.statistic.AppAnswerCountDTO;
import com.fantasy.rabbitdada.model.dto.statistic.AppAnswerResultCountDTO;
import com.fantasy.rabbitdada.model.entity.UserAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Fantasy
 * @description 针对表【user_answer(用户答题记录)】的数据库操作Mapper
 * @createDate 2025-09-01 14:45:59
 * @Entity com.fantasy.rabbitdada.model.entity.UserAnswer
 */
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {

    @Select("select appId, count(userId) as answerCount from user_answer\n" +
            "    group by appId order by answerCount desc limit 10;")
    List<AppAnswerCountDTO> doAppAnswerCount();


    @Select("select resultName, count(resultName) as resultCount from user_answer\n" +
            "    where appId = #{appId}\n" +
            "    group by resultName order by resultCount desc;")
    List<AppAnswerResultCountDTO> doAppAnswerResultCount(Long appId);
}
