package org.example.finaltask.exception;

import org.slf4j.Logger;
import org.example.finaltask.response.JsonResult;
import org.example.finaltask.response.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //当方法传递了非法或不适当的参数时会抛出此异常
    @ExceptionHandler(IllegalArgumentException.class)
    public JsonResult handleIllegalArgumentException(IllegalArgumentException e){
        System.out.println("发生了IllegalArgumentException异常: " + e.getMessage());
        e.printStackTrace();
        return new JsonResult(StatusCode.OPERATION_FAILED, e.getMessage());
    }

    //当应用程序试图在需要对象的地方使用 null 时抛出此异常
    @ExceptionHandler(NullPointerException.class)
    public JsonResult handleNullPointerException(NullPointerException e){
        System.out.println("发生了NullPointerException异常: " + e.getMessage());
        e.printStackTrace();
        return new JsonResult(StatusCode.OPERATION_FAILED, "系统内部错误，请稍后重试");
    }

    //当数组的索引超出了数组的长度时抛出此异常
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public JsonResult handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e){
        System.out.println("发生了ArrayIndexOutOfBoundsException异常: " + e.getMessage());
        e.printStackTrace();
        return new JsonResult(StatusCode.OPERATION_FAILED, "数组越界错误，请检查数组访问");
    }

    //RuntimeException 是所有运行时异常的父类
    @ExceptionHandler(RuntimeException.class)
    public JsonResult handleRuntimeException(RuntimeException e){
        System.out.println("发生了RuntimeException异常: " + e.getMessage());
        e.printStackTrace();
        return new JsonResult(StatusCode.OPERATION_FAILED, "操作失败，请稍后重试");
    }

    //Exception 是所有异常的父类
//    @ExceptionHandler(Exception.class)
//    public JsonResult handleException(Exception e){
//        System.out.println("发生了Exception异常: " + e.getMessage());
//        e.printStackTrace();
//        return new JsonResult(StatusCode.OPERATION_FAILED, "系统错误，请稍后重试");
//    }

    //Throwable 是所有错误和异常的超类
//    @ExceptionHandler(Throwable.class)
//    public JsonResult handleThrowable(Throwable t){
//        System.out.println("程序运行过程中出现错误: " + t.getMessage());
//        t.printStackTrace();
//        return new JsonResult(StatusCode.OPERATION_FAILED, "系统错误，请稍后重试");
//    }

}
