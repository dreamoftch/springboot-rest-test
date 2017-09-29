package com.tch.test.springboot.exception

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


/**
 * Created by higgs on 2017/9/29.
 */
@ControllerAdvice
class GlobalControllerExceptionHandler {

    private val LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun handleException(req: HttpServletRequest, e: Exception): GlobalException {
        LOGGER.error("发生异常:", e)
        return GlobalException("服务器内部异常")
    }

}