package com.sl.domain.dto.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

/**
 * dataTables分页类
 *
 * @project fooding-mailcenter
 * @Author baowb
 * @Since 2015年9月9日 下午4:27:03
 * @Version 2.0.0
 */
@ApiModel(value = "Pagination", description = "分页信息")
public class Pagination<T> implements Pageable {

	@ApiModelProperty(value = "一页数据")
	private int pageSize = 20;

	@ApiModelProperty(value = "一共有多少条数据")
	private long totalRecords;

	@ApiModelProperty(value = "当前页")
	private int currentPage;

	@ApiModelProperty(value = "排序字段")
	private String column;

	@ApiModelProperty(value = "升序降序")
	private String order;

	@ApiModelProperty(value = "数据集合")
	private List<T> data;

	@ApiModelProperty(value = "显示哪些字段")
	private List<String> includeFields;

	@ApiModelProperty(value = "是否分页")
	private Boolean isPagable = Boolean.TRUE;

	@ApiModelProperty(value = "表单类型标识")
	private String identity;

	@ApiModelProperty(value = "true:下一页（lastId必传），false:上一页")
	private Boolean nextPage;

	@ApiModelProperty(value = "请求参数")
	private List<String> params;

	@ApiModelProperty(value = "请求参数文字描述")
	private String paramsTip;

	@ApiModelProperty(value = "排除哪些字段")
	private List<String> excludes;

	@ApiModelProperty(value = "包含哪些字段")
	private List<String> includes;

	@ApiModelProperty(value = "搜索关键字(BaseMultilingualEntity对象使用)")
	private String searchKey;

	@ApiModelProperty(value = "国际化")
	private Boolean internationalize;

	@ApiModelProperty(value = "指定语种")
	private String language;

	@ApiModelProperty(value = "输出字段")
	private String output;

	public String getOutput() {

		return output;
	}

	public void setOutput(String output) {

		this.output = output;
	}

	public String getParamsTip() {

		return paramsTip;
	}

	public void setParamsTip(String paramsTip) {

		this.paramsTip = paramsTip;
	}

	public String getLanguage() {

		return language;
	}

	public void setLanguage(String language) {

		this.language = language;
	}

	public Boolean getInternationalize() {

		return internationalize;
	}

	public void setInternationalize(Boolean internationalize) {

		this.internationalize = internationalize;
	}

	/**
	 *
	 */
	public Pagination() {

		super();
	}

	public Pagination(int currentPage, int pageSize, long totalRecords) {

		this.setCurrentPage(currentPage);
		this.setPageSize(pageSize);
		this.setTotalRecords(totalRecords);
	}

	public String getSearchKey() {

		return searchKey;
	}

	public void setSearchKey(String searchKey) {

		this.searchKey = searchKey;
	}

	public List<String> getIncludes() {

		return includes;
	}

	public void setIncludes(List<String> includes) {

		this.includes = includes;
	}

	public List<String> getExcludes() {

		return excludes;
	}

	public void setExcludes(List<String> excludes) {

		this.excludes = excludes;
	}

	public Boolean getNextPage() {

		return nextPage;
	}

	public void setNextPage(Boolean nextPage) {

		this.nextPage = nextPage;
	}

	public List<String> getIncludeFields() {

		return includeFields;
	}

	public void setIncludeFields(List<String> includeFields) {

		this.includeFields = includeFields;
	}

	public void setPage(Page<T> page) {

		setData(page.getContent());
		setTotalRecords(page.getTotalElements());
	}

	public int getPageSize() {

		if (null == getIsPagable() || !getIsPagable())
			return Integer.MAX_VALUE;
		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public long getTotalRecords() {

		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {

		this.totalRecords = totalRecords;
	}

	public int getTotalPages() {

		return (int) ( ( getTotalRecords() % getPageSize() > 0 ) ?
				( getTotalRecords() / getPageSize() + 1 ) :
				getTotalRecords() / getPageSize() );
	}

	public int getCurrentPage() {

		if (null == getIsPagable() || !getIsPagable())
			return 1;
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {

		this.currentPage = currentPage;
	}

	public List<T> getData() {

		if (data == null)
			return new ArrayList<T>();
		return data;
	}

	public void setData(List<T> data) {

		this.data = data;
	}

	public String getColumn() {

		if (StringUtils.isEmpty(column))
			return "createDate";
		return column;
	}

	public void setColumn(String column) {

		this.column = column;
	}

	public String getOrder() {

		if (StringUtils.isEmpty(order))
			return Direction.DESC.name();
		return order;
	}

	public void setOrder(String order) {

		this.order = order;
	}

	public Boolean getIsPagable() {

		return isPagable;
	}

	public void setIsPagable(Boolean isPagable) {

		this.isPagable = isPagable;
	}

	@Override
	public int getPageNumber() {

		int start = getCurrentPage() - 1;
		if (start < 0)
			start = 0;
		return start;
	}

	@Override
	@JsonIgnore
	public Sort getSort() {

		Sort sort = new Sort(Direction.fromString(getOrder()), getColumn());
		return sort;
	}

	@Override
	public Pageable next() {

		return null;
	}

	@Override
	public Pageable previousOrFirst() {

		return null;
	}

	@Override
	public Pageable first() {

		return null;
	}

	@Override
	public boolean hasPrevious() {

		return ( getOffset() / getPageSize() ) > 0;
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {

		return identity;
	}

	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {

		this.identity = identity;
	}

	public List<String> getParams() {

		return params;
	}

	public void setParams(List<String> params) {

		this.params = params;
	}
	@Override
	public int getOffset() {

		return getPageNumber() * getPageSize();
	}
}
