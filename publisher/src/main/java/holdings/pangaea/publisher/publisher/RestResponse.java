/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holdings.pangaea.publisher.publisher;

/**
 *
 * @author timot
 */
class RestResponse {
    
    private String message;
    
    private int status;

    public RestResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }    
    
}
