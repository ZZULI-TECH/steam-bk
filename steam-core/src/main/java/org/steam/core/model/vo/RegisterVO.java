package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mingshan
 */
@Getter
@Setter
@ToString
public class RegisterVO {
    private String userName;
    private String email;
    private String password;
    private String address;
    private String seccode;
}
