package com.project.serenity_mental_center.util;

import com.project.serenity_mental_center.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Util {
    private static Util instance;
    private UserDto userDto;

    private Util() {}

    public static synchronized Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
}
