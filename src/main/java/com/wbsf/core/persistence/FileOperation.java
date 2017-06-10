package com.wbsf.core.persistence;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文件数据库实例
 * @author hubery
 *
 */
@Table(name = "file_operation_t")
public class FileOperation extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6798285913183240253L;

	/**
     * 附件主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 原始文件名
     */
    @Column(name = "original_file_name")
    private String originalFileName;

    /**
     * 文件说明
     */
    private String description;

    /**
     * 文件的MD5值
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件状态(0游离状态,1被引用，-1已过期)
     */
    private Integer state;

    /**
     * 过期时间
     */
    @Column(name = "`expired _date`")
    private Date expiredDate;

    /**
     * 文件存储路径
     */
    @Column(name = "file_uri")
    private String fileUri;

    /**
     * 下载次数
     */
    @Column(name = "download_times")
    private Long downloadTimes;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 修改人
     */
    @Column(name = "modify_by")
    private Long modifyBy;

    /**
     * 创建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 最后修改日期
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 逻辑删除标识(0正常,1删除,-1不可删除，)
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 获取附件主键
     *
     * @return id - 附件主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置附件主键
     *
     * @param id 附件主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 获取原始文件名
     *
     * @return original_file_name - 原始文件名
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    /**
     * 设置原始文件名
     *
     * @param originalFileName 原始文件名
     */
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName == null ? null : originalFileName.trim();
    }

    /**
     * 获取文件说明
     *
     * @return description - 文件说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文件说明
     *
     * @param description 文件说明
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取文件的MD5值
     *
     * @return md5 - 文件的MD5值
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置文件的MD5值
     *
     * @param md5 文件的MD5值
     */
    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    /**
     * 获取文件大小
     *
     * @return size - 文件大小
     */
    public Long getSize() {
        return size;
    }

    /**
     * 设置文件大小
     *
     * @param size 文件大小
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * 获取文件类型
     *
     * @return type - 文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文件类型
     *
     * @param type 文件类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取文件状态(0游离状态,1被引用，-1已过期)
     *
     * @return state - 文件状态(0游离状态,1被引用，-1已过期)
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置文件状态(0游离状态,1被引用，-1已过期)
     *
     * @param state 文件状态(0游离状态,1被引用，-1已过期)
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取过期时间
     *
     * @return expired _date - 过期时间
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * 设置过期时间
     *
     * @param expiredDate 过期时间
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * 获取文件存储路径
     *
     * @return file_uri - 文件存储路径
     */
    public String getFileUri() {
        return fileUri;
    }

    /**
     * 设置文件存储路径
     *
     * @param fileUri 文件存储路径
     */
    public void setFileUri(String fileUri) {
        this.fileUri = fileUri == null ? null : fileUri.trim();
    }

    /**
     * 获取下载次数
     *
     * @return download_times - 下载次数
     */
    public Long getDownloadTimes() {
        return downloadTimes;
    }

    /**
     * 设置下载次数
     *
     * @param downloadTimes 下载次数
     */
    public void setDownloadTimes(Long downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取修改人
     *
     * @return modify_by - 修改人
     */
    public Long getModifyBy() {
        return modifyBy;
    }

    /**
     * 设置修改人
     *
     * @param modifyBy 修改人
     */
    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    /**
     * 获取创建日期
     *
     * @return create_date - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取最后修改日期
     *
     * @return modify_date - 最后修改日期
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置最后修改日期
     *
     * @param modifyDate 最后修改日期
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取逻辑删除标识(0正常,1删除,-1不可删除，)
     *
     * @return delete_flag - 逻辑删除标识(0正常,1删除,-1不可删除，)
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置逻辑删除标识(0正常,1删除,-1不可删除，)
     *
     * @param deleteFlag 逻辑删除标识(0正常,1删除,-1不可删除，)
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 根据数据库uri资源路径构建file实例
     * @return
     */
    public File getFile(){
    	return new File(fileUri);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileName=").append(fileName);
        sb.append(", originalFileName=").append(originalFileName);
        sb.append(", description=").append(description);
        sb.append(", md5=").append(md5);
        sb.append(", size=").append(size);
        sb.append(", type=").append(type);
        sb.append(", state=").append(state);
        sb.append(", expiredDate=").append(expiredDate);
        sb.append(", fileUri=").append(fileUri);
        sb.append(", downloadTimes=").append(downloadTimes);
        sb.append(", createBy=").append(createBy);
        sb.append(", modifyBy=").append(modifyBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", modifyDate=").append(modifyDate);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append("]");
        return sb.toString();
    }
    
}