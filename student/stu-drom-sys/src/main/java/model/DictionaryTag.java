package model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据字典标签
 */
@Getter
@Setter
@ToString
public class DictionaryTag {
    
    private Integer id;

    /**
     * 键
     */
    private String dictionaryTagKey;

    /**
     * 值
     */
    private String dictionaryTagValue;

    /**
     * 描述
     */
    private String dictionaryTagDesc;

    /**
     * 数据字典id
     */
    private Integer dictionaryId;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictionaryTagKey() {
        return dictionaryTagKey;
    }

    public void setDictionaryTagKey(String dictionaryTagKey) {
        this.dictionaryTagKey = dictionaryTagKey;
    }

    public String getDictionaryTagValue() {
        return dictionaryTagValue;
    }

    public void setDictionaryTagValue(String dictionaryTagValue) {
        this.dictionaryTagValue = dictionaryTagValue;
    }

    public String getDictionaryTagDesc() {
        return dictionaryTagDesc;
    }

    public void setDictionaryTagDesc(String dictionaryTagDesc) {
        this.dictionaryTagDesc = dictionaryTagDesc;
    }

    public Integer getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Integer dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}