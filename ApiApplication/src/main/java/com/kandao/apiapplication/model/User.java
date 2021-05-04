package com.kandao.apiapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kandao.apiapplication.utils.JsonConverter;
import lombok.Data;

@Data
public class User {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("device")
    private String device;

    @JsonProperty("user_rank")
    private String userRank;

    @Override
    public String toString() {
        return JsonConverter.encode(this);
    }
}
