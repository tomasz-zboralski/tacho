package com.crud.tacho.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarificResponse {

    @JsonProperty("response")
    Response response;

    @Data
    public static class Response {
        @JsonProperty("holidays")
        private List<HolidayDto> holidayDtoList;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class HolidayDto {

            @JsonProperty("name")
            private String name;

        }
    }
}
