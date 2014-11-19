package org.bnu.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by xie on 14-11-8.
 */
public class JsonResponse {
    @Getter@Setter
    private String status;
    @Getter@Setter
    private String errorMessage;

    public JsonResponse(String status,String errorMessage){
        this.status=status;
        this.errorMessage=errorMessage;
    }
}
