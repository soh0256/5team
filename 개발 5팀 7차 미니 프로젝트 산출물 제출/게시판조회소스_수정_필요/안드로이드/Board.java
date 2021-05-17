package com.example.manager.model;

/**
 * 게시판 정보 모델
 */
public class Board {
    private int no;
    private String id;
    private String title;
    private String contents;
    private String dates;
    private String hits;
    private String image;
    private String searchCondition;
    private String searchKeyword;

    private boolean isChecked;

    public Board(int no , String id, String title, String contents, String dates, String hits, String profilePicUrl) {
        this.no=no;
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.dates = dates;
        this.hits = hits;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) { this.searchCondition = searchCondition; }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }


}
