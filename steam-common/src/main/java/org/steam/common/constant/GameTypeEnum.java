package org.steam.common.constant;

/**
 * 游戏类型枚举类
 */
public enum GameTypeEnum {
    /**
     * 数字游戏
     */
    DIGITAL(1,"数字游戏"),
    /**
     * 实体游戏
     */
    COMMON(2,"实体游戏");

    private Integer code;
    private String desc;

    GameTypeEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }

}
