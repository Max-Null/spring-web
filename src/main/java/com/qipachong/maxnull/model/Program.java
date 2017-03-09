package com.qipachong.maxnull.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//@Table(name = "tb_program")
public class Program implements Serializable {

    public static final int MATCH_CRACK_FAILED = 2;
    public static final int MATCH_DOWNLOADED = 4;
    public static final int MATCH_DOWNLOADING = 1;
    public static final int MATCH_DOWNLOAD_FAILED = 5;
    public static final int MATCH_INIT = -1;
    public static final int MATCH_SUCCEED = 8;
    public static final int MATCH_FAILED = 7;

    /**
     * 唯一标识，主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //节目名
    private String pname;
    //内容类型
    private String ctype;
    @Column(name = "pub_date")
    private String pubDate;
    //地区
    private String countrys;
    //介绍
    private String intro;
    //导演
    private String director;
    //演员
    private String actors;
    //总集数
    @Column(name = "total_sets")
    private Integer totalSets;

    @Column(name = "main_id")
    private String mainId;

    //网站名字
    @Column(name = "website_id")
    private Integer websiteId;
    //当前时间
    @Column(name = "get_time")
    private Date getTime;
    @Column(name = "pc_url")
    private String pcUrl;

    private String website;
    //节目类型
    private String ptype;
    @Column(name = "play_length")
    //时长
    private Integer playLength;
    @Column(name = "is_charge")
    //是否收费
    private Integer isCharge;
    //是否是预告片
    @Column(name = "is_preview")
    private Integer isPreview;

    @Column(name = "is_matched")
    private Integer isMatched;


    @Column(name = "extra_info")
    private Integer extraInfo;











    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(Integer isCharge) {
        this.isCharge = isCharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPcUrl() {
        return pcUrl;
    }

    public void setPcUrl(String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCountrys() {
        return countrys;
    }

    public void setCountrys(String countrys) {
        this.countrys = countrys;
    }


    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Integer getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(Integer totalSets) {
        this.totalSets = totalSets;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public Integer getPlayLength() {
        return playLength;
    }

    public void setPlayLength(Integer playLength) {
        this.playLength = playLength;
    }

    public Integer getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(Integer isPreview) {
        this.isPreview = isPreview;
    }

    public Integer getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Integer extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Integer getIsMatched() {
        return isMatched;
    }

    public void setIsMatched(Integer isMatched) {
        this.isMatched = isMatched;
    }


    @Transient
    private String keyWords;

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getKeyWords() {
        return keyWords;
    }



    @Transient
    private String title;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    @Transient
    private String searchMethod;
    public void setSearchMethod(String searchMethod) {
        this.searchMethod = searchMethod;
    }
    public String getSearchMethod() {
        return searchMethod;
    }

    @Transient
    private String workName;
    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    @Transient
    private Integer workId;
    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }





}