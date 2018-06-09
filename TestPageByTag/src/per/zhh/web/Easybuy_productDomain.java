package per.zhh.web;

import java.io.Serializable;

/**
 * 产品实体类
 * @author 周昊辉
 * @version
 */
public class Easybuy_productDomain implements Serializable{

	/**
	 * 自动生成一个序列号
	 */
	private static final long serialVersionUID = -5320764140120048943L;
	
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer stock;
	private Integer categoryLevel1Id;
	private Integer categoryLevel2Id;
	private Integer categoryLevel3Id;
	private String fileName;
	private String isDelete;//(1：删除 0：未删除)
	/**
	 * min constructor
	 */
	public Easybuy_productDomain() {
		
	}
	/**
	 * max constructor
	 * @param name 商品名称
	 * @param description 描述
	 * @param price 价格
	 * @param stock 库存
	 * @param categoryLevel1Id 分类1
	 * @param categoryLevel2Id 分类2
	 * @param categoryLevel3Id 分类3
	 * @param fileName 文件名称
	 * @param isDelete 是否删除
	 */
	public Easybuy_productDomain(String name, String description, Double price,
			Integer stock, Integer categoryLevel1Id, Integer categoryLevel2Id,
			Integer categoryLevel3Id, String fileName, String isDelete) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.categoryLevel1Id = categoryLevel1Id;
		this.categoryLevel2Id = categoryLevel2Id;
		this.categoryLevel3Id = categoryLevel3Id;
		this.fileName = fileName;
		this.isDelete = isDelete;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getCategoryLevel1Id() {
		return categoryLevel1Id;
	}
	public void setCategoryLevel1Id(Integer categoryLevel1Id) {
		this.categoryLevel1Id = categoryLevel1Id;
	}
	public Integer getCategoryLevel2Id() {
		return categoryLevel2Id;
	}
	public void setCategoryLevel2Id(Integer categoryLevel2Id) {
		this.categoryLevel2Id = categoryLevel2Id;
	}
	public Integer getCategoryLevel3Id() {
		return categoryLevel3Id;
	}
	public void setCategoryLevel3Id(Integer categoryLevel3Id) {
		this.categoryLevel3Id = categoryLevel3Id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}
