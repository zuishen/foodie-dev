package work.jimmy.foodie.common.enums;

/**
 * 性别枚举
 */
public enum Gender {
    FEMALE(0, "女"),

    MALE(1, "男"),

    SECRET(2, "保密");

    public final int type;

    public final String value;

    Gender(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
