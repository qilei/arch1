/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package me.lei.spring;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import me.lei.pagination.dto.datatables.BasePageCriteria;
import me.lei.pagination.dto.datatables.PagingCriteria;
import me.lei.pagination.dto.datatables.SearchField;
import me.lei.pagination.dto.datatables.SortField;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * resolver paramArgument with annotation.
 * </p>
 *
 * @author mumu @yfyang
 * @version 1.0 2013-09-05 10:44 PM
 * @see
 * @see
 * @since JDK 1.5
 */
public class TableParamArgumentResolver implements HandlerMethodArgumentResolver {
    /**
     * Information for DataTables to use for rendering.
     */
    private static final String S_ECHO = "p";
    /**
     * Display start point in the current data set.
     */
    private static final String I_DISPLAY_START = "iDisplayStart";
    /**
     * Number of records that the table can display in the current draw.
     * It is expected that the number of records returned will be equal to this number, unless the server has fewer records to return.
     */
    private static final String I_DISPLAY_LENGTH = "pageSize";
    /**
     * Number of columns to sort on
     */
    private static final String I_SORTING_COLS = "iSortingCols";
    /**
     * Column being sorted on (you will need to decode this number for your database)
     */
    private static final String I_SORT_COLS = "iSortCol_";
    /**
     * Direction to be sorted - "desc" or "asc".
     */
    private static final String S_SORT_DIR = "sSortDir_";
    /**
     * The value specified by mDataProp for each column.
     * This can be useful for ensuring that the processing of data is independent from the order of the columns.
     */
    private static final String S_DATA_PROP = "mDataProp_";
    /**
     * Individual column filter
     */
    private static final String S_SEACHE_VAL = "sSearch_";
    /**
     * True if the individual column filter should be treated as a regular expression for advanced filtering, false if not
     */
    private static final String B_REGEX = "bRegex_";
    /**
     * Indicator for if a column is flagged as sortable or not on the client-side
     */
    private static final String B_SORTTABLE = "bSortable_";
    /**
     * Global search field value
     */
    private static final String S_SEARCH = "sSearch";
    /**
     * Number of columns being displayed (useful for getting individual column search info)
     */
    private static final String I_COLUMNS = "iColumns";
    /**
     * Hump Split colum name
     */
    private final boolean humpSplit;

    public TableParamArgumentResolver() {
        humpSplit = false;
    }

    /**
     * Instantiates a new Table param argument resolver.
     *
     * @param humpSplit the hump split
     */
    public TableParamArgumentResolver(boolean humpSplit) {
        this.humpSplit = humpSplit;
    }


    /**
     * Gets sort fileds.
     *
     * @param httpRequest the http request
     * @return the sort fileds
     */
    private List<SortField> getSortFileds(final HttpServletRequest httpRequest) {

        String sSortingCols = httpRequest.getParameter(I_SORTING_COLS);

        int iSortingCols = Integer.parseInt(sSortingCols);
        final List<SortField> sortFields = Lists.newArrayListWithCapacity(iSortingCols);
        String sSortDir;
        String sColName;
        String sSortCol;
        for (int colCount = 0; colCount < iSortingCols; colCount++) {
            sSortCol = httpRequest.getParameter(I_SORT_COLS + colCount);
            sSortDir = httpRequest.getParameter(S_SORT_DIR + colCount);
            sColName = httpRequest.getParameter(S_DATA_PROP + sSortCol);
            sColName = humpSplit
                    ? CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sColName)
                    : sColName;
            sortFields.add(new SortField(sColName, sSortDir));
        }
        return sortFields;
    }

    /**
     * Gets search param.
     *
     * @param httpRequest the http request
     * @return the search param
     */
    private List<SearchField> getSearchParam(final HttpServletRequest httpRequest) {
        int iColumns = Integer.valueOf(httpRequest.getParameter(I_COLUMNS));
        final List<SearchField> searchFields = Lists.newArrayListWithCapacity(iColumns);
        boolean regex;
        boolean searchable;
        String searchValue;
        String sColName;
        final String sSearch = httpRequest.getParameter(S_SEARCH);
        for (int col = 0; col < iColumns; col++) {
            searchValue = httpRequest.getParameter(S_SEACHE_VAL + col);
            sColName = httpRequest.getParameter(S_DATA_PROP + col);
            sColName = humpSplit
                    ? CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sColName)
                    : sColName;
            if (!Strings.isNullOrEmpty(searchValue)) {
                regex = Boolean.valueOf(httpRequest.getParameter(B_REGEX + col));
                searchable = Boolean.valueOf(httpRequest.getParameter(B_SORTTABLE + col));
                searchFields.add(new SearchField(sColName, regex, searchable, searchValue));
            } else if (!Strings.isNullOrEmpty(sSearch)) {
                searchFields.add(new SearchField(sColName, false, false, sSearch));
            }
        }
        return searchFields;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TableParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {

        String name = ModelFactory.getNameForParameter(parameter);
        Object attribute = (mavContainer.containsAttribute(name)) ?
                mavContainer.getModel().get(name) : createAttribute(name, parameter, binderFactory, request);

        WebDataBinder binder = binderFactory.createBinder(request, attribute, name);
        if (binder.getTarget() != null) {
            bindRequestParameters(binder, request);
            validateIfApplicable(binder, parameter);
            if (binder.getBindingResult().hasErrors()) {
                if (isBindExceptionRequired(binder, parameter)) {
                    throw new BindException(binder.getBindingResult());
                }
            }
        }

        // Add resolved attribute and BindingResult at the end of the model

        Map<String, Object> bindingResultModel = binder.getBindingResult().getModel();
        mavContainer.removeAttributes(bindingResultModel);
        mavContainer.addAllAttributes(bindingResultModel);

        Object result = binder.getTarget();
        if(result instanceof BasePageCriteria){

            String sEcho = request.getParameter(S_ECHO);
            String sDisplayStart = request.getParameter(I_DISPLAY_START);
            String sDisplayLength = request.getParameter(I_DISPLAY_LENGTH);

            int iEcho = Strings.isNullOrEmpty(sEcho)? 1: Integer.parseInt(sEcho);
            int iDisplayLength =Strings.isNullOrEmpty(sDisplayLength) ? PagingCriteria.DEFAULT_SIZE : Integer.parseInt(sDisplayLength);

            int iDisplayStart = (iEcho-1)*iDisplayLength;

            PagingCriteria pageCriteria = PagingCriteria.createCriteria(iDisplayStart, iDisplayLength, iEcho);
            ((BasePageCriteria) result).setPage(pageCriteria);
        }

        return result;
//        return PagingCriteria.getDefaultCriteria();
    }
    protected Object createAttribute(String attributeName, MethodParameter parameter,
                                     WebDataBinderFactory binderFactory,  NativeWebRequest request) throws Exception {

        return BeanUtils.instantiateClass(parameter.getParameterType());
    }

    /**
     * Extension point to bind the request to the target object.
     * @param binder the data binder instance to use for the binding
     * @param request the current request
     */
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        ServletRequest servletRequest = request.getNativeRequest(ServletRequest.class);
        ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
        servletBinder.bind(servletRequest);
    }

    /**
     * Validate the model attribute if applicable.
     * <p>The default implementation checks for {@code @javax.validation.Valid}.
     * @param binder the DataBinder to be used
     * @param parameter the method parameter
     */
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        Annotation[] annotations = parameter.getParameterAnnotations();
        for (Annotation annot : annotations) {
            if (annot.annotationType().getSimpleName().startsWith("Valid")) {
                Object hints = AnnotationUtils.getValue(annot);
                binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
                break;
            }
        }
    }

    protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
        int i = parameter.getParameterIndex();
        Class<?>[] paramTypes = parameter.getMethod().getParameterTypes();
        boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));

        return !hasBindingResult;
    }
}
