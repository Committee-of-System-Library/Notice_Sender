package com.knu.noticesender.notice.model;


public enum Category implements Convertible {
    ALL("1", "전체"),
    NORMAL("2", "일반"),
    STUDENT("3", "학사"),
    SCHOLARSHIP("4", "장학"),
    SIM_COM("5", "심컴"),
    GL_SOP("6", "글솝"),
    GRADUATE_SCHOOL("7", "대학원"),
    GRADUATE_CONTRACT("8", "대학원 계약학과");

    private final String dbData;
    private final String desc;

    Category (String dbData, String desc) {
        this.dbData = dbData;
        this.desc = desc;
    }

    @Override
    public String getDbData() {return dbData;}
    public String getDesc() {return this.desc;}
}
