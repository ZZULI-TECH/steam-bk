package org.steam.core.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author mingshan
 */
@Data
public class LoginDTO implements Serializable {
    private String email;
    private String password;
}
