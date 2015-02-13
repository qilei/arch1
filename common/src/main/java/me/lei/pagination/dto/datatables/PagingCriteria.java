/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package me.lei.pagination.dto.datatables;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * jQuery DataTables's PagingCriteria.
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-09-05 10:37 PM
 * @since JDK 1.5
 */
public class PagingCriteria {
    /**
     * The constant DEFAULT_CRITERIA.
     */
    private static final PagingCriteria DEFAULT_CRITERIA = new PagingCriteria(0, PagingCriteria.DEFAULT_SIZE, 2);
    /**
     * default page size.
     */
    public static final int DEFAULT_SIZE = 10;
    /**
     * start display
     */
    private int displayStart;
    /**
     * disaplaySize
     */
    private int displaySize;
    /**
     * sort fields
     */
    private List<SortField> sortFields;
    /**
     * search field information
     */
    private List<SearchField> searchFields;
    /**
     * pageNumber
     */
    private int pageNumber;

    public PagingCriteria(){

    }

    /**
     * Instantiates a new Paging criteria.
     *
     * @param displayStart the display start
     * @param displaySize  the display size
     * @param pageNumber   the page number
     * @param sortFields   the sort fields
     * @param searchFields the search information
     */
    public PagingCriteria(int displayStart
            , int displaySize
            , int pageNumber
            , List<SortField> sortFields
            , List<SearchField> searchFields) {
        this.displayStart = displayStart;
        this.displaySize = displaySize;
        this.pageNumber = pageNumber;
        this.sortFields = sortFields;
        this.searchFields = searchFields;
    }

    /**
     * Instantiates a new Paging criteria and not sort\search.
     *
     * @param displaySize  the display size
     * @param displayStart the display start
     * @param pageNumber   the page number
     */
    public PagingCriteria(int displayStart
            , int displaySize
            , int pageNumber) {
        this.displaySize = displaySize;
        this.displayStart = displayStart;
        this.pageNumber = pageNumber;
        this.searchFields = Lists.newArrayListWithCapacity(0);
        this.sortFields = Lists.newArrayListWithCapacity(0);
    }

    /**
     * Instantiates a new Paging criteria and no search.
     *
     * @param displaySize  the display size
     * @param displayStart the display start
     * @param pageNumber   the page number
     * @param sortFields   the sort fields
     */
    public PagingCriteria(int displayStart
            , int displaySize
            , int pageNumber
            , List<SortField> sortFields) {
        this.sortFields = sortFields;
        this.displaySize = displaySize;
        this.displayStart = displayStart;
        this.pageNumber = pageNumber;
        this.searchFields = Lists.newArrayListWithCapacity(0);
    }

    /**
     * Instantiates a new Paging criteria and no sort.
     *
     * @param displayStart the display start
     * @param displaySize  the display size
     * @param searchFields the search fields
     * @param pageNumber   the page number
     */
    public PagingCriteria(int displayStart
            , int displaySize
            , List<SearchField> searchFields
            , int pageNumber) {
        this.displayStart = displayStart;
        this.displaySize = displaySize;
        this.searchFields = searchFields;
        this.pageNumber = pageNumber;
        this.sortFields = Lists.newArrayListWithCapacity(0);
    }

    /**
     * Create criteria with all paramter.
     *
     * @param displayStart the display start
     * @param displaySize  the display size
     * @param pageNumber   the page number
     * @param sortFields   the sort fields
     * @param searchFields the search fields
     * @return the paging criteria
     */
    public static PagingCriteria createCriteriaWithAllParamter(int displayStart
            , int displaySize
            , int pageNumber
            , List<SortField> sortFields
            , List<SearchField> searchFields) {
        return new PagingCriteria(displayStart, displaySize, pageNumber, sortFields, searchFields);
    }

    /**
     * Create criteria with sort.
     *
     * @param displayStart the display start
     * @param displaySize  the display size
     * @param pageNumber   the page number
     * @param sortFields   the sort fields
     * @return the paging criteria
     */
    public static PagingCriteria createCriteriaWithSort(int displayStart, int displaySize, int pageNumber
            , List<SortField> sortFields) {
        return new PagingCriteria(displayStart, displaySize, pageNumber, sortFields);
    }

    /**
     * Create criteria with search.
     *
     * @param displayStart the display start
     * @param displaySize  the display size
     * @param pageNumber   the page number
     * @param searchFields the search fields
     * @return the paging criteria
     */
    public static PagingCriteria createCriteriaWithSearch(int displayStart, int displaySize, int pageNumber
            , List<SearchField> searchFields) {
        return new PagingCriteria(displayStart, displaySize, searchFields, pageNumber);
    }

    /**
     * Create criteria.
     *
     * @param displayStart the display start
     * @param displaySize  the display size
     * @param pageNumber   the page number
     * @return the paging criteria
     */
    public static PagingCriteria createCriteria(int displayStart, int displaySize, int pageNumber) {
        return new PagingCriteria(displayStart, displaySize, pageNumber);
    }

    /**
     * Get default criteria.
     *
     * @return the paging criteria
     */
    public static PagingCriteria getDefaultCriteria() {
        return DEFAULT_CRITERIA;
    }

    /**
     * Gets display start.
     *
     * @return the display start
     */
    public Integer getDisplayStart() {
        return displayStart;
    }

    /**
     * Gets display size.
     *
     * @return the display size
     */
    public Integer getDisplaySize() {
        return displaySize;
    }

    /**
     * Gets search fields.
     *
     * @return the search fields
     */
    public List<SearchField> getSearchFields() {
        if (this.searchFields == null) {
            return Lists.newArrayListWithCapacity(0);
        }
        return Collections.unmodifiableList(searchFields);
    }

    /**
     * Gets sort fields.
     *
     * @return the sort fields
     */
    public List<SortField> getSortFields() {
        if (this.sortFields == null) {
            return Lists.newArrayListWithCapacity(0);
        }
        return Collections.unmodifiableList(sortFields);
    }

    /**
     * Gets page number.
     *
     * @return the page number
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setDisplayStart(int displayStart) {
        this.displayStart = displayStart;
    }

    public void setDisplaySize(int displaySize) {
        this.displaySize = displaySize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
