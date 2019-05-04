package org.steam.core.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author mingshan
 */
@Getter
@Setter
@ToString
public class UploadImageVO implements Serializable {
    private static final long serialVersionUID = -5692102885015537495L;
    private String url;
}
