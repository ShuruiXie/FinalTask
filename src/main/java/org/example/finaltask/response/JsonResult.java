package org.example.finaltask.response;

public class JsonResult {
    private int code;
    private String message;
    private Object data;

    public JsonResult(int code, String message){
        this.code = code;
        this.message = message;
    }

    public JsonResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public JsonResult(StatusCode statusCode,Object data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    //成功的情况下
    private JsonResult(Object data){
        this.code = StatusCode.SUCCESS.getCode();
        this.message = StatusCode.SUCCESS.getMessage();
        this.data = data;
    }

    public static JsonResult ok(Object data){
        return new JsonResult(data);
    }

    private JsonResult(){
        this.code = StatusCode.SUCCESS.getCode();
        this.message = StatusCode.SUCCESS.getMessage();
    }

    public static JsonResult ok(){
        return new JsonResult();
    }

//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
}
