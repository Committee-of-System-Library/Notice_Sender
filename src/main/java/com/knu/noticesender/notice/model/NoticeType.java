package com.knu.noticesender.notice.model;

/**
 * Notice 업로드 상태를 저장하는 열거형 클래스
 *
 * Usage) NoticeRecord 생성 시 참고 데이터
 * @see NoticeRecord
 */
public enum NoticeType implements Convertible {
    NEW("0", "알림 레코드 생성 대기 공지사항"),
    OLD("1", "알림 레코드 생성 완료 공지사항"),
    UPDATE("2", "알림 레코드 재생성 대기 공지사항");

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
