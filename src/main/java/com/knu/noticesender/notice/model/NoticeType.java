package com.knu.noticesender.notice.model;

/**
 * NEW: 알림 발송 대기 공지사항
 * UPDATE: 업데이트된 공지사항
 * OLD: 알림 발송 완료 공지사항
 */
public enum NoticeType implements Convertible {
    NEW("", "발송 대기 공지사항"),
    UPDATE("", "업데이트된 공지사항"),
    OLD("", "발송 완료 공지사항");

    private final String dbData;
    private final String desc;

    NoticeType (String dbData, String desc) {
        this.dbData = dbData;
        this.desc = desc;
    }

    @Override
    public String getDbData() {return dbData;}
    public String getDesc() {return this.desc;}
}
