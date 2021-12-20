package work.jimmmy.foodie.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import work.jimmy.foodie.common.utils.JsonResultResponse;

@RestControllerAdvice
public class CustomExceptionHandler {

    // 上传文件超过500k，捕获异常
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JsonResultResponse handleMaxUploadFile(MaxUploadSizeExceededException ex) {
        return JsonResultResponse.errorMsg("文件上传大小不能超过500k，请压缩图片或降低图片质量，再上传。");
    }
}
