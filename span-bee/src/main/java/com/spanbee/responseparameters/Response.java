package com.spanbee.responseparameters;

/**
 * @author sucheth.s
 *
 */

public class Response {

  
    
     private String status;
    
    private String code;
    
    private String errorNodeType;
    
    private String error_id;
    
    private String message;
    
    private String description;
    
    private String sessionId;
    
    private String nextPage;
    
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorNodeType() {
        return errorNodeType;
    }

    public void setErrorNodeType(String errorNodeType) {
        this.errorNodeType = errorNodeType;
    }

    public String getError_id() {
        return error_id;
    }

    public void setError_id(String error_id) {
        this.error_id = error_id;
    }

    public String getSessionId() {
      return sessionId;
    }

    public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
    }

    public String getNextPage() {
      return nextPage;
    }

    public void setNextPage(String nextPage) {
      this.nextPage = nextPage;
    }
}



