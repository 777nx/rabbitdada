package com.fantasy.rabbitdada.controller;

import com.fantasy.rabbitdada.common.BaseResponse;
import com.fantasy.rabbitdada.common.ErrorCode;
import com.fantasy.rabbitdada.common.ResultUtils;
import com.fantasy.rabbitdada.exception.ThrowUtils;
import com.fantasy.rabbitdada.mapper.UserAnswerMapper;
import com.fantasy.rabbitdada.model.dto.statistic.AppAnswerCountDTO;
import com.fantasy.rabbitdada.model.dto.statistic.AppAnswerResultCountDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * App 统计分析接口
 */
@RestController
@RequestMapping("/app/statistic")
public class AppStatisticController {

    @Resource
    private UserAnswerMapper userAnswerMapper;

    /**
     * 热门应用及回答数统计（top 10）
     *
     * @return
     */
    @GetMapping("/answer_count")
    public BaseResponse<List<AppAnswerCountDTO>> getAppAnswerCount() {
        return ResultUtils.success(userAnswerMapper.doAppAnswerCount());
    }

    /**
     * 某应用回答结果分布统计
     *
     * @param appId
     * @return
     */
    @GetMapping("/answer_result_count")
    public BaseResponse<List<AppAnswerResultCountDTO>> getAppAnswerResultCount(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userAnswerMapper.doAppAnswerResultCount(appId));
    }
}
