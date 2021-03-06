package com.cgg.pub.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Movie implements Cloneable, Serializable {
    public static final String Table = "t_movie";
    public static final String Alias = "as_t_movie";
    private static final long serialVersionUID = 1L;
    public static final String DBStrMovieid = "as_t_movie.MovieId";
    public static final String DBStrEnname = "as_t_movie.EnName";
    public static final String DBStrCnname = "as_t_movie.CnName";
    public static final String DBStrTypecode = "as_t_movie.TypeCode";
    public static final String DBStrDirector = "as_t_movie.Director";
    public static final String DBStrActors = "as_t_movie.Actors";
    public static final String DBStrScenarist = "as_t_movie.Scenarist";
    public static final String DBStrProducer = "as_t_movie.Producer";
    public static final String DBStrSmallposter = "as_t_movie.SmallPoster";
    public static final String DBStrBigposter = "as_t_movie.BigPoster";
    public static final String DBStrAreacode = "as_t_movie.AreaCode";
    public static final String DBStrLangcode = "as_t_movie.LangCode";
    public static final String DBStrDuration = "as_t_movie.Duration";
    public static final String DBStrIntroduction = "as_t_movie.Introduction";
    public static final String DBStrKeywords = "as_t_movie.KeyWords";
    public static final String DBStrCommentscore = "as_t_movie.CommentScore";
    public static final String DBStrProvider = "as_t_movie.Provider";
    public static final String DBStrDescription = "as_t_movie.Description";
    public static final String DBStrActive = "as_t_movie.Active";
    public static final String DBStrCreator = "as_t_movie.Creator";
    public static final String DBStrCreatetime = "as_t_movie.CreateTime";
    public static final String DBStrModifier = "as_t_movie.Modifier";
    public static final String DBStrModifytime = "as_t_movie.ModifyTime";
    public static final String DBStrTypename = "as_t_movie.TypeName";
    public static final String DBStrAreaname = "as_t_movie.AreaName";
    public static final String DBStrLangname = "as_t_movie.LangName";
    public static final String DBStrLicenseprovider = "as_t_movie.LicenseProvider";
    public static final String DBStrTagscodes = "as_t_movie.TagsCodes";
    public static final String DBStrThreesource = "as_t_movie.ThreeSource";
    public static final String DBStrCinemashowndate = "as_t_movie.CinemaShownDate";
    public static final String DBStrAdviewtime = "as_t_movie.AdViewTime";
    public static final String DBStrType = "as_t_movie.TYPE";
    public static final String DBStrReporturl = "as_t_movie.reportUrl";
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.MovieId
     * Remark - 电影ID
     * DBType - INTEGER
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Integer movieid;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.EnName
     * Remark - 电影中文名称
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String enname;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.CnName
     * Remark - 电影英文名称
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String cnname;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.TypeCode
     * Remark - 电影分类编码
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String typecode;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Director
     * Remark - 导演
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String director;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Actors
     * Remark - 主演
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String actors;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Scenarist
     * Remark - 编剧
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String scenarist;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Producer
     * Remark - 制片人
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String producer;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.SmallPoster
     * Remark - 小海报封面
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String smallposter;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.BigPoster
     * Remark - 大海报封面
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String bigposter;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.AreaCode
     * Remark - 片源所在地区编码
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String areacode;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.LangCode
     * Remark - 片源语言编码
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String langcode;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Duration
     * Remark - 电影时长
     * DBType - TIME
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Date duration;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Introduction
     * Remark - 电影简介
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String introduction;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.KeyWords
     * Remark - 电影关键字
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String keywords;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.CommentScore
     * Remark - 评分
     * DBType - DECIMAL
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private BigDecimal commentscore;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Provider
     * Remark - 电影提供商
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String provider;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Description
     * Remark - 影片描述
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String description;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Active
     * Remark - 是否有效，用于假删除数据
     * DBType - INTEGER
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Integer active;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Creator
     * Remark -  *NO SETTING* 
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String creator;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.CreateTime
     * Remark -  *NO SETTING* 
     * DBType - TIMESTAMP
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Date createtime;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.Modifier
     * Remark -  *NO SETTING* 
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String modifier;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.ModifyTime
     * Remark -  *NO SETTING* 
     * DBType - TIMESTAMP
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Date modifytime;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.TypeName
     * Remark - 电影分类名称
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String typename;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.AreaName
     * Remark - 区域名称
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String areaname;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.LangName
     * Remark - 语言名称
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String langname;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.LicenseProvider
     * Remark - 内容牌照商ID
     * DBType - INTEGER
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Integer licenseprovider;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.TagsCodes
     * Remark - 电影标签编码，多个以逗号分割
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String tagscodes;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.ThreeSource
     * Remark -  *NO SETTING* 
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String threesource;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.CinemaShownDate
     * Remark - 影院上映日期
     * DBType - TIMESTAMP
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Date cinemashowndate;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.AdViewTime
     * Remark - 后贴广告时间
     * DBType - TIME
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Date adviewtime;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.TYPE
     * Remark - 是否广告 0：否 1：是
     * DBType - INTEGER
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private Integer type;
    /**
     * This field was generated by THB Generator.
     * This field corresponds to the database column t_movie.reportUrl
     * Remark - 上报地址
     * DBType - VARCHAR
     *
     * @mbggenerated Tue May 09 14:29:33 GMT+08:00 2017
     */
    private String reporturl;

    public Movie() {
        super();
    }

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname == null ? null : cnname.trim();
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director == null ? null : director.trim();
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors == null ? null : actors.trim();
    }

    public String getScenarist() {
        return scenarist;
    }

    public void setScenarist(String scenarist) {
        this.scenarist = scenarist == null ? null : scenarist.trim();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    public String getSmallposter() {
        return smallposter;
    }

    public void setSmallposter(String smallposter) {
        this.smallposter = smallposter == null ? null : smallposter.trim();
    }

    public String getBigposter() {
        return bigposter;
    }

    public void setBigposter(String bigposter) {
        this.bigposter = bigposter == null ? null : bigposter.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode == null ? null : langcode.trim();
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public BigDecimal getCommentscore() {
        return commentscore;
    }

    public void setCommentscore(BigDecimal commentscore) {
        this.commentscore = commentscore;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getLangname() {
        return langname;
    }

    public void setLangname(String langname) {
        this.langname = langname == null ? null : langname.trim();
    }

    public Integer getLicenseprovider() {
        return licenseprovider;
    }

    public void setLicenseprovider(Integer licenseprovider) {
        this.licenseprovider = licenseprovider;
    }

    public String getTagscodes() {
        return tagscodes;
    }

    public void setTagscodes(String tagscodes) {
        this.tagscodes = tagscodes == null ? null : tagscodes.trim();
    }

    public String getThreesource() {
        return threesource;
    }

    public void setThreesource(String threesource) {
        this.threesource = threesource == null ? null : threesource.trim();
    }

    public Date getCinemashowndate() {
        return cinemashowndate;
    }

    public void setCinemashowndate(Date cinemashowndate) {
        this.cinemashowndate = cinemashowndate;
    }

    public Date getAdviewtime() {
        return adviewtime;
    }

    public void setAdviewtime(Date adviewtime) {
        this.adviewtime = adviewtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReporturl() {
        return reporturl;
    }

    public void setReporturl(String reporturl) {
        this.reporturl = reporturl == null ? null : reporturl.trim();
    }

    public Movie clone() {
        Movie o = null;
        try{
            o = (Movie)super.clone();
        }catch(CloneNotSupportedException ex){
            ex.printStackTrace();
        }
        return o;
    }

	@Override
	public String toString() {
		return "Movie [movieid=" + movieid + ", enname=" + enname + ", cnname=" + cnname + ", typecode=" + typecode
				+ ", director=" + director + ", actors=" + actors + ", scenarist=" + scenarist + ", producer="
				+ producer + ", smallposter=" + smallposter + ", bigposter=" + bigposter + ", areacode=" + areacode
				+ ", langcode=" + langcode + ", duration=" + duration + ", introduction=" + introduction + ", keywords="
				+ keywords + ", commentscore=" + commentscore + ", provider=" + provider + ", description="
				+ description + ", active=" + active + ", creator=" + creator + ", createtime=" + createtime
				+ ", modifier=" + modifier + ", modifytime=" + modifytime + ", typename=" + typename + ", areaname="
				+ areaname + ", langname=" + langname + ", licenseprovider=" + licenseprovider + ", tagscodes="
				+ tagscodes + ", threesource=" + threesource + ", cinemashowndate=" + cinemashowndate + ", adviewtime="
				+ adviewtime + ", type=" + type + ", reporturl=" + reporturl + "]";
	}
    
}